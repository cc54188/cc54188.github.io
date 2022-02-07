const Order = require('../models/order'); // 導入model
const utils = require('../utils');

const checkUserType = (req, res) => {
    if(!utils.validateType(req.decoded.type, 2)) { // user
        res.json({status: 403, data: {msg: "Forbidden"}});
        return false;
    }
    return true;
}

const create = async (req, res, next) => { // 收傳來的資料
    if(!checkUserType(req, res)) { // 使用者才能創訂單
        return;
    }
    let serial = new Date().getTime(); // 改成使用毫秒
    let user = req.decoded.userId;
    let status = req.body.status;
    let buyCars = req.body.buyCars;

    let totalPrice = 0;
    for(let i = 0; i < buyCars.length; i++) {
        totalPrice += buyCars[i].buyCar.price; // 購物車第i種商品的總價
    }

    let order = new Order({
        serial,
        user,
        status,
        buyCars,
        totalPrice
    });

    order // 依照model模板建出來的實體
        .save() // 寫進資料庫(mongodb)
        .then( // 儲存成功後做這件事
            () => {
                res.json({errorCode: 200, data: {msg: "success"}});
                //console.log(order);
            } 
        ).catch( // 失敗做甚麼
            (err) => res.json({errorCode: 400, data: err.message})
        ); // lambda只有一行，省略{}
};

const cancel = async (req, res, next) => {
    if(!checkUserType(req, res)) { // 使用者才能取消訂單
        return;
    }
    let serial = req.body.serial;
    let order = await Order.deleteOne({serial});
    if(order) {
        res.json({errorCode: 200, data: {msg: "success"}});
        return;
    }
    res.json({errorCode: 404, data: {msg: "Data Not Found"}});
}

const getBySerial = async (req, res, next) => { // 取得無限制
    let serial = req.body.serial;

    console.log(serial);
    if(serial === undefined) {
        res.json({errorCode: 400, data: "Bad Request"});
        return;
    }
                    // 若用.exec(強迫同步)，就不用await，但易卡住
    let order = await Order.findOne({serial})
    .populate("user", "-_id -__v -password") // 填充時不要的項目
    .select("-_id -__v"); // 不同項用空格間隔就好

    if(order == null) {
        res.json({errorCode: 404, data: "Not Found"});
        return;
    }

    res.json({errorCode: 200, data: order});
}

const getByUserId = async (req, res, next) => {
    let user = req.body.user;

    if(user === undefined) {
        res.json({errorCode: 400, data: "Bad Request"});
        return;
    }
                   
    let orders = await Order.find({user})
    .populate("user", "-_id -__v -password") 
    //.populate("products", "-__v -_id")
    .select("-_id -__v"); 

    if(orders == null) {
        res.json({errorCode: 404, data: "Not Found"});
        return;
    }

    res.json({errorCode: 200, data: {orders}});
}

const getAll = async (req, res, next) => { // 管理者才能全取
    if(!utils.validateType(req.decoded.type, 1)) { // 若非管理者
        res.json({status: 403, data: {msg: "Forbidden"}});
        return;
    }
                    
    let orders = await Order.find({});
    // .select("serial");的話只回傳流水號
    
    if(orders == null) {
        res.json({errorCode: 404, data: "Not Found"});
        return;
    }

    // let result = [];
    // for(let i = 0; i < orders.length; i++) {
    //     result.push(orders[i]); // .serial的話只印出流水號
    // }

    // res.json({errorCode: 200, data: result});
    res.json({errorCode: 200, data: {orders}});
}

const modifyStatus = async (req, res, next) => {
    if(!utils.validateType(req.decoded.type, 1)) {
        res.json({status:403, data: {msg: "Forbidden"}});
        return;
    }

    let serial = req.body.serial;
    let status = req.body.status;

    try {
        let order = await Order.updateOne({serial: serial}, {status});
        if(order) {
            res.json({errorCode: 200, data: {msg: "success"}});
            console.log();
            return;
        }
    } catch (error) { // 跟err應該一樣吧
        res.json({status: 400, data: {msg: error.message}});
    }
    res.json({errorCode: 404, data: {msg: "Data Not Found"}});
}

module.exports = {
    create,
    cancel,
    getBySerial,
    getByUserId,
    getAll,
    modifyStatus
}