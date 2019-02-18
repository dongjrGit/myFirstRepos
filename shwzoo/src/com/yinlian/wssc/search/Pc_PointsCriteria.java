package com.yinlian.wssc.search;

import java.util.Date;

public class Pc_PointsCriteria extends BaseCriteria {
	private Integer  userid;
	
	private String  begintime;
	
	private String  endtime;
	
	private  String type;

	

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
