package com.yinlian.app.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.HotCityService;

@Controller
@RequestMapping("/pub/address")
public class AddressController {
	@Autowired
	private CityServcie cityServcie;
	@Autowired
	private HotCityService hotCityService;

	@RequestMapping("/list")
	public String address(String path,Model model) {
		try {
			model.addAttribute("list", cityServcie.getCityAllList());
			model.addAttribute("path", path);
			model.addAttribute("hlist", hotCityService.getAllList());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "/app/address";
	}
}
