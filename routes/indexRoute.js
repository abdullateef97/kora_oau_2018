const app = require('express');
const router = app.Router();

const {registerUser, handleLogin, logoutUser} = require('../controllers/indexController');
const {verifyToken} = require('../controllers/baseController')


//Register new user [as a non-administrator]
router.post('/register', registerUser);

//User login
router.post('/login', handleLogin);

//User logout
router.get('/logout', logoutUser);

//Check if the server is up
router.get('/health', verifyToken, (req, res) => res.json({status: true, message: 'Up and running', token: req.user_id}));

module.exports = router;
