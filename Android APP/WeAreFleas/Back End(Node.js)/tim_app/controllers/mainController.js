const main = (req, res, next) => { // ex6.的lambda
    res.render('index', { title: '兔仔之家' });
};

const test = (req, res, next) => {
    console.log(req.body); // 在vscode印出參數
    res.send({Test: req.body});
};

module.exports = { // 很多function，用物件
    main, // 當key和value名稱一樣，value可省略
    test
};