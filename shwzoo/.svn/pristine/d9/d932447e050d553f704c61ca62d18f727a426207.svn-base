package com.yinlian.view.pc.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.SessionState;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/member/pcmessage")
public class PCMessageViewController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/messagelist", produces = "text/html;charset=UTF-8")
	public ModelAndView messagelist(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		try{
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if(sessionUser.getCode()!=0){
				view.setViewName("redirect:/member/user/showlogin");
                return view;
			}else{
				List<Messages> message= messageService.queryByUserId(sessionUser.getUserId());
				request.setAttribute("message", message);
				view.setViewName("/template/pc/memberCenter/KhFwmemb/MessageList");
				return view;
			}
		
		}catch(Exception e){
			LogHandle.debug(LogType.pc, MessageFormat.format("获取消息列表异常  异常信息:{0}", e.toString()),
					"message/messagelist");
			view.setViewName("redirect:/404.html");
			return view;
		}
		
	}
}
