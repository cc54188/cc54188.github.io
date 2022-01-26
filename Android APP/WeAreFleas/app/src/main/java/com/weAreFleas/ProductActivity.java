package com.weAreFleas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.adapter.ProductListAdapter;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.Product;
import com.weAreFleas.utils.Global;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    private TextView tvTypeName;
    private Button btnBackType;
    private EditText etSearchPro;
    private Button btnSearchPro;
    private Button btnAllPro;

    private RecyclerView rvProduct;
    private ProductListAdapter adapter;
    private ArrayList<Product> products;

    private String search = "";
    private String proType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_products);

        Bundle bundle = getIntent().getExtras(); // HomeUserActivity傳過來
        proType = bundle.getString("PROTYPE");
        Log.d("商品列表u", proType);

        tvTypeName = findViewById(R.id.tvTypeName);
        btnBackType = findViewById(R.id.btnBackType);
        etSearchPro = findViewById(R.id.etSearchPro);
        btnSearchPro = findViewById(R.id.btnSearchPro);
        btnAllPro = findViewById(R.id.btnAllPro);

        rvProduct = findViewById(R.id.rvProduct);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvProduct.setLayoutManager(layoutManager);

        products = new ArrayList<>();
        adapter = new ProductListAdapter(products, ProductActivity.this);
        rvProduct.setAdapter(adapter);

        tvTypeName.setText(proType);

        network();

        btnBackType.setOnClickListener(v -> { // 返回上一頁
            Intent intent;
            if(Global.token.equals("")) { // 未登入-> 回首頁
                intent = new Intent(ProductActivity.this, HomeActivity.class);
            } else {  // 已登入-> 回
                intent = new Intent(ProductActivity.this, HomeUserActivity.class);
            }
            this.startActivity(intent);
        });


        btnSearchPro.setOnClickListener(v -> { // 按下搜尋
            search = etSearchPro.getText().toString();
            network();
        });

        btnAllPro.setOnClickListener(v -> { // 按下全部
            etSearchPro.setText(""); // 把EditText變成空字串
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
            Log.d("連線", "連線");
            JSONArray array = data.getJSONArray("products");
            Log.d("商品們", array.toString());
            products.clear();
            if(search.equals("")) { // 若沒有輸入關鍵字，全列出
                for(int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    Product product = new Product(
                            jsonObject.getString("_id"),
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
                        Product product = new Product(
                                jsonObject.getString("_id"),
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
