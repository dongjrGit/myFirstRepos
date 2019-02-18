/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.view.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.MessagesTypeEnum;
import com.yinlian.wssc.platform.vo.MemberVo;

/**
 * 平台的站内信显示层
 * MessagePlatformViewController.java
 * @author Administrator
 * @version $Id: MessagePlatformViewController.java, v 0.1 2016年3月22日 下午2:15:08 Administrator Exp $
 */
@Controller
@RequestMapping("/platform/message")
public class MessagePlatformViewController {

    private static final Logger logger = LoggerFactory
                                           .getLogger(MessagePlatformViewController.class);

    /**
     * 显示发送站内信的页面
     * 
     * @return
     */
    @RequestMapping("/showSendMessage")
    public String showSendMessage(Model model) {
    	List<MemberVo> list = new ArrayList<MemberVo>();		
		for (int i = 0; i < MessagesTypeEnum.values().length; i++) {
			MemberVo messagesVo = new MemberVo();
			messagesVo.setCode(MessagesTypeEnum.values()[i].getValue());
			messagesVo.setName(MessagesTypeEnum.values()[i].name());
			list.add(messagesVo);
		}
		model.addAttribute("messageslist", list);
        return "platform/message/sendMessage";
    }

    /**
     * 显示已发送的站内信页面
     * 
     * @return
     */
    @RequestMapping("/showSendedMessage")
    public String showSendedMessage() {
    	
        return "platform/message/sendedMessage";
    }

    /**
     * 显示发送站内信编辑的页面
     * 
     * @return
     */
    @RequestMapping("/showSendMesInfo")
    public String showSendMesInfo(Model model) {
    	List<MemberVo> list = new ArrayList<MemberVo>();		
		for (int i = 0; i < MessagesTypeEnum.values().length; i++) {
			MemberVo messagesVo = new MemberVo();
			messagesVo.setCode(MessagesTypeEnum.values()[i].getValue());
			messagesVo.setName(MessagesTypeEnum.values()[i].name());
			list.add(messagesVo);
		}
		model.addAttribute("messageslist", list);
        return "platform/message/sendMesInfo";
    }

    /**
     * 显示发送邮件的会员页面 
     * 
     * @return
     */
    @RequestMapping("/showSendEmail")
    public String showSendEmail() {

        return "platform/message/sendEmail";
    }

    /**
     * 显示发送邮件的编辑页面
     * 
     * @return
     */
    @RequestMapping("/showSendEmailAdd")
    public String showSendEmailAdd() {

        return "platform/message/sendEmail_Add";
    }

    /**
     * 显示 发送短信的会员列表页面
     * 
     * @return
     */
    @RequestMapping("/showSendMobileMessage")
    public String showSendMobileMessage() {

        return "platform/message/sendMobileMessage";
    }

    /**
     * 显示发送短息编辑的页面
     * 
     * @return
     */
    @RequestMapping("/showSendMobileMessageAdd")
    public String showSendMobileMessageAdd() {

        return "platform/message/sendMobileMessage_Add";
    }

    /**
     * 显示已发送的短息页面 
     * 
     * @return
     */
    @RequestMapping("/showSendMobileMessageComplete")
    public String showSendMobileMessageComplete() {

        return "platform/message/sendMobileMessage_Complete";
    }
}
