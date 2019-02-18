package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.UserAttr;



/**
 * 用户信息的控制类
 * @author Administrator
 *
 */
public interface UserAttrService {

	/**
	 * 根据userid查询用户的基本信息
	 * @param userid
	 * @return
	 */
	List<UserAttr> selectById(Integer userid) throws Exception;


	/**
	 * 根据id修改userattr信息
	 * @param record1
	 * @return
	 */
	int updateById(UserAttr record1) throws Exception;

	/**
	 * 添加新会员UserAttr中的信息
	 * @param userAttr
	 * @return
	 */
	int addUserAttr(UserAttr userAttr) throws Exception;

	/**
	 * 根据userid删除会员的基本信息
	 * @param userid
	 * @return
	 */
	int deleteMemberByUserId(Integer userid) throws Exception;



}
