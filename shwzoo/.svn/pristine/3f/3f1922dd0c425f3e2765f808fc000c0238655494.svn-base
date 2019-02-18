/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.showarticle;
import com.yinlian.wssc.web.util.CriteriaSku;


/**
 * 
 * @author Administrator
 * @version $Id: AccountsService.java, v 0.1 2016年2月25日 下午4:48:21 Administrator Exp $
 * 
 */
public interface showarticleService {
	
	/**
	 * 添加晒出范
	 * @param vo
	 * @return
	 * @throws Exception
	 */
    public int insertshowarticle(showarticle vo) throws Exception;

	public PageBean getAllShow(Integer pc, Integer ps, CriteriaSku aoc) throws Exception;

	public showarticle selectInfo(String showid) throws Exception;

	public PageBean getPublishList(Integer pc, Integer ps,
			CriteriaSku aoc) throws Exception;

    /**
     * 根据登录名和密码查询
     * @param name
     * @param password
     * @return
     */
  //  public Accounts queryByNameAndPassword(String name, String password) throws Exception;

   
}
