package com.techown.app.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.util.StsUtil;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;

@RestController
@RequestMapping("/api/app/sts")
public class StsAppController {
	private static Logger logger = LoggerFactory.getLogger(RcmdScenicAppController.class);

	private static ObjectMapper MAPPER = new ObjectMapper();

	@RequestMapping(value = "/getStsToken", produces = "text/html;charset=UTF-8")
	public String getStsToken(String ch, String token) {
		logger.info("call app-sts getStsToken request ch {}", ch);
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}

			SessionUser sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}

			Map<String, String> map = StsUtil.assumeRole();
			if (null == map) {
				item.setCode(500);
				item.setDesc("系统错误！");
			} else {
				item.setData(map);
			}
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-sts getStsToken resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.info(
					"call app-rcmdScenic getAppRcmdScenic request ch {} !\r\n resp {} !\r\n exception",
					ch, result, e);
		}
		return result;
	}
}
