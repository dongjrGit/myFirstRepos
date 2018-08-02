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
 * TBasisUserOrg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_USER_DATA")
public class TBasisUserOrg implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisOrg TBasisOrg;
	private TBasisUser TBasisUser;

	// Constructors

	/** default constructor */
	public TBasisUserOrg() {
	}

	/** full constructor */
	public TBasisUserOrg(TBasisOrg TBasisOrg, TBasisUser TBasisUser) {
		this.TBasisOrg = TBasisOrg;
		this.TBasisUser = TBasisUser;
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
	@JoinColumn(name = "ORG_ID", nullable = false)
	public TBasisOrg getTBasisOrg() {
		return this.TBasisOrg;
	}

	public void setTBasisOrg(TBasisOrg TBasisOrg) {
		this.TBasisOrg = TBasisOrg;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	public TBasisUser getTBasisUser() {
		return this.TBasisUser;
	}

	public void setTBasisUser(TBasisUser TBasisUser) {
		this.TBasisUser = TBasisUser;
	}
}