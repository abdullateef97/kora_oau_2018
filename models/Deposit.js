const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const depositSchema = new Schema({
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
    user_email: {
        type: String,
        required: true
    }
}, {
    timestamps: true,
});

const Deposit = module.exports = mongoose.model('Deposit', depositSchema);