package com.yinlian.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.util.CriteriaMessage;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;
/**
 * 消息的控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/api/app/messages")
public class MessagesController {
	/**
	 * 日志输出的类
	 */
	//private static final Logger logger = LoggerFactory.getLogger(MessagesController.class);
	
	@Autowired
	private MessageService messageService;
	/**
	 * 查询用户的消息列表
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/queryMessages",produces = "text/html;charset=UTF-8")
	public @ResponseBody String queryMessages(String token,String ch,String page,String size){
		ReusltItem item = new ReusltItem();
		 
				try {
					if(!StringUtilsEX.isChannelTypeExist(ch)){
						item.setCode(-108);
						item.setDesc("登录通道参数错误");
						return item.toJson();
					}
					if(StringUtilsEX.ToInt(page)<=0 || StringUtilsEX.ToInt(size)<=0){
						item.setCode(-101);
						item.setDesc("分页参数错误！");
						return item.toJson();
					}
					SessionUser	sessionUser=SessionState.GetCurrentUser(token);
					if(sessionUser.getCode()!=0){
						item.setCode(-401);
						item.setDesc("用戶未登陆！");
					}else{
					CriteriaMessage criteria=new CriteriaMessage();
					criteria.setUserid(sessionUser.getUserId());
					criteria.setOrderByClause("sendtime");
					criteria.setSort("desc");
					PageBean pBean = messageService.getMessagesByUser(criteria,
							StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
						item.setCode(0);
						item.setData(pBean.getBeanList());
						item.setMaxRow(pBean.getTr());
						item.setPageIndex(pBean.getPc());
						item.setDesc("查询成功");	
					}
				} catch (Exception e) {
					item.setCode(-900);
					if (DebugConfig.BLUETOOTH_DEBUG) {
						item.setDesc(e.toString());
					} else {
						item.setDesc("系统错误!");
					}
					LogHandle.error(LogType.Api,"获取根据userid查询消息中心的信息出错! 异常信息:{0}",
							e, "messages/queryMessages");
					
				}
		
		return item.toJson();
	} 
	/**
	 * 根据id查询消息详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryMessagesById",produces = "text/html;charset=UTF-8")
	public @ResponseBody String queryMessagesById(String id,String ch){
		ReusltItem item = new ReusltItem();	
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("参数错误id"+id);
				return item.toJson();
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}			
			Messages messages = messageService.queryById(StringUtilsEX.ToInt(id));
			if (messages!= null) {
				item.setCode(0);
				item.setData(messages);
				item.setDesc("查询成功");
			}else {
				item.setCode(-200);
				item.setDesc("查询失败");				
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			LogHandle.error(LogType.Api,"获取根据id查询消息的信息出错! 异常信息:{0}",
					e, "messages/queryMessagesById");
		}
		return item.toJson();
	}
	/**
	 * 根据id删除对应的消息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteMessageById",produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleteMessageById(String id,String ch){
		ReusltItem item=new ReusltItem();
		try {
			if(StringUtilsEX.IsNullOrWhiteSpace(id)){
				item.setCode(-101);
				item.setDesc("消息id不能为空！");
				return item.toJson();
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
		
			int i=messageService.deleteById(StringUtilsEX.ToInt(id));
			if(i==0){
				item.setCode(-102);
				item.setDesc("该消息不存在");
			}else{
				item.setCode(0);
				item.setDesc("删除消息成功");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			LogHandle.error(LogType.Api,"根据id删除对应的消息出错! 异常信息:{0}",
					e, "messages/deleteMessageById");
		}
		return item.toJson();
	}
	
	/**
	 * 查询未读消息个数
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/getMessageCount",produces = "text/html;charset=UTF-8")
	public @ResponseBody String getMessageCount(String token,String ch){
		ReusltItem item = new ReusltItem();
		try {
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser	sessionUser=SessionState.GetCurrentUser(token);
			if(sessionUser.getCode()!=0){
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			int userid=sessionUser.getUserId();
			int count=messageService.getCount(userid);
			item.setMaxRow(count);
			
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"查询未读消息个数出错! 异常信息:{0}",
					e, "messages/getMessageCount");
		}
		return item.toJson();
	}
}
