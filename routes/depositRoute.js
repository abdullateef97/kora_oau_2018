const app = require('express');
const router = app.Router();
const {verifyToken} = require('../controllers/baseController')


const DepositController = require('../controllers/DepositController');

router.post('/', verifyToken, DepositController.createDeposit);


module.exports = router