package com.weAreFleas.utils;

import com.weAreFleas.models.BuyCar;

import java.util.ArrayList;

public class Global {

    public static String token = "";
    public static String acc = "";
    public static String id = "";
    public static int userType = 2; // 預設為使用者權限
    public static ArrayList<BuyCar> buyCars = new ArrayList<>(); // 購物車陣列(結帳後清空)
}
