var express = require('express');
var router = express.Router();

const productController = require('../controllers/productController');

router.get('/getAll', productController.getAll);
router.post('/getByProType', productController.getByProType);

module.exports = router;