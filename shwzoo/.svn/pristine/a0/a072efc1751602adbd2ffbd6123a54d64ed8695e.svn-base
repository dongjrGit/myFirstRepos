package com.yinlian.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@RestController
@RequestMapping("/app/news")
public class NewsAppController {

	@Autowired
	private NewsService newsService;

	@RequestMapping("/clist")
	public String newslist(String type, String code, String page, String size) {
		int ctype = StringUtilsEX.ToInt(type), ccode = StringUtilsEX.ToInt(code), cpage = StringUtilsEX.ToInt(page),
				csize = StringUtilsEX.ToInt(size);
		if (ctype == -1)
			ctype = 3;
		if (cpage <= 0)
			cpage = 1;
		if (csize <= 0)
			csize = 10;
		AppNewsCriteria criteria = new AppNewsCriteria();
		criteria.setCode(ccode);
		criteria.setType(ctype);
		try {
			PageBean pBean = newsService.getApplist(criteria, cpage, csize);
			pBean.getBeanList();
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
	 * 获取绿色故事
	 * 
	 * @param type
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/lslist", produces = "text/html;charset=UTF-8")
	public String newslslist(String type, String page, String size) {
		ReusltItem item = new ReusltItem();
		int ctype = StringUtilsEX.ToInt(type), cpage = StringUtilsEX.ToInt(page), csize = StringUtilsEX.ToInt(size);
		if (ctype == -1)
			ctype = 5;
		if (cpage <= 0)
			cpage = 1;
		if (csize <= 0)
			csize = 10;
		AppNewsCriteria criteria = new AppNewsCriteria();
		criteria.setType(ctype);
		criteria.setCtype(-1);
		try {
			PageBean pBean = newsService.getApplist(criteria, cpage, csize);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setPage(pBean.getTp());
			item.setCode(0);
		} catch (Exception ex) {
			item.setCode(-999);
			ex.printStackTrace();
		}
		return item.toJson();
	}

}
