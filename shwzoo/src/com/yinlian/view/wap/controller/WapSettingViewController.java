package com.yinlian.view.wap.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/wap/showsetting")
public class WapSettingViewController {

	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/setting")
	public ModelAndView setting() {
		/*ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/wap/404.html");
		return model;*/
		return new ModelAndView("/template/wap/setting/Setting");
	}

	@RequestMapping("/help")
	public ModelAndView help() {
		/*ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/wap/404.html");
		return model;*/
		return new ModelAndView("/template/wap/help/Help");
	}

	@RequestMapping("/agreement")
	public ModelAndView agreement() {
		/*ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/wap/404.html");
		return model;*/
		return new ModelAndView("/template/wap/agreement/UserAgreement");
	}

	@RequestMapping("/appagreement")
	public ModelAndView appagreement(HttpServletRequest request,Integer mark) throws Exception {
		List<SnewsWithBLOBs> newslist=new ArrayList<SnewsWithBLOBs>();
		if(mark==null||mark==1){
			newslist=newsService.findByCType(213);
		}else{
			newslist=newsService.findByCType(214);
		}
		
		request.setAttribute("mark",mark);
		request.setAttribute("newslist", newslist.get(0));
		/*ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/wap/404.html");
		return model;*/
		return new ModelAndView("/template/app/setting/settingDetail");
	}

	@RequestMapping("/appintroduce")
	public ModelAndView appintroduce() {
		/*ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/wap/404.html");
		return model;*/
		return new ModelAndView("/template/wap/appintroduce/AppIntroduce");
	}

	@RequestMapping("/UserAgreement")
	public ModelAndView UserAgreement(String type, HttpServletRequest request) {
		request.setAttribute("type", type);
		SnewsWithBLOBs swb = newsService.selSingle(StringUtilsEX.ToInt(type));
		request.setAttribute("vo", swb);
		return new ModelAndView("/template/wap/setting/UserAgreement");
	}

	@RequestMapping("/aboutus")
	public ModelAndView aboutus(String type, HttpServletRequest request) {
		request.setAttribute("type", type);
		SnewsWithBLOBs swb = newsService.selSingle(StringUtilsEX.ToInt(type));
		request.setAttribute("vo", swb);
		return new ModelAndView("/template/wap/setting/AboutUs");
	}
	@RequestMapping("/invitefriend")
	public ModelAndView invitefriend() {
		/*ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/wap/404.html");
		return model;*/
		return new ModelAndView("/template/wap/invitefriend/InviteFriend");
	}

	@RequestMapping("/changcity")
	public ModelAndView changcity(int type, HttpServletRequest request) {
		String hometown = "";
		if (type == 1) {
			hometown = "hometown";
			request.setAttribute("changcity", hometown);
		} else {
			hometown = "location";
			request.setAttribute("changcity", hometown);
		}
		/*ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/wap/404.html");
		return model;*/
		return new ModelAndView("/template/wap/userinfo/changcity");
	}
}
