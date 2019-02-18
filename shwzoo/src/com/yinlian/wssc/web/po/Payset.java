package com.yinlian.wssc.web.po;

public class Payset {
    private Integer id;

    private String  payname;

    private String  moneyname;

    private Float   poundage;

    private Boolean ispersent;

    private Boolean isonline;

    private Integer interfacetype;

    private Integer display;

    private String  discription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname == null ? null : payname.trim();
    }

    public String getMoneyname() {
        return moneyname;
    }

    public void setMoneyname(String moneyname) {
        this.moneyname = moneyname == null ? null : moneyname.trim();
    }

    public Float getPoundage() {
        return poundage;
    }

    public void setPoundage(Float poundage) {
        this.poundage = poundage;
    }

    public Boolean getIspersent() {
        return ispersent;
    }

    public void setIspersent(Boolean ispersent) {
        this.ispersent = ispersent;
    }

    public Boolean getIsonline() {
        return isonline;
    }

    public void setIsonline(Boolean isonline) {
        this.isonline = isonline;
    }

    public Integer getInterfacetype() {
        return interfacetype;
    }

    public void setInterfacetype(Integer interfacetype) {
        this.interfacetype = interfacetype;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription == null ? null : discription.trim();
    }
}