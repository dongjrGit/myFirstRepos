package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisActivityOrge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_ACTIVITY_ORG")
public class TBasisActivityOrg implements java.io.Serializable {

	// Fields

	private String id;
	private String activityId;
	private String orgId;

	// Constructors

	/** default constructor */
	public TBasisActivityOrg() {
	}

	/** full constructor */
	public TBasisActivityOrg(String activityId, String orgId) {
		this.activityId = activityId;
		this.orgId = orgId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ACTIVITY_ID", nullable = false, length = 32)
	public String getActivityId() {
		return this.activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Column(name = "ORG_ID", nullable = false, length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}