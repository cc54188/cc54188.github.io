package com.weAreFleas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.weAreFleas.dobuy.R;
import com.weAreFleas.utils.Global;
import com.weAreFleas.utils.NetworkController;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText etAccount;
    private EditText etPassword;
    private Button btnSureLogin;
    private Button btnCancelLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etAccount = findViewById(R.id.etAccount);
        etPassword = findViewById(R.id.etPassword);
        btnSureLogin = findViewById(R.id.btnSureLogin);
        btnCancelLogin = findViewById(R.id.btnCancelLoglin);

        btnSureLogin.setOnClickListener(v -> { // 按下確定登入
            Global.acc = etAccount.getText().toString();
            String pwd = etPassword.getText().toString();
            if(Global.acc.equals("") || pwd.equals("")) {
                return;
            }
            NetworkController.instance().login(Global.acc, pwd)
                    .onResponse(data -> {
                        Global.token = data.getString("token");
                        JSONObject jsonObject = data.getJSONObject("user");
                        Global.id = jsonObject.getString("_id"); // 使用者id
                        Global.userType = jsonObject.getInt("type"); // 使用者type
                        runOnUiThread(()-> {
                            Intent intent;
                            if(Global.token == "") {
                                return;
                            }
                            if(Global.userType == 1) { // 判斷管理者(轉管理頁面) or客戶
                                intent = new Intent(LoginActivity.this,
                                        HomeManagerActivity.class);
                                Log.d("Global.userType", "1");
                            } else { // 轉客戶頁面
                                intent = new Intent(LoginActivity.this,
                                        HomeUserActivity.class);
                            }

                            startActivity(intent); // 隨intent帶到 管理 或 客戶首頁
                            finish();
                        });
                    }).onFailure(((errorCode, msg) -> Log.d("onFailure", msg)))
                    .exec();
        });

        btnCancelLogin.setOnClickListener(v -> { // 取消
            Intent intent = new Intent(LoginActivity.this,
                    HomeActivity.class);
            startActivity(intent);
        });
    }
}
