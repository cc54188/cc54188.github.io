package com.weAreFleas.models;

public class Product {
    private String proType;
    private String name;
    private String desc;
    private int price;
    private String productId;

    public Product(String productId, String proType, String name, String desc, int price) {
        this.productId = productId;
        this.proType = proType;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String id) {
        this.productId = id;
    }
}
