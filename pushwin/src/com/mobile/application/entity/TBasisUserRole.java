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
public class TBasisUserRole implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisUser TBasisUser;
	private TBasisRole TBasisRole;

	// Constructors

	/** default constructor */
	public TBasisUserRole() {
	}

	/** full constructor */
	public TBasisUserRole(TBasisUser TBasisUser, TBasisRole TBasisRole) {
		this.TBasisUser = TBasisUser;
		this.TBasisRole = TBasisRole;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	public TBasisUser getTBasisUser() {
		return this.TBasisUser;
	}

	public void setTBasisUser(TBasisUser TBasisUser) {
		this.TBasisUser = TBasisUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	public TBasisRole getTBasisRole() {
		return this.TBasisRole;
	}

	public void setTBasisRole(TBasisRole TBasisRole) {
		this.TBasisRole = TBasisRole;
	}
}