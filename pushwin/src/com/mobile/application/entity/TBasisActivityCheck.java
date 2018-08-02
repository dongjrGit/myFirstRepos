package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisActivityCheck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_ACTIVITY_CHECK")
public class TBasisActivityCheck implements java.io.Serializable {

	// Fields

	private String id;
	private String activityId;
	private String opinion;
	private String checkTime;
	private String checkUser;
	private String status;
	

	// Constructors


	/** default constructor */
	public TBasisActivityCheck() {
	}

	/** minimal constructor */
	public TBasisActivityCheck(String activityId) {
		this.activityId = activityId;
	}

	/** full constructor */
	public TBasisActivityCheck(String activityId, String opinion,
			String checkTime, String checkUser) {
		this.activityId = activityId;
		this.opinion = opinion;
		this.checkTime = checkTime;
		this.checkUser = checkUser;
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

	
	@Column(name = "STATUS", nullable = false, length = 25)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "ACTIVITY_ID", nullable = false, length = 32)
	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Column(name = "OPINION", length = 600)
	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(name = "CHECK_TIME", length = 32)
	public String getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "CHECK_USER", length = 32)
	public String getCheckUser() {
		return this.checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

}