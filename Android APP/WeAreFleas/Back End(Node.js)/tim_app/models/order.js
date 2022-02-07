const mongoose = require('mongoose'); // 載入mongoose模組，以使用其方法
const Schema = mongoose.Schema;

const orderSchema = new Schema({ // 創建一個Schema
    serial: String,   
    user: {type: mongoose.Types.ObjectId, ref: 'User'},
    totalPrice: Number,
    status: String,
    buyCars: [{buyCar: {name: {type: String, ref: 'Product'}, 
    amount: Number, 
    price: Number}}]
}); // 是一個陣列，ref是reference，每一格都放這樣的物件

module.exports = mongoose.model('Order', orderSchema);