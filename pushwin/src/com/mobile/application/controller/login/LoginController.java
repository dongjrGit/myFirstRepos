package com.mobile.application.controller.login;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.service.login.ILoginService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.login.UserMenuVO;


@Controller
@RequestMapping("/system")
public class LoginController {
	

	protected final transient Log log = LogFactory
			.getLog(LoginController.class);

	@Autowired
	private ILoginService loginServiceImpl;

	
	@RequestMapping("/doc")
	@ResponseBody
	public Map<String, String> qryUser(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		
		// 输出文档路径及名称
		FileOutputStream fileOut = new FileOutputStream("d:/srcb/a.jpg");
		try {
			String a = (String) request.getAttribute("fileName");
//			while(em.hasMoreElements()){
//				Object a = em.nextElement();
				System.out.println(a);
//			}
			InputStream is = request.getInputStream();
			byte[] fileNameByte = new byte[20];
			is.read(fileNameByte);
			String fileName = new String(fileNameByte);
			System.out.println(fileName.trim());
			byte[] buf = new byte[512];
			for (int len = is.read(buf); len != -1; len = is.read(buf)) {
				fileOut.write(buf, 0, len);
			}
			fileOut.flush();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} finally {
			if(null != fileOut)
				fileOut.close();
		}
		response.setCharacterEncoding("utf-8");
		request.setAttribute("name", "Jerry");
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Jerry");
		model.addAttribute("map", map);
//		loginDaoImpl.qryUser();
		return map;
	}
	
	
	/**
	 * 跳转到登陆页面
	 * @param session
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpSession session, HttpServletRequest request) {
		return "login/login";
	}
	
	/**
	 * 登录
	 * @param userCode 员工号
	 * @param password 密码
	 * @param request
	 * @param response
	 * @param session
	 * @return 返回主页面
	 * @throws IOException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ServletException 
	 */
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public ModelAndView login(String userCode, String password, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, IllegalAccessException, InvocationTargetException, ServletException{
//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
//        ServletContext servletContext = webApplicationContext.getServletContext();  
		if(StringUtils.isBlank(userCode)){
			return new ModelAndView("login/login"); 
		}
		CommonVO commonVO = loginServiceImpl.queryUser(request, response, session, userCode, password);
        if(!commonVO.isSuccess()){
			Map<String, String> map = new HashMap<String, String>();
			map.put("msg", commonVO.getMsg());
			map.put("userCode", userCode);
			return new ModelAndView("login/login", map);
        }
        if(commonVO.getMsg().equals("isfirst")){
        	Map<String, String> map = new HashMap<String, String>();
			map.put("msg", commonVO.getMsg());
        	return new ModelAndView("login/pwd", null);
        }
		return new ModelAndView("login/index", null);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/menu")
	@ResponseBody
	public List<UserMenuVO> queryUserMenu(HttpSession session, HttpServletRequest request){
		return loginServiceImpl.queryUserMenu(session);
	}
	
	/**
	 * 退出系统
	 * 
	 * @param session
	 * @return
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public CommonVO logout(HttpSession session, HttpServletRequest request) throws BusinessException {
//		String urls = request.getParameter("jr");
		CommonVO commonVO = new CommonVO();
		if (session != null) {
			session.invalidate();
		}
//		commonVO.setRows(urls);
		commonVO.setSuccess(true);
		return commonVO;
	}
	
	/**
	 * 退出系统
	 * 
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public CommonVO upload(HttpSession session, HttpServletRequest request) throws IOException {
		// 复制文件到目标地
		InputStream ins = request.getInputStream();
		FileOutputStream outs = new FileOutputStream(new File("D:/fileMD5Check/upload.zip"));
		byte[] buffer = new byte[1024 * 512];
		int length;
		while ((length = ins.read(buffer)) != -1) {
			outs.write(buffer, 0, length);
		}
		ins.close();
		outs.flush();
		outs.close();
		return null;
	}
	/**
	 * Description : 退出登录
	 * @author 
	 * @version 1.01
	 * @see N/A
	 * @param session 
     * @param request 
	 * @return  登录页面
	 * @exception 
	 */
	@RequestMapping("/outLogin")
	public String outLogin(HttpSession session, HttpServletRequest request) {
		loginServiceImpl.deletesession(session,request);
		return "login/login";
	}
	
	
}
