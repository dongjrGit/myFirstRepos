/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.util.CriteriaShop;

/**
 * 
 * @author Administrator
 * @version $Id: ShopAuthenticationService.java, v 0.1 2016年3月10日 下午7:14:49 Administrator Exp $
 */
public interface ShopAuthenticationService {

    /**
     * 
     * @param id
     * @return
     */
    ShopAuthentication queryByShopId(Integer id) throws Exception;

    /**
     * 查询店铺关联信息的分页数据
     * @param criteria
     * @param pc 当前页
     * @param ps 每页大小
     * @return
     */
    PageBean selectAuthenticationPage(CriteriaShop criteria, Integer pc, Integer ps)
                                                                                    throws Exception;

    /**
     * 根据shopid查询 详细信息
     * @param shopid
     * @return
     */
    ShopAuthentication queryDetailByShopId(Integer shopid) throws Exception;

    /**
     * 根据id修改 公司信息
     * @param id
     * @param companyaddress
     * @param companymobile
     * @param companyemail
     * @param companyfox
     * @param postcode
     * @return
     */
    int updateByid(Integer id, String companyaddress, String companymobile, String companyemail,
                   String companyfox, String postcode,String BankName,String BankCardNo,String HoderName) throws Exception;

    /**
     * 根据id修改联系人信息
     * @param id
     * @param pName
     * @param pMobile
     * @param pEmail
     * @param pPost
     * @return
     */
    int updateContact(Integer id, String pName, String pMobile, String pEmail, String pPost)
                                                                                            throws Exception;
    
    int updateByExample(ShopAuthentication authentication)throws Exception;

}
