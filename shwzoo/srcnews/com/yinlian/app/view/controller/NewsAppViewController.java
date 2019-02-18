package com.yinlian.app.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Highspecialty;
import com.yinlian.wssc.web.po.Snews;
import com.yinlian.wssc.web.service.HighSpecialtyService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/app/news")
public class NewsAppViewController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private HighSpecialtyService highSpecialtyService;

	@RequestMapping("/nav")
	public String nav(Model model) {
		try {
			List<Highspecialty> rmlist = highSpecialtyService.getByRecommend();
			List<Snews> snewslist = newsService.getByRecommend();
			model.addAttribute("rmlist", rmlist);
			model.addAttribute("snewslist", snewslist);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "/app/fx/nav";
	}

	/**
	 * 
	 * @param type
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("/nlist")
	public String newslist(String type, String code, Model model) {
		int ctype = StringUtilsEX.ToInt(type), ccode = StringUtilsEX.ToInt(code);
		if (ctype == -1)
			ctype = 3;
		AppNewsCriteria criteria = new AppNewsCriteria();
		criteria.setCode(ccode);
		criteria.setType(ctype);
		criteria.setOrderByClause("sort");
		criteria.setSort("desc");
		try {

			PageBean pBean = newsService.getApplist(criteria, 1, 10);
			model.addAttribute("list", pBean.getBeanList());

			if (pBean.getTp() == null)
				model.addAttribute("page", 1);
			else
				model.addAttribute("page", pBean.getTp());
			// item.setData(pBean.getBeanList());
			// item.setMaxRow(pBean.getTr());
			// item.setPageIndex(pBean.getPc());
			// item.setCode(0);
		} catch (Exception ex) {
			// TODO 未记录日志
			ex.printStackTrace();
		}
		return "/app/fx/nlist";
	}

	/**
	 * 绿色故事
	 * 
	 * @param type
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("/lsgslist")
	public String lsgslist(String type, Model model) {
		int ctype = StringUtilsEX.ToInt(type);
		if (ctype == -1)
			ctype = 5;
		AppNewsCriteria criteria = new AppNewsCriteria();
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
			// item.setData(pBean.getBeanList());
			// item.setMaxRow(pBean.getTr());
			// item.setPageIndex(pBean.getPc());
			// item.setCode(0);
		} catch (Exception ex) {
			// TODO 未记录日志
			ex.printStackTrace();
		}
		return "/app/fx/lsgslist";
	}

}
