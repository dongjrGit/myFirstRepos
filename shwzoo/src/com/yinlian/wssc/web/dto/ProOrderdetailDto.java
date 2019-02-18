/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.dto;

import com.yinlian.wssc.web.po.Orderdetail;

/**
 * The class is Accounts DTO object
 * @author Administrator
 * @version $Id: AccountsStub.java, v 0.1 2016年2月25日 下午1:14:55 Administrator Exp $
 */
public class ProOrderdetailDto extends Orderdetail {
	
	private String ProvinceName;

	public String getProvinceName() {
		return ProvinceName;
	}

	public void setProvinceName(String provinceName) {
		ProvinceName = provinceName;
	}
	
}
