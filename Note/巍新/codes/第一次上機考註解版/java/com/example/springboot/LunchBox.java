package com.example.springboot;

import javax.persistence.*;

@Entity  // 告訴spring boot這是一個資料庫的類別
@Table(name = "LunchBoxs")  // name對應到資料表的名稱
public class LunchBox {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 告訴此Column的生成方式 ,如果設定成`GenerationType.AUTO`讓容器來自動產生
    private long id;
    @Column(name = "NAME") // name = 對應到Table欄位中的欄位名稱
    private String name;
    @Column(name = "PRICE")
    private int price;
    @Column(name = "DES")
    private String des;
    @Column(name = "AMOUNT")
    private int amount;

    public LunchBox(){}

    public LunchBox(String name, int price, String des, int amount) {
        this.name = name;
        this.price = price;
        this.des = des;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
