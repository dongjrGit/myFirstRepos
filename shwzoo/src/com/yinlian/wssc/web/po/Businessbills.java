/*
 * @(#) Businessbills.java 2016年8月29日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.po;

import java.math.BigDecimal;
import java.util.Date;

public class Businessbills {
	 private Integer id;

	    private Integer shopid;

	    private String shopname;

	    private Date begindate;

	    private Date enddate;

	    private BigDecimal turnover;

	    private BigDecimal commission;

	    private BigDecimal settlement;

	    private Integer status;

	    private String remittype;

	    private String bankusername;

	    private String banknum;

	    private String bankname;

	    private Date transferdate;

	    private BigDecimal transfermoney;

	    private String transfername;

	    private String remark;

	    private String operator;

	    private Integer operatorid;

	    private BigDecimal discountmoney;

	    private BigDecimal freight;

	    private BigDecimal voucher;

	    private BigDecimal actualpay;

	    private Integer datatype;

	    private Integer settlementtype;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
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

	    public Date getBegindate() {
	        return begindate;
	    }

	    public void setBegindate(Date begindate) {
	        this.begindate = begindate;
	    }

	    public Date getEnddate() {
	        return enddate;
	    }

	    public void setEnddate(Date enddate) {
	        this.enddate = enddate;
	    }

	    public BigDecimal getTurnover() {
	        return turnover;
	    }

	    public void setTurnover(BigDecimal turnover) {
	        this.turnover = turnover;
	    }

	    public BigDecimal getCommission() {
	        return commission;
	    }

	    public void setCommission(BigDecimal commission) {
	        this.commission = commission;
	    }

	    public BigDecimal getSettlement() {
	        return settlement;
	    }

	    public void setSettlement(BigDecimal settlement) {
	        this.settlement = settlement;
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }

	    public String getRemittype() {
	        return remittype;
	    }

	    public void setRemittype(String remittype) {
	        this.remittype = remittype == null ? null : remittype.trim();
	    }

	    public String getBankusername() {
	        return bankusername;
	    }

	    public void setBankusername(String bankusername) {
	        this.bankusername = bankusername == null ? null : bankusername.trim();
	    }

	    public String getBanknum() {
	        return banknum;
	    }

	    public void setBanknum(String banknum) {
	        this.banknum = banknum == null ? null : banknum.trim();
	    }

	    public String getBankname() {
	        return bankname;
	    }

	    public void setBankname(String bankname) {
	        this.bankname = bankname == null ? null : bankname.trim();
	    }

	    public Date getTransferdate() {
	        return transferdate;
	    }

	    public void setTransferdate(Date transferdate) {
	        this.transferdate = transferdate;
	    }

	    public BigDecimal getTransfermoney() {
	        return transfermoney;
	    }

	    public void setTransfermoney(BigDecimal transfermoney) {
	        this.transfermoney = transfermoney;
	    }

	    public String getTransfername() {
	        return transfername;
	    }

	    public void setTransfername(String transfername) {
	        this.transfername = transfername == null ? null : transfername.trim();
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark == null ? null : remark.trim();
	    }

	    public String getOperator() {
	        return operator;
	    }

	    public void setOperator(String operator) {
	        this.operator = operator == null ? null : operator.trim();
	    }

	    public Integer getOperatorid() {
	        return operatorid;
	    }

	    public void setOperatorid(Integer operatorid) {
	        this.operatorid = operatorid;
	    }

	    public BigDecimal getDiscountmoney() {
	        return discountmoney;
	    }

	    public void setDiscountmoney(BigDecimal discountmoney) {
	        this.discountmoney = discountmoney;
	    }

	    public BigDecimal getFreight() {
	        return freight;
	    }

	    public void setFreight(BigDecimal freight) {
	        this.freight = freight;
	    }

	    public BigDecimal getVoucher() {
	        return voucher;
	    }

	    public void setVoucher(BigDecimal voucher) {
	        this.voucher = voucher;
	    }

	    public BigDecimal getActualpay() {
	        return actualpay;
	    }

	    public void setActualpay(BigDecimal actualpay) {
	        this.actualpay = actualpay;
	    }

	    public Integer getDatatype() {
	        return datatype;
	    }

	    public void setDatatype(Integer datatype) {
	        this.datatype = datatype;
	    }

	    public Integer getSettlementtype() {
	        return settlementtype;
	    }

	    public void setSettlementtype(Integer settlementtype) {
	        this.settlementtype = settlementtype;
	    }
}
