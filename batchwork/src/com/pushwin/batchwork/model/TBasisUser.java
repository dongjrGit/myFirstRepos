package com.pushwin.batchwork.model;

import java.util.HashSet;
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

import org.hibernate.annotations.GenericGenerator;


/**
 * TBasisUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_USER")
public class TBasisUser implements java.io.Serializable {

	// Fields

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
	private String userIcon;
	private String iconTime;
	private String iconLength ;
	private String iconName;
	private String userArea;
	private String userCity;
	private String ca;
	private String userbeizhu1;
	private String userbeizhu2;
	private String userbeizhu3;
	private TBasisOrg TBasisOrg;
	private Set<TBasisUserData> TBasisUserDatas = new HashSet<TBasisUserData>(0);

	// Constructors

	/** default constructor */
	public TBasisUser() {
	}

	/** full constructor */
	public TBasisUser(String userCode, String userName, String userType,
			String userEmail, String userPhone, String isDisable,
			String isAdmin, String userMac, String upPwdTime, String noLogin,
			String userPwd, String updateTime, String updateUser,
			String userIcon,String iconTime,String iconLength,String iconName,
			Set<TBasisUserData> TBasisUserDatas,
			TBasisOrg TBasisOrg) {
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
		this.TBasisUserDatas = TBasisUserDatas;
		this.TBasisOrg = TBasisOrg;
		this.userIcon = userIcon;
		this.iconLength = iconLength;
		this.iconName = iconName;
		this.iconTime = iconTime;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "USER_ID", unique = true, nullable = false, length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USER_CODE", length = 32)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "USER_NAME", length = 32)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_TYPE", length = 20)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "USER_EMAIL", length = 20)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "USER_PHONE", length = 20)
	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Column(name = "IS_DISABLE", length = 20)
	public String getIsDisable() {
		return this.isDisable;
	}

	public void setIsDisable(String isDisable) {
		this.isDisable = isDisable;
	}

	@Column(name = "IS_ADMIN", length = 20)
	public String getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(name = "USER_MAC", length = 20)
	public String getUserMac() {
		return this.userMac;
	}

	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}

	@Column(name = "UP_PWD_TIME", length = 23)
	public String getUpPwdTime() {
		return this.upPwdTime;
	}

	public void setUpPwdTime(String upPwdTime) {
		this.upPwdTime = upPwdTime;
	}

	@Column(name = "NO_LOGIN", length = 10)
	public String getNoLogin() {
		return this.noLogin;
	}

	public void setNoLogin(String noLogin) {
		this.noLogin = noLogin;
	}

	@Column(name = "USER_PWD", length = 32)
	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Column(name = "UPDATE_TIME")
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_USER", length = 32)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TBasisUser")
	public Set<TBasisUserData> getTBasisUserDatas() {
		return this.TBasisUserDatas;
	}

	public void setTBasisUserDatas(Set<TBasisUserData> TBasisUserDatas) {
		this.TBasisUserDatas = TBasisUserDatas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	public TBasisOrg getTBasisOrg() {
		return this.TBasisOrg;
	}

	public void setTBasisOrg(TBasisOrg TBasisOrg) {
		this.TBasisOrg = TBasisOrg;
	}
	@Column(name = "USER_ICON")
	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	@Column(name = "ICON_TIME")
	public String getIconTime() {
		return iconTime;
	}

	public void setIconTime(String iconTime) {
		this.iconTime = iconTime;
	}
	@Column(name = "ICON_LENGTH")
	public String getIconLength() {
		return iconLength;
	}

	public void setIconLength(String iconLength) {
		this.iconLength = iconLength;
	}
	
	@Column(name = "ICON_NAME")
	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	
	@Column(name = "USER_AREA")
	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}
	@Column(name = "USER_CITY")
	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	@Column(name = "CA")
	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}
	@Column(name = "USERBEIZHU1")
	public String getUserbeizhu1() {
		return userbeizhu1;
	}

	public void setUserbeizhu1(String userbeizhu1) {
		this.userbeizhu1 = userbeizhu1;
	}
	@Column(name = "USERBEIZHU2")
	public String getUserbeizhu2() {
		return userbeizhu2;
	}

	public void setUserbeizhu2(String userbeizhu2) {
		this.userbeizhu2 = userbeizhu2;
	}
	@Column(name = "USERBEIZHU3")
	public String getUserbeizhu3() {
		return userbeizhu3;
	}

	public void setUserbeizhu3(String userbeizhu3) {
		this.userbeizhu3 = userbeizhu3;
	}
	
	
}