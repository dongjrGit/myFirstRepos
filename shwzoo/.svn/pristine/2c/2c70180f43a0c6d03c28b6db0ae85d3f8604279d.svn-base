package com.yinlian.wssc.seller.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.po.Groupbuyinfo;
import com.yinlian.wssc.web.service.GroupByClassService;
import com.yinlian.wssc.web.service.GroupbuyinfoService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/seller/group")
public class GroupbuyinfoSViewController {
	@Autowired
	private GroupByClassService groupByClassService;

	@Autowired
	private GroupbuyinfoService groupbuyinfoService;

	@RequestMapping("/list")
	public void list(Model model) {
		try {
			model.addAttribute("clslist", groupByClassService.getAllList());
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.group, "查询团购列表异常{0}!", e, "/platform/group/edit");
		}
	}
	
	@RequestMapping("/edit")
	public void edit(Model model, String id) {
		try {
			int sid = StringUtilsEX.ToInt(id);
			Groupbuyinfo groupbuyinfo = new Groupbuyinfo();
			groupbuyinfo.setState(0);
			if (sid > 0) {
				groupbuyinfo = groupbuyinfoService.getById(sid);
			}
			model.addAttribute("gbinfo", groupbuyinfo);
			model.addAttribute("clslist", groupByClassService.getAllList());
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.group, "编辑团购异常{0}!", e, "/platform/group/edit");
		}
	}
}
