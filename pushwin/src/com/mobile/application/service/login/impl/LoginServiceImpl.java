package com.mobile.application.service.login.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.tools.IpUtil;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.ILoginDao;
import com.mobile.application.dao.IUserDao;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.service.login.ILoginService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.login.UserMenuVO;
import com.mobile.application.vo.session.IpInfoVO;
import com.mobile.application.vo.session.SessionVO;

@Service
public class LoginServiceImpl implements ILoginService {
	@Autowired
	private ILoginDao loginDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ServletContext servletContext;
	
	@Override
	@Transactional
	public List<UserMenuVO> queryUserMenu(HttpSession session){
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		TBasisUser user = loginDao.get(TBasisUser.class, sessionVO.getUserId());
		return loginDao.qryUserMenu(user);
	}

	@Override
	@Transactional
	public CommonVO queryUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, String userCode, String password) throws IllegalAccessException, InvocationTargetException {
		
		CommonVO commonVO = new CommonVO(true, "登陆成功");
		
		//根据员工号查询用户信息
		List<TBasisUser> tBasisUsers = loginDao.qryUser(userCode);
		
		if(null == tBasisUsers || tBasisUsers.size() == 0){
			commonVO = new CommonVO(false, userCode+" 用户不存在");
			return commonVO;
		}
		
		TBasisUser tBasisUser = tBasisUsers.get(0);
		//配置文件中读取最大登陆失败次数
		int loginErrorTimes = Integer.parseInt(SpringProperty.props.getProperty("LoginErrorTimes"));
		//当前密码错误次数

		int nowErrorTimes = 0;
		if(tBasisUser.getNoLogin()==null){
			nowErrorTimes = 0;
		}else{
			nowErrorTimes = Integer.parseInt(tBasisUser.getNoLogin());
		}
		
		if(nowErrorTimes >= loginErrorTimes){
			commonVO = new CommonVO(false, userCode+" 账号已锁定，请联系管理员解除锁定。");
			return commonVO;
		}
		
		if(!password.equals(tBasisUser.getUserPwd())) {
			//当前错误次数+1
			nowErrorTimes++ ;
			if(nowErrorTimes >= loginErrorTimes){
				commonVO = new CommonVO(false, userCode+" 账号已锁定，请联系管理员解除锁定。");
				tBasisUser.setNoLogin(String.valueOf(nowErrorTimes));
//				    loginDao.update(tBasisUser);
			} else {
				commonVO = new CommonVO(false,  userCode+" 密码不正确，还有" + (loginErrorTimes - nowErrorTimes) + "次机会。");
				tBasisUser.setNoLogin(String.valueOf(nowErrorTimes));
//				    
			}
//			loginDao.update(tBasisUser);
			return commonVO;
		}
		
		//查看是否被禁用
		if("true".equals(tBasisUser.getIsClose())){
			return new CommonVO(false, "用户已被禁用，登录失败");
		}
		
		String userRoleCount = userDao.userBackRoleCount(tBasisUser.getUserId());
		if("0".equals(userRoleCount)&&!"1".equals(tBasisUser.getIsAdmin())){
			return new CommonVO(false, "请给 "+userCode+" 用户分配角色");
		}
		
		
		//设置用户登录session
		SessionVO sessionVO = new SessionVO();
		BeanUtils.copyProperties(sessionVO, tBasisUser);
		TBasisOrg tBasisOrg = tBasisUser.getTBasisOrg();
		if(null != tBasisOrg){
			sessionVO.setOrgCode(tBasisOrg.getOrgCode());
			sessionVO.setOrgId(tBasisOrg.getOrgId());
			sessionVO.setOrgName(tBasisOrg.getOrgName());
		}
		String ip = new String(IpUtil.getIpAddr(request));
		IpInfoVO ipInfoVO = new IpInfoVO(ip, new Date());
		sessionVO.setIpInfoVO(ipInfoVO);
    	Map<String, IpInfoVO> onlineUser = (Map<String, IpInfoVO>) servletContext.getAttribute("onlineUser");
		onlineUser.put(userCode, ipInfoVO);
		
		Set<String> actionUrls = new HashSet<String>();
		Set<String> menuIds = new HashSet<String>();
		List<UserMenuVO> userMenuVOs = loginDao.qryUserMenu(tBasisUser);
		for (UserMenuVO userMenuVO : userMenuVOs) {
			actionUrls.add(userMenuVO.getUrl());
			menuIds.add(userMenuVO.getId());
		}
		actionUrls.addAll(loginDao.qryUserAction(menuIds));
		sessionVO.setAuthorizedUrl(actionUrls);
		
		session.setAttribute("sessionVO", sessionVO);
		
		if("".equals(tBasisUser.getUpPwdTime())||tBasisUser.getUpPwdTime()==null){
			
			return new CommonVO(true,"isfirst" );
		}else{
			int updpwdTimes = Integer.parseInt(SpringProperty.props.getProperty("updpwdTimes"));
			Date date = new Date();
			int t = DateUtil.diffDate(date,DateUtil.getDateTime(tBasisUser.getUpPwdTime()));
			if(t>updpwdTimes&&!"1".equals(sessionVO.getIsAdmin())){
				return new CommonVO(true,"isfirst" );
			}
		}
		tBasisUser.setNoLogin("0");
		loginDao.update(tBasisUser);
		return commonVO;
	}

	@Override
	@Transactional
	public void deletesession(HttpSession session, HttpServletRequest request) {
		if (session != null) {
			session.invalidate();
		}
	}

}
