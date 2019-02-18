/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.pc.dto.AfterserviceDto;
import com.yinlian.wssc.search.Api_OrderCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.AfterserviceMapper;
import com.yinlian.wssc.web.po.Afterservice;
import com.yinlian.wssc.web.service.AfterService;
import com.yinlian.wssc.web.util.PageBeanUtil;



@Component("afterService")
public class AfterServiceImpl implements AfterService {

	@Autowired
	private AfterserviceMapper      afterserviceMapper;
	
	
	@Override
	public PageBean AfterServiceListByPage(Api_OrderCriteria criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<AfterserviceDto> beanList = afterserviceMapper.selectAfterByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}


	@Override
	public Afterservice getAfterserviceByid(Integer id) throws Exception {
		
		return  afterserviceMapper.getAfterserviceByid(id);
	}
}
