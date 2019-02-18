package com.yinlian.view.pc.controller;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.Enums.UserCollectTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.api.app.dto.OrderCommentCountDto;
import com.yinlian.api.app.dto.OrderCountDto;
import com.yinlian.api.app.dto.OrderMemberDto;
import com.yinlian.api.app.dto.OrderdetailDto;
import com.yinlian.api.app.dto.OrdersDto;
import com.yinlian.pc.dto.ConcernSpuDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.search.Pc_OrderCriteria;
import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SnewsClassMapper;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.Dispatching;
import com.yinlian.wssc.web.po.Invoice;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Receiveinfo;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.ArticlesService;
import com.yinlian.wssc.web.service.BankCardService;
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.DispatchingService;
import com.yinlian.wssc.web.service.InvoiceService;
import com.yinlian.wssc.web.service.LogisticsService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.NavclassfyService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ReceiveInfoService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 订单
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年7月15日
 */
@Controller
@RequestMapping("/member/order")
public class PcOrderViewController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ReceiveInfoService receiveInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private BankCardService bankcardservice;
	@Autowired
	private OrderdetailService orderdetailService;
	@Autowired
	private SnewsClassMapper snewsclassMapper;
	@Autowired
	private UsercollectService usercollectService;
	@Autowired
	private SpuService spuService;
	@Autowired
	private BrowsehistoryService browsehistoryServise;

	@Autowired
	private DispatchingService dispatchingService;
	@Autowired
	private InvoiceService invoiceService;
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
	private CommentService commentService;

	@Autowired
	private TopicService topicService;
	
	@Autowired
	private NavclassfyService navservice;
	
	@Autowired
	private LogisticsService logisticService;
	@Autowired
	private ArticlesService articlesService;
	/**
	 * 
	 * @param type  类型
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getorder.html", produces = "text/html;charset=UTF-8")
	public ModelAndView getorder(String status,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		ModelAndView view = new ModelAndView();
		try {			
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				//所有的订单
				Pc_OrderCriteria aoc = new Pc_OrderCriteria();
				aoc.setUserid(user.getUserId());
				if(status==null){
					status="-1";
				}
				aoc.setStatus(StringUtilsEX.ToInt(status));
				PageBean orders = orderService.getMemberListOrderPage(1,10,aoc);
 				request.setAttribute("totalcount",orders.getTr());
				request.setAttribute("pageindex",orders.getPc());
				request.setAttribute("ordergroup", orders.getBeanList());
				request.setAttribute("orderscount",orderService.selectCount(user.getUserId()));
				request.setAttribute("status", status);
				view.setViewName("/template/pc/memberCenter/DdZx/OrderList");
				return view;
			}

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询订单异常：" + e.getMessage());
			LogHandle.error(LogType.pc, "查询订单异常! 异常信息:{0}", e,
					"order/getorder");
			view.setViewName("redirect:/404.html");
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
		ModelAndView view = new ModelAndView();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(orderid)) {
				view.setViewName("redirect:/404.html");
				return view;
			}
			SessionUser user = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
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
					Province province = provinceService.queryByCode(receiveinfo
							.getProvince());
					City city = cityService.queryByCode(receiveinfo.getCity());
					Area area = areaService.queryByCode(receiveinfo.getArea());
					orderDto.setProvincename(province.getName());
					orderDto.setCityname(city.getName());
					orderDto.setAreaname(area.getName());
					orderDto.setTelPhone(receiveinfo.getTelphone());
				}

				orderDto.setCode(orders.getCode());
				orderDto.setId(StringUtilsEX.ToInt(orderid));
				orderDto.setPayType(orders.getPaytype());
				orderDto.setPayDate(orders.getPaydate());
				orderDto.setStatus(orders.getStatus());
				orderDto.setRemark(orders.getRemark());
				orderDto.setOrderDate(orders.getAddorderdate());
				Integer beans = orders.getBeans();
				if (beans == null) {
					beans = 0;
				}
				orderDto.setPulsePay(StringUtilsEX.ToDoubleNull(beans + ".00"));

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
					orderDto.setInvoiceType(invoice.getType());
				}

				Double acutalpay = StringUtilsEX.ToDoubleNull(orders
						.getActualpay().toString());

				orderDto.setActualPay(acutalpay);
				orderDto.setPrice(orders.getPrice());
				
				BigDecimal totalm=orderDto.getPrice().add(new BigDecimal(orderDto.getFreight().doubleValue()));
				BigDecimal paym=new BigDecimal(orderDto.getActualPay());
				orderDto.setDiscount(totalm.subtract(paym).doubleValue());
				
				List<OrderdetailDto> orderdetails = orderdetailService
						.selectByOrderid(StringUtilsEX.ToInt(orderid));

				orderDto.setLogisticname(logisticService.getByCode(orders.getLogisticsname()));
				orderDto.setLogisiticcode(orders.getLogisticscode());
				
				orderDto.setChildren(orderdetails);
				orderDto.setCount(orderdetails.size());
				request.setAttribute("detail", orderDto);

				
			}

		} catch (Exception e) {
			LogHandle.error(LogType.pc,"查询订单明细异常!", e,
					"order/showOrderDetail");
			view.setViewName("redirect:/template/pc/404");
			return view;
		}
		view.setViewName("/template/pc/memberCenter/DdZx/OrderDetail");
		return view;
	}
	
	/**
	 * 支付页
	 * 
	 * @author kh.wang
	 * @since 2016年7月15日
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/orderPay.html")
	public ModelAndView orderPay(String gc,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView view =new ModelAndView("/template/pc/Orders/order_pay");
		SessionUser user = new SessionUser();
		String token = CookieUtils.getTokenFromCookie(request);
		user = SessionState.GetCurrentUser(token);
		Receiveinfo receiveinfo=new Receiveinfo();
		Double actualpay=0.00;
		try {
			List<Orders> list=orderService.getOrderByGroupCode(gc);
			if(list!=null && list.size()>0){
				actualpay = list.stream().mapToDouble(x -> x.getActualpay().doubleValue()).sum();
				/*if(list.get(0).getStatus()==OrderStatusEnum.待发货.getValue()){
					response.sendRedirect("/member/order/orderPaySuccess.html?out_trade_no="+gc
							+"&total_fee="+actualpay);
					return view;
				}else if(list.get(0).getStatus()!=OrderStatusEnum.待付款.getValue()){
					view.setViewName("/template/error/index");
					return view;
				}*/
				receiveinfo = receiveInfoService.queryByOrderGroupId(list.get(0).getGroupcode());				
			}
			view.addObject("actualpay", actualpay);
			view.addObject("receiveinfo", receiveinfo);
			view.addObject("ordergroup", gc);
			initFoot(request);;
			view.addObject("userinfo", user);
			List<NavclassfyDto> lists=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "首页底部"); //网站资讯
			
			List<NavclassfyDto> artlist=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "关于我们"); //关于我们

			view.addObject("navsfw",lists);
			view.addObject("navszx",artlist);
			
			List<NavclassfyDto> listkh=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "客户服务"); //客户服务
		view.addObject("navskhfw",listkh);
		} catch (Exception e) {
			LogHandle.debug(LogType.pc,
					MessageFormat.format("支付页异常! 异常信息:{0}", e.toString()),
					"/member/order/orderPay");
			view.setViewName("redirect:/404.html");
		}
		initFoot(request);
		return view;
	}
	
	/**
	 * 支付成功页
	 * 
	 * @author kh.wang
	 * @since 2016年7月15日
	 * @param orderId 订单id
	 * @return
	 */
	@RequestMapping("/orderPaySuccess.html")
	public ModelAndView orderPaySuccess(HttpServletRequest request){
		ModelAndView view=new ModelAndView("/template/pc/Orders/order_paySuccess");
		try {
			view.addObject("groupnum",request.getParameter("out_trade_no"));
			view.addObject("price",request.getParameter("total_fee"));
			List<Api_TopicBySpuDto> topspuTM= topicService.getIndexTopic(PageMarkType.支付成功页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			view.addObject("cnxh", topspuTM);
			SessionUser user=SessionState.GetCurrentUser();
			initFoot(request);;
			view.addObject("userinfo", user);
			List<ConcernSpuDto> conlist=usercollectService.getConcernSpuByUser(user.getUserId());
			view.addObject("wdgz",conlist);
			
			List<NavclassfyDto> lists=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "首页底部"); //网站资讯
			
			List<NavclassfyDto> artlist=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "关于我们"); //关于我们

			view.addObject("navsfw",lists);
			view.addObject("navszx",artlist);
			
			List<NavclassfyDto> listkh=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "客户服务"); //客户服务
			view.addObject("navskhfw",listkh);
			
			if(user==null || user.getCode()!=0){
				view.addObject("lsjl",null);
			}
			else{
				view.addObject("lsjl", browsehistoryServise.queryDetailByUserId(user.getUserId()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.debug(LogType.pc,
					MessageFormat.format("支付成功异常! 异常信息:{0}", e.toString()),
					"/member/order/orderPaySuccess");
			view.setViewName("redirect:/404.html");
		}
		initFoot(request);
		return view;
	}
	
	/**
	 * 付款失败
	 * 
	 * @author kh.wang
	 * @since 2016年7月18日
	 * @return
	 */
	@RequestMapping("/orderPayError.html")
	public  ModelAndView orderPayError(HttpServletRequest request){
		ModelAndView view=new ModelAndView("/template/pc/Orders/order_payFail");
		try {
			List<Api_TopicBySpuDto> topspuTM= topicService.getIndexTopic(PageMarkType.支付失败页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
					TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()),5);
			view.addObject("cnxh", topspuTM);
			SessionUser user=SessionState.GetCurrentUser();
			initFoot(request);;
			view.addObject("userinfo", user);
			List<ConcernSpuDto> conlist=usercollectService.getConcernSpuByUser(user.getUserId());
			view.addObject("wdgz",conlist);
List<NavclassfyDto> lists=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "首页底部"); //网站资讯
			
			List<NavclassfyDto> artlist=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "关于我们"); //关于我们

			view.addObject("navsfw",lists);
			view.addObject("navszx",artlist);
			
			List<NavclassfyDto> listkh=articlesService.findByAssign(ClassifyPageType.首页, WebSetEnum.pc, "客户服务"); //客户服务
			view.addObject("navskhfw",listkh);
			if(user==null || user.getCode()!=0){
				view.addObject("lsjl",null);
			}
			else{
				view.addObject("lsjl", browsehistoryServise.queryDetailByUserId(user.getUserId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogHandle.debug(LogType.pc,
					MessageFormat.format("支付失败异常! 异常信息:{0}", e.toString()),
					"/member/order/orderPayError");
			view.setViewName("redirect:/404.html");
		}
		initFoot(request);
		return view;
	}
	
	
	  /**
		 * 猜你喜欢
		 * @param ch
		 * @return
		 */
		@RequestMapping(value = "/findspu")
		public @ResponseBody ReusltItem findNspu(String ch, int mark, String page,
				String size,int webset) {
			ReusltItem item = new ReusltItem();
			try {
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					item.setCode(-108);
					item.setDesc("登录通道参数错误");
					return item;
				}

				if ( mark <= 0) {
					item.setCode(-101);
					item.setDesc("专题标识参数错误");
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
				atc.setMark(mark);
				atc.setWebset(webset+"");
				PageBean listBean = topicService.getTopicBySpu(atc, ipage, isize);
				item.setData(listBean.getBeanList());
				item.setPage(listBean.getTp());
				item.setMaxRow(listBean.getTr());
				item.setPageIndex(ipage);
				item.setPageSize(isize);

			} catch (Exception e) {
				item.setCode(-900);
				item.setDesc("新品上市异常：" + e.getMessage());
				LogHandle.error(LogType.Api, "新品上市异常异常! 异常信息:{0}", e,
						"topic/findspu");
			}
			return item;
		}
	
		
	/**
	 * 查询当前顾客的某个状态的订单
	 * 
	 * @param buyerid
	 * @param status  状态
	 * @return
	 * 
	 */
	@RequestMapping(value = "/slectByIDAndStatus", produces = "text/html;charset=UTF-8")
	public ModelAndView slectByIDAndStatus(String page,String size,String statuss,
			HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(statuss)) {
				view.setViewName("/template/error/index.html");
				return view;
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				int buyerid = sessionUser.getUserId();
				//int status = Integer.parseInt(statuss);
				Integer page1 = StringUtilsEX.ToIntNull(page);
				Integer size11 = StringUtilsEX.ToIntNull(size);
				if (page1 == null || page1 <= 0) {
					page1 = 1;
				}
				if (size11 == null || size11 <= 0) {
					size11 = 10;
				}
				Pc_OrderCriteria aoc = new Pc_OrderCriteria();
				aoc.setUserid(buyerid);
				aoc.setSeachstr(statuss);
				PageBean orders = orderService.pcselectByStatus(page1, size11, aoc);
				request.setAttribute("statuss", statuss);
				request.setAttribute("orders", orders.getBeanList());
				request.setAttribute("orderscount",orderService.selectCount(buyerid));
				view.setViewName("/template/pc/memberCenter/DdZx/OrderList");
				return view;

			}
			

		} catch (Exception e) {
			LogHandle.error(
					LogType.pc,
					MessageFormat.format("查询当前顾客的某个状态的订单异常! 异常信息:{0}",
							e.toString()), "order/slectByIDAndStatus");
			view.setViewName("redirect:/404.html");
			return view;
		}

	}

	/**
	 * 取消订单记录
	 * 
	 * @param buyerid
	 * @param status
	 * @return
	 * 
	 */
	@RequestMapping(value = "/cancelorder", produces = "text/html;charset=UTF-8")
	public ModelAndView cancelorder(String page,String size, HttpServletRequest request) {

		BaseResult item = new BaseResult();
		ModelAndView view = new ModelAndView();
		try {
			
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser==null||sessionUser.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				int buyerId = sessionUser.getUserId();
				Integer page1 = StringUtilsEX.ToIntNull(page);
				Integer size11 = StringUtilsEX.ToIntNull(size);
				if (page1 == null || page1 <= 0) {
					page1 = 1;
				}
				if (size11 == null || size11 <= 0) {
					size11 = 10;
				}
				Pc_OrderCriteria aoc = new Pc_OrderCriteria();
				aoc.setUserid(buyerId);
				PageBean list = orderService.cancelorder(page1,size11,aoc);
				request.setAttribute("totalcount",list.getTr());
				request.setAttribute("pageindex",list.getPc());
				request.setAttribute("orders", list.getBeanList());
				view.setViewName("/template/pc/memberCenter/DdZx/OrderCancelHistoryList");
				return view;

			}

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询当前顾客的某个状态的订单异常：" + e.getMessage());
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取取消订单异常! 异常信息:{0}", e.toString()),
					"order/cancelorder");
			view.setViewName("redirect:/404.html");
			return view;
		}
	}
	
	/**
	 * 评价订单列表
	 * 
	 * @param buyerid
	 * @param iscomment  是否评价
	 * @return
	 * 
	 */
	@RequestMapping(value = "/commentorder", produces = "text/html;charset=UTF-8")
	public ModelAndView commentorder(String page,String size,String iscomment,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		ModelAndView view = new ModelAndView();
		try {
			if(StringUtilsEX.IsNullOrWhiteSpace(iscomment))
			{
				view.setViewName("/template/error/index.html");
				return view;
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				int buyerId = sessionUser.getUserId();
			    List<OrderCommentCountDto> count = orderService.queryCount(buyerId);
				//int iscoment = Integer.parseInt(iscomment);
				Integer page1 = StringUtilsEX.ToIntNull(page);
				Integer size11 = StringUtilsEX.ToIntNull(size);
				if (page1 == null || page1 <= 0) {
					page1 = 1;
				}
				if (size11 == null || size11 <= 0) {
					size11 = 10;
				}
				Pc_OrderCriteria aoc = new Pc_OrderCriteria();
				aoc.setUserid(buyerId);
				aoc.setSeachstr(iscomment);
				PageBean list = orderService.commentorder(page1, size11, aoc);
				OrderCountDto orderscount=orderService.selectDetailCount(buyerId);
				request.setAttribute("orderscount",orderscount);
				//request.setAttribute("orderscount",orderService.selectCount(buyerId));
				
			//	List<OrderMemberDto> beanList=new ArrayList<OrderMemberDto>();
				if(StringUtilsEX.ToInt(iscomment)==1){
					List<OrderMemberDto> listf=(List<OrderMemberDto>) list.getBeanList();
					for (OrderMemberDto orderMemberDto : listf) {
						List<OrderdetailDto> subdetaillist=new ArrayList<OrderdetailDto>();
						List<OrderdetailDto> detaillist=orderMemberDto.getChildren();
						subdetaillist=detaillist.stream().filter(a->a.getDetailIsComment()==1).collect(Collectors.toList());
						orderMemberDto.setChildren(subdetaillist);
					}
					list.setBeanList(listf);
					//beanList=listf.stream().filter(a->a.getChildren().)
				}
				
				if(StringUtilsEX.ToInt(iscomment)==0){
					List<OrderMemberDto> listf=(List<OrderMemberDto>) list.getBeanList();
					for (OrderMemberDto orderMemberDto : listf) {
						List<OrderdetailDto> subdetaillist=new ArrayList<OrderdetailDto>();
						List<OrderdetailDto> detaillist=orderMemberDto.getChildren();
						subdetaillist=detaillist.stream().filter(a->a.getDetailIsComment()==0 || a.getDetailIsComment()==null).collect(Collectors.toList());
						orderMemberDto.setChildren(subdetaillist);
					}
					list.setBeanList(listf);
					//beanList=listf.stream().filter(a->a.getChildren().)
				}
				 
				
				request.setAttribute("orders", list.getBeanList());
		        request.setAttribute("totalcount",list.getTr());
				request.setAttribute("pageindex",list.getPc());
				request.setAttribute("comment", null);
				view.setViewName("/template/pc/memberCenter/DdZx/CommentList");
				return view;

			}

		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			item.setDesc("查询评价订单异常：" + e.getMessage());
			LogHandle.error(LogType.pc,
					MessageFormat.format("查询评价订单异常! 异常信息:{0}", e.toString()),
					"order/commentorder");
			view.setViewName("redirect:/404.html");
			return view;
		}
	}
	
	/**
	 * 返回某个订单的评价列表
	 * 
	 * @param buyerid
	 * @param status
	 * @return
	 * 
	 */
	@RequestMapping(value = "/byorderidcomment", produces = "text/html;charset=UTF-8")
	public ModelAndView byorderidcomment(String orderid,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		ModelAndView view = new ModelAndView();
		try {
			if(StringUtilsEX.IsNullOrWhiteSpace(orderid))
			{
				view.setViewName("/template/error/index.html");
				return view;
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				int buyerId = sessionUser.getUserId();
				int orderids = Integer.parseInt(orderid);
				List<OrderMemberDto>  list = orderService.byorderidcomment(buyerId, orderids);
				request.setAttribute("count",null);
				request.setAttribute("orders", list);
				request.setAttribute("comment", null);
				view.setViewName("/template/pc/memberCenter/DdZx/CommentList");
				return view;

			}

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询评价订单异常：" + e.getMessage());
			LogHandle.error(LogType.pc,
					MessageFormat.format("查询评价订单异常! 异常信息:{0}", e.toString()),
					"order/commentorder");
			view.setViewName("redirect:/404.html");
			return view;
		}
	}
	/**
	 * 评价列表
	 * 
	 * @param buyerid
	 * @param status
	 * @return
	 * 
	 */
	@RequestMapping(value = "/commentlist", produces = "text/html;charset=UTF-8")
	public ModelAndView commentlist(String orderid,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		ModelAndView view = new ModelAndView();
		try {
			if(StringUtilsEX.IsNullOrWhiteSpace(orderid))
			{
				view.setViewName("/template/error/index.html");
				return view;
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				int buyerId = sessionUser.getUserId();
				int orderids = Integer.parseInt(orderid);
				List<OrderMemberDto>  list = orderService.byorderidcomment(buyerId, orderids);
				request.setAttribute("count",null);
				request.setAttribute("orders", list);
				Comment comment = commentService.selectByOrderId(orderids);
				request.setAttribute("comment", comment);
				view.setViewName("/template/pc/memberCenter/DdZx/CommentList");
				return view;

			}

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询评价订单异常：" + e.getMessage());
			LogHandle.error(LogType.pc,
					MessageFormat.format("查询评价订单异常! 异常信息:{0}", e.toString()),
					"order/commentorder");
			view.setViewName("redirect:/404.html");
			return view;
		}
	}
	/**
	 * 查询收藏的店铺或商品
	 * @param token
	 * @param spuId
	 * @return
	 */
	@RequestMapping(value = "/selectCollect", produces = "text/html;charset=UTF-8")
	public @ResponseBody BaseResult selectCollect(String type,String ch,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(type)) {
				item.setCode(-102);
				item.setDesc("收藏类型(type)不能为空！");
				return item;
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item;
			}
			int collectType=StringUtilsEX.ToInt(type);
			if(collectType!=UserCollectTypeEnum.商品.getValue()&&collectType!=UserCollectTypeEnum.店铺.getValue()){
				item.setCode(-104);
				item.setDesc("查询类型有误，0表示商品，1表示店铺");
			}
			SessionUser sessionUser=new SessionUser();
			sessionUser=SessionUtil.getSessionUser(request);
			if(sessionUser.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆！");
			}else{
				int buyerId=sessionUser.getUserId();
				List<Usercollect> list=usercollectService.select(buyerId, collectType);
				if(list!=null&&list.size()<1){
					item.setCode(0);
					
				}else{
					if(collectType==UserCollectTypeEnum.商品.getValue()){
						List<Spu> spulist=new ArrayList<Spu>();
						for (int i = 0; i < list.size(); i++) {
							Spu spu=spuService.queryById(list.get(i).getSpuid());
							if(spu!=null){
								spulist.add(spu);
							}
						}
						item.setCode(0);
						item.setDesc("查询成功");
						item.setData(spulist);
					}else{
						List<Shop> shoplist=new ArrayList<Shop>();
						for (int i = 0; i <list.size(); i++) {
							Shop shop=shopService.queryById(list.get(i).getShopid());
							if(shop!=null){
								shoplist.add(shop);
							}
						}
						item.setCode(0);
						item.setDesc("查询成功");
						item.setData(shoplist);
					}	
				}
				
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询收藏的店铺或商品：" + e.getMessage());
			LogHandle.error(LogType.pc, MessageFormat.format("查询收藏的店铺或商品! 异常信息:{0}",
					e.toString()), "concern/selectCollect");
		}
		
		return item;
	}
	
	private void initFoot(HttpServletRequest request){		
		List<SnewsClass> newscone =new ArrayList<SnewsClass>();
		List<SnewsClass> newsctwo =new ArrayList<SnewsClass>();
		List<SnewsClass> newscwthree =new ArrayList<SnewsClass>();
		List<SnewsClass> newscfour =new ArrayList<SnewsClass>();
		List<SnewsClass> newscfive =new ArrayList<SnewsClass>();
		try {
			List<SnewsClass> clist=snewsclassMapper.selectByParentId(0,2);
				for (int i = 0; i < clist.size(); i++) {
					if(clist.get(i).getLevel()==1){
						List<SnewsClass> newscone1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newscone.add(clist.get(i));
						for (int j = 0; j < newscone1.size(); j++) {
							newscone.add(newscone1.get(j));	
						}
					}
					if(clist.get(i).getLevel()==2){
						List<SnewsClass> newsctwo1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newsctwo.add(clist.get(i));
						for (int j = 0; j < newsctwo1.size(); j++) {
							newsctwo.add(newsctwo1.get(j));	
						}
					}
					if(clist.get(i).getLevel()==3){
						List<SnewsClass> newscwthree1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newscwthree.add(clist.get(i));
						for (int j = 0; j < newscwthree1.size(); j++) {
							newscwthree.add(newscwthree1.get(j));	
						}
					}
					if(clist.get(i).getLevel()==4){
						List<SnewsClass> newscfour1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newscfour.add(clist.get(i));
						for (int j = 0; j < newscfour1.size(); j++) {
							newscfour.add(newscfour1.get(j));	
						}
					}
					if(clist.get(i).getLevel()==5){
						List<SnewsClass> newscfive1=snewsclassMapper.selectByParentId(clist.get(i).getId(),2);
						newscfive.add(clist.get(i));
						for (int j = 0; j < newscfive1.size(); j++) {
							newscfive.add(newscfive1.get(j));	
						}
					}
				}
		} catch (Exception e) {
			LogHandle.error(LogType.pc, "获取首页内容错误：{0}", e, "/foot");
		}
		request.setAttribute("newscone", newscone==null?new ArrayList<SnewsClass>():newscone);
		request.setAttribute("newsctwo", newsctwo==null?new ArrayList<SnewsClass>():newsctwo);
		request.setAttribute("newscwthree",newscwthree==null?new ArrayList<SnewsClass>():newscwthree);
		request.setAttribute("newscfour",newscfour==null?new ArrayList<SnewsClass>():newscfour);
		request.setAttribute("newscfive",newscfive==null?new ArrayList<SnewsClass>():newscfive);
	}
	
	
	
	@RequestMapping(value = "/comment.html", produces = "text/html;charset=UTF-8")
	public ModelAndView comment(String orderid,Model model) {
		ReusltItem item = new ReusltItem();
		ModelAndView view = new ModelAndView();
		try {			
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
				view.setViewName("redirect:/member/user/showlogin");
				return view;
			} else {
				
				Orders orders = orderService.queryByID(StringUtilsEX.ToInt(orderid));
				if (orders != null) {
					Shop shop =shopService.queryById(orders.getShopid());
					OrdersDto orderDto = new OrdersDto();
					orderDto.setShopname(shop.getName());
					orderDto.setShopimgurl(shop.getImgurl());
					//orderDto.setShopid(shop.getId());		
					orderDto.setCode(orders.getCode());
					orderDto.setId(StringUtilsEX.ToInt(orderid));
					//orderDto.setSellerid(orders.getSellerid());
					Double acutalpay = StringUtilsEX.ToDoubleNull(orders.getActualpay().toString());
					orderDto.setActualPay(acutalpay);
					orderDto.setPrice(orders.getPrice());
					List<OrderdetailDto> orderdetails = orderdetailService.selectByOrderid(StringUtilsEX.ToInt(orderid));
					orderDto.setChildren(orderdetails);
					orderDto.setCount(orderdetails.size());
					model.addAttribute("products",orderDto);
					model.addAttribute("orderid",orderid);
					model.addAttribute("shopid", orders.getShopid());
				}
				/*model.addAttribute("orderid", orderid);
				List<OrderdetailDto> list=orderdetailService.selectByOrderid(StringUtilsEX.ToInt(orderid));
				Orders order=orderService.queryById(StringUtilsEX.ToInt(orderid));
				model.addAttribute("shopid", order.getShopid());
				model.addAttribute("orderlist", list);*/
				view.setViewName("/template/pc/memberCenter/DdZx/comment");
				return view;
			
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询订单异常：" + e.getMessage());
			LogHandle.error(LogType.pc, "查询订单异常! 异常信息:{0}", e,
					"order/comment");
			view.setViewName("redirect:/404.html");
			return view;
		}

	}
	
}
