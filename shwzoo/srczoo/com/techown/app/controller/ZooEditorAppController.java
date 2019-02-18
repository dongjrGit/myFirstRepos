package com.techown.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.wssc.web.po.ZooEditor;
import com.techown.wssc.web.service.ZooEditorService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
@RestController
@RequestMapping("/api/app/editor")
public class ZooEditorAppController {
	private static Logger logger = LoggerFactory.getLogger(ZooEditorAppController.class);
	@Autowired
	private ZooEditorService zooEditorService;
	
	private static ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * 获取景点详情信息
	 * 
	 * @param ch
	 *            渠道
	 * @param id
	 *            景点主键ID
	 * @return
	 */
	@RequestMapping(value = "/getEditorById", produces = "text/html;charset=UTF-8")
	public String getEditorById(String ch, Integer id) {
		logger.info("call app-editor /getEditorById request ch {} - id {}", ch, id);
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			 ZooEditor bean =   zooEditorService.getById(id);
			if (null == bean || 0==bean.getDelstate()) {
				item.setCode(-200);
				item.setDesc("维护中");
			} else {
				item.setData(bean);
			}
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-editor getEditorById resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error(
					"call app-editor getEditorById request ch {} - id {} !\r\n resp {} !\r\n exception",
					ch, id, result, e);
		}
		return result;
	}
}
