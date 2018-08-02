package com.mobile.application.controller.workbench;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.entity.TBasisMessage;
import com.mobile.application.service.workbench.IMessageService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	IMessageService messageService;

	/**
	 * 跳转到聊天页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpSession session, HttpServletRequest request) {
		return "workbench/message";
	}
	
	/**
	 * 查询未读消息
	 * 
	 * @param session
	 * @return
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping("/unread")
	public CommonVO qryUnreadMessage(HttpSession session, String sendUserId) {
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		CommonVO commonVO = messageService.qryUnreadMessage(sessionVO.getUserId(), sendUserId);
		return commonVO;
	}
	
	/**
	 * 保存消息
	 * 
	 * @param session
	 * @return
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping("/save")
	public CommonVO saveMessage(TBasisMessage bBasisMessage, boolean pushFlag, HttpSession session) {
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		CommonVO commonVO = messageService.saveMessage(bBasisMessage, pushFlag, sessionVO);
		return commonVO;
	}
	
	@ResponseBody
	@RequestMapping("/qryUsers")
	public CommonVO qryUsers(String userCode, String userName, String pageIndex, String pageSize){
		CommonVO commonVO = messageService.qryUsers(userCode, userName, pageIndex, pageSize);
		return commonVO;
	}
}
