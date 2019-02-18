package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.po.UsersRole;

public interface UsersRoleService {
	/**
	 * 通过userId查询角色
	 * @param UserId
	 * @return
	 * @throws Exception
	 */
	public UsersRole qureyByUserId(Integer accountid)throws Exception;
}
