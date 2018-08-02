package com.mobile.application.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Paduser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PADUSER")
public class Paduser implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private String bankid;
	private String company;
	private String organization;
	private String userid;
	private String password;
	private String cnname;
	private String mobile;
	private String caid;
	private String rolename;
	private String areacode;
	private String companycode;
	private String orgcode;
	private String bankcode;
	private String updatetime;
	private BigDecimal status;
	private String model;
	private String pin;
	private String sim;
	private BigDecimal taskcount;
	private BigDecimal finishcount;
	private String finishpercent;
	private String savetime;

	// Constructors

	/** default constructor */
	public Paduser() {
	}

	/** minimal constructor */
	public Paduser(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public Paduser(BigDecimal id, String bankid, String company,
			String organization, String userid, String password,
			String cnname, String mobile, String caid, String rolename,
			String areacode, String companycode, String orgcode,
			String bankcode, String updatetime, BigDecimal status,
			String model, String pin, String sim, BigDecimal taskcount,
			BigDecimal finishcount, String finishpercent, String savetime) {
		this.id = id;
		this.bankid = bankid;
		this.company = company;
		this.organization = organization;
		this.userid = userid;
		this.password = password;
		this.cnname = cnname;
		this.mobile = mobile;
		this.caid = caid;
		this.rolename = rolename;
		this.areacode = areacode;
		this.companycode = companycode;
		this.orgcode = orgcode;
		this.bankcode = bankcode;
		this.updatetime = updatetime;
		this.status = status;
		this.model = model;
		this.pin = pin;
		this.sim = sim;
		this.taskcount = taskcount;
		this.finishcount = finishcount;
		this.finishpercent = finishpercent;
		this.savetime = savetime;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	//@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "BANKID")
	public String getBankid() {
		return this.bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	//@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "COMPANYID")
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	//@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "ORGID")
	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(name = "USERID", length = 20)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "PASSWORD", length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "CNNAME", length = 20)
	public String getCnname() {
		return this.cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	@Column(name = "MOBILE", length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "CAID", length = 20)
	public String getCaid() {
		return this.caid;
	}

	public void setCaid(String caid) {
		this.caid = caid;
	}

	@Column(name = "ROLENAME", length = 40)
	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name = "AREACODE", length = 20)
	public String getAreacode() {
		return this.areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	@Column(name = "COMPANYCODE", length = 20)
	public String getCompanycode() {
		return this.companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	@Column(name = "ORGCODE", length = 20)
	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	@Column(name = "BANKCODE", length = 20)
	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	@Column(name = "UPDATETIME", length = 20)
	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "STATUS", precision = 22, scale = 0)
	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	@Column(name = "MODEL", length = 30)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "PIN", length = 50)
	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Column(name = "SIM", length = 50)
	public String getSim() {
		return this.sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	@Column(name = "TASKCOUNT", precision = 22, scale = 0)
	public BigDecimal getTaskcount() {
		return this.taskcount;
	}

	public void setTaskcount(BigDecimal taskcount) {
		this.taskcount = taskcount;
	}

	@Column(name = "FINISHCOUNT", precision = 22, scale = 0)
	public BigDecimal getFinishcount() {
		return this.finishcount;
	}

	public void setFinishcount(BigDecimal finishcount) {
		this.finishcount = finishcount;
	}

	@Column(name = "FINISHPERCENT", length = 20)
	public String getFinishpercent() {
		return this.finishpercent;
	}

	public void setFinishpercent(String finishpercent) {
		this.finishpercent = finishpercent;
	}

	@Column(name = "SAVETIME", length = 30)
	public String getSavetime() {
		return this.savetime;
	}

	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}

}