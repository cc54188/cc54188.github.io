package com.weAreFleas.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.OrderCarActivityM;
import com.weAreFleas.OrderCarActivityU;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.Order;
import com.weAreFleas.utils.Global;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder>{

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOrdSerial;
        private TextView tvOrdUserIdM;
        private Button btnDetail;
        private TextView tvOrdPrice;
        private TextView tvFinishList;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            Log.d("建構子", "鄧單橋接");
            if(Global.userType == 2) { // 若為客戶
                tvOrdSerial = itemView.findViewById(R.id.tvOrdSerial);
                btnDetail = itemView.findViewById(R.id.btnDetail);
                tvOrdPrice = itemView.findViewById(R.id.tvOrdPrice);
                tvFinishList = itemView.findViewById(R.id.tvFinishList);
            } else {  // 若為管理者
                tvOrdSerial = itemView.findViewById(R.id.tvOrdSerialM);
                btnDetail = itemView.findViewById(R.id.btnDetailM);
                tvOrdUserIdM = itemView.findViewById(R.id.tvOrdUserIdM);
                tvOrdPrice = itemView.findViewById(R.id.tvOrdPriceM);
                tvFinishList = itemView.findViewById(R.id.tvFinishListM);
            }
        }
    }

    private ArrayList<Order> arrayList;
    private Context context;

    public OrderListAdapter(ArrayList<Order> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(Global.userType == 2) { // 使用者
            view = inflater.inflate(R.layout.item_list_order_user, parent, false);
        } else { // 管理者
            view = inflater.inflate(R.layout.item_list_order_m, parent, false);
        }
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.tvOrdSerial.setText("serial: " + arrayList.get(position).getSerial());
        holder.tvOrdPrice.setText("totalPrice: " + arrayList.get(position).getTotalPrice());
        holder.tvFinishList.setText("status: " + arrayList.get(position).getStatus());

        Bundle bundle = new Bundle();
        bundle.putString("SERIAL", arrayList.get(position).getSerial());
        bundle.putInt("TOTALPRICE", arrayList.get(position).getTotalPrice());
        bundle.putString("STATUS", arrayList.get(position).getStatus());

        if(Global.userType == 2) { // 使用者
            holder.btnDetail.setOnClickListener(v -> { // 按下查看明細
                Intent intent = new Intent(context, OrderCarActivityU.class);
                intent.putExtras(bundle); // 把序號帶到下一個頁面
                context.startActivity(intent);
            });
        } else { // 管理者
            holder.tvOrdUserIdM.setText("userId: " + arrayList.get(position).getUserId());
            holder.btnDetail.setOnClickListener(v -> {
                Intent intent = new Intent(context, OrderCarActivityM.class);
                bundle.putString("USERID", arrayList.get(position).getUserId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            });
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
