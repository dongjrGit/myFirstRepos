package com.mobile.application.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IOrgDao;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.vo.session.SessionVO;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisUserOrg;

@Repository
public class OrgDaoImpl extends BaseDAOImpl<TBasisOrg> implements IOrgDao {

	
	@Override
	public List<?> qryOrgTree(SessionVO user,String orgId){
		String hql = "";
		if("1".equals(user.getIsAdmin())){
			hql = "select new map (org.orgId as id, org.orgPid as pid,org.orgName as name,org.orgLevel as orgLevel, org.orgCode as orgCode) from TBasisOrg org order by org.orgName";
		}
		else{
			hql = "select distinct new map (org.orgId as id, org.orgPid as pid,org.orgName as name,org.orgLevel as orgLevel, org.orgCode as orgCode) from TBasisUserData tbud left join tbud.TBasisOrg org where tbud.TBasisUser.userId='" + user.getUserId() + "' order by org.orgName";
		}
	List<?> list = this.getCurrentSession().createQuery(hql).list();
	return list;
	}

	@Override
	public List<?> qryOrgByID(String orgId) {
		TBasisOrg org = (TBasisOrg)this.getByID(TBasisOrg.class, orgId);
		String hql = "";
		if(org.getOrgPid() == null){
			hql = "select new map(org.orgPid as pOrgId, org.orgName as orgName,org.orgDesc as orgDesc,org.orgName as orgCmd,org.orgCode as orgCode, org.orgId as orgId) from TBasisOrg org where org.orgId = '"+orgId+"'";
		}
		else{
			hql ="select new map(porg.orgName as pOrgName, porg.orgCode as pOrgCode, org.orgPid as pOrgId, org.orgName as orgName,org.orgDesc as orgDesc,org.orgName as orgCmd,org.orgCode as orgCode, org.orgId as orgId) from TBasisOrg org, TBasisOrg porg where org.orgPid=porg.orgId and org.orgId ='"+orgId+"'";
		}		
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list = hqlQuery.list();	
		return list;
	}
	
	
	/**
	 * 根据机构ID查询出机构下人员信息
	 * 梁伯肇
	 * 2014-3-13
	 * */
	@Override
	public List<?> listUser(String deptId,String key,int indexPage,int indexSize,SessionVO user)
	{
		StringBuffer hql = new StringBuffer("");
		if(!"".equals(key) && key != null)
		{
		  hql.append("select new map (user.userId as userid ,user.TBasisOrg.orgId as orgId,user.userName as name,user.userCode as loginname,user.userEmail as email, user.userPhone as telephone) from TBasisUser user where user.isDisable = 'false' ");
		}else
		{
			hql.append("select new map (user.userId as userid ,user.TBasisOrg.orgId as orgId,user.userName as name,user.userCode as loginname,user.userEmail as email, user.userPhone as telephone) from TBasisUser user where user.isDisable = 'false' ");
		}
		if(!"".equals(deptId) && deptId != null)
		{
			hql.append(" and user.TBasisOrg.orgId = '"+deptId+"' ");
		}
		hql.append(" order by user.userCode asc");
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		if(!"".equals(key) && key != null)
		{
		hqlQuery.setParameter("key", "%"+key+"%");
		}
		hqlQuery.setFirstResult((indexPage)*indexSize);  
		hqlQuery.setMaxResults(indexSize);  
		List<?> list = hqlQuery.list();
		return list;
	}
	/**
	 * 根据机构ID查询出机构下人员信息(查询总页数)
	 * 梁伯肇
	 * 2014-3-13
	 * */
	@Override
	public List<?> listUser(String deptId,String key,SessionVO user)
	{
		StringBuffer hql = new StringBuffer("");
		if(!"".equals(key) && key != null)
		{
		  hql.append("select new map (user.userId as userid ,user.TBasisOrg.orgId as orgId,user.userName as name,user.userCode as loginname,user.userEmail as email, user.userPhone as telephone) from TBasisUser user where user.isDisable = 'false' ");
		}else
		{
			hql.append("select new map (user.userId as userid ,user.TBasisOrg.orgId as orgId,user.userName as name,user.userCode as loginname,user.userEmail as email, user.userPhone as telephone) from TBasisUser user where user.isDisable = 'false' ");
		}
		if(!"".equals(deptId) && deptId != null)
		{
			hql.append(" and user.TBasisOrg.orgId = '"+deptId+"' ");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		if(!"".equals(key) && key != null)
		{
		hqlQuery.setParameter("key", "%"+key+"%");
		}
		List<?> list = hqlQuery.list();
		return list;
	}
	
	/**
	 * 同一节点下验证机构名称是否相同
	 * 梁伯肇
	 * 2014-3-18
	 * @param orgId 
	 * */
	@Override
	public Long checkOrgNameDouble(String name)
	{
		String hql = " select count(org.orgId) from TBasisOrg org where org.orgName =:name ";
		Query query  = this.getCurrentSession().createQuery(hql).setParameter("name", name);
		Long count = (Long) query.uniqueResult();
		return count;
	}
	
	
	/**
	 * 根据ID以及机构Code 查询是否重复机构ID
	 * */
	@Override
	public Long checkOrgCodeDouble(String orgCode) {
		String hql = "select count(org.orgId) from TBasisOrg org where org.orgCode =:orgCode ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql).setParameter("orgCode", orgCode);
		Long count = (Long) hqlQuery.uniqueResult();
		return count;
	}
	
	
	/**
	 * 根据ID以及机构Code 查询是否重复机构ID
	 * */
	@Override
	public List<?> findOrgByUser(String userId) {
		String hql = "select tu.TBasisOrg.orgId from TBasisUser tu where tu.userId =:userId ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql).setParameter("userId", userId);
		List<?> list = hqlQuery.list();
		return list;
	}
	
	/**
	 * 根据ID以及机构Code 查询是否重复机构ID
	 * */
	@Override
	public TBasisOrg findByCode(String orgCode) {
		String hql = "from TBasisOrg org where org.orgCode =:orgCode ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql).setParameter("orgCode", orgCode);
		List<?> list = hqlQuery.list();
		TBasisOrg org =null;
		if(list.size() > 0)
		{
			org = (TBasisOrg) list.get(0);
		}
		return org;
	}
	
	
	/**
	 * 及时更新新增机构
	 * 梁伯肇
	 * 2014-3-14
	 * */
	@Override
	public void addOrg(String userid,String mid)
	{
		if(StringUtils.isNotBlank(userid)){
			TBasisUser user = new TBasisUser();
			user.setUserId(userid);
			TBasisOrg org = new TBasisOrg();
			org.setOrgId(mid);
			TBasisUserOrg orgMenu = new TBasisUserOrg();
			orgMenu.setTBasisOrg(org);
			orgMenu.setTBasisUser(user);
			this.save(TBasisUserOrg.class, orgMenu);
		}
	}
	
	/**
	 * 根据用户查询用户的数据权限
	 * 
	 * @author taosh
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TBasisOrg> queryOrgsByUser(SessionVO user) {
		List<TBasisOrg> emmOrg = new ArrayList<TBasisOrg>();
		String hql = "";
		if (null != user) {
			if (!"1".equals(user.getUserType())) {
				hql ="select uo.tBasisUser from TBasisUserOrg uo where uo.tBasisUser.userId='"+user.getUserId()
						+ "' and uo.tBasisOrg.isDel='1' and uo.tBasisOrg.orgLevel<>'0'";
			} else {
				hql = "from TBasisOrg";
			}
			Query query = this.getCurrentSession().createQuery(hql);
			emmOrg = query.list();
		}
		return emmOrg;
	}
	
	public long queryRoleHasUser(String orgId){
		String hql = " select count(ur.userId) from TBasisUser ur where ur.TBasisOrg.orgId=:orgId and ur.isDisable = 'false'";
		Query query  = this.getCurrentSession().createQuery(hql);
		Long count = (Long) query.setString("orgId", orgId).uniqueResult();
		return count;		
	}
	
	public long queryOrgHasDevice(String orgId){
		String hql = "select count(*) from TBasisDevice td where td.tBasisOrg.orgId = :orgId and td.delFlag = 'normal'";
		Query query  = this.getCurrentSession().createQuery(hql);
		Long count = (Long) query.setString("orgId", orgId).uniqueResult();
		return count;	
	}

	
	/**
	 * 根据ID删除机构
	 * 梁伯肇
	 * 2014-3-14
	 * */
	public void delOrg(String orgId)
	{	
		String sqlRole = "delete from TBasisOrgRole tor where tor.tBasisOrg = '"+orgId+"'";
		String sqlDa = "delete from TBasisUserData tud where tud.TBasisOrg = '"+orgId+"'";
		String sqlUo = "delete from TBasisUserOrg tuo where tuo.TBasisOrg = '"+orgId+"'";
		String sql = "delete from TBasisOrg to where to.orgId = '"+orgId+"'";
		Query queryRole  = this.getCurrentSession().createQuery(sqlRole);
		Query queryData = this.getCurrentSession().createQuery(sqlDa);
		Query queryUo  = this.getCurrentSession().createQuery(sqlUo);
		Query query  = this.getCurrentSession().createQuery(sql);
		queryRole.executeUpdate();
		queryData.executeUpdate();
		queryUo.executeUpdate();
		query.executeUpdate();
	}
	
	
	/**
	 * 校验登陆名是否存在
	 * 梁伯肇
	 * 2014-3-18
	 * */
	public Long loginNameDouble(String name)
	{
		String hql = " select count(u.userId) from TBasisUser u where u.userCode =:name ";
		Query query  = this.getCurrentSession().createQuery(hql).setParameter("name", name);
		Long count = (Long) query.uniqueResult();
		return count;
	}
	
	/**
	 * 根据机构ID添加用户
	 * 梁伯肇
	 * 2014-3-13
	 * */
	public void userSaveOrUpdata(TBasisUser user)
	{
		this.save(TBasisUser.class, user);
	}
	
	@SuppressWarnings("unchecked")
	public List<TBasisOrg> getAll(String userId){
		String hql = "select distinct new com.mobile.application.entity.TBasisOrg (tb.orgCode ,tb.orgName ,tb.orgDesc ,tb.orgPid ,tb.orgLevel ,tb.isDisable ,tb.orgAdd ,tb.updateUser ,tb.updateTime,tb.column1,tb.column2 ) from TBasisOrg tb,TBasisUserData ud where 1=1 ";
        if(!"".equals(userId)){
			hql+=" and ud.TBasisOrg.orgId=tb.orgId and ud.TBasisUser.userId= '"+userId+"' ";
		}
		Query query  = this.getCurrentSession().createQuery(hql);
		List<TBasisOrg> list = query.list();
		return list;
	}
	
	public List<?> queryOrg(SessionVO sessionVo) {
		String hql = "";
		if (!"1".equals(sessionVo.getUserType())) {
			hql = "select new map (org.orgId as id, org.orgPid as pid, concat(concat(concat('', org.orgCode),'-'), org.orgName) as name, " +
					") from TBasisOrg org where org.orgCode in (select uo.tBasisOrg from TBasisUserOrg uo " +
					"where uo.tBasisUser.userCode='"+sessionVo.getUserCode()+"' and uo.tBasisOrg.isDel='1' and uo.tBasisOrg.orgLevel<>'0') order by org.orgCode";
		}else
		{
			hql = "select new map (org.orgId as id, org.orgPid as pid, concat(concat(concat('', org.orgCode),'-'), org.orgName) as name) from TBasisOrg org where 1=1 order by org.orgCode";
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List list = hqlQuery.list();
		return list;
	}

	@Override
	public List<?> queryOrgByRoleId(String roleId) {
		String hql = " Select ur.tBasisOrg.orgId from TBasisOrgRole ur where ur.tBasisRole.roleId=:roleId";
		Query query = this.getCurrentSession().createQuery(hql).setParameter("roleId", roleId);
		List<?> list=query.list();
		return list;
	}
	
	@Override
	public List<?> qryOrgByProductId(String productId) {
		String hql = " Select po.TBasisOrg.orgId from TBasisProductOrg po where po.TBasisProduct.id=:productId";
		Query query = this.getCurrentSession().createQuery(hql).setParameter("productId", productId);
		List<?> list=query.list();
		return list;
	}

	@Override
	public List<String> qryUserDataOrg(String userId) {
		String hql = "select tbud.TBasisOrg.orgId from TBasisUserData tbud where tbud.TBasisUser.userId =:userId";
		Query query = this.getCurrentSession().createQuery(hql).setParameter("userId", userId);
		return query.list();
	}

	@Override
	public void deleteUserDataOrg(String userId) {
		String hql = "delete from TBasisUserData tbud where tbud.TBasisUser.userId='" + userId + "'";
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}
}
