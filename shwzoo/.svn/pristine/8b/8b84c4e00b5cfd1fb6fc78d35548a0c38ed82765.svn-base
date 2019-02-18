package com.techown.wssc.platform.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.techown.wssc.web.po.CriteriaScenic;
import com.techown.wssc.web.po.Scenic;
import com.techown.wssc.web.po.ScenicType;
import com.techown.wssc.web.po.ScenicWithBLOBs;
import com.techown.wssc.web.service.ScenicService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/zoo/scenic")
public class ScenicController {
	
	private static Logger logger = LoggerFactory.getLogger(ScenicController.class);
	/**
	 * JSON 转换
	 */
	private static ObjectMapper MAPPER = new ObjectMapper();
	static {
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Autowired
	private ScenicService scenicService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private TopicService topicService;

	/**
	 * 查询景点类型
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/queryScenicType")
	@ResponseBody
	public ReusltItem queryScenicType(String type) {
		ReusltItem item = new ReusltItem();
		try {
			List<ScenicType> list = scenicService.queryScenicType("PC");
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询分类信息异常：" + e.getMessage());
			logger.error("queryScenicType type {} !\r\n exception",type,e);
		}
		return item;

	}

	/**
	 * 查询景点列表
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param name
	 *            景点名称
	 * @param type
	 *            类型
	 * @param area
	 *            区域
	 * @return
	 */
	@RequestMapping("/querylist")
	@ResponseBody
	public ReusltItem querylist(String pageindex, String pagesize, String name, String type, Integer area) {
		ReusltItem item = new ReusltItem();
		int index = StringUtilsEX.ToInt(pageindex);
		index = index == -1 ? 1 : index;
		int size = StringUtilsEX.ToInt(pagesize);
		size = size == -1 ? 10 : size;

		CriteriaScenic criteria = new CriteriaScenic();
		criteria.setName(name);
		criteria.setType(type);
		criteria.setArea(area);

		criteria.setOrderByClause("createTime");
		criteria.setSort("DESC");
		PageBean pageBean = scenicService.querylist(criteria, index, size);
		item.setCode(0);
		item.setData(pageBean.getBeanList());
		item.setMaxRow(pageBean.getTr());
		item.setPageIndex(pageBean.getPc());
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
		ReusltItem item = new ReusltItem();
		ScenicWithBLOBs scenicWithBLOBs = null;
		try {
			scenicWithBLOBs = MAPPER.readValue(data, ScenicWithBLOBs.class);
			//验证名字是否存在
			if(scenicService.checkName(scenicWithBLOBs.getId(),scenicWithBLOBs.getScenicname())){
				item.setCode(100);
				item.setDesc("景点设施名称重复！");
			}else{
				ObjectNode objectNode = MAPPER.readValue(data, ObjectNode.class);
				int interaction = 0;
				int explain = 0;
				int actionShow = 0;
				if (!objectNode.path("interaction").asText().isEmpty()) {
					interaction = objectNode.path("interaction").asInt();
				}
				if (!objectNode.path("explain").asText().isEmpty()) {
					explain = objectNode.path("explain").asInt();
				}
				if (!objectNode.path("actionShow").asText().isEmpty()) {
					actionShow = objectNode.path("actionShow").asInt();
				}
				String imagePaths = objectNode.path("scenicImgs").asText();
				scenicWithBLOBs.setProperty(interaction + explain + actionShow);
				if (null == scenicWithBLOBs.getId()) {
					Date date = new Date();
					scenicWithBLOBs.setCreatetime(date);
					scenicWithBLOBs.setUpdatetime(date);
					scenicService.addScenic(scenicWithBLOBs, imagePaths);
					item.setCode(0);
				} else {

					scenicWithBLOBs.setUpdatetime(new Date());
					scenicService.updateScenic(scenicWithBLOBs, imagePaths);
					item.setCode(0);
				}
			}
		} catch (Exception e) {
			logger.error("editslist request data {} !\r\n exception",data,e);
			item.setCode(100);
			item.setDesc("操作失败");
		}
		return item;
	}

	/**
	 * 刪除景点设施
	 * 
	 * @param id
	 *            主键
	 * @param imageid
	 *            详情图片关联主键
	 * @return
	 */
	@RequestMapping("/dellist")
	@ResponseBody
	public ReusltItem dellist(int id, String imageid) {
		ReusltItem item = new ReusltItem();
		try {
			scenicService.dellist(id, imageid);
			item.setCode(0);
			item.setDesc("删除成功");
		} catch (Exception e) {
			logger.error("dellist request id {} - imageid {} !\r\n exception",id,imageid,e);
			item.setCode(-900);
			item.setDesc("删除公告信息异常：" + e.getMessage());
		}
		return item;
	}

	/**
	 * 修改发布状态
	 * 
	 * @param id
	 *            主键ID
	 * @param state
	 *            当前发布状态
	 * @return
	 */
	@RequestMapping("/updateState")
	@ResponseBody
	public ReusltItem updateState(int id, int state) {
		ReusltItem item = new ReusltItem();
		try {
			Boolean flag = scenicService.updateState(id, state);
			if (flag) {
				item.setCode(0);
				item.setDesc("操作成功");
			} else {
				item.setCode(1);
				item.setDesc("请先修改撤销首页景点设施");
			}
		} catch (Exception e) {
			logger.error("updateState request id {} - state {} !\r\n exception",id,state,e);
			item.setCode(-900);
			item.setDesc("操作失败：" + e.getMessage());
		}
		return item;
	}

	/**
	 * 根据类别code和景点名称查询景点名称列表
	 * 
	 * @param type 景点类别
	 * @param name 景点名称
	 * @param state 发布状态
	 * @return
	 */
	@RequestMapping("/queryScenicName")
	@ResponseBody
	public ReusltItem queryScenicName(String type, String name,Integer state) {
		ReusltItem item = new ReusltItem();
		try {
			List<Scenic> list = scenicService.getScenicStartWithName(type, name,state);
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			logger.error("queryScenicName type {} - name {} - state {} !\r\n exception",type,name,state,e);
			item.setCode(-900);
			item.setDesc("查询分类信息异常：" + e.getMessage());
		}
		return item;
	}
	
	@RequestMapping("/getShopListByName")
	@ResponseBody
	public ReusltItem getShopListByName(String name) {
		ReusltItem item = new ReusltItem();
		try {
			List<Shop> list = shopService.getShopListByName(name);
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			logger.error("getShopListByName request name {} !\r\n exception",name,e);
			item.setCode(-900);
			item.setDesc("查询分类信息异常：" + e.getMessage());
		}
		return item;
	}
	/**
	 * 获取猜你喜欢信息
	 * @param name
	 * @return
	 */
	@RequestMapping("/getTopicsByName")
	@ResponseBody
	public ReusltItem getTopicsByName(String name) {
		logger.info("getTopicsByName ! name {}",name);
		ReusltItem item = new ReusltItem();
		try {
			List<Topic> list = topicService.getTopicStartWithNameByMark(name); 
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("操作失败");
			logger.error("getTopicsByName exception ! name {}",name,e);
		}
		return item;
	}
}
