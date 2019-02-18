/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.Date;
import java.util.List;

import com.yinlian.wssc.platform.vo.AccountsVo;
import com.yinlian.wssc.web.dto.AccountsDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.UsersInfoDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.po.Operaterecords;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.CriteriaOperater;
import com.yinlian.wssc.web.util.CriteriaUser;

/**
 * 
 * @author Administrator
 * @version $Id: OperaterecordsService.java, v 0.1 2016年2月25日 下午4:48:21 Administrator Exp $
 * 
 */
public interface OperaterecordsService {
	
	/**
	 * 插入一条用户操作记录
	 * @param type               操作类型
	 * @param source             操作来源
	 * @param userid             操作人ID
	 * @param username           操作人登录名
	 * @param page               操作页面
	 * @param interfaceName      接口名称
	 * @param desc               描述
	 * @return
	 * @throws Exception
	 */
	public int insertOperaterecords(Integer type,Integer source,Integer userid,String username,String page,String interfaceName,String descript) throws Exception;
	
	/**
	 * 按条件分页查询
	 */
	public PageBean selectRecordsbyPage(CriteriaOperater criteria,Integer page, Integer size) throws Exception;
	
	/**
	 * 清除用户操作记录
	 * @param type
	 * @param source
	 * @param userid
	 * @param username
	 * @param page
	 * @param interfaceName
	 * @param desc
	 * @return
	 * @throws Exception
	 */
	public int deleteAll(Integer type,Integer source,Date createtime,SessionUser user) throws Exception;
} 
