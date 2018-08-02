package com.mobile.application.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IUserDao;
import com.mobile.application.entity.TBasisUser;

/**
 * @author Administrator
 *
 */
@Repository
public class UserDaoImpl extends BaseDAOImpl<TBasisUser> implements IUserDao {
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#qryUserCount(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String qryUserCount(TBasisUser user,String orgId,String userCode,String userName) {
		String hql = "select new map(count(distinct t.userId) as userCount)  from  "; 
		if(!"".equals(orgId) && orgId != null )
		{
			hql += " TBasisUser t where  t.isDisable <> 'true' and t.TBasisOrg.orgId='"+orgId+"'";
		}else if("1".equals(user.getIsAdmin())){
			hql += " TBasisUser t where  t.isDisable <> 'true' and t.isAdmin is null ";
		}else{
			hql += " TBasisUser t , TBasisUserData ud where ud.TBasisUser.userId='"+user.getUserId()+"' and  t.TBasisOrg.orgId=ud.TBasisOrg.orgId and t.isDisable <> 'true' ";
		}
		if(!"".equals(userCode) && userCode != null )
		{
			hql += " and t.userCode like '%"+userCode+"%'";
		}
		if(!"".equals(userName) && userName != null )
		{
			hql +=" and t.userName like '%"+userName+"%'";
		}
			Query hqlQuery = this.getCurrentSession().createQuery(hql);
			Map<String, Long> userCountMap = (Map<String, Long>) hqlQuery.uniqueResult(); // 		
			return userCountMap.get("userCount").toString();
	
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#qryUser(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<?> qryUser( TBasisUser user,int indexPage, int indexSize, String orgId,String userCode,String userName) {
		{
			String hql = "select distinct new map(t.userId as id,t.userCode as userCode,t.userName as userName,t.TBasisOrg.orgName as orgName,t.TBasisOrg.orgId as orgId,t.TBasisOrg.orgName as orgName,t.isClose as isClose ,coalesce(t.noLogin , 0) as noLogin ) from  ";
			if(!"".equals(orgId) && orgId != null )
			{
				hql += " TBasisUser t where  t.isDisable <> 'true' and t.TBasisOrg.orgId='"+orgId+"'";
			}else if("1".equals(user.getIsAdmin())){
				hql += " TBasisUser t where  t.isDisable <> 'true' ";
			}else{
				hql += " TBasisUser t , TBasisUserData ud where ud.TBasisUser.userId='"+user.getUserId()+"' and  t.TBasisOrg.orgId=ud.TBasisOrg.orgId and t.isDisable <> 'true' ";
			}
			if(!"".equals(userCode) && userCode != null )
			{
				hql += " and t.userCode like '%"+userCode+"%'";
			}
			if(!"".equals(userName) && userName != null )
			{
				hql +=" and t.userName like '%"+userName+"%'";
			}
			hql+=" order by t.userCode asc";
			Query hqlQuery = this.getCurrentSession().createQuery(hql);
			hqlQuery.setFirstResult((indexPage)*indexSize);  
			hqlQuery.setMaxResults(indexSize);  
			List<?> list = hqlQuery.list(); 
			
			if(list!=null && list.size()>0){
				for(int i = 0 ; i< list.size() ; i++){
					Map<String , String> map = (Map) list.get(i);
					String flag = map.get("isClose");
					if("true".equals(flag)){
						map.put("isClose", "是");
					}else{
						map.put("isClose", "否");
					}
				}
			}
			
			return list;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#qryUserRole(int, int, java.lang.String)
	 */
	@Override
	public List<?> qryUserRole(int indexPage, int indexSize, String userId) {
			String hql = "select new map(t.TBasisRole.roleId as roleId, t.TBasisRole.roleName as roleName, t.TBasisRole.roleDesc as roleDesc,t.TBasisRole.roleType as roleType) from TBasisUserRole t where t.TBasisUser.userId=:userId ";
			Query hqlQuery = this.getCurrentSession().createQuery(hql);
			hqlQuery.setParameter("userId", userId);
			hqlQuery.setFirstResult((indexPage)*indexSize);
			hqlQuery.setMaxResults(indexSize);
			List<?> list =hqlQuery.list();
			return list;
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#userRoleCount(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String userRoleCount(String userId) {
		String hql = "select new map(count(t.TBasisRole.roleId) as roleCount) from TBasisUserRole t where t.TBasisUser.userId=:userId";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("userId", userId);
		Map<String, Long> userCountMap = (Map<String, Long>) hqlQuery.uniqueResult(); // 		
		return userCountMap.get("roleCount").toString();
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#qryUserById(java.lang.String)
	 */
	@Override
	public TBasisUser qryUserById(String userId){
		String hql = "from TBasisUser tbu where tbu.userId=:userId";
		return (TBasisUser) this.getCurrentSession().createQuery(hql).setParameter("userId", userId).uniqueResult();
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#updateUser(com.mobile.application.entity.TBasisUser)
	 */
	@Override
	public void updateUser(TBasisUser tBasisUser) {
		this.getCurrentSession().saveOrUpdate(tBasisUser);
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#ComTypeList()
	 */
	@Override
	public List<?> ComTypeList() {
		String hql = "select new map(d.dictremark as id , d.dictremark as text) from TBasisDict d where d.id.busintypeid = '10090'  group by dictremark order by dictremark";
		Query query = this.getCurrentSession().createQuery(hql);
		List<?> list = query.list();
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#qryditUserData(java.lang.String)
	 */
	@Override
	public List<?> qryditUserData(String userId) {
		String hql = "select new map(tbu.userId as userIdData ,tbu.userCode as userCodeData ,tbu.userName as userNameData,tbu.userEmail as userEmailData,tbu.userPhone as userCardData,tbu.userIcon as imgPath , tbu.TBasisOrg.orgName as orgName , tbu.isClose as isClose) from TBasisUser tbu where tbu.userId=:userId";
		List<?> list = this.getCurrentSession().createQuery(hql).setParameter("userId", userId).list();
		return  list;
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#getRole(java.lang.String)
	 */
	@Override
	public List<?> getRole(String orgId) {
		String hql ="select new map(role.tBasisRole.roleId as roleId, role.tBasisRole.roleName as roleName, role.tBasisRole.roleDesc as roleDesc) from TBasisOrgRole role where role.tBasisOrg.orgId=:orgId ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("orgId", orgId);
		List<?> list = hqlQuery.list(); 
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#getRoleUser(java.lang.String)
	 */
	@Override
	public List<?> getRoleUser(String userId) {
		String hql = "select distinct new map( t.TBasisRole.roleId as roleId,  t.TBasisRole.roleName as roleName ) from TBasisUserRole t where t.TBasisUser.userId=:userId ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("userId", userId);
		List<?> list =hqlQuery.list();
		return list;
	}
 
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#qryUserByCode(java.lang.String)
	 */
	@Override
	public List<?> qryUserByCode(String userCode) {
		String hql ="select new map(u.userId as userId,u.userCode as userCode) from TBasisUser u where u.userCode =:userCode and u.isDisable <> 'true'"; 
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("userCode", userCode);
		List<?> list = hqlQuery.list(); 
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#removeUserData(java.lang.String)
	 */
	@Override
	public void removeUserData(String userId) {
		String hqlRole= "delete from TBasisUserRole2 role where role.userId =:userId ";
		String hqlOrg = "delete from TBasisUserOrg2 org where org.userId =:userId ";
		Query queryRole = this.getCurrentSession().createQuery(hqlRole);
		Query queryOrg = this.getCurrentSession().createQuery(hqlOrg);
		queryRole.setParameter("userId", userId);
		queryOrg.setParameter("userId", userId);
		queryRole.executeUpdate();
		queryOrg.executeUpdate();
	
		
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#deleuUserRole(java.lang.String)
	 */
	@Override
	public void removeUserRole(String userId) {
		String hqlRole= "delete from TBasisUserRole2 role where role.userId =:userId ";
		Query queryRole = this.getCurrentSession().createQuery(hqlRole);
		queryRole.setParameter("userId", userId);
		queryRole.executeUpdate();
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.mobile.application.dao.IUserDao#getUsers(java.lang.String)
	 */
	public List<?> getUsers(String userCode, String roleId){
		StringBuffer hql = new StringBuffer("select new map(tu.userId as userid,tu.userCode as userCode,tu.userName as realName,tu.TBasisOrg.orgName as orgName) from TBasisUser tu, TBasisOrgRole tor where  tu.isDisable != 'true' and tor.tBasisOrg.orgId=tu.TBasisOrg.orgId and tor.tBasisRole.roleId = '"+roleId+"' ");
		if(userCode != null && !"".equals(userCode)){
			hql.append(" and tu.userCode like '%"+userCode+"%'");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		List<?> list = hqlQuery.list();
		return list;
	}
	
	public void delUserData(String userId){
		String hql = "delete from TBasisUserData t where t.TBasisUser.userId = '"+userId+"'";
		Query query = this.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public List getOrgByRole(String roleId) {
		String hql = "select tor.tBasisOrg.orgId from TBasisOrgRole tor  where tor.tBasisRole.roleId = '"+roleId+"'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		List<?> list = hqlQuery.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override
	public String userBackRoleCount(String userId) {
		String hql = "select new map(count(t.TBasisRole.roleName) as roleCount) from TBasisUserRole t where t.TBasisUser.userId=:userId and t.TBasisRole.roleType<>'1'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("userId", userId);
		Map<String, Long> userCountMap = (Map<String, Long>) hqlQuery.uniqueResult(); // 		
		return userCountMap.get("roleCount").toString();
	}
}
