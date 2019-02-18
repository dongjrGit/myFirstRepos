package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

/**
 * 代金券Dto
 * @author Administrator
 *
 */
public class VoucherDto {

	private String code;             //代金券组合编号
	private Integer totalcount;      //总数
	private Integer lastcount;        //剩余可分配数
	private Float value;              //面值
	private Float quota;              //限制条件
	private Date endtime;            //到期时间
	private Integer isout;
	private String endtimestr;       //
	private String name;             //
	public String getCode() { 
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	
	public Integer getLastcount() {
		return lastcount;
	}
	public void setLastcount(Integer lastcount) {
		this.lastcount = lastcount;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Float getQuota() {
		return quota;
	}
	public void setQuota(Float quota) {
		this.quota = quota;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getEndtimestr() {
		return DateUtil.dateConvert(this.endtime);
	}
	public void setEndtimestr(String endtimestr) {
		this.endtimestr = endtimestr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsout() {
		return isout;
	}
	public void setIsout(Integer isout) {
		this.isout = isout;
	}
	
}
