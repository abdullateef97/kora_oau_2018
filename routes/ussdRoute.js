const router = require('express').Router();
const {validatePhone, validatePin} = require('../controllers/ussdController');

router.post('/validate_phone', validatePhone);

router.post('/validate_pin', validatePin);

module.exports = router;