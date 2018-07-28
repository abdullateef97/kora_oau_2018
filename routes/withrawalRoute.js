const app = require('express');
const router = app.Router();

const WithdrawalController = require('../controllers/WithrawalController');


router.post('/:account_id', WithdrawalController.createWithdrawal)
router.post('/addAccount',WithdrawalController.addAccountNumber);

module.exports = router