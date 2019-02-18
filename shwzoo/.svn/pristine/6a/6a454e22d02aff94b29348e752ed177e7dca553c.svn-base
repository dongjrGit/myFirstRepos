package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.UserslevelMapper;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.po.UserslevelExample;
import com.yinlian.wssc.web.service.UserslevelService;

@Component("userslevelService")
public class UserslevelServiceImpl implements UserslevelService {
	
	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(UserslevelServiceImpl.class);
	@Autowired
	private UserslevelMapper userslevelMapper; 

	/**
	 * 获取所有的用户等级
	 * @see com.yinlian.wssc.web.service.UserslevelService#queryAllLevel()
	 */
	@Override
	public List<Userslevel> queryAllLevel() throws Exception {
		UserslevelExample example = new UserslevelExample();
		List<Userslevel> list = userslevelMapper.selectByExample(example);
		return list;
	}

	/**
	 * 根据id查询用户等级信息
	 * @see com.yinlian.wssc.web.service.UserslevelService#queryMemberLevelById(java.lang.Integer)
	 */
	@Override
	public Userslevel queryMemberLevelById(Integer id) throws Exception {
		
		return userslevelMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据id修改等级信息
	 * @see com.yinlian.wssc.web.service.UserslevelService#updateMemberLevelById(com.yinlian.wssc.web.po.Userslevel)
	 */
	@Override
	public int updateMemberLevelById(Userslevel userslevel) throws Exception {
		
		return userslevelMapper.updateByPrimaryKey(userslevel);
	}

	/**
	 * 添加会员等级信息
	 * @see com.yinlian.wssc.web.service.UserslevelService#addMemberLevel(com.yinlian.wssc.web.po.Userslevel)
	 */
	@Override
	public int addMemberLevel(Userslevel userslevel) throws Exception {
		
		return userslevelMapper.insertSelective(userslevel);
	}

	/**
	 * 根据id删除等级信息
	 * @see com.yinlian.wssc.web.service.UserslevelService#deleteById(java.lang.Integer)
	 */
	public int deleteById(Integer id) throws Exception {
		
		return userslevelMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 获取用户等级下拉框
	 */
	public List<Userslevel> getlevelList()throws Exception{
		return userslevelMapper.getlevelList();
	}
	
	/**
	 * 获得最低的等级
	 */
	@Override
	public Userslevel getlowerLevel() throws Exception {
		
		return userslevelMapper.levelOrder();
	}
}
