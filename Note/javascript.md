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
