/*
 * @(#) PcShopController.java 2016年7月21日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.view.pc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.ShopcategoryService;
import com.yinlian.wssc.web.util.StringUtilsEX;
@Controller
@RequestMapping("/web/shop")
public class PcShopViewController {


	@Autowired
	private AdverisingService adverisingService;
	@Autowired
	private ShopcategoryService shopcategory;
	
	@Autowired
	private ShopService shopService;

	@Autowired
	private CouponService couponService;
	
	@Autowired
	private BrandService brandService;

	@RequestMapping("/details.html")
	public String shopHome(String shopId,String flagshopId,Model model,HttpServletRequest request) throws Exception{
	try {
		int shopid=0;
		model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.店铺页.getValue(), 1, 1,StringUtilsEX.ToInt(shopId)) );
		model.addAttribute("dz",adverisingService.getListByTypeAndDisplay(PageMarkType.店招.getValue(), 1, 1,StringUtilsEX.ToInt(shopId)));
		System.out.println(adverisingService.getListByTypeAndDisplay(PageMarkType.店招.getValue(), 1, 1,StringUtilsEX.ToInt(shopId)).size());
		if(StringUtilsEX.ToInt(shopId)>0){
			Shop shop=shopService.queryById(StringUtilsEX.ToInt(shopId));
			if(shop!=null){
				shopid=shop.getId();
			}else {
				model.addAttribute("msg", "该商铺不存在");
			}
		}else{
			if(StringUtilsEX.ToInt(flagshopId)>0){
				Brand brand=brandService.selectById(StringUtilsEX.ToInt(flagshopId));
				if(brand==null || brand.getFlagshipid()==null){
					model.addAttribute("msg", "该品牌未设置对应旗舰店");
				}else {
					shopid=brand.getFlagshipid();
				}
			}
		}
		String customcId=request.getParameter("customcId");
		if(StringUtilsEX.ToInt(customcId)<0){
			customcId="0";
		}
		String sname=request.getParameter("spuname");		
		String pmax=request.getParameter("pricemax");
		String pmin=request.getParameter("pricemin");
		model.addAttribute("customcId", customcId);		
		model.addAttribute("sname", sname);
		model.addAttribute("pmax", pmax);
		model.addAttribute("pmin", pmin);
		model.addAttribute("shopId", shopid);
		model.addAttribute("fl", shopcategory.queryByShopId(shopid));		
		model.addAttribute("yhj", couponService.findByShopId(shopid));
	} catch (Exception e) {
		e.printStackTrace();
		return "redirect:/404.html";
	}
		return "/template/pc/Sy/ShopHome";
	}
	
}
