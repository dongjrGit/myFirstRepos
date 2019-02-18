/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.dto.SearchkeyDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SearchattrMapper;
import com.yinlian.wssc.web.mapper.SearchvalueMapper;
import com.yinlian.wssc.web.po.Searchattr;
import com.yinlian.wssc.web.po.Searchvalue;
import com.yinlian.wssc.web.service.SearchattrService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

import data.ParseUtil;
import data.StringUtil;

/**
 * 搜索属性的业务实现类
 * @author Administrator
 * @version $Id: SearchattrServiceImpl.java, v 0.1 2016年2月29日 下午5:19:43 Administrator Exp $
 */
public class SearchattrServiceImpl implements SearchattrService {

    /**
    * 日志输出的类
    */
    private static final Logger logger = LoggerFactory.getLogger(SearchattrServiceImpl.class);

    @Autowired
    private SearchattrMapper    searchattrMapper;
    @Autowired
    private SearchvalueMapper   searchvalueMapper;

    /** 
     * @see com.yinlian.wssc.web.service.SearchattrService#selectSearchattrPage(com.yinlian.wssc.web.util.Criteria, java.lang.Integer)
     */
    @Override
    public PageBean selectSearchattrPage(Criteria criteria, Integer pc, Integer ps)
                                                                                   throws Exception {
        if (criteria == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Criteria is null!");
            }
        }
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Searchattr> beanList = searchattrMapper.selectSearchattrPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /** 
     * @see com.yinlian.wssc.web.service.SearchattrService#selectSearchvaluePage(com.yinlian.wssc.web.util.Criteria, java.lang.Integer)
     */
    @Override
    public PageBean selectSearchvaluePage(Criteria criteria, Integer pc, Integer ps)
                                                                                    throws Exception {
        if (criteria == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Criteria is null!");
            }
        }
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Searchvalue> beanList = searchvalueMapper.selectSearchvalueByAttrIdPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /** 
     * @see com.yinlian.wssc.web.service.SearchattrService#selectSearchAttrById(java.lang.Integer)
     */
    @Override
    public Searchattr selectSearchAttrById(Integer id) throws Exception {
        if (id == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter id is null!");
            }
        }

        return searchattrMapper.selectByPrimaryKey(id);
    }

    /** 
     * @return 
     * @see com.yinlian.wssc.web.service.SearchattrService#updateRecord(com.yinlian.wssc.web.po.Searchattr)
     */
    @Override
    public int updateRecord(Searchattr searchattr) throws Exception {
        if (searchattr == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Searchattr is null!");
            }
        }
        return searchattrMapper.updateByPrimaryKey(searchattr);
    }

    /** 
     * @return 
     * @see com.yinlian.wssc.web.service.SearchattrService#deleteSearchAttrById(java.lang.Integer)
     */
    @Override
    public int deleteSearchAttrById(Integer id) throws Exception {
        if (id == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter id is null!");
            }
        }
        return searchattrMapper.deleteByPrimaryKey(id);
    }

    /** 
     * @return 
     * @see com.yinlian.wssc.web.service.SearchattrService#insert(com.yinlian.wssc.web.po.Searchattr)
     */
    @Override
    public int insert(Searchattr searchattr) throws Exception {
        if (searchattr == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Searchattr is null!");
            }
        }
        return searchattrMapper.insert(searchattr);
    }

}
