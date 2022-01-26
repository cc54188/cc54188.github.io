package com.weAreFleas.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.HomeManagerActivity;
import com.weAreFleas.ProductActivity;
import com.weAreFleas.ProductActivityM;
import com.weAreFleas.utils.NetworkController;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.ProType;
import com.weAreFleas.utils.Global;

import java.util.ArrayList;

public class ProTypeListAdapter extends RecyclerView.Adapter<ProTypeListAdapter.ProTypeViewHolder> {

    // 綁定自己刻的元件
    static class ProTypeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameType;
        private TextView tvDescType;
        private TextView tvTypeId;
        private Button btnListPro;
        private Button btnModify;
        private Button btnDelete;

        public ProTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            if(Global.userType == 1) { // 管理者
                tvNameType = itemView.findViewById(R.id.tvNameTypeM);
                tvDescType = itemView.findViewById(R.id.tvDescTypeM);
                tvTypeId = itemView.findViewById(R.id.tvTypeId);
                btnListPro = itemView.findViewById(R.id.btnListProM);
                btnModify = itemView.findViewById(R.id.btnModify);
                btnDelete = itemView.findViewById(R.id.btnDeletePro);
            } else {  // 客戶
                tvNameType = itemView.findViewById(R.id.tvNameType);
                tvDescType = itemView.findViewById(R.id.tvDescType);
                btnListPro = itemView.findViewById(R.id.btnListPro);
            }
        }
    }

    private ArrayList<ProType> arrayList;
    private Context context;


    public ProTypeListAdapter(ArrayList<ProType> arrayList, Context context) { // 記得改回<proType>
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProTypeListAdapter.ProTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(Global.userType == 1) { // 管理者的商品分類列表
            view = inflater.inflate(R.layout.item_list_type_manager, parent, false);
        } else { // 使用者的商品分類列表
            view = inflater.inflate(R.layout.item_list_type_pro, parent, false);
        }
        return new ProTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProTypeListAdapter.ProTypeViewHolder holder, int position) {
        String name = arrayList.get(position).getName();
        String desc = arrayList.get(position).getDesc();
        if(Global.userType == 1) { // 若是管理者
            holder.tvNameType.setText(name);
            holder.tvDescType.setText(desc);
            holder.tvTypeId.setText(arrayList.get(position).getId());
            holder.btnListPro.setOnClickListener(view -> { // 按下商品清單鍵
                Intent intent = new Intent(context,
                        ProductActivityM.class);

                Bundle bundle = new Bundle();
                bundle.putString("PROTYPE", arrayList.get(position).getName());
                intent.putExtras(bundle); // 資料放intent才能帶過去

                context.startActivity(intent); // 隨intent帶到MainActivity
            });

            AlertDialog reMoveAd = new AlertDialog.Builder(context)
                    .setMessage("確定要刪除此分類?")
                    .setCancelable(false)
                    .setPositiveButton("確定", ((dialog1, which) -> {
                        NetworkController.instance().deleteProType(name)
                                .onResponse(data -> { // 成功
                                    Log.d("刪除成功", name);
                                })
                                .onFailure((errorCode, msg) -> { // 失敗
                                    Log.d("刪除失敗", msg);
                                }).exec();
                        HomeManagerActivity homeManagerActivity = (HomeManagerActivity)context;

                        homeManagerActivity.network(); // 印出分類列表
                    })).setNegativeButton("取消", ((dialog1, which) -> {

                    })).create();

            holder.btnDelete.setOnClickListener(v -> { // 按下刪除鍵
                reMoveAd.show();
            });

            // 建立修改分類的dialog
            View contentView = LayoutInflater.from(context)
                    .inflate(R.layout.dialog_modify_type_pro, null);
            Button btnModifyTypeSure = contentView.findViewById(R.id.btnModifyTypeSure); // 確定
            Button btnModifyTypeCancel = contentView.findViewById(R.id.btnModifyTypeCancel); // 取消
            EditText etModifyTypeName = contentView.findViewById(R.id.etModifyTypeName);
            etModifyTypeName.setText(name);
            EditText etModifyTypeDesc = contentView.findViewById(R.id.etModifyTypeDesc);
            etModifyTypeDesc.setText(desc);

            AlertDialog modifyTypeDL = new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setView(contentView)
                    .create(); // 先建好dialog


            btnModifyTypeSure.setOnClickListener(v -> { // 按下確認鍵
                String name1 = etModifyTypeName.getText().toString();
                String desc1 = etModifyTypeDesc.getText().toString();
                String id = arrayList.get(position).getId();
                // 修改分類
                NetworkController.instance().modifyProTypes(name1, desc1, id)
                        .onResponse(data -> {

                        })
                        .onFailure((errorCode, msg) -> {
                            Log.d("modifyProType", msg);
                        }).exec();

                HomeManagerActivity homeManagerActivity = (HomeManagerActivity)context;
                homeManagerActivity.network(); // 印出分類列表

                modifyTypeDL.dismiss(); // 關掉dialog
            });

            btnModifyTypeCancel.setOnClickListener(v -> modifyTypeDL.dismiss()); // 按下取消鍵

            holder.btnModify.setOnClickListener(v-> { // 按下修改商品分類鍵
                modifyTypeDL.show(); // 顯示dialog
            });
        } else { // 若是使用者
            holder.tvNameType.setText(arrayList.get(position).getName());
            holder.tvDescType.setText(arrayList.get(position).getDesc());
            holder.btnListPro.setOnClickListener(view -> { // 按下商品清單鍵
                Intent intent = new Intent(context,
                        ProductActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("PROTYPE", arrayList.get(position).getName());
                intent.putExtras(bundle); // 資料放intent才能帶過去

                context.startActivity(intent); // 隨intent帶到MainActivity
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
