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
import com.techown.wssc.web.po.CriteriaNews;
import com.techown.wssc.web.po.ZooNews;
import com.techown.wssc.web.service.ZooNewsService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/zoo/news")
public class ZooNewsController {
	private static Logger logger = LoggerFactory.getLogger(ZooNewsController.class);
	@Autowired
	private ZooNewsService zooNewsService;
	
	/**
	 * JSON 转换
	 */
	private static ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 列表查询
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param title
	 *            标题
	 * @param state
	 *            发布状态
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public ReusltItem querylist(String pageindex, String pagesize, String title, Integer state, Integer delState) {
		logger.info("zooNews querylist pageindex {} - pagesize {} - title {} - state {}", pageindex, pagesize, title, state);
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			CriteriaNews criteria = new CriteriaNews();
			criteria.setTitle(title);
			criteria.setState(state);
			criteria.setDelState(delState);
			criteria.setOrderByClause("createTime");
			criteria.setSort("DESC");
			PageBean pageBean = zooNewsService.querylist(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			logger.info("zooNews querylist exception pageindex {} - pagesize {} - title {} - state {} !\r\n exception", pageindex, pagesize, title, state, e);
			item.setCode(-900);
			item.setDesc("操作失败");
		}
		return item;
	}
	/**
	 * 编辑
	 * @param data
	 * @return
	 */
	@RequestMapping("/editlist")
	@ResponseBody
	public ReusltItem editslist(String data) {
		logger.info("zooNews editlist request data {}", data);
		ReusltItem item = new ReusltItem();
		try {
			ZooNews bean = MAPPER.readValue(data, ZooNews.class);
			Date date = new Date();
			if (null == bean.getId()) {
				bean.setCreateTime(date);
				bean.setUpdateTime(date);
				bean.setCreator(SessionState.GetCurrentUser().getLoginName());
				zooNewsService.addNews(bean);
			} else {
				bean.setUpdateTime(date);
				bean.setOperator(SessionState.GetCurrentUser().getLoginName());
				zooNewsService.updateById(bean);
			}
			item.setCode(0);
		} catch (Exception e) {
			logger.error("zooNews editslist data {} !\r\n exception", data, e);
			item.setCode(100);
		}
		return item;
	}

	/**
	 * 修改发布状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 */
	@RequestMapping("/updateState")
	@ResponseBody
	public ReusltItem updateState(Integer id, Integer state) {
		logger.info("zooNews updateState id {} - state {}", id, state);
		ReusltItem item = new ReusltItem();
		try {
			String operator = SessionState.GetCurrentUser().getLoginName();
			zooNewsService.updateState(id, state, operator);
			item.setCode(0);
		} catch (Exception e) {
			logger.error("zooNews updateState error id {} - state {} !\r\n exception", id, state, e);
			item.setCode(100);
		}
		return item;
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/dellist")
	@ResponseBody
	public ReusltItem dellist(Integer id) {
		logger.info("zoobanner delist id {} - user {}", id, SessionState.GetCurrentUser().getLoginName());
		ReusltItem item = new ReusltItem();
		try {
			zooNewsService.dellist(id, SessionState.GetCurrentUser().getLoginName());
			item.setCode(0);
			item.setDesc("删除成功");
		} catch (Exception e) {
			logger.error("zoobanner dellist exception id {} - user {} !\r\n exception", id, SessionState.GetCurrentUser().getLoginName(), e);
			item.setCode(-900);
			item.setDesc("删除失败");
		}
		return item;
	}

}
