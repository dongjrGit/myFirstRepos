package com.yinlian.wssc.web.po;

public class OrderactivityChildHistory {
    private Integer id;

    private Integer skuid;

    private String skuname;

    private Integer couponid;

    private String ordercode;

    private String oahid;

    private Integer status;

    private Integer point;

    private Integer count;

    private Float skuprice;

    private Integer type;

    private Integer actid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSkuid() {
        return skuid;
    }

    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
    }

    public String getSkuname() {
        return skuname;
    }

    public void setSkuname(String skuname) {
        this.skuname = skuname == null ? null : skuname.trim();
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public String getOahid() {
        return oahid;
    }

    public void setOahid(String oahid) {
        this.oahid = oahid == null ? null : oahid.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getSkuprice() {
        return skuprice;
    }

    public void setSkuprice(Float skuprice) {
        this.skuprice = skuprice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getActid() {
        return actid;
    }

    public void setActid(Integer actid) {
        this.actid = actid;
    }
}