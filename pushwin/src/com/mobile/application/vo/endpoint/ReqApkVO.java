package com.mobile.application.vo.endpoint;

public class ReqApkVO {
	String userCode; //员工号
	String lastTime; //最后一次下载zip包的时间，如果第一次下载传递空值 yyyy-mm-dd hh:MM:ss
	
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
}
