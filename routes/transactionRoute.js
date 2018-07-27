const app = require('express');
const router = app.Router();

const TransactionController = require('../controllers/TransationController');

router.post('/', TransactionController.createTransaction);


module.exports = router