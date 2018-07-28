const paystack = require('paystack')(process.env.paystack)
const Deposit = require('../models/Deposit');
const Wallet = require('../models/Wallet');
const PayStackHelper = require('../lib/paystackHelper');

const createDeposit = (depositObj, user_id) => new Promise((resolve, reject) => {
    const deposit = new Deposit({
        amount: depositObj.amount,
        user_id: user_id,
        user_email: depositObj.user_email
    })

    return deposit.save().then(deposit => {
        const payObj = {
            reference : deposit._id,
            amount: parseInt(deposit.amount) *100,
            email: deposit.user_email
        }
        const url = 'https://api.paystack.co/transaction/initialize'
        PayStackHelper.postToPaystack(url, payObj).then((response) => {
            const {body} = response
                if(!body.status){
                    throw new Error(body.message);
                }
                return resolve(body);
        })
    });
});

const verifyDeposit = (reference) => new Promise((resolve, reject) => {
    return paystack.transaction.verify(reference, (err, body) => {
        if(err) reject(err);
        if(body.data.status === 'success'){
            return _creditDepositorWallet(reference).then(response => resolve(response))
        }else{ 
            return reject({message: 'Deposit Failed'});
        }
    }).catch(err => reject(err));
});


const _creditDepositorWallet = (deposit_id) => new Promise((resolve, reject) => {
    Deposit.findOne({_id: deposit_id}).then(depositDetails => {
        if(!depositDetails || depositDetails == null){
            return reject({message: 'Invalid Reference Id'})
        }
        Wallet.findOne({creator: depositDetails.user_id, name: 'Standard'}).then(wallet => {
            wallet.balance += depositDetails.amount;
            wallet.save().then(() => resolve(wallet))
        })
    }).catch(err => reject(err));
})





module.exports = {
    createDeposit, verifyDeposit
}