package com.yinlian.view.wap.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.MemberCouponTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.CouponDto;
import com.yinlian.api.app.dto.SearchCouponDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.CriteriaCoupon;
import com.yinlian.wssc.web.util.GetCookie;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;


	@Controller
	@RequestMapping("/wap/coupon")
	public class WapCouponViewController {
		@Autowired
		private CouponService couponService;
		
		/**
		 * 查询当前用户的优惠券
		 * 
		 * @param token
		 * @return
		 */
		@RequestMapping(value = "/showCoupon", produces = "text/html;charset=UTF-8")
		public ModelAndView showCoupon(String type,String ch,  String page, String size,HttpServletRequest request) {
			page="1";size="20";
			ModelAndView view = new ModelAndView();
			try {
				SessionUser  user = new SessionUser();
				String token = CookieUtils.getTokenFromCookie(request);
				user = SessionState.GetCurrentUser(token);
				if (user.getCode() != 0) {
					view.setViewName("/template/wap/userinfo/login");
					return view;
				}
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					view.setViewName("/template/error/index.html");
					return view;
				}
				if (StringUtilsEX.ToInt(page) <= 0
						|| StringUtilsEX.ToInt(size) <= 0) {
					view.setViewName("/template/error/index.html");
					return view;
				}
				
				if (StringUtilsEX.ToInt(type) < MemberCouponTypeEnum.全部.getValue()
						|| StringUtilsEX.ToInt(type) > MemberCouponTypeEnum.回收站
								.getValue()) {
					view.setViewName("/template/error/index.html");
					return view;
				}
				CriteriaCoupon criteria = new CriteriaCoupon();
				criteria.setStatus(StringUtilsEX.ToInt(type));
				criteria.setSort("desc");
				criteria.setOrderByClause("ProvideTime");
				criteria.setUserid(user.getUserId());	
				criteria.setUseplatform(ActivityUsePlatformEnum.wap.getValue());
				PageBean pBean = couponService.getByUserID(criteria,
			    StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
				request.setAttribute("coupon", pBean.getBeanList());
				view.setViewName("/template/wap/coupon/Coupon");
				return view;
				//return ErrorRedirect.getInstance().wapRedirectErrorModel();
				
			} catch (Exception e) {
				
				LogHandle.error(LogType.Coupon, "获取当前用户的优惠券异常，信息：" + e,
						"CouponInfo/showCoupon");
				view.setViewName("/template/wap/error/index.html");
				return view;
			}
			
		}
		/**
		 * 查询所有店铺优惠券
		 * 
		 * @param token
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping(value = "/getShopCoupon", produces = "text/html;charset=UTF-8")
		public ModelAndView getShopCoupon(String ch,HttpServletRequest request) throws Exception
		{
			ch="3";
			ModelAndView view = new ModelAndView();
			try{
			List<Coupon> list = couponService.getShopCoupon(WebSetEnum.wap.getValue());
			request.setAttribute("coupon", list);
			view.setViewName("/template/wap/coupon/ShopCoupon");
			//return ErrorRedirect.getInstance().wapRedirectErrorModel();
			
			return view;
			}catch(Exception e){
				LogHandle.error(LogType.Coupon, "获取店铺的优惠券异常，信息：" + e, "CouponInfo/showCoupon");
				view.setViewName("/template/wap/error/index.html");
				return view;
			}
		}
		
		@RequestMapping(value = "/orderCoupon.html", produces = "text/html;charset=UTF-8")
		public ModelAndView orderCounpon(){
			ModelAndView model = new ModelAndView("/template/wap/coupon/orderCoupon");
			List<CouponDto> dtos = new ArrayList<CouponDto>();
			try {
				SessionUser user = SessionState.GetCurrentUser();
				if (user == null || user.getCode() != 0) {
					model = new ModelAndView("/template/wap/userinfo/login");//跳转登陆页
					return model;
				}
				int userid = user.getUserId();
				// int userid=1;
				String paramstr = GetCookie.getcookies("search_orderCoupon");
				if (!StringUtilsEX.IsNullOrWhiteSpace(paramstr)) {
					JSONArray json = JSONArray.fromObject(paramstr);
					
					@SuppressWarnings("unchecked")
					List<SearchCouponDto> searchCouponDtos = (List<SearchCouponDto>) JSONArray.toCollection(json, SearchCouponDto.class);
					dtos = couponService.getOrderCoupon(searchCouponDtos, userid,ActivityUsePlatformEnum.wap.getValue());
				}
			} catch (Exception e) {
				LogHandle.error(LogType.Coupon, "用户可用优惠券页面访问异常，信息：" + e, "WapCoupon/orderCounpon");
				model = new ModelAndView("/template/wap/error/index");
			}
			model.addObject("coupons", dtos);
			//return ErrorRedirect.getInstance().wapRedirectErrorModel();
			
			return model;
		}
	}

