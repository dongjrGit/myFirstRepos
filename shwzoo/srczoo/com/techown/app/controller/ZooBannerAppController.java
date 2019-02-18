package com.techown.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.wssc.web.po.ZooBanner;
import com.techown.wssc.web.service.ZooBannerService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;

@RestController
@RequestMapping("/api/app/zooBanner")
public class ZooBannerAppController {
	private static Logger logger = LoggerFactory.getLogger(ZooBannerAppController.class);
	private static ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private ZooBannerService zooBannerService;

	/**
	 * 获取首页banner图信息
	 * 
	 * @param ch
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/getAppBanner", produces = "text/html;charset=UTF-8")
	public String getBanner(String ch, Integer status) {
		logger.info("call app-zooBanner getBanner request ch {} - state {}", ch, status);
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if (null == status) {
				status = 1;
			}
			List<ZooBanner> list = zooBannerService.getAppBanner(status);
			item.setData(list);
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-zooBanner getBanner resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error(
					"call app-zooBanner getBanner request ch {} - state {} !\r\n resp {} !\r\n exception",
					ch, status, result, e);
		}
		return result;
	}
}
