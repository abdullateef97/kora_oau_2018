const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const WalletSchema = Schema({
    name: {type: String, default: 'Standard'},
    owners: [{
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: true
    }],
    balance: {
        type: Number,
        default: 0.00
    },
    end_date: {type: String, default: null},
    description: {type: String, default: 'Standard Wallet'},
    deduction_interval: {type: Number, default: null},
    deduction_amount: {type: Number, default: null},
    creator: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    },
    type: {type: 'Number', default: 0}
});

const Wallet = module.exports = mongoose.model('Wallet', WalletSchema);