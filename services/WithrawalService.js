const paystack = require('paystack')(process.env.paystack)
const Deposit = require('../models/Deposit');
const Wallet = require('../models/Wallet');
const Withrawal = require('../models/Withrawal');
const Account = require('../models/Account')
const PayStackHelper = require('../lib/paystackHelper');

const addAccountNumber = (accountBody, user_id ) =>  new Promise((resolve, reject) => {
    _pushAccountToPaystack(accountBody).then(paystackResponse => {
        accountBody.type = 'nuban';
        accountBody.recipient_code= paystackResponse.data.recipient_code;
        accountBody.user_id= userId;

        accountBody.save().then((account) => resolve(account)).catch((err) => reject(err));

    })
})

const _pushAccountToPaystack = (accountBody) => {
    const url = "https://api.paystack.co/transferrecipient";
    PayStackHelper.postToPaystack(url,accountBody).then(response => {
        const {body} = response
        if(!body.status){
            throw new Error(body.message);
        }
        return resolve(body);
    })
}











module.exports = {
    addAccountNumber,
}