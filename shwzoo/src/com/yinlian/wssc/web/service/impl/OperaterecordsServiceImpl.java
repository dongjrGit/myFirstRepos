/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.OperaterecordsMapper;
import com.yinlian.wssc.web.po.Operaterecords;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaOperater;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 
 * @author Administrator
 * @version $Id: AccountsServiceImpl.java, v 0.1 2016年2月26日 下午1:27:03
 *          Administrator Exp $
 */
@Component("operaterecordsService")
public class OperaterecordsServiceImpl implements OperaterecordsService {

	@Autowired
	private  OperaterecordsMapper    operaterecordsMapper;
	
	@Autowired  
	private  HttpServletRequest request;  
	/**
	 * 插入一条用户操作记录
	 */
	@Override
	public int insertOperaterecords(Integer type, Integer source,
			Integer userid, String username, String page,
			String interfaceName, String descript) throws Exception {
		
		//String userIp=GetIpAddresss.getIpAddr();
		
		//System.out.println(userIp);
		Operaterecords records=new Operaterecords();
		records.setUserid(userid);
		records.setUsername(username);
		records.setUserip(GetIpAddresss.getIp());
		records.setCreatetime(new Date());
		records.setDescription(descript);
		records.setInterfacename(interfaceName);
		records.setType(type);
		records.setSource(source);
		records.setPage(page);
		return operaterecordsMapper.insertSelective(records);
		
	}

	@Override
	public PageBean selectRecordsbyPage(CriteriaOperater criteria,
			Integer page, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		try {
		
			List<Operaterecords> list = operaterecordsMapper.getRecordsByPage(pageBeanUtil);
			pBean.setBeanList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pBean;
	}

	@Override
	public int deleteAll(Integer type,Integer source,Date createtime,SessionUser user)
			throws Exception {
		CriteriaOperater  criteria=new CriteriaOperater();
		criteria.setType(type);
		criteria.setScoure(source);
		criteria.setTime(createtime);
		operaterecordsMapper.deleteAll(criteria);
		Operaterecords records=new Operaterecords();
		records.setUserid(user.getUserId());
		String userIp=GetIpAddresss.getIpAddr();
		records.setUserip(userIp);
		records.setUsername(user.getLoginName());
		records.setCreatetime(new Date());
		return operaterecordsMapper.insertSelective(records);
	}

}
