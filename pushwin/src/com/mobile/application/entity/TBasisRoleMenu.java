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
 * TBasisRoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_ROLE_MENU")
public class TBasisRoleMenu implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisMenu TBasisMenu;
	private TBasisRole TBasisRole;

	// Constructors

	/** default constructor */
	public TBasisRoleMenu() {
	}

	/** full constructor */
	public TBasisRoleMenu(TBasisMenu TBasisMenu, TBasisRole TBasisRole) {
		this.TBasisMenu = TBasisMenu;
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
	@JoinColumn(name = "MENU_ID")
	public TBasisMenu getTBasisMenu() {
		return this.TBasisMenu;
	}

	public void setTBasisMenu(TBasisMenu TBasisMenu) {
		this.TBasisMenu = TBasisMenu;
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