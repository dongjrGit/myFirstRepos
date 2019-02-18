package com.techown.wssc.platform.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.Enums.BannerType;
import com.techown.wssc.web.po.CriteriaBanner;
import com.techown.wssc.web.po.ZooBanner;
import com.techown.wssc.web.service.ZooBannerService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/zoo/banner")
public class ZooBannerController {
	private static Logger logger = LoggerFactory.getLogger(ZooBannerController.class);

	@Autowired
	private ZooBannerService zooBannerService;
	/**
	 * JSON 转换
	 */
	private static ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	/**
	 * 分页查询
	 * @param pageindex
	 * @param pagesize
	 * @param title  
	 * @param status
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public ReusltItem querylist(String pageindex, String pagesize, String title, Integer status) {
		logger.info("zooBanner querylist pageindex {} - pagesize {} - title {} - status {}", pageindex, pagesize, title, status);
		ReusltItem item = new ReusltItem();
		;
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			CriteriaBanner criteria = new CriteriaBanner();
			criteria.setTitle(title);
			criteria.setStatus(status);
			criteria.setOrderByClause("createTime");
			criteria.setSort("DESC");
			PageBean pageBean = zooBannerService.querylist(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			logger.info("zooBanner querylist exception pageindex {} - pagesize {} - title {} - status {} !\r\n exception {}", pageindex, pagesize, title, status, e);
			item.setCode(-900);
			item.setDesc("操作失败");
		}
		return item;
	}
	/**
	 * 启用/禁用
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public ReusltItem updateStatus(Integer id, Integer status) {
		logger.info("zoobanner updateStatus id {} - status {}", id, status);
		ReusltItem item = new ReusltItem();
		try {
			String operator = SessionState.GetCurrentUser().getLoginName();
			zooBannerService.updateStatus(id, status, operator);
			item.setCode(0);
		} catch (Exception e) {
			logger.error("zoobanner updateStatus error id {} - status {} !\r\n exception", id, status, e);
			item.setCode(100);
		}
		return item;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/dellist")
	@ResponseBody
	public ReusltItem dellist(Integer id) {
		logger.info("zoobanner delist id {} - user {}", id, SessionState.GetCurrentUser().getLoginName());
		ReusltItem item = new ReusltItem();
		try {
			zooBannerService.dellist(id);
			item.setCode(0);
			item.setDesc("删除成功");
		} catch (Exception e) {
			logger.error("zoobanner dellist exception id {} - user {} !\r\n exception", id, SessionState.GetCurrentUser().getLoginName(), e);
			item.setCode(-900);
			item.setDesc("删除失败");
		}
		return item;
	}
	/**
	 * 获取跳转类型
	 * @return
	 */
	@RequestMapping("/getBannerType")
	public @ResponseBody ReusltItem getBannerType() {
		ReusltItem item = new ReusltItem();
		try {
			item.setCode(0);
			item.setData(MAPPER.writeValueAsString(BannerType.values()));
		} catch (Exception e) {
			logger.error("zoobanner getBannerType exception", e);
			item.setCode(-900);
			item.setDesc("系统错误！");
		}
		return item;
	}

	/**
	 * 保存和编辑景点设施
	 * 
	 * @param data
	 *            编辑信息的json
	 * @return
	 */
	@RequestMapping("/editlist")
	@ResponseBody
	public ReusltItem editslist(String data) {
		logger.info("zoobanner editlist request data {}", data);
		ReusltItem item = new ReusltItem();
		try {
			ZooBanner bean = MAPPER.readValue(data, ZooBanner.class);
			Integer type = bean.getType();
			Integer typeId = bean.getTypeId();
			if (type == BannerType.Link.getCode()) {
//				bean.setUrl("url://link|||" + bean.getUrl());
				bean.setUrl(bean.getUrl());
			} else if (type == BannerType.PRODUCT.getCode()) {
				bean.setUrl("url://prodetailed|||" + typeId);
			} else if (type == BannerType.SHOP.getCode()) {
				bean.setUrl("url://shopdetail|||" + typeId + "&href=/wap/index.html");
			} else if (type == BannerType.SCENIC.getCode()) {
				bean.setUrl(typeId + "");
			} else if(type == BannerType.EDITOR.getCode()){
				bean.setUrl(typeId + "");
			}
			if (null == bean.getId()) {
				Date date = new Date();
				bean.setCreateTime(date);
				bean.setUpdateTime(date);
				bean.setCreator(SessionState.GetCurrentUser().getLoginName());
				zooBannerService.addBanner(bean);
			} else {
				bean.setUpdateTime(new Date());
				bean.setOperator(SessionState.GetCurrentUser().getLoginName());
				zooBannerService.updateById(bean);
			}
			item.setCode(0);
		} catch (Exception e) {
			logger.error("zoobanner editslist data {} !\r\n exception", data, e);
			item.setCode(100);
		}
		return item;
	}
}
