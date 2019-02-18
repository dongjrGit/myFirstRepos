package com.yinlian.wssc.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.UsersRoleMapper;
import com.yinlian.wssc.web.po.UsersRole;
import com.yinlian.wssc.web.service.UsersRoleService;

@Component("usersRoleService")
public class UsersRoleServiceImpl implements UsersRoleService{
	
	 /**
     * 日志输出的类
     */
    private static final Logger    logger = LoggerFactory.getLogger(UsersRoleServiceImpl.class);

	@Autowired
	private UsersRoleMapper            userRoleMapper;
	
	@Override
	public UsersRole qureyByUserId(Integer accountid) throws Exception {
		
		return userRoleMapper.selectByUserId(accountid);
	}

}
