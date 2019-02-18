package com.pushwin.batchwork.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * THisBantchwork entity. @author MyEclipse Persistence Tools
 */
//@Entity
@Table(name = "T_HIS_BANTCHWORK", schema = "MOBILEBANK")
public class THisBantchwork implements java.io.Serializable {

	// Fields

	private Long id;
	private TBantchwork TBantchwork;
	private Date startTm;
	private Date endTm;
	private Integer exeRslt;
	private Long exeUsr;

	// Constructors

	/** default constructor */
	public THisBantchwork() {
	}

	/** full constructor */
	public THisBantchwork(TBantchwork TBantchwork, Date startTm,
			Date endTm, Integer exeRslt, Long exeUsr) {
		this.TBantchwork = TBantchwork;
		this.startTm = startTm;
		this.endTm = endTm;
		this.exeRslt = exeRslt;
		this.exeUsr = exeUsr;
	}

	// Property accessors
	@SequenceGenerator(name = "generator")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 16, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANTCHWORK_ID")
	public TBantchwork getTBantchwork() {
		return this.TBantchwork;
	}

	public void setTBantchwork(TBantchwork TBantchwork) {
		this.TBantchwork = TBantchwork;
	}

	@Column(name = "START_TM")
	public Date getStartTm() {
		return this.startTm;
	}

	public void setStartTm(Date startTm) {
		this.startTm = startTm;
	}

	@Column(name = "END_TM")
	public Date getEndTm() {
		return this.endTm;
	}

	public void setEndTm(Date endTm) {
		this.endTm = endTm;
	}

	@Column(name = "EXE_RSLT", precision = 8, scale = 0)
	public Integer getExeRslt() {
		return this.exeRslt;
	}

	public void setExeRslt(Integer exeRslt) {
		this.exeRslt = exeRslt;
	}

	@Column(name = "EXE_USR", precision = 16, scale = 0)
	public Long getExeUsr() {
		return this.exeUsr;
	}

	public void setExeUsr(Long exeUsr) {
		this.exeUsr = exeUsr;
	}

}