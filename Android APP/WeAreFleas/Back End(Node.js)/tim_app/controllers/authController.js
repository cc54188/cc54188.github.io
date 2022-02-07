const User = require('../models/user'); // 前兩層內的models
const jwt = require('jsonwebtoken');
const config = require('../config');

const login = async (req, res, next) => { // 第一個方法: 登入
    // 第一步: 先查出user本身，所以要先傳帳號密碼
    let account = req.body.account;
    let password = req.body.password;
    
    try{   // 都用帳密找了，一定只找一個
        let user = await User
        .findOne({account, password})
        .select('-password  -__v'); // 密碼不回傳，要用id找的話，id要回傳
        if(user === null) {
            res.json({errorCode: 401, data: "Login Failed"});
            return; // 找到以後簽
        } // 簽完得到token，裡面有id，鹽巴，過期時間
        let token = jwt.sign({userId: user._id, type: user.type}, config.salt, {expiresIn: 300}); //  300s後失效
        // 這邊是把token和user資料一起回傳，但密碼除外
        res.json({errorCode: 200, data: {token, user}});
    }catch(err) {
        res.json({errorCode: 500, data: "Internal Error"});
    }
    
};

const verify = (req, res, next) => { // 第二個方法: 驗證
    let token = req.headers['x-access-token'];
    console.log(token);
    if(token) { // 就是if(token != null)
        try {
            let decoded = jwt.verify(token, config.salt); // 解碼後的結果
            // .一個不存在的key，會把=後面的值存進此key(javascript物件特性)
            req.decoded = decoded; //命名body可能會跟post重疊
            next();
            return;
        }catch(err){
            res.json({errorCode: 401, data: "TokenExpiredError: jwt expired"});
        }
    } // 沒進if的話，回傳下面
    res.json({errorCode: 401, data: "Permission Denied"});
};

module.exports = {
    login,
    verify
}