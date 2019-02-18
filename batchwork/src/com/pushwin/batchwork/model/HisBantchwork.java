package com.pushwin.batchwork.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * HisBantchwork entity. @author MyEclipse Persistence Tools
 */
//@Entity
@Table(name = "HIS_BANTCHWORK", schema = "MOBILEBANK")
public class HisBantchwork implements java.io.Serializable {

	// Fields

	private long id;
	private TBantchwork TBantchwork;
	private String taskID;
	private String taskName;
	private String startTm;
	private String endTm;
	private String exeRslt;
	private Long exeUsr;

	// Constructors

	/** default constructor */
	public HisBantchwork() {
	}
	/** minimal constructor */
	public HisBantchwork(TBantchwork TBantchwork) {
		this.TBantchwork = TBantchwork;
	}

	/** full constructor */
	public HisBantchwork(TBantchwork TBantchwork, String startTm, String endTm,
			String exeRslt, Long exeUsr) {
		this.TBantchwork = TBantchwork;
		this.startTm = startTm;
		this.endTm = endTm;
		this.exeRslt = exeRslt;
		this.exeUsr = exeUsr;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_HIS_BANTCHWORK")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BANTCHWORK_ID", nullable = false)
	public TBantchwork getTBantchwork() {
		return this.TBantchwork;
	}

	public void setTBantchwork(TBantchwork TBantchwork) {
		this.taskID = TBantchwork.getBantchworkId();
		this.taskName = TBantchwork.getBantchworkName();
		this.TBantchwork = TBantchwork;
	}

	@Column(name = "START_TM", length = 23)
	public String getStartTm() {
		return this.startTm;
	}

	public void setStartTm(String startTm) {
		this.startTm = startTm;
	}

	@Column(name = "END_TM", length = 23)
	public String getEndTm() {
		return this.endTm;
	}

	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}

	@Column(name = "EXE_RSLT", length = 8)
	public String getExeRslt() {
		return this.exeRslt;
	}

	public void setExeRslt(String exeRslt) {
		this.exeRslt = exeRslt;
	}

	@Column(name = "EXE_USR", precision = 16, scale = 0)
	public Long getExeUsr() {
		return this.exeUsr;
	}

	public void setExeUsr(Long exeUsr) {
		this.exeUsr = exeUsr;
	}
	/**
	 * @return the taskID
	 */
	@Transient
	public String getTaskID() {
		return this.taskID;
	}
	/**
	 * @param taskID the taskID to set
	 */
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	/**
	 * @return the taskName
	 */
	@Transient
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}