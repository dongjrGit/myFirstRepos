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
 * TbEmmOrgRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_ORG_ROLE")
public class TBasisOrgRole implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private String id;
	private TBasisRole tBasisRole;
	private TBasisOrg tBasisOrg;

	// Constructors

	/** default constructor */
	public TBasisOrgRole() {
	}

	/** full constructor */
	public TBasisOrgRole(TBasisRole tBasisRole, TBasisOrg tBasisOrg) {
		this.tBasisRole = tBasisRole;
		this.tBasisOrg = tBasisOrg;
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
	@JoinColumn(name = "ROLE_ID")
	public TBasisRole gettBasisRole() {
		return tBasisRole;
	}

	public void settBasisRole(TBasisRole tBasisRole) {
		this.tBasisRole = tBasisRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	public TBasisOrg gettBasisOrg() {
		return tBasisOrg;
	}

	public void settBasisOrg(TBasisOrg tBasisOrg) {
		this.tBasisOrg = tBasisOrg;
	}

}
