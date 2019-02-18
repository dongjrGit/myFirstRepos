package com.yinlian.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.HighSpecialtyService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@RestController
@RequestMapping("/app/pl")
public class PlAppController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private CityServcie cityServcie;
	@Autowired
	private HighSpecialtyService highSpecialtyService;

	@RequestMapping(value = "/clist", produces = "text/html;charset=UTF-8")
	public String newslist(String type, String code, String stype, String page, String size) {
		ReusltItem item = new ReusltItem();

		int ctype = StringUtilsEX.ToInt(type), ccode = StringUtilsEX.ToInt(code), cpage = StringUtilsEX.ToInt(page),
				csize = StringUtilsEX.ToInt(size), btype = StringUtilsEX.ToInt(stype);
		if (ctype == -1)
			ctype = 3;
		if (cpage <= 0)
			cpage = 1;
		if (csize <= 0)
			csize = 10;
		AppNewsCriteria criteria = new AppNewsCriteria();
		criteria.setCode(ccode);
		criteria.setType(ctype);
		criteria.setCtype(btype);
		System.out.println(btype);
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

	/**
	 * 获取优质特产
	 * 
	 * @param type
	 * @param code
	 * @param stype
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/hlist", produces = "text/html;charset=UTF-8")
	public String newshlist(String type, String code, String page, String size) {
		ReusltItem item = new ReusltItem();

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
			PageBean pBean = highSpecialtyService.getApplist(criteria, cpage, csize);
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
