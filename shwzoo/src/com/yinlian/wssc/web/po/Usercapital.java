package com.yinlian.wssc.web.po;

import java.text.DecimalFormat;

public class Usercapital {
    private Integer id;

    private Integer userid;

    private Double balance;

    private Double freezemoney;

    private Integer status;

    private Double bond;
    
    private String balances;
    private String freezemoneys;
   

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

    public Double getFreezemoney() {
        return freezemoney;
    }

    public void setFreezemoney(Double freezemoney) {
        this.freezemoney = freezemoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getBond() {
        return bond;
    }

    public void setBond(Double bond) {
        this.bond = bond;
    }

	public String getBalances() {
		 DecimalFormat df = new DecimalFormat("0.00"); 
		return df.format(balance);
	}

	public void setBalances(String balances) {
		this.balances = balances;
	}

	public String getFreezemoneys() {
		 DecimalFormat df = new DecimalFormat("0.00"); 
		 
		return df.format(freezemoney);
	}

	public void setFreezemoneys(String freezemoneys) {
		this.freezemoneys = freezemoneys;
	}
}