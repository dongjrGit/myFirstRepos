package com.yinlian.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.po.Baner;
import com.yinlian.wssc.web.service.BanerService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/platform/baner")
public class BanerViewController {

	@Autowired
	private BanerService banerService;

	/**
	 * 获取发现baner列表
	 * 
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(String type, HttpServletRequest request,String site) {
		request.setAttribute("type", type);
		request.setAttribute("site", site);
		return "/platform/baner/list";
	}

	/**
	 * 编辑发现baner
	 * 
	 * @param type
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(String type, HttpServletRequest request, String id,String site) {
		Baner baner = new Baner();
		String[] strs = null;
		try {
			if (!StringUtilsEX.IsNullOrWhiteSpace(id)) {
				baner = banerService.selBanerById(StringUtilsEX.ToInt(id));
				String ctype = baner.getCtype();
				strs = new String[ctype.length()];
				for (int i = 0; i < ctype.length(); i++) {
					strs[i] = ctype.substring(i, i + 1);
				}
				request.setAttribute("ctype1", strs[0]);
				request.setAttribute("ctype2", strs[1]);
				request.setAttribute("ctype3", strs[2]);
				request.setAttribute("ctype4", strs[3]);
			}else{
				switch (site) {
				case "pc":
					request.setAttribute("ctype1", 1);
					break;
				case "wap":
					request.setAttribute("ctype2", 1);
					break;
				case "app":
					request.setAttribute("ctype3", 1);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("vo", baner);
		request.setAttribute("type", type);
		return "/platform/baner/edit";
	}

}
