/*
 * @(#) PcActivityViewController.java 2016年7月12日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.view.pc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaBaner;
import com.yinlian.wssc.web.service.BanerService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.util.CriteriaActivity;

/**
 * 活动
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年7月12日
 */
@Controller
@RequestMapping("/web/activity")
public class PcActivityViewController {

	@Autowired
	private BanerService banerService;

	@Autowired
	private CategoryService categoryService;
	

	@Autowired
	private SpikeActivityService spikeActivityService;
	
	/**
	 * 秒杀
	 * 
	 * @author kh.wang
	 * @since 2016年7月12日
	 * @return
	 */
	@RequestMapping("/miao.html")
	public String miao(Model model){
		//活动数量
		int count =spikeActivityService.seekSeckillCount();
		model.addAttribute("count", count);
		return "/template/pc/pro/Goods_SpikeList";
	}
	
	@RequestMapping("/shangou.html")
	public String tuan(Model model) throws Exception{
		
		CriteriaBaner criteria = new CriteriaBaner();
		criteria.setType(4);//闪购
		criteria.setOrderByClause("sort");
		PageBean pageBean = banerService.selBaner(criteria, 0, 5);
		model.addAttribute("ad1list",pageBean.getBeanList());
		criteria.setType(5);//闪购侧面
		criteria.setOrderByClause("sort");
		pageBean = banerService.selBaner(criteria, 0,1);
		model.addAttribute("adleftlist",pageBean.getBeanList());
		return "/template/pc/pro/Goods_SgList";
	}
	
	@RequestMapping("/tuangou.html")
	public String tuangou(Model model){
		return "/template/pc/pro/Goods_tgList";
	}
	@RequestMapping("/coupon.html")
	public String coupon(Model model){
		return "/template/pc/pro/WCouponList";
	}
	
	
}
