# Angular

## 預習
### 1.預備知識
熟悉基本的HTML，CSS，JavaScript，終端機指令
### 2.認識Angular
以TypeScript為基礎的開發平台
### 3.建立專案
#### 1.安裝Node.js
官網下載(LTS長期維護版)，可在終端機輸入```node -v```檢查版本






[資料來源](https://developer.mozilla.org/zh-TW/docs/Learn/Tools_and_testing/Client-side_JavaScript_frameworks/Angular_getting_started)

```typescript
import { Component } from '@angular/core'; // js import匯入Angular

@Component({ // 指定關於AppComponent的metadata(元資料)
  selector: 'app-root', // css選擇器名稱
  //template: '<a title="The Mozilla homepage">My cat is very grumpy.</a>',  // html直接寫這裡的話
  templateUrl: './app.component.html', // 指定與此元件相關的HTML檔案
  //styles: ['h1 {color: yellow;}'] // css直接寫這裡的話
  styleUrls: ['./app.component.css'] // 提供要套用在這個元件的樣式表的路徑
})
export class AppComponent {
  title = 'todo';
  // 底下為新增
  filter: 'all' | 'active' | 'done' = 'all';
  allItems = [
    {description: 'eat', done: true},
    {description: 'sleep', done: false},
    {description: 'play', done: false},
    {description: 'laugh', done: false}
  ];

  get items() {
    if(this.filter === 'all') {
        return this.allItems;
    }
    return this.allItems.filter(
      item => this.filter === 'done' ? item.done : !item.done);
  }
}
```
