package com.example.city_picker.models;

import javax.persistence.*;

@Entity
@Table(name = "CITY")
public class City {

    @Id
    @Column(name = "HSN_CD", nullable = false)
    private String hsnCd;

    @Column(name = "HSN_NM")
    private String hsnNm;

    @Column(name = "ENG_HSN_NM")
    private String engHsnNm;

    public String getHsnCd() {
        return hsnCd;
    }

    public void setHsnCd(String hsnCd) {
        this.hsnCd = hsnCd;
    }

    public String getHsnNm() {
        return hsnNm;
    }

    public void setHsnNm(String hsnNm) {
        this.hsnNm = hsnNm;
    }

    public String getEngHsnNm() {
        return engHsnNm;
    }

    public void setEngHsnNm(String engHsnNm) {
        this.engHsnNm = engHsnNm;
    }
}
