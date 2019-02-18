package com.yinlian.view.pc.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.PointRecordService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.UserFinanceService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;


@Controller
@RequestMapping("/member/coupon")
public class PCCouponViewController {

	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private   PointRecordService pointrecordService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFinanceService userfinanceService;
	
	@Autowired
	private UsercapitalService usercapitalService;
	
	@Autowired
	private TopicService topservice;
	
	
	
	
	@RequestMapping(value = "/Coupons", produces = "text/html;charset=UTF-8")
	public ModelAndView showCoupon(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		try{
			SessionUser  user = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			}
			List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
			TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			request.setAttribute("topspuTM", topspuTM);

			view.setViewName("/template/pc/memberCenter/ZcZx/Coupons");
			return view;

		} catch (Exception e) {
			
			LogHandle.error(LogType.pc, "获取当前用户的优惠券异常，信息：" + e,
					"CouponInfo/showCoupon");
			view.setViewName("redirect:/404.html");
			return view;
		}
		
	}
	
	
	 /**
     * 积分查询
     * @param request
     * @return
     */
    @RequestMapping("/points")
    public  String points(HttpServletRequest request,Model model){
    	
    	try{
    		Integer userid=SessionUtil.getSessionUserId(request);
    		Users users=userService.selectByPrimaryKey(userid);
    		if(users!=null){
    			model.addAttribute("points", users.getPoints());
    		}

    	}catch(Exception e){
    		LogHandle.debug(LogType.pc,
					MessageFormat.format(" 积分查询异常! 异常信息:{0}", e.toString()),
					"userinfo/points");
    		return "redirect:/404.html";
    	}
    	return "/template/pc/memberCenter/ZcZx/point";
    }
    
    
    /**
     * 余额查询
     * @param request
     * @return
     */
    @RequestMapping("/balance")
    public ModelAndView balance(String type,HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try{
    		if(StringUtilsEX.IsNullOrWhiteSpace(type)){
    			view.setViewName("/template/wap/index");
				return view;
    		}
    		SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);

			if (sessionUser.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
                return view;
                } else {
                	Usercapital usercapital = usercapitalService.queryByUserId(sessionUser.getUserId());
        			request.setAttribute("userbalance", usercapital);
                	view.setViewName("/template/pc/memberCenter/ZcZx/Balances");
                    return view;
			}
    	}catch(Exception e){
    		LogHandle.debug(LogType.pc,
					MessageFormat.format(" 余额查询异常! 异常信息:{0}", e.toString()),
					"userinfo/points");
			view.setViewName("redirect:/404.html");
			return view;
    	}
    }
    
    /**
     * 余额充值第一步
     * @param request
     * @return
     */
    @RequestMapping("/balancepay1")
    public ModelAndView balancePay1(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try{
    		SessionUser user = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				request.setAttribute("name", user.getName());
				view.setViewName("/template/pc/memberCenter/ZcZx/Balance_Pay1");
	        	return view;
			}
    		
    	}catch(Exception e){
    		LogHandle.debug(LogType.pc,
					MessageFormat.format("到余额充值界面异常! 异常信息:{0}", e.toString()),
					"userinfo/balancepay");
			view.setViewName("redirect:/404.html");
			return view;
    	}
    	
    	}
    
    /**
     * 余额充值第二步
     * @param request
     * @return
     * money 充值的金额
     */
    
    @RequestMapping("/balancepay2")
    public ModelAndView balancePay2(String money,HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try{
    		SessionUser user = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				request.setAttribute("name", user.getName());
				request.setAttribute("money",money);
				view.setViewName("/template/pc/memberCenter/ZcZx/Balance_Pay2");
	        	return view;
			}
    		
    	}catch(Exception e){
    		LogHandle.debug(LogType.pc,
					MessageFormat.format("到余额充值界面异常! 异常信息:{0}", e.toString()),
					"userinfo/balancepay");
			view.setViewName("redirect:/404.html");
			return view;
    	}
    	
    	}
    
    /**
     * 余额充值第三步
     * @param request
     * @return
     */
    @RequestMapping("/balancepay3")
    public ModelAndView balancePay3(String money,HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try{
    		SessionUser user = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				request.setAttribute("name", user.getName());
				request.setAttribute("money",money);
				view.setViewName("/template/pc/memberCenter/ZcZx/Balance_Pay3");
	        	return view;
			}
    		
    	}catch(Exception e){
    		LogHandle.debug(LogType.pc,
					MessageFormat.format("到余额充值界面异常! 异常信息:{0}", e.toString()),
					"userinfo/balancepay");
			view.setViewName("redirect:/404.html");
			return view;
    	}
    	
    	}
    
    /**
     * 余额明细
     * @param request
     * @return
     */
    @RequestMapping("/balancebyuserid")
    public ModelAndView balancebyuserid(HttpServletRequest request){
    	ModelAndView view = new ModelAndView();
    	try{
    		
    		SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
                return view;
                } else {
                	
                    //List<Userfinance> balance = userfinanceService.getUseridFinance(sessionUser.getUserId());
                   // request.setAttribute("balance", balance); 
                	Usercapital usercapital = usercapitalService.queryByUserId(sessionUser.getUserId());
        			request.setAttribute("userbalance", usercapital);
                	view.setViewName("/template/pc/memberCenter/ZcZx/Balance_AddLists");
                    return view;
			}
    	}catch(Exception e){
    		LogHandle.debug(LogType.pc,
					MessageFormat.format("查询余额明细异常! 异常信息:{0}", e.toString()),
					"userinfo/points");
			view.setViewName("redirect:/404.html");
			return view;
    	}
    }
}
