/*
 * @(#) WebsitesConfigServiceImpl.java 2016年6月23日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.WebsitesConfigDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.WebsitesConfigMapper;
import com.yinlian.wssc.web.po.WebsitesConfig;
import com.yinlian.wssc.web.service.WebsitesConfigService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("WebsitesConfigService")
public class WebsitesConfigServiceImpl implements WebsitesConfigService {

	@Autowired
	WebsitesConfigMapper websitesConfigMapper;
	
	@Override
	public int insertAdd(WebsitesConfig WebsitesConfig) {
		return websitesConfigMapper.insertSelective(WebsitesConfig);
	}

	@Override
	public PageBean getListByPage(Integer page, Integer size) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<WebsitesConfigDto> beanList = websitesConfigMapper.getListByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public int deltel(Integer toInt) {
		return websitesConfigMapper.deleteByPrimaryKey(toInt);
	}

	@Override
	public WebsitesConfigDto selectById(Integer id) {
		return websitesConfigMapper.selectById(id);
	}


	@Override
	public int updateById(WebsitesConfig dto) {
		return websitesConfigMapper.updateById(dto);
	}

	@Override
	public WebsitesConfigDto selectByUserId(Integer integer) {
		return websitesConfigMapper.selectByUserId(integer);
	}

	@Override
	public WebsitesConfig selectConfig() throws Exception {
		
		return websitesConfigMapper.selectConfig();
	}





}
