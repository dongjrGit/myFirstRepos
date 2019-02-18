/*
 * @(#) PcShopController.java 2016年7月21日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.pc.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/pc/shop")
public class PcShopController {

	@Autowired
	SpuService spuservice;
	
	@RequestMapping("/productlist")
	public @ResponseBody ReusltItem productList(String ch,String page,String pagesize,String shopId,
			Integer customcId,String pricemin,String pricemax,String spuname){
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item;
			}
			int index=1;
			int size=10;
			if (StringUtilsEX.ToInt(page) > 0) {
				index=StringUtilsEX.ToInt(page);
			}
			if (StringUtilsEX.ToInt(pagesize) > 0) {
				size=StringUtilsEX.ToInt(pagesize);
			}
			CriteriaShop criteria = new CriteriaShop();
			criteria.setSpuname(spuname);
			if(StringUtilsEX.ToFloat(pricemin)>=0){
				criteria.setPricemin(StringUtilsEX.ToFloat(pricemin));
			}
			if(StringUtilsEX.ToFloat(pricemax)>0){
				criteria.setPricemax(StringUtilsEX.ToFloat(pricemax));
			}
			criteria.setShopid(StringUtilsEX.ToInt(shopId));
			criteria.setOrderByClause("CreateTime");
			criteria.setSort("desc");
			if(customcId!=null&&customcId>0){
				criteria.setCustomcId(customcId);
			}			
			PageBean pBean = spuservice.getShopSpuPage(criteria, index, size);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("系统错误");
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取店铺商品列表异常! 异常信息:{0}", e), "/shop/productList");
		}

		return item;
	}
	
	
	
}
