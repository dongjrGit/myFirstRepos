package com.yinlian.api.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Feedback;
import com.yinlian.wssc.web.po.Feedbacktype;
import com.yinlian.wssc.web.service.FeedBackService;
import com.yinlian.wssc.web.service.FeedBackTypeService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/app/feedback")
public class FeedBackController {
	/**
	 * 日志输出的类
	 */
	//private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);
	
	@Autowired
	private   FeedBackService     feedBackService;
	
	@Autowired
	private  FeedBackTypeService   feedBackTypeService;
	/**
	 * 添加反馈
	 * @param token
	 * @param content
	 * @param type
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/addFeedBack", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addFeedBack(String token,String content,String type,String mobile,String ch) {
		BaseResult item = new BaseResult();
 		try {
 			if (StringUtilsEX.IsNullOrWhiteSpace(content)) {
 				item.setCode(-102);
 				item.setDesc("内容(content)不能为空！");
 				return item.toJson();
 			}
 			if (StringUtilsEX.IsNullOrWhiteSpace(type)) {
 				item.setCode(-103);
 				item.setDesc("反馈类型(type)不能为空！");
 				return item.toJson();
 			}
 			if(!StringUtilsEX.isChannelTypeExist(ch)){
 				item.setCode(-108);
 				item.setDesc("登录通道参数错误");
 				return item.toJson();
 			}
// 			if (StringUtilsEX.IsNullOrWhiteSpace(mobile)) {
// 				item.setCode(-104);
// 				item.setDesc("手机号或邮箱不能为空！");
// 				return item.toJson();
// 			}
// 			if (!mobile.matches("1\\d{10}")) {
// 				item.setCode(-118);
// 				item.setDesc("手机号(mobile)格式错误！");
// 				return item.toJson();
// 			}
 			boolean flag=false;
 			List<Feedbacktype> list=feedBackTypeService.selectAll();
 			if(list!=null&&list.size()>0){
 				for (int i = 0; i < list.size(); i++) {
 					if(list.get(i).getType().equals(StringUtilsEX.ToInt(type))){
 						flag=true;
 						break;
 					}
 				}
 				if(flag==false){
 					item.setCode(-107);
 					item.setDesc("该反馈类型不存在");
 					
 				}else{
 					SessionUser sessionUser=new SessionUser();
 					sessionUser=SessionState.GetCurrentUser(token);
 					if(sessionUser.getCode()!=0){
 						item.setCode(-401);
 						item.setDesc("请先登陆！");
 						return item.toJson();
 					}else{
 						int id=sessionUser.getUserId();
 						Feedback feedback=new Feedback();
 						feedback.setContent(content);
 						feedback.setType(StringUtilsEX.ToInt(type));
 						feedback.setCreattime(new Date());
 						feedback.setStatus(0);
 						feedback.setMobile(mobile);
 						feedback.setUserid(id);
 						int temp;
 						
 						temp = feedBackService.insert(feedback);
 						
 						if(temp==0){
 							item.setCode(-106);
 							item.setDesc("插入反馈失败");
 						}else{
 							item.setCode(0);
 							item.setDesc("添加反馈成功");
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
			LogHandle.error(LogType.Api, "添加反馈失败：{0}", e,"/feedback/addFeedBack");
		}
		return item.toJson();
	}
}
