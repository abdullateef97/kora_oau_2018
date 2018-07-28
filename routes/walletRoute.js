const app = require('express');
const router = app.Router();
const {verifyToken} = require('../controllers/baseController')



const WalletController = require('../controllers/WalletController');

router.post('/kolo', verifyToken, WalletController.createKolo);
router.get('/', verifyToken, WalletController.getAllWallets);
router.get('/aajo', verifyToken, WalletController.createAajo);
router.get('/get_aajo_link', verifyToken, WalletController.getAajoUrl);

module.exports = router