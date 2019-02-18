/*
 * @(#) SearchkeyServiceImpl.java 2016年6月23日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.SearchkeyDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SearchkeyMapper;
import com.yinlian.wssc.web.po.Searchkey;
import com.yinlian.wssc.web.service.SearchkeyService;
import com.yinlian.wssc.web.util.PageBeanUtil;


@Component("SearchkeyService")
public class SearchkeyServiceImpl implements SearchkeyService {

	@Autowired
	SearchkeyMapper searchkeyMapper;
	
	@Override
	public PageBean getListByPage(int page, int size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<SearchkeyDto> beanList = searchkeyMapper.getListByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public boolean deltel(int id)throws Exception {
		return searchkeyMapper.deleteByPrimaryKey(id)>0;
	}

	@Override
	public boolean orderByUpd(SearchkeyDto dto)throws Exception {
		return searchkeyMapper.orderByUpd(dto)>0;
	}

	@Override
	public boolean insert(Searchkey record)throws Exception {
		return searchkeyMapper.insert(record)>0;
	}

	public List<Searchkey> getSearchkeys(int usesites)throws Exception{
		return searchkeyMapper.getSearchkeys(usesites);
	}

}
