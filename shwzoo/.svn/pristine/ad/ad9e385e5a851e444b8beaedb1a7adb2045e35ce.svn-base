package com.yinlian.wssc.platform.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.po.IntegraproductWithBLOBs;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.service.IntegraproductService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/platform/integra")
public class IntegraproductViewController {

	@Autowired
	private UserslevelService userslevelService;

	@Autowired
	private IntegraproductService integraproductService;

	/**
	 * 获取积分商品列表
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "/platform/integraproduct/list";
	}

	/**
	 * 编辑积分商品
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editintepro")
	public String editintepro(String id, Model model) {
		List<Userslevel> list = null;
		IntegraproductWithBLOBs iwb = new IntegraproductWithBLOBs();
		try {
			list = userslevelService.queryAllLevel();
			iwb = integraproductService.getListById(StringUtilsEX.ToInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		model.addAttribute("vo", iwb);
		return "/platform/integraproduct/edit";
	}

}
