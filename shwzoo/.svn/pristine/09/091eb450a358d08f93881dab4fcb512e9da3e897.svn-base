/*
 * @(#) WebsitesConfigViewController.java 2016年6月23日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.dto.WebsitesConfigDto;
import com.yinlian.wssc.web.service.WebsitesConfigService;
import com.yinlian.wssc.web.util.SessionUtil;

import data.ParseUtil;

@Controller
@RequestMapping("/platform/websitesConfigView")
public class WebsitesConfigViewController {
	
	@Autowired 
	WebsitesConfigService websitesConfigService;
	
	@RequestMapping("/index")
	public String index(){
		return "redirect:/platform/websitesConfigView/modify";
	}
	
	@RequestMapping("/modify")
	public String add(Model model,HttpServletRequest request) throws Exception{
		WebsitesConfigDto web=websitesConfigService.selectByUserId(ParseUtil.toInteger(SessionUtil.getSessionUser(request).getId()));
		model.addAttribute("vo",web );
		return "/platform/websitesConfig/websitesConfig_modify";
	}
	
}
