package com.techown.wssc.platform.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.wssc.web.po.RcmdScenic;
import com.techown.wssc.web.service.RcmdScenicService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.util.SessionState;

@Controller
@RequestMapping("/zoo/rcmdScenic")
public class RcmdScenicController {
	private static Logger logger = LoggerFactory.getLogger(RcmdScenicController.class);
	
	@Autowired
	private RcmdScenicService rcmdScenicService;

	/**
	 * JSON 转换
	 */
	private static ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@RequestMapping("/querylist")
	@ResponseBody
	public ReusltItem querylist(String name, String type, Integer state) {
		logger.info("querylist request name="+name+"!type="+type+"!state="+state);
		ReusltItem item = new ReusltItem();
		try {
			List<RcmdScenic> list = rcmdScenicService.querylist(name, type, state);
			item.setCode(0);
			item.setData(list);
			item.setMaxRow(list.size());
		} catch (Exception e) {
			logger.error("call querylist exception request name="+name+"!type="+type+"!state="+state, e);
			item.setCode(-100);
		}
		return item;
	}

	/**
	 * 保存和编辑景点设施
	 * 
	 * @param data
	 *            编辑信息的json
	 * @return
	 */
	@RequestMapping("/editlist")
	@ResponseBody
	public ReusltItem editslist(String data) {
		logger.info("editslist request data=" + data);
		ReusltItem item = new ReusltItem();
		RcmdScenic rcmdScenic = null;
		try {
			rcmdScenic = MAPPER.readValue(data, RcmdScenic.class);

			if (null == rcmdScenic.getId()) {
				Date date = new Date();
				rcmdScenic.setCreatetime(date);
				rcmdScenic.setUpdatetime(date);
				rcmdScenic.setOperator(SessionState.GetCurrentUser().getLoginName());
				rcmdScenicService.addRcmdScenic(rcmdScenic);
			} else {
				rcmdScenic.setUpdatetime(new Date());
				rcmdScenic.setOperator(SessionState.GetCurrentUser().getLoginName());
				rcmdScenicService.updateRcmdScenic(rcmdScenic);
			}
			item.setCode(0);
		} catch (Exception e) {
			logger.error("call editslist exception request data=" + data, e);
			item.setCode(100);
		}
		return item;
	}

}
