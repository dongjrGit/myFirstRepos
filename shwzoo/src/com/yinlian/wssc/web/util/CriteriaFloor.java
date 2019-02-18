package com.yinlian.wssc.web.util;
/**
 * 分页查询专题
 * CriteriaTopic.java
 * @author Administrator
 * @version $Id: CriteriaTopic.java, v 0.1 2016年4月8日 下午2:37:22 Administrator Exp $
 */
public class CriteriaFloor extends Criteria {

	private String name;
	
	private int type;
	private String webset;
	/*private int display;*/
	
	private int pagetag;

	public String getWebSet() {
		return webset;
	}

	public void setWebSet(String webset) {
		this.webset = webset;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/*public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}*/

	public int getPagetag() {
		return pagetag;
	}

	public void setPagetag(int pagetag) {
		this.pagetag = pagetag;
	}

}
