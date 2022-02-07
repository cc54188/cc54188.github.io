var express = require('express');
var router = express.Router();

const productController = require('../controllers/productController');

router.post('/add', productController.add);
router.post('/modify', productController.modify);
router.get('/getAllId', productController.getAllId);
router.post('/remove', productController.remove);

module.exports = router;