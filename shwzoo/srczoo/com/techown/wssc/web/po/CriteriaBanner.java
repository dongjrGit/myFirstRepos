package com.techown.wssc.web.po;

import com.yinlian.wssc.web.util.Criteria;

public class CriteriaBanner  extends Criteria{
	private String title;
	
	private Integer status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
