const _ = require('lodash');
const validator = require('validator');
const User = require('../models/User');
const Wallet = require('../models/Wallet');
const Deposit = require('../models/Deposit');
const Account = require('../models/Account')
const Transaction = require('../models/Transaction')
const {sendSuccess, sendError, setUserInfo, generateUserToken} = require('./base.ctrl');
const Constants = require('../constants/constants');
const ResponseMessages = require('../constants/responseMessages');
const WithrawalService = require('../services/WithrawalService');

const addAccountNumber = (req, res) => {
    const {body} = req;
    const user_id = body.user_id;
    if(!body) return sendError(res, null, 'Fields Must Not Be Empty', 400);
    if(!body.account_number) return sendError(res, null, 'Account Number Not Specified', 400);
    if(!body.bank_code) return sendError(res, null, 'Bank Code Not Specified', 400);
    if(!body.name) return sendError(res, null, 'Name not spec', 400);

    const accountObj = new Account({
        account_number: body.account_number,
        bank_code: bank_code,
        name: body.name
    })

    WithrawalService.addAccountNumber(accountObj, user_id).then(account => {
        return sendSuccess(res, account, 'Account successfully Created');
    }).catch(err => sendError(res, err,'Couldnt Add Account Number'));
}










module.exports = {
    addAccountNumber
}
