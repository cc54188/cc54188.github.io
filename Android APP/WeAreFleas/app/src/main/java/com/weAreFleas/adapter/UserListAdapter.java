package com.weAreFleas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    static class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUCardName;
        private TextView tvUCardPhone;
        private TextView tvUCardAddress;
        private Button btnUCardOrder;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            // 連結item各元件
            tvUCardName = itemView.findViewById(R.id.tvUCardName);
            tvUCardPhone = itemView.findViewById(R.id.tvUCradPhone);
            tvUCardAddress = itemView.findViewById(R.id.tvUCardAddress);
            btnUCardOrder = itemView.findViewById(R.id.btnUCardOrder);
        }
    }

    private ArrayList<User> arrayList;
    private String token = "";

    public UserListAdapter(ArrayList<User> arrayList, String token) {
        this.arrayList = arrayList;
        this.token = token;
    }

    @NonNull
    @Override
    public UserListAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.UserViewHolder holder, int position) {
        holder.tvUCardName.setText(arrayList.get(position).getName());
        holder.tvUCardPhone.setText(arrayList.get(position).getPhone());
        holder.tvUCardAddress.setText(arrayList.get(position).getAddress());

        holder.btnUCardOrder.setOnClickListener(v -> { // 查此使用者訂單

        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
