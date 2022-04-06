package com.example.shortcut;

import javax.persistence.*;

@Entity
public class Shortcut {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // 長網址
    // @Column
    private String longUrl;

    // 短網址
    private String shortUrl;

    public Shortcut(){}

    public Shortcut(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        System.out.println("資料內: " + shortUrl);
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
