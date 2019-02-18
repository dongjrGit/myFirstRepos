package com.yinlian.wssc.web.util;

/**
 * 分页咨询列表用到的条件字段
 * @author Administrator
 *
 */
public class CriteriaGoodConsult extends Criteria {
	
	private String title;//产品标题
	
	private String pronumstr;//产品编号
	
	private String spuId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPronumstr() {
		return pronumstr;
	}

	public void setPronumstr(String pronumstr) {
		this.pronumstr = pronumstr;
	}

	public String getSpuId() {
		return spuId;
	}

	public void setSpuId(String spuId) {
		this.spuId = spuId;
	}
	
	

}
