package com.yinlian.view.wap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/wap/user")
public class WapLoginViewController {
	 /**
     * 显示登录
     */
    @RequestMapping("/showlogin")
    public String login(){
    	//return "redirect:/wap/404.html";
    	return "/template/wap/userinfo/login";
    }
    
    /**
     * 显示登录
     */
    @RequestMapping("/showregister")
    public String showregister(){
    	//return "redirect:/wap/404.html";
    	return "/template/wap/userinfo/register";
    }
    
    
    /**
     * 显示注册手机号
     */
    @RequestMapping("/showRegisterPhone")
    public String showRegisterPhone(){
    	//return "redirect:/wap/404.html";
    	return "/template/wap/userinfo/registerPhone";
    }
    
    

    /**
     * 显示注册验证码
     */
    @RequestMapping("/showRegisterSms")
    public String showRegisterSms(String phone,Model model){
    	model.addAttribute("phone", phone);
    	//return "redirect:/wap/404.html";
    	return "/template/wap/userinfo/registerSms";
    }
    
    /**
     * 显示注册密码
     */
    @RequestMapping("/showRegisterPwd")
    public String showRegisterPwd(String phone,String sms,Model model){
    	model.addAttribute("phone", phone);
    	model.addAttribute("sms", sms);
    	//return "redirect:/wap/404.html";
    	return "/template/wap/userinfo/showPass";
    }
    
    /**
     * 显示找回密码
     */
    @RequestMapping("/showFindPwd")
    public String showFindPwd(){
    	//return "redirect:/wap/404.html";
    	return "/template/wap/userinfo/findPwd";
    }
    
    /**
     * 显示修改密码
     */
    @RequestMapping("/showupdpwd")
    public String showupdpwd(){
    	//return "redirect:/wap/404.html";
    	return "/template/wap/userinfo/updpwd";
    }
		
}
