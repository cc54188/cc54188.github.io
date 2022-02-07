var express = require('express');
var router = express.Router();

const proTypeController = require('../controllers/proTypeController');

router.post('/getByName', proTypeController.getByName);
router.get('/getAll', proTypeController.getAll);

module.exports = router;