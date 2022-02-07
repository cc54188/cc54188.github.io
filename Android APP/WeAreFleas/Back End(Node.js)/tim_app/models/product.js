const mongoose = require('mongoose'); // 載入mongoose模組，以使用其方法
const Schema = mongoose.Schema;

const productSchema = new Schema({ // 創建一個Schema(概要，架構圖)
    proType: {type: String, required: true}, // 商品類型
    name: {type: String, required: true}, // 必要
    desc: String,
    imgUrl: String,
    price: {type: Number, required: true} // 必要 
});

module.exports = mongoose.model('Product', productSchema);