const app = require('express');
const router = app.Router();
const {verifyToken} = require('../controllers/baseController')


const WithdrawalController = require('../controllers/WithrawalController');


router.post('/:account_id',verifyToken, WithdrawalController.createWithdrawal)
router.post('/addAccount', verifyToken, WithdrawalController.addAccountNumber);

module.exports = router