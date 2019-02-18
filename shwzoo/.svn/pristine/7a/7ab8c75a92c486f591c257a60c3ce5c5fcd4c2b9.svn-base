/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 专题的后台页面控制类
 * TopicViewController.java
 * @author Liang.ma.s
 * @version $Id: TopicViewController.java, v 0.1 2016年4月7日 下午4:03:22 Administrator Exp $
 */
@Controller
@RequestMapping("/platform/img")
public class ImgViewController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ImgViewController.class);

    @RequestMapping("/ImageShow")
    public String showProList(String imgUrl, Model model) {

        model.addAttribute("imgUrl", imgUrl);
       
        return "platform/img/imageShow";
    }

   
}
