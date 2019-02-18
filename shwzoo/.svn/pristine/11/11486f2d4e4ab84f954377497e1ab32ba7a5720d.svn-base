/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.ShopBrand;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 
 * @author Administrator
 * @version $Id: ShopBrandService.java, v 0.1 2016年3月11日 下午5:48:29 Administrator Exp $
 */
public interface ShopBrandService {

    /**
     * 
     * @param userid 
     * @param toInt
     * @return
     */
    int deleteById(Integer id, Integer userid) throws Exception;

    /**
     * 获取分页的数据
     * @param criteria
     * @param toInt
     * @param toInt2
     * @return
     */
    PageBean selectShopBrandPage(Criteria criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 
     * @param shopid
     * @param brandid
     * @return
     */
    ShopBrand queryByShopIdAndBrandId(Integer shopid, Integer brandid) throws Exception;

    /**
     * 新增一条数据shopbrand表
     * @param shopid
     * @param brandid
     * @return
     */
    int insert(Integer shopid, Integer brandid) throws Exception;

    /**
     * 修改shopbrand 表示
     * @param shopid
     * @param brandid
     * @param id
     * @return
     */
    int update(Integer shopid, Integer brandid, Integer id) throws Exception;

    /**
     * 
     * @param id
     * @return
     */
    ShopBrand queryById(Integer id) throws Exception;

    /**
     * 修改状态
     * 
     * @param id
     * @param status
     * @return
     */
    int updateStatus(Integer id, Integer status) throws Exception;

    /**
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     */
    PageBean selectPage(Criteria criteria, Integer pc, Integer ps) throws Exception;
    
    /**
     * 添加品牌关联时验证是否存在
     * @param sid
     * @param bid
     * @return
     * @throws Exception
     */
    public ShopBrand getbySidAndBid(Integer sid,Integer bid)throws Exception;

}
