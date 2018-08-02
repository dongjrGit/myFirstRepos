package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasisBusStores entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_BUS_RISKSTORES")
public class TBasisBusRiskStores implements java.io.Serializable {

	// Fields

	private String store_number;
	private String store_name;
	private String account_name;
	private String account;
	private String status;
	private String beizhu1;
	private String beizhu2;
	private String beizhu3;

	// Constructors

	/** default constructor */
	public TBasisBusRiskStores() {
	}


	/** full constructor */
	public TBasisBusRiskStores(String store_number, String store_name,
			String account_name, String account, String status, String beizhu1,
			String beizhu2, String beizhu3) {
		super();
		this.store_number = store_number;
		this.store_name = store_name;
		this.account_name = account_name;
		this.account = account;
		this.status = status;
		this.beizhu1 = beizhu1;
		this.beizhu2 = beizhu2;
		this.beizhu3 = beizhu3;
	}

	@Id
	@Column(name = "STORE_NUMBER", unique = true, nullable = false, length = 64)
	public String getStore_number() {
		return store_number;
	}


	public void setStore_number(String store_number) {
		this.store_number = store_number;
	}

	

	@Column(name = "STORE_NAME", length = 64)
	public String getStore_name() {
		return store_name;
	}


	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}


	@Column(name = "ACCOUNT_NAME", length = 64)
	public String getAccount_name() {
		return account_name;
	}


	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}


	@Column(name = "ACCOUNT", length = 64)
	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	@Column(name = "BEIZHU1", length = 64)
	public String getBeizhu1() {
		return this.beizhu1;
	}

	public void setBeizhu1(String beizhu1) {
		this.beizhu1 = beizhu1;
	}

	@Column(name = "BEIZHU2", length = 64)
	public String getBeizhu2() {
		return this.beizhu2;
	}

	public void setBeizhu2(String beizhu2) {
		this.beizhu2 = beizhu2;
	}

	@Column(name = "BEIZHU3", length = 64)
	public String getBeizhu3() {
		return this.beizhu3;
	}

	public void setBeizhu3(String beizhu3) {
		this.beizhu3 = beizhu3;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}