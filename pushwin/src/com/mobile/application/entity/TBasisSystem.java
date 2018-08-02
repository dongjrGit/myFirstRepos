package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisSystem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_SYSTEM")
public class TBasisSystem implements java.io.Serializable {

	// Fields

	private String id;
	private String logoTxt;
	private String logoImg;
	private String bankName;

	// Constructors

	/** default constructor */
	public TBasisSystem() {
	}

	/** full constructor */
	public TBasisSystem(String id, String logoTxt, String logoImg,
			String bankName) {
		super();
		this.id = id;
		this.logoTxt = logoTxt;
		this.logoImg = logoImg;
		this.bankName = bankName;
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

	@Column(name = "LOGO_TXT", length = 60)
	public String getLogoTxt() {
		return this.logoTxt;
	}

	public void setLogoTxt(String logoTxt) {
		this.logoTxt = logoTxt;
	}

	@Column(name = "LOGO_IMG", length = 64)
	public String getLogoImg() {
		return this.logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	@Column(name = "BANK_NAME", length = 128)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
}