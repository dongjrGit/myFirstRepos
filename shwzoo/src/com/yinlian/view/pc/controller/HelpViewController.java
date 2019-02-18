/*
 * @(#) HelpViewController.java 2016年8月15日
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

import com.yinlian.Enums.IndexFootEnum;
import com.yinlian.wssc.web.mapper.SnewsClassMapper;
import com.yinlian.wssc.web.mapper.SnewsMapper;
import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.ClassfyArticleService;
import com.yinlian.wssc.web.service.NavclassfyService;
import com.yinlian.wssc.web.service.NewsService;

@Controller
@RequestMapping("/web/help")
public class HelpViewController{
	@Autowired
	ArticlesService articlesService;
	@Autowired
	NavclassfyService navclassfyService;
	
	@Autowired
	ClassfyArticleService classfyArticleService;
	@Autowired
	SnewsClassMapper newsclassMapper;
	@Autowired
	SnewsMapper snewsMapper;
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/list.html")
	public String list(Integer classid,Integer pid,Model model){
		try {
			List<SnewsClass> newscleft=newsclassMapper.selectByEx1(IndexFootEnum.一级菜单.getValue(), 2);
			List<SnewsClass> newsclass=new ArrayList<SnewsClass>();
			if(pid!=null){
				SnewsClass nc=newsclassMapper.selectByPrimaryKey(newsclassMapper.selectByPrimaryKey(pid).getPid());
				newsclass=newsclassMapper.selectByPId(nc.getId(), 2);
				model.addAttribute("newsc", nc);
			}else{
				newsclass=newsclassMapper.selectByPId(classid, 2);
				SnewsClass nc=newsclassMapper.selectByPrimaryKey(classid);
				model.addAttribute("newsc", nc);
			}
			
			
			
			//List<NavclassfyDto> navlist=navclassfyService.querySon(dto.getParentid());
			//List<Articles> list=classfyArticleService.queryByClassfyId(dto.getId());
			
			model.addAttribute("newsclass", newsclass);
			
			model.addAttribute("newscleft", newscleft);
			return "/template/pc/helpcenter/help_list";
		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}
	/**
	 * 页脚点击进详情
	 * @param classid
	 * @param model
	 * @return
	 */
	@RequestMapping("/details.html")
	public String details(Integer classid,Integer ctype,Model model){
		try {
			List<SnewsClass> navlist=newsclassMapper.selectByPId(0, 2);//父分类集合
			List<SnewsWithBLOBs> news=new ArrayList<SnewsWithBLOBs>();
			if(ctype!=null){
				news=snewsMapper.selectByNewsCtype(ctype);
			}else{
				SnewsClass zdto= newsclassMapper.selectByPrimaryKey(classid);//子分类
				SnewsClass fdto= newsclassMapper.selectByPrimaryKey(zdto.getPid());
				news=snewsMapper.selectByNewsCid(classid);
				model.addAttribute("zvo", zdto);//子分类
				model.addAttribute("fvo",fdto);//父分类
			}
			model.addAttribute("navlist", navlist);//左边的下拉
			model.addAttribute("news",news);
			return "/template/pc/helpcenter/help_details";
		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}
	
	
	/**
	 * 底部详情
	 * @return
	 */
	@RequestMapping("/newsinfo.html")
	public String newsinfo(Integer ctype,Model model) {
		try {
			List<SnewsClass> newscleft=newsclassMapper.selectByEx1(IndexFootEnum.一级菜单.getValue(), 2);
			
			List<SnewsWithBLOBs> newslist=newsService.findByCType(ctype);
			if (newslist.size()>0) {
				model.addAttribute("vo", newslist.get(0));
			}
			model.addAttribute("newscleft", newscleft);
			return "/template/pc/helpcenter/news_info";
		} catch (Exception e) {
			return "redirect:/404.html";
		}
	}
	
}
