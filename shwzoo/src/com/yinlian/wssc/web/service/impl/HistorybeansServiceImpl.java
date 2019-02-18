/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.HistorybeansMapper;
import com.yinlian.wssc.web.po.Historybeans;
import com.yinlian.wssc.web.service.HistorybeansService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 业务实现类
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月26日 下午9:36:54 Exp $
 */
@Component("historybeansService")
public class HistorybeansServiceImpl implements HistorybeansService {

    @Autowired
    private HistorybeansMapper historybeansMapper;

    /**
     * @see com.yinlian.wssc.web.service.HistorybeansService#selectPage(com.yinlian.wssc.web.util.Criteria, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectPage(Criteria criteria, Integer pc, Integer ps) throws Exception {

        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Historybeans> beanList = historybeansMapper.selectByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /**
     * @see com.yinlian.wssc.web.service.HistorybeansService#deleteById(java.lang.Integer)
     */
    @Override
    public int deleteById(Integer id) throws Exception {

        return historybeansMapper.deleteByPrimaryKey(id);
    }

}
