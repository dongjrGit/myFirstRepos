package com.mobile.application.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMPANY")
public class Company implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private String name;
	private String code;
	private Set<Paduser> padusers = new HashSet<Paduser>(0);

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(BigDecimal id) {
		this.id = id;
	}

	/** full constructor */
	public Company(BigDecimal id, String name, String code,
			Set<Paduser> padusers) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.padusers = padusers;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CODE", length = 30)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Paduser> getPadusers() {
		return this.padusers;
	}

	public void setPadusers(Set<Paduser> padusers) {
		this.padusers = padusers;
	}

}