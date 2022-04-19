package com.example.city_picker.models;

import javax.persistence.*;

@Entity
@Table(name = "AREA")
public class Area {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "HSN_CD")
    private String hsnCd;

    @Column(name = "TOWN_NM")
    private String townNm;

    @Column(name = "TOWN_CD", nullable = false)
    private String townCd;

    @Column(name = "ZIP_CD")
    private String zipCd;

    @Column(name = "ENG_HSN_NM")
    private String engHsnNm;


    public String getTownCd() {
        return townCd;
    }

    public void setTownCd(String townCd) {
        this.townCd = townCd;
    }

    public String getHsnCd() {
        return hsnCd;
    }

    public void setHsnCd(String hsnCd) {
        this.hsnCd = hsnCd;
    }

    public String getTownNm() {
        return townNm;
    }

    public void setTownNm(String townNm) {
        this.townNm = townNm;
    }

    public String getZipCd() {
        return zipCd;
    }

    public void setZipCd(String zipCd) {
        this.zipCd = zipCd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEngHsnNm() {
        return engHsnNm;
    }

    public void setEngHsnNm(String engHsnNm) {
        this.engHsnNm = engHsnNm;
    }
}
