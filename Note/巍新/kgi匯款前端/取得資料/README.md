# 說明(思路)
- 先去main.component.html複製一顆按鈕貼到下面，中文改成我的待辦清單，並將(click)後的方法改成"goMyList()"
- 然後到main.component.ts新增一個goMyList()的方法，並將路徑指向['/'+COMPONENT_AIO.MYLIST](用法複製上面)
- 當然，我還沒定義這個路徑，所以要先去aio.path.ts的COMPONENT_AIO內新增一個MYLIST = 'my_list'
- 然後到app-routing.module.ts新增一個
```typescript
{
    path: COMPONENT_AIO.MYLIST,  // 我的待辦清單
    component: MyListComponent
}
```
- 在pf-form下使用ng g c myList建立元件my-list，其中ts和html就看code吧! 基本上對照finish元件差不多
