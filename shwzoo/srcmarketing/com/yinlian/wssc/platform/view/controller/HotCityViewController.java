package com.yinlian.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.po.Hotcity;
import com.yinlian.wssc.web.service.HotCityService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/platform/rmcs")
public class HotCityViewController {

	@Autowired
	private HotCityService hotCityService;

	/**
	 * 获取热门城市列表
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "/platform/hotcity/list";
	}

	/**
	 * 编辑热门城市
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(String id, HttpServletRequest request) {
		Hotcity hotcity = new Hotcity();
		String province = "";
		String city = "";
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				hotcity = hotCityService.selHotCityById(StringUtilsEX.ToInt(id));
				province = hotcity.getPcode();
				city = hotcity.getCode();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("province", province);
		request.setAttribute("city", city);
		request.setAttribute("gbinfo", hotcity);
		return "/platform/hotcity/edit";
	}

}
