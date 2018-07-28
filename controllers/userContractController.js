/**
 * Created by mayomi on 7/27/18 by 2:35 PM.
 */
const helper = require('../config/helpers');
const config = require('../config/main');
const sign = require('ethjs-signer').sign;
const logger = require('../config/logger');
const tokenABI = require('../config/abiToken');


const SignerProvider = require('ethjs-provider-signer');
const TransEth = require('ethjs-query');
const Eth = require('ethjs');

const privateKey = config.private_key;
//const privateKey = '0x5aa950ebc37b00d2ecac31d22b92c993f095390350a033e832b8c0970be3e37c';
const acctAddress = config.account_address;
const contractAddress = config.contract_address;

const provider = new SignerProvider(config.signer_and_http_provider, {
  signTransaction: (rawTx, cb) => cb(null, sign(rawTx, privateKey)),
  accounts: (cb) => cb(null, [acctAddress]),
});
const transEth = new TransEth(provider);


const eth = new Eth(new Eth.HttpProvider(config.signer_and_http_provider));

const byteCode = config.byte_code;
const contractInstance = eth.contract(tokenABI, byteCode, {from: acctAddress, gas: 3000000}).at(contractAddress);

const stringToHex = (str) => {

  //converting string into buffer
  let bufStr = Buffer.from(str, 'utf8');

  //with buffer, you can convert it into hex with following code
  return bufStr.toString('hex');

}

exports.getUserAuthCode = (req, res, next) => {
  let userId = req.body.user_id;

  contractInstance.getAuthCode(userId, {from: acctAddress}).then(
    response => {
      res.json({
        status: true,
        data: {auth_code: response['0']},
        message: "",
        j: response,
      });
    }).catch(e => res.json({
      status: false,
      data: {},
      message: e
    }
    )
  );
};


exports.addUserAuthCode = (req, res, next) => {
  console.log('runn2222');
  const userId = req.body.user_id;
  const authCode = req.body.auth_code;

  if(!authCode) {
     logger.info({
      status: false,
      message: 'All fields are required'
    });
  }

  if(!userId) {
     logger.info({
      status: false,
      message: 'All fields are required'
    });
  }

  transEth.sendTransaction(
    {
      from: acctAddress,
      to: contractAddress,
      gas: 5000000,
      data: helper.getUserAuthCode(
        helper.toHex(stringToHex(userId)),
        helper.toHex(stringToHex(authCode))
  )
    }).then(
    txHash => {
      logger.info(
        {
          status: true,
          data: {transaction_id: txHash},
          message: "Transaction Successfully Sent"
        }
      );
    }).catch(
    e => logger.info(
      {
        status: false,
        data: {},
        message: e
      }
    )
  );

};


exports.updateUserAuthCode = (req, res, next) => {
  let userId = req.params.user_id;
  transEth.sendTransaction(
    {
      from: acctAddress,
      to: contractAddress,
      gas: 3000000,
      data: helper.getUserAuthCode(
        helper.toHex(stringToHex(userId)))
    }).then(
    txHash => {
      logger.info(
        {
          status: true,
          data: {transaction_id: txHash},
          message: "Transaction Successfully Sent"
        }
      );
    }).catch(
    e => logger.info(
      {
        status: false,
        data: {},
        message: e
      }
    )
  );
}
