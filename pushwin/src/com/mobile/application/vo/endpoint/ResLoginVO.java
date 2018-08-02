package com.mobile.application.vo.endpoint;

public class ResLoginVO {
	//用户信息
	private String userId; //用户ID
	private String userCode; //员工号
	private String userName; //用户姓名
	private String userType; //用户类型
	private String userEmail; //用户邮箱
	private String userPhone; //手机号码
	//机构信息
	private String orgCode; //机构编号
	private String orgName; //机构名称
	//角色信息
	private String roleId; //一个用户可能有多个角色，角色ID“，”分割
	private String roleName;
	//其他信息
	private String userCard; //身份证号
	private String sysLog; //系统log
	private String userIcon; //用户头像路径
	private String iconTime; //图片更新时间
	private String iconLength; //图标长度
	private String iconName; //图片名称
	private String sysTime;
	//是否需要修改密码
	private String modifyPassword; //是否修改密码，true、false
	
	private String userArea;//用戶地區
	private String userCity;//用戶城市
	private String ca;//CA
	private String userbeizhu1;
	private String userbeizhu2;
	private String userbeizhu3;
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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserCard() {
		return userCard;
	}
	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}
	public String getSysLog() {
		return sysLog;
	}
	public void setSysLog(String sysLog) {
		this.sysLog = sysLog;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public String getIconTime() {
		return iconTime;
	}
	public void setIconTime(String iconTime) {
		this.iconTime = iconTime;
	}
	public String getIconLength() {
		return iconLength;
	}
	public void setIconLength(String iconLength) {
		this.iconLength = iconLength;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public String getModifyPassword() {
		return modifyPassword;
	}
	public void setModifyPassword(String modifyPassword) {
		this.modifyPassword = modifyPassword;
	}
	public String getSysTime() {
		return sysTime;
	}
	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}
	public String getUserArea() {
		return userArea;
	}
	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getCa() {
		return ca;
	}
	public void setCa(String ca) {
		this.ca = ca;
	}
	public String getUserbeizhu1() {
		return userbeizhu1;
	}
	public void setUserbeizhu1(String userbeizhu1) {
		this.userbeizhu1 = userbeizhu1;
	}
	public String getUserbeizhu2() {
		return userbeizhu2;
	}
	public void setUserbeizhu2(String userbeizhu2) {
		this.userbeizhu2 = userbeizhu2;
	}
	public String getUserbeizhu3() {
		return userbeizhu3;
	}
	public void setUserbeizhu3(String userbeizhu3) {
		this.userbeizhu3 = userbeizhu3;
	}
	
}
