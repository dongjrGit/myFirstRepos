package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.search.Pc_TopicCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaSnews;
import com.yinlian.wssc.web.po.CriteriaVHpSku;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Snews;
import com.yinlian.wssc.web.po.users_news;
import com.yinlian.wssc.web.po.users_newsExample;
import com.yinlian.wssc.web.service.HighSpecialtyService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.users_newsService;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

//中绿咨询相关
@RestController
@RequestMapping("/wap/zlzx")
public class WapZlzxController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private ProvinceServcice provincesercive;
	@Autowired
	private TopicService topicService;
	@Autowired
	private HighSpecialtyService highSpecialtyService;
	@Autowired
	private users_newsService usersNewsService;

	/**
	 * 中绿专区
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param cid
	 *            分类id
	 * @param isrecommend
	 *            是否推荐 0 推荐，1不推荐
	 * @param istop
	 *            是否置顶 （用来代替人气条件）0置顶，1不置顶
	 * @param provincecode
	 *            省code
	 * @param citycode
	 *            市code
	 * @param areacode
	 *            区code
	 * @return
	 */
	@RequestMapping("/getwapzqpagelist")
	public ReusltItem getWapZQPageList(String pageindex, String pagesize,
			String cid, String isrecommend, String istop, String provincecode,
			String citycode, String areacode) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(pageindex);
			index = index == -1 ? 1 : index;
			int size = StringUtilsEX.ToInt(pagesize);
			size = size == -1 ? 10 : size;
			CriteriaSnews criteria = new CriteriaSnews();

			criteria.setState(0);// 新闻0为发布，1为未发布
			if (StringUtilsEX.ToInt(cid) > 0) {
				criteria.setCid(StringUtilsEX.ToInt(cid));
			}
			if (StringUtilsEX.ToInt(isrecommend) >= 0) {
				criteria.setIsrecommend(StringUtilsEX.ToInt(isrecommend));
			}
			if (StringUtilsEX.ToInt(istop) >= 0) {
				criteria.setIstop(StringUtilsEX.ToInt(istop));
			}
			criteria.setProvince(provincecode);
			criteria.setCity(citycode);
			criteria.setArea(areacode);
			criteria.setCtype(11);// 后台菜单 type=11
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");

			PageBean pageBean = newsService.querySlist(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());			
			item.setPageIndex(pageBean.getPc());
			item.setPage(pageBean.getTp());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.wap,
					MessageFormat.format("获取中绿专区列表信息异常! 异常信息:{0}",
							e.getMessage()), "/zlzx/getwapzqpagelist");
		}
		return item;
	}

	/**
	 * 获取新闻列表的省字段
	 * 
	 * @return
	 */
	@RequestMapping("/getProvinceList")
	public ReusltItem getProvinceList() {
		ReusltItem item = new ReusltItem();
		try {
			List<Snews> list = newsService.getProvinceList();
			List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			for (Snews snews : list) {
				List<Province> l = provincesercive.selectProvinceByCode(snews
						.getProvince());
				Map<String, Object> map = new HashMap<String, Object>();
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
			LogHandle.error(
					LogType.wap,
					MessageFormat.format("查询 中绿专区分类异常! 异常信息:{0}",
							e.getMessage()), "/zlzx/getProvinceList");
		}
		return item;
	}

	/**
	 * 根据省的code获取新闻详情
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping("/getdetailByProvince")
	public ReusltItem getdetailByProvince(String code) {
		ReusltItem item = new ReusltItem();
		try {
			List<Snews> news = newsService.getProvincebyCode(StringUtilsEX
					.ToInt(code));
			item.setData(news);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("系统错误");
		}
		return item;
	}

	/**
	 * 绿色故事
	 * 
	 * @author kh.wang
	 * @since 2016年11月7日
	 * @param page
	 *            页
	 * @param pagesize
	 *            页大小
	 * @param ctype
	 * @return
	 */
	@RequestMapping("/querylsgs")
	public ReusltItem querylsgs(Integer page, Integer pagesize) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaSnews criteria = new CriteriaSnews();
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
			criteria.setCtype(5);
			criteria.setState(0);
			PageBean pageBean = newsService.querySlist(criteria, page, pagesize);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setPage(pageBean.getTp());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "获取绿色故事异常", e, "/zlzx/querylist");
		}
		return item;
	}

	/**
	 * 热门城市
	 * 
	 * @param page
	 * @param pagesize
	 * @param 
	 * @return
	 */
	@RequestMapping("/queryrmcs")
	public ReusltItem queryrmcs(Integer page,String hotis,String proid, Integer size) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaTopic topicCriteria = new CriteriaTopic();
			topicCriteria.setSpmark(TopicMarkEnum.地方馆.getValue());
			topicCriteria.setType(TopicTypeEnum.商品.getValue());
			topicCriteria.setWebset(WebSetEnum.pc.getValue() + "");
			topicCriteria.setOrderByClause("id");
			topicCriteria.setSort("desc");
			if (!StringUtilsEX.IsNullOrWhiteSpace(proid)) {
				topicCriteria.setProvinceid(StringUtilsEX.ToInt(proid));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(hotis)) {
				topicCriteria.setHotis(1);
			}
			PageBean pageBean = topicService.queryTopicListByCriteria(topicCriteria, page, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPage(pageBean.getTp());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "获取热门城市异常", e, "/zlzx/queryrmcs");
		}
		return item;
	}

	/***
	 * 获取中绿资讯分页列表
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getpagelist")
	public ReusltItem getZXPageList(String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(page) <= 0 ? 1 : StringUtilsEX
					.ToInt(page);
			int pagesize = StringUtilsEX.ToInt(size) <= 0 ? 9 : StringUtilsEX
					.ToInt(size);
			CriteriaSnews cs = new CriteriaSnews();
			cs.setCtype(10);
			cs.setState(0);//0发布，1未发布
			cs.setOrderByClause("createtime");
			cs.setSort("desc");
			PageBean pageBean = newsService.querySlist(cs, index, pagesize);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPage(pageBean.getTp());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.wap, MessageFormat.format(
					"获取中绿资讯信息异常! 异常信息:{0}", e.getMessage()),
					"/zlzx/getZXPageList");
		}
		return item;
	}
	/**
	 * 中绿味道
	 * @param pageindex
	 * @param pagesize
	 * @param ctype 专家讲堂13，菜谱大全14，厨房百科15 
	 * @param order 排序 0收藏 1人气 2时间
	 * @param isdesc 降序 1 升序0
	 * @return
	 */
	@RequestMapping("/getwdpagelist")	
	public ReusltItem getWDPageList(String page, String size, String ctype,String order,String isdesc) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(page);
			index = index == -1 ? 1 : index;
			int si = StringUtilsEX.ToInt(size);
			si = si == -1 ? 10 : si;
			CriteriaSnews criteria = new CriteriaSnews();
		    String descstr="desc";
		    String orderstr="createtime";
		    criteria.setState(0);//新闻0为发布，1为未发布
			criteria.setCtype(StringUtilsEX.ToInt(ctype));
					
			if(StringUtilsEX.ToInt(order)>=0){
				switch (StringUtilsEX.ToInt(order)) {
				case 0:
					orderstr="yconut";
					break;
				case 1:
					orderstr="ex1";
					break;
				case 2:
					orderstr="createtime";
					break;
				default:
					break;
				}
			}
			if(StringUtilsEX.ToInt(isdesc)>=0){
				if(StringUtilsEX.ToInt(isdesc)==1)
				{descstr="desc";}
				else{descstr="asc";}
			}
			criteria.setOrderByClause(orderstr);	
			criteria.setSort(descstr);			
			PageBean pageBean = newsService.querySlist(criteria, index, si);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPage(pageBean.getTp());
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
	 * 优质特产
	 * @param page
	 * @param size
	 * @param province 省ID
	 * @return
	 */
	@RequestMapping("/dfgyztc")
	public ReusltItem dfgyztc(Integer page,Integer size,Integer citycode) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaVHpSku vhpskucriteria = new CriteriaVHpSku();
			vhpskucriteria.setOrderByClause("sort");
			vhpskucriteria.setType(1);
			vhpskucriteria.setCitycode(citycode);
			
			PageBean list=highSpecialtyService.getList(vhpskucriteria, page, size);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPageIndex(list.getPc());
			item.setPage(list.getTp());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.pc,"优质特产异常", e,"/pc/index/dfgyztc");
		}
		return item;
	}
	
	/**
	 * 文化特色 或 特产名录
	 * @param type 4.文化特色3.特产名录
	 * @return
	 */
	@RequestMapping("/dfgwhts")
	public ReusltItem dfgwhts(Integer page,Integer size,String procode,Integer type,String citycode) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaSnews criteria = new CriteriaSnews();
			criteria.setCtype(type);
			criteria.setOrderByClause("createtime");
			criteria.setSort("desc");
			if (StringUtilsEX.ToInt(citycode)>0) {
				criteria.setCity(citycode);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(procode)) {
				criteria.setProvince(procode);
			}
			PageBean pageBean = newsService.querySlist(criteria, page, size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setPage(pageBean.getTp());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.pc,"优质特产异常", e,"/pc/index/dfgyztc");
		}
		return item;
	}
	/**
	 * 获取wap包邮直送商品专题列表
	 * @param index
	 * @param size
	 * @param orderby 排序 1：评论数 2：创建时间 3：价格
	 * @return
	 */
	@RequestMapping("/getWapTopicList")
	public ReusltItem getWapTopicList(String page, String size,Integer orderby,Integer mark) {
		ReusltItem item = new ReusltItem();
		String logpath = "/zlzx/getWapTopicList";
		try {
			Pc_TopicCriteria criteria=new Pc_TopicCriteria();
			if(mark==1){
				criteria.setMark(TopicMarkEnum.每日鲜.getValue());
				criteria.setPagetag(PageMarkType.每日鲜.getValue());
				criteria.setType(TopicTypeEnum.商品.getValue());
			}else{
				criteria.setMark(TopicMarkEnum.包邮直送.getValue());
				criteria.setPagetag(PageMarkType.包邮直送.getValue());
				criteria.setType(TopicTypeEnum.商品.getValue());
			}
			
			criteria.setWebset(Integer.toString(WebSetEnum.app.getValue()));
			if(orderby!=null){
				switch (orderby) {
				case 1:
					criteria.setOrderByClause(" p.CommentCount");
					break;
				case 2:
					criteria.setOrderByClause(" CreateTime ");
					break;
				case 3:
					criteria.setOrderByClause("  price desc ");
					break;
				default:
					criteria.setOrderByClause(" CreateTime ");
					break;
				}
			}
			PageBean list=topicService.getPcTopByPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPageIndex(list.getPc());
			item.setPage(list.getTp());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.wap,
					MessageFormat.format("获取包邮直送专题异常! 异常信息:{0}", e), logpath);
		}
		return item;
	}
	
	/**
	 * 特产大全
	 * @param page
	 * @param pagesize
	 * @param fl 分类ID
	 * @param min 最小价格
	 * @param max 最大价格
	 * @return
	 */
	@RequestMapping("/tsdqpro")
	public ReusltItem querytsdqspu(Integer page, Integer size,String orderByClause,String sort){
		ReusltItem item = new ReusltItem();
		try {
			Pc_TopicCriteria criteria=new Pc_TopicCriteria();
			criteria.setMark(TopicMarkEnum.特色大全.getValue());
			criteria.setType(TopicTypeEnum.专题分类.getValue());
			if (!StringUtilsEX.IsNullOrWhiteSpace(orderByClause)&&!StringUtilsEX.IsNullOrWhiteSpace(sort)) {
				criteria.setOrderByClause(orderByClause);
				criteria.setSort(sort);
			}
			PageBean list=topicService.getPcTopByPage(criteria, page, size);
			item.setData(list.getBeanList());
			item.setMaxRow(list.getTr());
			item.setPageIndex(list.getPc());
			item.setPage(list.getTp());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"特产大全异常",e,"/lszg/querytsdqspu");
		}
		return item;
	}
	
	@RequestMapping("/getusercpList")
	public ReusltItem getusercpList(String token){
		ReusltItem item = new ReusltItem();
		SessionUser user=SessionState.GetCurrentUser(token);
  		if(user.getCode()>=0){
  			List<users_news>usernews=new ArrayList<users_news>();
  			usernews=usersNewsService.getnewsByUserid(user.getUserId());
  			item.setData(usernews);
  			item.setCode(0);
  		}else{
  			item.setCode(-1);
  			item.setDesc("获取失败");
  		}
		return item;
	}
	
	/**
	 * 获取专题内容
	 * @param ch 通道
	 * @param page 当前页
	 * @param size 页大小
	 * @param mark 专题标识 
	 * @param topicid 专题ID
	 */
	@RequestMapping("/findtopic")
  	public  ReusltItem findtopic(String ch, String page,String topicid,String size,String mark) {
  		ReusltItem item = new ReusltItem();
  		try {
  			if (!StringUtilsEX.isChannelTypeExist(ch)) {
  				item.setCode(-108);
  				item.setDesc("登录通道参数错误");
  				return item;
  			}
  			Integer marks=StringUtilsEX.ToInt(mark);
  			if (marks <= 0) {
				item.setCode(-109);
				item.setDesc("专题标识参数错误");
				return item;
			}
  			if (StringUtilsEX.IsNullOrWhiteSpace(topicid)) {
				item.setCode(-109);
				item.setDesc("专题ID参数错误");
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
  			Api_TopicBySpuCriteria atc = new Api_TopicBySpuCriteria();
  			atc.setWebset(Integer.toString(WebSetEnum.app.getValue()));
  			atc.setMark(StringUtilsEX.ToInt(mark));
  			atc.setTopicid(StringUtilsEX.ToInt(topicid));
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
			LogHandle.error(LogType.Api,"获取专题错误：{0}", e,"/topic/findtopic");
  		}
  		return item;
  	}
	
}
