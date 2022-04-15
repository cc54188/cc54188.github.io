package com.example.shareq.models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "ShareQ")
public class ShareQ {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "idno")
    private String idno;

    @Column(name = "shortUrl")
    private String shortUrl;

    @Column(name = "longUrl")
    private String longUrl;

    @Column(name = "shareCount")
    private int shareCount;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }
}
