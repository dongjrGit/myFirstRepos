package com.mobile.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * author by chenji
 * time      20170210
 */
@Entity
@Table(name = "T_BASIS_BUS_STORE_SPECIALCODE")
public class TbasisBusStoreSpecialCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String storeNumber;
	private String specialCode;
	private String orgCode;
	private String updateTime;
	
	/** default constructor */
	public TbasisBusStoreSpecialCode() {
		// TODO Auto-generated constructor stub
	}

	/** full constructor */
	public TbasisBusStoreSpecialCode(String storeNumber, String specialCode,
			String orgCode,String updateTime) {
		super();
		this.storeNumber = storeNumber;
		this.specialCode = specialCode;
		this.orgCode = orgCode;
		this.updateTime=updateTime;
	}
    @Id             
	@Column(name = "STORE_NUMBER", nullable = false, length = 9)
	public String getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(String storeNumber) {
		this.storeNumber = storeNumber;
	}
    
	@Id     
	@Column(name = "SPECIAL_CODE", nullable = false, length = 9)
	public String getSpecialCode() {
		return specialCode;
	}

	public void setSpecialCode(String specialCode) {
		this.specialCode = specialCode;
	}

	@Column(name = "ORG_CODE", length = 3)
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Column(name = "UPDATETIME", length = 32)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


}
