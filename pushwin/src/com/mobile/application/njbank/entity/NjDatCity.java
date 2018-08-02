package com.mobile.application.njbank.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NjDatCity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NJ_DAT_CITY", schema = "PUSHWIN02")
public class NjDatCity implements java.io.Serializable {

	// Fields

	private String id;
	private Timestamp updatetime;
	private String name;
	private String provinceid;

	// Constructors

	/** default constructor */
	public NjDatCity() {
	}

	/** full constructor */
	public NjDatCity(String id, Timestamp updatetime, String name,
			String provinceid) {
		this.id = id;
		this.updatetime = updatetime;
		this.name = name;
		this.provinceid = provinceid;
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

	@Column(name = "PROVINCEID", nullable = false, length = 128)
	public String getProvinceid() {
		return this.provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

}