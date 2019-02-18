/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.view.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.po.Navigation;
import com.yinlian.wssc.web.service.NavigationService;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * NavigationController.java
 * @author sssssssl.m
 * @version $Id: NavigationController.java, v 0.1 2016年4月15日 上午9:56:05 Administrator Exp $
 */
@Controller
@RequestMapping("/platform/navigation")
public class NavigationViewController {

    @Autowired
    private NavigationService navigationService;

    /**
     * 显示导航的管理页面
     * 
     * @return
     */
    @RequestMapping("/list")
    public String list() {

        return "platform/navigation/navigation_list";
    }

    /**
     * 显示导航编辑页面
     * 
     * @param id
     * @return
     */
    @RequestMapping("/showNavigationEdit")
    public String showNavigationEdit(String id, Model model) {
        if (StringUtils.isNotNull(id)) {
            try {
                Navigation record = navigationService.queryById(StringUtilsEX.ToInt(id));
                model.addAttribute("navigation", record);
            } catch (Exception e) {
                LogHandle.error(LogType.Error,
                    MessageFormat.format("显示导航编辑页面! 异常信息:{0}", e.toString()),
                    "navigation/showNavigationEdit");
            }
        }
        return "platform/navigation/navigation_edit";
    }
}
