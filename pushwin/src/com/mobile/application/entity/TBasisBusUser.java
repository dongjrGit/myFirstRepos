package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasisBusUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_BUS_USER")
public class TBasisBusUser implements java.io.Serializable {

	// Fields

	private String userCode;
	private String userName;
	private String userPhone;
	private String orgId;
	private String ca;
	private String userCity;
	private String userArea;
	private String userbeizhu1;
	private String userbeizhu2;
	private String userbeizhu3;

	// Constructors

	/** default constructor */
	public TBasisBusUser() {
	}

	/** minimal constructor */
	public TBasisBusUser(String userCode) {
		this.userCode = userCode;
	}

	/** full constructor */
	public TBasisBusUser(String userCode, String userName, String userPhone,
			String orgId, String ca, String userCity, String userArea,
			String userbeizhu1, String userbeizhu2, String userbeizhu3) {
		this.userCode = userCode;
		this.userName = userName;
		this.userPhone = userPhone;
		this.orgId = orgId;
		this.ca = ca;
		this.userCity = userCity;
		this.userArea = userArea;
		this.userbeizhu1 = userbeizhu1;
		this.userbeizhu2 = userbeizhu2;
		this.userbeizhu3 = userbeizhu3;
	}

	// Property accessors
	@Id
	@Column(name = "USER_CODE", unique = true, nullable = false, length = 32)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "USER_NAME", length = 32)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_PHONE", length = 20)
	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "CA", length = 64)
	public String getCa() {
		return this.ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}

	@Column(name = "USER_CITY", length = 64)
	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	@Column(name = "USER_AREA", length = 64)
	public String getUserArea() {
		return this.userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	@Column(name = "USERBEIZHU1", length = 64)
	public String getUserbeizhu1() {
		return this.userbeizhu1;
	}

	public void setUserbeizhu1(String userbeizhu1) {
		this.userbeizhu1 = userbeizhu1;
	}

	@Column(name = "USERBEIZHU2", length = 64)
	public String getUserbeizhu2() {
		return this.userbeizhu2;
	}

	public void setUserbeizhu2(String userbeizhu2) {
		this.userbeizhu2 = userbeizhu2;
	}

	@Column(name = "USERBEIZHU3", length = 64)
	public String getUserbeizhu3() {
		return this.userbeizhu3;
	}

	public void setUserbeizhu3(String userbeizhu3) {
		this.userbeizhu3 = userbeizhu3;
	}

}