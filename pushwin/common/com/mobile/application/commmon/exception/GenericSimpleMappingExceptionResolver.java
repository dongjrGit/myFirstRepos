package com.mobile.application.commmon.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.mobile.application.vo.CommonVO;

public class GenericSimpleMappingExceptionResolver extends
		SimpleMappingExceptionResolver {
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String viewName = determineViewName(ex, request);
		StringPrintWriter strintPrintWriter = new StringPrintWriter();
		ex.printStackTrace(strintPrintWriter);
		request.setAttribute("errorInfo", strintPrintWriter);
			// 如果不是异步请求
			if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				if(StringUtils.isNotBlank(viewName)) {
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				return getModelAndView(viewName, ex, request);
			} else {// JSON格式返回
				return getModelAndView("error/500", ex, request);
			}
		} else {
			try {
				CommonVO commonVO = new CommonVO(false, ex.getMessage());
				JSONObject commonJson = JSONObject.fromObject(commonVO);
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(commonJson);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
