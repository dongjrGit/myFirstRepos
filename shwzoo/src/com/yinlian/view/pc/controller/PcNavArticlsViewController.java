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
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 活动
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年7月12日
 */
@Controller
@RequestMapping("/web/article")
public class PcNavArticlsViewController {

    @Autowired
    private  ArticlesService   articlesService;
	
	@RequestMapping("/contact.html")
	public String contact(String id,Model model){
		try {
			Articles articles=articlesService.queryarticlebyid(StringUtilsEX.ToInt(id));
			model.addAttribute("aritcles", articles);
			
		} catch (Exception e) {		
			e.printStackTrace();
			return "redirect:/404.html";
		}
		return "/template/pc/NavArticls/Contact";
	}
	
	
	
}
