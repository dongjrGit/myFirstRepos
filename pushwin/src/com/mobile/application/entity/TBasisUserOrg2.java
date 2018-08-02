package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisUserOrg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_USER_ORG")
public class TBasisUserOrg2 implements java.io.Serializable {

	// Fields

	private String id;
	private String orgId;
	private String userId;

	// Constructors

	/** default constructor */
	public TBasisUserOrg2() {
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
	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}