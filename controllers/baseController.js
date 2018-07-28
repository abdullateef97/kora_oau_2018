const jwt = require('jsonwebtoken');
const User = require('../models/User');
const Constants = require('../constants/constants');
const ResponseMessages = require('../constants/responseMessages');


/**
 * Generic send success helper
 * @param res
 * @param data
 * @param message
 * @param token
 * @param status
 * @param header
 */
const sendSuccess = (res, data, message, token, status, header) => {
    let resp = {status: Constants.TRUE};

    if (message)
        resp.message = message;

    if (data || data === [] || data === {})
        resp.data = data;

    status = status ? status : 200;

    if (header) return res.header(header, token).status(status).json(resp);

    return res.status(status).json(resp);
};

/**
 * Generic send error helper
 * @param res
 * @param message
 * @param error
 * @param status
 */
const sendError = (res, error, message, status) => {
    let resp = {status: Constants.FALSE};
    resp.message = message ? message : ResponseMessages.ERROR_OCCURRED;

    if (error)
        resp.error = error.stack;

    status = status ? status : 500;

    return res.status(status).json(resp);
};


const generateUserToken = (user) => {
    const secret = process.env.JWT_SECRET;
    jwt.sign(user, secret, {expiresIn: '1 hour'});
};

const setUserInfo = (user) => {
    return {
        _id: user._id,
        email: user.email
    }
};


const isAdmin = (req, res, next) => {
    const token = req.headers[Constants.AUTH_HEADER];

    User.findByToken(token).then((user) => {
        if (!user || !user.isAdmin) return Promise.reject();

        req.user = user;
        req.token = token;
        next();
    }).catch((err) => sendError(res, null, ResponseMessages.NOT_PERMITTED, 401));
};

const authenticate = (req, res, next) => {
    const token = req.headers[Constants.AUTH_HEADER];
    User.findByToken(token).then((user) => {
        if (!user) return Promise.reject();

        req.user = user;
        req.token = token;
        next();
    }).catch((err) => {
        console.log('Error:', err);
        return sendError(res, null, ResponseMessages.NOT_PERMITTED, 401)
    });
};

const isLoggedIn = (req, res, next) => {
    if (req.user)
        return next();

    sendError(res, null, ResponseMessages.NOT_PERMITTED, 401);
};

module.exports = {
    sendSuccess,
    sendError,
    generateUserToken,
    setUserInfo,
    isAdmin,
    authenticate,
    isLoggedIn
};