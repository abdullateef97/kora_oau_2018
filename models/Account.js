const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const accountSchema = new Schema({
    name : {
        required : true,
        type: String
    },
    account_number: {
        required: true,
        type: String
    },
    bank_code: {
        type: String,
        required: true
    },
    user_id: {
        type: String,
        required: true
    },
    recipient_code: {
        type: String,
        required: true
    }
}, {
    timestamps: true,
});


const Account = module.exports = mongoose.Schema('Account', accountSchema);