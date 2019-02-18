/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.HistoryService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 经彩豆控制类
 * @author admin
 *
 */
@RestController
@RequestMapping("/api/app/colourbeans")
public class ColourbeansController {
  
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HistoryService        historyService;
	

	/**
	 * 查询用户的可用经彩豆
	 * @param ch
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/totalBeans", produces = "text/html;charset=UTF-8")
	public String totalBeans(String token,String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser user =SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录！");
				return item.toJson();
			}
			Users  users=userService.queryById(user.getUserId());
		    int currentbeans = 0;

            if (users != null &&users.getAvailablebeans()!=null) {
            	currentbeans = users.getAvailablebeans();
            }
            
            item.setCode(0);
            item.setData(currentbeans);
           
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"查询可用经彩豆异常! 异常信息:{0}", e,
					"colourbeans/totalBeans");
		}
		return item.toJson();
	}

	
	/**
	 * 积分兑换经彩豆
	 * @param ch
	 * @param token
	 * @param point
	 * @return
	 */
	@RequestMapping(value = "/exchangeBeans", produces = "text/html;charset=UTF-8")
	public String exchangeBeans(String token, String ch,String point) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if(StringUtilsEX.ToInt(point)<0){
				item.setCode(-104);
				item.setData("积分参数错误");
				return item.toJson();
			}
			
			SessionUser user =SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录！");
				return item.toJson();
			}
			/*
			Configdictionary configdictionary=configSetService.showConfigSetByType(ConfigSetTypeEnum.积分兑换经采豆.getValue());
			String _value=configdictionary.getValue();
			String _key=configdictionary.getKeyword();*/
			Users users=userService.queryById(user.getUserId());
			//查询用户可用积分
			int _points=0;
			if(users!=null&&users.getPoints()!=null){
				_points=users.getPoints();
			}
			
			
			if(StringUtilsEX.ToInt(point)>_points){
				item.setCode(-101);
				item.setDesc("兑换积分少于实际可用积分");
				return item.toJson();
			}
			
			/*int value=StringUtilsEX.ToInt(_value);
			int key=StringUtilsEX.ToInt(_key);*/
			int points=StringUtilsEX.ToInt(point);
			
			if(points<1000){
				item.setCode(-103);
				item.setDesc("兑换积分不足不能兑换,1000积分兑换一个经彩豆");
			}else{
				//积分能兑换的经彩豆个数
				int beans=points/1000;
				
				//没有兑换完的积分
				int remainPoint=points%1000;
				
				int userid=user.getUserId();
				
				int temp=historyService.addexchangePoint(userid, points, beans,remainPoint);
				if(temp>0){
					item.setCode(0);
					item.setDesc("积分兑换成功");
				}else{
					item.setCode(-102);
					item.setDesc("兑换失败");
				}
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"积分兑换经彩豆异常! 异常信息:{0}", e,
					"colourbeans/exchangeBeans");
		}
		
		return item.toJson();
	}
}
