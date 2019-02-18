package com.yinlian.wssc.web.po;

import java.util.Date;

public class Shop {
    private Integer id;

    private Integer userid;

    private String  name;

    private String  shopnum;

    private String  shopaddress;

    private String  companyname;

    private String  supporttel;

    private String  imgurl;

    private Integer status;

    private Boolean isowned;

    private Boolean isflagship;

    private Boolean isdel;

    private Date    deltime;

    private Integer deluserid;

    private Integer maxEmployee;

    private Integer maxRole;

    private Float   margin;

    private Integer shoplevelid;

    private Integer settlementtype;

    private Float   workingcost;

    private Float   royaltyrate;

    private String  reason;

    private String  description;

    private Date    creattime;

    private Boolean isjck;

    private Boolean isfree;

    private Boolean issupport;

    private String  shopcircle;

    private Boolean isep;

    private String  hodername;

    private String  bankcardno;

    private String  bankname;

    private String  lineno;

    private String  banktype;

    private Boolean issyncup;

    private Integer classid;

    private Integer accounttype;

    private String  discount;

    private Integer popularity;

    private Float   consumption;

    private Integer circleid;

    private String  username;

    private String  longitude;

    private String  latitude;
    
    private String distances;
    
    private Integer shoptype;
    
    private String     contactname;

    private String     contactmobile;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShopnum() {
        return shopnum;
    }

    public void setShopnum(String shopnum) {
        this.shopnum = shopnum == null ? null : shopnum.trim();
    }

    public String getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(String shopaddress) {
        this.shopaddress = shopaddress == null ? null : shopaddress.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    public String getSupporttel() {
        return supporttel;
    }

    public void setSupporttel(String supporttel) {
        this.supporttel = supporttel == null ? null : supporttel.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsowned() {
        return isowned;
    }

    public void setIsowned(Boolean isowned) {
        this.isowned = isowned;
    }

    public Boolean getIsflagship() {
        return isflagship;
    }

    public void setIsflagship(Boolean isflagship) {
        this.isflagship = isflagship;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Date getDeltime() {
        return deltime;
    }

    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    public Integer getDeluserid() {
        return deluserid;
    }

    public void setDeluserid(Integer deluserid) {
        this.deluserid = deluserid;
    }

    public Integer getMaxEmployee() {
        return maxEmployee;
    }

    public void setMaxEmployee(Integer maxEmployee) {
        this.maxEmployee = maxEmployee;
    }

    public Integer getMaxRole() {
        return maxRole;
    }

    public void setMaxRole(Integer maxRole) {
        this.maxRole = maxRole;
    }

    public Float getMargin() {
        return margin;
    }

    public void setMargin(Float margin) {
        this.margin = margin;
    }

    public Integer getShoplevelid() {
        return shoplevelid;
    }

    public void setShoplevelid(Integer shoplevelid) {
        this.shoplevelid = shoplevelid;
    }

    public Integer getSettlementtype() {
        return settlementtype;
    }

    public void setSettlementtype(Integer settlementtype) {
        this.settlementtype = settlementtype;
    }

    public Float getWorkingcost() {
        return workingcost;
    }

    public void setWorkingcost(Float workingcost) {
        this.workingcost = workingcost;
    }

    public Float getRoyaltyrate() {
        return royaltyrate;
    }

    public void setRoyaltyrate(Float royaltyrate) {
        this.royaltyrate = royaltyrate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Boolean getIsjck() {
        return isjck;
    }

    public void setIsjck(Boolean isjck) {
        this.isjck = isjck;
    }

    public Boolean getIsfree() {
        return isfree;
    }

    public void setIsfree(Boolean isfree) {
        this.isfree = isfree;
    }

    public Boolean getIssupport() {
        return issupport;
    }

    public void setIssupport(Boolean issupport) {
        this.issupport = issupport;
    }

    public String getShopcircle() {
        return shopcircle;
    }

    public void setShopcircle(String shopcircle) {
        this.shopcircle = shopcircle == null ? null : shopcircle.trim();
    }

    public Boolean getIsep() {
        return isep;
    }

    public void setIsep(Boolean isep) {
        this.isep = isep;
    }

    public String getHodername() {
        return hodername;
    }

    public void setHodername(String hodername) {
        this.hodername = hodername == null ? null : hodername.trim();
    }

    public String getBankcardno() {
        return bankcardno;
    }

    public void setBankcardno(String bankcardno) {
        this.bankcardno = bankcardno == null ? null : bankcardno.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getLineno() {
        return lineno;
    }

    public void setLineno(String lineno) {
        this.lineno = lineno == null ? null : lineno.trim();
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype == null ? null : banktype.trim();
    }

    public Boolean getIssyncup() {
        return issyncup;
    }

    public void setIssyncup(Boolean issyncup) {
        this.issyncup = issyncup;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Integer accounttype) {
        this.accounttype = accounttype;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount == null ? null : discount.trim();
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Float getConsumption() {
        return consumption;
    }

    public void setConsumption(Float consumption) {
        this.consumption = consumption;
    }

    public Integer getCircleid() {
        return circleid;
    }

    public void setCircleid(Integer circleid) {
        this.circleid = circleid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

	public String getDistances() {
		return distances;
	}

	public void setDistances(String distances) {
		this.distances = distances;
	}

	public Integer getShoptype() {
		return shoptype;
	}

	public void setShoptype(Integer shoptype) {
		this.shoptype = shoptype;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactmobile() {
		return contactmobile;
	}

	public void setContactmobile(String contactmobile) {
		this.contactmobile = contactmobile;
	}

}