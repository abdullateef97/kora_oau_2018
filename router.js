express = require('express');

const UserContractController = require('./controllers/userContractController');
const PaymentController = require('./controllers/paymentController');

module.exports = function (app) {
    // Initializing route groups
    const apiRoutes = express.Router();

    // auth to get auth code
    apiRoutes.post('/get_contract', UserContractController.getUserAuthCode);
    // route to post the auth code
    apiRoutes.post('/contract', UserContractController.addUserAuthCode);
    // route to update auth code
    apiRoutes.put('/contract/:user_id', UserContractController.updateUserAuthCode);

    apiRoutes.post('/get_access_code', PaymentController.getAccessCode);
    apiRoutes.get('/verify/:reference/:save_auth/:user_id', PaymentController.verifyTransaction);
    apiRoutes.post('/charge_returning_customer', PaymentController.chargeReturningCustomer);

// Set url for API group routes
    app.use('/api', apiRoutes);
};
