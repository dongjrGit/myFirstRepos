package com.mobile.application.service.role;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.mobile.application.entity.TBasisApk;
import com.mobile.application.entity.TBasisOrgRole;
import com.mobile.application.entity.TBasisPage;
import com.mobile.application.entity.TBasisRole;
import com.mobile.application.entity.TBasisRoleApk;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisUserRole;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

public interface IRoleService {
	
	 /**
	  * Description : 角色列表查詢
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleName 角色名称
	 * @param roleType 
	  * @param page 页码信息
	 * @param session 
	  * @return 角色列表map集合
	  * @exception BusinessException
	  */
	public Map<String,Object> queryRoleList(String roleName,String roleType, TBasisPage page, HttpSession session);
	
	 /**
	  * Description : 角色下辖用户列表查询
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @param roleName 角色名称
	  * @param page 页码信息
	  * @return 角色下用户信息列表map集合
	  * @exception BusinessException
	  */
	public Map<String,Object> queryRoleUserList(String roleId,String roleName,TBasisPage page);
	
	 /**
	  * Description : 删除用户角色关系 
	  * @author  杨习超
	  * @version 1.01
	  * @see N/A
	  * @param userids 用户集合
	  * @param roleids 角色
	  * @return 数字标志
	  * @exception BusinessException
	  */
	public int delRoleUser(String userids, String roleids);
	
	 /**
	  * Description : 查询用户所属角色
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param userId 用户ID
	  * @return
	  * @exception BusinessException
	  */
	public Map<String,Object> queryRoleByUserId(String userId);
	
	 /**
	  * Description : 查询角色名称对应角色
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleName 角色名称
	 * @param orgId 
	  * @return
	  * @exception BusinessException
	  */
	public List<?> queryRoleByName(String roleId,String roleName, String orgId);
	
	 /**
	  * Description : 保存角色信息
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param role 角色实体类TBasisRole
	  * @exception BusinessException
	  */
	public void saveRole(TBasisRole role);
	
	 /**
	  * Description : 获取角色对象
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param id 
	  * @return TBasisRole
	  * @exception BusinessException
	  */
	public TBasisRole getRoleEnity(Serializable id);
	
	 /**
	  * Description : 保存角色机构对象
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param orgRole 角色机构TBasisOrgRole
	  * @exception BusinessException
	  */
	public void saveRoleOrg(TBasisOrgRole orgRole);
	
	 /**
	  * Description : 删除角色所属机构
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @exception BusinessException
	  */
	public void delOrgByRoleId(String roleId);
	
	 /**
	  * Description : 查询角色对象
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @return
	  * @exception BusinessException
	  */
	public Long queryRoleHasUser(String roleId);
	
	 /**
	  * Description : 删除角色相关信息
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @exception BusinessException
	  */
	public void delRoleRelInfo(String roleId);
	
	 /**
	  * Description : 保存角色用户关系 
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param userRole 用户角色TBasisUserRole
	  * @exception BusinessException
	  */
	public void saveUserRole(TBasisUserRole userRole);

	 /**
	  * Description : 删除用户角色关系 
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @exception BusinessException
	  */
	public void delUserRoleByRoleId(String roleId);

	 /**
	  * Description : 查询菜单 
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @return 菜单List
	  * @exception BusinessException
	  */
	public List<?> showMenu();

	 /**
	  * Description : 查询角色已关联菜单 
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param id 角色ID
	  * @return 菜单List
	  * @exception BusinessException
	  */
	public List<?> showMenuOrg(String id);

	//添加角色菜单关系 
	public void addRoleMenu(String roleId, String menuIds);
	
	 /**
	  * Description : 使用角色 id查询角色信息
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色 id
	  * @return 角色列表List
	  * @exception BusinessException
	  */
	public List<?> findRolerById(String roleId);
	
	//
	 /**
	  * Description : 使用用户id查询用户信息
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param userId 用户id
	  * @return TBasisUser
	  * @exception BusinessException
	  */
	public TBasisUser findUserById(String userId);
	
	 /**
	  * Description : 查询apk
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @return 所有APKList
	  * @exception BusinessException
	  */
	public List<?> queryApk();
	
	 /**
	  * Description : 查询与角色已关联的apk
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @return 角色下APKList
	  * @exception BusinessException
	  */
	public List<?> queryRoleApk(String roleId);
	
	 /**
	  * Description : 保存角色与apk关系数据
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleApk 角色关联apk TBasisRoleApk
	  * @exception BusinessException
	  */
	public void saveRoleApk(TBasisRoleApk roleApk);
	
	 /**
	  * Description : 删除角色与apk关系数据
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @exception BusinessException
	  */
	public void deleteRoleApk(String roleId);
	
	 /**
	  * Description : 獲取交易數據
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param dealId apkID
	  * @return TBasisApk
	  * @exception BusinessException
	  */
	public TBasisApk findApkById(String dealId);

	/**
	  * Description : 獲取角色用户
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色id
	  * @return list
	  * @exception BusinessException
	  */
	public List<?> qryRoleUser(String roleId);


}