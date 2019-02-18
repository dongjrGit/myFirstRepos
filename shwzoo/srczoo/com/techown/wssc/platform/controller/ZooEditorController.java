package com.techown.wssc.platform.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.wssc.web.po.CriteriaEditor;
import com.techown.wssc.web.po.ZooEditor;
import com.techown.wssc.web.service.ZooEditorService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/zoo/editor")
public class ZooEditorController {
	private static Logger logger = LoggerFactory.getLogger(ZooEditorController.class);
	@Autowired
	private ZooEditorService zooEditorService;

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
	 * @param type
	 * @param delState
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public ReusltItem querylist(String pageindex, String pagesize, String title, Integer type,
			Integer delState) {
		logger.info("zooEditor querylist pageindex {} - pagesize {} - title {} - type {}",
				pageindex, pagesize, title, type);
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			CriteriaEditor criteria = new CriteriaEditor();
			criteria.setTitle(title);
			criteria.setType(type);
			criteria.setDelState(delState);
			criteria.setOrderByClause("createTime");
			criteria.setSort("DESC");
			PageBean pageBean = zooEditorService.listByType(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			logger.info(
					"zooEditor querylist exception pageindex {} - pagesize {} - title {} - type {} !\r\n exception",
					pageindex, pagesize, title, type, e);
			item.setCode(-900);
			item.setDesc("操作失败");
		}
		return item;
	}

	/**
	 * 编辑
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping("/editlist")
	@ResponseBody
	public ReusltItem editslist(String data) {
		logger.info("zooEditor editlist request data {}", data);
		ReusltItem item = new ReusltItem();
		try {
			ZooEditor bean = MAPPER.readValue(data, ZooEditor.class);
			Date date = new Date();
			if (null == bean.getId()) {
				bean.setCreatetime(date);
				bean.setUpdatetime(date);
				bean.setCreator(SessionState.GetCurrentUser().getLoginName());
				bean.setDelstate(1);
				zooEditorService.saveEditor(bean);
			} else {
				bean.setUpdatetime(date);
				bean.setOperator(SessionState.GetCurrentUser().getLoginName());
				zooEditorService.updateById(bean);
			}
			item.setCode(0);
		} catch (Exception e) {
			logger.error("zooEditor editslist data {} !\r\n exception", data, e);
			item.setCode(100);
		}
		return item;
	}

	/**
	 * 根据类别和标题进行模糊查询
	 * 
	 * @param type
	 * @param title
	 * @return
	 */
	@RequestMapping("/listByTitle")
	@ResponseBody
	public ReusltItem listByTitle(Integer type, String title) {
		ReusltItem item = new ReusltItem();
		try {
			List<ZooEditor> list = zooEditorService.listByTitle(type, title);
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			logger.error("listByTitle type {}, title {}", type, title, e);
			item.setCode(-900);
			item.setDesc("查询分类信息异常：" + e.getMessage());
		}
		return item;
	}

	/**
	 * 验证标题是否重复
	 * 
	 * @param id
	 * @param type
	 * @param title
	 * @return
	 */
	@RequestMapping("/checkTitle")
	@ResponseBody
	public ReusltItem checkTitle(Integer id, Integer type, String title) {
		ReusltItem item = new ReusltItem();
		try {
			int count = zooEditorService.countCheckTitle(id, type, title);
			if (count > 0) {
				item.setData(false);
			} else {
				item.setData(true);
			}
			item.setCode(0);
		} catch (Exception e) {
			logger.error("zooEditor checkTitle id {}, type {}", id, type, e);
			item.setCode(100);
			item.setDesc("操作异常");
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
	public ReusltItem dellist(Integer id, Integer type) {
		logger.info("zooEditor delist id {} - type {} - user {}", id, type,
				SessionState.GetCurrentUser().getLoginName());
		ReusltItem item = new ReusltItem();
		try {
			Map<String, String> map = zooEditorService.deleteById(id, type,
					SessionState.GetCurrentUser().getLoginName());
			item.setDesc(map.get("desc"));
			item.setCode(0);
		} catch (Exception e) {
			logger.error("zoobanner dellist exception id {} - type {} - user {} !\r\n exception",
					id, type, SessionState.GetCurrentUser().getLoginName(), e);
			item.setCode(-900);
			item.setDesc("删除失败");
		}
		return item;
	}

}
