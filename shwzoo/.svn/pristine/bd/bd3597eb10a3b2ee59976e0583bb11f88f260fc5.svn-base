/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.seller.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.util.SessionUtil;

/**
 * 站内信的显示控制类
 * MessageViewController.java
 * @author Administrator
 * @version $Id: MessageViewController.java, v 0.1 2016年3月21日 下午7:41:07 Administrator Exp $
 */
@Controller
@RequestMapping("/seller/message")
public class MessageViewController {

    /**
     * 日子输出类
     */
    private static final Logger logger = LoggerFactory.getLogger(MessageViewController.class);
    @Autowired
    private ShopService         shopService;

    /**
     * 显示站内信的页面
     * 
     * @return
     */
    @RequestMapping("/showUserMessage")
    public String showUserMessage(Integer userid, HttpServletRequest request) {
        try {
            userid = SessionUtil.getSessionUserId(request);
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("userid", userid);
        return "seller/zhgl/userMessages";
    }

    /**
     * 显示站内信的信息页面
     * 
     * @return
     */
    @RequestMapping("/showMessageInfo")
    public String showMessageInfo() {

        return "seller/zhgl/messagesInfo";
    }

}
