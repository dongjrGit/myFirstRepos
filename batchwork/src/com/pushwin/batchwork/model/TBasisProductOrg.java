package com.pushwin.batchwork.model;

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
 * TBasisProductOrg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_PRODUCT_ORG")
public class TBasisProductOrg implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisOrg TBasisOrg;
	private TBasisProduct TBasisProduct;

	// Constructors

	/** default constructor */
	public TBasisProductOrg() {
	}

	/** full constructor */
	public TBasisProductOrg(TBasisOrg TBasisOrg, TBasisProduct TBasisProduct) {
		this.TBasisOrg = TBasisOrg;
		this.TBasisProduct = TBasisProduct;
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
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public TBasisProduct getTBasisProduct() {
		return this.TBasisProduct;
	}

	public void setTBasisProduct(TBasisProduct TBasisProduct) {
		this.TBasisProduct = TBasisProduct;
	}

}