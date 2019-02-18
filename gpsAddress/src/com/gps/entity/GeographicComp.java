/*
 *gpsAddress
 *com.gps.entity
 *2018-3-23
 *TODO
 *chenji
 *
 *
 *
 * 
 */
package com.gps.entity;

/**
 * @TODO
 * @date 2018-3-23
 * @time ����3:44:08
 * @author chenji
 */
public class GeographicComp extends Address{
	private String address;
	private String cellid;
	private String lac;
	private String latitude;
	private String longtitude;
	private String mcc;
	private String requestTime;
	private String responseTime;
	private String resultError;
	private String sdb;
	private String visitType;
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCellid() {
		return cellid;
	}
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
	public String getLac() {
		return lac;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public GeographicComp(String address, String latitude, String longtitude,String visitType,String resultError) {
		super();
		this.address = address;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.visitType=visitType;
		this.resultError=resultError;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
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
	public String getSdb() {
		return sdb;
	}
	public void setSdb(String sdb) {
		this.sdb = sdb;
	}

}
