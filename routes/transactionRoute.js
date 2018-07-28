const app = require('express');
const router = app.Router();
const {verifyToken} = require('../controllers/baseController')


const TransactionController = require('../controllers/TransationController');

router.post('/', verifyToken, TransactionController.createTransaction);
router.get('/:user_id', verifyToken, TransactionController.getAllTransactions);
router.get('/one/:transaction_id',verifyToken, TransactionController.getOneTransaction );


module.exports = router;