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

import com.yinlian.Enums.ShopStatusEnum;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.BrandMapper;
import com.yinlian.wssc.web.mapper.ShopBrandMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.po.ShopBrand;
import com.yinlian.wssc.web.po.ShopBrandExample;
import com.yinlian.wssc.web.service.ShopBrandService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 
 * @author Administrator
 * @version $Id: ShopBrandServiceImpl.java, v 0.1 2016年3月11日 下午5:48:44 Administrator Exp $
 */
public class ShopBrandServiceImpl implements ShopBrandService {
    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(ShopBrandServiceImpl.class);

    @Autowired
    private ShopBrandMapper     shopBrandMapper;
    @Autowired
    private ShopMapper          shopMapper;
    @Autowired
    private BrandMapper         brandMapper;

    /** 
     * @see com.yinlian.wssc.web.service.ShopBrandService#deleteById(java.lang.Integer)
     */
    @Override
    public int deleteById(Integer id, Integer userid) throws Exception {

        ShopBrand shopBrand = shopBrandMapper.selectByPrimaryKey(id);
        shopBrand.setDeltime(new Date());
        shopBrand.setIsdel(true);
        shopBrand.setDeluserid(userid);
        return shopBrandMapper.updateByPrimaryKey(shopBrand);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopBrandService#selectShopBrandPage(com.yinlian.wssc.web.util.Criteria, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectShopBrandPage(Criteria criteria, Integer pc, Integer ps) throws Exception {
        if (criteria == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Criteria is null!");
            }
        }
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<ShopBrand> beanList = shopBrandMapper.selectShopBrandPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopBrandService#queryByShopIdAndBrandId(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public ShopBrand queryByShopIdAndBrandId(Integer shopid, Integer brandid) throws Exception {
        ShopBrandExample example = new ShopBrandExample();
        ShopBrandExample.Criteria criteria = example.createCriteria();
        criteria.andShopidEqualTo(shopid);
        criteria.andBrandidEqualTo(brandid);
        criteria.andIsdelEqualTo(false);
        List<ShopBrand> list = shopBrandMapper.selectByExample(example);
        return list.get(0);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopBrandService#insert(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public int insert(Integer shopid, Integer brandid) throws Exception {
        ShopBrandExample example = new ShopBrandExample();
        ShopBrandExample.Criteria criteria = example.createCriteria();
        criteria.andBrandidEqualTo(brandid);
        criteria.andShopidEqualTo(shopid);
        criteria.andIsdelNotEqualTo(true);
        List<ShopBrand> list = shopBrandMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return -2;
        }
        ShopBrand record = new ShopBrand();
        record.setShopid(shopid);
        record.setBrandid(brandid);
        record.setCreatetime(new Date());
        record.setIsdel(false);        
        record.setCheckstatus(ShopStatusEnum.CheckPass.getValue()); // TODO：需要设置状态 
        return shopBrandMapper.insert(record);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopBrandService#update(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public int update(Integer shopid, Integer brandid, Integer id) throws Exception {
        ShopBrand record = shopBrandMapper.selectByPrimaryKey(id);
        record.setShopid(shopid);
        record.setBrandid(brandid);
        return shopBrandMapper.updateByPrimaryKey(record);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopBrandService#queryById(java.lang.Integer)
     */
    @Override
    public ShopBrand queryById(Integer id) throws Exception {

        return shopBrandMapper.selectByPrimaryKey(id);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopBrandService#updateStatus(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public int updateStatus(Integer id, Integer status) throws Exception {
        ShopBrand record = shopBrandMapper.selectByPrimaryKey(id);
        record.setCheckstatus(status);
        record.setChecktime(new Date());
        return shopBrandMapper.updateByPrimaryKey(record);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopBrandService#selectPage(com.yinlian.wssc.web.util.Criteria, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectPage(Criteria criteria, Integer pc, Integer ps) throws Exception {
        if (criteria == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Criteria is null!");
            }
        }
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<ShopBrand> beanList = shopBrandMapper.selectPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }
    
    public ShopBrand getbySidAndBid(Integer sid,Integer bid)throws Exception{
    	return shopBrandMapper.getbySidAndBid(sid, bid);
    }
}
