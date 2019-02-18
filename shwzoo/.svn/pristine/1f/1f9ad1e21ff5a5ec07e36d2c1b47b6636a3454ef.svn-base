package com.yinlian.api.wap.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/messages")
public class WapMessageController {
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;

	/**
	 * 根据id删除对应的消息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteMessageById", produces = "text/html;charset=UTF-8")
	public @ResponseBody String deleteMessageById(String id, String ch) {
		ch = "3";
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-101);
				item.setDesc("消息id不能为空！");
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}

			int i = messageService.deleteById(StringUtilsEX.ToInt(id));
			if (i == 0) {
				item.setCode(-102);
				item.setDesc("该消息不存在");
			} else {
				item.setCode(0);
				item.setDesc("删除消息成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "message.html", "/api/wap/messages/deleteMessageById", "删除消息");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"删除消息 异常信息:",
    								e, "/api/wap/messages/deleteMessageById");
						}
					}
				});
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(
					LogType.wap,"根据id删除对应的消息出错! 异常信息:{0}",
							e, "messages/deleteMessageById");
		}
		return item.toJson();
	}

	/**
	 * 查询未读消息个数
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/getMessageCount", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getMessageCount(String ch,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser  sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			//SessionUser sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			int userid = sessionUser.getUserId();
			int count = messageService.getCount(userid);
			item.setData(count);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"查询未读消息个数出错! 异常信息:{0}", e,
					"messages/getMessageCount");
		}
		return item.toJson();
	}
}
