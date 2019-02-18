package com.yinlian.wssc.search;

import com.yinlian.wssc.web.util.Criteria;

public class AppNewsCriteria extends Criteria {
	private int code;
	private int type;
	private int ctype;
	
	/**
	 * 根据站点查询
	 */
	private String webset;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCtype() {
		return ctype;
	}

	public void setCtype(int ctype) {
		this.ctype = ctype;
	}

	public String getWebset() {
		return webset;
	}

	public void setWebset(String webset) {
		this.webset = webset;
	}
}
