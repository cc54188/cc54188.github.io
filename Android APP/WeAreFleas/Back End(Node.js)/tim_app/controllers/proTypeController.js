const ProType = require('../models/proType');
const utils =require('../utils');

const checkManagerType = (req, res) => {
    if(!utils.validateType(req.decoded.type, 1)) {
        res.json({status: 403, data: {msg: "Forbidden"}});
        return false;
    }
    return true;
}

const add = (req, res, next) => {
    if(!checkManagerType(req, res)) { // 非manager就不能新增
        return;
    }
    let name = req.body.name;
    let desc = req.body.desc;

    let proType = new ProType({
        name,
        desc
    });

    proType.save().then(() => 
    res.json({errorCode: 200, data:{msg: "success"}})
    ).catch((err) => 
    res.json({errorCode: 400, data: {msg: err.message}}));
};

const getByName = async(req, res, next) => {
    let proType = await ProType.findOne({name: req.body.name});
    res.json({errorCode: 200, data: {proType}});
} 

const getAll = async(req, res, next) => {
    let proTypes = await ProType.find({}).select('-_id -__v');
    res.json({errorCode: 200, data: {proTypes}});
}

const getAllId = async(req, res, next) => {
    if(!checkManagerType(req, res)) {
        return;
    }
    let proTypes = await ProType.find({}).select('-__v');
        res.json({errorCode: 200, data: {proTypes}});
}

const modify = async(req, res, next) => {
    if(!checkManagerType(req, res)) { // 非manager不能修改
        return;
    }
    let id = req.body._id;
    let name = req.body.name;
    let desc = req.body.desc;

    try {
        let proType = await ProType
        .updateOne({_id: id}, {name, desc});
        if(proType) {
            res.json({errorCode: 200, data: {msg: "success"}});
            return;
        }
    }catch(err) {
        res.json({status: 400, data: {msg: err.message}});
    }
    res.json({errorCode: 404, data: {msg: "Data Not Found"}});
}

const remove = async(req, res, next) => {
    if(!checkManagerType(req, res)) { // 非manager就不能刪除
        return;
    }
    let name = req.body.name; // 用post找name
    console.log(name);
    let proType = await ProType.deleteOne({name});
    if(proType) {
        res.json({errorCode: 200, data: {msg: "success"}});
        return;
    }
    res.json({errorCode: 404, data: {msg: "Data Not Found"}});
}

module.exports = {
    add,
    getByName,
    getAll,
    getAllId,
    modify,
    remove
}