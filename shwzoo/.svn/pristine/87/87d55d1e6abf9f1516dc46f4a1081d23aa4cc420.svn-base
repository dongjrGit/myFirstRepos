package com.techown.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.wssc.web.po.ScenicApp;
import com.techown.wssc.web.service.ScenicService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;

@RestController
@RequestMapping("/api/app/scenic")
public class ScenicAppController {

	private static Logger logger = LoggerFactory.getLogger(ScenicAppController.class);
	@Autowired
	private ScenicService scenicService;

	private static ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 载入时获取的初始数据（景点和景点类别信息）
	 * 
	 * @param ch
	 *            渠道
	 * @param state
	 *            状态
	 * @return
	 */
	@RequestMapping(value = "/getInitData", produces = "text/html;charset=UTF-8")
	public String getInitData(String ch, Integer state) {
		logger.info("call app-scenic getInitData request ch {} - state {}", ch, state);
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			state = (null == state) ? 1 : state;
			item.setData(scenicService.getInitData("PAD", state));
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-scenic getInitData resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error(
					"call app-scenic getInitData request ch {} - state {} !\r\n resp {} !\r\n exception",
					ch, state, result, e);
		}
		return result;
	}

	/**
	 * 获取景点详情信息
	 * 
	 * @param ch
	 *            渠道
	 * @param id
	 *            景点主键ID
	 * @return
	 */
	@RequestMapping(value = "/getScenicById", produces = "text/html;charset=UTF-8")
	public String getScenicById(String ch, Integer id) {
		logger.info("call app-scenic getScenicById request ch {} - id {}", ch, id);
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			ScenicApp scenicApp = scenicService.getAppScenicById(id, 1);
			if (null == scenicApp) {
				item.setCode(-200);
				item.setDesc("维护中");
			} else {
				item.setData(scenicApp);
			}
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-scenic getScenicById resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error(
					"call app-scenic getScenicById request ch {} - id {} !\r\n resp {} !\r\n exception",
					ch, id, result, e);
		}
		return result;
	}
}
