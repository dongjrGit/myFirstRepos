package com.yinlian.view.wap.controller;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CartDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.CartService;
import com.yinlian.wssc.web.util.ErrorRedirect;
import com.yinlian.wssc.web.util.SessionState;
import com.yl.soft.log.LogHandle;


@Controller
@RequestMapping("/wap/shopcart")
public class ShopCartController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/cartlist.html")
	public String shopcart(HttpServletRequest request) {
		try {			
			SessionUser user = SessionState.GetCurrentUser();
			boolean islogin=false;
			if (user!=null&&user.getCode() == 0) {				
				int userid = user.getUserId();
				CartDto cartlist=new CartDto();
				cartlist=cartService.getByUser(userid,ActivityUsePlatformEnum.wap.getValue());
				request.setAttribute("datacart", cartlist);	
				islogin=true;
			}else{
				islogin=false;
			}
			request.setAttribute("islogin", islogin);	
		} catch (Exception e) {			
			LogHandle.error(LogType.wap,
					MessageFormat.format("获取购物车数据异常! 异常信息:{0}", e),
					"shopcart/cartlist");
			return ErrorRedirect.getInstance().wapRedirect("购物车错误");
		}
		//return ErrorRedirect.getInstance().wapRedirectError();
		return "/template/wap/shopcart/shopcart";
	}
}
