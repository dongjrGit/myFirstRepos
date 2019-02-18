/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.ShopClass;

/**
 * 店铺分类的业务 接口类
 * @author Administrator
 * @version $Id: ShopClassService.java, v 0.1 2016年3月11日 下午1:34:53 Administrator Exp $
 */
public interface ShopClassService {

    /**
     * 
     * @param shopid
     * @return
     */
    List<ShopClass> queryBy(Integer shopid) throws Exception;
    int deleteByshopid(Integer shopid)throws Exception;
}
