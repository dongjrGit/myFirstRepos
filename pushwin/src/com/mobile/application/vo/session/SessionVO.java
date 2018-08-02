package com.mobile.application.vo.session;

import java.util.Set;

public class SessionVO {
	
	private String userId;
	private String userCode;
	private String userName;
	private String userType;
	private String userIcon;
	private String isDisable;
	private String isAdmin;
	private String orgCode;
	private String orgId;
	private String orgName;
	private Set<String> authorizedUrl;
	private IpInfoVO ipInfoVO;
	
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
	public IpInfoVO getIpInfoVO() {
		return ipInfoVO;
	}
	public void setIpInfoVO(IpInfoVO ipInfoVO) {
		this.ipInfoVO = ipInfoVO;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public Set<String> getAuthorizedUrl() {
		return authorizedUrl;
	}
	public void setAuthorizedUrl(Set<String> authorizedUrl) {
		this.authorizedUrl = authorizedUrl;
	}
}
