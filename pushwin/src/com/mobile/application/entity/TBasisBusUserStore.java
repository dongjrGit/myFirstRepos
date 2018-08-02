package com.mobile.application.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TBasisBusUserStore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_BUS_USER_STORE")
public class TBasisBusUserStore implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2332775785218617157L;
	private TBasisBusUserStoreId id;
	private String orgcode;
	private String updatetime;

	private String usercode;
	private String storenumber;
	private String beizhu1;
	
	// Constructors

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getStorenumber() {
		return storenumber;
	}

	public void setStorenumber(String storenumber) {
		this.storenumber = storenumber;
	}

	/** default constructor */
	public TBasisBusUserStore() {
	}

	/** minimal constructor */
	public TBasisBusUserStore(TBasisBusUserStoreId id) {
		this.id = id;
	}

	/** full constructor */
	public TBasisBusUserStore(TBasisBusUserStoreId id, String orgcode,
			String updatetime) {
		this.id = id;
		this.orgcode = orgcode;
		this.updatetime = updatetime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "usercode", column = @Column(name = "USERCODE", nullable = false, length = 32)),
			@AttributeOverride(name = "storenumber", column = @Column(name = "STORENUMBER", nullable = false, length = 128)) })
	public TBasisBusUserStoreId getId() {
		return this.id;
	}

	public void setId(TBasisBusUserStoreId id) {
		this.id = id;
	}

	@Column(name = "BEIZHU1", length = 32)
	public String getBeizhu1() {
		return this.beizhu1;
	}

	public void setBeizhu1(String orgcode) {
		this.beizhu1 = orgcode;
	}
	
	@Column(name = "ORGCODE", length = 32)
	public String getOrgcode() {
		return this.orgcode;
	}
	
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	@Column(name = "UPDATETIME", length = 32)
	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

}