package com.yinlian.api.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Pointsrecords;
import com.yinlian.wssc.web.service.PointsRecordService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/app/qiandao")
public class QianController {
	
	@Autowired
	private PointsRecordService pointsRecordService;
	
	/**
	 * 添加签到记录
	 * @return
	 */
	
	 @RequestMapping(value = "/pointRecordAdd", produces = "text/html;charset=UTF-8")
	public @ResponseBody String pointRecordAdd(String ch,String token){
		ReusltItem item = new ReusltItem();
		int result = 0;
		
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-103);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			SessionUser sessionUser = SessionState.GetCurrentUser(token);
			if(sessionUser.getCode()!=0){
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}else{
				List<Pointsrecords> list=pointsRecordService.selectByuser(sessionUser.getUserId());
				if(list!=null&&list.size()>0){
					item.setCode(-405);
					item.setDesc("今日已签到");
					return item.toJson();
				}
				result=pointsRecordService.addPoints(sessionUser.getUserId());
				if (result==0) {
					item.setCode(-200);
					item.setDesc("请先添加签到规则");
				}else {
					item.setCode(0);
					item.setDesc("签到成功");
				}
		}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
            LogHandle.error(LogType.Api,"添加签到信息异常! 异常信息:{0}", e,
                "qiandao/pointRecordAdd");
        }
        return item.toJson();
	}
	 
	 
	 /**
		 * 查询用户已签到
		 * @param userid
		 * @return
		 */
		@RequestMapping(value = "/queryQian",produces = "text/html;charset=UTF-8")
		public @ResponseBody String queryQian(String ch,String token){
			ReusltItem item = new ReusltItem();
			 
					try {
						/*if(StringUtilsEX.ToInt(userid)<0){
							item.setCode(-101);
				            item.setDesc("参数userid错误"+userid);
				            return item.toJson();
						}*/
						if (!StringUtilsEX.isChannelTypeExist(ch)) {
							item.setCode(-103);
							item.setDesc("登录通道(ch)不正确！");
							return item.toJson();
						}
						SessionUser sessionUser = SessionState.GetCurrentUser(token);
						
						if(sessionUser.getCode()!=0){
							item.setCode(-401);
							item.setDesc("用戶未登陆！");
						}else{
							List<Pointsrecords> list=pointsRecordService.selectByuserid(sessionUser.getUserId());
							item.setCode(0);
							item.setData(list);
							item.setDesc("查询成功");
						}
					} catch (Exception e) {
						if (DebugConfig.BLUETOOTH_DEBUG) {
							item.setDesc(e.toString());
						} else {
							item.setDesc("系统错误!");
						}
						item.setCode(-900);
						LogHandle.error(LogType.Api, "获取用户已签到信息出错! 异常信息:{0}",
								e, "qiandao/queryQian");
						
					}
			
			return item.toJson();
		} 
		
		/**
		 * 查询签到可得积分
		 * @param token
		 * @param ch
		 * @return
		 */
		@RequestMapping(value = "/getPoint",produces = "text/html;charset=UTF-8")
		public @ResponseBody String getPoint(String token,String ch){
			BaseResult item=new BaseResult();
			try {
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					item.setCode(-103);
					item.setDesc("登录通道(ch)不正确！");
					return item.toJson();
				}
				SessionUser user =SessionState.GetCurrentUser(token);
				if (user.getCode() != 0) {
					item.setCode(-401);
					item.setDesc("请先登录！");
					return item.toJson();
				}
				Integer points=pointsRecordService.getPoints(user.getUserId());
				item.setData(points);
			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(e.toString());
				} else {
					item.setDesc("系统错误!");
				}
				item.setCode(-900);
				LogHandle.error(LogType.Api,"查询签到可得积分出错! 异常信息:{0}",
						e, "qiandao/getPoint");
			}
			return item.toJson();
		}
		
		/**
		 * 连续签到次数
		 * @param token
		 * @param ch
		 * @return
		 */
		@RequestMapping(value = "/continousCount",produces = "text/html;charset=UTF-8")
		public @ResponseBody String continousCount(String token,String ch){
			BaseResult item=new BaseResult();
			try {
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					item.setCode(-103);
					item.setDesc("登录通道(ch)不正确！");
					return item.toJson();
				}
				SessionUser user =SessionState.GetCurrentUser(token);
				if (user.getCode() != 0) {
					item.setCode(-401);
					item.setDesc("请先登录！");
					return item.toJson();
				}
				
				int count=pointsRecordService.getContinousCount(user.getUserId());
				item.setData(count);
			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(e.toString());
				} else {
					item.setDesc("系统错误!");
				}
				item.setCode(-900);
				LogHandle.error(LogType.Api, "查询连续签到次数出错! 异常信息:{0}",
						e, "qiandao/continuousCount");
			}
			return item.toJson();
		}
		
		
}
