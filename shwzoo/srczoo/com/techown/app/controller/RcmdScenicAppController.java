package com.techown.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.wssc.web.service.RcmdScenicService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;

@RestController
@RequestMapping("/api/app/rcmdScenic")
public class RcmdScenicAppController {
	private static Logger logger = LoggerFactory.getLogger(RcmdScenicAppController.class);
	@Autowired
	private RcmdScenicService rcmdScenicService;

	private static ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 前端推荐景点接口
	 * 
	 * @param ch
	 *            渠道
	 * @param type
	 *            类型 1：剧场 ；2：动物 ；3：开心游乐
	 * @return
	 */
	@RequestMapping(value = "/getAppRcmdScenic", produces = "text/html;charset=UTF-8")
	public String getAppRcmdScenic(String ch, Integer type) {
		logger.info("call app-rcmdScenic getAppRcmdScenic request ch {} - type {}", ch, type);
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			item.setData(rcmdScenicService.getAppRcmdScenic(type));
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-rcmdScenic getAppRcmdScenic resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.info(
					"call app-rcmdScenic getAppRcmdScenic request ch {} - type {} !\r\n resp {} !\r\n exception",
					ch, type, result, e);
		}
		return result;
	}
}
