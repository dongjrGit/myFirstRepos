package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_TopicBrandDto;
import com.yinlian.api.app.dto.ShopCategoryDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.CriteriaBaner;
import com.yinlian.wssc.web.po.Searchkey;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.BanerService;
import com.yinlian.wssc.web.service.FloorService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.SearchkeyService;
import com.yinlian.wssc.web.service.ShopcategoryService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import data.StringUtil;

@Controller
@RequestMapping("/wap/")
public class WapMainViewController {

    @Autowired
    private ShopcategoryService shopcategoryService;

	@Autowired
	private AdverisingService adverisingService;

	@Autowired
	private TopicService topicService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private BanerService banerService;
	@Autowired
	private SearchkeyService searchkeyService;
	@Autowired FloorService floorService;
	@RequestMapping("/search.html")
	public String search(HttpServletRequest request,String href,Model model){
		try {
			int messagecount=0;
			Integer userid=SessionUtil.getSessionUserId(request);
			
			if(userid!=null){
				messagecount=messageService.getCount(userid);
				
			}
			model.addAttribute("messagecount", messagecount);
			List<Searchkey> keysData=searchkeyService.getSearchkeys(ActivityUsePlatformEnum.wap.getValue());
			model.addAttribute("keysdata", keysData);
			model.addAttribute("href", href);
			return "/template/wap/search";
		} catch (Exception e) {
            LogHandle.error(LogType.wap, MessageFormat.format("搜索页面错误：{0}", e.toString()),
                "/wap/search");
            return ErrorRedirect.getInstance().wapRedirect("搜索页面错误");
		}
	}
	
	@RequestMapping("/fjsm")
	public String fjsm(String lng,String lat,String classid,String issort,Model model){

		try {
			model.addAttribute("lat", lat);
			model.addAttribute("lng", lng);
		if (StringUtil.isNotNull(issort)) {
			model.addAttribute("sort", issort);
		}else{
			model.addAttribute("sort", "");
		}
		if (StringUtil.isNotNull(classid)) {
			model.addAttribute("classid", classid);
		}else{
			model.addAttribute("classid", "");
		}
			model.addAttribute("fl",shopcategoryService.getAllList());
	//	model.addAttribute("qy", qeuryBusinessScop("3").getData());

		} catch (Exception e) {

            LogHandle.error(LogType.wap, MessageFormat.format("获取附近商盟错误：{0}", e.toString()),
                "/wap/fjsm");
            return ErrorRedirect.getInstance().wapRedirect("获取附近商盟错误");
		}
		return "/template/wap/zhuye/fjsm";
	}
	
	@RequestMapping("/dt.html")
	public String dt(){
		return "/template/wap/dt";
	}
	@RequestMapping("/404.html")
	public String errorpage(Model model){		
		return "/template/wap/404";
	}
	/**
	 * wap首页
	 * 
	 * @author kh.wang
	 * @since 2016年6月21日
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index(Model model,HttpServletRequest request){

		try {
			Api_TopicBySpuCriteria atc = new Api_TopicBySpuCriteria();
			atc.setMark(3);
			atc.setWebset(Integer.toString(WebSetEnum.wap.getValue()));
			PageBean listBean = topicService.getTopicBySpu(atc, 1, 6);
		//新品上市
		model.addAttribute("xpss",listBean.getBeanList());
		//发现好店
		model.addAttribute("fxhd", topicService.getTopicByShop(1, 4).getBeanList());
		/*//金彩
		List<Api_FirstPageTopicDto> ls = topicService.getTopic(1);
		*/
		/*List<Api_FirstPageChildTopicDto> a=new ArrayList<Api_FirstPageChildTopicDto>();
		
		List<Api_FirstPageChildTopicDto> b=new ArrayList<Api_FirstPageChildTopicDto>();
		for (Api_FirstPageTopicDto dto : ls) {
			for (Api_FirstPageChildTopicDto api_FirstPageTopicDto : dto.getChilds()) {
				if (api_FirstPageTopicDto.getFloorType()==0) {
					a.add(api_FirstPageTopicDto);
				}else if (api_FirstPageTopicDto.getFloorType()==1) {
					b.add(api_FirstPageTopicDto);
				}else{
					continue;
				}
			}
		}
		*//**
		 * A楼
		 *//*
		model.addAttribute("a", a);
		*//**
		 * B楼
		 *//*
		model.addAttribute("b", b);*/
		
		//AdvertApiController ad=new AdvertApiController();
		
		
		List<Api_TopicBrandDto> topicbrands = topicService.getTopicBrand(PageMarkType.首页.getValue(),TopicMarkEnum.品牌街.getValue(),TopicTypeEnum.品牌.getValue(),Integer.toString(WebSetEnum.wap.getValue()),6,true);
		
		model.addAttribute("ppj", topicbrands);
		
		List<Advertising> listgg=(List<Advertising>)adverisingService.getListByTypeAndDisplay(PageMarkType.首页.ordinal(), 1,WebSetEnum.wap.getValue());
		//首页顶部导航
		model.addAttribute("gg1",listgg);
		
		//首页顶部导航数量
		model.addAttribute("gg1size", listgg.size());
		
		//首页第二个广告
		model.addAttribute("gg2", adverisingService.getListByTypeAndDisplay(PageMarkType.首页.ordinal(), 2,WebSetEnum.wap.getValue()));
		
		//首页第三个广告
		model.addAttribute("gg3", adverisingService.getListByTypeAndDisplay(PageMarkType.首页.ordinal(), 3,WebSetEnum.wap.getValue()));
		
		//首页第四个广告
		model.addAttribute("gg4",adverisingService.getListByTypeAndDisplay(PageMarkType.首页.ordinal(), 4,WebSetEnum.wap.getValue()));

		CriteriaBaner criteria = new CriteriaBaner();
		criteria.setType(1);
		criteria.setOrderByClause("sort");
		PageBean pageBean = banerService.selBaner(criteria, 0, 5);
		model.addAttribute("ban",pageBean.getBeanList());
		
		int messagecount=0;
		Integer userid=SessionUtil.getSessionUserId(request);			
		if(userid!=null){
			messagecount=messageService.getCount(userid);				
		}		
		model.addAttribute("messagecount", messagecount);
		
		} catch (Exception e) {
            LogHandle.error(LogType.wap, MessageFormat.format("wap首页错误：{0}", e.toString()),
                "/wap/index");
            return ErrorRedirect.getInstance().wapRedirect("wap首页错误");
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/new_index";
	}

	  /**
     * 查询商品分类
     *@param ch
     *@return
     */
    @RequestMapping(value = "/querycategory", produces = "text/html;charset=UTF-8")
    public BaseResult querycategory(String ch) {
        BaseResult item = new BaseResult();
        try {
            List<ShopCategoryDto> list = shopcategoryService.getAllList();
            item.setData(list);
            item.setCode(0);
        } catch (Exception e) {
            item.setCode(-900);
            item.setDesc("查询商盟分类异常：" + e.getMessage());
            LogHandle.error(LogType.wap, MessageFormat.format("查询商盟分类异常! 异常信息:{0}", e.getMessage())
                .toString(), "/wap/queryscopshop");
        }
        return item;
    }
    

    /**
     * 专题详情
     * @param mark 专题标识
     * @return
     */
    @RequestMapping("/topicdet.html")
    public String topicdet(Integer mark,Model model,String ch){
    	try {
    		model.addAttribute("ch", ch);
    		model.addAttribute("mark", mark);        	
        	if (mark==TopicMarkEnum.独家品牌.getValue()) {
    			model.addAttribute("markname", "独家品牌");
    			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.独家品牌.getValue(), 1,WebSetEnum.app.getValue()));
    		}else if (mark==TopicMarkEnum.值得购.getValue()) {
    			model.addAttribute("markname", "值得购");
    			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.值得购.getValue(), 1,WebSetEnum.app.getValue()));
    		}else if (mark==TopicMarkEnum.新品尝鲜.getValue()) {
    			model.addAttribute("markname", "新品尝鲜");
    			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.新品尝鲜.getValue(), 1,WebSetEnum.app.getValue()));
			}
        	
		} catch (Exception e) {
			e.printStackTrace();                                      
			LogHandle.error(LogType.wap, "专题详情错误：{0}", e, "/wap/products/proList");		
			return ErrorRedirect.getInstance().wapRedirect("专题详情错误");
		}
    	//return ErrorRedirect.getInstance().wapRedirectError();
    	return "/template/wap/products/topic_proList";
    }
    /**
     * 专题详情
     * @param mark 专题标识
     * @return
     */
    @RequestMapping("/app/topicdet.html")
    public String apptopicdet(Integer mark,Model model,HttpServletRequest request){
    	try {
    		int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
    		model.addAttribute("mark", mark);
    		if (mark==TopicMarkEnum.独家品牌.getValue()) {
    			model.addAttribute("markname", "独家品牌");
    			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.独家品牌.getValue(), 1,WebSetEnum.app.getValue()));
    		}else if (mark==TopicMarkEnum.值得购.getValue()) {
    			model.addAttribute("markname", "值得购");
    			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.值得购.getValue(), 1,WebSetEnum.app.getValue()));
    		}else if (mark==TopicMarkEnum.新品尝鲜.getValue()) {
    			model.addAttribute("markname", "新品尝鲜");
    			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.新品尝鲜.getValue(), 1,WebSetEnum.app.getValue()));
			}else if (mark==TopicMarkEnum.每日鲜.getValue()) {
    			model.addAttribute("markname", "每日鲜");
    			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.每日鲜.getValue(), 1,WebSetEnum.app.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();                                      
			LogHandle.error(LogType.wap, "专题详情错误：{0}", e, "/wap/products/proList");		
			return ErrorRedirect.getInstance().wapRedirect("专题详情错误");
		}
    	return "/template/app/topic/app_topic_proList";
    }
    
    @RequestMapping("/findfxhd.html")
    public String findfxhd(){
    	//return ErrorRedirect.getInstance().wapRedirectError();
    	return "/template/wap/shop/dpList";
    }
    
    @RequestMapping("/app/findfxhd.html")
    public String appfindfxhd(Integer mark,Integer ch,Model model,HttpServletRequest request){
    	try {
    		model.addAttribute("ch", ch);//1安卓，2苹果
    		model.addAttribute("markname", "发现好店");
			model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.发现好店.getValue(), 1,WebSetEnum.app.getValue()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return "/template/app/topic/app_topic_dpList";
    }
    
    //
    @RequestMapping("/brand/brandpros.html")
	public String proList(String bid,String bname,HttpServletRequest request) {		
		try {			
			request.setAttribute("bname", bname);
			request.setAttribute("bid", bid);
		} catch (Exception e) {
			e.printStackTrace();                                      
			LogHandle.error(LogType.wap, "访问商品列表错误：{0}", e, "/wap/products/proList");		
			return ErrorRedirect.getInstance().wapRedirect("商品信息错误");
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/zhuye/brandpros";
	}
    
    @RequestMapping("/app/gettopic")
    public String appgettopic(String topicid,Model model,String ch){
    	try {
    		model.addAttribute("gg",adverisingService.getListByTypeAndDisplay(PageMarkType.专题页.getValue(), 1,WebSetEnum.app.getValue()));
		} catch (Exception e) {
		}
    	model.addAttribute("topicid", topicid);
    	model.addAttribute("ch", ch);
    	return "/template/app/topic/app_topic_proList";
    }
    @RequestMapping("/gettopic")
    public String gettopic(String topicid,Model model,String ch){
    	model.addAttribute("topicid", topicid);
    	model.addAttribute("ch", ch);
    	//return ErrorRedirect.getInstance().wapRedirectError();
    	return "/template/wap/products/topic_proList";
    }
}
