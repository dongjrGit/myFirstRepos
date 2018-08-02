package com.mobile.application.vo.endpoint;

public class ReqLoginVO {
	
	private String userCode; //员工号
	private String userPwd; //用户MD5加密密码
	private String devicePin; //设备编号
	private String deviceSim; //设备SIM卡号
	private String deviceTime; //设备当前时间 YYYYMMDDHHMMSS
	private String bankFlag; //银行标示（区分南京银行，昆山银行，日照银行，根据此标示返回对应银行的业务数据）
	
	public String getBankFlag() {
		return bankFlag;
	}
	public void setBankFlag(String bankFlag) {
		this.bankFlag = bankFlag;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getDeviceTime() {
		return deviceTime;
	}
	public void setDeviceTime(String deviceTime) {
		this.deviceTime = deviceTime;
	}
	public String getDevicePin() {
		return devicePin;
	}
	public void setDevicePin(String devicePin) {
		this.devicePin = devicePin;
	}
	public String getDeviceSim() {
		return deviceSim;
	}
	public void setDeviceSim(String deviceSim) {
		this.deviceSim = deviceSim;
	}
}
