var express = require('express');
var router = express.Router();

const orderController = require('../controllers/orderController');

/* GET users listing. */
router.post('/create', orderController.create); // 創建
router.post('/cancel', orderController.cancel);
router.post('/getBySerial', orderController.getBySerial);
router.post('/getByUserId', orderController.getByUserId);
router.get('/getAll', orderController.getAll);
router.post('/modifyStatus', orderController.modifyStatus);

module.exports = router;