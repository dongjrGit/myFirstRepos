package com.yinlian.wssc.web.po;

public class Attrvalues {
    private Integer id;

    private String value;

    private Integer attrid;

    private String guid;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getAttrid() {
        return attrid;
    }

    public void setAttrid(Integer attrid) {
        this.attrid = attrid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}