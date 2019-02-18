package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

/**
 * 经彩活动参与用户Dto
 * @author Administrator
 *
 */
public class ExcitingUserDto {
	
	private Integer id;              //用户参与经彩活动ID
	private String  spikecode;       //经彩活动验证码
	private Integer isuse;           //是否使用
	private Date usetime;            //使用时间
	private Date outtime;            //过期时间
	private String username;         //用户名
	private String phone;            //手机号
	private Integer isout;           //是否过期
		
	public Integer getIsout() {
		return isout;
	}
	public void setIsout(Integer isout) {
		this.isout = isout;
	}
	public Integer getIsuse() {
		return isuse;
	}
	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}
	public String getSpikecode() {
		return spikecode;
	}
	public void setSpikecode(String spikecode) {
		this.spikecode = spikecode;
	}
	public String getUsetime() {
		return DateUtil.dateConvert(this.usetime);
	}
	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}
	public String getOuttime() {
		return DateUtil.dateConvert(this.outtime);
	}
	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
