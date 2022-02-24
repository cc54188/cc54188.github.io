```html
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>js練習網頁</title>
        <link rel="stylesheet" href="styleJs.css">
    </head>
    <body>
        <!-- <script>
            // 在html標籤裡寫字
            document.write("<h1>兔寶可愛</h1>")
        </script> -->
        <!-- 外部引入 -->
        <script src="script.js"></script>
    </body>
</html>
```
```javascript
// 資料型態&變數
// 變數只能是英文大小寫，數字，$或是_的組合
// 開頭不能是數字

var myName = "帥哥";  // 字串 ""
var my_age = 36;  // 數字
var is_male = true;  // 布林值 true & false

document.write("<h1>" + myName + "</h1>");
myName = true; // 可以改型態
document.write(myName);
```
```javascript
// 如何使用字串，字串用法
var phrase = "hello Tim"; //想在字串內+"，要先+斜線
// 空白也算字串長度
//.charAt(0) -> h
//indexOf("h") -> 0 回傳最前面的h的位置
//.substring(2, 5) -> 取2~5位置
document.write(phrase.toUpperCase());
```
```javascript
// 如何使用數字，數字的用法
var number = -16;
//Math方法
//abs: 絕對值
//round: 四捨五入
//pow(2, 3) 2的3次方=8
//sqrt(64) 開根號=8
//random()0~1隨機一個數
document.write(Math.round(Math.random() * 10));
```
```javascript
// 製作一個基本的計算機
var num1 = prompt("請輸入數字1");
var num2 = prompt("請輸入數字2");
// document.write()預設為字串
num1 = parseFloat(num1);  // 轉成浮點數
num2 = parseFloat(num2);
document.write(num1 + num2);
```
```javascript
// array 陣列
var scores = [80, 60, 20, 30, 10];
var friends = ["小黑", 20, true]; // 陣列可放不同資料型態
var score1 = 80;
var score2 = 60;
var score3 = 20;
var score4 = 30;
var score5 = 10;
document.write(friends);
```
```javascript
// funcrion 函式
// function hello(name, age){  // 定義
//     document.write("您好" + name + "你今年" + age + "歲");
// }
// hello("小白", 36);  // 呼叫

function add(num1, num2) {
    //document.write(num1 + num2);
    return num1 + num2;
}
document.write(add(3, 5));
```
```javascript
// if 判斷

// var score = 50;
// if(score == 100) {
//     document.write("我就給你1000元");
// } else if(score >= 80){
//     document.write("我就給你500");
// } else if(score >= 60) {
//     document.write("我就給你100元");
// } else {
//     document.write("你就給我300元");
// }

// 如果 你考100分 或 今天沒有下雨
//     我給你1000元
// 否則
//     你給我100元
// var score = 90;
// var rainy = false;
// if(score == 100 || !rainy) {
//     document.write("我給你1000元");
// } else {
//     document.write("你給我100元");
// }
```

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
```javascript
// 製作一個問答程式

var questions = [
    {
        prompt:"香蕉是甚麼顏色?\n(a)紅色(b)綠色(c)黃色",
        answer:"c"
    },
    {
        prompt:"草莓是甚麼顏色?\n(a)紅色(b)紫色(c)黃色",
        answer:"a"
    },
    {
        prompt:"1公尺等於幾公分?\n(a)1(b)10(c)100",
        answer:"c"
    }
]
var score = 0;
for(var i = 0; i < questions.length; i++) {
    var input = prompt(questions[i].prompt);
    if(input == questions[i].answer){
        score++;
        alert("答對了");
    } else {
        alert("答錯了");
    }
}
alert("總共答對了" + score + "題");
```
```javascript
// 2維陣列，巢狀迴圈

var number = [
    [1,2,3], 
    [4,5,6], 
    [7,8,9], 
    [0]
];

// document.write(number[3][0]) ;

for(var i = 0; i < 4; i++) {
    for(var j = 0; j < number[i].length; j++) {
        document.write(number[i][j]);
    }
    document.write("<br/>");
}
```
```javascript
// class

class Phone {
    constructor(number, year, is_waterproof) {
        this.number = number;
        this.year = year;
        this.is_waterproof = is_waterproof;
    }
    phone_age() {
        return 2021 - this.year;
    }
}
var phone1 = new Phone("123", 2020, false);
document.write(phone1.phone_age());
```
#### event listener 事件監聽器
1.直接寫在html裡面
```html
<body>
    <img id="img" src="bullet1.png" width="300px" />
    <button id="btn" onclick="handle_click(this)">按我</button>
    <!-- this:被按下去的那個元素(button) -->
    <!-- 外部引入 -->
    <script src="script.js"></script>
</body>
```
```javascript
function handle_click(element){ // 自己取名叫element
    alert("叫你按就按阿!?")
    element.innerText = "按屁阿"; // 改button上的字
    element.style.color = "red";
}
```
2.寫在javascript
```javascript
var btn = document.getElementById("btn"); 

btn.addEventListener("click", function(){ 
    // 對此元素監聽(觸發事件,要做啥) 
    //alert("叫你按就按阿!?"); 
    this.innerText = "按屁"; 
    this.style.color = "red";
})

var img = document.getElementById("img");
img.addEventListener("mouseover", function(){ // 滑鼠滑入
    this.src = "bmi履歷.png";  // 滑鼠滑過變成這張
})

img.addEventListener("mouseout", function(){ // 滑鼠滑出
    this.src = "bullet1.png";
})
// 監聽方法寫在html前面都要加on，例:onclick，onmouseover
```
