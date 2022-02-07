var express = require('express');
var router = express.Router();

const authController = require('../controllers/authController');
const usersController = require('../controllers/usersController');

router.post('/login', authController.login);
router.post('/register', usersController.register);
router.get('/getAllAcc', usersController.getAllAcc);

module.exports = router;