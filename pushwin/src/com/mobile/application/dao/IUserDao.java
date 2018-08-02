package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisUser;

public interface IUserDao extends IBaseDAO<TBasisUser>{

   /**
 * @param userId 
     * Description : 查询用户
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param orgId 机构ID
	 * @param userCode 员工号
	 * @param userName 员工号
	 * @return 用户信息列表
	 * @exception 
	 */
	List<?> qryUser(TBasisUser tBasisUser, int indexPage, int indexSize, String orgId,String userCode,String userName);

   	/**
	  * Description : 统计用户数目
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param orgId 机构ID
	  * @param userCode 员工号
	  * @param userName 员工号
	  * @return 用户数目
	  * @exception 
	  */
	String qryUserCount(TBasisUser tBasisUser,String orgId,String userCode,String userName);
	
	/**
     * Description : 查询用户角色
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId 用户ID
	 * @return 用户角色信息列表
	 * @exception 
	 */
	List<?> qryUserRole(int indexPage, int indexSize, String userId);

	/**
     * Description : 统计用户角色数目
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId 用户ID
	 * @return 用户角色数目
	 * @exception 
	 */
	String userRoleCount(String userId);

 	/**
	  * Description : 根据用户ID查询用户信息
	  * @author 姜瑞
	  * @version 1.01
	  * @see N/A
	  * @param userId 用户ID
	  * @return 用户信息类
	  * @exception 
	  */
	TBasisUser qryUserById(String userId);

	 /**
	  * Description : 更新用户信息
	  * @author 姜瑞
	  * @version 1.01
	  * @see N/A
	  * @param tBasisUser  用户实体类
	  * @return N/A
	  * @exception 
	  */
	void updateUser(TBasisUser tBasisUser);
	

	List<?> ComTypeList();

	 /**
	  * Description : 查询用户数据
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param userId 用户ID
	  * @return 用户信息列表
	  * @exception 
	  */
	List<?> qryditUserData(String userId);

	 /**
	  * Description :  根据机构查询机构下角色
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param orgId 机构ID
	  * @return 角色信息列表
	  * @exception 
	  */
	List<?> getRole(String orgId);

	 /**
	  * Description : 根据用户查询角色
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param userId 用户ID
	  * @return 角色信息列表
	  * @exception 
	  */
	List<?> getRoleUser(String userId);
	
	 /**
	  * Description : 根据员工号查用户信息
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param userCode 员工号
	  * @return 用户信息列表
	  * @exception 
	  */
	List<?> qryUserByCode(String userCode);

	 /**
	  * Description : 根据员工号，删除用户关联数据表 用户角色，用户机构表数据
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param userCode 员工号
	  * @return N/A
	  * @exception 
	  */
	void removeUserData(String userCode);

	 /**
	  * Description : 根据用户ID，删除用户角色
	  * @author 姜瑞
	  * @version 1.01
	  * @see N/A
	  * @param userId 用户ID
	  * @return N/A
	  * @exception 
	  */
	void removeUserRole(String userId);
	
	  /**
	 * @param roleId 
	  * Description : 根据员工号模糊查询，获取用户信息
	  * @author 
	  * @version 1.01
	  * @see N/A
	  * @param userCode 员工号
	  * @return 用户信息列表
	  * @exception 
	  */
	List<?> getUsers(String userCode, String roleId);
	
	void delUserData(String userId);

	List getOrgByRole(String roleId);

	String userBackRoleCount(String userId);

}
