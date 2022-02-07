const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const proTypeSchema = new Schema({
    name: {type: String, required: true},
    desc: String
});

module.exports = mongoose.model('ProType', proTypeSchema);