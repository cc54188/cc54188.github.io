const User = require('../models/user'); // 導入model
const utils = require('../utils');

const checkManagerType = (req, res) => { // 1 -> true
    if(!utils.validateType(req.decoded.type, 1)) { 
        res.json({status: 403, data: {msg: "Forbidden"}});
        return false;      
    }
    return true;
}

const register = (req, res, next) => { // register註冊
  
    let account = req.body.account; // 有點像物件導向的屬性
    let password = req.body.password;
    let name = req.body.name;
    let phone = req.body.phone;
    let address = req.body.address;
    let type = req.body.type;

    if(type === undefined || (type !== 1 && type !== 2)) {
      res.json({status: 400, data: {msg: "Type Error!"}});
      console.log(type);
      return;
    } // 限制type

    let user = new User({ // 把傳過來的資料new出來(有點像建構子)
      account, // key,value同名，值可省
      password, // 值可省
      name,
      phone,
      address,
      type
    });
    user // 依照model模板建出來的實體
      .save() // 寫進資料庫(mongodb)
      .then( // 儲存成功後做這件事
          () => res.json({errorCode: 200, data: {msg: "success"}}) 
      ).catch( // 失敗做甚麼
          (err) => res.json({errorCode: 500, data: {msg: err.message}})
      ); // lambda只有一行，省略{}
};

const getUser = async(req, res, next) => { // 從回傳的id找出user(透過body(post))
  let user = await User.findOne({_id: req.decoded.userId})
      .select('-password -_id -__v'); // 不抓出來
  // let users = await User.findOne().byName(req.body.firstName, req.body.lastName);
  res.json({errorCode: 200, data: {user}});  // json格式印出
  // 可以試著做沒成功怎麼處理
};

const getAll = async(req, res, next) => {
  if(!checkManagerType(req, res)) { 
    return;
  }
  let users = await User.find({})
  .select('-password -__v');
  res.json({errorCode: 200, data: {users}});
};

const getAllAcc = async(req, res, next) => {
  let users = await User.find({})
  .select("account -_id");
  res.json({errorCode: 200, data: {users}});
};

const findSameLevel = async(req, res, next) => { // 找此使用者的同level使用者
  let results = await User.findByName(req.body.firstName, req.body.lastName);
  let user = results[0]; // 找出符合條件的第一筆資料就好
  let users = await user.findSameLevel(); // 在尋找同level的使用者

  res.json({status: -1, msg: {users: users}});
};

const modify = async (req, res, next) => {
  let id = req.decoded.userId; // 變數 = 傳入body.key名稱
  let name = req.body.name;
  let phone = req.body.phone;
  let address = req.body.address;

  try{          
      let user = await User
      .updateOne({_id: id}, // {取得後顯示的key: 變數}
          {name, phone, address}, 
          {omitUndefined: true}); // 預設false，true會刪掉未定義項
      if(user) {
          res.json({errorCode: 200, data: {msg: "success"}});
          console.log(id);
          return;
      }
  }catch(err) {
      res.json({status: 400, data: {msg: err.message}});
  }
  res.json({errorCode: 404, data: {msg: "Data Not Found"}});
}

module.exports = {
  register,
  getUser,
  getAll,
  getAllAcc,
  findSameLevel,
  modify
};