package com.yinlian.view.wap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wap/error")
public class ErrorController {

	@RequestMapping(value = { "/index.html", "index" })
	public String index(String msg, String code, Model model) {
		model.addAttribute("msg", msg);
		model.addAttribute("code", code);
		return "/template/wap/error";
	}
	
	@RequestMapping(value = { "/info.html", "info" })
	public String info(String msg, String code, Model model) {
		model.addAttribute("message", msg);
		return "/template/wap/info";
	}
}
