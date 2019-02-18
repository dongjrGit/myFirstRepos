package com.yinlian.wssc.web.util;

/**
 * 搜索属性的多条件查询类
 * @author Administrator
 *
 */
public class CriteriaAttr extends Criteria {
	
	private Integer attrid;         // 搜索属性的id
	
	private String  searchattrrname; // 搜索属性的名称  
	
	/**
	 * 
	 * @return
	 */
	public Integer getAttrid() {
		return attrid;
	}
	/**
	 * 
	 * @param attrid
	 */
	public void setAttrid(Integer attrid) {
		this.attrid = attrid;
	}

	public String getSearchattrrname() {
		return searchattrrname;
	}

	public void setSearchattrrname(String searchattrrname) {
		this.searchattrrname = searchattrrname;
	}

	
}
