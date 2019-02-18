package com.yinlian.wssc.web.util;

import java.util.Date;

/**
 * 会员反馈分页查询用到的字段
 * @author Administrator
 *
 */
public class CriteriaMemberFeedBack extends Criteria {

	private int userid;
	
	private String buyername;
	
	private Date starttime;
	
	private Date endtime;

	public String getBuyername() {
		return buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
}
