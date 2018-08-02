package com.mobile.application.vo.login;

public class LoginUserVO {

	private String userId;
	private String userCode;
	private String userName;
	private String userType;
	private String userEmail;
	private String userPhone;
	private String isDisable;
	private String isAdmin;
	private String userMac;
	private String upPwdTime;
	private String noLogin;
	private String userPwd;
	private String updateTime;
	private String updateUser;
	
	private String orgId;
	private String orgCode;
	private String orgName;
	
	public LoginUserVO() {
		super();
	}
	public LoginUserVO(String userId, String userCode, String userName,
			String userType, String userEmail, String userPhone,
			String isDisable, String isAdmin, String userMac, String upPwdTime,
			String noLogin, String userPwd, String updateTime, String updateUser) {
		super();
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.userType = userType;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.isDisable = isDisable;
		this.isAdmin = isAdmin;
		this.userMac = userMac;
		this.upPwdTime = upPwdTime;
		this.noLogin = noLogin;
		this.userPwd = userPwd;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(String isDisable) {
		this.isDisable = isDisable;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getUserMac() {
		return userMac;
	}
	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}
	public String getUpPwdTime() {
		return upPwdTime;
	}
	public void setUpPwdTime(String upPwdTime) {
		this.upPwdTime = upPwdTime;
	}
	public String getNoLogin() {
		return noLogin;
	}
	public void setNoLogin(String noLogin) {
		this.noLogin = noLogin;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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
}
