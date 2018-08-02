package com.mobile.application.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisOrg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_ORG")
public class TOrgTreeNode implements java.io.Serializable {

	// Fields

	private String orgId;
	private String orgCode;
	private String orgName;
	private String orgDesc;
	private String orgPid;
	private String orgLevel;
	private String isDisable;
	private String orgAdd;
	private String updateUser;
	private String updateTime;
	private String column1;
	private String column2;
	
	// Constructors
	/** default constructor */
	public TOrgTreeNode() {
	}

	/** minimal constructor */
	public TOrgTreeNode(String orgPid) {
		this.orgPid = orgPid;
	}

	/** full constructor */
	public TOrgTreeNode(String orgCode, String orgName, String orgDesc,
			String orgPid, String orgLevel, String isDisable, String orgAdd,
			String updateUser, String updateTime,String column1,String column2) {
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgDesc = orgDesc;
		this.orgPid = orgPid;
		this.orgLevel = orgLevel;
		this.isDisable = isDisable;
		this.orgAdd = orgAdd;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.column1 = column1;
		this.column2 = column2;
	}

	@Column(name = "COLUMN1")
	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	@Column(name = "COLUMN2")
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ORG_ID", unique = true, nullable = false, length = 32)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_CODE", length = 50)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_NAME", length = 128)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "ORG_DESC", length = 128)
	public String getOrgDesc() {
		return this.orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	@Transient
	public String getOrgPid() {
		return this.orgPid;
	}

	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}

	@Column(name = "ORG_LEVEL", length = 4)
	public String getOrgLevel() {
		return this.orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	@Column(name = "IS_DISABLE", length = 2)
	public String getIsDisable() {
		return this.isDisable;
	}

	public void setIsDisable(String isDisable) {
		this.isDisable = isDisable;
	}

	@Column(name = "ORG_ADD", length = 128)
	public String getOrgAdd() {
		return this.orgAdd;
	}

	public void setOrgAdd(String orgAdd) {
		this.orgAdd = orgAdd;
	}

	@Column(name = "UPDATE_USER", length = 32)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	//以下是迭代查询部分
	
	private TOrgTreeNode parent;
	private Set<TOrgTreeNode> children = new LinkedHashSet<TOrgTreeNode>();

	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)  
    @JoinColumn(name = "ORG_PID")
	public TOrgTreeNode getParent() {
		return parent;
	}

	public void setParent(TOrgTreeNode parent) {
		this.parent = parent;
		if(null != parent)
		this.orgPid = parent.getOrgId();
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
	public Set<TOrgTreeNode> getChildren() {
		return children;
	}
	
	public void setChildren(Set<TOrgTreeNode> children) {
		this.children = children;
	}
}