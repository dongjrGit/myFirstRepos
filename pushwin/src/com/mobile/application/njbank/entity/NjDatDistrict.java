package com.mobile.application.njbank.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NjDatDistrict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NJ_DAT_DISTRICT", schema = "PUSHWIN02")
public class NjDatDistrict implements java.io.Serializable {

	// Fields

	private String id;
	private Timestamp updatetime;
	private String name;
	private String parentid;

	// Constructors

	/** default constructor */
	public NjDatDistrict() {
	}

	/** full constructor */
	public NjDatDistrict(String id, Timestamp updatetime, String name,
			String parentid) {
		this.id = id;
		this.updatetime = updatetime;
		this.name = name;
		this.parentid = parentid;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 128)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "UPDATETIME", nullable = false, length = 11)
	public Timestamp getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "NAME", nullable = false, length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARENTID", nullable = false, length = 128)
	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}