package com.weAreFleas.utils;

import android.util.Log;

import com.weAreFleas.models.BuyCar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class  NetworkController {

    private static NetworkController networkController; // 單例
    private static final String URL_ROOT = "http://34.80.156.172:3000/api";

    public static final MediaType JSON  // 貼在這邊
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    private NetworkController() {} // 建構子

    public static NetworkController instance() { // 單例
        if(networkController == null) {
            networkController = new NetworkController();
        }
        return networkController;
    }

    public interface OnFailureInterface {
        void action(int errorCode, String msg);
    }
    public interface OnResponseInterface {
        void action(JSONObject data) throws JSONException;
    }
    public interface OnCompleteInterface {
        void action();
    }

    public static class CallbackAdapter implements Callback {

        private OnFailureInterface onFailureInterface;
        private OnResponseInterface onResponseInterface;
        private OnCompleteInterface onCompleteInterface;

        public CallbackAdapter(OnFailureInterface onFailureInterface,
                               OnResponseInterface onResponseInterface,
                               OnCompleteInterface onCompleteInterface) {
            this.onFailureInterface = onFailureInterface;
            this.onResponseInterface = onResponseInterface;
            this.onCompleteInterface = onCompleteInterface;
        }

        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            if(onFailureInterface != null) { // 失敗時
                onFailureInterface.action(500, e.getMessage());
            }
            if(onCompleteInterface != null) {
                onCompleteInterface.action();
            }
        }

        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            String res = response.body().string();
            try {
                JSONObject jsonObject = new JSONObject(res);
                int errorCode = jsonObject.getInt("errorCode");
                System.out.println(res); // 有到這
                if(errorCode != 200) {
                    if(onFailureInterface != null) {
                        onFailureInterface.action(errorCode, jsonObject
                        .getJSONObject("data")
                        .getString("msg"));
                    }
                } else {
                    if(onResponseInterface != null) {
                        onResponseInterface.action(jsonObject.getJSONObject("data"));
                    }
                }
            } catch (JSONException e) { // 沒到這
                if(onFailureInterface != null) {
                    onFailureInterface.action(500, "JSON Format Error:" + e.getMessage());
                }
                System.out.println("catch");
            } finally {
                if(onCompleteInterface != null) {
                    onCompleteInterface.action();
                }
                System.out.println("finally");
            }
        }
    }

    public class CCall {
        private Request request;
        private OnFailureInterface onFailureInterface;
        private OnResponseInterface onResponseInterface;
        private OnCompleteInterface onCompleteInterface;

        private CCall(Request request) {
            this.request = request;
        }

        public CCall onFailure(OnFailureInterface onFailureInterface) {
            this.onFailureInterface = onFailureInterface;
            System.out.println("net-onFai");
            return this;
        }
        public CCall onResponse(OnResponseInterface onResponseInterface) {
            this.onResponseInterface = onResponseInterface;
            System.out.println("net-onRes"); // 有跑到這
            return this;
        }
        public CCall onComplete(OnCompleteInterface onCompleteInterface) {
            this.onCompleteInterface = onCompleteInterface;
            return this;
        }

        public Call exec() {
            Call call = client.newCall(request);
            call.enqueue(new CallbackAdapter(onFailureInterface,
                    onResponseInterface, onCompleteInterface));
            System.out.println("net-exec");
            return call;
        }
    }

    public CCall login(String account, String password) {
        RequestBody requestBody = new FormBody.Builder()
                .add("account", account)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/user/login")
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall register(String account, String password, // 註冊(新增使用者)
                          String name, String phone, String address) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("account", account)
                    .put("password", password)
                    .put("name", name)
                    .put("phone", phone)
                    .put("address", address)
                    .put("type", 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(
                jsonObject.toString(), JSON
        );
        Request request = new Request.Builder()
                .url(URL_ROOT + "/user/register")
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall getAllProTypes() {
        Request request = new Request.Builder()
                .url(URL_ROOT + "/proType/getAll")
                .get()
                .build();
        return new CCall(request);
    }

    public CCall getAllProTypesId() {
        Request request = new Request.Builder()
                .url(URL_ROOT + "/proType/getAllId")
                .header("x-access-token", Global.token)
                .get()
                .build();
        Log.d("getAllId", Global.token);
        return new CCall(request);
    }

    public CCall addProTypes(String name, String desc) { // 新增分類
        RequestBody requestBody = new FormBody.Builder()
                .add("name", name)
                .add("desc", desc)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/proType/add")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall deleteProType(String name) { // 刪除分類
        RequestBody requestBody = new FormBody.Builder()
                .add("name", name)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/proType/remove")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall modifyProTypes(String name, String desc, String id) { // 修改分類
        RequestBody requestBody = new FormBody.Builder()
                .add("_id", id)
                .add("name", name)
                .add("desc", desc)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/proType/modify")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall getAllUsers() { // 取得客戶清單
        Request request = new Request.Builder()
                .url(URL_ROOT + "/user/getAll")
                .header("x-access-token", Global.token)
                .get()
                .build();
        return new CCall(request);
    }

    public CCall getAllUserAccs() { // 取得所有使用者帳號
        Request request = new Request.Builder()
                .url(URL_ROOT + "/user/getAllAcc")
                .get()
                .build();
        return new CCall(request);
    }

    public CCall getUser() { // 取得使用者自己資料
        Request request = new Request.Builder()
                .url(URL_ROOT + "/user/get")
                .header("x-access-token", Global.token)
                .get()
                .build();
        return new CCall(request);
    }

    public CCall modifyUser(String name, String phone, String address) {
        RequestBody requestBody = new FormBody.Builder()
                .add("name", name)
                .add("phone", phone)
                .add("address", address)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/user/modify")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall getAllProducts() { // 取得所有商品
        Request request = new Request.Builder()
                .url(URL_ROOT + "/product/getAll")
                .get()
                .build();
        return new CCall(request);
    }

    public CCall addProducts(String proType, String name,
                             String desc, int price) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("proType", proType)
                    .put("name", name)
                    .put("desc", desc)
                    .put("price", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), JSON);
        Request request = new Request.Builder()
                .url(URL_ROOT + "/product/add")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall getByProType(String proType) {
        RequestBody requestBody = new FormBody.Builder()
                .add("proType", proType)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/product/getByProType")
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall modifyProducts(String id, String proType, String name, String desc, int price) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject
                    .put("_id", id)
                    .put("proType", proType)
                    .put("name", name)
                    .put("desc", desc)
                    .put("price", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), JSON);
        Request request = new Request.Builder()
                .url(URL_ROOT + "/product/modify")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall deleteProduct(String name) { // 刪除商品
        RequestBody requestBody = new FormBody.Builder()
                .add("name", name)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/product/remove")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall addOrders(String userId, String status, ArrayList<BuyCar> buyCars) {
        JSONArray buyCarsJ = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            for(int i = 0; i < buyCars.size(); i++) {
                JSONObject buyCarJ = new JSONObject();
                buyCarJ.put("name", buyCars.get(i).getName())
                        .put("amount", buyCars.get(i).getAmount())
                        .put("price", buyCars.get(i).getTotalPrice());
                JSONObject buyCarJo = new JSONObject().put("buyCar", buyCarJ);
                //Log.d("掰咖", buyCarJ.toString());
                buyCarsJ.put(i, buyCarJo);
                //Log.d("掰咖陣列", buyCarsJ.toString());
            }
            jsonObject.put("user", userId)
                    .put("status", status)
                    .put("buyCars", buyCarsJ);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(
                jsonObject.toString(), JSON
        );
        Request request = new Request.Builder()
                .url(URL_ROOT + "/order/create")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall getBySerial(String serial) {
        RequestBody requestBody = new FormBody.Builder()
                .add("serial", serial)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/order/getBySerial")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall getByUserId(String userId) { // 因為不確定是管理者還是客戶查
        RequestBody requestBody = new FormBody.Builder()
                .add("user", userId)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/order/getByUserId")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall getAllOrders() {
        Request request = new Request.Builder()
                .url(URL_ROOT + "/order/getAll")
                .header("x-access-token", Global.token)
                .get()
                .build();
        return new CCall(request);
    }

    public CCall cancelOrder(String serial) {
        RequestBody requestBody = new FormBody.Builder()
                .add("serial", serial)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/order/cancel")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }

    public CCall modifyStatus(String serial, String status) {
        RequestBody requestBody = new FormBody.Builder()
                .add("serial", serial)
                .add("status", status)
                .build();
        Request request = new Request.Builder()
                .url(URL_ROOT + "/order/modifyStatus")
                .header("x-access-token", Global.token)
                .post(requestBody)
                .build();
        return new CCall(request);
    }
}
