/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.view.controller;

import java.text.MessageFormat;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.MenuService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.JsonUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yl.soft.log.LogHandle;

/**
 * Handler User Message.
 * @author Administrator
 * @version $Id: UserController.java, v 0.1 2016年2月24日 下午6:07:53 Administrator Exp $
 */
@Controller
public class UserController {
    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AccountsService     accountsService;
    @Autowired
    private MenuService         menuService;
    @Autowired
    private ShopService         shopService;
    /**
     * 显示平台登录页面
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/platform/login")
    public String showPlatformLogin() {

        return "platform/user/login";
    }

    /**
     * 平台的单点登录
     * @param token
     * @param request
     * @return
     */
    @RequestMapping("/platform/simpleLogin")
    public String simpleLogin(String token, HttpServletResponse  response,HttpServletRequest request,Model model) {
    	ReusltItem item = new ReusltItem();
    	try {
    		//通过token 得到username
    		//TODO 通过token 得到username
        	String username="admin";
        	Integer[] array={UserTypeEnum.SupAdmin.getValue(),UserTypeEnum.Admin.getValue()};
        	Object[] rls=accountsService.querybyName(username,array);
        	String userInfo = "";
        	switch (Integer.parseInt(rls[0].toString())) {
            case 0:
                userInfo = JsonUtil.getJsonStringFromObject(rls[1]);
                break;
            default:
                item.setCode(-104);
                item.setDesc("登录失败！");
                request.setAttribute("item", item);
                return "redirect:/platform/login";
           }
        	String token1=UUID.randomUUID().toString();
            SessionUser sessionUser= (SessionUser)rls[1];
            SessionState.SetSessionUser(token, sessionUser);
            Cookie cookie=new Cookie(ConstanValue.TOKEN_KEY, token1);
            response.addCookie(cookie);
          
            
		} catch (Exception e) {
			 LogHandle.error(LogType.Login,
	            		MessageFormat.format("平台登录错误，详情：{0}", e.getMessage()));
			item.setCode(-900);
			item.setDesc("异常：" + e.getMessage());
			request.setAttribute("item", item);
			return "redirect:/platform/login";
		}
    	return "redirect:/platform/index";
    }
}
