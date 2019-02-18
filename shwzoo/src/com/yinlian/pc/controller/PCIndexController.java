package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Pc_TopicCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/pc/index")
public class PCIndexController {
	@Autowired private TopicService tpService;
 
	/**
	 * 获取专题
	 * @param pagetag 页面标识
	 * @param mark 专题标识 位置）
	 * @return
	 */
	@RequestMapping(value = "/gettopic", produces = "text/html;charset=UTF-8")
	public @ResponseBody String gettopic(String pagetag,String mark) {
		ReusltItem item = new ReusltItem();
		String logpath = "activity/1/getSpike";
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(pagetag)) {
				item.setCode(-101);
				item.setDesc("页面标识不为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(mark)) {
				item.setCode(-102);
				item.setDesc("专题标识不为空！");
				return item.toJson();
			}
			List<Api_TopicBySpuDto> topspulist=tpService.getIndexTopic(StringUtilsEX.ToInt(pagetag),StringUtilsEX.ToInt(mark),
					TopicTypeEnum.商品.getValue(), Integer.toString(WebSetEnum.pc.getValue()),5);	
			item.setData(topspulist);
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取专题异常：" + e.toString());
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取专题异常! 异常信息:{0}", e), logpath);
		}
		return item.toJson();
	}
	/**
	 * 获取专题列表
	 * @param pagetag
	 * @param indexgetpcTopicList
	 * @param size
	 * @param mark   专题表示
	 * @param orderby 排序 1：评论数 2：创建时间 3：价格
	 * @return
	 */
	@RequestMapping(value = "/getpcTopicList", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getPcTopByPage(String pagetag,String index, String size,String mark,String orderby,String sort) {
		ReusltItem item = new ReusltItem();
		String logpath = "Topic/getpcTopicList";
		try {
			Pc_TopicCriteria criteria=new Pc_TopicCriteria();
			criteria.setMark(StringUtilsEX.ToInt(mark));
			if (StringUtilsEX.ToInt(mark)==TopicMarkEnum.每日鲜.getValue()) {
				criteria.setPagetag(PageMarkType.每日鲜.getValue());
			}else if (StringUtilsEX.ToInt(mark)==TopicMarkEnum.包邮直送.getValue()) {
				criteria.setPagetag(PageMarkType.包邮直送.getValue());
			}
			criteria.setType(TopicTypeEnum.商品.getValue());
			criteria.setWebset(Integer.toString(WebSetEnum.pc.getValue()));
			if(!StringUtilsEX.IsNullOrWhiteSpace(orderby)){
				criteria.setOrderByClause(orderby);
				criteria.setSort(sort);
			}
			PageBean list=tpService.getPcTopByPage(criteria, StringUtilsEX.ToInt(index), StringUtilsEX.ToInt(size));
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPageIndex(list.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取专题异常：" + e.toString());
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取专题异常! 异常信息:{0}", e), logpath);
		}
		return item.toJson();
	}
}
