package com.weAreFleas.models;

import java.util.ArrayList;

public class Order {
    private String serial;
    private String userId;
    private String status;
    private ArrayList<String> products;
    private ArrayList<Integer> amounts;
    private ArrayList<Integer> prices;
    private int totalPrice;

    public Order(String serial, String userId, String status, ArrayList<String> products,
                 ArrayList<Integer> amounts, ArrayList<Integer> prices, int totalPrice) {
        this.serial = serial;
        this.userId = userId;
        this.status = status;
        this.products = products;
        this.amounts = amounts;
        this.prices = prices;
        this.totalPrice = totalPrice;
    }

    public Order(String serial, String userId, int totalPrice, String status) {
        this.serial = serial;
        this.userId = userId;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public ArrayList<Integer> getAmounts() {
        return amounts;
    }

    public void setAmounts(ArrayList<Integer> amounts) {
        this.amounts = amounts;
    }

    public ArrayList<Integer> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Integer> prices) {
        this.prices = prices;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
