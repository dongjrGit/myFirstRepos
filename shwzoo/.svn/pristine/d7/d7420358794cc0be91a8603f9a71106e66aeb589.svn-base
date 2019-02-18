package com.yinlian.view.pc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.dto.BrowseHistoryDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/member/pcbrowsehistory")
public class BrowseHistoryViewController {
	@Autowired
	private   BrowsehistoryService   browsehistoryServise;
	
	@RequestMapping(value = "/queryhistory", produces = "text/html;charset=UTF-8")
	public ModelAndView queryghistory(HttpServletRequest request){
		ModelAndView view=new ModelAndView();
		try{
			SessionUser  sessionUser=SessionUtil.getSessionUser(request);
			if (sessionUser==null || sessionUser.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				List<BrowseHistoryDto> browsehistory=browsehistoryServise.queryDetailByUserId(sessionUser.getUserId());
				request.setAttribute("browsehistory",browsehistory);
			}
		}catch(Exception e){
			LogHandle.error(LogType.pc, "查询订单异常!", e,
					"pcbrowsehistory/queryhistory");
			view.setViewName("redirect:/404.html");
			return view;
			
		}
		view.setViewName("/template/pc/memberCenter/GzZx/BrowseHistory");
		return view;
	}
}
