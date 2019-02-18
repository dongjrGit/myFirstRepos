/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.mapper.SearchvalueMapper;
import com.yinlian.wssc.web.po.Searchvalue;
import com.yinlian.wssc.web.service.SearchvalueService;

/**
 * 搜索属性值得业务实现类
 * @author Administrator
 * @version $Id: SearchvalueServiceImpl.java, v 0.1 2016年2月29日 下午5:49:43 Administrator Exp $
 */
public class SearchvalueServiceImpl implements SearchvalueService {

    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(SearchvalueServiceImpl.class);

    @Autowired
    private SearchvalueMapper   searchvalueMapper;

    /** 
     * @return 
     * @see com.yinlian.wssc.web.service.SearchvalueService#insert(com.yinlian.wssc.web.po.Searchvalue)
     */
    @Override
    public int insert(Searchvalue searchvalue) throws Exception {
        if (searchvalue == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter searchvalue is null!");
            }
        }
        return searchvalueMapper.insert(searchvalue);
    }

    /** 
     * @return 
     * @see com.yinlian.wssc.web.service.SearchvalueService#update(com.yinlian.wssc.web.po.Searchvalue)
     */
    @Override
    public int update(Searchvalue searchvalue) throws Throwable {
        if (searchvalue == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter searchvalue is null!");
            }
        }
        return searchvalueMapper.updateByPrimaryKey(searchvalue);
    }

    /** 
     * @return 
     * @see com.yinlian.wssc.web.service.SearchvalueService#delete(java.lang.Integer)
     */
    @Override
    public int delete(Integer id) throws Exception {
        if (id == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter id is null!");
            }
        }
        return searchvalueMapper.deleteByPrimaryKey(id);
    }

    /** 
     * @see com.yinlian.wssc.web.service.SearchvalueService#selectById(java.lang.Integer)
     */
    @Override
    public Searchvalue selectById(Integer id) throws Exception {

        return searchvalueMapper.selectByPrimaryKey(id);
    }

}
