/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Shopnotice;
import com.yinlian.wssc.web.util.CriteriaShop;

/**
 * 店铺通知的业务接口
 * ShopnoticeService.java
 * @author Administrator
 * @version $Id: ShopnoticeService.java, v 0.1 2016年3月17日 上午11:03:10 Administrator Exp $
 */
public interface ShopnoticeService {

    /**
     * 添加店铺通告
     * @param shopid
     * @param title
     * @param content
     * @param edituser
     * @return
     */
    int insert(Integer shopid, String title, String content, String edituser) throws Exception;

    /**
     * 根据id修改店铺通告
     * @param id
     * @param title
     * @param content
     * @return
     */
    int update(Integer id, String title, String content) throws Exception;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Shopnotice selectById(Integer id) throws Exception;

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Integer id) throws Exception;

    /**
     * 分页查询 某个店铺下的所有通告
     * @param criteria
     * @param pc
     * @param ps
     * @return
     */
    PageBean queryShopNoticesByCriteria(CriteriaShop criteria, Integer pc, Integer ps)
                                                                                      throws Exception;

}
