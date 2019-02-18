package com.yinlian.api.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.service.BanerService;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/api/banner")
public class BannerController {

	@Autowired
	private BanerService banerService;

	@RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
	public String list(String type) {

		BaseResult item = new BaseResult();
		try {
			item.setCode(0);
			item.setData(banerService.getList());
		} catch (Exception e) {
			item.setCode(-900);
			LogHandle.error(LogType.baner, e, "/api/banner/list");
		}
		return item.toJson();
	}
}
