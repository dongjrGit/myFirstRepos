package com.yinlian.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/platform/sh")
public class ShViewController {
    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(ShViewController.class);
  
    /**
     * 显示
     * @return
     */
    @RequestMapping("/ReturnTrade")
    public String showAdvertImg(String id, HttpServletRequest request) {

        return "platform/sh/ReturnTrade";
    }
    
    @RequestMapping("/editNote")
    public String editNote(String id,Model model) {
    	model.addAttribute("id",id);
        return "platform/sh/AddNote";
    }
}
