package com.yinlian.wssc.web.po;

public class Specsvalues {
    private Integer id;

    private String value;

    private Integer specsid;

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

    public Integer getSpecsid() {
        return specsid;
    }

    public void setSpecsid(Integer specsid) {
        this.specsid = specsid;
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