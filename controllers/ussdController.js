const validator = require('validator');
const User = require('../models/User');
const Account = require('../models/Account');
const ResponseMessages = require('../constants/responseMessages');
const {sendSuccess, sendError} = require('./baseController');

const validatePhone = (req, res) => {
    if (!validator.isNumeric(req.body.phone))
        return sendError(res, null, ResponseMessages.INVALID_ENTRY, 400);

    User.getUserByPhone(req.body.phone, (err, user) => {
        if (err) return Promise.reject();
        if (!user) return sendError(res, null, ResponseMessages.INVALID_ENTRY, 400);

        return sendSuccess(res, user);
    }).catch((err) => sendError(res, err, err.message, err.status));
};

const validatePin = (req, res) => {
    const phone = req.body.phone,
        pin = req.body.pin;

    if (!validator.isNumeric(phone) || !validator.isNumeric(pin))
        return sendError(res, null, ResponseMessages.INVALID_ENTRY, 400);

    User.getUserByPhone(phone, (err, user) => {
        if (err) return Promise.reject();
        if (user) {
            User.comparePin(pin, user.pin, (err, isMatch) => {
                if (err) return Promise.reject();
                if (isMatch) return sendSuccess(res);
                if (!isMatch) return sendError(res, null, ResponseMessages.INCORRECT_PIN, 400);
            })
        }
    }).catch((err) => sendError(res, err, err.message, err.status));
};

const getAccountByUserId = (req, res) => {
    const user_id = req.body.user_id;
    Account.findOne({user_id}).then((acc) => sendSuccess(res, acc))
        .catch((err) => sendError(res, err, err.message, err.status));
};

module.exports = {
    validatePhone,
    validatePin,
    getAccountByUserId
};