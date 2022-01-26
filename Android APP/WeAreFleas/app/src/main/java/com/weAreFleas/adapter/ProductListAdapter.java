package com.weAreFleas.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.ProductActivityM;
import com.weAreFleas.utils.NetworkController;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.BuyCar;
import com.weAreFleas.models.Product;
import com.weAreFleas.utils.Global;

import java.util.ArrayList;

import static com.weAreFleas.utils.Global.buyCars;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    static class ProductViewHolder extends RecyclerView.ViewHolder{
        private int userType; // 使用者類型
        private TextView tvType;
        private TextView tvNamePro;
        private TextView tvPrice;
        private TextView tvDescPro;
        private Button btnAddCar;
        private Button btnModifyPro;
        private Button btnDelete;

        public ProductViewHolder(@NonNull View itemView, int userType) {
            super(itemView);

            this.userType = userType;
            if(this.userType == 1) { // 管理者
                tvType = itemView.findViewById(R.id.tvTypeM);
                tvNamePro = itemView.findViewById(R.id.tvNameProM);
                tvPrice = itemView.findViewById(R.id.tvPriceM);
                tvDescPro = itemView.findViewById(R.id.tvDescProM);
                btnModifyPro = itemView.findViewById(R.id.btnModifyPro);
                btnDelete = itemView.findViewById(R.id.btnDeletePro);
            } else{ // 客戶
                tvType = itemView.findViewById(R.id.tvType);
                tvNamePro = itemView.findViewById(R.id.tvNamePro);
                tvPrice = itemView.findViewById(R.id.tvPrice);
                tvDescPro = itemView.findViewById(R.id.tvDescPro);
                btnAddCar = itemView.findViewById(R.id.btnAddCar);
            }
        }
    }

    private ArrayList<Product> arrayList;
    private Context context;

    public ProductListAdapter(ArrayList<Product> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if(Global.userType == 1) { // 管理者
            view = inflater.inflate(R.layout.item_list_product_m, parent, false);
        } else { // 使用者
            view = inflater.inflate(R.layout.item_list_product, parent, false);
        }
        return new ProductViewHolder(view, Global.userType);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductViewHolder holder, int position) {
        holder.tvType.setText(arrayList.get(position).getProType());
        holder.tvNamePro.setText(arrayList.get(position).getName());
        holder.tvPrice.setText(String.valueOf(arrayList.get(position).getPrice()));
        holder.tvDescPro.setText(arrayList.get(position).getDesc());
        if(Global.userType == 1) { // 若是管理者
            // 修改小視窗
            View contentView = LayoutInflater.from(context)
                    .inflate(R.layout.dialog_modify_product, null);
            Button btnModifyProdSure = contentView.findViewById(R.id.btnModifyProdSure);
            Button btnModifyProdCancel = contentView.findViewById(R.id.btnModifyProdCancel);
            EditText etModifyProType = contentView.findViewById(R.id.etModifyProType);
            EditText etModifyProName = contentView.findViewById(R.id.etModifyProName);
            EditText etModifyProDesc = contentView.findViewById(R.id.etModifyProDesc);
            EditText etModifyProPrice = contentView.findViewById(R.id.etModifyProPrice);

            AlertDialog modifyProDL = new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setView(contentView)
                    .create();

            btnModifyProdSure.setOnClickListener(v -> { // 按下確認
                String id = arrayList.get(position).getProductId();
                String proType = etModifyProType.getText().toString();
                String name = etModifyProName.getText().toString();
                String desc = etModifyProDesc.getText().toString();
                int price = Integer.parseInt(etModifyProPrice.getText().toString());

                NetworkController.instance().modifyProducts(id, proType, name, desc, price)
                        .onFailure((errorCode, msg) -> {
                            Log.d("修改商品", msg);
                        }).onResponse(data -> {

                }).exec();

                ProductActivityM productActivityM = (ProductActivityM)context;
                productActivityM.network(); // 印出商品列表
                modifyProDL.dismiss(); // 關掉dialog
            });

            btnModifyProdCancel.setOnClickListener(v -> modifyProDL.dismiss()); // 按取消

            holder.btnModifyPro.setOnClickListener(v -> { // 按下修改
                modifyProDL.show(); // 顯示dialog
            });
            holder.btnDelete.setOnClickListener(v -> { // 刪除
                String name = arrayList.get(position).getName();
                NetworkController.instance().deleteProduct(name)
                        .onFailure((errorCode, msg) -> {
                            Log.d("刪除商品失敗", msg);
                        }).onResponse(data -> {
                            Log.d("刪除商品成功", name);
                }).exec();
                ProductActivityM productActivityM = (ProductActivityM)context;
                productActivityM.network(); // 印出商品列表
            });
        } else { // 如果是使用者
            View contentView = LayoutInflater.from(context)
                    .inflate(R.layout.dialog_buy_amount, null);
            EditText etBuyAmount = contentView.findViewById(R.id.etBuyAmount);
            Button btnSureAmount = contentView.findViewById(R.id.btnSureAmount);
            Button btnCancelAmount = contentView.findViewById(R.id.btnCancelAmount);

            AlertDialog buyAmountAD = new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setView(contentView)
                    .create();

            btnSureAmount.setOnClickListener(v -> { // 按下確定
                if(etBuyAmount.getText().toString().equals("")) { // 擋空字串
                    return;
                }
                String name = arrayList.get(position).getName();
                String proId = arrayList.get(position).getProductId();
                int amount = Integer.parseInt(etBuyAmount.getText().toString());
                int totalPrice = arrayList.get(position).getPrice() * amount;
                BuyCar buyCar = new BuyCar(name, proId, amount, totalPrice);
                buyCars.add(buyCar);

                buyAmountAD.dismiss();
            });

            btnCancelAmount.setOnClickListener(v -> buyAmountAD.dismiss());

            holder.btnAddCar.setOnClickListener(v -> { // 放入購物車
                buyAmountAD.show();
            });
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
