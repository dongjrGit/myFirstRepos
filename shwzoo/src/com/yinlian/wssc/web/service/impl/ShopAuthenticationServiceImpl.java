/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ShopAuthenticationMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.ShopAuthenticationExample;
import com.yinlian.wssc.web.service.ShopAuthenticationService;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 
 * @author Administrator
 * @version $Id: ShopAuthenticationServiceImpl.java, v 0.1 2016年3月10日 下午7:15:13 Administrator Exp $
 */
public class ShopAuthenticationServiceImpl implements ShopAuthenticationService {

    private static final Logger      logger = LoggerFactory
                                                .getLogger(ShopAuthenticationServiceImpl.class);

    @Autowired
    private ShopAuthenticationMapper shopAuthenticationMapper;
    @Autowired
    private ShopMapper               shopMapper;

    /** 
     * @see com.yinlian.wssc.web.service.ShopAuthenticationService#queryByShopId(java.lang.Integer)
     */
    @Override
    public ShopAuthentication queryByShopId(Integer id) throws Exception {

        ShopAuthenticationExample example = new ShopAuthenticationExample();
        ShopAuthenticationExample.Criteria criteria = example.createCriteria();
        criteria.andShopidEqualTo(id);
        return shopAuthenticationMapper.selectByExample(example).get(0);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopAuthenticationService#selectAuthenticationPage(com.yinlian.wssc.web.util.CriteriaShop, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectAuthenticationPage(CriteriaShop criteria, Integer pc, Integer ps)
                                                                                           throws Exception {
        if (criteria == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Criteria is null!");
            }
        }
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<ShopAuthentication> beanList = shopAuthenticationMapper.selectPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopAuthenticationService#queryDetailByShopId(java.lang.Integer)
     */
    @Override
    public ShopAuthentication queryDetailByShopId(Integer shopid) throws Exception {
        Shop shop = shopMapper.selectByPrimaryKey(shopid);
        ShopAuthenticationExample example = new ShopAuthenticationExample();
        ShopAuthenticationExample.Criteria criteria = example.createCriteria();
        criteria.andShopidEqualTo(shopid);
        ShopAuthentication authentication = shopAuthenticationMapper.selectByExample(example)
            .get(0);
        authentication.setShopname(shop.getName());
        return authentication;
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopAuthenticationService#updateByid(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int updateByid(Integer id, String companyaddress, String companymobile,
                          String companyemail, String companyfox, String postcode,String BankName,String BankCardNo,String HoderName) throws Exception {
    	
    	
        ShopAuthentication authentication = shopAuthenticationMapper.selectByPrimaryKey(id);
        authentication.setCompanyadress(companyaddress);
        //authentication.setCompanyemail(companyemail);
        authentication.setCompanyfox(companyfox);
        //authentication.setCompanytel(companymobile);
        authentication.setPostcode(postcode);
        //Shop shop = shopMapper.selectByPrimaryKey(authentication.getShopid());
        //shop.setBankcardno(BankCardNo);
        //shop.setBankname(BankName);
        //shop.setHodername(HoderName);
        //shopMapper.updateByPrimaryKey(shop);
        return shopAuthenticationMapper.updateByPrimaryKey(authentication);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ShopAuthenticationService#updateContact(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int updateContact(Integer id, String pName, String pMobile, String pEmail, String pPost)
                                                                                                   throws Exception {
        ShopAuthentication authentication = shopAuthenticationMapper.selectByPrimaryKey(id);
        authentication.setPrincipalname(pName);
        authentication.setPrincipalmobile(pMobile);
        authentication.setPrincipalemail(pEmail);
        authentication.setPrincipalpost(pPost);
        return shopAuthenticationMapper.updateByPrimaryKey(authentication);
    }

	@Override
	public int updateByExample(ShopAuthentication authentication)
			throws Exception {
		return shopAuthenticationMapper.updateByPrimaryKey(authentication);
	}

}
