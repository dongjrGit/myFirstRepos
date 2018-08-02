package com.mobile.application.controller.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.service.notice.INoticeService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private INoticeService noticeService;
	
	@RequestMapping("/init")
	public String init(){
		return "notice/notice_list";
	}
	
	@RequestMapping("/queryNotice")
	@ResponseBody
	public Map<String,Object> queryNotice(HttpSession session,int pageIndex,int pageSize,String orgId ,String startTime,String endTime,String userCode,String userName,String noticeType,HttpServletRequest request){
		return noticeService.queryNotice(session,pageIndex,pageSize,startTime,endTime,userCode,userName,noticeType,orgId,request);
	}
	
	@RequestMapping("/noticeAddInit")
	public ModelAndView noticeAddInit(String noticeType){
		Map<String, String> noticeTypeMap = new HashMap<String, String>();
		noticeTypeMap.put("noticeType", noticeType);
		return new ModelAndView("notice/new_notice", noticeTypeMap);
	}
	
	@RequestMapping("/orgSelecter")
	public String selectOrgTreeWindow(){
		return "notice/org_selecter";
	}
	
	@RequestMapping("/roleSelecter")
	public String selectRoleWindow(){
		return "notice/role_selecter";
	}
	
	@RequestMapping("/userSelecter")
	public String selectUserWindow(){
		return "notice/user_selecter";
	}
	
	@RequestMapping("/queryRoleByOrg")
	@ResponseBody
	public Map<String,Object> queryRoleByOrg(int pageIndex,int pageSize,String orgIds){
		return this.noticeService.queryRoleByOrg(pageIndex,pageSize,orgIds);
	}
	
	@RequestMapping("/queryUserByRole")
	@ResponseBody
	public CommonVO queryUserByRole(int pageIndex,int pageSize,String orgIds, String roleIds, String userName, String userCode){
		return this.noticeService.queryUserByRole(pageIndex, pageSize, orgIds, roleIds, userName, userCode);
	}
	
	@RequestMapping("/saveNotice")
	@ResponseBody
	public CommonVO saveNotice(HttpSession session, String receiveUser, String receiveOrg, String receiveRole, String noticeTitle, String textArea){
		return this.noticeService.saveNotice(session, receiveUser, receiveOrg, receiveRole, noticeTitle, textArea);
	}
	
	@RequestMapping("/queryNoticeDetail")
	@ResponseBody
	public Map<String,String> queryNoticeDetail(String noticeId){
		return this.noticeService.queryNoticeDetail(noticeId);
	}
	
	@RequestMapping("/queryAllNotice")
	@ResponseBody
	public List<?> queryAllNotice(HttpSession session){
		List<?> list = noticeService.queryAllNotice(session);
		session.setAttribute("noticeList", list);
		return list;
	}
	
}
