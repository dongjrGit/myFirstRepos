package com.yinlian.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.po.Highspecialty;
import com.yinlian.wssc.web.po.V_Hp_Sku;
import com.yinlian.wssc.web.service.HighSpecialtyService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/platform/yztc")
public class HighSpecialtyViewController {

	@Autowired
	private HighSpecialtyService highSpecialtyService;

	/**
	 * 优质特产列表
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "/platform/Highspecialty/list";
	}

	/**
	 * 编辑优质特产
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(String id, HttpServletRequest request) {
		V_Hp_Sku v_Hp_Sku = new V_Hp_Sku();
		Highspecialty highspecialty = new Highspecialty();
		try {
			if (StringUtilsEX.ToInt(id) > 0) {
				v_Hp_Sku = highSpecialtyService.selVById(StringUtilsEX.ToInt(id));
				highspecialty = highSpecialtyService.selListById(StringUtilsEX.ToInt(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", highspecialty);
		request.setAttribute("gbinfo", v_Hp_Sku);
		return "/platform/Highspecialty/edit";
	}

}
