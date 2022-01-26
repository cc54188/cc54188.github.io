package com.weAreFleas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.adapter.ProTypeListAdapter;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.ProType;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;
    private EditText etSearchType;
    private Button btnSearchType;
    private Button btnAllType;

    private RecyclerView rvProType;
    private ProTypeListAdapter adapter;
    private ArrayList<ProType> proTypes;
    private String search = ""; // 初始化空字串

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // 使用者狀態欄
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        etSearchType = findViewById(R.id.etSearchType);
        btnSearchType = findViewById(R.id.btnSearchType);
        btnAllType = findViewById(R.id.btnAllType);

        btnLogin.setOnClickListener(v -> { // 進入登入畫面
            Intent intent =
                    new Intent(HomeActivity.this, LoginActivity.class);
            this.startActivity(intent);
        });

        btnRegister.setOnClickListener(v -> { // 進入註冊畫面
            Intent intent =
                    new Intent(HomeActivity.this, RegisterActivity.class);
            this.startActivity(intent);
        });

        btnSearchType.setOnClickListener(v -> { // 按下搜尋按鈕
            search = etSearchType.getText().toString();
            networkPrint();
        });

        btnAllType.setOnClickListener(v -> { // 按下全部分類按鈕
            etSearchType.setText(""); // 把EditText變成空字串
            search = ""; // 關鍵字變成空字串
            networkPrint();
        });

        rvProType = findViewById(R.id.rvProType);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvProType.setLayoutManager(layoutManager);

        proTypes = new ArrayList<>();
        adapter = new ProTypeListAdapter(proTypes, HomeActivity.this);
        rvProType.setAdapter(adapter);

        networkPrint();
    }

    private void networkPrint() { // 連線更新分類列表
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
