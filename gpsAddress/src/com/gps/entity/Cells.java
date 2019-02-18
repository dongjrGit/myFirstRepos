package com.gps.entity;

public class Cells {
	private String mcc;
	private String mnc;
	private String lac;
	private String cellid;
	private String sdb;	//信号强度 默认 0
	
	public String getSdb() {
		return sdb;
	}
	public void setSdb(String sdb) {
		this.sdb = sdb;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public String getLac() {
		return lac;
	}
	public void setLac(String lac) {
		this.lac = lac;
	}
	public String getCellid() {
		return cellid;
	}
	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
	public Cells(String mcc, String mnc, String lac, String cellid, String sdb) {
		super();
		this.mcc = mcc;
		this.mnc = mnc;
		this.lac = lac;
		this.cellid = cellid;
		this.sdb = sdb;
	}
	public Cells() {
		super();
	}
	
}
