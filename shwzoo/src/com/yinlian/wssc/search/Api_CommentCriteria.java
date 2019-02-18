package com.yinlian.wssc.search;

public class Api_CommentCriteria extends BaseCriteria {
	private Integer spuid;
	/**
	 * 0差评 1中评 2好评
	 */
	private Integer star;
	
	private Integer status;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getSpuid() {
		return spuid;
	}

	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}
}
