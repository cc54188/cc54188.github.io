var express = require('express');
var router = express.Router();

const proTypeController = require('../controllers/proTypeController');

router.get('/getAllId', proTypeController.getAllId);
router.post('/remove', proTypeController.remove);
router.post('/add', proTypeController.add);
router.post('/modify', proTypeController.modify);

module.exports = router;