package com.weAreFleas;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.weAreFleas.adapter.UserListAdapter;
import com.weAreFleas.dobuy.R;
import com.weAreFleas.models.User;
import com.weAreFleas.utils.NetworkController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {

    private EditText etSearchUser;
    private Button btnSearchUser;
    private Button btnAllUsers;

    private RecyclerView rvUser;
    private UserListAdapter adapter;
    private ArrayList<User> users;
    private String search = ""; // 初始化空字串
    private String token = ""; // 同上

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_user);

        etSearchUser = findViewById(R.id.etSearchUser);
        btnSearchUser = findViewById(R.id.btnSearchUser);
        btnAllUsers = findViewById(R.id.btnAllUsers);

        rvUser = findViewById(R.id.rvUser);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this);
        rvUser.setLayoutManager(layoutManager);

        users = new ArrayList<>();
        adapter = new UserListAdapter(users, token);
        rvUser.setAdapter(adapter);

        netWork();

        btnSearchUser.setOnClickListener(v -> {
            search = etSearchUser.getText().toString();
            netWork();
        });

        btnAllUsers.setOnClickListener(v -> {
            etSearchUser.setText("");
            search = "";
            netWork();
        });
    }

    public void netWork() {
        NetworkController.instance().getAllUsers()
                .onFailure((errorCode, msg) -> {
                    Log.d("getAllUsers", msg);
                }).onResponse(data -> {
            JSONArray jsonArray = data.getJSONArray("users");
            users.clear();
            if(search.equals("")) {
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    User user = new User(
                            jsonObject.getString("name"),
                            jsonObject.getString("phone"),
                            jsonObject.getString("address")
                    );
                    users.add(user);
                }
            } else { // 有輸入關鍵字的話，
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if(jsonObject.getString("name").contains(search)) {
                        User user = new User(
                                jsonObject.getString("name"),
                                jsonObject.getString("phone"),
                                jsonObject.getString("address")
                        );
                        users.add(user);
                    }
                }
            }
            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
            });
        }).exec();
    }
}
