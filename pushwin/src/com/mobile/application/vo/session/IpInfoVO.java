package com.mobile.application.vo.session;

import java.util.Date;

public class IpInfoVO {
	// IP地址
	private String ip;
	//登录时间
	private Date logonDate;
	
	public IpInfoVO() {
		super();
	}
	public IpInfoVO(String ip, Date logonDate) {
		super();
		this.ip = ip;
		this.logonDate = logonDate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLogonDate() {
		return logonDate;
	}
	public void setLogonDate(Date logonDate) {
		this.logonDate = logonDate;
	}
}
