package com.mobile.application.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IRoleDao;
import com.mobile.application.entity.TBasisApk;
import com.mobile.application.entity.TBasisOrgRole;
import com.mobile.application.entity.TBasisRole;
import com.mobile.application.entity.TBasisRoleApk;
import com.mobile.application.entity.TBasisRoleMenu;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisUserRole;
import com.mobile.application.vo.session.SessionVO;

@SuppressWarnings("rawtypes")
@Repository
public class RoleDaoImpl extends BaseDAOImpl<TBasisRole> implements IRoleDao {
	
	@Autowired
	ServletContext servletContext;

	/**
	 * 分页查询角色数
	 * 
	 * @param roleName
	 *            角色名称
	 * @param indexPage
	 *            当前页
	 * @param indexSize
	 *            每页显示条数
	 * @return 角色集合
	 */
	public List<?> qryRole(String roleName,String roleType, int indexPage, int indexSize,SessionVO sessionVO) {
		StringBuffer hql = new StringBuffer();
		if("1".equals(sessionVO.getIsAdmin())){
			hql = new StringBuffer(
			"select new map(roleId as id,roleName as name,roleDesc as desc) from TBasisRole where 1=1 and roleType = '"+roleType+"'");
			if (StringUtils.isNotBlank(roleName)) {
				hql.append(" and roleName like '%" + roleName + "%'");
			}
			hql.append("order by roleName");
		}
		else{
			hql = new StringBuffer("select new map(tor.tBasisRole.roleId as id,tor.tBasisRole.roleName as name,tor.tBasisRole.roleDesc as desc) from TBasisOrgRole tor where tor.tBasisOrg.orgId = '"+sessionVO.getOrgId()+"' and tor.tBasisRole.roleType = '"+roleType+"'");
			if (StringUtils.isNotBlank(roleName)) {
				hql.append(" and tor.tBasisRole.roleName like '%" + roleName + "%'");
			}
			hql.append("order by tor.tBasisRole.roleName");
		}

		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		hqlQuery.setFirstResult((indexPage) * indexSize);
		hqlQuery.setMaxResults(indexSize);

		List<?> list = hqlQuery.list();
		return list;
	}

	/**
	 * 统计角色数量
	 * 
	 * @param roleName
	 *            角色名称
	 * @param indexPage
	 *            当前页
	 * @param indexSize
	 *            每页显示条数
	 * @return 角色总数
	 */
	public List<?> qryRoleSize(String roleName,String roleType,SessionVO sessionVO) {
		StringBuffer hql = new StringBuffer();
		if("1".equals(sessionVO.getIsAdmin())){
			hql = new StringBuffer(
			"select new map(roleId as id,roleName as name,roleDesc as desc) from TBasisRole where 1=1 and roleType = '"+roleType+"'");
			if (StringUtils.isNotBlank(roleName)) {
				hql.append(" and roleName like '%" + roleName + "%'");
			}
		}
		else{
			hql = new StringBuffer("select new map(tor.tBasisRole.roleId as id,tor.tBasisRole.roleName as name,tor.tBasisRole.roleDesc as desc) from TBasisOrgRole tor where tor.tBasisOrg.orgId = '"+sessionVO.getOrgId()+"' and tor.tBasisRole.roleType = '"+roleType+"'");
			if (StringUtils.isNotBlank(roleName)) {
				hql.append(" and tor.tBasisRole.roleName like '%" + roleName + "%'");
			}
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		List list = hqlQuery.list();
		return list;

	}

	/**
	 * 分页查询数据
	 * 
	 * @param oleId
	 *            角色id
	 * @param roleName
	 *            角色名称
	 * @param indexPage
	 *            当前页
	 * @param indexSize
	 *            每页显示条数
	 * @return 角色下辖用户集合
	 */
	public List<?> qryRoleUser(String roleId, String roleName, int indexPage,
			int indexSize) {
		StringBuffer hql = new StringBuffer(
				"select new map(orgU.TBasisRole.roleName as orleName,orgU.TBasisRole.roleId as roleid,orgU.TBasisUser.userId as userid,orgU.TBasisUser.userName as userName,orgU.TBasisUser.userCode as userCode,orgU.TBasisUser.TBasisOrg.orgName as orgName,orgU.TBasisUser.TBasisOrg.orgId as orgId,orgU.TBasisUser.userPhone as telephone,orgU.TBasisUser.userEmail as email)"
						+ " from TBasisUserRole orgU where 1=1");
		List<?> list = new ArrayList();
		if (roleId == null || "".equals(roleId)) {
			return list;
		}
		if (roleId != null && !"".equals(roleId)) {
			hql.append(" and orgU.TBasisRole.roleId =" + "'" + roleId + "'");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		hqlQuery.setFirstResult((indexPage) * indexSize);
		hqlQuery.setMaxResults(indexSize);
		list = hqlQuery.list();
		return list;
	}

	/**
	 * @param oleId
	 *            角色id
	 * @param roleName
	 *            角色名称
	 * @return 角色下辖用户总数
	 */
	public List<?> qryRoleUserSize(String roleId, String roleName) {
		StringBuffer hql = new StringBuffer(
				"select new map(orgU.TBasisRole.roleName as orleName,orgU.TBasisRole.roleId as roleid,orgU.TBasisUser.userId as userid,orgU.TBasisUser.userName as userName,orgU.TBasisUser.userCode as userCode,orgU.TBasisUser.TBasisOrg.orgName as orgName,orgU.TBasisUser.userPhone as telephone,orgU.TBasisUser.userEmail as email)"
						+ " from TBasisUserRole orgU where 1=1");
		List<?> list = new ArrayList();
		if (StringUtils.isBlank(roleId)) {
			return list;
		}
		if (StringUtils.isNotBlank(roleId)) {
			hql.append(" and orgU.TBasisRole.roleId =" + "'" + roleId + "'");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		list = hqlQuery.list();
		return list;
	}

	/**
	 * @param userids
	 *            用戶id
	 * @param roleids
	 *            角色id
	 * @return 操作結果标识
	 */
	public int delRoleUser(String userids, String roleids) {
		int num = -1;
		String[] uIds = userids.split(",");
		for (int i = 0; i < uIds.length; i++) {
			String sql = "DELETE FROM T_BASIS_USER_ROLE WHERE USER_ID = '"
					+ uIds[i] + "' AND ROLE_ID ='" + roleids + "'";
			Query query = this.getCurrentSession().createSQLQuery(sql);
			num = query.executeUpdate();
		}

		return num;
	}

	/**
	 * 
	 * @param roleId
	 *            角色id
	 * @return 用户所属角色
	 */
	public List<?> queryRoleById(String userId) {
		String hql = "select new map(role.TBasisRole.roleName as roleName) from TBasisUserRole role"
				+ " where role.TBasisUser.userId ='" + userId + "'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List List = hqlQuery.list();
		return List;
	}

	/**
	 * 查询角色数据
	 * 
	 * @param roleName
	 *            角色名称
	 * @return 角色集合
	 */
	public List<?> queryRoleByName(String roleId,String roleName,String orgId) {
		
		String hql = "from TBasisOrgRole tor where tor.tBasisRole.roleName = '" + roleName + "' ";//and tor.tBasisOrg.orgId = '"+orgId+"'
		if(roleId!=null&&!"".equals(roleId)){
			hql+=" and tor.tBasisRole.roleId <> '"+roleId+"' ";
		}
		if(orgId!=null&&!"".equals(orgId))	{
			hql+=" and (  ";
			String org[] = orgId.split(",");
			hql+="  tor.tBasisOrg.orgId = '"+org[0]+"' ";
			for(int i=1;i<org.length;i++){
				hql+=" or tor.tBasisOrg.orgId = '"+org[i]+"' ";
			}
			hql+=" ) ";
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list = hqlQuery.list();
		return list;
	}

	/**
	 * 保存角色信息
	 * 
	 * @param role
	 *            角色对象
	 */
	public void saveRole(TBasisRole role) {
		saveOrUpdate(role);
	}

	/**
	 * 获取角色信息
	 * 
	 * @param id
	 *            角色对象id
	 */
	public TBasisRole getRoleEnity(Serializable id) {
		return get(TBasisRole.class, id);
	}

	/**
	 * 保存机构角色关系
	 * 
	 * @param orgRole
	 */
	public void saveRoleOrg(TBasisOrgRole orgRole) {
		this.getCurrentSession().saveOrUpdate(orgRole);
	}

	/**
	 * 使用角色id删除角色机构关系数据
	 * 
	 * @param roleId
	 *            角色id
	 */
	public void delOrgByRoleId(String roleId) {
		String qSql = "delete from TBasisOrgRole where tBasisRole.roleId="
				+ "'" + roleId + "'";
		Query query = getCurrentSession().createQuery(qSql);
		query.executeUpdate();
	}

	/**
	 * 统计角色数量
	 * 
	 * @param roleId
	 *            较色id
	 * @return
	 */
	public Long queryRoleHasUser(String roleId) {
		String hql = " Select count(id) from TBasisUserRole ur where ur.TBasisRole.roleId=:roleId";
		Query query = this.getCurrentSession().createQuery(hql);
		Long count = (Long) query.setString("roleId", roleId).uniqueResult();
		return count;
	}

	/**
	 * 删除角色及角色关联数据
	 * 
	 * @param roleId
	 *            角色id
	 */
	public void delRoleRelInfo(String roleId) {
		// 先删除关联表信息
		String sqlOrg = "DELETE  FROM T_BASIS_ORG_ROLE WHERE ROLE_ID =:ROLEID ";
		String sqlRole = "DELETE FROM T_BASIS_ROLE WHERE ROLE_ID =:ROLEID ";
		String sqlRoleMenu = "DELETE FROM T_BASIS_ROLE_MENU WHERE ROLE_ID=:ROLEID";
		String sqlRoleUser = "DELETE FROM T_BASIS_USER_ROLE WHERE ROLE_ID=:ROLEID";
		String sqlRoleApk = "DELETE FROM T_BASIS_ROLE_APK WHERE ROLE_ID=:ROLEID";
		String sqlRoleNotice = "DELETE　FROM T_BASIS_ROLE_NOTICE WHERE ROLE_ID=:ROLEID";
		Query queryOrg = this.getCurrentSession().createSQLQuery(sqlOrg);
		Query queryRoleMenu = this.getCurrentSession().createSQLQuery(
				sqlRoleMenu);
		Query queryRoleApk = this.getCurrentSession()
				.createSQLQuery(sqlRoleApk);
		Query queryUserRole = this.getCurrentSession().createSQLQuery(
				sqlRoleUser);
		Query queryRoleNotice = this.getCurrentSession().createSQLQuery(sqlRoleNotice);
		Query queryRole = this.getCurrentSession().createSQLQuery(sqlRole);
		queryOrg.setString("ROLEID", roleId);
		queryRole.setString("ROLEID", roleId);
		queryRoleMenu.setString("ROLEID", roleId);
		queryRoleApk.setString("ROLEID", roleId);
		queryUserRole.setString("ROLEID", roleId);
		queryRoleNotice.setString("ROLEID", roleId);
		queryOrg.executeUpdate();
		queryRoleMenu.executeUpdate();
		queryUserRole.executeUpdate();
		queryRoleApk.executeUpdate();
		queryRoleNotice.executeUpdate();
		queryRole.executeUpdate();

	}

	/**
	 * 使用用户id查询用户角色信息数据
	 * 
	 * @param userId
	 *            用户id
	 * @return 用户集合
	 */
	public List<?> findRoleUser(String userId) {
		String hql = "from TBasisUserRole u where u.TBasisUser.userId = :userId ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		hqlQuery.setString("userId", userId);
		List<?> list = hqlQuery.list();
		return list;

	}

	/**
	 * 使用用户id查询用户信息
	 * 
	 * @param userId
	 *            用户id
	 * @return 用户对象
	 */
	public TBasisUser findUserById(String userId) {
		TBasisUser user = new TBasisUser();
		if (StringUtils.isNotBlank(userId)) {
			String hql = "from TBasisUser u where u.userId=:id";
			Query query = this.getCurrentSession().createQuery(hql);
			query.setString("id", userId);
			user = (TBasisUser) query.uniqueResult();
		}
		return user;
	}

	/**
	 * 使用角色id查询角色信息
	 * 
	 * @param roleid
	 *            角色id
	 * @return 用户角色对象
	 */
	public List<?> findRolerById(String roleId) {
		List<?> list = null;
		if (StringUtils.isNotBlank(roleId)) {
			String hql = "select u.TBasisUser.userId from TBasisUserRole u where u.TBasisRole.roleId =:id";
			Query query = this.getCurrentSession().createQuery(hql);
			query.setString("id", roleId);
			list = query.list();
		}
		return list;
	}

	/**
	 * 保存角色与用户关系数据
	 * 
	 * @param userRole
	 *            用户角色对象
	 */
	public void saveUserRole(TBasisUserRole userRole) {
		this.getCurrentSession().saveOrUpdate(userRole);
	}

	/**
	 * 使用角色 id删除角色与用户关系数据
	 * 
	 * @param roleaId
	 *            角色id
	 */
	public void delUserRoleByRoleId(String roleId) {
		String qSql = "delete from TBasisUserRole where TBasisRole.roleId="
				+ "'" + roleId + "'";
		Query query = getCurrentSession().createQuery(qSql);
		query.executeUpdate();
	}

	/**
	 * 查询菜单数据
	 */
	public List<?> showMenu() {
		String hql = "select new map(menuId as id , menuPid as pid , menuName as text, menuSort as isDel) from TBasisMenu order by menuSort";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list = hqlQuery.list();
		return list;
	}

	/**
	 * 使用角色id查询角色与菜单关系 数据
	 * 
	 * @id roleId 角色 id
	 */
	public List<?> showMenuOrg(String id) {
		String hql = "select new map(Rmenu.TBasisMenu.menuId as id , Rmenu.TBasisMenu.menuPid as pid , Rmenu.TBasisMenu.menuName as text) from TBasisRoleMenu Rmenu"
				+ " where Rmenu.TBasisRole.roleId ='" + id + "'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list = hqlQuery.list();
		return list;
	}

	/**
	 * 使用角色id删除角色与菜单关系
	 * 
	 * @param roleId
	 *            角色 id
	 */
	public void deleteRoleMenu(String roleId) {
		String sql = "DELETE FROM T_BASIS_ROLE_MENU WHERE ROLE_ID =:ROLEID";
		Query query = this.getCurrentSession().createSQLQuery(sql);
		query.setString("ROLEID", roleId);
		query.executeUpdate();
	}

	/**
	 * 保存角色与菜单关系数据
	 * 
	 * @param roleMenu
	 *            角色与菜单关系对象
	 * 
	 */
	public void saveRoleMenu(TBasisRoleMenu roleMenu) {
		this.getCurrentSession().saveOrUpdate(roleMenu);
	}

	/**
	 * 查询apk数据
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<?> queryApk() {
		String hql = "select new map(apk.dealId as dealId , apk.dealName as dealName, apk.dealCode as dealCode,apk.dealChannel as dealChannel, apk.dealType as dealType,apk.dealDescription as dealDescription) from TBasisApk apk order by apk.dealName";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<Map<String,String>> list = hqlQuery.list();
		//转换业务字典
		Map<String, Map<String, String>> dictMap = (Map<String, Map<String, String>>) servletContext.getAttribute("dictMap");
		Map<String, String> apkType =  dictMap.get("BASIS_APK_TYPE");
		Map<String, String> apkChannel =  dictMap.get("BASIS_APK_CHANNEL");
		for (Map<String, String> item : list) {
			item.put("dealType", apkType.get(item.get("dealType")));
			item.put("dealChannel", apkChannel.get(item.get("dealChannel")));
		}
		return list;
	}

	/**
	 * 查询角色已关联apk数据
	 * 
	 * @param roleId
	 *            角色id
	 */
	@Override
	public List<?> queryRoleApk(String roleId) {
		String hql = "select roleApk.TBasisApk.dealId from TBasisRoleApk roleApk"
				+ " where roleApk.TBasisRole.roleId ='" + roleId + "'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list = hqlQuery.list();
		return list;
	}

	/**
	 * 保存角色与apk关系数据
	 */
	@Override
	public void saveRoleApk(TBasisRoleApk roleApk) {
		this.getCurrentSession().saveOrUpdate(roleApk);
	}

	/**
	 * 使用角色id删除角色与apk关系
	 * 
	 * @param roleId
	 *            角色 id
	 */
	@Override
	public void deleteRoleApk(String roleId) {
		String sql = "DELETE FROM T_BASIS_ROLE_APK WHERE ROLE_ID =:ROLEID";
		Query query = this.getCurrentSession().createSQLQuery(sql);
		query.setString("ROLEID", roleId);
		query.executeUpdate();
	}

	@Override
	public TBasisApk queryApkById(String dealId) {
		TBasisApk apk = new TBasisApk();
		if (StringUtils.isNotBlank(dealId)) {
			String hql = "from TBasisApk u where u.dealId=:id";
			Query query = this.getCurrentSession().createQuery(hql);
			query.setString("id", dealId);
			apk = (TBasisApk) query.uniqueResult();
		}
		return apk;
	}

	
}
