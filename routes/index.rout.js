const app = require('express');
const router = app.Router();

const {registerUser, handleLogin, logoutUser} = require('../controllers/index.ctrl');


//Register new user [as a non-administrator]
router.post('/register', registerUser);

//User login
router.post('/login', handleLogin);

//User logout
router.get('/logout', logoutUser);

//Check if the server is up
router.get('/health', (req, res) => res.json({status: true, message: 'Up and running'}));

module.exports = router;
