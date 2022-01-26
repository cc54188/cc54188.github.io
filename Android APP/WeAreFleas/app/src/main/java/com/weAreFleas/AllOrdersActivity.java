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

import com.weAreFleas.adapter.OrderListAdapter;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.Order;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllOrdersActivity extends AppCompatActivity {

    private TextView tvOrders;
    private Button btnAllOrders;
    private EditText etOrdByUser;
    private Button btnSearchByU;
    private EditText etOrdByS;
    private Button btnSearchByS;
    private Button btnOrderMTurn;

    private RecyclerView rvOrdersM;
    private ArrayList<Order> orders;
    private OrderListAdapter adapter;

    private String idSearch = "";
    private String serialSearch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_orders_manage);

        tvOrders = findViewById(R.id.tvOrders);
        btnAllOrders = findViewById(R.id.btnAllOrders);
        etOrdByUser = findViewById(R.id.etOrdByUser);
        btnSearchByU = findViewById(R.id.btnSearchByU);
        etOrdByS = findViewById(R.id.etOrdByS);
        btnSearchByS = findViewById(R.id.btnSearchByS);
        btnOrderMTurn = findViewById(R.id.btnOrderMTurn);

        rvOrdersM = findViewById(R.id.rvOrdersM);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvOrdersM.setLayoutManager(layoutManager);

        orders = new ArrayList<>();
        adapter = new OrderListAdapter(orders, AllOrdersActivity.this);
        rvOrdersM.setAdapter(adapter);

        netWork();

        btnAllOrders.setOnClickListener(v -> {
            idSearch = "";
            etOrdByUser.setText(idSearch);
            serialSearch = "";
            etOrdByS.setText(serialSearch);

            netWork();
        });

        btnSearchByU.setOnClickListener(v -> {
            serialSearch = "";
            etOrdByS.setText(serialSearch);
            idSearch = etOrdByUser.getText().toString();

            netWork();
        });

        btnSearchByS.setOnClickListener(v -> {
            idSearch = "";
            etOrdByUser.setText(idSearch);
            serialSearch = etOrdByS.getText().toString();

            netWork();
        });

        btnOrderMTurn.setOnClickListener(v -> {
            Intent intent = new Intent(AllOrdersActivity.this, HomeManagerActivity.class);
            startActivity(intent);

            finish();
        });
    }

    public void netWork() {
        NetworkController.instance().getAllOrders()
                .onFailure((errorCode, msg) -> {
                    Log.d("全訂單失敗", msg);
                }).onResponse(data -> {
            JSONArray jsonArray = data.getJSONArray("orders");
            orders.clear();
            if(serialSearch.equals("")) {
                if(idSearch.equals("")) {
                    for(int i = 0; i < jsonArray.length(); i++) {  // 全部
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Order order = new Order(
                                jsonObject.getString("serial"),
                                jsonObject.getString("user"),
                                jsonObject.getInt("totalPrice"),
                                jsonObject.getString("status")
                        );
                        orders.add(order);
                    }
                } else {
                    for(int i = 0; i < jsonArray.length(); i++) {  // 用使用者搜尋
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if(jsonObject.getString("user").equals(idSearch)) {
                            Order order = new Order(
                                    jsonObject.getString("serial"),
                                    idSearch,
                                    jsonObject.getInt("totalPrice"),
                                    jsonObject.getString("status")
                            );
                            orders.add(order);
                        }
                    }
                }
            } else {
                for(int i = 0; i < jsonArray.length(); i++) { // 用序號搜尋
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if(jsonObject.getString("serial").equals(serialSearch)) {
                        Order order = new Order(
                                serialSearch,
                                jsonObject.getString("user"),
                                jsonObject.getInt("totalPrice"),
                                jsonObject.getString("status")
                        );
                        orders.add(order);
                    }
                }
            }
            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
            });
        }).exec();
    }
}
