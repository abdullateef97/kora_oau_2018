const mongoose = require('mongoose'),
    Schema = mongoose.Schema;
const _ = require('lodash');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcrypt');

const Constants = require('../constants/constants');
const Wallet = require('./Wallet');

//================================
// User Schema
//================================
const UserSchema = new Schema({
    first_name: {
        type: String,
        required: true
    },
    last_name: {
        type: String,
        required: true
    },
    phone: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true
    },
    pin: {
        type: String,
        required: true
    },
    tokens: [{
        access: {
            type: String,
            required: true
        },
        token: {
            type: String,
            required: true
        }
    }],
    bank: {
        name: String,
        code: String
    },
    card: [],
    //Create card model- owner, cvv, expiry date, card no
    wallet: {
        type: Schema.Types.ObjectId,
        ref: 'Wallet'
    },
    kolo: [],
    //Create kolo model <= 3
    isAdmin: {type: Boolean, default: false},
}, {timestamp: true});


UserSchema.statics.findByToken = function (token) {
    const User = this;
    let decoded;

    try {
        decoded = jwt.verify(token, process.env.JWT_SECRET);
    } catch (e) {
        return Promise.reject();
    }

    return User.findOne({
        _id: decoded._id,
        'tokens.token': token,
        'tokens.access': Constants.AUTH
    });
};

UserSchema.methods.generateAuthToken = function () {
    const user = this;
    const access = Constants.AUTH;
    const token = jwt.sign({
        _id: user._id.toHexString(),
        access
    }, process.env.JWT_SECRET).toString();

    user.tokens.push({access, token});

    return user.save().then(() => {
        return token;
    });
};

UserSchema.methods.toJSON = function () {
    const user = this;
    const userObject = user.toObject();

    return _.pick(userObject, ['_id', 'first_name', 'last_name', 'phone', 'email', 'bank', 'card', 'wallet'])
};

UserSchema.methods.createWallet = function () {
    const user = this;
    const newWallet = new Wallet({owner: user._id});
    return newWallet.save().then((wallet) => {
        user.wallet = wallet._id;
        user.save();
    });
};

const User = module.exports = mongoose.model('User', UserSchema);

//Custom DB access functions
module.exports.createUser = (newUser, cb) => {
    bcrypt.genSalt(10, (err, salt) => {
        bcrypt.hash(newUser.pin, salt, (err, hash) => {
            newUser.pin = hash;
            newUser.save(cb);
        })
    })
};

module.exports.getUserByEmail = (email, cb) => {
    return Promise.resolve(User.find({email}, cb));
};

module.exports.comparePin = (pin, hash, cb) => {
    bcrypt.compare(pin, hash, cb);
};