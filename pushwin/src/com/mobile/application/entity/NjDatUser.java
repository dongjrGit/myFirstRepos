package com.mobile.application.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NjDatUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NJ_DAT_USER")
public class NjDatUser implements java.io.Serializable {

	// Fields

	private String id;
	private Timestamp updatetime;
	private String area;
	private String bankid;
	private String canumber;
	private String company;
	private String mobile;
	private String name;
	private String orgnization;
	private String role;

	// Constructors

	/** default constructor */
	public NjDatUser() {
	}

	/** minimal constructor */
	public NjDatUser(String id, Timestamp updatetime, String area,
			String bankid, String company, String mobile, String name,
			String orgnization, String role) {
		this.id = id;
		this.updatetime = updatetime;
		this.area = area;
		this.bankid = bankid;
		this.company = company;
		this.mobile = mobile;
		this.name = name;
		this.orgnization = orgnization;
		this.role = role;
	}

	/** full constructor */
	public NjDatUser(String id, Timestamp updatetime, String area,
			String bankid, String canumber, String company, String mobile,
			String name, String orgnization, String role) {
		this.id = id;
		this.updatetime = updatetime;
		this.area = area;
		this.bankid = bankid;
		this.canumber = canumber;
		this.company = company;
		this.mobile = mobile;
		this.name = name;
		this.orgnization = orgnization;
		this.role = role;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 128)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "UPDATETIME", nullable = false, length = 11)
	public Timestamp getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "AREA", nullable = false, length = 6)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "BANKID", nullable = false, length = 20)
	public String getBankid() {
		return this.bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	@Column(name = "CANUMBER", length = 20)
	public String getCanumber() {
		return this.canumber;
	}

	public void setCanumber(String canumber) {
		this.canumber = canumber;
	}

	@Column(name = "COMPANY", nullable = false, length = 20)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "MOBILE", nullable = false, length = 40)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "NAME", nullable = false, length = 80)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ORGNIZATION", nullable = false, length = 8)
	public String getOrgnization() {
		return this.orgnization;
	}

	public void setOrgnization(String orgnization) {
		this.orgnization = orgnization;
	}

	@Column(name = "ROLE", nullable = false, length = 80)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}