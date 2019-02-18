package com.yinlian.app.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.service.HighSpecialtyService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 地方馆
 * 
 * @author mjx
 *
 */
@Controller
@RequestMapping("/app/pl")
public class PlAppViewController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private HighSpecialtyService highSpecialtyService;

	/**
	 * 优质特产
	 * 
	 * @param type
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("/hslist")
	public String hslist(String type, String code, String pname, Model model) {
		int ctype = StringUtilsEX.ToInt(type), ccode = StringUtilsEX.ToInt(code);
		if (ctype == -1)
			ctype = 0;
		if (ccode == -1)
			pname = "全国";
		AppNewsCriteria criteria = new AppNewsCriteria();
		criteria.setCode(ccode);
		criteria.setType(ctype);
		criteria.setCtype(-1);
		criteria.setOrderByClause("sort");
		criteria.setSort("desc");
		try {
			PageBean pBean = highSpecialtyService.getApplist(criteria, 1, 10);
			model.addAttribute("list", pBean.getBeanList());
			if (pBean.getTp() == null)
				model.addAttribute("page", 1);
			else
				model.addAttribute("page", pBean.getTp());
			model.addAttribute("code", code);
			model.addAttribute("pname", pname);

			// item.setData(pBean.getBeanList());
			// item.setMaxRow(pBean.getTr());
			// item.setPageIndex(pBean.getPc());
			// item.setCode(0);
		} catch (Exception ex) {
			// TODO 未记录日志
			ex.printStackTrace();
		}
		return "/app/pl/hslist";
	}

	/**
	 * 特产名录
	 * 
	 * @param type
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("/sdlist")
	public String sdlist(String type, String code, String pname, Model model) {
		int ctype = StringUtilsEX.ToInt(type), ccode = StringUtilsEX.ToInt(code);
		if (ctype == -1)
			ctype = 3;
		if (ccode == -1)
			pname = "全国";
		AppNewsCriteria criteria = new AppNewsCriteria();
		criteria.setCode(ccode);
		criteria.setType(ctype);
		criteria.setCtype(-1);
		criteria.setOrderByClause("sort");
		criteria.setSort("desc");
		try {
			PageBean pBean = newsService.getApplist(criteria, 1, 10);
			model.addAttribute("list", pBean.getBeanList());
			if (pBean.getTp() == null)
				model.addAttribute("page", 1);
			else
				model.addAttribute("page", pBean.getTp());
			model.addAttribute("code", code);
			model.addAttribute("pname", pname);
			model.addAttribute("clslist", newsService.getClassByType(3));
			// item.setData(pBean.getBeanList());
			// item.setMaxRow(pBean.getTr());
			// item.setPageIndex(pBean.getPc());
			// item.setCode(0);
		} catch (Exception ex) {
			// TODO 未记录日志
			ex.printStackTrace();
		}
		return "/app/pl/sdlist";
	}

	/**
	 * 文化特色
	 * 
	 * @param type
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("/cclist")
	public String cclist(String type, String code, String pname, Model model) {
		int ctype = StringUtilsEX.ToInt(type), ccode = StringUtilsEX.ToInt(code);
		if (ctype == -1)
			ctype = 4;
		if (ccode == -1)
			pname = "全国";
		AppNewsCriteria criteria = new AppNewsCriteria();
		criteria.setCode(ccode);
		criteria.setType(ctype);
		criteria.setCtype(-1);
		criteria.setOrderByClause("sort");
		criteria.setSort("desc");
		try {
			PageBean pBean = newsService.getApplist(criteria, 1, 10);
			model.addAttribute("list", pBean.getBeanList());
			if (pBean.getTp() == null)
				model.addAttribute("page", 1);
			else
				model.addAttribute("page", pBean.getTp());
			model.addAttribute("code", code);
			model.addAttribute("pname", pname);

			// item.setData(pBean.getBeanList());
			// item.setMaxRow(pBean.getTr());
			// item.setPageIndex(pBean.getPc());
			// item.setCode(0);
		} catch (Exception ex) {
			// TODO 未记录日志
			ex.printStackTrace();
		}
		return "/app/pl/cclist";
	}

	/**
	 * 特产名录 文化特色 详细页
	 * 
	 * @param backurl
	 * @param model
	 * @return
	 */
	@RequestMapping("/acdetails")
	public String acdetails(String id, String backurl, Model model) {
		try {
			SnewsWithBLOBs po = newsService.getById(StringUtilsEX.ToInt(id));
			model.addAttribute("title", po.getTitle());
			model.addAttribute("content", po.getContent());
			model.addAttribute("backurl", backurl);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "/app/pl/acdetails";
	}
}
