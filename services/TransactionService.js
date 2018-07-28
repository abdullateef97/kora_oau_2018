
const Transaction = require('../models/Transaction');
const User = require('../models/User');
const Wallet = require('../models/Wallet');


createTransaction = (transactionObj, userId) => new Promise((resolve, reject) => {
    User.findOne({email: transactionObj.receiver_email}).populate('wallets').then(user => {
        transactionObj.sender_id = userId;
        transactionObj.receiver_id = user._id;
        const {wallets} = user
        _deductSenderAccount(transactionObj.sender_id, transactionObj.amount).then(() => {
            
            transactionObj.save().then(transactionResponse => {
                _creditReceiverAccount(wallets, transactionObj.receiver_id, transactionObj.amount).then(() => {
                    return resolve(transactionResponse);
                })
            })
        })
       
    })
}).catch(err => reject(err));


getAllTransactions = (user_id) => new Promise((resolve, reject) => {
    const conditions = { $or: [{ sender_id: user_id }, { receiver_id: user_id }] };
    return Transaction.find(conditions).then(trans => {
        if(!trans || trans.length <= 0) {throw new Error('No Transactions Exist')}
        return resolve(trans)
    }).catch(err => reject(err))
})

getOneTransaction = (trans_id) => new Promise((resolve, reject) => {
    return Transaction.findOne({_id: trans_id}).then(trans => {
        if(!trans || trans.length <= 0) throw new Error('No Transactions Exist');
        return resolve(trans);
    }).catch(err => reject(err));
})

_deductSenderAccount = ( sender_id, amount) => {
    return new Promise((resolve, reject) => {
        Wallet.findOne({creator: sender_id, name: 'Standard'}).then(wallet => {
            let balance = wallet.balance;
            balance = balance - parseInt(amount);
            if(balance< 0) {return reject({message: 'Not Suficient Balance'})}
            wallet.balance = balance;
            wallet.save().then(() => resolve()).catch(err => reject(err))
        })
    
})
}

_creditReceiverAccount = (wallets, receiver_id, amount) => new Promise((resolve, reject) => {
    Wallet.findOne({creator: receiver_id, name: 'Standard'}).then(wallet => {
        let balance = wallet.balance;
        balance+= parseInt(amount);
        wallet.balance = balance;
        wallet.save().then(() => resolve()).catch(err => reject(err))
    
    })
})

module.exports = {createTransaction, getAllTransactions, getOneTransaction}