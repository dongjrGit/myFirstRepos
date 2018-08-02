package com.mobile.application.service.role.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.dao.IDeviceDao;
import com.mobile.application.dao.IRoleDao;
import com.mobile.application.entity.TBasisApk;
import com.mobile.application.entity.TBasisMenu;
import com.mobile.application.entity.TBasisOrgRole;
import com.mobile.application.entity.TBasisPage;
import com.mobile.application.entity.TBasisRole;
import com.mobile.application.entity.TBasisRoleApk;
import com.mobile.application.entity.TBasisRoleMenu;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisUserRole;
import com.mobile.application.service.role.IRoleService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IDeviceDao deviceDao;

	@Override
	@Transactional
	public Map<String, Object> queryRoleList(String roleName,String roleType, TBasisPage page,HttpSession session) {
		SessionVO user = (SessionVO)session.getAttribute("sessionVO");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<?> roleList = roleDao.qryRole(roleName,roleType, page.getPageIndex(),page.getPageSize(),user);
		List<?> roleCount = roleDao.qryRoleSize(roleName,roleType,user);
		dataMap.put("roleList", roleList);
		dataMap.put("roleCount", roleCount.size());
		return dataMap;
	}

	@Override
	@Transactional
	public Map<String, Object> queryRoleUserList(String roleId,
			String roleName, TBasisPage page) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<?> roleUserList = roleDao.qryRoleUser(roleId, null,
				page.getPageIndex(), page.getPageSize());
		List<?> roleUserSize = roleDao.qryRoleUserSize(roleId, null);
		dataMap.put("roleUserList", roleUserList);
		dataMap.put("roleUserCount", roleUserSize.size());	

		return dataMap;
	}

	@Transactional
	public int delRoleUser(String userids, String roleids) {
		return roleDao.delRoleUser(userids, roleids);
	}

	@Transactional
	public Map<String, Object> queryRoleByUserId(String userId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<?> roleList = roleDao.queryRoleById(userId);
		dataMap.put("roleList", roleList);
		return dataMap;
	}

	@Transactional
	public List<?> queryRoleByName(String roleId,String roleName,String orgId) {
		return roleDao.queryRoleByName(roleId,roleName,orgId);
	}

	@Transactional
	public void saveRole(TBasisRole role) {
		roleDao.saveRole(role);
	}

	@Transactional
	public TBasisRole getRoleEnity(Serializable id) {
		return roleDao.getRoleEnity(id);
	}

	@Transactional
	public void saveRoleOrg(TBasisOrgRole orgRole) {
		roleDao.saveRoleOrg(orgRole);
	}

	@Transactional
	public void delOrgByRoleId(String roleId) {
		roleDao.delOrgByRoleId(roleId);
	}

	@Transactional
	public Long queryRoleHasUser(String roleId) {
		TBasisRole role = this.roleDao.getRoleEnity(roleId);
		Long count = 0L;
		Set<TBasisUserRole> TBasisUserRoles = role.getTBasisUserRoles();
		for(TBasisUserRole tBasisUserRoles:TBasisUserRoles){
			TBasisUser user = tBasisUserRoles.getTBasisUser();
			if("false".equals(user.getIsDisable())){
				count++;
			}
		}
		return count;
		//return roleDao.queryRoleHasUser(roleId);
	}

	@Transactional
	public void delRoleRelInfo(String roleId) {
		roleDao.delRoleRelInfo(roleId);
	}

	@Transactional
	public List<?> findRoleUser(String userId) {
		return roleDao.findRoleUser(userId);
	}

	@Transactional
	public TBasisUser findUserById(String userId) {
		return roleDao.findUserById(userId);
	}

	@Transactional
	public List<?> findRolerById(String roleId) {
		return roleDao.findRolerById(roleId);
	}

	@Override
	@Transactional
	public void saveUserRole(TBasisUserRole userRole) {
		roleDao.saveUserRole(userRole);

	}

	@Transactional
	public void delUserRoleByRoleId(String roleId) {
		roleDao.delUserRoleByRoleId(roleId);
	}

	@Override
	@Transactional
	public List<?> showMenu() {
		return roleDao.showMenu();
	}

	@Override
	@Transactional
	public List<?> showMenuOrg(String id) {
		return roleDao.showMenuOrg(id);
	}

	@Transactional
	public void addRoleMenu(String roleId, String menuIds) {
		if (StringUtils.isNotBlank(roleId)) {
			String rId = roleId;
			String[] mIds = menuIds.split(",");
			roleDao.deleteRoleMenu(rId);
			TBasisRole role = new TBasisRole();
			role.setRoleId(rId);
			for (int i = 0; i < mIds.length; i++) {
				if (!"1".equals(mIds[i])) {
					TBasisMenu menu = new TBasisMenu();
					menu.setMenuId(mIds[i]);
					TBasisRoleMenu roleMenu = new TBasisRoleMenu();
					roleMenu.setTBasisRole(role);
					roleMenu.setTBasisMenu(menu);
					roleDao.saveRoleMenu(roleMenu);
				}
			}
		}
	}

	@Override
	@Transactional
	public List<?> queryApk() {
		return roleDao.queryApk();
	}

	@Override
	@Transactional
	public List<?> queryRoleApk(String roleId) {
		return roleDao.queryRoleApk(roleId);
	}

	@Override
	@Transactional
	public void saveRoleApk(TBasisRoleApk roleApk) {
		roleDao.saveRoleApk(roleApk);
	}

	@Override
	@Transactional
	public void deleteRoleApk(String roleId) {
		roleDao.deleteRoleApk(roleId);
	}

	@Override
	@Transactional
	public TBasisApk findApkById(String dealId) {
		return roleDao.queryApkById(dealId);
	}

	@Override
	@Transactional
	public List<?> qryRoleUser(String roleId) {
		return roleDao.qryRoleUser(roleId,"",0,1000);
	}



}
