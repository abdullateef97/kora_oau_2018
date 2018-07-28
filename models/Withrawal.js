const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const WithrawalSchema = new Schema({
    amount: {
        type: Number,
        required: true
    },
    status: {
        type: Number,
    },
    user_id: {
        type: String,
        required: true
    },
    account_id: {
        type: String,
        required: true
    },
    recipient: {
        type: String,
        required: true
    },
    transfer_code: {
        type: String,
        required: true
    },
    reason: {
        type: String
    }
}, {
    timestamps: true,
});

const Withrawal = module.exports = mongoose.model('Withdrawal', WithrawalSchema);