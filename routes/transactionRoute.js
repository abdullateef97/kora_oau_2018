const app = require('express');
const router = app.Router();

const TransactionController = require('../controllers/TransationController');

router.post('/', TransactionController.createTransaction);
router.get('/:user_id',TransactionController.getAllTransactions);
router.get('/one/:transaction_id', TransactionController.getOneTransaction );


module.exports = router