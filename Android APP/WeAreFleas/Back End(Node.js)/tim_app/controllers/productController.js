const Product = require('../models/product'); // 導入model
const utils = require('../utils');

const checkManagerType = (req, res) => {
    if(!utils.validateType(req.decoded.type, 1)) { // 若(a, b) a != b(type != 1)
        res.json({status: 403, data: {msg: "Forbidden"}});
        return false;      // 1 -> true
    }
    return true;
} // 這邊包裝一下，以後就不用一直寫Forbidden

const add = (req, res, next) => { // 新增商品
    if(!checkManagerType(req, res)) { // 若type非manager就不能新增
        return;
    }
    let proType = req.body.proType;
    let name = req.body.name;
    let desc = req.body.desc;
    let imgUrl = req.body.imgUrl;
    let price = req.body.price;
    
    console.log(name);

    let product = new Product({ 
        proType, 
        name,
        desc, 
        imgUrl,
        price
    });
    product.save()
        .then(() => res.json({errorCode: 200, data: {msg: "success"}}) 
        ).catch((err) => res.json({errorCode: 400, data: {msg: err.message}})
        ); // lambda只有一行，省略{}
};

const getAll = async(req, res, next) => { // 取得無限制
    let products = await Product.find({})
        .select('-_id -__v');
    res.json({errorCode: 200, data: {products}});
}  // 若成功，在Postman印出

const getAllId = async(req, res, next) => { 
    if(!checkManagerType(req, res)) {
        return;
    }
    let products = await Product.find({})
        .select('-__v');
    res.json({errorCode: 200, data: {products}});
}

const getByProType = async(req, res, next) => {
    let proType = req.body.proType;
    console.log(proType);

    let products = await Product.find({proType}).select('-__v');

    if(products == null) {
        res.json({errorCode: 404, data: "Not Found"});
        return;
    }

    res.json({errorCode: 200, data: {products}}); 
}                              // products不加{}的話陣列沒名字

const modify = async (req, res, next) => {
    if(!checkManagerType(req, res)) { // 若type非manager就不能修改
        return;
    }
    let id = req.body._id; // 變數 = 傳入body.key名稱
    let proType = req.body.proType;
    let name = req.body.name;
    let desc = req.body.desc;
    let imgUrl = req.body.imgUrl;
    let price = req.body.price;

    try{          
        let product = await Product
        .updateOne({_id: id}, // {取得後顯示的key: 變數}
            {proType, name, desc, imgUrl, price}, 
            {omitUndefined: true}); // 預設false，true會刪掉未定義項
        if(product) {
            res.json({errorCode: 200, data: {msg: "success"}});
            console.log();
            return;
        }
    }catch(err) {
        res.json({status: 400, data: {msg: err.message}});
    }
    res.json({errorCode: 404, data: {msg: "Data Not Found"}});
}

const remove = async(req, res, next) => {
    if(!checkManagerType(req, res)) { 
        return;
    }
    let name = req.body.name;
    console.log(name);
    let product = await Product.deleteOne({name});
    if(product) {
        res.json({errorCode: 200, data: {msg: "success"}});
        return;
    }
    res.json({errorCode: 404, data: {msg: "Data Not Found"}});
}

module.exports = {
    add,
    getByProType,
    getAll,
    getAllId,
    modify,
    remove
}