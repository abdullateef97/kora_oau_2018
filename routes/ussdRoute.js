const router = require('express').Router();
const {validatePhone, validatePin, getAccountByUserId} = require('../controllers/ussdController');

router.post('/validate_phone', validatePhone);

router.post('/validate_pin', validatePin);

router.post('/get_account', getAccountByUserId);

module.exports = router;