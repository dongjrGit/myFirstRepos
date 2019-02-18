package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.userNewsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaSnews;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Snews;
import com.yinlian.wssc.web.po.SnewsClassExample;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.po.users_news;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.users_newsService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/zlnews")
public class PcZLZXController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private ProvinceServcice provincesercive;
	@Autowired
	private users_newsService usernewsSercive;
	/***
	 * 获取中绿资讯分页列表
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/zlzx/getpagelist")
	public ReusltItem getZXPageList(String page, String size){
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(page) <= 0 ? 1 : StringUtilsEX.ToInt(page);
			int pagesize = StringUtilsEX.ToInt(size) <= 0 ? 9 : StringUtilsEX.ToInt(size);
			CriteriaSnews cs = new CriteriaSnews();
			cs.setCtype(10);
			cs.setOrderByClause("createtime");
			cs.setSort("desc");
			cs.setState(0);
			PageBean pageBean = newsService.querySlist(cs, index, pagesize);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.news, MessageFormat.format("获取中绿资讯信息异常! 异常信息:{0}", e.getMessage()),
					"/zlzx/getZXPageList");
		}
		return item;
	}
	
	
	/**
	 * 中绿味道
	 * @param pageindex
	 * @param pagesize
	 * @param ctype 专家讲堂13，菜谱大全14，厨房百科15 
	 * @return
	 */
	@RequestMapping("/zlwd/getwdpagelist")	
	public ReusltItem getWDPageList(String pageindex, String pagesize, String ctype) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			CriteriaSnews criteria = new CriteriaSnews();
		
		    criteria.setState(0);//新闻0为发布，1为未发布

			criteria.setCtype(StringUtilsEX.ToInt(ctype));
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
						
			PageBean pageBean = newsService.querySlist(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.news, MessageFormat.format("获取中绿味道列表信息异常! 异常信息:{0}", e.getMessage()),
					"/zlwd/getwdpagelist");
		}
		return item;
	}
	
	/**
	 * 中绿专区
	 * @param pageindex
	 * @param pagesize
	 * @param cid 分类id
	 * @param isrecommend 是否推荐 0 推荐，1不推荐
	 * @param istop 是否置顶 （用来代替人气条件）0置顶，1不置顶
	 * @param provincename 省名称
	 * @param cityname 市名称
	 * @param areaname 区名称
	 * @return
	 */
	@RequestMapping("/zlzq/getzqpagelist")	
	public ReusltItem getZQPageList(String pageindex, String pagesize,String cid,String isrecommend,String istop,
			String provincename,String cityname,String areaname) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			CriteriaSnews criteria = new CriteriaSnews();
		
		    criteria.setState(0);//新闻0为发布，1为未发布
		    if(StringUtilsEX.ToInt(cid)>0){
		    	criteria.setCid(StringUtilsEX.ToInt(cid));
		    }
		    if(StringUtilsEX.ToInt(isrecommend)>=0){
		    	criteria.setIsrecommend(StringUtilsEX.ToInt(isrecommend));
		    }
		    if(StringUtilsEX.ToInt(istop)>=0){
		    	criteria.setIstop(StringUtilsEX.ToInt(istop));
		    }
		    criteria.setProvince(provincename);
		    criteria.setCity(cityname);
		    criteria.setArea(areaname);
			criteria.setCtype(11);
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
						
			PageBean pageBean = newsService.querySlist(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.news, MessageFormat.format("获取中绿专区列表信息异常! 异常信息:{0}", e.getMessage()),
					"/zlzq/getzqpagelist");
		}
		return item;
	}
	/**
	 * 获取 中绿专区分类
	 * @return
	 */
	@RequestMapping("/zlzq/getzqclass")
	public ReusltItem getZQClass() {
		ReusltItem item = new ReusltItem();
		try {
			List<SnewsClassExample> list = newsService.queryClassTree(0, 5);
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.news, MessageFormat.format("查询 中绿专区分类异常! 异常信息:{0}", e.getMessage()),
					"/zlzq/getzqclass");
		}
		return item;
	}
	/**
	 * 获取 新闻详情
	 * @return
	 */
	@RequestMapping("/newsinfo/getnewsbyid")
	public ReusltItem getNewsByID(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if(StringUtilsEX.ToInt(id)<0){
				item.setCode(-101);
				item.setDesc("新闻id错误");
				return item;
			}
			SnewsWithBLOBs news = newsService.selSlistById(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(news);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.news, MessageFormat.format("查询 获取新闻详情异常! 异常信息:{0}", e.getMessage()),
					"/newsinfo/getnewsbyid");
		}
		return item;
	}
	/**
	 * 获取新闻列表的省字段
	 * @return
	 */
	@RequestMapping(value="/getProvinceList", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getProvinceList() {
		ReusltItem item = new ReusltItem();
		try {
			List<Snews> list=newsService.getProvinceList();
			List<Map<String, Object>> maps=new ArrayList<Map<String,Object>>();
			for (Snews snews : list) {
				List<Province> l=provincesercive.selectProvinceByCode(snews.getProvince());
				Map<String, Object>map=new HashMap<String, Object>();
				for (Province province : l) {
					map.put("name", province.getName());
					map.put("code", province.getCode());
				}
				map.put("precision", snews.getEx5());
				map.put("latitude", snews.getEx6());
				maps.add(map);
			}
			item.setData(maps);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.news, MessageFormat.format("查询 中绿专区分类异常! 异常信息:{0}", e.getMessage()),
					"/zlzq/getzqclass");
		}
		return item.toJson();
	}
	/**
	 * 根据省的code获取新闻详情
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/getdetailByProvince", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getdetailByProvince(String code) {
		ReusltItem item = new ReusltItem();
		try {
			
			List<Snews> news =newsService.getProvincebyCode(StringUtilsEX.ToInt(code));
			item.setData(news);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
		}
		return item.toJson();
	}
	@RequestMapping(value="/getscUser", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getscUser(HttpServletRequest request) throws Exception {
		ReusltItem item = new ReusltItem();
		Integer userid=SessionUtil.getSessionUserId(request);
		try {
			userNewsCriteria cri=new userNewsCriteria();
			cri.setUserid(userid);
			PageBean list =usernewsSercive.usersNewslistPage(cri, 1, 8);
			item.setData(list.getBeanList());			
			item.setCode(0);
			item.setDesc("获取数据成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
		}
		return item.toJson();
	}
	@RequestMapping(value="/getscCount", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getscCount(HttpServletRequest request) throws Exception {
		ReusltItem item = new ReusltItem();
		try {
			List<users_news> list=usernewsSercive.selectscCount();
			item.setData(list);			
			item.setCode(0);
			item.setDesc("获取数据成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
		}
		return item.toJson();
	}
	
	
	
}
