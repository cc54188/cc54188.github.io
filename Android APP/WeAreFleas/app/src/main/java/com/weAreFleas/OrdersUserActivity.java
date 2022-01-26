package com.weAreFleas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.adapter.OrderListAdapter;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.Order;
import com.weAreFleas.utils.Global;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrdersUserActivity extends AppCompatActivity {

    private Button btnOrdTurn;
    private EditText etOrdSerial;
    private Button btnOrdSearch;
    private Button btnOrdAll;
    private RecyclerView rvOrdersU;

    private ArrayList<Order> orders;
    private OrderListAdapter adapter;
    private String search = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_orders_user);

        btnOrdTurn = findViewById(R.id.btnOrdTurn);
        etOrdSerial = findViewById(R.id.etOrdSerial);
        btnOrdSearch = findViewById(R.id.btnOrdSearch);
        btnOrdAll = findViewById(R.id.btnOrdAll);
        rvOrdersU = findViewById(R.id.rvOrdersU);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvOrdersU.setLayoutManager(layoutManager);

        orders = new ArrayList<>();
        adapter = new OrderListAdapter(orders, OrdersUserActivity.this);
        rvOrdersU.setAdapter(adapter);

        netWork();

        btnOrdTurn.setOnClickListener(v -> { // 返回->首頁
            Intent intent = new Intent(OrdersUserActivity.this, HomeUserActivity.class);
            this.startActivity(intent);
        });

        btnOrdSearch.setOnClickListener(v -> {
            search = etOrdSerial.getText().toString();
            netWork();
        });

        btnOrdAll.setOnClickListener(v -> {
            etOrdSerial.setText("");
            search = "";
            netWork();
        });
    }

    public void netWork() {
        NetworkController.instance().getByUserId(Global.id)
                .onFailure((errorCode, msg) -> {
                    Log.d("getByUserId", msg);
                }).onResponse(data -> {
            JSONArray jsonArray = data.getJSONArray("orders");
            orders.clear(); // 先清空
            if(search.equals("")) {
                for(int i = 0; i <jsonArray.length(); i++) {
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
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if(jsonObject.getString("serial").equals(search)) {
                        Order order = new Order(
                                jsonObject.getString("serial"),
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
