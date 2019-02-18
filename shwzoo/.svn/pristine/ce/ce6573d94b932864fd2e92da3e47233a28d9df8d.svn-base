package com.yinlian.view.wap.controller;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.api.app.dto.OrderdetailDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.ShopShoppingCartDto;
import com.yinlian.wap.dto.OrdersDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.ActivityDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.Dispatching;
import com.yinlian.wssc.web.po.Invoice;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Receiveaddress;
import com.yinlian.wssc.web.po.Receiveinfo;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.V_Freights;
import com.yinlian.wssc.web.service.ActivityService;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.CartService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.DispatchingService;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.InvoiceService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ReceiveAddressService;
import com.yinlian.wssc.web.service.ReceiveInfoService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/order")
public class WapOrderViewController {
	@Autowired
	private CartService cartService;
	@Autowired
	private ReceiveAddressService addressService;
	@Autowired
	private FreightService freightService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private ReceiveInfoService receiveInfoService;
	@Autowired
	private DispatchingService dispatchingService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private OrderdetailService orderdetailService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private UsercapitalService usercapitalService;
	@Autowired
	private ProvinceServcice provinceService;
	@Autowired
	private CityServcie cityService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private UserService userService;
	@Autowired
	private TopicService topservice;
	@Autowired
	private AdverisingService  adservice;
	@Autowired
	private ActivityService actservice;
	@RequestMapping("/pay.html")
	public ModelAndView oderPay(String ogc){
		ModelAndView model = new ModelAndView("/template/wap/Order/orderPay");
		/*model.setViewName("redirect:/wap/404.html");*/
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user == null || user.getId() <= 0) {
				return model;
//				return new ModelAndView("/template/wap/userinfo/login");
			}
			List<Orders> orders = orderService.getOrderByGroupCode(ogc);
			double price = 0;
			for (Orders order : orders) {
				BigDecimal ofm = new BigDecimal(0);
				if(order.getFreight()!=null&& Double.parseDouble(order.getFreight().toString())>0){
					ofm = order.getFreight();
				}
				int beans=order.getBeans();
				if(order.getDiscount()>0){
				price += Double.parseDouble(order.getPrice().toString())+Double.parseDouble(ofm.toString())-Double.parseDouble(order.getDiscount().toString())-beans/100;
				}else{
				price += Double.parseDouble(order.getPrice().toString())+Double.parseDouble(ofm.toString())-beans/100;
				}
				
			}
			model.addObject("ogc", ogc);
			model.addObject("price", price);
			Usercapital usercapital = usercapitalService.queryByUserId(user.getUserId());
			model.addObject("blance", usercapital.getBalance());
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.Api, "访问订单支付页错误：{0}", e, "Wap/Order");
		}
		return model;
	}
	@RequestMapping("/paysuccess.html")
	public ModelAndView oderPaySuccess(String price,String paytype,String ispay,String reason){
		ModelAndView model = new ModelAndView("/template/wap/Order/orderpaysuccess");
		/*model.setViewName("redirect:/wap/404.html");*/
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user == null || user.getId() <= 0) {
				//return model;
				return new ModelAndView("/template/wap/userinfo/login");
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(price)){
				price="0";
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(paytype)){
				paytype="0";
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(ispay)){
				ispay="true";
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(reason)){
				reason="";
			}
			model.addObject("paytype", paytype);
     		model.addObject("price", price);
     		model.addObject("ispay", ispay);
     		model.addObject("reason", reason);
     		//获取主题
			List<Api_TopicBySpuDto> sputj= topservice.getIndexTopic(PageMarkType.支付成功页.getValue(),TopicMarkEnum.会员推荐.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.wap.getValue()),5);
			model.addObject("sputj", sputj);
			//获取广告
			List<Advertising> adlist=adservice.getListByTypeAndDisplay(PageMarkType.支付成功页.getValue(), 1, WebSetEnum.wap.getValue());
			model.addObject("adlist", adlist);
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.error(LogType.wap, "访问支付成功页错误：{0}", e, "Wap/Order");
		}
		return model;
	}
	@RequestMapping("/newOrder.html")
	public String newOrder(String adid,Integer cpid,HttpServletRequest  request){		
		SessionUser user;
		CartDto cart = new CartDto();
		double freight = 0;
		try {
			user = SessionUtil.getSessionUser(request);
			if (user == null || user.getId() <= 0) {
//				return "redirect:/wap/404.html";
				return "/template/wap/userinfo/login";
			}			
			cart = cartService.getBuyCart(user.getId(),ActivityUsePlatformEnum.wap.getValue());			
			
			Receiveaddress address = addressService.getDefaultAddress(user.getId());
			if(StringUtilsEX.ToInt(adid)>0){
				address=addressService.selectByPrimaryKey(StringUtilsEX.ToInt(adid));
			}
			if(address.getId()==null||address.getId()==0){
				String backhref="/wap/order/newOrder.html";
				if(cpid!=null&&cpid>0){
					backhref=backhref+"?cpid="+cpid.toString();
				}
				request.setAttribute("href", backhref);
				return "/template/wap/address/WritAddress";
			}
			request.setAttribute("address", address);
			String shengString=address.getProvincename();
			if(shengString.contains("省")){
				shengString=shengString.substring(0, shengString.length()-1);
			}
			if (address.getId() != null && address.getId() > 0) {
				List<V_Freights> fs = freightService.getFreightPrice(cart.shops.stream().map(x->x.getShopid()).collect(Collectors.toList()), shengString);
				if(fs!=null && fs.size()>0){
					for (ShopShoppingCartDto shop : cart.shops) {
						if(!shop.getSpuscd().stream().allMatch(x -> x.getIsPostage()))
						{
							for (V_Freights vf : fs) {
								if (shop.getShopid() == vf.getShopID()) {							
									Double fm = orderService.freightMoney(vf, shop.getCount(), new BigDecimal(shop.getTotalmoney()),0);
									shop.setFreightMoney(fm);
									freight += fm;
								}
							}
						}else{
							shop.setFreightMoney(0.0);
						}
					}
				}else{//无运费模板
					for (ShopShoppingCartDto shop : cart.shops) {						
							shop.setFreightMoney(0.0);						
					}
				}				
			}
			cart.setMoney(cart.getMoney()+(float)freight);
			cart.setAmountpayable(cart.getAmountpayable()+(float)freight);
			
			int points=0;//用户积分
			Users users = userService.queryById(user.getUserId());
			if (users != null && users.getPoints() != null) {
				points = users.getPoints();
			}
			int usepoints=0;//用户可用积分
			double pointMoney=0;
			if(points>100){
				pointMoney=Math.floor(points/100);
			}
			if(pointMoney>0){
				double apay = cart.getAmountpayable();
				if (apay > 2) {
					double ap = Math.floor(apay / 2);
				if(ap>pointMoney){
					usepoints=StringUtilsEX.ToInt(Double.toString(pointMoney*100).split("\\.")[0]);					
				}else{						
					usepoints=StringUtilsEX.ToInt(Double.toString(Math.floor(ap*100/100)*100).split("\\.")[0]);
					pointMoney=ap;
				}
				if(usepoints<100){//可用积分要大于100 且等值金额小于订单金额的1/2
					usepoints=0;
				}
			  }
			}
			//优惠券
			Float cpu=0f;
			Boolean cpisowned=false;
			int cpshop=0;
			if (cpid!=null&&cpid>0) {
				Coupon coupon = couponService.getByID(cpid);
				cpu=coupon.getFacevalue();
				cpshop = coupon.getShopid();				
				if(cpshop>0){
					Shop shop=shopService.queryById(cpshop);
					cpisowned =shop.getIsowned();
				}				 
			}
			request.setAttribute("cpiprice", cpu);
			request.setAttribute("cpid", cpid);
			request.setAttribute("cpisowned", cpisowned);
			request.setAttribute("cpshop", cpshop);
			cart.setDelmoney(cart.getDelmoney()+(float)cpu);
			cart.setAmountpayable(cart.getAmountpayable()-(float)cpu);
			//积分
			double paymoney=cart.getAmountpayable()-pointMoney;
			request.setAttribute("points",points);//总共积分
			request.setAttribute("usepoints",usepoints);//可用积分
			request.setAttribute("paymoney",paymoney);//订单支付的钱
			request.setAttribute("pointMoney",pointMoney);//积分兑换的钱
			
		} catch (Exception e) {
			e.printStackTrace();			
			LogHandle.error(LogType.Api, "访问订单生成页错误：{0}", e, "Wap/Order");
			return ErrorRedirect.getInstance().wapRedirect("订单页面错误");	
		}
		request.setAttribute("cart", cart);
		request.setAttribute("freight", freight);
		/*return "redirect:/wap/404.html";*/
		return "/template/wap/shopcart/newOrder";
	}
	 /**
     * 会员中心-首页
     * 
     * @return
     * @throws Exception
     */
	@RequestMapping("/homepage")
	public ModelAndView homepage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/template/wap/memberhome/MemberHomepage");
		/*view.setViewName("redirect:/wap/404.html");*/
		return view;
	}
	/**
	 * 查询当前顾客的全部订单
	 * status 订单状态
	 * @param buyerid
	 * @return
	 */
	@RequestMapping(value = "/getorder", produces = "text/html;charset=UTF-8")
	public ModelAndView getorder(String status, String page, String size,
			String ch, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		ModelAndView view = new ModelAndView();
		ch="3";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				// item.setCode(-102);
				// item.setDesc("通道不能为空");
				view.setViewName("/template/error/index.html");
				return view;
			}
			SessionUser user = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				view.setViewName("/template/wap/userinfo/login");
				/*view.setViewName("redirect:/wap/404.html");*/
                return view;
			} else {
				view.addObject("status", status);
				view.setViewName("/template/wap/Order/OrderAll");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询订单异常：" + e.getMessage());
			LogHandle.error(LogType.Api, "查询订单异常! 异常信息:{0}", e,
					"order/getorder");
			view.setViewName("/template/wap/index");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;
		}
		//return "/template/wap/Order/OrderAll";

	}
	/**
	 * 查询当前顾客的某个状态的订单
	 * 
	 * @param buyerid
	 * @param status
	 * @return
	 * 
	 */
	@RequestMapping(value = "/slectByIDAndStatus", produces = "text/html;charset=UTF-8")
	public ModelAndView slectByIDAndStatus(String status, String ch,
			HttpServletRequest request) {
		ch = "3";
		BaseResult item = new BaseResult();
		ModelAndView view = new ModelAndView();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(status)) {
				view.setViewName("/template/error/index.html");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				view.setViewName("/template/error/index.html");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				view.setViewName("/template/wap/userinfo/login");
                return view;
			} else {
				int buyerId = sessionUser.getUserId();
				// int buyerId=492;
				List<Orders> list = null;

				list = orderService.queryByStatus(buyerId,
						StringUtilsEX.ToInt(status));
				if (list == null || list.size() <= 0) {
					view.setViewName("/template/error/index.html");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;
				} else {
					request.setAttribute("orders", list);
					view.setViewName("/template/wap/Order/OrderStatues");
					/*view.setViewName("redirect:/wap/404.html");*/
					return view;

				}

			}

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询当前顾客的某个状态的订单异常：" + e.getMessage());
			LogHandle.error(
					LogType.Api,
					MessageFormat.format("查询当前顾客的某个状态的订单异常! 异常信息:{0}",
							e.toString()), "order/slectByIDAndStatus");
			view.setViewName("/template/error/index.html");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;
		}
		
	}
	/**
	 * 查询订单明细
	 * 
	 * @param token
	 * @param orderid
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/showOrderDetail", produces = "text/html;charset=UTF-8")
	public ModelAndView showOrderDetail(String orderid, String ch,
			HttpServletRequest request) {
		ch = "3";
		ModelAndView view = new ModelAndView();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(orderid)) {
				view.setViewName("/template/error/index.html");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;

			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				view.setViewName("/template/error/index.html");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;

			}

			SessionUser user = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				view.setViewName("/template/wap/userinfo/login");
				/*view.setViewName("redirect:/wap/404.html");*/
                return view;
			}

			Orders orders = orderService
					.queryByID(StringUtilsEX.ToInt(orderid));
			if (orders != null) {
				Shop shop = shopService.queryById(orders.getShopid());
				OrdersDto orderDto = new OrdersDto();
				orderDto.setShopname(shop.getName());
				orderDto.setShopimgurl(shop.getImgurl());
				Receiveinfo receiveinfo = receiveInfoService
						.queryByOrderGroupId(orders.getGroupcode());
				if (receiveinfo != null) {
					orderDto.setConsignee(receiveinfo.getConsignee());
					orderDto.setAddress(receiveinfo.getAddress());
					Province province = provinceService.queryByCode(receiveinfo.getProvince());
					City city  = cityService.queryByCode(receiveinfo.getCity());
					Area area = areaService.queryByCode(receiveinfo.getArea());
                    if (province != null && province.getId() > 0) {
						orderDto.setProvincename(province.getName());
					}
					if (city != null && city.getId() > 0) {
						orderDto.setCityname(city.getName());
					}
					if (area != null && area.getId() > 0) {
						orderDto.setAreaname(area.getName());
					}
					
					 orderDto.setProvincename(province.getName());
					 
					 orderDto.setCityname(city.getName());
					 orderDto.setAreaname(area.getName());
					orderDto.setTelPhone(receiveinfo.getTelphone());
				}

				orderDto.setCode(orders.getCode());
				orderDto.setId(StringUtilsEX.ToInt(orderid));
				orderDto.setPayType(orders.getPaytype());
				orderDto.setStatus(orders.getStatus());
				orderDto.setRemark(orders.getRemark());
				orderDto.setOrderDate(orders.getAddorderdate());
				Integer beans = orders.getBeans();
				if (beans == null) {
					beans = 0;
				}
				orderDto.setPointsPay(StringUtilsEX.ToDoubleNull(Integer.toString(beans/100)));

				Dispatching dispatching = dispatchingService
						.queryByOrderGroupCode(orders.getGroupcode());
				if (dispatching != null) {
					orderDto.setTransportMode(dispatching.getType());
				}

				orderDto.setFreight(StringUtilsEX.ToFloat(orders.getFreight()
						.toString()));
				orderDto.setDeliverDate(orders.getDeliverdate());
				// 查询订单发票
				Invoice invoice = invoiceService.selectByOrderId(StringUtilsEX
						.ToInt(orderid));
				if (invoice != null) {
					orderDto.setInvoiceTitle(invoice.getTitle());
					orderDto.setInvoiceContent(invoice.getContent());
				}

				Double acutalpay = StringUtilsEX.ToDoubleNull(orders
						.getActualpay().toString());

				orderDto.setActualPay(acutalpay);
				orderDto.setPrice(orders.getPrice());
                ActivityDto act=new ActivityDto();
                if(orders.getActivityid()>0){
                	act=actservice.getActivityDtoByID(orders.getActivityid());
                }
                
				List<OrderdetailDto> orderdetails = orderdetailService.selectByOrderid(StringUtilsEX.ToInt(orderid));
                 
				orderDto.setChildren(orderdetails);
				orderDto.setCount(orderdetails.size());
				request.setAttribute("detail", orderDto);
				request.setAttribute("act",act);
				String href=request.getParameter("href");
				request.setAttribute("href",href);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.debug(LogType.Api,
					MessageFormat.format("查询订单明细异常! 异常信息:{0}", e.toString()),
					"order/showOrderDetail");
			view.setViewName("/template/error/index");
		   return ErrorRedirect.getInstance().wapRedirectModel("查询订单明细异常");
		}		
		view.setViewName("/template/wap/Order/OrderDetail");
		/*view.setViewName("redirect:/wap/404.html");*/
		return view;

	}
	/**
	 * 返回退换货页面
	 */
	
	@RequestMapping(value = "/changproduct", produces = "text/html;charset=UTF-8")
	public ModelAndView changproduct(String orderdet, String ch,
			HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(orderdet)) {
				view.setViewName("/template/error/index");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;

			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				view.setViewName("/template/error/index");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;

			}
			String token = CookieUtils.getTokenFromCookie(request);
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				view.setViewName("/template/wap/userinfo/login");
				/*view.setViewName("redirect:/wap/404.html");*/
                return view;
			}

			Orderdetail orderdetail =orderdetailService.queryByID(StringUtilsEX.ToInt(orderdet));
			if (orderdetail != null) {
				request.setAttribute("pro", orderdetail);
				view.setViewName("/template/wap/Order/ChangProduct");
				/*view.setViewName("redirect:/wap/404.html");*/
				return view;
			}
			view.setViewName("/template/erroe/index");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;

		} catch (Exception e) {

			LogHandle.debug(LogType.Api,
					MessageFormat.format("查询订单明细异常! 异常信息:{0}", e.toString()),
					"order/showOrderDetail");
			view.setViewName("/template/error/index");
			/*view.setViewName("redirect:/wap/404.html");*/
			return view;
		}

	}
	
}
