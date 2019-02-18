/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ShopnoticeMapper;
import com.yinlian.wssc.web.po.Shopnotice;
import com.yinlian.wssc.web.service.ShopnoticeService;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 店铺通知业务的实现类
 * ShopnoticeServiceImpl.java
 * @author Administrator
 * @version $Id: ShopnoticeServiceImpl.java, v 0.1 2016年3月17日 上午11:03:44 Administrator Exp $
 */
public class ShopnoticeServiceImpl implements ShopnoticeService {

    private static final Logger logger = LoggerFactory.getLogger(ShopnoticeServiceImpl.class);
    @Autowired
    private ShopnoticeMapper    shopnoticeMapper;

    /** 
     * @see com.yinlian.wssc.web.service.ShopnoticeService#insert(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int insert(Integer shopid, String title, String content, String edituser)
                                                                                    throws Exception {
        Shopnotice shopnotice = new Shopnotice();
        shopnotice.setShopid(shopid);
        shopnotice.setContent(content);
        shopnotice.setEdituser(edituser);
        shopnotice.setTitle(title);
        shopnotice.setSendtime(new Date());
        return shopnoticeMapper.insert(shopnotice);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopnoticeService#update(java.lang.Integer, java.lang.String, java.lang.String)
     */
    @Override
    public int update(Integer id, String title, String content) throws Exception {
        Shopnotice record = shopnoticeMapper.selectByPrimaryKey(id);
        record.setTitle(title);
        record.setContent(content);
        return shopnoticeMapper.updateByPrimaryKey(record);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopnoticeService#selectById(java.lang.Integer)
     */
    @Override
    public Shopnotice selectById(Integer id) throws Exception {

        return shopnoticeMapper.selectByPrimaryKey(id);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopnoticeService#deleteById(java.lang.Integer)
     */
    @Override
    public int deleteById(Integer id) throws Exception {

        return shopnoticeMapper.deleteByPrimaryKey(id);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopnoticeService#queryShopNoticesByCriteria(com.yinlian.wssc.web.util.CriteriaShop, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean queryShopNoticesByCriteria(CriteriaShop criteria, Integer pc, Integer ps)
                                                                                             throws Exception {

        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Shopnotice> beanList = shopnoticeMapper.selectShopNoticesByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }
}
