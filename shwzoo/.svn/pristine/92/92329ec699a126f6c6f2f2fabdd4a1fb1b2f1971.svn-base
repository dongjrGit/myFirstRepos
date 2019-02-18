package com.yinlian.wssc.web.util;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;

import org.springframework.web.servlet.ModelAndView;

public class ErrorRedirect {
	private static  ErrorRedirect  _instance;
	public static ErrorRedirect  getInstance() {
		if(_instance==null)
			_instance=new ErrorRedirect();
		return _instance;
	}
	private   ErrorRedirect() {
		
	}
	private  boolean isoutStackTrace = false;

	private  String getmsg(Exception e) {
		String expMessage = "";
		try {
			if (isoutStackTrace) {
				ByteArrayOutputStream buf = new ByteArrayOutputStream();
				e.printStackTrace(new java.io.PrintWriter(buf, true));
				expMessage = URLEncoder.encode(buf.toString(), "UTF-8");
				buf.close();
			} else {
				expMessage = URLEncoder.encode(expMessage.toString(), "UTF-8");
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		return expMessage;
	}

	private  String encode(String msg) {
		try {
			return URLEncoder.encode(msg, "UTF-8");
		} catch (Exception e) {
			return msg;
		}
	}

	public  String wapRedirect() {
		return "redirect:/wap/error/index.html";
	}

	public  String wapRedirect(String msg) {
		return "redirect:/wap/error/index.html?msg=" +encode(msg);
	}
	public ModelAndView wapRedirectModel(String msg){
		return new ModelAndView("redirect:/wap/error/index.html?msg="+encode(msg));
	}
	public ModelAndView pcRedirectErrorModel(){
		return new ModelAndView("redirect:/404.html");
	}
	
	public ModelAndView wapRedirectErrorModel(){
		return  new ModelAndView("redirect:/wap/404.html");
	}

	public  String wapRedirect(int code) {
		return "redirect:/wap/error/index.html?code=" + code;
	}
	
	public String pcRedirectError(){
		return "redirect:/404.html";
	}
	
	public String wapRedirectError(){
		return "redirect:/wap/404.html";
	}

	public  String wapRedirect(int code, String msg) {
		return "redirect:/wap/error/index.html?code=" + code + "&msg=" +encode(msg);
	}

	public  String wapRedirect(Exception e) {
		return "redirect:/wap/error/index.html?msg=" + getmsg(e);
	}

	public  String wapRedirect(int code, Exception e) {
		return "redirect:/wap/error/index.html?code=" + code + "&msg=" + getmsg(e);
	}

	public  String pcRedirect() {
		return "redirect:/wap/error/index.html";
	}

	public  String pcRedirect(String msg) {

		return "redirect:/wap/error/index.html?msg=" +encode(msg);
	}

	public  String pcRedirect(int code) {
		return "redirect:/wap/error/index.html?code=" + code;
	}

	public  String pcRedirect(int code, String msg) {
		return "redirect:/wap/error/index.html?code=" + code + "&msg=" +encode(msg);
	}

	public  String pcRedirect(Exception e) {
		return "redirect:/wap/error/index.html?msg=" + getmsg(e);
	}

	public  String pcRedirect(int code, Exception e) {
		return "redirect:/wap/error/index.html?code=" + code + "&msg=" + getmsg(e);
	}

}
