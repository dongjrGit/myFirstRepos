package com.yinlian.api.app.controller;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.util.AES;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/upload")
public class LogController {
	@RequestMapping( value="/log",produces = "text/html;charset=UTF-8")
	public String log(String ch, String pwd, String c) {
		BaseResult item = new BaseResult();
		if(!StringUtilsEX.isChannelTypeExist(ch)){
			item.setCode(-108);
			item.setDesc("登录通道参数错误");
			return item.toJson();
		}
		try {
			ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						String str = AES.getInstance().decrypt(pwd);
						if (str == null) {
							LogHandle.error(LogType.Log, MessageFormat.format("msg:{0} ", "密码不正确"), "upload/log/err");
							return;
						}
						LogHandle.info(LogType.Log, c, "upload/log/" + ch);
					} catch (Exception e) {
						LogHandle.error(LogType.Log, "Rmsg:{0} ", e, "upload/log");
					}
				}
			});
			cachedThreadPool.shutdown();
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			LogHandle.error(LogType.Log, "msg:{0} ", e, "upload/log");
		}
		return item.toJson();
	}

}
