package com.pushwin.batchwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasisBusSubbank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_BUS_SUBBANK")
public class TBasisBusSubbank implements java.io.Serializable {

	// Fields

	private String orgCode;
	private String orgName;
	private String orgId;
	
	private String updaTime ;
	
	

	// Constructors
	@Column(name = "UPDATIME", length = 32)
	public String getUpdaTime() {
		return updaTime;
	}

	public void setUpdaTime(String upddTime) {
		this.updaTime = upddTime;
	}

	/** default constructor */
	public TBasisBusSubbank() {
	}

	/** minimal constructor */
	public TBasisBusSubbank(String orgCode) {
		this.orgCode = orgCode;
	}

	/** full constructor */
	public TBasisBusSubbank(String orgCode, String orgName, String orgId) {
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgId = orgId;
	}

	// Property accessors
	@Id
	@Column(name = "ORG_CODE", unique = true, nullable = false, length = 50)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_NAME", length = 128)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}