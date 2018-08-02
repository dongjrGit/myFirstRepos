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
 * TBasisRoleNotice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_ROLE_NOTICE")
public class TBasisRoleNotice implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisOrg TBasisOrg;
	private TBasisRole TBasisRole;
	private TBasisNotice TBasisNotice;

	// Constructors

	/** default constructor */
	public TBasisRoleNotice() {
	}

	/** full constructor */
	public TBasisRoleNotice(TBasisOrg TBasisOrg, TBasisRole TBasisRole,
			TBasisNotice TBasisNotice) {
		this.TBasisOrg = TBasisOrg;
		this.TBasisRole = TBasisRole;
		this.TBasisNotice = TBasisNotice;
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
	@JoinColumn(name = "ORG_ID")
	public TBasisOrg getTBasisOrg() {
		return this.TBasisOrg;
	}

	public void setTBasisOrg(TBasisOrg TBasisOrg) {
		this.TBasisOrg = TBasisOrg;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	public TBasisRole getTBasisRole() {
		return this.TBasisRole;
	}

	public void setTBasisRole(TBasisRole TBasisRole) {
		this.TBasisRole = TBasisRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NOTICE_ID")
	public TBasisNotice getTBasisNotice() {
		return this.TBasisNotice;
	}

	public void setTBasisNotice(TBasisNotice TBasisNotice) {
		this.TBasisNotice = TBasisNotice;
	}

}