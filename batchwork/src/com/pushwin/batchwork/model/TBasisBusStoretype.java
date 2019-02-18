package com.pushwin.batchwork.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TBasisBusStoretype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_BUS_STORETYPE")
public class TBasisBusStoretype implements java.io.Serializable {

	// Fields

	private TBasisBusStoretypeId id;

	// Constructors

	/** default constructor */
	public TBasisBusStoretype() {
	}

	/** full constructor */
	public TBasisBusStoretype(TBasisBusStoretypeId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "storeTypeNumber", column = @Column(name = "STORE_TYPE_NUMBER", nullable = false, length = 6)),
			@AttributeOverride(name = "storeTypeName", column = @Column(name = "STORE_TYPE_NAME", nullable = false, length = 20)),
			@AttributeOverride(name = "updateTime", column = @Column(name = "UPDATE_TIME", nullable = false, length = 20)),
			@AttributeOverride(name = "companymemo1", column = @Column(name = "COMPANYMEMO1", length = 25)),
			@AttributeOverride(name = "companymemo2", column = @Column(name = "COMPANYMEMO2", length = 25)),
			@AttributeOverride(name = "companymemo3", column = @Column(name = "COMPANYMEMO3", length = 25)),
			@AttributeOverride(name = "companymemo4", column = @Column(name = "COMPANYMEMO4", length = 25)),
			@AttributeOverride(name = "companymemo5", column = @Column(name = "COMPANYMEMO5", length = 25)) })
	public TBasisBusStoretypeId getId() {
		return this.id;
	}

	public void setId(TBasisBusStoretypeId id) {
		this.id = id;
	}

}