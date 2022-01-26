package com.weAreFleas.models;

public class BuyCar {
    private String name;
    private String proId;
    private int amount;
    private int totalPrice;

    public BuyCar(String name, String proId, int amount, int totalPrice) {
        this.name = name;
        this.proId = proId;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public BuyCar(String name, int amount, int totalPrice) {
        this.name = name;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
