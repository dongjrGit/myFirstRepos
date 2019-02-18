package com.yinlian.api.wap.controller;

import java.util.Date;
import java.util.List;
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
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Feedback;
import com.yinlian.wssc.web.po.Feedbacktype;
import com.yinlian.wssc.web.service.FeedBackService;
import com.yinlian.wssc.web.service.FeedBackTypeService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping(value = "/api/wap/feedback")
public class WapFeedBackController {

	@Autowired
	private FeedBackService feedBackService;

	@Autowired
	private FeedBackTypeService feedBackTypeService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;

	/**
	 * 添加反馈
	 * 
	 * @param token
	 * @param content
	 * @param type
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/addFeedBack", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addFeedBack(String content, String type,
			String mobile, String ch, HttpServletRequest request) {
		ch = "3";
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(content)) {
				item.setCode(-102);
				item.setDesc("内容不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(type)) {
				item.setCode(-103);
				item.setDesc("反馈类型不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
				item.setCode(-104);
				item.setDesc("手机号不能为空！");
				return item.toJson();
			}
			if (!mobile.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			boolean flag = false;
			List<Feedbacktype> list = feedBackTypeService.selectAll();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getType() == StringUtilsEX.ToInt(type)) {
						flag = true;
						break;
					}
				}
				if (flag == false) {
					item.setCode(-107);
					item.setDesc("该反馈类型不存在");
				} else {
					SessionUser sessionUser = new SessionUser();
					String token = CookieUtils.getTokenFromCookie(request);
					sessionUser = SessionState.GetCurrentUser(token);
					if (sessionUser.getCode() != 0) {
						item.setCode(-401);
						item.setDesc("请先登陆！");
					} else {
						int id = sessionUser.getUserId();
						// int id =492;
						Feedback feedback = new Feedback();
						feedback.setContent(content);
						feedback.setType(StringUtilsEX.ToInt(type));
						feedback.setCreattime(new Date());
						feedback.setStatus(0);
						feedback.setMobile(mobile);
						feedback.setUserid(id);
						int temp;

						temp = feedBackService.insert(feedback);
						if (temp == 0) {
							item.setCode(-106);
							item.setDesc("插入反馈失败");
						} else {
							item.setCode(0);
							item.setDesc("添加反馈成功");
							ExecutorService executorService=Executors.newCachedThreadPool();
							executorService.execute(new Runnable() {
								@Override
								public void run() {
									try {
										SessionUser user=SessionState.GetCurrentUser();
										operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "FeedbackIcons.html", "/api/wap/feedback/addFeedBack", "插入反馈");
									} catch (Exception e) {
										LogHandle.error(LogType.OperateRecords,"插入反馈 异常信息:",
			    								e, "/api/wap/feedback/addFeedBack");
									}
								}
							});
						}
					}
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"添加反馈失败：{0}", e);
		}
		return item.toJson();
	}
}
