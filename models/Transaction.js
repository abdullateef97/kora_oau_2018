const mongoose = require('mongoose');
const Schema = mongoose.Schema;


const transactionSchema = new Schema({
    amount: {
        type: Number,
        required: true
    },
    status: {
        type: Number,
    },
    receiver_phone: {
        type: String,
        required: true
    },
    sender_phone: {
        type: String,
        required: true
    },
    receiver_id: {
        type: String,
        required: true
    },
    sender_id: {
        type: String,
        required: true
    },
    remarks: {
        type: String
    }
}, {
    timestamps: true,
});

const Transaction = module.exports  = mongoose.model('Transaction', transactionSchema);