package com.example.springboot;

public class Product {
    private String name;
    private int price;
    private String hint;

    public Product(){}

    public Product(String name, int price, String hint) {
        this.name = name;
        this.price = price;
        this.hint = hint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
