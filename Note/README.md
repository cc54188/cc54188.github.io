# 筆記

1.上傳kotlin app時遇到 developer.android.com/about/versions/12/behavior-changes-12#exported 訊息
  解法: 在manifest加上 android:exported="true" 或 android:exported="false"
  ``` xml
          <activity
            android:name=".ui.ManageAccountActivity"
            android:label="@string/title_activity_manage_accounts"
            android:launchMode="singleTask"
            android:exported="true"/> ```
