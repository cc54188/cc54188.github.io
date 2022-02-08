# Tim 的git空間
### Andorid APP

- 1.[BMIComputer](https://github.com/cc54188/cc54188.github.io/tree/main/Android%20APP/BMIComputer/app)(身高體重比計算機)
- 2.[WeAreFleas](https://github.com/cc54188/cc54188.github.io/tree/main/Android%20APP/WeAreFleas)(跳蚤小拍賣)

### Computer Game
- 1.[Running Loser](https://github.com/cc54188/cc54188.github.io/tree/main/Computer%20Game/Running%20Loser)(NetBeans遊戲)

### 筆記
1.上傳kotlin app時遇到 developer.android.com/about/versions/12/behavior-changes-12#exported 訊息
  解法: 在manifest加上 android:exported="true" 或 android:exported="false"
  ``` xml
          <activity
            android:name=".ui.ManageAccountActivity"
            android:label="@string/title_activity_manage_accounts"
            android:launchMode="singleTask"
            android:exported="true"/> ```
