package com.mobile.application.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.entity.TBasisApk;
import com.mobile.application.entity.TBasisDevice;
import com.mobile.application.entity.TBasisOrgRole;
import com.mobile.application.entity.TBasisRole;
import com.mobile.application.entity.TBasisRoleApk;
import com.mobile.application.entity.TBasisRoleMenu;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisUserRole;
import com.mobile.application.vo.session.SessionVO;

public interface IRoleDao extends IBaseDAO<TBasisRole> {

	public List<?> qryRole(String name, String roleType, int indexPage, int indexSize, SessionVO sessionVO);

	public List<?> qryRoleSize(String name,String roleType, SessionVO sessionVO);

	public List<?> qryRoleUser(String roleId, String roleName, int indexPage,
			int indexSize);

	public List<?> qryRoleUserSize(String roleId, String roleName);

	public int delRoleUser(String userids, String roleids);

	public List<?> queryRoleById(String userId);

	public List<?> queryRoleByName(String roleId,String roleName,String orgId);

	public void saveRole(TBasisRole role);

	public TBasisRole getRoleEnity(Serializable id);

	public void saveRoleOrg(TBasisOrgRole orgRole);

	public void delOrgByRoleId(String roleId);

	public Long queryRoleHasUser(String roleId);

	public void delRoleRelInfo(String roleId);
	
	public List<?> findRoleUser(String userId);
	
	public TBasisUser findUserById(String userId);
	
	public List<?> findRolerById(String roleId);
	
	public void saveUserRole(TBasisUserRole userRole);
	
	public void delUserRoleByRoleId(String roleId);
	
	public List<?> showMenu();
	
	public List<?> showMenuOrg(String id);
	
	public void deleteRoleMenu(String roleId);
	
	public void saveRoleMenu(TBasisRoleMenu roleMenu);
	
	public List<?> queryApk();
	
	public List<?> queryRoleApk(String roleId);
	
	public void saveRoleApk(TBasisRoleApk roleApk);
	
	public void deleteRoleApk(String roleId);
	
	public TBasisApk queryApkById(String dealId);
	
}
