```javascript
// 物件 object
// key(鍵) value(值)
var person = {
    name:"小白",
    age:23,
    is_male:true,
    print_name:function() { // 物件內放函式
        // this是person的
        document.write(this.name);
    }
}
person.print_name();

var phrase = "hello";
phrase.length; //每個東西都視為物件
```
```javascript
//物件 objext
var movie = {
    title:"刻在你心底的命字",
    maker:"氧氣電影",
    duration:114,
    actors:[
        {
            name:"陳昊森",
            age:24,
            is_male:true
        },
        {
            name:"曾敬驊",
            age:23,
            is_male:true
        }
    ]
};

document.write(movie.actors[1].is_male);
```
```javascropt
// while 迴圈

var i = 1;
while(i <= 3) {
    document.write(i++);
    document.write("<br/>");
}
```
```javascript
// 密碼檢驗程式

var password = 123456;
var input;
var entry_count = 0;
var entry_limit = 3;
var out_of_limit = false;

while(password != input && !out_of_limit) {
    entry_count++;
    if(entry_count <= entry_limit) {
        input=prompt("請輸入密碼");
    } else {
        out_of_limit = true;
    }
}

if(out_of_limit) {
    alert("密碼輸入錯誤三次，如忘記密碼，請洽客服")
} else {
    alert("登入成功");
}
```
```javascript
// for 迴圈

var friends = ["小黑", "小黃", "小綠"];

for(var i = 0; i < friends.length; i++) {
    document.write(friends[i]);
    document.write("<br/>");
}
```
