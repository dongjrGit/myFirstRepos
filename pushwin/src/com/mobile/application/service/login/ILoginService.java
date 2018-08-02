package com.mobile.application.service.login;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.login.LoginUserVO;
import com.mobile.application.vo.login.UserMenuVO;

public interface ILoginService {

	List<UserMenuVO> queryUserMenu(HttpSession session);

	CommonVO queryUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, String userCode, String password) throws IllegalAccessException, InvocationTargetException;
	/**
	 * Description : 删除session信息
	 * @author 
	 * @version 1.01
	 * @see N/A
	 * @param session 
     * @param request 
	 * @exception 
	 */
	void deletesession(HttpSession session, HttpServletRequest request);
}
