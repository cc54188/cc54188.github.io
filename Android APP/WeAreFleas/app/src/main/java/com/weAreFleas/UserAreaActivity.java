package com.weAreFleas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.weAreFleas.dobuy.R;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONObject;

public class UserAreaActivity extends AppCompatActivity {

    private EditText etNameUser;
    private EditText etPhoneUser;
    private EditText etAddressUser;
    private Button btnOrderUser;
    private Button btnUserModi;
    private Button btnLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        etNameUser = findViewById(R.id.etNameUser);
        etPhoneUser = findViewById(R.id.etPhoneUser);
        etAddressUser = findViewById(R.id.etAddressUser);
        btnOrderUser = findViewById(R.id.btnOrderUser);
        btnUserModi = findViewById(R.id.btnUserModi);
        btnLeave = findViewById(R.id.btnLeave);

        netWork();

        btnOrderUser.setOnClickListener(v -> { // 跳轉至訂單紀錄
            Intent intent = new Intent(UserAreaActivity.this, OrdersUserActivity.class);
            startActivity(intent);
        });

        btnUserModi.setOnClickListener(v -> { // 修改客戶資料
            String nameUser = etNameUser.getText().toString();
            String phoneUser = etPhoneUser.getText().toString();
            String addressUser = etAddressUser.getText().toString();

            NetworkController.instance().modifyUser(nameUser, phoneUser, addressUser)
                    .onFailure((errorCode, msg) -> {
                        Log.d("modifyUser", msg);
                    }).onResponse(data -> {

            }).exec();

            netWork();
        });

        btnLeave.setOnClickListener(v -> {
            Intent intent = new Intent(UserAreaActivity.this, HomeUserActivity.class);
            startActivity(intent);
        });
    }

    public void netWork() {
        NetworkController.instance().getUser()
                .onFailure((errorCode, msg) -> {
                    Log.d("getUser", msg);
                }).onResponse(data -> {
                    JSONObject jsonObject = data.getJSONObject("user");
                    etNameUser.setText(jsonObject.getString("name"));
                    etPhoneUser.setText(jsonObject.getString("phone"));
                    etAddressUser.setText(jsonObject.getString("address"));
        }).exec();
    }
}
