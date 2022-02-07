var express = require('express');
var router = express.Router();

const usersController = require('../controllers/usersController');

/* GET users listing. */
//router.post('/register', usersController.register); // 註冊移到auth
router.get('/get', usersController.getUser); // 取得
router.get('/getAll', usersController.getAll);
router.post('/findSameLevel', usersController.findSameLevel);
router.post('/modify', usersController.modify);

module.exports = router;
