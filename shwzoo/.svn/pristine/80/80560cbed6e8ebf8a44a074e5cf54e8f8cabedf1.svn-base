package com.yinlian.api.wap.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_SpuCriteria;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap")
public class WapMainController {



	@Autowired
	private TopicService topicService;
    @Autowired
    private SpikeActivityService spikeActivityService;
	@Autowired private SpuService spuService;
	
	
    
    
    /**
  	 * 发现好货
  	 * @param ch
  	 * @return
  	 */
  	@RequestMapping(value = "/findspu")
  	public  String findNspu(String ch, String mark, String page,
  			String size,Model model) {
  		ReusltItem item = new ReusltItem();
  		try {
  			if (!StringUtilsEX.isChannelTypeExist(ch)) {
  				item.setCode(-108);
  				item.setDesc("登录通道参数错误");
  			}

  			Integer imark = StringUtilsEX.ToIntNull(mark);
  			if (imark == null || imark <= 0) {
  				item.setCode(-101);
  				item.setDesc("专题标识参数错误");
  			}
  			Integer ipage = StringUtilsEX.ToIntNull(page);
  			Integer isize = StringUtilsEX.ToIntNull(size);
  			if (ipage == null || ipage <= 0) {
  				ipage = 1;
  			}
  			if (isize == null || isize <= 0) {
  				isize = 10;
  			}
  			Api_TopicBySpuCriteria atc = new Api_TopicBySpuCriteria();
  			atc.setMark(imark);
  			atc.setWebset(Integer.toString(WebSetEnum.wap.getValue()));
  			PageBean listBean = topicService.getTopicBySpu(atc, ipage, isize);
  			item.setData(listBean.getBeanList());
  			item.setPage(listBean.getTp());
  			item.setMaxRow(listBean.getTr());
  			item.setPageIndex(ipage);
  			item.setPageSize(isize);
  			model.addAttribute("list", item);
  		} catch (Exception e) {
  			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
  			LogHandle.error(LogType.wap, "发现好货异常! 异常信息:{0}", e,
  					"/wap/findspu");
  			return ErrorRedirect.getInstance().wapRedirect("发现好货异常");
  		}
  		return "/template/wap/index_list";
  	}

  	
  	/**
	 * 限时秒杀
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/findmshd")
	public @ResponseBody ReusltItem findmshd(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item;
			}
			Integer ipage = StringUtilsEX.ToIntNull(page);
  			Integer isize = StringUtilsEX.ToIntNull(size);
  			if (ipage == null || ipage <= 0) {
  				ipage = 1;
  			}
  			if (isize == null || isize <= 0) {
  				isize = 10;
  			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setUsesite(ActivityUsePlatformEnum.wap.getValue());
			item.setData(spikeActivityService.getmsActivityList(criteria,ipage, isize, item));
			item.setCode(0);
			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "获取秒杀活动错误：{0}", e, "/wap/findmshd");
		}
		return item;
	}
	/**
	 * 根据品牌id获取商品列表
	 * 
	 * @param ch
	 *            通道
	 * @param page
	 *            当前页
	 * @param size
	 *            页大小
	 * @param bid
	 *            品牌Id
	 * @param sortfield
	 *            排序 字段 0销量 1价格
	 * @param sorttype
	 *            排序类型 0降序 1升序
	 * @return
	 */
	@RequestMapping(value = "/getprobybid", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProByBid(String ch, String page, String size, String bid, String sortfield,
			String sorttype) {
		String logpath = "product/getprobybid";
		ReusltItem item = new ReusltItem();		
		try {
			Integer branid = StringUtilsEX.ToInt(bid);
			Integer index = StringUtilsEX.ToInt(page);
			Integer psize = StringUtilsEX.ToInt(size);
			if (index <= 0) {
				index = 1;
			}
			if (psize <= 0) {
				psize = 10;
			}
			Api_SpuCriteria criteria = new Api_SpuCriteria();
			criteria.setBid(branid);			
			Integer s = StringUtilsEX.ToIntNull(sortfield);
			Integer s1 = StringUtilsEX.ToIntNull(sorttype);
			s = s == null ? 0 : s;
			s1 = s1 == null ? 0 : s1;
			switch (s) {
			case 1:// 价格
				criteria.setOrderByClause("price");
				break;
			case 0:// 销量
			default:
				criteria.setOrderByClause("salescount");
				break;
			}
			switch (s1) {
			case 1:// 升序
				criteria.setSort("asc");
				break;
			case 0:// 降序
			default:
				criteria.setSort("desc");
				break;
			}
			PageBean list = spuService.getSpuByBidPage(criteria, index, psize);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPage(list.getTp());
			item.setPageIndex(index);
			item.setPageSize(psize);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "获取商品列表错误：{0}", e, logpath);
		}
		return item.toJson();
	}
	
	/**
	 * 发现好店
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/findfxhd")
	public @ResponseBody ReusltItem findfxhd(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
  				item.setCode(-108);
  				item.setDesc("登录通道参数错误");
  				return item;
  			}
  			Integer ipage = StringUtilsEX.ToIntNull(page);
  			Integer isize = StringUtilsEX.ToIntNull(size);
  			if (ipage == null || ipage <= 0) {
  				ipage = 1;
  			}
  			if (isize == null || isize <= 0) {
  				isize = 10;
  			}
  			PageBean listBean=topicService.getTopicByShop(ipage, isize);
  			item.setData(listBean.getBeanList());
  			item.setPage(listBean.getTp());
  			item.setMaxRow(listBean.getTr());
  			item.setPageIndex(ipage);
  			item.setPageSize(isize);
  			item.setPage(listBean.getTp());
  			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "发现好店内容错误：", e, "/wap/findfxhd");
		}
		return item;
	} 
	
	/**
	 * 获取专题内容
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/findtopic")
	public @ResponseBody ReusltItem findtopic(String ch, String page, String size,String mark,String topicid) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
  				item.setCode(-108);
  				item.setDesc("登录通道参数错误");
  				return item;
  			}
  			Integer marks=StringUtilsEX.ToInt(mark);
  			Integer ipage = StringUtilsEX.ToIntNull(page);
  			Integer isize = StringUtilsEX.ToIntNull(size);
  			if (ipage == null || ipage <= 0) {
  				ipage = 1;
  			}
  			if (isize == null || isize <= 0) {
  				isize = 10;
  			}
  			Api_TopicBySpuCriteria atc = new Api_TopicBySpuCriteria();
  			if (marks>0) {
  				atc.setMark(marks);
			}
  			if (StringUtilsEX.ToInt(topicid)>0) {
				atc.setTopicid(StringUtilsEX.ToInt(topicid));
			}
			atc.setWebset(Integer.toString(WebSetEnum.wap.getValue()));
  			PageBean listBean = topicService.getTopicBySpu(atc, ipage, isize);
  			item.setData(listBean.getBeanList());
  			item.setPage(listBean.getTp());
  			item.setMaxRow(listBean.getTr());
  			item.setPageIndex(ipage);
  			item.setPageSize(isize);
  			item.setPage(listBean.getTp());
  			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "获取专题内容错误：", e, "/wap/findtopic");
		}
		return item;
	} 
	
	/**
	 * 获取专题内容
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/app/findtopic")
	public @ResponseBody ReusltItem appfindtopic(String ch, String page, String size,String mark,String topicid) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
  				item.setCode(-108);
  				item.setDesc("登录通道参数错误");
  				return item;
  			}
  			Integer marks=StringUtilsEX.ToInt(mark);
  			Integer ipage = StringUtilsEX.ToIntNull(page);
  			Integer isize = StringUtilsEX.ToIntNull(size);
  			if (ipage == null || ipage <= 0) {
  				ipage = 1;
  			}
  			if (isize == null || isize <= 0) {
  				isize = 10;
  			}
  			Api_TopicBySpuCriteria atc = new Api_TopicBySpuCriteria();
  			if (marks>0) {
  				atc.setMark(marks);
			}
  			if (StringUtilsEX.ToInt(topicid)>0) {
				atc.setTopicid(StringUtilsEX.ToInt(topicid));
			}
  			atc.setWebset(Integer.toString(WebSetEnum.app.getValue()));
  			PageBean listBean = topicService.getTopicBySpu(atc, ipage, isize);
  			item.setData(listBean.getBeanList());
  			item.setPage(listBean.getTp());
  			item.setMaxRow(listBean.getTr());
  			item.setPageIndex(ipage);
  			item.setPageSize(isize);
  			item.setPage(listBean.getTp());
  			return item;
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "获取专题内容错误：", e, "/wap/findtopic");
		}
		return item;
	} 
}
