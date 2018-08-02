package com.music.entity;

public class User {
	private String userID;
	private String userName;
	private String password;
	private String nickName;
	private String loginTime;
	
	public User() {
		super();
	}
	public User(String userName, String password, String nickName, String loginTime) {
		super();
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.loginTime = loginTime;
	}
	public User(String userID, String userName, String password, String nickName, String loginTime) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.loginTime = loginTime;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickName;
	}
	public void setNickname(String nickname) {
		nickName = nickname;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", password=" + password + ", nickName=" + nickName
				+ ", loginTime=" + loginTime + "]";
	}
}
