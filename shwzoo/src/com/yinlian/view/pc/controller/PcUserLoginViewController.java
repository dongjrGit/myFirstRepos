/*
 * @(#) PcActivityViewController.java 2016年7月12日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.view.pc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.pc.dto.NavfyDto;
import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.CartService;
import com.yinlian.wssc.web.service.FloorService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.NavclassfyService;
import com.yinlian.wssc.web.service.SearchkeyService;
import com.yinlian.wssc.web.service.WebsitesConfigService;
import com.yinlian.wssc.web.util.SessionState;
import com.yl.soft.log.LogHandle;

@Controller

public class PcUserLoginViewController {

	
	@Autowired
	private FloorService flservice;
	@Autowired
	private NavclassfyService navservice;
	
	@Autowired
	private ArticlesService articlesService;
	@Autowired
	private WebsitesConfigService websitesConfigService; 
	
	@Autowired
	private MessageService   messageService;
	
	@Autowired
	private CartService     cartService;

	@Autowired
	private SearchkeyService searchkeyService;
	
	 /**
     * 显示登录
     */
    @RequestMapping("/web/user/showlogin")
    public String login(){
    	return "/template/pc/memberCenter/DlZc/login";
    }
    
    /**
     * 显示登录
     */
    @RequestMapping("/member/user/showlogin")
    public String sylogin(){
    	return "/template/pc/memberCenter/DlZc/UserLogin";
    }
    
    
    /**
     * 显示注册
     */
    @RequestMapping("/member/user/register")
    public String register(){
    	return "/template/pc/memberCenter/DlZc/Register";
    }
    
	@RequestMapping("/decorators/decoratorsMember.html")
	public String decoratorsMember(Model model,HttpServletRequest request){		
		try {
			SessionUser sessionUser =SessionState.GetCurrentUser();
			model.addAttribute("userinfo", sessionUser);
			List<NavclassfyDto> list=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "首页底部"); //网站资讯
			List<NavclassfyDto> artlist=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "关于我们"); //关于我们

			Integer messagecount=0;
			//用户登录获取消息数量
			if(sessionUser!=null && sessionUser.getCode()==0){
				messagecount=messageService.getCount(sessionUser.getUserId());
			}
			model.addAttribute("messagecount", messagecount);
			model.addAttribute("navsfw",list);
			model.addAttribute("navszx",artlist);
			List<NavclassfyDto> listkh=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "客户服务"); //客户服务
			List<NavfyDto> listdh=articlesService.getNavfyDto(4); //网站导航
			model.addAttribute("navskhfw",listkh);
			model.addAttribute("navswzdh",listdh);
			
//			model.addAttribute("webconfig",websitesConfigService.selectConfig());  //网站配置
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.pc, "获取买家首页内容错误：{0}", e, "/member/index");
			return "redirect:/404.html";
		}
		return "/template/pc/decorators/decoratorsMember";
	}
	
	
}
