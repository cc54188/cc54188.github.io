const validateType = (value, type) => { // 拉一個function做
    if(value === type) {
        return true;
    }
    return false;
}

module.exports = {
    validateType // 驗證類型
}