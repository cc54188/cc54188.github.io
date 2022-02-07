//主路由
var express = require('express');
var router = express.Router();

const mainRouter = require('./mainRouter');
const usersRouter = require('./usersRouter');
const orderRouter = require('./orderRouter');
const authRouter = require('./authRouter');
const productRouter = require('./productRouter');
const productManageRouter = require('./productManageRouter');
const proTypeRouter = require('./proTypeRouter');
const proTypeManageRouter = require('./proTypeManageRouter');

const authController = require('../controllers/authController');

router.use('/', mainRouter);    // 有可能get或post，所以用use
router.use('/user', authRouter); // 改成'/user'
router.use('/proType', proTypeRouter); // 取得
router.use('/product', productRouter); // 取得
router.use(authController.verify); // 驗證一定在登入後，中介層不帶路徑
router.use('/user', usersRouter); // 原user放在驗證下
router.use('/proType', proTypeManageRouter);
router.use('/product', productManageRouter); // 管理(新增，修改)
router.use('/order', orderRouter);

module.exports = router;