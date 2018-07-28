const _ = require('lodash');
const validator = require('validator');
const User = require('../models/User');
const Wallet = require('../models/Wallet');
const Deposit = require('../models/Deposit');
const Account = require('../models/Account')
const Withdrawal = require('../models/Withrawal')
const {sendSuccess, sendError, setUserInfo, generateUserToken} = require('./base.ctrl');
const Constants = require('../constants/constants');
const ResponseMessages = require('../constants/responseMessages');
const WithdrawalService = require('../services/WithrawalService');

const addAccountNumber = (req, res) => {
    const {body} = req;
    const user_id = body.user_id;
    if(!body) return sendError(res, null, 'Fields Must Not Be Empty', 400);
    if(!body.account_number) return sendError(res, null, 'Account Number Not Specified', 400);
    if(!body.bank_code) return sendError(res, null, 'Bank Code Not Specified', 400);
    if(!body.name) return sendError(res, null, 'Name not spec', 400);

    const accountObj = new Account({
        account_number: body.account_number,
        bank_code: body.bank_code,
        name: body.name
    })

    WithdrawalService.addAccountNumber(accountObj, user_id).then(account => {
        return sendSuccess(res, account, 'Account successfully Created');
    }).catch(err => {

        sendError(res, err,'Couldnt Add Account Number')
    });
}

const createWithdrawal = (req, res) => {
    const {body} = req;
    const {account_id} = req.params;
    const user_id = body.user_id;
    if(!body) return sendError(res, null, 'Fields Must Not Be Empty', 400);
    if(!body.amount) return sendError(res, null, 'Amount Not Specified', 400);
    // if(!body.bank_code) return sendError(res, null, 'Bank Code Not Specified', 400);
    // if(!body.name) return sendError(res, null, 'Name not spec', 400);
    const WithdrawalObj = new Withdrawal({
        amount: body.amount
    });
    WithdrawalService.createWithdrawal(WithdrawalObj, user_id, account_id).then(Withdrawal => sendSuccess(res,Withdrawal, 'Withdrawal Successful'))
            .catch(err => sendError(res, err, 'Couldnt init with'))
}










module.exports = {
    addAccountNumber, createWithdrawal
}
