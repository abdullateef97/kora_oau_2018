const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const WalletSchema = Schema({
    name: {type: String, default: 'Standard'},
    owner: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    balance: {
        type: Number,
        default: 0.00
    }
});

const Wallet = module.exports = mongoose.model('Wallet', WalletSchema);