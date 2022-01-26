package com.weAreFleas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.adapter.ProTypeListAdapter;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.ProType;
import com.weAreFleas.utils.Global;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeManagerActivity extends AppCompatActivity {
    private Button btnSignOutManager;
    private Button btnAddType;
    private Button btnUsers;
    private Button btnOrders;
    private Button btnAllTypeM;
    private Button btnSearchTypeM;
    private EditText etSearchTypeM;

    private RecyclerView rvProType;
    private ProTypeListAdapter adapter;
    private ArrayList<ProType> proTypes;
    private String search = ""; // 初始化空字串

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manage);

        btnSignOutManager = findViewById(R.id.btnSignOutManager); // 連結各元件
        btnAddType = findViewById(R.id.btnAddType);
        btnUsers = findViewById(R.id.btnUsers);
        btnOrders = findViewById(R.id.btnOrders);
        btnAllTypeM = findViewById(R.id.btnAllTypeM);
        btnSearchTypeM = findViewById(R.id.btnSearchTypeM);
        etSearchTypeM = findViewById(R.id.etSearchTypeM);

        rvProType = findViewById(R.id.rvProType);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);
        rvProType.setLayoutManager(layoutManager);

        proTypes = new ArrayList<>();
        adapter = new ProTypeListAdapter(proTypes, HomeManagerActivity.this);
        rvProType.setAdapter(adapter);
        // 連線取得所有商品分類
        network();

        btnSignOutManager.setOnClickListener(v -> { // 登出回首頁
            Intent intent = new Intent(HomeManagerActivity.this,
                    HomeActivity.class);
            Global.token = ""; // 清空token
            Global.userType = 2;
            Global.acc = "";
            Global.id = "";
            startActivity(intent);
        });

        btnSearchTypeM.setOnClickListener(v -> { // 按下搜尋按鈕
            search = etSearchTypeM.getText().toString(); // 取得字串
            network();
        });

        btnAllTypeM.setOnClickListener(v -> { // 按下全部分類按鈕
            etSearchTypeM.setText(""); // 把EditText變成空字串
            search = ""; // 關鍵字變成空字串
            network();
        });
        // 增加分類的dialog
        View contentView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_add_protype, null);
        Button btnAddTypeSure = contentView.findViewById(R.id.btnAddTypeSure);
        Button btnAddTypeCancel = contentView.findViewById(R.id.btnAddTypeCancel);
        EditText etAddTypeName = contentView.findViewById(R.id.etAddTypeName);
        EditText etAddTypeDesc = contentView.findViewById(R.id.etAddTypeDesc);

        AlertDialog addTypeDL = new AlertDialog.Builder(HomeManagerActivity.this)
                .setCancelable(false)
                .setView(contentView)
                .create(); // 先建好dialog


        btnAddTypeSure.setOnClickListener(v -> { // 按下確認鍵
            String name = etAddTypeName.getText().toString();
            String desc = etAddTypeDesc.getText().toString();
            // 新增分類
            NetworkController.instance().addProTypes(name, desc)
                    .onResponse(data -> {

                    })
                    .onFailure((errorCode, msg) -> {
                        Log.d("addProType", msg);
                    }).exec();

            network();

            addTypeDL.dismiss(); // 關掉dialog
        });

        btnAddTypeCancel.setOnClickListener(v -> addTypeDL.dismiss()); // 按下取消鍵

        btnAddType.setOnClickListener(v -> { // 新增商品分類
            addTypeDL.show();  // 顯示dialog
        });

        btnUsers.setOnClickListener(v -> { // 查看客戶列表
            runOnUiThread(() -> {
                // 跳轉至用戶列表
                Intent intent = new Intent(HomeManagerActivity.this, UsersListActivity.class);

                startActivity(intent);
                finish(); 
            });
        });

        btnOrders.setOnClickListener(v -> {
            Intent intent = new Intent(HomeManagerActivity.this, AllOrdersActivity.class);
            startActivity(intent);
            // 測試一下不用finish();
        });
    }

    public void network() {
        NetworkController.instance().getAllProTypesId()
                .onFailure((errorCode, msg) -> {
                    Log.d("getAllProTypes", msg);
                }).onResponse(data -> {
            JSONArray array = data.getJSONArray("proTypes");
            proTypes.clear();
            if(search.equals("")) { // 若沒有輸入關鍵字，全列出
                for(int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    ProType proType = new ProType(
                            jsonObject.getString("name"),
                            jsonObject.getString("desc"),
                            jsonObject.getString("_id")
                    );
                    proTypes.add(proType);
                }
            } else {  // 列出含關鍵字的分類列表
                for(int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    if(jsonObject.getString("name").contains(search)) {
                        ProType proType = new ProType(
                                jsonObject.getString("name"),
                                jsonObject.getString("desc"),
                                jsonObject.getString("_id")
                        );
                        proTypes.add(proType);
                    }
                }
            }
            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
            });
        }).exec();
    }
}
