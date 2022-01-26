package com.weAreFleas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.adapter.OrdCarListAdapter;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.BuyCar;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderCarActivityU extends AppCompatActivity {

    private TextView tvCarOrdSerial;
    private Button btnCarOrdTurn;
    private TextView tvTotalPrice;
    private TextView tvStatus;
    private Button btnCancelOrd;

    private RecyclerView rvOrderCar;
    private ArrayList<BuyCar> orderCars;
    private OrdCarListAdapter adapter;

    private String serial;
    private String status;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_car_order_u);
        // 上個頁面的資料(序號)傳過來
        Bundle bundle = getIntent().getExtras();// OrderListAdapter傳來的
        serial = bundle.getString("SERIAL");
        status = bundle.getString("STATUS");
        totalPrice = bundle.getInt("TOTALPRICE");
        // 連結元件與變數
        tvCarOrdSerial = findViewById(R.id.tvCarOrdSerial);
        btnCarOrdTurn = findViewById(R.id.btnCarOrdTurn);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvStatus = findViewById(R.id.tvStatus);
        btnCancelOrd = findViewById(R.id.btnCancelOrd);
        // 連結後，決定RecyclerView的長相
        rvOrderCar = findViewById(R.id.rvOrderCar);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvOrderCar.setLayoutManager(layoutManager);
        // 建立表單和RV的橋接器
        orderCars = new ArrayList<>();
        adapter = new OrdCarListAdapter(orderCars, OrderCarActivityU.this);
        rvOrderCar.setAdapter(adapter);

        tvCarOrdSerial.setText("訂單編號: " + serial.toString());
        tvTotalPrice.setText("訂單總金額: " + totalPrice);
        tvStatus.setText("訂單狀況: " + status);

        netWork(); // 印出該訂單

        btnCarOrdTurn.setOnClickListener(v -> { // 返回該用戶訂單列表
            intentFunc(OrdersUserActivity.class);
        });
        // 確定對話框(取消 or 回首頁)
        AlertDialog dialog;
        if(status.equals("未出貨")) { // 未出貨才可取消
            btnCancelOrd.setText("取消訂單");
            dialog = new AlertDialog.Builder(OrderCarActivityU.this)
                    .setMessage("確定取消此次訂單?")
                    .setCancelable(false)
                    .setPositiveButton("Sure", (dialog1, which) -> {
                        NetworkController.instance().cancelOrder(serial)
                                .onFailure((errorCode, msg) -> {
                                    Log.d("取消orderCar", msg);
                                }).onResponse(data -> {
                                    Log.d("取消訂單", "按下");
                        }).exec();
                        intentFunc(OrdersUserActivity.class); // 返回訂單列表
                    }).setNegativeButton("Cancel", (dialog1, which) -> {
                        Log.d("不取消訂單", "按下");
                    }).create();
        } else { // 若非未出貨，則讓用戶回首頁
            btnCancelOrd.setText("首頁");
            dialog = new AlertDialog.Builder(OrderCarActivityU.this)
                    .setMessage("確定返回首頁?")
                    .setCancelable(false)
                    .setPositiveButton("sure", (dialog1, which) -> {
                        intentFunc(HomeUserActivity.class); // 跳至使用者首頁
                    }).setNegativeButton("Cancel", (dialog1, which) -> {
                        Log.d("取消回首頁", "按下");
                    }).create();
        }

        btnCancelOrd.setOnClickListener(v -> {
            dialog.show();
        });
    }

    public void netWork() {
        NetworkController.instance().getBySerial(serial)
                .onFailure((errorCode, msg) -> {
                    Log.d("印出OrderCar", msg);
                }).onResponse(data -> {
            JSONArray jsonArray = data.getJSONArray("buyCars");
            orderCars.clear();

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject buyCar = jsonObject.getJSONObject("buyCar");
                BuyCar orderCar = new BuyCar(
                        buyCar.getString("name"),
                        buyCar.getInt("amount"),
                        buyCar.getInt("price")
                );
                orderCars.add(orderCar);
            }
            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
            });
        }).exec();
    }

    public void intentFunc(Class cls) {
        Intent intent = new Intent(OrderCarActivityU.this, cls);
        this.startActivity(intent);
    }

}
