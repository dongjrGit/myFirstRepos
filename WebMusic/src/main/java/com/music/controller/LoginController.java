package com.music.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.music.constants.Constants;
import com.music.entity.User;
import com.music.service.UserService;
import com.music.util.CommonVO;
import com.music.util.MD5Util;

@Controller
public class LoginController {
	private static Logger logger = (Logger) Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	/**
	 * 登录
	 * @param reqContent
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public CommonVO login(@RequestParam String reqContent,HttpServletResponse response){
		logger.info("LoginController-->>login()"+"   reqContent:"+reqContent);
		JSONObject reqJson = JSONObject.parseObject(reqContent);
		String userName = reqJson.getString("userName");
		String password = reqJson.getString("password");
		User loginUser = new User();
		loginUser.setUserName(userName);
		loginUser.setPassword(password);
		
		User u = userService.login(loginUser);
		if(null!=u){
			logger.info("登陆成功");
			return new CommonVO("1","登录成功");
		}
		logger.info("登陆失败");
		return new CommonVO("0","登录失败");
	}
	/**
	 * 注册用户
	 * @param reqContent
	 * @param session
	 * @return
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public CommonVO addUser(String reqContent,HttpSession session) {
		JSONObject reqJson = JSONObject.parseObject(reqContent);
		String userName = reqJson.getString("userName");
		String nickName = reqJson.getString("nickName");
		String password = MD5Util.MD5Encode(reqJson.getString("password"));
		User user = new User();
		user.setUserName(userName);
		user.setNickname(nickName);
		user.setPassword(password);
		if (userName.matches("^[a-zA-Z][a-zA-Z0-9_]{6,12}$")) {
			if (nickName.matches("^[\u4e00-\u9fa5a-zA-Z0-9_]{4,15}$")) {
				if (password.matches("^[a-z0-9_-]{6,18}$")) {
					User exsitUser = userService.getByUserName(userName);
					if (null == exsitUser) {
						User nickUser = userService.findByNickName(nickName);
						if (null == nickUser) {
							int result = userService.addUser(user);
							if (result == 1) {
								session.setAttribute("currentUser", user);
								return new CommonVO(Constants.SUCCESS, "操作成功");
							}
							return new CommonVO(Constants.ERROR, "操作失败");
						}
						return new CommonVO(Constants.ERROR, "昵称已存在");
					}
					return new CommonVO(Constants.ERROR, "用户已存在");
				} else {
					return new CommonVO(Constants.ERROR, "密码组成规则");
				}
			} else {
				return new CommonVO(Constants.ERROR, "昵称组成规则");
			}
		}
		return new CommonVO(Constants.ERROR, "用户名组成规则");
	}
}
