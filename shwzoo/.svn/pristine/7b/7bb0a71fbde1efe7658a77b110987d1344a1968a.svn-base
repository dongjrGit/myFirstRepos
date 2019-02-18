package com.yinlian.view.wap.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.OrderdetailDto;
import com.yinlian.wap.dto.OrdersDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.GroupBuyService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.SatisfactionService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/wap/comment")
public class WapCommentViewController {

	@Autowired
    private SatisfactionService satisfactionService;

    @Autowired
    private SpuService          spuService;

    @Autowired
    private CommentService      commentService;
    
    @Autowired
    private OrderService        orderService;
    
    @Autowired
    private OrderdetailService   orderdetailService;
    
    @Autowired
    private GroupBuyService      groupbuyservice;
    @Autowired
	private ShopService shopService;
	 /**
	 * 返回评价页面
	 */
	
	@RequestMapping(value = "/comment", produces = "text/html;charset=UTF-8")
	public ModelAndView comment(String orderid, String ch,HttpServletRequest request) {
		ch="3";
		ModelAndView view = new ModelAndView();
		try {
			
			if (StringUtilsEX.IsNullOrWhiteSpace(orderid)) {
				view.setViewName("/template/error/index.html");
				return view;
			
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				view.setViewName("/template/error/index.html");
				return view;
			
			}
			String token = CookieUtils.getTokenFromCookie(request);
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				view.setViewName("/template/wap/userinfo/login");
				return view;
			}else{

			Orders orders = orderService.queryByID(StringUtilsEX.ToInt(orderid));
			if (orders != null) {
				Shop shop =shopService.queryById(orders.getShopid());
				OrdersDto orderDto = new OrdersDto();
				orderDto.setShopname(shop.getName());
				orderDto.setShopimgurl(shop.getImgurl());
				orderDto.setShopid(shop.getId());		
				orderDto.setCode(orders.getCode());
				orderDto.setId(StringUtilsEX.ToInt(orderid));
				orderDto.setSellerid(orders.getSellerid());
				Double acutalpay = StringUtilsEX.ToDoubleNull(orders.getActualpay().toString());
				orderDto.setActualPay(acutalpay);
				orderDto.setPrice(orders.getPrice());
				List<OrderdetailDto> orderdetails = orderdetailService.selectByOrderid(StringUtilsEX.ToInt(orderid));
				orderDto.setChildren(orderdetails);
				orderDto.setCount(orderdetails.size());
				request.setAttribute("products",orderDto);
				request.setAttribute("orderid",orderid);
				request.setAttribute("shopid", orders.getShopid());
				view.setViewName("/template/wap/Comment/addcomment");
				return view;
				
			}
			view.setViewName("/template/error/index.html");
			return view;
			//return ErrorRedirect.getInstance().wapRedirectErrorModel();
			}
		} catch (Exception e) {
			LogHandle.debug(LogType.wap, MessageFormat.format("查询订单明细异常! 异常信息:{0}", e.toString()),
					"/wap/comment/comment");
			view.setViewName("/template/error/index.html");
			return view;
		}
		
		
	}
}

