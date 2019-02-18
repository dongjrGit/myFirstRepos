package com.yinlian.view.pc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_SeachAtrrDto;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.dto.FloorDto;
import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.mapper.SnewsClassMapper;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Searchkey;
import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.FloorService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.ProductService;
import com.yinlian.wssc.web.service.SearchkeyService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.WebsitesConfigService;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
public class PCIndexViewController {
	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request) {
		return "/platform/user/login";
}
	
//	@Autowired
//	private CategoryService categoryService;
//	@Autowired
//	private ProductService productService;
//	@Autowired
//	private AdverisingService adservice;
//	@Autowired
//	private TopicService topservice;
//	@Autowired
//	private FloorService flservice;
//	@Autowired
//	private ArticlesService articlesService;
//	@Autowired
//	private WebsitesConfigService websitesConfigService; 
//	@Autowired
//	private SnewsClassMapper snewsclassMapper;
//	@Autowired
//	private SearchkeyService searchkeyService;
//	@Autowired
//	private NewsService news;
//	@Autowired
//	private AdvertImgService advertimgService;
//	@RequestMapping("/index.html")
//	public String index(Model model, HttpServletRequest request) {
//		try {
//			initHead(model,request);
//			intitNav(model);
//			initFoot(model);
//			//获取广告
//			//首页顶部分类广告 位置100
//			List<Advertising> adclass=adservice.getListByTypeAndDisplay(PageMarkType.首页.getValue(), 100, WebSetEnum.pc.getValue());
//			//首页顶部广告 位置0
//			List<Advertising> adtop=adservice.getListByTypeAndDisplay(PageMarkType.首页.getValue(), 0, WebSetEnum.pc.getValue());
//			//首页banner广告 位置1
//			List<Advertising> adlist=adservice.getListByTypeAndDisplay(PageMarkType.首页.getValue(), 1, WebSetEnum.pc.getValue());
//			//首页楼层上广告 位置2
//			List<Advertising> adlist2=adservice.getListByTypeAndDisplay(PageMarkType.首页.getValue(), 2, WebSetEnum.pc.getValue());
//			//获取主题
//			List<Api_TopicBySpuDto> topspuTM= topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.超值特卖.getValue(), 			
//					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
//			List<Api_TopicBySpuDto> topspuSS= topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.新品尝鲜.getValue(), 			
//					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
//			List<Api_TopicBySpuDto> topspuHH= topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.爆款好货.getValue(), 			
//					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
//			List<Api_TopicBySpuDto> topspuQG= topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.限时抢购.getValue(), 			
//					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
//			List<Api_TopicBySpuDto> topspuTJ= topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.精选推荐.getValue(),			
//					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),9);
//			Topic tps=topservice.selectByMark(TopicMarkEnum.精选推荐.getValue());
//			 
//			
//			//存放超值特卖左边的位置（排序第一个）
//			Api_TopicBySpuDto tpartone=new Api_TopicBySpuDto();
//			List<Api_TopicBySpuDto> tparttwoTM=new ArrayList<Api_TopicBySpuDto>();		
//			if(topspuTM.size()>0){
//				Integer i=1;
//				for (Api_TopicBySpuDto tp : topspuTM) {
//					if(i>1){
//						tparttwoTM.add(tp);
//					}else{
//						tpartone=tp;
//					}
//					i++;
//				}
//			}
//			Api_TopicBySpuDto tpartoneSS=new Api_TopicBySpuDto();
//			List<Api_TopicBySpuDto> tparttwoSS=new ArrayList<Api_TopicBySpuDto>();		
//			if(topspuSS.size()>0){
//				Integer i=1;
//				for (Api_TopicBySpuDto tp : topspuSS) {
//					if(i>1){
//						tparttwoSS.add(tp);
//					}else{
//						tpartoneSS=tp;
//					}
//					i++;
//				}
//			}
//			Api_TopicBySpuDto tpartonehh=new Api_TopicBySpuDto();
//			List<Api_TopicBySpuDto> tparttwoHH=new ArrayList<Api_TopicBySpuDto>();		
//			if(topspuHH.size()>0){
//				Integer k=1;
//				for (Api_TopicBySpuDto tp : topspuHH) {
//					if(k>1){
//						tparttwoHH.add(tp);
//					}else{
//						tpartonehh=tp;
//					}
//					k++;
//				}
//			}
//			Api_TopicBySpuDto tpartonetj=new Api_TopicBySpuDto();
//			List<Api_TopicBySpuDto> tparttwotj=new ArrayList<Api_TopicBySpuDto>();		
//			if(topspuQG.size()>0){
//				Integer i=1;
//				for (Api_TopicBySpuDto tp : topspuQG) {
//					if(i>1){
//						tparttwotj.add(tp);
//					}else{
//						tpartonetj=tp;
//					}
//					i++;
//				}
//			}
//			Api_TopicBySpuDto topspuonetj=new Api_TopicBySpuDto();
//			List<Api_TopicBySpuDto> topsputwotj=new ArrayList<Api_TopicBySpuDto>();		
//			if(topspuTJ.size()>0){
//				Integer i=1;
//				for (Api_TopicBySpuDto tp : topspuTJ) {
//					if(i>1){
//						topsputwotj.add(tp);
//					}else{
//						topspuonetj=tp;
//					}
//					i++;
//				}
//			}
//			//楼层
//			List<FloorDto> fls=flservice.selectAllByWebSet(Integer.toString(WebSetEnum.pc.getValue()));	
//			if(adclass!=null&&adclass.size()>0){
//				model.addAttribute("adclass",adclass.get(0));//顶部分类广告
//			}else{
//				model.addAttribute("adclass","");//顶部分类广告
//			}
//			model.addAttribute("tpartone",tpartone);//超值特卖
//			model.addAttribute("tparttwo",tparttwoTM);
//			model.addAttribute("tpartoness",tpartoneSS);//新品上市
//			model.addAttribute("tparttwoss",tparttwoSS);
//			model.addAttribute("tpartonehh",tpartonehh);//爆款好货
//			model.addAttribute("tparttwoHH",tparttwoHH);
//			model.addAttribute("tpartonetj",tpartonetj);//限时抢购
//			model.addAttribute("tparttwotj",tparttwotj);
//			model.addAttribute("topspuonetj",topspuonetj);//精选推荐
//			model.addAttribute("topsputwotj",topsputwotj);
//			model.addAttribute("adtop",adtop);
//			model.addAttribute("adlist",adlist);
//			model.addAttribute("tps", tps);
//			model.addAttribute("adsize", adlist.size());
//			model.addAttribute("adlist2",adlist2);
//			model.addAttribute("adlistsize2",adlist2.size());
//			model.addAttribute("floors",fls);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			LogHandle.error(LogType.pc, "获取首页内容错误：{0}", e, "/index");
//			return "/template/pc/404";
//		} finally {
//		}
//		return "/template/pc/index";
//	}
//	@RequestMapping("/")
//	public String dictindex(Model model, HttpServletRequest request) {
//		return index(model,request);
//	}
//	private void initHead(Model model, HttpServletRequest request) {
//		List<Searchkey> keysData= new ArrayList<Searchkey>();
//		List<NavclassfyDto> listkh=new ArrayList<NavclassfyDto>();		
//		try {
//		SessionUser sessionUser = SessionState.GetCurrentUser();
//		if (sessionUser==null||sessionUser.getCode() == -1)
//			sessionUser = null;
//		model.addAttribute("userinfo", sessionUser);
//		keysData=searchkeyService.getSearchkeys(ActivityUsePlatformEnum.pc.getValue());
//		adservice.getListByType(0, 1);
//		if(keysData==null)
//			keysData= new ArrayList<Searchkey>();
//		listkh=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "客户服务"); //客户服务
//		} catch (Exception e) {
//			listkh=new ArrayList<NavclassfyDto>();
//			keysData= new ArrayList<Searchkey>();
//			LogHandle.error(LogType.pc, "获取首页内容错误：{0}", e, "/head");
//		}	
//		String keyword = "";
//		if (request != null) {
//			keyword = request.getParameter("keywords");
//		}
//		model.addAttribute("keywords",StringUtilsEX.IsNullOrWhiteSpace(keyword) ? "" : keyword);
//		model.addAttribute("navskhfw",listkh);
//		model.addAttribute("keysdata", keysData);
//	}
//	private void initFoot(Model model){		
//		List<SnewsClass> newscone =new ArrayList<SnewsClass>();
//		List<SnewsClass> newsctwo =new ArrayList<SnewsClass>();
//		List<SnewsClass> newscwthree =new ArrayList<SnewsClass>();
//		List<SnewsClass> newscfour =new ArrayList<SnewsClass>();
//		List<SnewsClass> newscfive =new ArrayList<SnewsClass>();
//		try {
//			List<SnewsClass> clist=snewsclassMapper.selectByParentId(0,2);
//				for (int i = 0; i < clist.size(); i++) {
//					if(clist.get(i).getLevel()==1){
//						List<SnewsClass> newscone1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
//						newscone.add(clist.get(i));
//						for (int j = 0; j < newscone1.size(); j++) {
//							newscone.add(newscone1.get(j));	
//						}
//					}
//					if(clist.get(i).getLevel()==2){
//						List<SnewsClass> newsctwo1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
//						newsctwo.add(clist.get(i));
//						for (int j = 0; j < newsctwo1.size(); j++) {
//							newsctwo.add(newsctwo1.get(j));	
//						}
//					}
//					if(clist.get(i).getLevel()==3){
//						List<SnewsClass> newscwthree1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
//						newscwthree.add(clist.get(i));
//						for (int j = 0; j < newscwthree1.size(); j++) {
//							newscwthree.add(newscwthree1.get(j));	
//						}
//					}
//					if(clist.get(i).getLevel()==4){
//						List<SnewsClass> newscfour1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
//						newscfour.add(clist.get(i));
//						for (int j = 0; j < newscfour1.size(); j++) {
//							newscfour.add(newscfour1.get(j));	
//						}
//					}
//					if(clist.get(i).getLevel()==5){
//						List<SnewsClass> newscfive1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
//						newscfive.add(clist.get(i));
//						for (int j = 0; j < newscfive1.size(); j++) {
//							newscfive.add(newscfive1.get(j));	
//						}
//					}
//				}
//				
//				
//				
//				
//		} catch (Exception e) {
//			LogHandle.error(LogType.pc, "获取首页内容错误：{0}", e, "/foot");
//		}
//		model.addAttribute("newscone",newscone);
//		model.addAttribute("newsctwo",newsctwo);
//		model.addAttribute("newscwthree",newscwthree);
//		model.addAttribute("newscfour",newscfour);
//		model.addAttribute("newscfive",newscfive);
//		 
//	}
//	private void intitNav(Model model) {
//		intitNav(model, null);
//	}
//	private void intitNav(Model model, List<CategoryDTO> list) {
//		try {
//			if (list == null) {
//				list = categoryService.selectAll(0);
//			}
//			model.addAttribute("classlist", list);
//
//		} catch (Exception e) {
//			LogHandle.error(LogType.pc, "获取首页内容错误：{0}", e, "/index");
//		}
//	}
//
//	/**
//	 * 产品列表页
//	 * 
//	 * @param model
//	 * @param cat
//	 * @return
//	 */
//	@RequestMapping("/list.html")
//	public String list(Model model, String cat, HttpServletRequest request) {
//		try {
//			initHead(model,request);
//			List<Category> list = categoryService.queryAllList();
//			intitNav(model);
//			String[] catarr = cat.split(",");
//			int id = StringUtilsEX.ToInt(catarr[0]);
//			String title = "";
//			Category cls = list.stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList()).get(0);
//			model.addAttribute("fsclsname", cls.getName());
//			title += cls.getName() + " ";
//			int id1 = StringUtilsEX.ToInt(catarr[1]);
//			cls = list.stream().filter(x -> x.getId().equals(id1)).collect(Collectors.toList()).get(0);
//			model.addAttribute("scclsname", cls.getName());
//			title += cls.getName() + " ";
//			int id2 = StringUtilsEX.ToInt(catarr[2]);
//			cls = list.stream().filter(x -> x.getId().equals(id2)).collect(Collectors.toList()).get(0);
//			model.addAttribute("thclsname", cls.getName());
//			title += cls.getName();
//			model.addAttribute("title", title);
//			model.addAttribute("desc", "");
//			model.addAttribute("keywords", "");
//			List<Api_SeachAtrrDto> seachAtrrlist = productService.getApiSeachAtrrList(id2,ActivityUsePlatformEnum.pc.getValue());		
//			model.addAttribute("seachatrrlist", seachAtrrlist);				
//			model.addAttribute("procount", "1");
//		} catch (Exception e) {
//			e.printStackTrace();
//			LogHandle.error(LogType.pc, "获取产品列表内容错误：{0}", e, "/pro/list");
//		}
//		return "/template/pc/pro/list";
//	}
//	
//	@RequestMapping("/decorators/decoratorsPC.html")
//	public String decoratorPC(Model model, HttpServletRequest request){		
//		try {
//			initHead(model, request);
//			intitNav(model);
//			initFoot(model);
//		} catch (Exception e) {
//			LogHandle.error(LogType.pc, "获取首页内容错误：{0}", e, "/index");
//		}
//		return "/template/pc/decorators/decoratorsPC";
//	}
//	
//	@RequestMapping("/decorators/decoratorsLs.html")
//	public String decoratorsLs(Model model, HttpServletRequest request){		
//		try {
//			initHead(model,request);
//			intitNav(model);
//			initFoot(model);
//		} catch (Exception e) {
//			LogHandle.error(LogType.pc, "获取绿色首页内容错误：{0}", e, "/index");
//		}
//		return "/template/pc/decorators/decoratorsLs";
//	}
//	@RequestMapping("/decorators/decoratorsZLZX.html")
//	public String decoratorsZLZX(Model model, HttpServletRequest request){		
//		try {
//			initHead(model,request);
//			intitNav(model);
//			initFoot(model);
//		} catch (Exception e) {
//			LogHandle.error(LogType.pc, "获取绿色首页内容错误：{0}", e, "/index");
//		}
//		return "/template/pc/decorators/decoratorsZLZX";
//	}
//	@RequestMapping("/404.html")
//	public String errorpage(Model model){		
//		return "/template/pc/404";
//	}
	
	/**
	 * h5页面测试
	 * @param model
	 * @return
	 */
//	@RequestMapping("/Coupon")
//	public String conpon(Model model){		
//		return "/template/h5/Coupon";
//	}
//	@RequestMapping("/msg")
//	public String msg(Model model){		
//		return "/template/h5/Message";
//	}
}
