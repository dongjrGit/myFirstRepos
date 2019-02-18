/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;



import com.yinlian.wssc.search.Api_OrderCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Afterservice;

/**
 * 
 * @author Administrator
 * @version $Id: AccountsService.java, v 0.1 2016年2月25日 下午4:48:21 Administrator Exp $
 * 
 */
public interface AfterService {

	 /**
	  * 返修/退换货记录列表
	  * @param criteria
	  * @param toInt
	  * @param toInt2
	  * @return
	  */
	PageBean AfterServiceListByPage(Api_OrderCriteria criteria, Integer pc,
			Integer ps) throws Exception;

	Afterservice getAfterserviceByid(Integer id) throws Exception ;

}
