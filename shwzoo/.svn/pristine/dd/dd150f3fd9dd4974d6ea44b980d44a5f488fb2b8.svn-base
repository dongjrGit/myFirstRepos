package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.UserAttrMapper;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.UserAttrExample;
import com.yinlian.wssc.web.service.UserAttrService;
@Component("userAttrService")
public class UserAttrServiceImpl implements UserAttrService {
	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserAttrServiceImpl.class);
	@Autowired
	private UserAttrMapper userAttrMapper;
	/**
	 * 根据userid查询用户基本信息
	 * @see com.yinlian.wssc.web.service.UserAttrService#selectById(java.lang.Integer)
	 */
	@Override
	public List<UserAttr> selectById(Integer userid) throws Exception {
		UserAttrExample example = new UserAttrExample();
		UserAttrExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		return userAttrMapper.selectByExample(example);
	}
	/**
	 * 根据userid修改用户基本信息
	 * @see com.yinlian.wssc.web.service.UserAttrService#updateById(com.yinlian.wssc.web.po.UserAttr)
	 */
	@Override
	public int updateById(UserAttr record1) throws Exception {

		return userAttrMapper.updateByPrimaryKey(record1);
	}
	/**
	 * 添加新会员的基本信息
	 * @see com.yinlian.wssc.web.service.UserAttrService#addUserAttr(com.yinlian.wssc.web.po.UserAttr)
	 */
	@Override
	public int addUserAttr(UserAttr userAttr) {

		return userAttrMapper.insert(userAttr);
	}
	/**
	 * 根据userid删除会员的基本信息
	 * @see com.yinlian.wssc.web.service.UserAttrService#deleteMemberByUserId(java.lang.Integer)
	 */
	@Override
	public int deleteMemberByUserId(Integer userid) throws Exception {
		UserAttrExample example = new UserAttrExample();
		UserAttrExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		return userAttrMapper.deleteByExample(example);
	}

}
