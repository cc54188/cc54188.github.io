package com.weAreFleas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.BuyCar;

import java.util.ArrayList;

public class OrdCarListAdapter extends RecyclerView.Adapter<OrdCarListAdapter.OrdCarViewHolder>{

    static class OrdCarViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCarOrdPro;
        private TextView tvCarOrdAmount;
        private TextView tvCarOrdPrice;

        public OrdCarViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCarOrdPro = itemView.findViewById(R.id.tvCarOrdPro);
            tvCarOrdAmount = itemView.findViewById(R.id.tvCarOrdAmount);
            tvCarOrdPrice = itemView.findViewById(R.id.tvCarOrdPrice);
        }
    }

    private ArrayList<BuyCar> arrayList;
    private Context context;

    public OrdCarListAdapter(ArrayList<BuyCar> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrdCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_car_ord, parent, false);
        return new OrdCarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdCarViewHolder holder, int position) {
        holder.tvCarOrdPro.setText("商品: " + arrayList.get(position).getName());
        holder.tvCarOrdAmount.setText("數量: " + arrayList.get(position).getAmount());
        holder.tvCarOrdPrice.setText("總價: " + arrayList.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
