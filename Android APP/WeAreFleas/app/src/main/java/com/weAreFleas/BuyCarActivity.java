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

import com.weAreFleas.adapter.CarListAdapter;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.utils.Global;
import com.weAreFleas.utils.NetworkController;

public class BuyCarActivity extends AppCompatActivity {

    private Button btnPay;
    private Button btnTurn;
    private TextView tvCarTotalPrice;

    private int totalPrice = 0;

    private RecyclerView rvCar;
    private CarListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_car);

        btnPay = findViewById(R.id.btnPay);
        btnTurn = findViewById(R.id.btnTurn);
        tvCarTotalPrice = findViewById(R.id.tvCarTotalPrice);

        rvCar = findViewById(R.id.rvCar);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCar.setLayoutManager(layoutManager);

        adapter = new CarListAdapter(BuyCarActivity.this);
        rvCar.setAdapter(adapter);
        // Log.d("購物車頭等艙", buyCars.get(0).getName()); // 有到這邊~

        print();

        AlertDialog dialog = new AlertDialog.Builder(BuyCarActivity.this)
                .setMessage("此次訂單總價: " + totalPrice + "\n確定要結帳嗎?")
                .setCancelable(false)
                .setPositiveButton("SURE", (dialog1, which) -> {
                    Log.d("確認結帳", "click");

                    NetworkController.instance().addOrders(Global.id, "未出貨",
                            Global.buyCars)
                            .onFailure((errorCode, msg) ->  {
                                Log.d("addOrder", msg);
                            }).onResponse(data -> {

                    }).exec();
                    Global.buyCars.clear(); // 清空購物車
                    print(); // 刷新購物車列表(此時應該清空了)
                    Intent intent = new Intent(BuyCarActivity.this, OrdersUserActivity.class);
                    this.startActivity(intent);
                }).setNegativeButton("cancel", ((dialog1, which) -> {
                    Log.d("取消結帳", "click");
                })).create();

        btnPay.setOnClickListener(v -> { // 結帳->產生訂單
            dialog.show();
        });

        btnTurn.setOnClickListener(v -> { // 回到使用者首頁
            Intent intent = new Intent(BuyCarActivity.this, HomeUserActivity.class);
            this.startActivity(intent);
        });
    }

    public void print() {
        adapter.notifyDataSetChanged();

        if(Global.buyCars.size() > 0) { // 若購物車有東西，印出總價錢
            for(int i = 0; i < Global.buyCars.size(); i++) {
                totalPrice += Global.buyCars.get(i).getTotalPrice();
            }
            tvCarTotalPrice.setText("總金額:" + totalPrice);
        } else {
            tvCarTotalPrice.setText("您的購物車內沒有商品。");
        }
    }
}
