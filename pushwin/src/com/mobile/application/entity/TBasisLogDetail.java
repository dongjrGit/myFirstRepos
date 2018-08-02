package com.mobile.application.entity;

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
 * TBasisLogDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_LOG_DETAIL")
public class TBasisLogDetail implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisLogType TBasisLogType;
	private String description;
	private String userCode;
	private String userName;
	private String userIp;
	private String orgCode;
	private String orgName;
	private String updateTime;

	// Constructors

	/** default constructor */
	public TBasisLogDetail() {
	}

	/** minimal constructor */
	public TBasisLogDetail(TBasisLogType TBasisLogType) {
		this.TBasisLogType = TBasisLogType;
	}

	/** full constructor */
	public TBasisLogDetail(TBasisLogType TBasisLogType, String description,
			String userCode, String userName, String userIp, String orgCode,
			String orgName, String updateTime) {
		this.TBasisLogType = TBasisLogType;
		this.description = description;
		this.userCode = userCode;
		this.userName = userName;
		this.userIp = userIp;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.updateTime = updateTime;
	}
	
	
	public TBasisLogDetail(String id,String description,String userCode, String userName, String userIp,String updateTime) {
		this.id = id;
		this.description = description;
		this.userCode = userCode;
		this.userName = userName;
		this.userIp = userIp;
		this.updateTime = updateTime;
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
	@JoinColumn(name = "URL", nullable = false)
	public TBasisLogType getTBasisLogType() {
		return this.TBasisLogType;
	}

	public void setTBasisLogType(TBasisLogType TBasisLogType) {
		this.TBasisLogType = TBasisLogType;
	}

	@Column(name = "DESCRIPTION", length = 120)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "USER_CODE", length = 20)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "USER_NAME", length = 120)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_IP", length = 32)
	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@Column(name = "ORG_CODE", length = 20)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_NAME", length = 120)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "UPDATE_TIME", length = 20)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}