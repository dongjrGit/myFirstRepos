package com.yinlian.api.app.dto;

/**
 * 店铺基本信息
 * @author Administrator
 *
 */
public class ShopBaseDto {
	
	private Integer id;        //店铺ID
	private String name;       //名称
	private String phone;      //联系电话
	private String address;    //地址
	private String longitude;  //经度
	private String latitude;   //纬度
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
}
