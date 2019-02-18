package com.yinlian.pc.dto;
/**
 * 收货地址信息
 * @author Administrator
 *
 */
public class ReceiveaddrDto {

	private Integer id;          //收货地址ID
	private String username;     //收货人姓名
	private String province;     //省
	private String city;         //市
	private String area;         //区
	private String address;	     //具体地址
	private String mobile;       //联系电话
	private Integer isdefault;       //是否默认 1
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}
	
	
}
