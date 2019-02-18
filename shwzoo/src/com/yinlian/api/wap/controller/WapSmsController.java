package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Smsrecord;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.SmsService;
import com.yinlian.wssc.web.util.ConfigUtil;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SmsUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/sms")
public class WapSmsController {
	 @Autowired
    private SmsService          smsService;
	 
		@Autowired
		private AccountsService accountsService;

    @RequestMapping(value = "/send", produces = "text/html;charset=UTF-8")
    public @ResponseBody String send(String ph,Integer smsType,String ch) {
        BaseResult item = new BaseResult();
        try {
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
            AccountsExample example = new AccountsExample();
			AccountsExample.Criteria criteria = example.createCriteria();
			criteria.andPhoneEqualTo(ph);
			criteria.andIsdelEqualTo(false);
			List<Accounts> result = accountsService.queryByMobile(example);
			if (result!=null && result.size() > 0) {
				item.setCode(-106);
				item.setDesc("该手机号(mobile)已被注册，你可以找回密码");
				return item.toJson();
			}
            String val = ProductNumUtil.getRandNum();
            String message="【中绿生活】您好,您的本次验证码为"+val;
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
			item.setDesc("获取验证码异常：" + e.getMessage());
			LogHandle.error(LogType.Api, MessageFormat.format("获取验证码异常! 异常信息:{0}",
					e.toString()), "sms/send");
		}
        
        return item.toJson();
    }
    @RequestMapping(value = "/sendMes", produces = "text/html;charset=UTF-8")
    public @ResponseBody String sendMes(String ph,Integer smsType,String ch) {
        BaseResult item = new BaseResult();
        try {
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
            AccountsExample example = new AccountsExample();
			AccountsExample.Criteria criteria = example.createCriteria();
			criteria.andPhoneEqualTo(ph);
			criteria.andIsdelEqualTo(false);
			List<Accounts> result = accountsService.queryByMobile(example);
			if (result==null || result.size() == 0) {
				item.setCode(-106);
				item.setDesc("该手机号未注册，请先注册");
				return item.toJson();
			}
            String val = ProductNumUtil.getRandNum();
            String message="【中绿生活】您好,您的本次验证码为"+val;
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
			item.setDesc("获取验证码异常：" + e.getMessage());
			LogHandle.error(LogType.wap, MessageFormat.format("获取验证码异常! 异常信息:{0}",
					e.toString()), "sms/sendMes");
		}
        
        return item.toJson();
    }
}
