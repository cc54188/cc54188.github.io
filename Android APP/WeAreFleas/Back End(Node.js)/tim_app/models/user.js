const mongoose = require('mongoose'); // 載入mongoose模組，以使用其方法
const Schema = mongoose.Schema;

const userSchema = new Schema({ // 創建一個Schema(概要，架構圖)
    // 帳號，密碼，type設成必填，要設required
    account: {type: String, required: true},
    password: {type: String, required: true},
    name: {type: String, required: true},
    phone: {type: String, required: true},
    address: {type: String, require: true},
    type: {type: Number, require: true} // type一定要再包裝一層
});
// // 靜態方法: 類別呼叫; 非靜態方法: 物件呼叫
// // 靜態方法 => 通常多用在搜尋這個Table裡的內容
// userSchema.statics.findByName = function(firstName, lastName) {
//     return this.find({name: {first: firstName, last: lastName}});
// };
// // Query Helper => 利用mongoose提供的查詢方法，建立預設的查詢條件
// userSchema.query.byName = function(firstName, lastName) {
//     return this.where({name: {first: firstName, last: lastName}});
// };
// //一般方法 => 利用當前物件的資料去查詢/修改
// userSchema.methods.findSameLevel = function() {
//     return this.model('User').find({level: this.level});
// };
// // 抽象方法 => 整合物件中的屬性
// userSchema.virtual('fullName').get(function() {
//     return this.name.first + ' ' + this.name.last;
// });
// 創建一個model(依照userSchema創造一個名字叫User)
module.exports = mongoose.model('User', userSchema);