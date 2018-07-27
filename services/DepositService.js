const paystack = require('paystack')(process.env.paystack)
const Deposit = require('../models/Deposit');
const Wallet = require('../models/Wallet');

const createDeposit = (depositObj, user_id) => new Promise((resolve, reject) => {
    const deposit = new Deposit({
        amount: depositObj.amount,
        user_id: user_id,
        user_email: depositObj.user_email
    })

    return deposit.save().then(deposit => {
        paystack.transaction.initialize({
            reference : deposit._id,
            amount: parseInt(deposit.amount) *100,
            email: deposit.user_email
        }, (err, body) => {
            if(err) return reject(err);
            return resolve(body.data);
        });
    });
});





module.exports = {
    createDeposit
}