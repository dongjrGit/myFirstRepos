package com.yinlian.api.app.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.util.DateUtil;

/**
 * api秒杀活动Dto
 * 
 * @author Administrator
 *
 */
public class SpikeDto {
	
	public SpikeDto(){
		productList=new ArrayList<SpikeSpuDto>();
	}
	private Integer id;   // 活动ID
	private Date enddate; // 结束时间
	private Integer isout_E;//结束 1未结束 0 已结束
	private Integer isout_S;//开始  1未开始 0已开始
	
	private String month;
	
	private String day;
	
	private String hours;
	
	private String minutes;
	
	private Date startdate;

	public List<SpikeSpuDto> productList; // 商品列表
	
	private Calendar cal=Calendar.getInstance();
	
	/**
	 * 过期时间 天:时：分：秒
	 */
	private String pastduedate;

	public String getEnddate() {
		return DateUtil.dateConvert(this.enddate);
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public List<SpikeSpuDto> getProductList() {
		return productList;
	}

	public void setProductList(List<SpikeSpuDto> productList) {
		this.productList = productList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsout_E() {
		return isout_E;
	}

	public void setIsout_E(Integer isout_E) {
		this.isout_E = isout_E;
	}

	public Integer getIsout_S() {
		return isout_S;
	}

	public void setIsout_S(Integer isout_S) {
		this.isout_S = isout_S;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getMonth() {
		if(cal!=null)
		return (cal.get(Calendar.MONTH)+1)+"";
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		if(cal!=null)
		return (cal.get(Calendar.DATE))+"";
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHours() {
		if(cal!=null)
		return (cal.get(Calendar.HOUR_OF_DAY))+"";
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinutes() {
		if(cal!=null)
		return (cal.get(Calendar.MINUTE))+"";
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}


	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	public String getPastduedate() {
		if (enddate!=null) {
			 //两时间差,精确到毫秒   
            long diff = enddate.getTime()-new Date().getTime();  
            long day = diff / 86400000;                         //以天数为单位取整  
            long hour= diff % 86400000 / 3600000;               //以小时为单位取整  
            long min = diff % 86400000 % 3600000 / 60000;       //以分钟为单位取整  
            long seconds = diff % 86400000 % 3600000 % 60000 / 1000;   //以秒为单位取整  
            pastduedate=day+":"+(hour<10?"0"+hour:hour)+":"+(min<10?"0"+min:min)+":"+(seconds<10?"0"+seconds:seconds);
		}
		return pastduedate;
	}

	public void setPastduedate(String pastduedate) {
		this.pastduedate = pastduedate;
	}
	
}
