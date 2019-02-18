/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.mapper.ShopClassMapper;
import com.yinlian.wssc.web.po.ShopClass;
import com.yinlian.wssc.web.po.ShopClassExample;
import com.yinlian.wssc.web.service.ShopClassService;

/**
 * 店铺分类的业务实现类
 * @author Administrator
 * @version $Id: ShopClassServiceImpl.java, v 0.1 2016年3月11日 下午1:35:06 Administrator Exp $
 */
public class ShopClassServiceImpl implements ShopClassService {

    @Autowired
    private ShopClassMapper shopClassMapper;

    /** 
     * @see com.yinlian.wssc.web.service.ShopClassService#queryBy(java.lang.Integer)
     */
    @Override
    public List<ShopClass> queryBy(Integer shopid) throws Exception {
        ShopClassExample example = new ShopClassExample();
        ShopClassExample.Criteria criteria = example.createCriteria();
        criteria.andShopidEqualTo(shopid);
        return shopClassMapper.selectByExample(example);
    }

	@Override
	public int deleteByshopid(Integer shopid) throws Exception {
		// TODO Auto-generated method stub
		return shopClassMapper.deleteByshopid(shopid);
	}

}
