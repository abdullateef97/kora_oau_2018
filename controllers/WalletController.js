
const _ = require('lodash');
const validator = require('validator');
const User = require('../models/User');
const Wallet = require('../models/Wallet');
const {sendSuccess, sendError, setUserInfo, generateUserToken} = require('./baseController');
const Constants = require('../constants/constants');
const ResponseMessages = require('../constants/responseMessages');
const WalletService = require('../services/WalletService');



const createKolo = (req, res) => {
    const {body} = req;
    const user_id = req.user_Id;

    WalletService.createKolo(body, user_id).then(wallet => sendSuccess(res, wallet, 'Kolo Succesfully Created'))
                    .catch(err => sendError(res, err, 'Kolo Not Created'))

}

const createAajo  = (re, res) => {
    const {body} = req;
    const user_id = req.user_Id;
    WalletService.createAajo(body, user_id).then(wallet => sendSuccess(res, wallet, 'Aajo Succesfully Created'))
    .catch(err => sendError(res, err, 'Aajo Not Created'))
}

const getAllWallets = (req, res) => {
    const user_id = req.user_Id;
    WalletService.getAllWallets(user_id).then(wallets => sendSuccess(res, wallets, 'Wallets Fetched'))
                    .catch(err => sendError(res, err, 'Wallets Not Found'));
}

const getAajoUrl = (req, res) => {
    const user_id = req.user_Id;
    const wallet_id = req.params.wallet_id;

   const url =  WalletService.getAajoUrl(user_id, wallet_id);
   return sendSuccess(res, {url}, 'Url gen');
}



module.exports ={createKolo, getAllWallets, createAajo, getAajoUrl}