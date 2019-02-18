package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.Userslevel;

/**
 * 用户等级的业务层
 * @author Administrator
 *
 */
public interface UserslevelService {

	/**
	 * 获取所有的用户等级
	 * @return
	 * @throws Exception
	 */
	List<Userslevel> queryAllLevel() throws Exception;

	/**
	 * 根据id查询用户等级信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Userslevel queryMemberLevelById(Integer id) throws Exception;

	/**
	 * 根据id修改等级信息
	 * @param userslevel
	 * @return
	 * @throws Exception
	 */
	int updateMemberLevelById(Userslevel userslevel) throws Exception;

	/**
	 * 添加会员等级信息
	 * @param userslevel
	 * @return
	 * @throws Exception
	 */
	int addMemberLevel(Userslevel userslevel) throws Exception;

	/**
	 * 根据id删除等级信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteById(Integer id) throws Exception;
	
	List<Userslevel> getlevelList()throws Exception;
	
	/**
	 * 查询用户最低等级
	 * @return
	 * @throws Exception
	 */
	Userslevel getlowerLevel() throws Exception;

}
