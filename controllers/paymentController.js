/**
 * Created by mayomi on 7/28/18 by 5:17 AM.
 */
const config = require('../config/main');
const response = require('../config/helpers');
const paystack = require('paystack')(config.paystack_secret);
const unirest = require('unirest');
const logger = require('../config/logger');

const formatResponse = (data) => {
  return {

    auth_code: data.authorization.authorization_code,
    amount: data.amount / 100,
    paid_at: data.paid_at,
    last4: data.authorization.last4,
    bank: data.authorization.bank,
    created_at: data.created_at,
    transaction_date: data.transaction_date,
  };
};

const saveAuth2Contract = (params) => {
  unirest.post('https://kontribute.herokuapp.com/api/contract')
    .headers({'Content-Type': 'application/json'})
    .send({
      user_id: params.user_id,
      auth_code: params.auth_code
    })
    .end(function (response) {
      logger.info(response.body);
    });
};

exports.getAccessCode = (req, res)=>{
  const userId = req.body.user_id;
  const amount = req.body.amount;

  const amountInKobo = Number(amount) * 100;

  const email = req.body.email;

  // all fields supported by this call can be gleaned from
  // https://developers.paystack.co/reference#initialize-a-transaction
  paystack.transaction.initialize({
    email:     email,        // a valid email address
    amount:    amountInKobo, // only kobo and must be integer
    metadata:  {
      custom_fields:[
        {
          "display_name": email
        },
      ]
    }
  },function(error, body) {
    if(error){
      return res.json(error);
    }
    response.success(res, 'payment intialized', body.data)
  });
};

exports.verifyTransaction = (req, res) => {
  const reference = req.params.reference;
  const saveAuthCode = req.params.save_auth;
  const user_id = req.params.user_id;

  paystack.transaction.verify(reference,
    function(error, body) {
      if(error){
        return res.json(error);
      }

      const data = body.data;
      if(saveAuthCode==='true' && data.authorization.authorization_code) {
        //call save to contract

        console.log('running...');
        const params = {
          user_id: user_id,
          auth_code: data.authorization.authorization_code,
        };
        saveAuth2Contract(params);

      }

      //res.send(data)
      return response.success(res, 'transaction verified', formatResponse(data));
    });
};

exports.chargeReturningCustomer = (req, res) => {
  const authCode = req.body.auth_code;
  const email = req.body.email;
  const amount =  req.body.amount*100; //convert to kobo

  paystack.transaction.charge({
      email: email,
      authorization_code: authCode,
      amount: amount,
    },
    function(error, body) {
      if(error){
        return res.json(error);
      }

      const data = body.data;
      return response.success(res, 'transaction verified', formatResponse(data));
    });
};
