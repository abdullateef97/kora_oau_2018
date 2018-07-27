const _ = require('lodash');
const validator = require('validator');
const User = require('../models/User');
const {sendSuccess, sendError, setUserInfo, generateUserToken} = require('./base.ctrl');
const Constants = require('../constants/constants');
const ResponseMessages = require('../constants/responseMessages');

/**
 * Register user
 * @param req
 * @param res
 * @returns {*}
 */
const registerUser = (req, res) => {
    const firstName = req.body.first_name,
        lastName = req.body.last_name,
        phone = req.body.phone,
        email = req.body.email,
        pin = req.body.pin;

    if (!(firstName || lastName || phone || email || pin))
        return sendError(res, null, ResponseMessages.ALL_FIELDS_REQUIRED, 400);

    if (!(validator.isAlpha(firstName || lastName)))
        return sendError(res, null, ResponseMessages.INVALID_NAME_CHARACTER, 400);

    if (!validator.isEmail(email))
        return sendError(res, null, ResponseMessages.INVALID_EMAIL, 400);

    if (pin < Constants.PIN_LENGTH)
        return sendError(res, null, ResponseMessages.PIN_LENGTH, 400);

    if (!validator.isNumeric(parseInt(pin)))
        return sendError(res, null, ResponseMessages.PIN_SHOULD_CONTAIN, 400);

    if (phone.slice(0, 4) !== '+234' || parseInt(phone).length !== 10)
        return sendError(res, null, ResponseMessages.INVALID_PHONE, 400);

    const params = {
        first_name: firstName,
        last_name: lastName,
        phone,
        email,
        pin
    };

    User.getUserByEmail(email, (err, user) => {
        if (err) return Promise.reject();
        if (user) return sendSuccess(res, null, ResponseMessages.USER_ALREADY_EXISTS);

        if (!user) {
            const newUser = new User(params);
            newUser.createUser(newUser, (err, _user) => {
                if (err || !_user) return Promise.reject();

                if (_user) {
                    _user.createWallet();
                    return _user.generateAuthToken().then((token) => {
                        return sendSuccess(res, _user, ResponseMessages.USER_CREATED, token, 200, Constants.AUTH_HEADER);
                    });
                }
            })
        }
    }).catch((err) => sendError(res, err, err.message, err.status));
};

/**
 * Login user successfully
 * @param req
 * @param res
 * @returns {*}
 */
const handleLogin = (req, res) => {
    const email = req.body.email;
    const pin = req.body.pin;

    return User.getUserByEmail(email, function (err, user) {
        if (err) return Promise.reject();

        if (!user)
            return sendError(res, null, ResponseMessages.INCORRECT_LOGIN_PARAMS, 400);

        User.comparePin(pin, user.pin, (err, isMatch) => {
            if (err) return Promise.reject();

            if (isMatch) {
                const userInfo = setUserInfo(user);
                const token = 'bearer ' + generateUserToken(userInfo);
                req.user = user;
                return sendSuccess(res, req.user, ResponseMessages.LOGIN_SUCCESSFUL, token);
            } else {
                return sendError(res, null, ResponseMessages.INCORRECT_PIN, 400);
            }
        })
    }).catch((err) => sendError(res, err, err.message, err.status));
};

/**
 * Log user out successfully
 * @param req
 * @param res
 */
const logoutUser = (req, res) => {
    req.logout();
    return sendSuccess(res, null, ResponseMessages.LOGOUT_SUCCESSFUL);
};


module.exports = {
    registerUser,
    handleLogin,
    logoutUser
};