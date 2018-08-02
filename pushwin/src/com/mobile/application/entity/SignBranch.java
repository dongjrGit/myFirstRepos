package com.mobile.application.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SignBranch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SIGN_BRANCH")
public class SignBranch implements java.io.Serializable {

	// Fields

	private String dbId;
	private String orgCode;
	private String branchType;
	private String branchDistrict;
	private String branchValue;
	private String branchName;
	private String subBranchValue;
	private String subBranchName;
	private String address;
	private String telephone;
	private String updateUser;
	private Date createDate;
	private Date updateDate;

	// Constructors

	/** default constructor */
	public SignBranch() {
	}

	/** minimal constructor */
	public SignBranch(String dbId) {
		this.dbId = dbId;
	}

	/** full constructor */
	public SignBranch(String dbId, String orgCode, String branchType,
			String branchDistrict, String branchValue, String branchName,
			String subBranchValue, String subBranchName, String address,
			String telephone, String updateUser, Date createDate,
			Date updateDate) {
		this.dbId = dbId;
		this.orgCode = orgCode;
		this.branchType = branchType;
		this.branchDistrict = branchDistrict;
		this.branchValue = branchValue;
		this.branchName = branchName;
		this.subBranchValue = subBranchValue;
		this.subBranchName = subBranchName;
		this.address = address;
		this.telephone = telephone;
		this.updateUser = updateUser;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	// Property accessors
	@Id
	@Column(name = "DB_ID", unique = true, nullable = false, length = 6)
	public String getDbId() {
		return this.dbId;
	}

	public void setDbId(String dbId) {
		this.dbId = dbId;
	}

	@Column(name = "ORG_CODE", length = 3)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "BRANCH_TYPE", length = 2)
	public String getBranchType() {
		return this.branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	@Column(name = "BRANCH_DISTRICT", length = 6)
	public String getBranchDistrict() {
		return this.branchDistrict;
	}

	public void setBranchDistrict(String branchDistrict) {
		this.branchDistrict = branchDistrict;
	}

	@Column(name = "BRANCH_VALUE", length = 9)
	public String getBranchValue() {
		return this.branchValue;
	}

	public void setBranchValue(String branchValue) {
		this.branchValue = branchValue;
	}

	@Column(name = "BRANCH_NAME", length = 50)
	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name = "SUB_BRANCH_VALUE", length = 9)
	public String getSubBranchValue() {
		return this.subBranchValue;
	}

	public void setSubBranchValue(String subBranchValue) {
		this.subBranchValue = subBranchValue;
	}

	@Column(name = "SUB_BRANCH_NAME", length = 50)
	public String getSubBranchName() {
		return this.subBranchName;
	}

	public void setSubBranchName(String subBranchName) {
		this.subBranchName = subBranchName;
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TELEPHONE", length = 30)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "UPDATE_USER", length = 30)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATE_DATE", length = 7)
	public Date getUpdateDate() {
		return this.updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		
		this.updateDate = updateDate;
	}
	
}