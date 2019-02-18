/*
 * YinLian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.FreightAttrMapper;
import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.po.FreightAttr;
import com.yinlian.wssc.web.po.FreightAttrExample;
import com.yinlian.wssc.web.service.FreightAttrService;
import com.yinlian.wssc.web.util.CriteriaFreightAttr;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * FreightAttrServiceImpl.java
 * @author Liang.ma.s
 * @version $Id: FreightAttrServiceImpl.java, v 0.1 2016年4月5日 上午11:01:40 Administrator Exp $
 */
@Component("freightAttrService")
public class FreightAttrServiceImpl implements FreightAttrService {

    @Autowired
    private FreightAttrMapper freightAttrMapper;

    /**
     * @see com.yinlian.wssc.web.service.FreightAttrService#selectByFreightId(java.lang.Integer)
     */
    @Override
    public List<FreightAttr> selectByFreightId(Integer id) throws Exception {
        FreightAttrExample example = new FreightAttrExample();
        FreightAttrExample.Criteria criteria = example.createCriteria();
        criteria.andFreightidEqualTo(id);
        return freightAttrMapper.selectByExample(example);
    }

	@Override
	public PageBean queryAttrByFrightId(CriteriaFreightAttr criteria,
			Integer pc, Integer ps) throws Exception {
		 PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																         // 设置其他的参数
																         // 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<FreightAttr> beanList = freightAttrMapper.selectByFreightIdByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

}
