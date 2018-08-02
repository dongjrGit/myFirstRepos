package com.mobile.application.entity;

import java.util.Date;
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
 * TDealManager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LOG_MODULE_OPERATION")
public class LogModuleOperation implements java.io.Serializable {

	// Fields

	private String id;
	private String moduleId;
	private String operateCode;
	private String operateType;
	private String description;
	private String operateIp;
	private TBasisUser operateUser;
	private TBasisOrg operateOrg;
	private Date operateTime;
	// Constructors

	/** default constructor */
	public LogModuleOperation() {
	}

	/** full constructor */
	public LogModuleOperation(String id, String moduleId, String operateCode, String operateType,
			String description, String operateIp, TBasisUser operateUser,
			TBasisOrg operateOrg, Date operateTime) {
		this.id = id;
		this.moduleId = moduleId;
		this.operateCode = operateCode;
		this.operateType = operateType;
		this.description = description;
		this.operateIp = operateIp;
		this.operateUser = operateUser;
		this.operateOrg = operateOrg;
		this.operateTime = operateTime;
	}

	
	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "MODULE_ID", length = 50)
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "OPERATE_CODE", length = 50)
	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}
	
	@Column(name = "OPERATE_TYPE", length = 50)
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	@Column(name = "DESCRIPTION", length = 50)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "OPERATE_IP", length = 50)
	public String getOperateIp() {
		return operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATE_USER")
	public TBasisUser getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(TBasisUser operateUser) {
		this.operateUser = operateUser;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATE_ORG")
	public TBasisOrg getOperateOrg() {
		return operateOrg;
	}

	public void setOperateOrg(TBasisOrg operateOrg) {
		this.operateOrg = operateOrg;
	}

	@Column(name = "OPERATE_TIME", length = 50)
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}


}