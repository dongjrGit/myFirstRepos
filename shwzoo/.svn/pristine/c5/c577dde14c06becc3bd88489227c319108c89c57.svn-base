package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

	@Controller
	@RequestMapping("/wap/messages")
	public class WapMessageViewController {

	@Autowired
	private MessageService messageService;

	@RequestMapping("/Message")
	public String Message() {
		//return "redirect:/wap/404.html";
		return "/template/wap/message/Messages";
	}

	@RequestMapping("/MessageDetail")
	public String MessageDetail() {
		//return "redirect:/wap/404.html";
		return "/template/wap/message/MessageDetail";
	}

	/**
	 * 查询用户的消息列表
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/queryMessages", produces = "text/html;charset=UTF-8")
	public ModelAndView queryMessages(String href,String ch, HttpServletRequest request) {
		
		ModelAndView view = new ModelAndView();
		ch="3";
		try {
			
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				view.setViewName("/template/wap/userinfo/login");
				view.setViewName("redirect:/wap/404.html");
                return view;
			} else {

				int userid = sessionUser.getUserId();
				// int userid=492;
				List<Messages> list = messageService.queryByUserId(userid);
				request.setAttribute("messages", list);
			}
			view.setViewName("/template/wap/message/Messages");
			request.setAttribute("href", href);
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(
					LogType.Api,
					MessageFormat.format("获取根据userid查询消息中心的信息出错! 异常信息:{0}",
							e.toString()), "messages/queryMessages");
			view.setViewName("/template/wap/index");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;

		}

	}

	/**
	 * 根据id查询消息详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryMessagesById", produces = "text/html;charset=UTF-8")
	public ModelAndView queryMessagesById(String id, String ch,
			HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		ModelAndView view = new ModelAndView();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				view.setViewName("/template/error/index.html");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				view.setViewName("/template/error/index.html");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
			Messages messages = messageService.queryById(StringUtilsEX
					.ToInt(id));
			if (messages != null) {
				request.setAttribute("message", messages);
				view.setViewName("/template/wap/message/MessageDetail");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
			view.setViewName("/template/wap/message/MessageDetail");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取根据id查询消息的信息出错：" + e.getMessage());
			LogHandle.error(
					LogType.Api,
					MessageFormat.format("获取根据id查询消息的信息出错! 异常信息:{0}",
							e.toString()), "messages/queryMessagesById");
			view.setViewName("/template/wap/index");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;
		}

	}
}
