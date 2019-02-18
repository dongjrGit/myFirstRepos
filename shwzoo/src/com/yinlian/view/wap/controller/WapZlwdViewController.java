package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.AdvertImgAppDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaSnews;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.users_newsService;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/zlwd")
public class WapZlwdViewController {
	@Autowired
	private AdverisingService adverisingService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private users_newsService usersNewsService;
		//中绿味道首页
		@RequestMapping("/index.html")
	    public String index(HttpServletRequest request,Model model,String pagetype) {
			try {
				if (StringUtilsEX.IsNullOrWhiteSpace(pagetype)) {
					pagetype="findindex";
				}
				List<AdvertImgAppDto> advertisings = new ArrayList<AdvertImgAppDto>();
				advertisings = adverisingService.getListByTypeAndDisplayApi(PageMarkType.中绿味道.getValue(), 0, WebSetEnum.wap.getValue());
				//ctype 专家讲堂13，菜谱大全14，厨房百科15 
				CriteriaSnews criteria = new CriteriaSnews();				
			    criteria.setState(0);//新闻0为发布，1为未发布				
				criteria.setOrderByClause("createtime");
				criteria.setSort("desc");
				criteria.setCtype(13);							
				PageBean zjjt = newsService.querySlist(criteria, 1, 4);
				criteria.setCtype(14);
				PageBean cpdq = newsService.querySlist(criteria, 1, 4);
				criteria.setCtype(15);
				PageBean cfbk = newsService.querySlist(criteria, 1, 4);
				request.setAttribute("zjjt", zjjt.getBeanList());//专家讲堂
				request.setAttribute("cpdq", cpdq.getBeanList());//菜谱大全
				request.setAttribute("cfbk", cfbk.getBeanList());//厨房百科
				request.setAttribute("ads", advertisings);//中绿味道 首页广告
				model.addAttribute("pagetype", pagetype);
			} catch (Exception e) {
				LogHandle.error(LogType.wap, MessageFormat.format("中绿味道首页:{0}", e),
						"zlwd/index");
				return ErrorRedirect.getInstance().wapRedirect("页面错误");
			}
			//return "redirect:/wap/404.html";
	        return "/template/wap/zlwd/index";
	  	}
		//APP中绿味道首页
		@RequestMapping("/app_index.html")
	    public String appindex(HttpServletRequest request) {
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
				List<AdvertImgAppDto> advertisings = new ArrayList<AdvertImgAppDto>();
				advertisings = adverisingService.getListByTypeAndDisplayApi(PageMarkType.中绿味道.getValue(), 0, WebSetEnum.app.getValue());
				//ctype 专家讲堂13，菜谱大全14，厨房百科15 
				CriteriaSnews criteria = new CriteriaSnews();				
			    criteria.setState(0);//新闻0为发布，1为未发布				
				criteria.setOrderByClause("createtime");
				criteria.setSort("desc");
				criteria.setCtype(13);							
				PageBean zjjt = newsService.querySlist(criteria, 1, 4);
				criteria.setCtype(14);
				PageBean cpdq = newsService.querySlist(criteria, 1, 4);
				criteria.setCtype(15);
				PageBean cfbk = newsService.querySlist(criteria, 1, 4);
				request.setAttribute("zjjt", zjjt.getBeanList());//专家讲堂
				request.setAttribute("cpdq", cpdq.getBeanList());//菜谱大全
				request.setAttribute("cfbk", cfbk.getBeanList());//厨房百科
				request.setAttribute("ads", advertisings);//中绿味道 首页广告
			} catch (Exception e) {
				LogHandle.error(LogType.wap, MessageFormat.format("中绿味道首页:{0}", e),
						"zlwd/index");
				return ErrorRedirect.getInstance().wapRedirect("页面错误");
			}

	        return "/template/app/news/app_index";
	  	}
		//菜谱大全
	  	@RequestMapping("/cpdq.html")
	    public ModelAndView cpdq() {
	  		/*ModelAndView model=new ModelAndView();
	  		model.setViewName("redirect:/wap/404.html");
	  		return model;*/
	        return new ModelAndView("/template/wap/zlwd/cpdq");
	  	}
	  	
		//厨房百科
	  	@RequestMapping("/cfbk.html")
	    public ModelAndView cfbk() { 
	  		/*ModelAndView model=new ModelAndView();
	  		model.setViewName("redirect:/wap/404.html");
	  		return model;*/
	        return new ModelAndView("/template/wap/zlwd/cfbk");
	  	}
	  	
		//专家讲堂
	  	@RequestMapping("/zjjt.html")
	    public ModelAndView zjjt() {
	  		/*ModelAndView model=new ModelAndView();
	  		model.setViewName("redirect:/wap/404.html");
	  		return model;*/
	        return new ModelAndView("/template/wap/zlwd/zjjt");
	  	}	
	  	//菜谱大全列表
	  	@RequestMapping("/cpdqList.html")
	    public ModelAndView cpdqList(String token,HttpServletRequest req) {
	  		SessionUser user=SessionState.GetCurrentUser(token);
	  		if(user.getCode()>=0){
	  			req.setAttribute("token", token);
	  		}else{
	  			req.setAttribute("token", "false");
	  		}
	  		/*ModelAndView model=new ModelAndView();
	  		model.setViewName("redirect:/wap/404.html");
	  		return model;*/
	        return new ModelAndView("/template/wap/zlwd/cpdqList");
	  	}
	    //app菜谱大全列表
	  	@RequestMapping("/appcpdqList.html")
	    public String appcpdqList(String token,HttpServletRequest req) throws Exception {
	  		int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(req.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(req.getParameter("ch"));
			}
			req.setAttribute("ch", ch);//1安卓，2苹果
	  		SessionUser user=SessionState.GetCurrentUser(token);
	  		if(user.getCode()>=0){
	  			req.setAttribute("token", token);
	  		}else{
	  			req.setAttribute("token", "false");
	  		}
	  		
	        return "/template/app/news/app_cpdqList";
	  	}
	  	
		//app厨房百科
	  	@RequestMapping("/appcfbk.html")
	    public ModelAndView appcfbk(HttpServletRequest request) { 
	  		int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
	        return new ModelAndView("/template/app/news/app_cfbk");
	  	}
	  	
		//app专家讲堂
	  	@RequestMapping("/appzjjt.html")
	    public ModelAndView appzjjt(HttpServletRequest request) {
	  		int ch=1;
			if(!StringUtilsEX.IsNullOrWhiteSpace(request.getParameter("ch"))){
				ch=StringUtilsEX.ToInt(request.getParameter("ch"));
			}
			request.setAttribute("ch", ch);//1安卓，2苹果
	        return new ModelAndView("/template/app/news/app_zjjt");
	  	}
	  
	  	
}
