package com.yinlian.wssc.platform.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.po.Groupbuyinfo;
import com.yinlian.wssc.web.po.VGroupShop;
import com.yinlian.wssc.web.service.GroupByClassService;
import com.yinlian.wssc.web.service.GroupbuyinfoService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/group")
public class GroupbuyinfoViewController {

	@Autowired
	private GroupByClassService groupByClassService;

	@Autowired
	private GroupbuyinfoService groupbuyinfoService;

	@Autowired
	private ShopService shopService;

	@RequestMapping("/list")
	public String list(Model model) {
		try {
			model.addAttribute("clslist", groupByClassService.getAllList());
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.group, "查询团购列表异常{0}!", e, "/platform/group/edit");
		}
		return "/platform/group/list";
	}

	/**
	 * 修改团购商品
	 * 
	 * @param model
	 * @param id
	 */
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

	/**
	 * 团购审核 商品
	 */
	@RequestMapping("/chklist")
	public String chklist(Model model) {
		try {
			model.addAttribute("clslist", groupByClassService.getAllList());
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.group, "查询团购列表异常{0}!", e, "/platform/group/edit");
		}
		return "/platform/group/chklist";
	}

	/**
	 * 店铺团购商品
	 */
	@RequestMapping("/slist")
	public void slist(Model model) {
		try {
			model.addAttribute("clslist", groupByClassService.getAllList());
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.group, "查询团购列表异常{0}!", e, "/platform/group/edit");
		}
	}

	/**
	 * 团购审核操作
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/chkedit")
	public void chklistedit(Model model, String id) {
		try {
			int sid = StringUtilsEX.ToInt(id);
			VGroupShop groupbuyinfo = new VGroupShop();
			groupbuyinfo = groupbuyinfoService.getV_G_ShopById(sid);
			model.addAttribute("gbinfo", groupbuyinfo);
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.group, "团购审核操作异常{0}!", e, "/platform/group/chklistedit");
		}
	}

	/**
	 * 团购审核 商品
	 */
	@RequestMapping("/slsit")
	public void slsit(Model model) {
		try {
			model.addAttribute("clslist", groupByClassService.getAllList());
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.group, "查询团购列表异常{0}!", e, "/platform/group/slsit");
		}
	}

	/**
	 * 店铺团购商品
	 * 
	 * @param model
	 * @param id
	 */
	@RequestMapping("/slsitedit")
	public void slsitedit(Model model, String id) {
		try {
			int sid = StringUtilsEX.ToInt(id);
			VGroupShop groupbuyinfo = new VGroupShop();
			groupbuyinfo = groupbuyinfoService.getV_G_ShopById(sid);
			model.addAttribute("gbinfo", groupbuyinfo);
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.group, "团购审核操作异常{0}!", e, "/platform/group/slsitedit");
		}
	}

}
