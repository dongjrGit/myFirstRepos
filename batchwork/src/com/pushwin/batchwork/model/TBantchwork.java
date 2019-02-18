package com.pushwin.batchwork.model;

import java.sql.Timestamp;
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
 * TBantchwork entity. @author MyEclipse Persistence Tools
 */
//@Entity
@Table(name = "T_BANTCHWORK", schema = "MOBILEBANK")
public class TBantchwork implements java.io.Serializable {

	// Fields

	private String bantchworkId;
	private String bantchworkName;
	private String bantchworkDesc;
	private String lastStartTm;
	private String lastEndTm;
	private String lastExerslt;
	private Long updateUsre;
	private Timestamp updateTm;
	private String taskTimes;
	private Set<HisBantchwork> hisBantchworks = new HashSet<HisBantchwork>(0);

	// Constructors

	/** default constructor */
	public TBantchwork() {
	}
	/** minimal constructor */
	public TBantchwork(String bantchworkId) {
		this.bantchworkId = bantchworkId;
	}

	/** full constructor */
	public TBantchwork(String bantchworkId, String bantchworkName,
			String bantchworkDesc, String lastStartTm, String lastEndTm,
			String lastExerslt, Long updateUsre, Timestamp updateTm,
			String taskTimes, Set<HisBantchwork> hisBantchworks) {
		this.bantchworkId = bantchworkId;
		this.bantchworkName = bantchworkName;
		this.bantchworkDesc = bantchworkDesc;
		this.lastStartTm = lastStartTm;
		this.lastEndTm = lastEndTm;
		this.lastExerslt = lastExerslt;
		this.updateUsre = updateUsre;
		this.updateTm = updateTm;
		this.taskTimes = taskTimes;
		this.hisBantchworks = hisBantchworks;
	}

	// Property accessors
	@Id
	@Column(name = "BANTCHWORK_ID", unique = true, nullable = false, length = 20)
	public String getBantchworkId() {
		return this.bantchworkId;
	}

	public void setBantchworkId(String bantchworkId) {
		this.bantchworkId = bantchworkId;
	}

	@Column(name = "BANTCHWORK_NAME", length = 64)
	public String getBantchworkName() {
		return this.bantchworkName;
	}

	public void setBantchworkName(String bantchworkName) {
		this.bantchworkName = bantchworkName;
	}

	@Column(name = "BANTCHWORK_DESC", length = 128)
	public String getBantchworkDesc() {
		return this.bantchworkDesc;
	}

	public void setBantchworkDesc(String bantchworkDesc) {
		this.bantchworkDesc = bantchworkDesc;
	}

	@Column(name = "LAST_START_TM", length = 23)
	public String getLastStartTm() {
		return this.lastStartTm;
	}

	public void setLastStartTm(String lastStartTm) {
		this.lastStartTm = lastStartTm;
	}

	@Column(name = "LAST_END_TM", length = 23)
	public String getLastEndTm() {
		return this.lastEndTm;
	}

	public void setLastEndTm(String lastEndTm) {
		this.lastEndTm = lastEndTm;
	}

	@Column(name = "LAST_EXERSLT", length = 8)
	public String getLastExerslt() {
		return this.lastExerslt;
	}

	public void setLastExerslt(String lastExerslt) {
		this.lastExerslt = lastExerslt;
	}

	@Column(name = "UPDATE_USRE", precision = 16, scale = 0)
	public Long getUpdateUsre() {
		return this.updateUsre;
	}

	public void setUpdateUsre(Long updateUsre) {
		this.updateUsre = updateUsre;
	}

	@Column(name = "UPDATE_TM", length = 11)
	public Timestamp getUpdateTm() {
		return this.updateTm;
	}

	public void setUpdateTm(Timestamp updateTm) {
		this.updateTm = updateTm;
	}

	@Column(name = "TASK_TIMES", length = 1024)
	public String getTaskTimes() {
		return this.taskTimes;
	}

	public void setTaskTimes(String taskTimes) {
		this.taskTimes = taskTimes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TBantchwork")
	public Set<HisBantchwork> getHisBantchworks() {
		return this.hisBantchworks;
	}

	public void setHisBantchworks(Set<HisBantchwork> hisBantchworks) {
		this.hisBantchworks = hisBantchworks;
	}

}