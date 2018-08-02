package com.music.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class HandlerException implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		MusicException musicException = null;
		if (ex instanceof MusicException) {
			musicException = (MusicException) ex;
		} else {
			musicException = new MusicException("未知错误");
		}
		String message = musicException.getMessage();
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", message);
		System.out.println(message);
		mav.setViewName("error");// error是逻辑视图名，视图解析器会将其解析为真正的物理视图error.jsp
		return mav;
	}

}
