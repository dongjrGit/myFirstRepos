package com.mobile.application.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.dao.IDeviceDao;
import com.mobile.application.entity.TBasisDevice;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.vo.session.SessionVO;


@Repository
public class DeviceDaoImpl extends BaseDAOImpl<TBasisDevice> implements IDeviceDao{
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.dao.IDeviceDao#findDevice(java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<?> findDevice(String deptId, int indexPage, int indexSize, String userCode, String pinCode, String simCode, String userName, String startTime, String endTime) throws ParseException {
		StringBuffer hql = new StringBuffer("select new map(p.reId as reId,p.pinId as pinId,p.simId as simId," +
				"user.userCode as userCode,p.recipientTime as recipientTime,case p.padStatus when '0' then '已入库' when '1' then '已出库' when '2' then '待接收' when '3' then '已保管' end as padStatus," +
				"p.delFlag as delFlag,p.updateTime as updateTime," +
				"p.tBasisOrg.orgName as orgName,p.tBasisOrg.orgId as orgId) from TBasisDevice p left join p.tBasisUser user where p.tBasisOrg.orgId ='"+deptId+"'");
		if(!"".equals(userCode) && userCode !=null)
		{
			hql.append(" and p.tBasisUser.userCode like '%"+userCode+"%'");
		}
		if(!"".equals(pinCode) && pinCode !=null)
		{
			hql.append(" and p.pinId like '%"+pinCode+"%'");
		}
		if(!"".equals(simCode) && simCode !=null)
		{
			hql.append(" and p.simId like '%"+simCode+"%'");
		}
		if(!"".equals(userName) && userName != null)
		{
			hql.append(" and p.tBasisUser.userName like '%"+userName+"%'");
		}
		if(!"".equals(startTime) && startTime != null)
		{
			hql.append(" and SUBSTR(p.recipientTime,1,10) >= :startTime");
		}
		if(!"".equals(endTime) && endTime != null)
		{
			hql.append(" and SUBSTR(p.recipientTime,1,10) <= :endTime");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		if(!"".equals(startTime) && startTime !=null)
		{
			//DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			hqlQuery.setDate("startTime",DateUtil.parseDate(startTime));
		}
		if(!"".equals(endTime) && endTime !=null)
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.parse(endTime);
			hqlQuery.setDate("endTime", DateUtil.parseDate(endTime));
		}
		hqlQuery.setFirstResult((indexPage)*indexSize);  
		hqlQuery.setMaxResults(indexSize);  
		List<?> list = hqlQuery.list();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.dao.IDeviceDao#findDevice(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<?> findDevice(String dept_id, String userCode, String pinCode, String simCode, String userName, String startTime, String endTime) throws ParseException {
		StringBuffer hql = new StringBuffer("select count(*) from TBasisDevice p where p.tBasisOrg.orgId ='"+dept_id+"'");
		if(!"".equals(userCode) && userCode !=null)
		{
			hql.append(" and p.tBasisUser.userCode ='"+userCode+"'");
		}
		if(!"".equals(pinCode) && pinCode !=null)
		{
			hql.append(" and p.pinId ='"+pinCode+"'");
		}
		if(!"".equals(simCode) && simCode !=null)
		{
			hql.append(" and p.simId ='"+simCode+"'");
		}
		if(!"".equals(userName) && userName != null)
		{
			hql.append(" and p.tBasisUser.userName like '%"+userName+"%'");
		}
		if(!"".equals(startTime) && startTime != null)
		{
			hql.append(" and SUBSTR(p.recipientTime,1,10) >= :startTime");
		}
		if(!"".equals(endTime) && endTime != null)
		{
			hql.append(" and SUBSTR(p.recipientTime,1,10) <= :endTime");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		if(!"".equals(startTime) && startTime !=null)
		{
			hqlQuery.setDate("startTime",DateUtil.parseDate(startTime));
		}
		if(!"".equals(endTime) && endTime !=null)
		{
			hqlQuery.setDate("endTime", DateUtil.parseDate(endTime));
		}
		List<?> list = hqlQuery.list();
		return list;
	}

   
	@Override
	public List<?> findOrgTree(SessionVO user,String orgId) {
//		String hql = "";
//		if (!"1".equals(user.getUserType())) {
//			hql = "select new map (org.orgId as id, org.orgPid as pid, concat(concat(concat('', org.orgCode),'-'), org.orgName) as name) from TBasisOrg org where org.orgId in (select uo.TBasisOrg from TBasisUserOrg uo where uo.TBasisOrg.userId='"+user.getUserId()+"' and uo.TBasisOrg.isDisable='1' and uo.TBasisOrg.orgLevel<>'0') ";
//		}else
//		{
//			hql = "select new map (org.orgId as id, org.orgPid as pid, concat(concat(concat('', org.orgCode),'-'), org.orgName) as name) from TBasisOrg org where 1=1 ";
//		}
//		hql = hql + " order by org.orgId";
//		Query hqlQuery = this.getCurrentSession().createQuery(hql);
//		List list = hqlQuery.list();
//		return list;
		
		String sql = "";
		if(!"1".equals(user.getUserType())){
			sql = "SELECT ORG.ORG_ID as id , ORG.ORG_PID as pid, ORG.ORG_NAME as name,ORG.ORG_LEVEL as orgLevel FROM T_BASIS_ORG ORG WHERE ORG.ORG_ID IN (SELECT TUO.ORG_ID FROM T_BASIS_USER_ORG TUO JOIN T_BASIS_USER TU ON TUO.USER_ID = TU.USER_ID JOIN T_BASIS_ORG TBO ON TUO.ORG_ID = TBO.ORG_ID WHERE TU.USER_ID = '"+user.getUserId()+"' AND TBO.IS_DISABLE = '0' AND TBO.ORG_LEVEL != '0')";
		}else if("1".equals(user.getIsAdmin())){
			sql = "SELECT ORG.ORG_ID as id , ORG.ORG_PID as pid, ORG.ORG_NAME as name,ORG.ORG_LEVEL as orgLevel FROM T_BASIS_ORG ORG WHERE 1=1";
		}
		else{
			sql = "SELECT ORG.ORG_ID as id , ORG.ORG_PID as pid, ORG.ORG_NAME as name,ORG.ORG_LEVEL as orgLevel FROM T_BASIS_ORG ORG WHERE ORG.ORG_ID IN (select org_id from t_basis_org start with org_id= '"+orgId+"' connect by prior org_id=org_pid)";
		}
		List<?> list = this.getCurrentSession().createSQLQuery(sql).list();
		List orglist = new ArrayList();
		for(int i = 0;i<list.size();i++){
			Object[] js = (Object[]) list.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", js[0].toString());
			if(js[1] == null){
				map.put("pid", null);
			}else{
				map.put("pid", js[1].toString());
			}
			map.put("name", js[2].toString());
			map.put("orgLevel", js[3].toString());
			orglist.add(map);
		}
		return orglist;
	}
	

	@Override
	public List<?> findOrgByUser(String userId) {
		String hql = "select new map(tu.TBasisOrg.orgId as orgId,tu.TBasisOrg.orgName as orgName) from TBasisUser tu where tu.userId =:userId ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql).setParameter("userId", userId);
		List<?> list = hqlQuery.list();
		return list;
	}
	
	
	@Override
	public List<?> findUser(String orgId, int indexPage, int indexSize,String key) {
		StringBuffer hql = new StringBuffer("select new map(u.userId as id,u.userCode as userCode,u.userName as userName,u.userPhone as userPhone,u.userEmail as userEmail) from TBasisUser u" +
				" where u.TBasisOrg.orgId = '"+orgId+"' and u.isDisable = 'false'");
		if(key != null){
			hql.append(" and u.userName like '%"+key+"%' ");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		hqlQuery.setFirstResult((indexPage)*indexSize);  
		hqlQuery.setMaxResults(indexSize);  
		List<?> list = hqlQuery.list();
		return list;
	}
	
	
	@Override
	public List<?> findUser(String orgId,String key) {
		StringBuffer hql = new StringBuffer("select count(*) from TBasisUser u" +
				" where u.TBasisOrg.orgId = '"+orgId+"' and u.isDisable = 'false' ");
		if(key != null){
			hql.append(" and u.userName like '%"+key+"%' ");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		List<?> list = hqlQuery.list();
		return list;
	}


/////////////////////////////////////////////////////////////////////////////
	/**
	 * 验证用户信息
	 * xuxp
	 * 
	*/
	@Override
	public TBasisUser getUserById(String userCode,String password) {
		String hql = " from TBasisUser u where u.userCode ='"+userCode+"' and u.userPwd='"+password+"' and u.isDisable = 'false'" ;
		return (TBasisUser) this.getCurrentSession().createQuery(hql).uniqueResult();
	}
	/**
	 * 用户手上的设备
	 * xuxp
	 * 
	*/
	@Override
	public List<?> qryDeviceUser(String userId) {
		String hql = " from TBasisDevice p where p.tBasisUser.userId =:userId";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("userId", userId);
		List<?> list = hqlQuery.list();
		return list;
	}


	/**
	 * 根据设备Id查设备信息
	 * xuxp
	 * 
	*/
	@Override
	public TBasisDevice getDeviceById(String deviceId) {
		String hql = " from TBasisDevice u where u.reId =:deviceId " ;
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		hqlQuery.setParameter("deviceId", deviceId);
		TBasisDevice device = (TBasisDevice) hqlQuery.uniqueResult();
		return device;
	}
   /**
     * 添加或更新
     */
    public void save1(TBasisDevice pad) {
        this.getCurrentSession().saveOrUpdate(pad);
        this.getCurrentSession().flush();
    	
    }

    /**
     * 根据员工号查用户信息
     * xuxp
     * 
    */
	@Override
	public TBasisUser getUserByCode(String userCode) {
		String hql = " from TBasisUser u where u.userCode ='"+userCode+"' and u.isDisable = 'false'" ;
		return (TBasisUser) this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	public List<?> findOrgByUser(SessionVO user){
		String hql = "select new map(tu.TBasisOrg.orgId as orgId, tu.TBasisOrg.orgName as orgName) from TBasisUser tu where tu.userId = '"+user.getUserId()+"'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		List<?> org = hqlQuery.list();
		return org;
	}
	

	
	/**
	 * 查询一个机构的所有父机构
	 * @param orgId
	 * @return
	 */
	public List<?> getParentOrg(String orgId){
		String sql = "select org_id from t_basis_org start with org_id= '"+orgId+"' connect by prior org_pid=org_id";
		Query sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		List<?> list = sqlQuery.list();
		return list;
	}

	
	
	/**
	 * 根据PinID查询出历史记录
	 * 梁伯肇
	 * 2015-6-21
	 * @param indexSize 
	 * @param indexPage 
	 * */
	public List<?> findRecord(String pinId, int indexPage, int indexSize) {
		String hql = "select new map(p.operatorTime as operatorTime,p.logId as id,p.operatorUser.userName as userName,p.operatorType as operatorType,p.receiveUser.userName as receiveName ) from TBasisDeviceTransferLog p where p.pinId ='"+pinId+"' Order By p.operatorTime Desc";
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		hqlQuery.setFirstResult((indexPage)*indexSize);  
		hqlQuery.setMaxResults(indexSize);  
		List<?> list = hqlQuery.list();
		return list;
	}
	
	/**
	 * 根据PinID查询出历史记录
	 * 梁伯肇
	 * 2015-6-21
	 * @param indexSize 
	 * @param indexPage 
	 * */
	public List<?> findRecord(String pinId) {
		String hql = "select count(*) from TBasisDeviceTransferLog p where p.pinId ='"+pinId+"'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		List<?> list = hqlQuery.list();
		return list;
	}


}
