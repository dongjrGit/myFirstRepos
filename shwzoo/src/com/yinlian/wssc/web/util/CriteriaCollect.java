package com.yinlian.wssc.web.util;

public class CriteriaCollect extends Criteria {

	private String title;//产品标题
	
	private String pronumstr;//产品编号
	
	private String username;//收藏人
	
	private String shopname;//店铺名称
	private Integer userid;//用户id
	private Integer type;//收藏类型 商品(0), 店铺(1);
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	
}
