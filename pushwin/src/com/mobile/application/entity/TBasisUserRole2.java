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
 * TBasisUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_USER_ROLE")
public class TBasisUserRole2 implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String roleId;

	// Constructors

	/** default constructor */
	public TBasisUserRole2() {
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
	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ROLE_ID", length = 32)
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}