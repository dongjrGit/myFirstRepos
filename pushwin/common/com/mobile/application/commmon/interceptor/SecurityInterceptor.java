package com.mobile.application.commmon.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.commmon.tools.IpUtil;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.entity.TBasisLogDetail;
import com.mobile.application.entity.TBasisLogType;
import com.mobile.application.service.log.ILogService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.IpInfoVO;
import com.mobile.application.vo.session.SessionVO;

public class SecurityInterceptor implements HandlerInterceptor {
	
	@Autowired
	private ServletContext servletContext;
	@Autowired
	ILogService logService;
	
	//private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);
	
	private List<String> excludeUrls;// 不需要拦截的资源
	
	// 在DispatcherServlet完全处理完请求后被调用
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj2, Exception e)
			throws Exception {
		response.setHeader("Set-Cookie", "cookiename=value;Path=/;Domain=domainvalue;Max-Age=seconds;HTTPOnly");
		response.setHeader("Server"," ");
		//request.containsHeader("log-token");
		String requestUrl = (String) request.getAttribute("requestUrl");
		Map<String, TBasisLogType> logTypeMap = (Map<String, TBasisLogType>) servletContext.getAttribute("logMap");
		if(logTypeMap.containsKey(requestUrl)){
			TBasisLogType tBasisLogType = logTypeMap.get(requestUrl);
			SessionVO sessionVO = (SessionVO) request.getSession().getAttribute("sessionVO");
			String ip = new String(IpUtil.getIpAddr(request));
			String log_description = (String) request.getAttribute("log-token");
			TBasisLogDetail tBasisLogDetail = new TBasisLogDetail(tBasisLogType, StringUtils.isNotBlank(log_description) ? log_description : tBasisLogType.getDescription(), sessionVO.getUserCode(), sessionVO.getUserName(), ip, sessionVO.getOrgCode(), sessionVO.getOrgName(), DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			logService.saveLogDetail(tBasisLogDetail);
		}
	}

	// 在业务处理器处理完请求后，但是DispatcherServlet向客户端返回请求前被调用
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	// 业务处理器处理请求之前被调用
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		response.setHeader("Set-Cookie", "cookiename=value;Path=/;Domain=domainvalue;Max-Age=seconds;HTTPOnly");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		request.setAttribute("requestUrl", url);
		
		if(excludeUrls.contains(url)){
			return true;
		}
		
		SessionVO sessionVO = (SessionVO) request.getSession().getAttribute("sessionVO");
		
/*		Set<String> authorizedUrls = sessionVO.getAuthorizedUrl();
		if(!authorizedUrls.contains(url) && sessionVO.getIsAdmin()==null){
			try {
				CommonVO commonVO = new CommonVO(false, "没有权限的操作");
				JSONObject commonJson = JSONObject.fromObject(commonVO);
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				PrintWriter pw = response.getWriter();
				pw.print(commonJson);
				pw.close();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		
		if (null == sessionVO || StringUtils.isBlank(sessionVO.getUserId())) {// 如果没有登录或登录超时
			if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				request.setAttribute("msg", "已超时，请重新登录！");
				request.getRequestDispatcher("/views/error/noSession.jsp").forward(request, response);
				return false;
			} else {
				response.setHeader("sessionstatus", "timeout");
				try {
					CommonVO commonVO = new CommonVO(false, "系统登录超时，请重新登录");
					JSONObject commonJson = JSONObject.fromObject(commonVO);
					response.setContentType("text/html");
					response.setCharacterEncoding("UTF-8");
					PrintWriter pw = response.getWriter();
					pw.print(commonJson);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		} else {
			@SuppressWarnings("unchecked")
			Map<String, IpInfoVO> onlineUser = (Map<String, IpInfoVO>) servletContext.getAttribute("onlineUser");
			IpInfoVO ipInfoVO = onlineUser.get(sessionVO.getUserCode());
			if(!IpUtil.getIpAddr(request).equals(ipInfoVO.getIp()) || sessionVO.getIpInfoVO().getLogonDate().before(ipInfoVO.getLogonDate()))
			if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				request.setAttribute("msg", "您的帐号已在其他地点登录，请注意帐号安全。<br/>登录IP为：" + ipInfoVO.getIp());
				request.getRequestDispatcher("/views/error/noSession.jsp").forward(request, response);
				return false;
			} else {
				response.setHeader("sessionstatus", "timeout");
				try {
					CommonVO commonVO = new CommonVO(false, "您的帐号已在其他地点登录，请注意帐号安全。登录IP为：" + ipInfoVO.getIp());
					JSONObject commonJson = JSONObject.fromObject(commonVO);
					response.setContentType("text/html");
					response.setCharacterEncoding("UTF-8");
					PrintWriter pw = response.getWriter();
					pw.print(commonJson);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		}
		return true;
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	public static void main(String[] args) {
		Date date = new Date();
		Date date1 = date;
		System.out.println(date.before(date1));
	}
}
