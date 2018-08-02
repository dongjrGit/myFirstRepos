package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasisBusUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_VERSION")
public class TBasisVersion implements java.io.Serializable {

	// Fields

	private String id;
	private String version;
	private String bank;
	private String time;
	private String upFlag;
	private String beizhu1;
	private String beizhu2;
	private String beizhu3;

	// Constructors

	/** default constructor */
	public TBasisVersion() {
	}

	/** minimal constructor */
	public TBasisVersion(String id) {
		this.id = id;
	}

	/** full constructor */
	public TBasisVersion(String id, String version, String bank,
			String time, String upFlag,
			String beizhu1, String beizhu2, String beizhu3) {
		this.id = id;
		this.version = version;
		this.bank = bank;
		this.time = time;
		this.upFlag = upFlag;
		this.beizhu1 = beizhu1;
		this.beizhu2 = beizhu2;
		this.beizhu3 = beizhu3;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 4)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "VERSION", nullable = false,length = 10)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Column(name = "BANK", nullable = false,length = 10)
	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "TIME", length = 8)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "UPFLAG", nullable = false,length = 1)
	public String getUpFlag() {
		return this.upFlag;
	}

	public void setUpFlag(String upFlag) {
		this.upFlag = upFlag;
	}

	@Column(name = "BEIZHU1", length = 16)
	public String getBeizhu1() {
		return this.beizhu1;
	}

	public void setBeizhu1(String beizhu1) {
		this.beizhu1 = beizhu1;
	}
	
	@Column(name = "BEIZHU2", length = 16)
	public String getBeizhu2() {
		return this.beizhu2;
	}

	public void setBeizhu2(String beizhu2) {
		this.beizhu2 = beizhu2;
	}
	
	@Column(name = "BEIZHU3", length = 32)
	public String getBeizhu3() {
		return this.beizhu3;
	}

	public void setBeizhu3(String beizhu3) {
		this.beizhu3 = beizhu3;
	}

	

}