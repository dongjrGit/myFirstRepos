package com.yinlian.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Groupbuyinfo_Api_Criteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.GroupByClassService;
import com.yinlian.wssc.web.service.GroupbuyinfoService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;


/**
 * 团购
 * @author mjx
 *
 */
@RestController
@RequestMapping("/api/app/group")
public class GroupApiController {

	@Autowired
	private GroupByClassService groupByClassService;
	
	@Autowired
	private GroupbuyinfoService groupbuyinfoService;

	/**
	 * 获取分类
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/clslist", produces = "text/html;charset=UTF-8")
	public String getclslist(String ch) {
		BaseResult item = new BaseResult();
		try {
			item.setCode(0);
			item.setData(groupByClassService.getAllList());
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询团购分类异常：" + e.getMessage());
			LogHandle.error(LogType.Api, "查询团购分类异常! 异常信息:{0}", e, "group/clslist");
		}
		return item.toJson();
	}	
	
	/**
	 * 获取列表
	 * @param ch
	 * @param page
	 * @param size
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/list", produces = "text/html;charset=UTF-8")
	public String  glist(String ch,String page,String size,String cid) {
		ReusltItem item = new ReusltItem();
		try {
			item.setCode(0);
			Integer spage=StringUtilsEX.ToInt(page),sszie=StringUtilsEX.ToInt(size);
			if (spage<=0) {
				spage=1;
			}
			if (sszie<=0) {
				sszie=10;
			}
			Groupbuyinfo_Api_Criteria criteria =new Groupbuyinfo_Api_Criteria();
			criteria.setAuditing(0);
			criteria.setState(0);
			PageBean list=groupbuyinfoService.getApiList(criteria, spage, sszie);	
			item.setMaxRow(list.getTr());
			item.setPageIndex(list.getPc());
			item.setPageSize(sszie);
			item.setData(list.getBeanList());	
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询团购列表异常：" + e.getMessage());
			LogHandle.error(LogType.Api, "查询团购列表异常! 异常信息:{0}", e, "group/glist");
		}
		return item.toJson();
	}
}
