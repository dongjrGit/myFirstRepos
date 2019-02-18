package com.yinlian.view.pc.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.ReceiveAddressService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.UserAttrService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

@Controller
@RequestMapping("/member/userInfo")
public class PCUserInfoViewController {
	
	@Autowired
	private UserService     userService;
	@Autowired
	private UserAttrService  userAttrService;
	 
	@Autowired
	private ReceiveAddressService  receiveAddressService;
	
	   @Autowired
	    private TopicService topservice;
	   
	   @Autowired
	   private   ArticlesService   articlesService;
	/**
	 * 个人中心
	 * @param model
	 * @param request
	 * @return
	 */
    @RequestMapping("/personalInformation.html")
    public String personalInformation(){
    	//return "redirect:/template/pc/memberCenter/zhsz/PersonalInformation";
    	return "/template/pc/memberCenter/zhsz/PersonalInformation";
    }
    
    @RequestMapping("/accountBinding.html")
    public String accountBinding(){
    	
    	return "/template/pc/memberCenter/zhsz/AccountBinding";
    }
    
    
    @RequestMapping("/accountSecurity.html")
    public String accountSecurity(HttpServletRequest request,Model model){
    	try {
			Integer  userid=SessionUtil.getSessionUserId(request);
			Users   users=userService.selectByPrimaryKey(userid);
			/*model.addAttribute("mobile", users.getMobile());
			model.addAttribute("email", users.getEmail());*/
			List<NavclassfyDto> articledto=articlesService.findByAssign(ClassifyPageType.会员中心, WebSetEnum.pc, "安全问题");
			Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				count=articledto.size();
			}
			model.addAttribute("count", count);
			model.addAttribute("articledto", articledto);
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
			model.addAttribute("users", users);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/zhsz/AccountSecurity";
    }
    
    
    @RequestMapping("/receiveAddress.html")
    public String receiveAddress(){
    	return "/template/pc/memberCenter/zhsz/ReceiveAddress";
    }
    
    @RequestMapping("/addRecAdr.html")
    public String addRecAdr(){
    	return "/template/pc/memberCenter/zhsz/AddRecAdr";
    }
    
    @RequestMapping("/bindingEmail.html")
    public String bindingEmail(int setup,Model model){
    	
		try {
			model.addAttribute("setup", setup);
			List<NavclassfyDto> articledto=articlesService.findByAssign(ClassifyPageType.会员中心, WebSetEnum.pc, "安全问题");
			Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				
				count=articledto.size();
			}
			model.addAttribute("count", count);
			model.addAttribute("articledto", articledto);
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
	
    	return "/template/pc/memberCenter/zhsz/BindingEmail";
    }
    
    
    @RequestMapping("/changeEmail.html")
    public String changeEmail(int setup,Model model){
    	
    	try {
    		model.addAttribute("setup", setup);
    		List<NavclassfyDto> articledto=articlesService.findByAssign(ClassifyPageType.会员中心, WebSetEnum.pc, "安全问题");
    		Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				
				count=articledto.size();
			}
			model.addAttribute("count", count);
			model.addAttribute("articledto", articledto);
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
			TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	
    	return "/template/pc/memberCenter/zhsz/ChangeEmail";
    }
    
    
    @RequestMapping("/changeMobile.html")
    public String ChangeMobile(int setup,Model model){
    	try {
    		model.addAttribute("setup", setup);
    		List<NavclassfyDto> articledto=articlesService.findByAssign(ClassifyPageType.会员中心, WebSetEnum.pc, "安全问题");
			Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				
				count=articledto.size();
			}
			model.addAttribute("count", count);
			model.addAttribute("articledto", articledto);
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	
    	return "/template/pc/memberCenter/zhsz/ChangeMobile";
    }
    
    
    @RequestMapping("/checkingMobile.html")
    public String checkingMobile(int setup,Model model,HttpServletRequest request){
    	model.addAttribute("setup", setup);
    	try {
    		
    		List<NavclassfyDto> articledto=articlesService.findByAssign(ClassifyPageType.会员中心, WebSetEnum.pc, "安全问题");
			Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				
				count=articledto.size();
			}
			model.addAttribute("count", count);
			model.addAttribute("articledto", articledto);
			Integer userid=SessionUtil.getSessionUserId(request);
			Users users=userService.queryById(userid);
			model.addAttribute("mobile", users.getMobile());
			model.addAttribute("setup", setup);
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	
    	return "/template/pc/memberCenter/zhsz/CheckingMobile";
    }
    
    
    @RequestMapping("/checkingEmail.html")
    public String heckingEmail(int setup,Model model,HttpServletRequest request){
    	model.addAttribute("setup", setup);
    	try {
    		
    		List<NavclassfyDto> articledto=articlesService.findByAssign(ClassifyPageType.会员中心, WebSetEnum.pc, "安全问题");
			Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				
				count=articledto.size();
			}
			model.addAttribute("count", count);
			model.addAttribute("articledto", articledto);
			Integer userid=SessionUtil.getSessionUserId(request);
			Users users=userService.queryById(userid);
			model.addAttribute("email", users.getEmail());
			model.addAttribute("setup", setup);
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	
    	return "/template/pc/memberCenter/zhsz/CheckingEmail";
    }
    
    @RequestMapping("/bindingMobile.html")
    public String bindingMobile(int setup,Model model){
    	try {
    		model.addAttribute("setup", setup);
    		List<NavclassfyDto> articledto=articlesService.findByAssign(ClassifyPageType.会员中心, WebSetEnum.pc, "安全问题");;
			Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				
				count=articledto.size();
			}
			model.addAttribute("count", count);
			model.addAttribute("articledto", articledto);
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/zhsz/BindingMobile";
    }
    
    
    @RequestMapping("/changeLoginPwd.html")
    public String changeLoginPwd(int setup,int type, Model model,HttpServletRequest request){
    	
    	try {
			Integer userid=SessionUtil.getSessionUserId(request);
			List<NavclassfyDto> articledto=articlesService.findByAssign(ClassifyPageType.会员中心, WebSetEnum.pc, "安全问题");
			Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				
				count=articledto.size();
			}
			model.addAttribute("count", count);
			model.addAttribute("articledto", articledto);
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			model.addAttribute("topspuTM", topspuTM);
			model.addAttribute("userid", setup == 3 ? 0 : userid);
			Users users=new Users();
			users=userService.queryById(userid);
			model.addAttribute("users", users);
			model.addAttribute("setup", setup);
	    	model.addAttribute("type", type);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/zhsz/ChangeLoginPwd";
    }
    
    
    @RequestMapping("/findPwd.html")
    public String findPwd(int setup,int type,String userid,HttpServletRequest request,Model model){
    	try {
			
			List<NavclassfyDto> articledto=articlesService.findByAssign(ClassifyPageType.会员中心, WebSetEnum.pc, "安全问题");
			Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				
				count=articledto.size();
			}
			if(userid!=null){
				Users users=userService.queryById(StringUtilsEX.ToInt(userid));
				if(users!=null){
					model.addAttribute("phone", users.getMobile());
			    	model.addAttribute("emails", users.getEmail());
				}
			}
			model.addAttribute("count", count);
			model.addAttribute("articledto", articledto);
			model.addAttribute("setup", setup);
	    	model.addAttribute("type", type);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/zhsz/FindPwd";
    }
     @RequestMapping("/cardcharge.html")
    public String cardCharge(HttpServletRequest request,Model model){
    	try {

		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/404.html";
		}
    	return "/template/pc/memberCenter/ZcZx/cardcharge";
    }
    
}
