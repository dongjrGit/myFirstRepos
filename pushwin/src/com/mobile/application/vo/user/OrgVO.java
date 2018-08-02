package com.mobile.application.vo.user;

public class OrgVO {
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
	
	public OrgVO() {
		super();
	}
	public OrgVO(String orgId, String orgCode, String orgName, String orgDesc,
			String orgPid, String orgLevel, String isDisable, String orgAdd,
			String updateUser, String updateTime) {
		super();
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgDesc = orgDesc;
		this.orgPid = orgPid;
		this.orgLevel = orgLevel;
		this.isDisable = isDisable;
		this.orgAdd = orgAdd;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgDesc() {
		return orgDesc;
	}
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
	public String getOrgPid() {
		return orgPid;
	}
	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(String isDisable) {
		this.isDisable = isDisable;
	}
	public String getOrgAdd() {
		return orgAdd;
	}
	public void setOrgAdd(String orgAdd) {
		this.orgAdd = orgAdd;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
