package com.example.city_picker.models;

import javax.persistence.*;

@Entity
@Table(name = "VILLAGE")
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VILL_CD", nullable = false)
    private String villCd;

    @Column(name = "HSN_CD")
    private String hsnCd;

    @Column(name = "TOWN_CD")
    private String townCd;

    @Column(name = "VILL_NM")
    private String villNm;

    @Column(name = "ENG_HSN_NM")
    private String engHsnNm;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVillCd() {
        return villCd;
    }

    public void setVillCd(String villCd) {
        this.villCd = villCd;
    }

    public String getHsnCd() {
        return hsnCd;
    }

    public void setHsnCd(String hsnCd) {
        this.hsnCd = hsnCd;
    }

    public String getTownCd() {
        return townCd;
    }

    public void setTownCd(String townCd) {
        this.townCd = townCd;
    }

    public String getVillNm() {
        return villNm;
    }

    public void setVillNm(String villNm) {
        this.villNm = villNm;
    }

    public String getEngHsnNm() {
        return engHsnNm;
    }

    public void setEngHsnNm(String engHsnNm) {
        this.engHsnNm = engHsnNm;
    }
}
