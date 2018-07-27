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





module.exports = {
    createDeposit
}