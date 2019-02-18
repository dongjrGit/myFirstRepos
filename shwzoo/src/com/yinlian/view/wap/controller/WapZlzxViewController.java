package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.quartz.utils.StringKeyDirtyFlagMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.AdvertImgAppDto;
import com.yinlian.wap.dto.CityDto;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.CriteriaBaner;
import com.yinlian.wssc.web.po.CriteriaVHpSku;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.SnewsClassExample;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.po.V_Hp_Sku;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.BanerService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.HighSpecialtyService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.TextAbbreviationUtil;
import com.yl.soft.log.LogHandle;

/**
 * wap中绿咨询相关
 *
 */
@Controller
@RequestMapping("/wap")
public class WapZlzxViewController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private HighSpecialtyService highSpecialtyService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private AdverisingService adverisingService;

	@Autowired
	private AdverisingService advertImgService;

	@Autowired
	private ProvinceServcice provinceService;
	@Autowired
	private CityServcie cityServcie;
	@Autowired
	private BanerService banerService;

	// wap发现首页
	@RequestMapping("/find/findindex.html")
	public String findindex(HttpServletRequest request) {
		try {
			List<AdvertImgAppDto> advertisings = new ArrayList<AdvertImgAppDto>();
			advertisings = adverisingService.getListByTypeAndDisplayApi(
					PageMarkType.发现首页.getValue(), 1, WebSetEnum.app.getValue());
			CriteriaBaner criteria = new CriteriaBaner();
			criteria.setType(0);
			criteria.setOrderByClause("sort");
			PageBean pageBean = banerService.selBaner(criteria, 0, 5);
			request.setAttribute("ban",pageBean.getBeanList());
			request.setAttribute("ads", advertisings);
		} catch (Exception e) {
			LogHandle.error(LogType.wap, MessageFormat.format("发现页面异常:{0}", e),
					"find/findindex");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/find_index";
	}

	// app发现首页
	@RequestMapping("/app/findindex.html")
	public String app_findindex(HttpServletRequest request) {
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
			List<AdvertImgAppDto> advertisings = new ArrayList<AdvertImgAppDto>();
			advertisings = adverisingService.getListByTypeAndDisplayApi(
					PageMarkType.发现首页.getValue(), 1, WebSetEnum.app.getValue());
			request.setAttribute("ads", advertisings);
			
		} catch (Exception e) {
			LogHandle.error(LogType.wap, MessageFormat.format("发现页面异常:{0}", e),
					"find/findindex");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		return "/template/app/news/app_findindex";
	}

	/**
	 * APP绿色中国首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/app/lszgindex.html")
	public String appfindlszgindex(Model model,HttpServletRequest request) {
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
			String href=request.getParameter("href");
			if(!StringUtilsEX.IsNullOrWhiteSpace(href)){
				request.setAttribute("href", href);
			} 
			// 实时推荐
			CriteriaVHpSku vhpskucriteria = new CriteriaVHpSku();
			vhpskucriteria.setOrderByClause("sort");
			vhpskucriteria.setType(1);
			List<V_Hp_Sku> vhpskulist = (List<V_Hp_Sku>) highSpecialtyService.getList(vhpskucriteria, 1, 5).getBeanList();
			// 热门城市 专题
			CriteriaTopic topicCriteria = new CriteriaTopic();
			topicCriteria.setSpmark(TopicMarkEnum.地方馆.getValue());
			topicCriteria.setType(TopicTypeEnum.商品.getValue());
			topicCriteria.setWebset(WebSetEnum.pc.getValue() + "");
			topicCriteria.setOrderByClause("id");
			topicCriteria.setSort("desc");
			topicCriteria.setHotis(1);
			List<Topic> rmcslist = (List<Topic>) topicService
					.queryTopicListByCriteria(topicCriteria, 1, 5)
					.getBeanList();

			model.addAttribute("rmcslist", rmcslist);
			model.addAttribute("vhpskulist", vhpskulist);		
			
			List<AdvertImgAppDto> advertisings = advertisings = adverisingService.getListByTypeAndDisplayApi(
					PageMarkType.绿色中国首页.getValue(), 1, WebSetEnum.app.getValue());
			model.addAttribute("adver", advertisings);
		} catch (Exception e) {
			LogHandle
					.error(LogType.wap,
							MessageFormat.format("绿色中国首页页面异常:{0}", e),
							"find/lszgindex");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}

		return "/template/app/news/app_lszg_index";
	}

	// 绿色中国首页
	@RequestMapping("/find/lszgindex.html")
	public String findlszgindex(Model model,String pagetype) {
		try {
			if (pagetype==null) {
				pagetype="findindex";
			}
			// 实时推荐
			CriteriaVHpSku vhpskucriteria = new CriteriaVHpSku();
			vhpskucriteria.setOrderByClause("sort");
			vhpskucriteria.setType(1);
			List<V_Hp_Sku> vhpskulist = (List<V_Hp_Sku>) highSpecialtyService
					.getList(vhpskucriteria, 1, 5).getBeanList();
			// 热门城市 专题
			CriteriaTopic topicCriteria = new CriteriaTopic();
			topicCriteria.setSpmark(TopicMarkEnum.地方馆.getValue());
			topicCriteria.setType(TopicTypeEnum.商品.getValue());
			topicCriteria.setWebset(WebSetEnum.wap.getValue() + "");
			topicCriteria.setOrderByClause("id");
			topicCriteria.setSort("desc");
			topicCriteria.setHotis(1);
			List<Topic> rmcslist = (List<Topic>) topicService.queryTopicListByCriteria(topicCriteria, 1, 5).getBeanList();
			model.addAttribute("rmcslist", rmcslist);
			model.addAttribute("vhpskulist", vhpskulist);
			model.addAttribute("pagetype", pagetype);
		} catch (Exception e) {
			LogHandle
					.error(LogType.wap,
							MessageFormat.format("绿色中国首页页面异常:{0}", e),
							"find/lszgindex");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/lszg_index";
	}

	// APP中绿专区
	@RequestMapping("/app/findzlzq.html")
	public String appfindzlzq(HttpServletRequest request) {
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
			String href=request.getParameter("href");
			if(StringUtilsEX.IsNullOrWhiteSpace(href)){
				request.setAttribute("href", href);
			}
			List<SnewsClassExample> clist = new ArrayList<SnewsClassExample>();
			List<Province> provinces = new ArrayList<Province>();
			clist = newsService.queryClassTree(0, 5);// 获取中绿专区分类
			provinces = provinceService.query();
			request.setAttribute("provinces", provinces);
			request.setAttribute("clist", clist);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("中绿专区页面异常:{0}", e), "find/findzlzq");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}

		return "/template/app/news/app_find_zlzq";
	}
	// App中绿专区地图
	@RequestMapping("/app/findzlzqmap.html")
	public String appfindzlzqmap(HttpServletRequest request) {
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果

		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("中绿专区地图页面异常:{0}", e),
					"find/findzlzqmap");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}

		return "/template/app/news/app_find_zlzqmap";
	}
	// 中绿专区
	@RequestMapping("/find/findzlzq.html")
	public String findzlzq(HttpServletRequest request,String pagetype) {
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(pagetype)) {
				pagetype="findindex";
			}
			List<SnewsClassExample> clist = new ArrayList<SnewsClassExample>();
			List<Province> provinces = new ArrayList<Province>();
			clist = newsService.queryClassTree(0, 5);// 获取中绿专区分类
			provinces = provinceService.query();
			request.setAttribute("provinces", provinces);
			request.setAttribute("clist", clist);
			request.setAttribute("pagetype", pagetype);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("中绿专区页面异常:{0}", e), "find/findzlzq");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/find_zlzq";
	}

	// 中绿专区地图
	@RequestMapping("/find/findzlzqmap.html")
	public String findzlzqmap(HttpServletRequest request) {
		try {

		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("中绿专区地图页面异常:{0}", e),
					"find/findzlzqmap");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/find_zlzqmap";
	}

	// 地方馆区域列表
	@RequestMapping("/find/dfgqylb.html")
	public String finddfgqylb(Model model) {
		try {
			List<City> critylist = cityServcie.query();
			List<CityDto> cityDtos = new ArrayList<CityDto>();

			for (City crity : critylist) {
				/**
				 * 获取市名称第一个字并获取汉字拼音第一个字母转大写
				 */
				String letter = TextAbbreviationUtil.cn2py(
						crity.getName().substring(0, 1)).toUpperCase();

				/**
				 * 用于判断字母集合是否存在
				 */
				boolean is = false;

				/**
				 * 遍历字典集合 有责添加集合内容
				 */
				for (CityDto citys : cityDtos) {
					if (citys.getLetter().equals(letter)) {
						is = true;
						citys.getList().add(crity);
						break;
					} else {
						continue;
					}
				}

				/**
				 * 判断这条信息是否在字典集合有相匹配的 无责添加
				 */
				if (!is) {
					CityDto cityDto = new CityDto();
					cityDto.setLetter(letter);
					cityDto.getList().add(crity);
					cityDtos.add(cityDto);
				}
			}
			cityDtos.sort((p1, p2) -> p1.getLetter().compareToIgnoreCase(
					p2.getLetter()));
			model.addAttribute("citylist", cityDtos);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("地方馆区域列表页面异常:{0}", e), "find/dfgqylb");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/dfg_qyxz";
	}
	
	// 地方馆区域列表
		@RequestMapping("/app/dfgqylb.html")
		public String appdfgqylb(Model model,String procode,HttpServletRequest request) {
			try {
				int ch=1;
				if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
					ch=StringUtilsEX.ToInt(request.getParameter("ch"));
				}
				request.setAttribute("ch", ch);//1安卓，2苹果
				//获取所有大区
				List<Province> provinces=provinceService.selectAll();
				Map<String, List<Province>> maplist=provinces.stream().collect(Collectors.groupingBy(Province::getBigarea));
				model.addAttribute("citylist", maplist);
				model.addAttribute("procode", procode);
			} catch (Exception e) {
				LogHandle.error(LogType.wap,
						MessageFormat.format("地方馆区域列表页面异常:{0}", e), "find/dfgqylb");
				return ErrorRedirect.getInstance().wapRedirect("页面错误");
			}

			return "/template/app/news/app_dfg_qyxz";
		}

	// 地方馆详细（包含特产名录 文化特色 优质特产 放一个页面）
	@RequestMapping("/find/dfginfo.html")
	public String finddfgtcml(Model model, Integer citycode, String cityname) {
		// 广告1
		try {
			List<Advertising> ad1List = advertImgService
					.getListByTypeAndDisplay(PageMarkType.绿色中国地方官.getValue(),
							1, WebSetEnum.wap.getValue());
			model.addAttribute("imglist", ad1List);
			List<Province> provinceslist = provinceService.query();
			model.addAttribute("provinceslist", provinceslist);
			model.addAttribute("citycode", citycode);
			model.addAttribute("cityname", cityname);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("地方馆详细页面异常:{0}", e), "find/dfginfo");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/dfg_yztc";
	}
	
	
	@RequestMapping("/app/dfginfo.html")
	public String appdfgtcml(Model model,String topicid, Integer citycode, String cityname,HttpServletRequest request) {
		// 广告1
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
			List<Advertising> ad1List = advertImgService
					.getListByTypeAndDisplay(PageMarkType.绿色中国地方官.getValue(),
							1, WebSetEnum.wap.getValue());
			model.addAttribute("imglist", ad1List);
			List<Province> provinceslist = provinceService.query();
			model.addAttribute("provinceslist", provinceslist);
			model.addAttribute("citycode", citycode);
			model.addAttribute("cityname", cityname);
			model.addAttribute("topicid", topicid);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("地方馆详细页面异常:{0}", e), "find/dfginfo");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		return "/template/app/news/app_dfg_yztc";
	}
	@RequestMapping("/app/dfg.html")
	public String dfg(Model model,String proid,String procode, Integer citycode, String cityname,HttpServletRequest request) {
		// 广告1
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
			List<Advertising> ad1List = advertImgService
					.getListByTypeAndDisplay(PageMarkType.绿色中国地方官.getValue(),
							1, WebSetEnum.wap.getValue());
			model.addAttribute("imglist", ad1List);
			List<Province> provinceslist = provinceService.query();
			model.addAttribute("provinceslist", provinceslist);
			model.addAttribute("citycode", citycode);
			model.addAttribute("cityname", cityname);
			model.addAttribute("proid", proid);
			model.addAttribute("procode", procode);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,MessageFormat.format("地方馆详细页面异常:{0}", e), "find/dfginfo");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		return "/template/app/news/app_dfg_citylist";
	}

	/**
	 * 特产大全
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/find/lszgtcdq.html")
	public String lszgtcdq(Model model) {
		try {
		} catch (Exception e) {
			LogHandle.error(LogType.wap, MessageFormat.format("特产大全异常:{0}", e),
					"find/lszgtcdq");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/lszg_tcdqs";
	}
	
	/**
	 * App特产大全
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/app/lszgtcdq.html")
	public String applszgtcdq(Model model,HttpServletRequest request) {
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
		} catch (Exception e) {
			LogHandle.error(LogType.wap, MessageFormat.format("特产大全异常:{0}", e),
					"find/lszgtcdq");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		return "/template/app/news/app_lszg_tcdqs";
	}
	// app发现包邮直送
	@RequestMapping("/app/findbyzs.html")
	public String appfindbyzs(HttpServletRequest request) {
		try {			
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
			List<AdvertImgAppDto> advertisings = new ArrayList<AdvertImgAppDto>();
			advertisings = adverisingService.getListByTypeAndDisplayApi(
					PageMarkType.包邮直送.getValue(), 1, WebSetEnum.app.getValue());
			request.setAttribute("ads", advertisings);// 包邮直送广告
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("发现包邮直送页面异常:{0}", e), "find/findbyzs");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}

		return "/template/app/news/app_find_byzs";
	}
	// app每日鲜
	@RequestMapping("/app/mrx.html")
	public String appfindmrx(HttpServletRequest request) {
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
			List<AdvertImgAppDto> advertisings = new ArrayList<AdvertImgAppDto>();
			advertisings = adverisingService.getListByTypeAndDisplayApi(
					PageMarkType.每日鲜.getValue(), 1, WebSetEnum.app.getValue());
			request.setAttribute("ads", advertisings);// 广告
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("发现包邮直送页面异常:{0}", e), "find/findbyzs");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}

		return "/template/app/news/app_find_mrx";
	}
	//wap每日鲜
	@RequestMapping("/wap/mrx.html")
	public String wapfindmrx(HttpServletRequest request) {
		try {				
			List<AdvertImgAppDto> advertisings = new ArrayList<AdvertImgAppDto>();
			advertisings = adverisingService.getListByTypeAndDisplayApi(
					PageMarkType.每日鲜.getValue(), 0, WebSetEnum.wap.getValue());
			request.setAttribute("ads", advertisings);// 包邮直送广告
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("发现包邮直送页面异常:{0}", e), "find/findbyzs");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/find_mrx";
	}
	// 发现包邮直送
	@RequestMapping("/find/findbyzs.html")
	public String findbyzs(HttpServletRequest request) {
		try {
			List<AdvertImgAppDto> advertisings = new ArrayList<AdvertImgAppDto>();
			advertisings = adverisingService.getListByTypeAndDisplayApi(
					PageMarkType.包邮直送.getValue(), 0, WebSetEnum.wap.getValue());
			request.setAttribute("ads", advertisings);// 包邮直送广告
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("发现包邮直送页面异常:{0}", e), "find/findbyzs");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/find_byzs";
	}
	/**
	 * app资讯详情
	 * 
	 * @param id
	 * @param mark
	 *            1.中绿专区、中绿资讯-资讯详情 2.绿色故事-资讯详情 用于跳转与页面跳转 9.消息详情
	 * @return
	 */
	@RequestMapping("/app/newsdetail.html")
	public String appfindnewsinfo(Integer id, Integer mark, Model model,String token,HttpServletRequest request) {
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
			SessionUser user=SessionState.GetCurrentUser(token);
	  		if(user.getCode()>=0){
	  			model.addAttribute("token", token);
	  		}else{
	  			model.addAttribute("token", "false");
	  		}
			SnewsWithBLOBs news = newsService.getById(id);
			if(news.getEx1()==null){
				news.setEx1(1);
			}else{
				news.setEx1(news.getEx1()+1);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(news.getEx2())) {
				news.setContent(news.getEx2());
			}
			newsService.updateSlistById(news);
			model.addAttribute("vo", news);
			model.addAttribute("mark", mark);			
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("资讯详情页面异常:{0}", e), "app/newsdetail");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}

		return "/template/app/news/app_newsdetail";
	}
	/**
	 * 资讯详情
	 * 
	 * @param id
	 * @param mark
	 *            1.中绿专区、中绿资讯-资讯详情 2.绿色故事-资讯详情 用于跳转与页面跳转
	 * @return
	 */
	@RequestMapping("/find/newsdetail.html")
	public String findnewsinfo(Integer id, Integer mark, Model model,String token) {
		try {
			SnewsWithBLOBs news = newsService.getById(id);
			if(news.getEx1()==null){
				news.setEx1(1);
			}else{
				news.setEx1(news.getEx1()+1);
			}
			newsService.updateSlistById(news);
			model.addAttribute("vo", news);
			model.addAttribute("token",token);
			model.addAttribute("mark", mark);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("资讯详情页面异常:{0}", e), "find/newsdetail");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/newsdetail";
	}

	// 绿色故事
	@RequestMapping("/find/lszglsgs.html")
	public String lszglsgs(HttpServletRequest request) {
		try {

		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("绿色故事页面异常:{0}", e), "find/lszglsgs");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/lszg_lsgs";
	}
	
	@RequestMapping("/app/lszglsgs.html")
	public String applszglsgs(HttpServletRequest request) {
		try {
			int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("绿色故事页面异常:{0}", e), "find/lszglsgs");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		return "/template/app/news/app_lszg_lsgs";
	}

	//热门地区列表
	@RequestMapping("/dfg/rmdq.html")
	public String waprmdq(HttpServletRequest request,String ch,Integer id) {
		try {
			request.setAttribute("id", id);
			request.setAttribute("ch", ch);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,
					MessageFormat.format("热门地区页面异常:{0}", e), "find/waprmdq");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		//return "redirect:/wap/404.html";
		return "/template/wap/news/dfg_rmdq";
	}
	/**
	 * 地方官线详情
	 * @return
	 */
	@RequestMapping("/app/dfg/info.html")
	public String dfginfo(String id,String name,Model model,String ch){
		try {
			Integer sch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(ch)){
				sch=StringUtilsEX.ToInt(ch);
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				return ErrorRedirect.getInstance().wapRedirect("地方官ID错误");
			}
			model.addAttribute("ch", sch);
			Api_TopicBySpuCriteria atc = new Api_TopicBySpuCriteria();
			atc.setMark(TopicMarkEnum.地方馆.getValue());
			atc.setWebset(Integer.toString(WebSetEnum.app.getValue()));
			atc.setTopicid(StringUtilsEX.ToInt(id));
			model.addAttribute("dfglist", topicService.getTopicBySpu(atc, 1, 10).getBeanList());
			model.addAttribute("dfgid", id);
			model.addAttribute("name", name);
		} catch (Exception e) {
			LogHandle.error(LogType.wap,"热门地区页面异常:{0}", e, "/app/dfg/info");
			return ErrorRedirect.getInstance().wapRedirect("页面错误");
		}
		return "/template/app/news/app_find_dfg_info";
	}
	
}
