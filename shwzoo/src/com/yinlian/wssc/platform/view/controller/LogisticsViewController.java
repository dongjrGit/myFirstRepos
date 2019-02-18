package com.yinlian.wssc.platform.view.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.service.LogisticsService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/logistics")
public class LogisticsViewController {
	@Autowired
	private LogisticsService logisticsService;

	@RequestMapping("/list")
	public void list() {
		
	}

	@RequestMapping("/edit")
	public void edit(String id, Model model) {

		Integer id1 = StringUtilsEX.ToIntNull(id);
		if (id1 != null && id1 >= 0) {
			try {
				model.addAttribute("dto", logisticsService.getById(id1));
			} catch (Exception ex) {
				LogHandle.error(LogType.PlatformAdmin, MessageFormat.format("接收优惠卷信息异常! 异常信息:{0}", ex),
						"/platform/logistics/edit");
			}
		}

	}
}
