package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Userfinance {
    private Integer id;

    private Integer userid;

    private Double   balance;

    private Double   money;

    private Integer type;

    private String  number;

    private Date    createtime;

    private Integer status;

    private String  paynum;

    private String  userip;

    private String  description;
    
    private Integer financetype;

    private String  creattimestr;

    private Integer paytype;
    
	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public Integer getFinancetype() {
		return financetype;
	}

	public void setFinancetype(Integer financetype) {
		this.financetype = financetype;
	}

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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPaynum() {
        return paynum;
    }

    public void setPaynum(String paynum) {
        this.paynum = paynum == null ? null : paynum.trim();
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip == null ? null : userip.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * Getter method for property <tt>creattimestr</tt>.
     * 
     * @return property value of creattimestr
     */
    public String getCreattimestr() {
        this.creattimestr = DateUtil.dateConvert(createtime);
        return creattimestr;
    }

    /**
     * Setter method for property <tt>creattimestr</tt>.
     * 
     * @param creattimestr value to be assigned to property creattimestr
     */
    public void setCreattimestr(String creattimestr) {
        this.creattimestr = creattimestr;
    }

}