/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Navigation;
import com.yinlian.wssc.web.util.CriteriaNavigation;

/**
 * NavigationService.java
 * @author sssssssl.m
 * @version $Id: NavigationService.java, v 0.1 2016年4月15日 上午10:00:51 Administrator Exp $
 */
public interface NavigationService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Navigation queryById(Integer id) throws Exception;

    /**
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     */
    PageBean queryByCriteria(CriteriaNavigation criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 插入一条记录
     * @param record
     * @return
     */
    int insert(Navigation record) throws Exception;

    /**
     * 修改信息
     * @param record
     * @return
     */
    int update(Navigation record) throws Exception;

    /**
     * 修改状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Integer id, Integer status) throws Exception;

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Integer id) throws Exception;

    /**
     * 
     * @param page
     * @param size
     * @return
     */
    PageBean selectNavigationPage(Integer page, Integer size) throws Exception;

}
