/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.yinlian.Extended.LogType;
import com.yl.soft.log.LogHandle;

/**
 * This is Global Exception Resolver handler All Exception.
 * @author Administrator
 * @version $Id: GlobalExceptionResolver.java, v 0.1 2016年2月23日 下午3:03:01 Administrator Exp $
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    /** 
     * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception exception) {
        GlobalException globalException = null;
        if (exception instanceof GlobalException) {
            globalException = (GlobalException) exception;
        } else {
        	if(exception instanceof MaxUploadSizeExceededException){
        		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
            	MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(request);
                String flag = multiReq.getParameter("zoo-upload");//获取参数中的token
                if("true".equals(flag)){
                	globalException = new GlobalException("The ERROR is MaxUploadSizeExceededException!");
                    try {
                    	response.setContentType("text/html");
        				response.getWriter().print("{\"code\" : 700}");
        				response.getWriter().flush();
        				response.getWriter().close();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
                }else{
                	globalException = new GlobalException("The ERROR is Unknown Mistake!");
                }
        	}else{
        		globalException = new GlobalException("The ERROR is Unknown Mistake!");
        	}
        } 
        String message = globalException.getMessage();
        if(exception!=null)
        {
        	LogHandle.error(LogType.global, "未知异常", exception,
		"global/exception");
        	message=exception.getMessage();
        }
      
        ModelAndView view = new ModelAndView();
        // The information is added to the view
        view.addObject("message", message);
        // Back to View name.
        view.setViewName("error");
        return view;
    }

}
