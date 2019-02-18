/*
 * @(#) PcActivityViewController.java 2016年7月12日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.SexEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Smsrecord;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AcctountNoService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.SmsService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.ConfigUtil;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.JsonUtil;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.SmsUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/user")
public class PcUserLoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SmsService   smsService;

	@Autowired
	private AccountsService  accountsService;
	
	@Autowired
	private AcctountNoService  acctountNoService;

	@Autowired
	private UserslevelService  userslevelService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;
	/**
	 * 会员登录
	 * 
	 * @param name
	 * @param pwd
	 * @param ch
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	public  String Login(String name, String pwd, String ch, String code,
			HttpServletResponse response) {
		BaseResult item = new BaseResult();
		try {
			item.setCode(0);
			String token = UUID.randomUUID().toString();
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-102);
				item.setDesc("登录名不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(pwd)) {
				item.setCode(-103);
				item.setDesc("密码不能为空！");
				return item.toJson();
			}

			//String userInfo = "";
			Object[] rls = userService.login(name, pwd,
					UserTypeEnum.Buyer.getValue());
			switch (Integer.parseInt(rls[0].toString())) {
			case 0:
				//userInfo = JsonUtil.getJsonStringFromObject(rls[1]);
				break;
			case -1:
				item.setCode(-104);
				item.setDesc("登录失败,用户名或密码错误！");
				return item.toJson();
			case -2:
				item.setCode(-105);
				item.setDesc("登录失败,账户被锁定！");
				return item.toJson();
			case -3:
				item.setCode(-106);
				item.setDesc("登录失败,账户异常！");
				return item.toJson();
			default:
				item.setCode(-104);
				item.setDesc("登录失败,用户名或密码错误！");
				return item.toJson();
			}
			SessionUser sessionUser = (SessionUser) rls[1];
			SessionState.SetSessionUser(token, sessionUser);
			Users users = userService.queryById(sessionUser.getUserId());
			item.setCode(0);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Token", token);
			map.put("Channel", ch);
			map.put("sessionUser", sessionUser);
			map.put("UserID", sessionUser.getId());
			map.put("UserName", sessionUser.getLoginName());
			map.put("Mobile", users.getMobile());
			map.put("Email", users.getEmail());
			item.setData(JsonUtil.getJsonStringFromMap(map));
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("会员登录异常！");
			}			
			LogHandle.error(LogType.pc,
					MessageFormat.format("会员登录异常! 异常信息:{0}", e.getMessage()),
					"user/login");
		}
		return item.toJson();
	}
	
	
	 @RequestMapping(value = "/send", produces = "text/html;charset=UTF-8")
	    public  String send(String ph,Integer smsType,String ch, String imgcode) {
	        BaseResult item = new BaseResult();
	        try {
	        	String vCode = RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
	            if (!vCode.equalsIgnoreCase(imgcode)) {
	                item.setCode(-102);
	                item.setDesc("图片验证码错误！");
	                return item.toJson();
	            }
	        	if (StringUtilsEX.IsNullOrWhiteSpace(ph)) {
	                item.setCode(-101);
	                item.setDesc("电话号码不能为空！");
	                return item.toJson();
	            }
	            if (!ph.matches("1\\d{10}")) {
	    			item.setCode(-118);
	    			item.setDesc("手机号格式错误！");
	    			return item.toJson();
	    		}
	            if(!StringUtilsEX.isChannelTypeExist(ch)){
	    			item.setCode(-108);
	    			item.setDesc("登录通道参数错误");
	    			return item.toJson();
	    		}
	       /*     AccountsExample example = new AccountsExample();
				AccountsExample.Criteria criteria = example.createCriteria();
				criteria.andPhoneEqualTo(ph);
				criteria.andIsdelEqualTo(false);
				List<Accounts> result = accountsService.queryByMobile(example);
				if (result!=null && result.size() > 0) {
					item.setCode(-106);
					item.setDesc("该手机号(mobile)已被注册，你可以找回密码");
					return item.toJson();
				}*/
	            String val = ProductNumUtil.getRandNum();
	            String message="【中绿生活】您好,您的本次验证码为 "+val;
	            SmsUtil.sendMessage(ph, message);
	            RedisUserInfo.Set("I" + ph, val, ConfigUtil.get_instances().getSmsCodeTmeOut());
	            item.setData(val);
	            //保存到数据库
	            Smsrecord smsrecord = new Smsrecord();
	            smsrecord.setPhone(ph);
	            smsrecord.setContent(val);
	            smsrecord.setCreateDate(new Date());
	            smsrecord.setType(smsType);
	            smsService.addSms(smsrecord);
	            item.setCode(0);
	            item.setData(smsrecord);
	            item.setDesc("获取验证码成功");
			} catch (Exception e) {
				item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(e.toString());
				} else {
					item.setDesc("获取验证码失败");
				}
				LogHandle.error(LogType.pc, MessageFormat.format("获取验证码异常! 异常信息:{0}",
						e.toString()), "/user/send");
			}
	        
	        return item.toJson();
	    }
	
	 /**
		 * 账号注册
		 * 
		 * @param mobile
		 * @param sms
		 * @param password
		 * @return
		 */
		@RequestMapping(value = "/register", produces = "text/html;charset=UTF-8")
		public String register(String typevalue, String username, String pwd,
                String rpwd, String pcode,String sex, String birthyear,
    			String birthmonth, String birthday,
    			String provincecode, String provincename, String citycode,
    			String cityname, String areacode, String areaname, String address) {
			BaseResult item = new BaseResult();
			try {	
				 if (!StringUtils.isNotNull(username)) {
		                item.setCode(-101);
		                item.setDesc("用户名不能为空");
		                return item.toJson();
		            }
		            if (!StringUtils.isNotNull(pwd)) {
		                item.setCode(-102);
		                item.setDesc("密码不能为空");
		                return item.toJson();
		            }
		            if (!StringUtils.isNotNull(rpwd)) {
		                item.setCode(-103);
		                item.setDesc("确认密码不能为空");
		                return item.toJson();
		            }
		            if (!pwd.equalsIgnoreCase(rpwd)) {
		                item.setCode(-104);
		                item.setDesc("两次密码不一致");
		                return item.toJson();
		            }
		            if (!StringUtils.isNotNull(typevalue)) {
		                item.setCode(-105);
		                item.setDesc("电话不能为空");
		                return item.toJson();
		            }
		            if (!StringUtils.isNotNull(pcode)) {
		                item.setCode(-105);
		                item.setDesc("短信验证码不能为空");
		                return item.toJson();
		            }

		            // 验证短信验证码
		            String smss = RedisUserInfo.Get("I" + typevalue);
		            if (!pcode.equals(smss)) {
		                item.setCode(-106);
		                item.setDesc("短信验证码错误");
		                return item.toJson();
		            }

				AccountsExample example = new AccountsExample();
				AccountsExample.Criteria criteria = example.createCriteria();
				// criteria.andUsertypeEqualTo(UserTypeEnum.Buyer.getValue());
				criteria.andPhoneEqualTo(typevalue);
				criteria.andIsdelEqualTo(false);
				List<Accounts> result = accountsService.queryByMobile(example);
				if (result.size() > 0) {
					item.setCode(-106);
					item.setDesc("该手机号(mobile)已被注册，你可以找回密码");
					return item.toJson();
				}
				Userslevel userlevel = userslevelService.getlowerLevel();

				if (userlevel == null) {
					item.setCode(-105);
					item.setDesc("用户等级不存在！");
					return item.toJson();
				}
				//String no = acctountNoService.getNo();
				int temp = accountsService
						.addMenber(UserTypeEnum.Buyer.getValue(),username,"","",
								"", pwd, typevalue, "", userlevel.getId(),"","",
								"","","","",provincename,"",cityname,"",areaname,"");
				if (temp > 0) {
					item.setData(temp);
					item.setCode(0);
					item.setDesc("用户注册成功");
					  ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {																	
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), 0,username, "register.html", "/pc/user/register", "用户注册成功");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"用户注册记录错误:",
											e, "/pc/user/register");
								}
							}
						});
				}else{
					item.setCode(-200);
					if(temp==-200){
						item.setDesc("系统未设置用户等级");
					}else{				
					item.setDesc("账号注册失败");}
				}

			} catch (Exception e) {
				item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(e.toString());
				} else {
					item.setDesc("账号注册异常");
				}
				LogHandle.error(LogType.pc,
						MessageFormat.format("账号注册异常! 异常信息:{0}", e.getMessage()),
						"user/register");
			}

			return item.toJson();
		}
		    
	 
	 
	@RequestMapping(value = "/isLogins",produces = "text/html;charset=UTF-8")
	public  String isLogins(HttpServletRequest request){
		BaseResult item=new BaseResult();
		try {
			SessionUser user =SessionUtil.getSessionUser(request);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录！");
				return item.getCode()+"";
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("查询登录出错");
			}
			LogHandle.error(LogType.pc, MessageFormat.format("查询登录出错! 异常信息:{0}",
					e.toString()), "user/isLogin");
		}
		return item.getCode()+"";
	} 
    
		
}
