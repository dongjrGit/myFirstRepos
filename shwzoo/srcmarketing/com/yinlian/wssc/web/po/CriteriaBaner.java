package com.yinlian.wssc.web.po;

import com.yinlian.wssc.web.util.Criteria;

public class CriteriaBaner extends Criteria {

	private String title;

	private Integer ctype;
	
	private Integer type;

	public Integer getCtype() {
		return ctype;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
