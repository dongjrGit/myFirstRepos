package com.techown.wssc.web.po;

import com.yinlian.wssc.web.util.Criteria;

public class CriteriaEditor extends Criteria {
	private Integer type;
	private String title;

	private Integer delState;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDelState() {
		return delState;
	}

	public void setDelState(Integer delState) {
		this.delState = delState;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
