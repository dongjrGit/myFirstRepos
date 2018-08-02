package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisCredit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_CREDIT")
public class TBasisCredit implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String idNo;
	private String homePhone;
	private String status;
	private String updateTime;
	private String creditKind;

	private TBasisUser TBasisUser;
	// Constructors

	/** default constructor */
	public TBasisCredit() {
	}

	/** full constructor */
	public TBasisCredit(String name, String idNo, String homePhone, String status, String updateTime) {
		this.name = name;
		this.idNo = idNo;
		this.homePhone = homePhone;
		this.status = status;
		this.updateTime = updateTime;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 120)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ID_NO", length = 20)
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "HOME_PHONE", length = 20)
	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "UPDATE_TIME", length = 20)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public TBasisUser getTBasisUser() {
		return this.TBasisUser;
	}

	public void setTBasisUser(TBasisUser TBasisUser) {
		this.TBasisUser = TBasisUser;
	}
	
	@Column(name = "CREDIT_KIND", length = 20)
	public String getCreditKind() {
		return creditKind;
	}

	public void setCreditKind(String creditKind) {
		this.creditKind = creditKind;
	}
}