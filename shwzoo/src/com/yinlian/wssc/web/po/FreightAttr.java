package com.yinlian.wssc.web.po;

public class FreightAttr {
    private Integer id;

    private Integer freightid;

    private String areas;

    private Integer firstcount;

    private Float firstprice;

    private Integer elsecount;

    private Float elseprice;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFreightid() {
        return freightid;
    }

    public void setFreightid(Integer freightid) {
        this.freightid = freightid;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas == null ? null : areas.trim();
    }

    public Integer getFirstcount() {
        return firstcount;
    }

    public void setFirstcount(Integer firstcount) {
        this.firstcount = firstcount;
    }

    public Float getFirstprice() {
        return firstprice;
    }

    public void setFirstprice(Float firstprice) {
        this.firstprice = firstprice;
    }

    public Integer getElsecount() {
        return elsecount;
    }

    public void setElsecount(Integer elsecount) {
        this.elsecount = elsecount;
    }

    public Float getElseprice() {
        return elseprice;
    }

    public void setElseprice(Float elseprice) {
        this.elseprice = elseprice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}