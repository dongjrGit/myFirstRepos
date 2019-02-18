package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.CriteriaMessage;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/pcmessage")
public class PCMessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;
	
	@RequestMapping("/messagelist")
	public ReusltItem messagelist(HttpServletRequest request,String page,String pagesize){
		ReusltItem item = new ReusltItem();
		try{
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
				return item;
			}
			int index=1,size=10;
			CriteriaMessage criteria=new CriteriaMessage();
			if(StringUtilsEX.ToInt(page)>0){
				index=StringUtilsEX.ToInt(page);
			}
			if(StringUtilsEX.ToInt(pagesize)>0){
				size=StringUtilsEX.ToInt(pagesize);
			}
			criteria.setUserid(sessionUser.getUserId());
			criteria.setOrderByClause(" SendTime ");
			criteria.setSort(" desc ");
			PageBean bean=messageService.selectMessagesByUserIdPage(criteria, index, size);
			item.setPage(bean.getTp());
			item.setMaxRow(bean.getTr());
			item.setPageIndex(bean.getPc());
			item.setPageSize(bean.getPs());
			item.setData(bean.getBeanList());
			return item;
		}catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.debug(LogType.pc,MessageFormat.format("获取未读消息数量异常  异常信息:{0}", e.toString()),
					"pcmessage/messagelist");
		}
		return item;
	}
	
	@RequestMapping("/messagecount")
	public String messagecount(HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try{
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
				return item.toJson();
			} else {
				item.setData(messageService.getCount(sessionUser.getUserId()));
				item.setCode(0);
				item.setDesc("获取未读消息数量成功");
			}
			
		}catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.debug(LogType.pc,MessageFormat.format("获取未读消息数量异常  异常信息:{0}", e.toString()),
					"pcmessage/messagecount");
		}
		return item.toJson();
	}
	
	
	
	/**
	 * 根据id删除对应的消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteMessageById",produces = "text/html;charset=UTF-8")
	public  String deleteMessageById(String id,String ids,HttpServletRequest request){
		ReusltItem item=new ReusltItem();
		try {
			if(StringUtilsEX.IsNullOrWhiteSpace(id)){
				item.setCode(-101);
				item.setDesc("消息id(id)不能为空！");
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
				return item.toJson();
			} else {
			int i=0;
			
			if(StringUtilsEX.ToInt(id)>0){
				i=messageService.deleteById(StringUtilsEX.ToInt(id));
			}else{
				i=messageService.deleteByIds(ids);
			}
			
			
			if(i==0){
				item.setCode(-102);
				item.setDesc("该消息不存在");
			}else{
				item.setCode(0);
				item.setDesc("删除消息成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "MessageList.html", "/pc/pcmessage/deleteMessageById", "删除消息");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"删除消息 异常信息:",
    								e, "/pc/pcmessage/deleteMessageById");
						}
					}
				});
			}
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("根据id删除对应的消息出错! 异常信息:{0}",
					e.toString()), "pcmessages/deleteMessageById");
		}
		return item.toJson();
	}
	
	/**
	 * 根据id删除对应的消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletemessagebyuserId",produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleteMessageByUserId(HttpServletRequest request){
		ReusltItem item=new ReusltItem();
		try {
			
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
				return item.toJson();
			} else {
			int i=messageService.deleteByUserid(sessionUser.getUserId());
			if(i==0){
				item.setCode(-102);
				item.setDesc("该消息不存在");
			}else{
				item.setCode(0);
				item.setDesc("清空消息成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "MessageList.html", "/pc/pcmessage/deletemessagebyuserId", "清空消息");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"清空消息 异常信息:",
    								e, "/pcmessage/deletemessagebyuserId");
						}
					}
				});
			}
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("根据用户id删除对应的消息出错! 异常信息:{0}",
					e.toString()), "pcmessages/deleteMessageByUserId");
		}
		return item.toJson();
	}
	/**
	 * 设置消息已读(status=0为已读，1未读)
	 * @return
	 */
	@RequestMapping(value = "/setReadMessage",produces = "text/html;charset=UTF-8")
	public @ResponseBody String setReadMessage(HttpServletRequest request){
		ReusltItem item=new ReusltItem();
		try {
			
			SessionUser sessionUser = new SessionUser();			
			sessionUser = SessionState.GetCurrentUser();
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
				return item.toJson();			 
			}
			item.setCode(0);
			int result=messageService.updateSetMes(sessionUser.getUserId());			
			if(result<0){
				item.setCode(-201);
				item.setDesc("设置消息已读失败！");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("设置用户消息已读出错! 异常信息:{0}",
					e.toString()), "pcmessages/setReadMessage");
		}
		return item.toJson();
	}
}
