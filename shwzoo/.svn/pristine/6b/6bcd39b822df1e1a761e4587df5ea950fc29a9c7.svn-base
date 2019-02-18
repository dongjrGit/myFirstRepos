package com.yinlian.api.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Smsrecord;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.service.SmsService;
import com.yinlian.wssc.web.util.ConfigUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SmsUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.ZooSmsUtil;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/app/sms")
public class SmsController {

    @Autowired
    private SmsService          smsService;
	 

    @RequestMapping(value = "/send", produces = "text/html;charset=UTF-8")
    public @ResponseBody String send(String ph,Integer smsType,String ch) {
        BaseResult item = new BaseResult();
        try {
        	if (StringUtilsEX.IsNullOrWhiteSpace(ph)) {
                item.setCode(-101);
                item.setDesc("手机号码(ph)不能为空！");
                return item.toJson();
            }
            if (!ph.matches("1\\d{10}")) {
    			item.setCode(-118);
    			item.setDesc("手机号(ph)格式错误！");
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
			/*List<Accounts> result = accountsService.queryByMobile(example);
			if (result!=null && result.size() > 0) {
				item.setCode(-106);
				item.setDesc("该手机号(mobile)已被注册，你可以找回密码");
				return item.toJson();
			}
*/
            String val = ProductNumUtil.getRandNum();
            String ret= ZooSmsUtil.sendMessage(ph, "您的验证码是"+ val+ "。请不要把验证码泄露给其他人。如非本人操作，请忽略此信息！");
            if(ret.equals("1")){
            	// SmsUtil.sendMessage(ph, "【因联商城】您的验证码是"+ val+ "。请不要把验证码泄露给其他人。如非本人操作，请忽略此信息！");
                RedisUserInfo.Set("I" + ph, val, ConfigUtil.get_instances().getSmsCodeTmeOut());
                //保存到数据库
                Smsrecord smsrecord = new Smsrecord();
                smsrecord.setPhone(ph);
                smsrecord.setContent(val);
                smsrecord.setCreateDate(new Date());
                smsrecord.setType(smsType);
                smsService.addSms(smsrecord);
               // item.setCode(0);
                item.setDesc("获取验证码成功");
            }else{
            	 item.setCode(-200);
            	 item.setDesc("获取验证码失败");
            }
           
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取验证码异常! 异常信息:{0}",
					e, "sms/send");
		}
        
        return item.toJson();
    }

}
