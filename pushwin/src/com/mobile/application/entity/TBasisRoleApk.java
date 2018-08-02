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
@Table(name = "T_BASIS_ROLE_APK")
public class TBasisRoleApk implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisApk TBasisApk;
	private TBasisRole TBasisRole;

	// Constructors

	/** default constructor */
	public TBasisRoleApk() {
	}

	/** full constructor */
	public TBasisRoleApk(TBasisApk TBasisApk, TBasisRole TBasisRole) {
		this.TBasisApk = TBasisApk;
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
	@JoinColumn(name = "APK_ID")
	public TBasisApk getTBasisApk() {
		return this.TBasisApk;
	}

	public void setTBasisApk(TBasisApk TBasisApk) {
		this.TBasisApk = TBasisApk;
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