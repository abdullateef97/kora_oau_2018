const paystack = require('paystack')(process.env.paystack)
const Deposit = require('../models/Deposit');
const Wallet = require('../models/Wallet');
const Withdrawal = require('../models/Withrawal');
const Account = require('../models/Account')
const PayStackHelper = require('../lib/paystackHelper');

const addAccountNumber = (accountBody, user_id ) =>  new Promise((resolve, reject) => {
    _pushAccountToPaystack(accountBody).then(paystackResponse => {
        accountBody.type = 'nuban';
        accountBody.recipient_code= paystackResponse.data.recipient_code;
        accountBody.user_id= user_id;

        accountBody.save().then((account) => resolve(account)).catch((err) => reject(err));

    }).catch(err => reject(err))
})

const createWithdrawal  = (withdrawalBody, user_id, account_id) => new Promise((resolve, reject) => {
    withdrawalBody.user_id = user_id;
    _updateWalletBalance(user_id, withdrawalBody.amount).then(user => {
        _fetchAccountDetails(account_id).then(account => {
            if(!account || account == null){ throw new Error('Invalid Account Id')};
            withdrawalBody.account_id = account_id;
            withdrawalBody.recipient = account.recipient_code;
            withdrawalBody.source = 'balance';
            withdrawalBody.currency = "NGN";
            withdrawalBody.amount = withdrawalBody.amount * 100;

            _pushWithdrawalToPayStack(withdrawalBody).then(withdrawalObj => {
                withdrawalBody.transfer_code = withdrawalObj.data.transfer_code;
                    withdrawalBody.amount = withdrawalObj.amount / 100
                    return _saveWithdrawal(withdrawalBody).then(withdrawalDetails => resolve(withdrawalDetails))
            })
        })
    }).catch(err => reject(err))
})

const _saveWithdrawal = (withdrawalBody) => {
    return withdrawalBody.save();
}

const _pushWithdrawalToPayStack = withdrawalBody => new Promise((resolve, reject) => {
    const url = "https://api.paystack.co/transfer";
    return PayStackHelper.postToPaystack(url, withdrawalBody)
    .then(response => {
        const {body} = response
        if(!body.status){
            return reject(body.message);
        }
        return resolve(body);
    })
})

const _fetchAccountDetails = (account_id) => {
    return Account.findOne({_id: account_id});
}

const _updateWalletBalance = (user_id, amount) => {
    return new Promise((resolve, reject) => {
        Wallet.findOne({creator: user_id, name: 'Standard'}).then(wallet => {
            console.log('w', wallet)
            if(!wallet || wallet.length <= 0){
                throw new Error('Invalid User Id')
            }
            let balance = wallet.balance;
            balance = balance - parseInt(amount);
            if(balance< 0) {return reject({message: 'Not Suficient Balance'})}
            wallet.balance = balance;
            wallet.save().then(() => resolve()).catch(err => reject(err))
        });
    })
}

const _pushAccountToPaystack = (accountBody) => {
    return new Promise((resolve, reject) => {
        const url = "https://api.paystack.co/transferrecipient";
        PayStackHelper.postToPaystack(url, accountBody).then(response => {
            const {body} = response
            if(!body.status){
                return reject({err : body.message});
            }
            return resolve(body);
        })
    })
    
}











module.exports = {
    addAccountNumber,createWithdrawal
}