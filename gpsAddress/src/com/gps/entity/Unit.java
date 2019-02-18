package com.gps.entity;

/**
 * 单位访
 * @author Administrator
 *
 */
public class Unit {
	private String lon;		//经度
	private String lat;		//纬度
	private String address;	//地址
	private String requestTime; //请求时间
	private String responseTime;//响应时间
	private String resultError;	//返回错误信息
	

	public String getLon() {
		return lon;
	}



	public void setLon(String lon) {
		this.lon = lon;
	}



	public String getLat() {
		return lat;
	}



	public void setLat(String lat) {
		this.lat = lat;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getRequestTime() {
		return requestTime;
	}



	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}



	public String getResponseTime() {
		return responseTime;
	}



	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}



	public String getResultError() {
		return resultError;
	}



	public void setResultError(String resultError) {
		this.resultError = resultError;
	}



	public Unit(String address) {
		super();
		this.address = address;
	}



	public Unit(String lon, String lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}



	public Unit(String lon, String lat, String address) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.address = address;
	}


	public Unit(String lon, String lat, String address, String requestTime,
			String responseTime, String resultError) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.address = address;
		this.requestTime = requestTime;
		this.responseTime = responseTime;
		this.resultError = resultError;
	}



	public Unit() {
		super();
	}
	
}
