/*
 * @(#) PcCouponController.java 2016年7月20日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.view.pc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.wssc.search.Pc_TopicCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.CriteriaBaner;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.BanerService;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.TopicService;

/**
 * 优惠卷
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年7月22日
 */
@Controller
@RequestMapping("/web/coupon")
public class PcCouponController {

	@Autowired
	CouponService couponService;
	@Autowired
	private BanerService banerService;
	@Autowired
	private AdverisingService adverusingService;
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("/index.html")
	public String index(Model model) throws Exception{
		try{
			Pc_TopicCriteria atc = new Pc_TopicCriteria();
			CriteriaBaner criteria = new CriteriaBaner();
			
			//优惠券轮播
			criteria.setType(9);
			criteria.setOrderByClause("sort");
			PageBean pageBean = banerService.selBaner(criteria, 0, 5);
			model.addAttribute("ad1list",pageBean.getBeanList());
			
			//优惠券广告
			List<Advertising> list=adverusingService.getListByType(PageMarkType.优惠卷领取页PC.getValue(), WebSetEnum.pc.getValue());
			model.addAttribute("ad2list",list);
			
			//商品推荐
			atc.setMark(TopicMarkEnum.优惠券热门推荐.getValue());
			atc.setPagetag(PageMarkType.优惠卷领取页.getValue());
			atc.setType(TopicTypeEnum.商品.getValue());
			atc.setWebset( WebSetEnum.pc.getValue() + "");
			model.addAttribute("rmtj",topicService.getPcTopByPage(atc, 1, 10).getBeanList());
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/404.html";
		}		
		return "/template/pc/WebCoupon/WCouponList";
	}
	
	
}
