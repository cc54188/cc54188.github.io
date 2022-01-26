package com.weAreFleas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.User;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private EditText etAccRegi;
    private EditText etPwdRegi;
    private EditText etNameRegi;
    private EditText etPhoneRegi;
    private EditText etAddressRegi;

    private Button btnSureRegi;
    private Button btnCancelRegi;

    private ArrayList<User> users;
    Boolean can = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAccRegi = findViewById(R.id.etAccRegi);
        etPwdRegi = findViewById(R.id.etPwdRegi);
        etNameRegi = findViewById(R.id.etNameRegi);
        etPhoneRegi = findViewById(R.id.etPhoneRegi);
        etAddressRegi = findViewById(R.id.etAddressRegi);
        btnSureRegi = findViewById(R.id.btnSureRegi);
        btnCancelRegi = findViewById(R.id.btnCancelRegi);

        users = new ArrayList<>();

        AlertDialog dialog = new AlertDialog.Builder(RegisterActivity.this)
                .setMessage("確認資料無誤？")
                .setCancelable(false)
                .setNegativeButton("No", (dialog1, witch) -> {

                }).setPositiveButton("Yes", ((dialog1, which) -> {

                })).create();

        btnSureRegi.setOnClickListener(v -> { // 按下確定
            String acc = etAccRegi.getText().toString();
            Log.d("acc", acc);
            String pwd = etPwdRegi.getText().toString();
            String name = etNameRegi.getText().toString();
            String phone = etPhoneRegi.getText().toString();
            String address = etAddressRegi.getText().toString();

            if(acc.equals("") || pwd.equals("") || name.equals("") // 擋掉沒輸入的
             || phone.equals("") || address.equals("")) {
                return;
            }

            NetworkController.instance().getAllUserAccs() // 比對帳號，重複擋掉
                    .onFailure((errorCode, msg) -> Log.d("比對帳號", msg))
                    .onResponse(data -> {
                        JSONArray array = data.getJSONArray("users");
                        users.clear();
                        for (int i = 0; i < array.length(); i++) { // 跑JSONArray
                            JSONObject jsonObject = array.getJSONObject(i);
                            if(acc.equals(jsonObject.getString("account"))) {
                                Log.d("相同", acc);
                                Log.d("相同", jsonObject.getString("account"));
                                can = false;
                                return;
                            }
                        }
                    }).exec();
            Log.d("布林: ", can.toString());
            dialog.show();
            if(can) { // 若無重複，才給註冊
                Log.d("註冊", "進入");
                NetworkController.instance().register(acc, pwd, name, phone, address)
                        .onFailure((errorCode, msg) -> Log.d("客戶首頁", msg))
                        .onResponse(data1 -> {
                        }).exec();
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnCancelRegi.setOnClickListener(v -> { // 按下取消
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}
