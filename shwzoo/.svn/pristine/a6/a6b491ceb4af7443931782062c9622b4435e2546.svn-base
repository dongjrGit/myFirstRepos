package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.OrderCountDto;
import com.yinlian.wap.dto.UserInfoDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.BankCard;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AcctountNoService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.BankCardService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.PointRecordService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.SmsService;
import com.yinlian.wssc.web.service.UserAttrService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

	@Controller
	@RequestMapping("/wap/userinfo")
	public class WapUserInfoViewController {
		
		@Autowired
		private UserService userService;

		@Autowired
		private SmsService smsservice;
		
		@Autowired
		private AccountsService accountsService;
		
		@Autowired
		private BankCardService bankcardService;


		@Autowired
		private UserAttrService userAttrService;

		@Autowired
		private ProvinceServcice provinceService;

		@Autowired
		private CityServcie cityService;

		@Autowired
		private AreaService areaService;

		@Autowired
		private PointRecordService pointRecordService;

		@Autowired
		private BankCardService bankCardService;

		@Autowired
		private UserslevelService userslevelService;
		@Autowired
		private AcctountNoService acctountNoService;
		
		@Autowired
		private UsercapitalService usercapitalService;
		@Autowired
		private OrderService orderService;
		@Autowired
		private MessageService messageService;
		@Autowired
		private CouponService couponService;
		@Autowired
		private BankCardService bankcardservice;
		@Autowired
		private GroupBuyOrderService   groupBuyOrderService;
		
		 /**
	     * 返回服务反馈页面
	     */
	    @RequestMapping("/service")
	    public ModelAndView service() {
			/*ModelAndView model=new ModelAndView();
			model.setViewName("redirect:/wap/404.html");
			return model;*/
	        return new ModelAndView("/template/wap/servicefeedback/ServiceFeedback");
	    }
	    
	    /**
		 * 显示我的信息
		 * 
		 * @param userId
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/selectInfo", produces = "text/html;charset=UTF-8")
		public ModelAndView selectInfo(String ch,HttpServletRequest request) throws Exception {
			ModelAndView view = new ModelAndView();
			try {
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					view.setViewName("/template/error/index.html");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;
				}
				SessionUser sessionUser = new SessionUser();
				String token = CookieUtils.getTokenFromCookie(request);
				sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
					//JOptionPane.showMessageDialog(null, "你还没有登录，请先登录", "请先登录", JOptionPane.ERROR_MESSAGE); 
					view.setViewName("/template/wap/userinfo/login");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;
			} else {
					int userId = sessionUser.getUserId();
	                 // int userId=492;
					UserInfoDto userInfo = new UserInfoDto();
					userInfo.setUserId(String.valueOf(userId));
					Users users = new Users();
					users = userService.queryById(userId);
					if (users == null) {
						view.setViewName("/template/userinfo/login");
						/*view.setViewName("redirect:/wap/404.html");*/
						return view;

					} else {
						userInfo.setImgUrl(users.getImgurl());
						userInfo.setRealName(users.getRealname());
						userInfo.setPayPassStatus(users.getPaypassstatus());
						List<UserAttr> list = new ArrayList<UserAttr>();
						list = userAttrService.selectById(userId);
						if (list != null & list.size() > 0) {
							UserAttr userattr = new UserAttr();
							userattr = list.get(0);
							Date date = userattr.getBirthday();
							if (date != null) {
								Date today = new Date();
								userInfo.setAge(DateUtil.getYear(today) - DateUtil.getYear(date));
								userInfo.setBirthday(date);
							} else {
								userInfo.setAge(0);
							}
							String provincename = userattr.getProvincename();
							String cityname = userattr.getCityname();
							String areaname = userattr.getAreaname();
							String address = userattr.getAddress();
							String hometown = "";
							if (provincename != null && cityname != null & areaname != null) {
								hometown = provincename + "-" + cityname + "-" + areaname;
							}
							
							userInfo.setHometown(hometown);
							userInfo.setLocation(address);
							request.setAttribute("userInfos",userInfo);
							view.setViewName("/template/wap/userinfo/UserInfo");
							/*view.setViewName("redirect:/wap/404.html");*/
							return view;
						} else{
							view.setViewName("/template/wap/index");
							/*view.setViewName("redirect:/wap/404.html");*/
							return view;
						}
					}
				}
			} catch (Exception e) {
				LogHandle.error(LogType.wap, MessageFormat.format("显示我的信息异常! 异常信息:{0}", e.getMessage()),
						"userinfo/selectInfo");
				view.setViewName("/template/wap/index");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}

			//return "/template/wap/userinfo/UserInfo";
		}
		


		@RequestMapping(value = "/infoBalance", produces = "text/html;charset=UTF-8")
		public ModelAndView infoBalance(HttpServletRequest request){
			try {
				/*ModelAndView model=new ModelAndView();
				model.setViewName("redirect:/wap/404.html");*/
				SessionUser sessionUser = new SessionUser();
				String token = CookieUtils.getTokenFromCookie(request);
				sessionUser = SessionState.GetCurrentUser(token);
				if (sessionUser.getCode() != 0) {
					//return model;
					return new ModelAndView("/template/wap/userinfo/login");
				}
				Usercapital userb=usercapitalService.queryByUserId(sessionUser.getUserId());
				request.setAttribute("price",userb.getBalance());
			
				//return model;
				return new ModelAndView("/template/wap/userinfo/user_balance");
			} catch (Exception e) {
				e.printStackTrace();
				LogHandle.error(LogType.wap, MessageFormat.format("显示我的余额异常! 异常信息:{0}", e.getMessage()),"userinfo/infoBalance");
				return new ModelAndView("/template/wap/index");
				/*ModelAndView model=new ModelAndView();
				model.setViewName("redirect:/wap/404.html");
				return model;*/
			}
		}
		
		/**
		 * 查询用户的当前积分
		 * 
		 * @param userId
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/totalPoints", produces = "text/html;charset=UTF-8")
		public ModelAndView totalPoints(HttpServletRequest request) {
			ModelAndView view = new ModelAndView();
			String ch="3";
			try {
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					view.setViewName("/template/error/index");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;
				}
				SessionUser sessionUser = new SessionUser();
				String token = CookieUtils.getTokenFromCookie(request);
				sessionUser = SessionState.GetCurrentUser(token);
				if (sessionUser.getCode() != 0) {
					view.setViewName("/template/wap/userinfo/login");
					/*view.setViewName("redirect:/wap/404.html");*/
	                return view;
				} else {
					int userId = sessionUser.getUserId();
				    //int userId=492;
					int currentPoints = 0;

					Users users = userService.queryById(userId);
					if (users != null && users.getPoints() != null) {
						currentPoints = users.getPoints();
					}
					request.setAttribute("points",currentPoints );
					
				}
			} catch (Exception e) {
				
				LogHandle.error(LogType.wap, MessageFormat.format("查询用户的当前积分异常! 异常信息:{0}", e.getMessage()),
						"userinfo/totalPoints");
				view.setViewName("/template/wap/index");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
			view.setViewName("/template/wap/point/Point");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;
			
		}
		
		/*
		 * 会员主页
		 * 
		 */
		@RequestMapping(value = "/homepage", produces = "text/html;charset=UTF-8")
		public ModelAndView homepage(String ch,HttpServletRequest request) throws Exception {
			ModelAndView view = new ModelAndView();
			try {
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					view.setViewName("/template/wap/index");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;
				}
				SessionUser sessionUser = new SessionUser();
				String token = CookieUtils.getTokenFromCookie(request);
				sessionUser = SessionState.GetCurrentUser(token);
				UserInfoDto userInfo = new UserInfoDto();
				if (sessionUser.getCode() != 0) {
					request.setAttribute("userInfo",userInfo);
					view.setViewName("/template/wap/memberhome/MemberHomepage");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;
				} else {
					int userId = sessionUser.getUserId();
	                  //int userId=492;
					OrderCountDto dto = orderService.selectCount(userId);
					Integer dfk=groupBuyOrderService.selectDfkCount(userId);
					Integer dpj=groupBuyOrderService.selectDplCount(userId);
					Integer dxf=groupBuyOrderService.selectDxfCount(userId);
					Integer tk=groupBuyOrderService.selectTkCount(userId);
					request.setAttribute("dfk", dfk);
					request.setAttribute("dpj", dpj);
					request.setAttribute("dxf", dxf);
					request.setAttribute("tk", tk);
					userInfo.setChildren(dto);
					userInfo.setUserId(String.valueOf(userId));
					
					Users users = new Users();
					users = userService.queryById(userId);
					if (users == null) {
						view.setViewName("/template/wap/index");
						/*view.setViewName("redirect:/wap/404.html");*/
						return view;

					} else {
						int currentPoints = users.getPoints();
						userInfo.setPoints(currentPoints);
						int messagecount=messageService.getCount(userId);
						userInfo.setMessagecount(messagecount);
						int couponcount = couponService.getWapCouponCount(userId,ActivityUsePlatformEnum.wap.getValue());
						userInfo.setCouponcount(couponcount);
						int bankcard = bankcardservice.selectCount(userId);
						Usercapital usercapital = usercapitalService.queryByUserId(userId);
	        			userInfo.setBlance(usercapital.getBalance());
						userInfo.setBankcard(bankcard);
						userInfo.setImgUrl(users.getImgurl());
						userInfo.setRealName(users.getRealname());
						
							request.setAttribute("userInfo",userInfo);
							
						} 
					}
				
			} catch (Exception e) {
				e.printStackTrace();
				LogHandle.error(LogType.wap, MessageFormat.format("显示会员主页异常! 异常信息:{0}", e.getMessage()),
						"userinfo/selectInfo");
				view.setViewName("/template/wap/index");
				return view;
			}
			view.setViewName("/template/wap/memberhome/MemberHomepage");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;
		}
		
		/**
		 * 
		 *查询银行卡信息
		 * @param ch
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/queryBankcard", produces = "text/html;charset=UTF-8")
		public ModelAndView queryBankcard(String ch,HttpServletRequest request){
			BaseResult item = new BaseResult();
			ModelAndView view = new ModelAndView();
			try{
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					view.setViewName("/template/error/index");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;
				}
				SessionUser sessionUser = new SessionUser();
				String token = CookieUtils.getTokenFromCookie(request);
				sessionUser = SessionState.GetCurrentUser(token);
				if (sessionUser.getCode() != 0) {
					view.setViewName("/template/wap/userinfo/login");
					/*view.setViewName("redirect:/wap/404.html");*/
	                return view;
				} else {
					int userid = sessionUser.getUserId();
					List<BankCard> bankcard = bankcardService.queryBrandCardByUserId(userid);
					item.setCode(0);
					item.setData(bankcard);
					item.setDesc("获取银行卡信息成功");
					request.setAttribute("bankcard", bankcard);
					view.setViewName("/template/wap/bankcard/BankCard");
					/*view.setViewName("redirect:/wap/404.html");*/
	                return view;
				}
				
			}catch(Exception e){
				item.setCode(-900);
				item.setDesc("系统错误");
				LogHandle.error(LogType.wap,MessageFormat.format("查询银行卡信息! 异常信息:{0}", e.getMessage()),"userinfo/updUserInfo");
				view.setViewName("/template/error/index");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
			
		
		}
		
		/**
		 * 
		 *查询余额信息
		 * @param ch
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/balance", produces = "text/html;charset=UTF-8")
		public ModelAndView balance(String ch,HttpServletRequest request){
			BaseResult item = new BaseResult();
			ModelAndView view = new ModelAndView();
			try{
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					view.setViewName("/template/error/index");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;
				}
				SessionUser sessionUser=SessionUtil.getSessionUser(request);
				if (sessionUser.getCode() != 0) {
					view.setViewName("/template/wap/userinfo/login");
					/*view.setViewName("redirect:/wap/404.html");*/
	                return view;
				} else {
					int userid = sessionUser.getUserId();
					Usercapital usercapital = usercapitalService.queryByUserId(userid);
        			request.setAttribute("userbalance", usercapital.getBalance());
					view.setViewName("/template/wap/balance/Balance");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;
				}
				
			}catch(Exception e){
				item.setCode(-900);
				item.setDesc("系统错误");
				LogHandle.error(LogType.Api,
						MessageFormat.format("查询银行卡信息! 异常信息:{0}", e.getMessage()),
						"userinfo/updUserInfo");
				view.setViewName("/template/error/index");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
		}
}
