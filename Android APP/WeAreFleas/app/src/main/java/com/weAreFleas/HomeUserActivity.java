package com.weAreFleas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.weAreFleas.adapter.ProTypeListAdapter;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.ProType;
import com.weAreFleas.utils.Global;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeUserActivity extends AppCompatActivity {

    private TextView tvUserWelcome;
    private Button btnBuyCar;
    private Button btnData;
    private Button btnSignOut;
    private EditText etSearchTypeU;
    private Button btnSearchTypeU;
    private Button btnAllTypeU;

    private RecyclerView rvProType;
    private ProTypeListAdapter adapter;
    private ArrayList<ProType> proTypes;

    private String search = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

//        Bundle bundle = getIntent().getExtras(); // login傳過來
//        token = bundle.getString("TOKEN");
//        acc = bundle.getString("ACCOUNT");
//        Log.d("HomeUserActivity", token); // 印出token驗證一下
//        Log.d("使用者首頁", acc);

        tvUserWelcome = findViewById(R.id.tvUserWelcome);
        btnBuyCar = findViewById(R.id.btnBuyCar);
        btnData = findViewById(R.id.btnData);
        btnSignOut = findViewById(R.id.btnSignOut);
        etSearchTypeU = findViewById(R.id.etSearchTypeU);
        btnSearchTypeU = findViewById(R.id.btnSearchTypeU);
        btnAllTypeU = findViewById(R.id.btnAllTypeU);

        rvProType = findViewById(R.id.rvProType);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvProType.setLayoutManager(layoutManager);

        proTypes = new ArrayList<>();
        adapter = new ProTypeListAdapter(proTypes, HomeUserActivity.this);
        rvProType.setAdapter(adapter);

        tvUserWelcome.setText(Global.acc + "您好");
        network();

        btnBuyCar.setOnClickListener(v -> { // 查看購物車
            Intent intent = new Intent(HomeUserActivity.this, BuyCarActivity.class);
            this.startActivity(intent);
        });

        btnData.setOnClickListener(v -> { // 管理帳號
            Intent intent =
                    new Intent(HomeUserActivity.this, UserAreaActivity.class);
            this.startActivity(intent);
        });

        btnSignOut.setOnClickListener(v -> { // 登出操作
            Global.token = ""; // 把簽證清掉
            Intent intent =
                    new Intent(HomeUserActivity.this, HomeActivity.class);
            this.startActivity(intent);
        });

        btnSearchTypeU.setOnClickListener(v -> {
            search = etSearchTypeU.getText().toString();
            network();
        });

        btnAllTypeU.setOnClickListener(v -> { // 按下全部分類按鈕
            etSearchTypeU.setText(""); // 把EditText變成空字串
            search = ""; // 關鍵字變成空字串
            network();
        });

    }

    public void network() {
        NetworkController.instance()
                .getAllProTypes() // 目前是全撈出來，再顯示你要的
                .onFailure((errorCode, msg) -> { // 實現此介面的action方法
                    Log.d("getAllProTypes", msg);
                }).onResponse(data -> {
            Log.d("net", "連線");
            JSONArray array = data.getJSONArray("proTypes");
            proTypes.clear();
            if(search.equals("")) { // 若沒有輸入關鍵字，全列出
                for(int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    ProType proType = new ProType(
                            jsonObject.getString("name"),
                            jsonObject.getString("desc")
                    );
                    proTypes.add(proType);
                }
            } else {  // 列出含關鍵字的分類列表
                for(int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    if(jsonObject.getString("name").contains(search)) {
                        ProType proType = new ProType(
                                jsonObject.getString("name"),
                                jsonObject.getString("desc")
                        );
                        proTypes.add(proType);
                    }
                }
            }
            runOnUiThread(() ->{
                adapter.notifyDataSetChanged();
            });
        }).exec();
    }
}