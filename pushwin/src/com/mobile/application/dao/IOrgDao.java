package com.mobile.application.dao;

import java.util.List;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.vo.session.SessionVO;

public interface IOrgDao extends IBaseDAO<TBasisOrg>{

	/**
	 * Description : 根据当前登录用户和机构id获取权限机构树
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param user ： 登录用户session
	 * @param orgId ： 机构id
	 * @return 查询结果集
	 * @exception
	 */
	List<?> qryOrgTree(SessionVO user, String orgId);
    
    /**
	 * Description : 根据机构id获取机构信息
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
     * @param orgId
     * @return 查询结果集
     * @exception
     */
	List<?> qryOrgByID(String orgId);
	
    /**
	 * Description : 根据机构和查询条件获取该机构下的人员信息
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
     * @param deptId : 机构id
     * @param key ： 查询条件
     * @param indexPage ： 分页
     * @param indexSize ： 分页大小
     * @param user ： 登录用户session
     * @return 查询结果集
     * @exception
     */
	List<?> listUser(String deptId, String key, int indexPage, int indexSize,
			SessionVO user);

	/**
	 * Description : 人员信息记录数
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param deptId
	 * @param key
	 * @param user
	 * @return 查询结果集
	 * @exception
	 */
	List<?> listUser(String deptId, String key, SessionVO user);

	/**
	 * Description : 验证机构名称是否重复
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgName ： 机构名
	 * @return 查询结果集
	 * @exception
	 */
	Long checkOrgNameDouble(String orgName);

	/**
	 * Description : 验证机构号是否重复
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgCode
	 * @return
	 */
	Long checkOrgCodeDouble(String orgCode);


	/**
	 * Description : 新增机构
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param userId
	 * @param orgId
	 */
	void addOrg(String userId, String orgId);

    /**
	 * Description : 查询用户的机构权限数据
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
     * @param user
     * @return 查询结果集
     * @exception
     */
	List<TBasisOrg> queryOrgsByUser(SessionVO user);

	/**
	 * Description : 通过机构号获取机构实体类
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgCode
	 * @return 查询结果集
	 * @exception
	 */
	TBasisOrg findByCode(String orgCode);

	/**
	 * Description : 查询用户是否有角色
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgId
	 * @return 查询结果集
	 * @exception
	 */
	long queryRoleHasUser(String orgId);

	/**
	 * Description : 删除机构
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgId
	 */
	void delOrg(String orgId);

	/**
	 * @param userId 
	 * Description : 获取所有机构信息
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @return 查询结果集
	 * @exception
	 */
	List<TBasisOrg> getAll(String userId);

	/**
	 * Description : 通过用户id获取用户所在机构id
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param userId : 用户id
	 * @return 查询结果集
	 * @exception
	 */
	List<?> findOrgByUser(String userId);

	/**
	 * Description : 获取角色机构树
	 * @author 杨习超
	 * @version 1.01
	 * @see N/A
	 * @param sessionVo
	 * @return
	 */
	List<?> queryOrg(SessionVO sessionVo);

	List<?> queryOrgByRoleId(String roleId);
	
	long queryOrgHasDevice(String orgId);

	List<?> qryOrgByProductId(String productId);

	List<String> qryUserDataOrg(String userId);

	void deleteUserDataOrg(String userId);

}
