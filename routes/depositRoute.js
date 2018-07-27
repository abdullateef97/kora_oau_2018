const app = require('express');
const router = app.Router();

const DepositController = require('../controllers/DepositController');

router.post('/', DepositController.createDeposit);


module.exports = router