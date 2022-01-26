package com.weAreFleas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.dobuy.R;
import com.weAreFleas.utils.NetworkController;
import com.weAreFleas.adapter.ProductListAdapter;
import com.weAreFleas.models.Product;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductActivityM extends AppCompatActivity {
    private TextView tvTypeNameM;
    private Button btnAddPro;
    private Button btnBackTypeM;
    private EditText etSearchProM;
    private Button btnSearchProM;
    private Button btnAllProM;

    private RecyclerView rvProductM;
    private ProductListAdapter adapter;
    private ArrayList<Product> products;

    private String search = "";
    private String proType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_products_m);

        Bundle bundle = getIntent().getExtras(); // HomeManagerActivity傳過來
        proType = bundle.getString("PROTYPE");
        Log.d("商品列表m", proType);

        tvTypeNameM = findViewById(R.id.tvTypeNameM);
        btnAddPro = findViewById(R.id.btnAddPro);
        btnBackTypeM = findViewById(R.id.btnBackTypeM);
        etSearchProM = findViewById(R.id.etSearchProM);
        btnSearchProM = findViewById(R.id.btnSearchProM);
        btnAllProM = findViewById(R.id.btnAllProM);

        rvProductM = findViewById(R.id.rvProductM);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvProductM.setLayoutManager(layoutManager);

        products = new ArrayList<>();
        adapter = new ProductListAdapter(products, ProductActivityM.this);
        rvProductM.setAdapter(adapter);

        tvTypeNameM.setText(proType);

        network();

        // 新增
        // 先跑小視窗
        View contentView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_add_product, null);

        AlertDialog addProDL = new AlertDialog.Builder(ProductActivityM.this)
                .setCancelable(false)
                .setView(contentView)
                .create();

        TextView tvAddProType = contentView.findViewById(R.id.tvAddProType);
        tvAddProType.setText(proType);
        EditText etAddProName = contentView.findViewById(R.id.etAddProName);
        EditText etAddProDesc = contentView.findViewById(R.id.etAddProDesc);
        EditText etAddProPrice = contentView.findViewById(R.id.etAddProPrice);
        Button btnAddProSure = contentView.findViewById(R.id.btnAddProSure);
        Button btnAddProCancel = contentView.findViewById(R.id.btnAddProCancel);

        btnAddProSure.setOnClickListener(v -> { // 按下確認
            String name = etAddProName.getText().toString();
            String desc = etAddProDesc.getText().toString();
            int price = Integer.parseInt(etAddProPrice.getText().toString());
            NetworkController.instance().addProducts(proType, name, desc, price)
                    .onFailure((errorCode, msg) -> Log.d("新增商品失敗", msg))
                    .onResponse(data -> {
                        Log.d("新增商品", "成功!");
                    }).exec();
            network();
            addProDL.dismiss(); // 關掉dialog
        });

        btnAddProCancel.setOnClickListener(v -> addProDL.dismiss()); // 按取消

        btnAddPro.setOnClickListener(v -> { // 新增商品
            addProDL.show();
        });

        btnBackTypeM.setOnClickListener(v -> { // 返回管理者首頁
            Intent intent = new Intent(ProductActivityM.this, HomeManagerActivity.class);
            this.startActivity(intent);
        });


        btnSearchProM.setOnClickListener(v -> { // 按下搜尋
            search = etSearchProM.getText().toString();
            Log.d("搜尋字串", search);
            network();
        });

        btnAllProM.setOnClickListener(v -> { // 按下全部
            etSearchProM.setText(""); // 把EditText變成空字串
            search = ""; // 關鍵字變成空字串
            network();
        });

    }

    public void network() {
        NetworkController.instance()
                .getByProType(proType) // 只撈出該種類的商品
                .onFailure((errorCode, msg) -> { // 實現此介面的action方法
                    Log.d("getByProType", msg);
                }).onResponse(data -> {
            JSONArray array = data.getJSONArray("products");
            Log.d("商品們", array.toString());
            products.clear();
            if(search.equals("")) { // 若沒有輸入關鍵字，全列出
                for(int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    Product product = new Product(
                            jsonObject.getString("_id"), // 只有管理者可看到
                            jsonObject.getString("proType"),
                            jsonObject.getString("name"),
                            jsonObject.getString("desc"),
                            jsonObject.getInt("price")
                    );
                    products.add(product);
                }
            } else {  // 列出含關鍵字的分類列表
                for(int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    if(jsonObject.getString("name").contains(search)) {
                        Log.d("搜尋", jsonObject.toString());
                        Product product = new Product(
                                jsonObject.getString("_id"), // 只有管理者可看到
                                jsonObject.getString("proType"),
                                jsonObject.getString("name"),
                                jsonObject.getString("desc"),
                                jsonObject.getInt("price")
                        );
                        products.add(product);
                    }
                }
            }
            runOnUiThread(() ->{
                adapter.notifyDataSetChanged();
            });
        }).exec();
    }
}
