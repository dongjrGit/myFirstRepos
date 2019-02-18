/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SkuSpecsvMapperCustom;
import com.yinlian.wssc.web.mapper.SpecsvaluesMapper;
import com.yinlian.wssc.web.po.SkuSpecsv;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.po.SpecsvaluesExample;
import com.yinlian.wssc.web.service.SpecsvaluesService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 
 * @author Administrator
 * @version $Id: SpecsvaluesServiceImpl.java, v 0.1 2016年2月29日 上午11:06:49 Administrator Exp $
 */
public class SpecsvaluesServiceImpl implements SpecsvaluesService {

    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(SpecsvaluesServiceImpl.class);

    @Autowired
    private SpecsvaluesMapper   specsvaluesMapper;
    @Autowired
    private SkuSpecsvMapperCustom skuSpecsvMapperCustom;
    /** 
     * @see com.yinlian.wssc.web.service.SpecsvaluesService#querySpecsValuePageBySpecsId(com.yinlian.wssc.web.config.SpecsValueCriteria)
     */
    @Override
    public PageBean querySpecsValuePageBySpecsId(Criteria criteria, Integer pc, Integer ps)
                                                                                           throws Exception {
        if (criteria == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Criteria is null!");
            }
        }

        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();

        List<Specsvalues> beanList = specsvaluesMapper.selectSpecsValuesBySpecsIdPage(pageBeanUtil);
        pageBean.setBeanList(beanList);

        return pageBean;
    }

    /** 
     * @see com.yinlian.wssc.web.service.SpecsvaluesService#deleteById(java.lang.Integer)
     */
    @Override
    public int deleteById(Integer id) throws Exception {
        return specsvaluesMapper.deleteByPrimaryKey(id);
    }

    /** 
     * @see com.yinlian.wssc.web.service.SpecsvaluesService#insertRecord(com.yinlian.wssc.web.po.Specsvalues)
     */
    @Override
    public int insertRecord(Specsvalues record) throws Exception {
        return specsvaluesMapper.insert(record);
    }
    
    @Override
    public int updateStatus(Integer status,Integer id) throws Exception {
    	return specsvaluesMapper.updateStatus(status, id);
    }

	@Override
	public SkuSpecsv getbyValueID(int svid) throws Exception {

		return skuSpecsvMapperCustom.getbyValueID(svid);
	}

    /**
     * 判断是否有该属性值
     * @see com.yinlian.wssc.web.service.SpecsvaluesService#ishave(java.lang.Integer, java.lang.String)
     */
	@Override
	public int ishave(Integer specsid, String value) throws Exception {
		SpecsvaluesExample example = new SpecsvaluesExample();
		SpecsvaluesExample.Criteria criteria = example.createCriteria();
		criteria.andSpecsidEqualTo(specsid);
		criteria.andValueEqualTo(value);
		List<Specsvalues> list = specsvaluesMapper.selectByExample(example);
		if (list.size()>0) {
			return 1;
		}
		return 0;
	}

}
