package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Baner;
import com.yinlian.wssc.web.po.CriteriaBaner;
import com.yinlian.wssc.web.service.BanerService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/baner")
public class BanerController {

	@Autowired
	private BanerService banerService;

	/**
	 * 编辑发现baner
	 * 
	 * @param id
	 * @param title
	 * @param url
	 * @param img
	 * @param type
	 * @param ctype1
	 * @param ctype2
	 * @param ctype3
	 * @param ctype4
	 * @param sort
	 * @return
	 */
	@RequestMapping("/editlist")
	@ResponseBody
	public ReusltItem editlist(String id, String title, String url, String img, String type, String ctype1,
			String ctype2, String ctype3, String ctype4, String sort) {
		ReusltItem item = new ReusltItem();
		try {
			Baner baner = new Baner();
			baner.setTitle(title);
			baner.setUrl(url);
			baner.setImg(img);
			baner.setType(StringUtilsEX.ToInt(type));
			String str = "";
			if (ctype1 == null || ctype1 == "") {
				str += "0";
			} else {
				str += ctype1;
			}
			if (ctype2 == null || ctype2 == "") {
				str += "0";
			} else {
				str += ctype2;
			}
			if (ctype3 == null || ctype3 == "") {
				str += "0";
			} else {
				str += ctype3;
			}
			if (ctype4 == null || ctype4 == "") {
				str += "0";
			} else {
				str += ctype4;
			}
			str += "0";
			baner.setCtype(str);
			if (sort == null || sort == "") {
				baner.setSort(0);
			} else {
				baner.setSort(StringUtilsEX.ToInt(sort));
			}
			if (StringUtilsEX.ToInt(id) >= 0) {
				baner.setId(StringUtilsEX.ToInt(id));
				banerService.updateBanerById(baner);
				item.setCode(0);
			} else {
				banerService.insertBaner(baner);
				item.setCode(0);
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("编辑发现baner异常：" + e.getMessage());
			LogHandle.error(LogType.baner, MessageFormat.format("编辑发现baner异常! 异常信息:{0}", e.getMessage()),
					"/platform/baner/editlist");
		}
		return item;
	}

	/**
	 * 获取发现baner列表
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @return
	 */
	@RequestMapping("/getlist")
	@ResponseBody
	public ReusltItem getlist(String title, String ctype,Integer type, String pageindex, String pagesize) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaBaner criteria = new CriteriaBaner();
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			criteria.setTitle(title);
			criteria.setType(type);
			criteria.setCtype(StringUtilsEX.ToInt(ctype));
			criteria.setOrderByClause("sort");
			PageBean pageBean = banerService.selBaner(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取发现baner异常：" + e.getMessage());
			LogHandle.error(LogType.baner, MessageFormat.format("获取发现baner异常! 异常信息:{0}", e.getMessage()),
					"/platform/baner/getlist");
		}
		return item;
	}

	/**
	 * 删除baner
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delbaner")
	@ResponseBody
	public ReusltItem delbaner(String id) {
		ReusltItem item = new ReusltItem();
		try {
			banerService.delBaner(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setDesc("删除成功");
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除baner异常：" + e.getMessage());
			LogHandle.error(LogType.baner, MessageFormat.format("删除baner异常! 异常信息:{0}", e.getMessage()),
					"/platform/baner/delbaner");
		}
		return item;
	}

}
