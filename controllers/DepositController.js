const _ = require('lodash');
const validator = require('validator');
const User = require('../models/User');
const Wallet = require('../models/Wallet');
const Deposit = require('../models/Deposit')
const {sendSuccess, sendError, setUserInfo, generateUserToken} = require('./base.ctrl');
const Constants = require('../constants/constants');
const ResponseMessages = require('../constants/responseMessages');
const WalletService = require('../services/WalletService');
const DepositService = require('../services/DepositService');


const createDeposit = (req, res) => {
    const amount = req.body.amount;
    const user_email = req.body.user_email;
    const user_id = req.body.user_id;


    if(!req.body)  return sendError(res, null, ResponseMessages.ALL_FIELDS_REQUIRED, 400);
    if(!req.body.user_email) return sendError(res, null, 'User_Email Not Specified', 400);
    if(!req.body.amount) return sendError(res,  null, 'AMount Not Specified', 400);
    DepositService.createDeposit({amount, user_email}, user_id).then(deposit => {
        return sendSuccess(res, deposit, "Deposit Created");
    }).catch(err => {
        return sendError(res, err, 'Deposit Failed');
    })
}

module.exports = {
    createDeposit
}