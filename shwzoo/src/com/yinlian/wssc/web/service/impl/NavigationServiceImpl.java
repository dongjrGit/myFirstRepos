/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.NavigationAppDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.NavigationMapper;
import com.yinlian.wssc.web.po.Navigation;
import com.yinlian.wssc.web.service.NavigationService;
import com.yinlian.wssc.web.util.CriteriaNavigation;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * NavigationServiceImpl.java
 * @author sssssssl.m
 * @version $Id: NavigationServiceImpl.java, v 0.1 2016年4月15日 上午10:01:10 Administrator Exp $
 */
@Component("navigationService")
public class NavigationServiceImpl implements NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    /**
     * @see com.yinlian.wssc.web.service.NavigationService#queryById(java.lang.Integer)
     */
    @Override
    public Navigation queryById(Integer id) throws Exception {

        return navigationMapper.selectByPrimaryKey(id);
    }

    /**
     * @see com.yinlian.wssc.web.service.NavigationService#queryByCriteria(com.yinlian.wssc.web.util.CriteriaNavigation, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean queryByCriteria(CriteriaNavigation criteria, Integer pc, Integer ps)
                                                                                        throws Exception {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Navigation> beanList = navigationMapper.selectByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /**
     * @see com.yinlian.wssc.web.service.NavigationService#insert(com.yinlian.wssc.web.po.Navigation)
     */
    @Override
    public int insert(Navigation record) throws Exception {
        return navigationMapper.insert(record);
    }

    /**
     * @see com.yinlian.wssc.web.service.NavigationService#update(com.yinlian.wssc.web.po.Navigation)
     */
    @Override
    public int update(Navigation record) throws Exception {
        return navigationMapper.updateByPrimaryKey(record);
    }

    /**
     * @see com.yinlian.wssc.web.service.NavigationService#updateStatus(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public int updateStatus(Integer id, Integer status) throws Exception {
        Navigation record = navigationMapper.selectByPrimaryKey(id);
        record.setStatus(status);
        return navigationMapper.updateByPrimaryKey(record);
    }

    /**
     * @see com.yinlian.wssc.web.service.NavigationService#deleteById(java.lang.Integer)
     */
    @Override
    public int deleteById(Integer id) throws Exception {
        return navigationMapper.deleteByPrimaryKey(id);
    }

    /**
     * @see com.yinlian.wssc.web.service.NavigationService#selectNavigationPage(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectNavigationPage(Integer page, Integer size) throws Exception {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(page, size);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<NavigationAppDto> beanList = navigationMapper.selectNavigationPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

}
