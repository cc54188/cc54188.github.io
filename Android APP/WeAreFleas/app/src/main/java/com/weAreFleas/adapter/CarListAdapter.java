package com.weAreFleas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.BuyCarActivity;
import com.weAreFleas.dobuy.R;

import static com.weAreFleas.utils.Global.buyCars;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarViewHolder> {

    static class CarViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNameBuy;
        private TextView tvAmount;
        private TextView tvPriceBuy;
        private Button btnCancelCar;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameBuy = itemView.findViewById(R.id.tvNameBuy);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvPriceBuy = itemView.findViewById(R.id.tvPriceBuy);
            btnCancelCar = itemView.findViewById(R.id.btnCancelCar);
        }
    }

    private Context context;

    public CarListAdapter(Context  context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CarListAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.tvNameBuy.setText("商品: " + buyCars.get(position).getName());
        holder.tvAmount.setText("數量: " + buyCars.get(position).getAmount());
        holder.tvPriceBuy.setText("總價: " + String.valueOf(buyCars.get(position).getTotalPrice()));

        holder.btnCancelCar.setOnClickListener(v -> { // 從購物車中刪除
            buyCars.remove(position);

            BuyCarActivity buyCarActivity = (BuyCarActivity)context;
            buyCarActivity.print();
        });
    }

    @Override
    public int getItemCount() {
        return buyCars.size();
    }
}
