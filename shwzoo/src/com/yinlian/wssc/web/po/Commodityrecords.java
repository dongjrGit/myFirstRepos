package com.yinlian.wssc.web.po;

public class Commodityrecords {
    private Integer id;

    private String relationid;

    private Integer spuid;

    private Integer shopid;

    private String shopname;

    private String skuname;

    private Integer skuid;

    private String spuname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelationid() {
        return relationid;
    }

    public void setRelationid(String relationid) {
        this.relationid = relationid == null ? null : relationid.trim();
    }

    public Integer getSpuid() {
        return spuid;
    }

    public void setSpuid(Integer spuid) {
        this.spuid = spuid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname == null ? null : shopname.trim();
    }

    public String getSkuname() {
        return skuname;
    }

    public void setSkuname(String skuname) {
        this.skuname = skuname == null ? null : skuname.trim();
    }

    public Integer getSkuid() {
        return skuid;
    }

    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
    }

    public String getSpuname() {
        return spuname;
    }

    public void setSpuname(String spuname) {
        this.spuname = spuname == null ? null : spuname.trim();
    }
}