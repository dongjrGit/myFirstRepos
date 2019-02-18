package com.yinlian.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.po.GroupbuyClass;
import com.yinlian.wssc.web.service.GroupByClassService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/platform/tglx")
public class GroupByClassViewController {
	
	@Autowired
	private GroupByClassService groupByClassService;
	
	/**
	 * 显示团购类型
	 * @return
	 */
	@RequestMapping("/tglx_list")
	public String tglx_list(){
		return "platform/gbc/tglx_list";
	}
	
	/**
	 * 团购类型添加编辑
	 */
	@RequestMapping("/tglx_edit")
	public String tglx_edit(Model model,String id,HttpServletRequest request){
		try {
			if(!StringUtilsEX.IsNullOrWhiteSpace(id)){
				GroupbuyClass gbc=groupByClassService.selById(StringUtilsEX.ToInt(id));
				model.addAttribute("vo", gbc);
				request.setAttribute("title", "编辑团购分类");
			}else{
				request.setAttribute("title", "添加团购分类");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "platform/gbc/tglx_edit";
	}
	
}
