package com.yinlian.wssc.web.util;
/**
 * 申请店铺分页所用到的字段
 * CriteriaApplyShop.java
 * @author Administrator
 * @version $Id: CriteriaApplyShop.java, v 0.1 2016年5月16日 上午10:03:36 Administrator Exp $
 */
public class CriteriaApplyShop extends Criteria {
	private String contactsname;
	
	private String mobile;
	
	private String shopname;
	
	public String getContactsname() {
		return contactsname;
	}
	public void setContactsname(String contactsname) {
		this.contactsname = contactsname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	
}
