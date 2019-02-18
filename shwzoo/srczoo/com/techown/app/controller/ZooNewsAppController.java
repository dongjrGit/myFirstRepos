package com.techown.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techown.wssc.web.po.CriteriaNews;
import com.techown.wssc.web.po.ZooNews;
import com.techown.wssc.web.service.ZooNewsService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;

@RestController
@RequestMapping("/api/app/zooNews")
public class ZooNewsAppController {
	private static Logger logger = LoggerFactory.getLogger(ZooNewsAppController.class);

	private static ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private ZooNewsService zooNewsService;

	/**
	 * 获取最新消息
	 * 
	 * @param ch
	 * @param state
	 *            状态（备用）
	 * @return
	 */
	@RequestMapping(value = "/getLastNews", produces = "text/html;charset=UTF-8")
	public String getLastNews(String ch, Integer state) {
		ReusltItem item = new ReusltItem();
		logger.info("call app-zooNews getLastNews request ch {} - state {}", ch, state);
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if (null == state) {
				state = 1;
			}
			ZooNews zooNews = zooNewsService.getLastNews(state);
			if (null != zooNews) {
				item.setData(zooNews);
			} else {
				item.setCode(700);
				item.setDesc("无记录");
			}
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-zooNews getLastNews resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error(
					"call app-zooNews getLastNews exception request ch {} - state {} !\r\n resp {} !\r\n exception", ch,
					state, result, e);
		}
		return result;
	}

	/**
	 * 获取详情
	 * 
	 * @param ch
	 * @param id
	 *            主键ID
	 * @param state
	 *            状态（备用）
	 * @return
	 */
	@RequestMapping(value = "/getDetailById", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public String getDetailById(String ch, Integer id,
			@RequestParam(value = "state", defaultValue = "1") Integer state) {
		ReusltItem item = new ReusltItem();
		logger.info("call app-zooNews getDetailById request ch {} - id {} - state {}", ch, id, state);
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			ZooNews zooNews = zooNewsService.selectById(id);
			if (zooNews.getState() == state) {
				item.setData(zooNews);
			} else {
				item.setCode(701);
				item.setDesc("更新中......");
			}
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-zooNews getDetailById resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error("call app-zooNews getDetailById exception request ch {} - id {} - state {} !\r\n resp {} !\r\n exception",
					ch, id, state, result, e);
		}
		return result;
	}

	/**
	 * 分页查询新闻
	 * 
	 * @param ch
	 *            渠道
	 * @param page
	 *            页码
	 * @param size
	 *            每页显示数量
	 * @param state
	 *            发布状态（备用）
	 * @return
	 */
	@RequestMapping(value = "/getNewsList", produces = "text/html;charset=UTF-8")
	public String getNewsList(String ch, Integer page, Integer size, Integer state) {
		logger.info("call app-zooNews getNewsList request ch {} - page {} - size {} -state {}", ch, page, size, state);
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			CriteriaNews criteria = new CriteriaNews();
			if (null == state) {
				state = 1;
			}
			criteria.setState(state);
			criteria.setOrderByClause("createTime");
			criteria.setSort("DESC");
			PageBean pageBean = zooNewsService.querylist(criteria, page, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setPage(pageBean.getTp());
			item.setPageSize(pageBean.getPs());
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-zooNews getNewsList resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error(
					"call app-zooNews getNewsList request ch {} - page {} - size {} -state {} !\r\n resp {} !\r\n exception",
					ch, page, size, state, result, e);
		}
		return result;
	}

	/**
	 * 首页动物科普新闻
	 * 
	 * @param ch
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/getNewsListHot", produces = "text/html;charset=UTF-8")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public String getNewsListHot(HttpServletResponse response,String ch, Integer state) {
		logger.info("call app-zooNews getNewsListHot request ch {} -state {}", ch, state);
		ReusltItem item = new ReusltItem();
		String result = null;
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			if (null == state) {
				state = 1;
			}
			List<ZooNews> zooNewsList = zooNewsService.getNewsListHot(state);

			item.setCode(0);
			item.setData(zooNewsList);
			result = MAPPER.writeValueAsString(item);
			logger.debug("call app-zooNews getNewsListHot resp {}", result);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			result = item.toJson();
			logger.error("call app-zooNews getNewsListHot request ch {} - -state {} !\r\n resp {} !\r\n exception", ch,
					state, result, e);
		}
		return result;
	}
}
