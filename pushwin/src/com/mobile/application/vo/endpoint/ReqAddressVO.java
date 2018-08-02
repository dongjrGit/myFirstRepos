package com.mobile.application.vo.endpoint;

public class ReqAddressVO {
    String latitude;//纬度
    String longitude;//经度
    String userCode;//员工号
    String devicePin;//设备pin
    String updTime;//更新时间
    
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getDevicePin() {
		return devicePin;
	}
	public void setDevicePin(String devicePin) {
		this.devicePin = devicePin;
	}
	public String getUpdTime() {
		return updTime;
	}
	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}
    
}
