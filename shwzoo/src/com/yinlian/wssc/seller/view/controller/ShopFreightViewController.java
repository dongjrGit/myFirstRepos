/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.seller.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 卖家的店铺运费模板显示页面的控制类
 * ShopFreight.java
 * @author Administrator
 * @version $Id: ShopFreight.java, v 0.1 2016年3月17日 下午6:05:16 Administrator Exp $
 */
@Controller
@RequestMapping("/seller/freight")
public class ShopFreightViewController {
	
	@Autowired
	private FreightService      freightSerivice;
    /**
     * 日志类
     */
    private static final Logger logger = LoggerFactory.getLogger(ShopFreightViewController.class);

    @RequestMapping("/showShopFreight")
    public String showShopFreight(Integer userid, HttpServletRequest request) {
        try {
            userid = SessionUtil.getSessionUserId(request);
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("userid", userid);
        return "seller/shopFreight/freightTemplate";
    }
    
    /**
     * 显示运费子模板页面
     * 
     * @return
     */
    @RequestMapping("/subFreight_Manager_list")
    public String subFreight_Manager_list(String ftid,HttpServletRequest request) {
        try {
        	Freight freight=freightSerivice.selectById(StringUtilsEX.ToInt(ftid));
        	if(freight!=null){
        		Integer isby=freight.getIsexemptionpostage();
        		/* List<FreightAttr> freightAttr=reightAttrService.selectByFreightId(StringUtilsEX.ToInt(ftid));*/
        		request.setAttribute("ftid", ftid);
        		if(isby==1){
        			return "seller/shopFreight/bysubfreightTemplate";
        		}else{
        			return "seller/shopFreight/nobysubfreightTemplate";
        		}
        	}
        } catch (Exception e) {
            logger.error("", e);
        }
        return "";
    }
    
}
