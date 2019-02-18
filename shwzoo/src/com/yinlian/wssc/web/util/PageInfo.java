package com.yinlian.wssc.web.util;

import net.sf.json.JSONObject;

/**
 * 分页属性
 * 
 * @author mjx
 *
 */
public class PageInfo {
	private Integer index;
	private Integer count;
	private Integer maxpage;
	private Integer size;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(Integer maxpage) {
		this.maxpage = maxpage;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String tojson() {
		JSONObject json;
		json = JSONObject.fromObject(this);
		return json.toString();	}

}
