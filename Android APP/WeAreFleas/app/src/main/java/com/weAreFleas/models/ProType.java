package com.weAreFleas.models;

public class ProType {
    private String name;
    private String desc;
    private String id;

    public ProType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public ProType(String name, String desc, String id) {
        this.name = name;
        this.desc = desc;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
