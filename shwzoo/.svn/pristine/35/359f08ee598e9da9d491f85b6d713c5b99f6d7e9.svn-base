package com.techown.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.wssc.web.po.ZooMap;
import com.techown.wssc.web.service.ZooMapService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;

import data.ParseUtil;

@RestController
@RequestMapping("/api/app/zooMap")
public class ZooMapAppController {
	private static Logger logger = LoggerFactory.getLogger(ZooMapAppController.class);
	@Autowired
	private ZooMapService zooMapService;

	private static ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 获取地图图片
	 * 
	 * @param ch
	 * @param time
	 *            地图最新时间
	 * @param touristTime
	 *            导游图最新时间
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAppMap", produces = "text/html;charset=UTF-8")
	public String getAppMap(String ch, String time, String touristTime) {
		logger.info("call app-zooMap getAppMap request ch {} - time {} - touristTime {}", ch, time,
				touristTime);
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			List<ZooMap> list = zooMapService.queryMaxTime();
			List resplist = new ArrayList();
			for (ZooMap zooMap : list) {
				if (1 == zooMap.getType()) {
					if (StringUtils.isNotBlank(time)) {
						String tempTime1 = ParseUtil.parseDateToString(zooMap.getUpdateTime(),
								"yyyy-MM-dd HH:mm:ss");
						if (!time.equals(tempTime1)) {
							HashMap<String, Object> tempMap1 = zooMapService
									.queryLastMap(zooMap.getType(), 1);
							if (null != tempMap1) {
								tempMap1.put("updateTime", tempTime1);
								resplist.add(tempMap1);
							}
						}
					}
				}
				if (2 == zooMap.getType()) {
					if (StringUtils.isNotBlank(touristTime)) {
						String tempTime2 = ParseUtil.parseDateToString(zooMap.getUpdateTime(),
								"yyyy-MM-dd HH:mm:ss");
						if (!touristTime.equals(tempTime2)) {
							HashMap tempMap2 = zooMapService.queryLastMap(zooMap.getType(), 1);
							if (null != tempMap2) {
								tempMap2.put("updateTime", tempTime2);
								resplist.add(tempMap2);
							}
						}
					}
				}
			}
			item.setData(resplist);
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-zooMap getAppMap resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.info(
					"call app-zooMap getAppMap request ch {} - time {} - touristTime {} !\r\n resp {} !\r\n exception",
					ch, time, touristTime, result, e);
		}
		return result;
	}
}
