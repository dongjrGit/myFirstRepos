package com.mobile.application.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.INoticeDao;
import com.mobile.application.entity.TBasisNotice;
import com.mobile.application.entity.TBasisUser;

@Repository
public class NoticeDaoImpl extends BaseDAOImpl<TBasisNotice> implements INoticeDao {

	@Override
	public List<?> queryNotice(TBasisUser user,String startTime, String endTime, String userCode, String userName, String noticeType, String orgId) {
		String sql = "select distinct BN.NOTICE_ID as noticeId,CASE bn.NOTICE_TYPE WHEN '1' THEN '公告' WHEN '2' THEN '消息' ELSE '' END as noticeType,bn.NOTICE_TITLE as noticeTitle,bn.NOTICE_TIME as noticeTime,u1.USER_CODE as userCode  from T_BASIS_NOTICE BN LEFT JOIN T_BASIS_ROLE_NOTICE BRN on BRN.NOTICE_ID=bn.NOTICE_ID LEFT JOIN T_BASIS_NOTICE_PUSHLIST bnp ON BNP.NOTICE_ID=BN.NOTICE_ID LEFT JOIN T_BASIS_USER u ON u.USER_ID=bnp.USER_ID LEFT JOIN T_BASIS_USER u1 ON u1.user_id=bn.CREATE_USER LEFT JOIN T_BASIS_ORG o ON u.ORG_ID=o.ORG_ID";
		sql +=" WHERE 1=1";
		if(StringUtils.isNotBlank(startTime)){
			sql +=" and bn.NOTICE_TIME>='"+startTime+"' ";
			}
		if(StringUtils.isNotBlank(endTime)){
			sql +=" and bn.NOTICE_TIME<='"+endTime+"' ";
			}
		if(StringUtils.isNotBlank(noticeType)){
		sql +=" and bn.NOTICE_TYPE='"+noticeType+"' ";
		}
		if(StringUtils.isNotBlank(userName)){
		sql +=" and u.user_name='"+userName+"' ";
	    }
		if(StringUtils.isNotBlank(userCode)){
		sql +=" and u.USER_CODE='"+userCode+"' ";
        }
		if(StringUtils.isNotBlank(orgId)){
			sql +=" and BRN.ORG_ID='"+orgId+"' ";
		}else if(StringUtils.isBlank(user.getIsAdmin())){
		sql +=" AND BRN.ORG_ID in (select ud.ORG_ID from T_BASIS_USER_DATA ud where ud.USER_ID = '"+user.getUserId()+"')";
		}
		sql +=" order by bn.NOTICE_TIME desc";
		Query sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		List<?> list = sqlQuery.list(); 
		return list;
		
			}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> queryNotice(TBasisUser user, int pageIndex, int pageSize,String startTime, String endTime, String userCode, String userName, String noticeType, String orgId) {
		String sql = "select distinct BN.NOTICE_ID as noticeId,CASE bn.NOTICE_TYPE WHEN '1' THEN '公告' WHEN '2' THEN '通知' ELSE '' END as noticeType,bn.NOTICE_TITLE as noticeTitle,bn.NOTICE_TIME as noticeTime,u1.USER_CODE as userCode  from T_BASIS_NOTICE BN LEFT JOIN T_BASIS_ROLE_NOTICE BRN on BRN.NOTICE_ID=bn.NOTICE_ID LEFT JOIN T_BASIS_NOTICE_PUSHLIST bnp ON BNP.NOTICE_ID=BN.NOTICE_ID LEFT JOIN T_BASIS_USER u ON u.USER_ID=bnp.USER_ID LEFT JOIN T_BASIS_USER u1 ON u1.user_id=bn.CREATE_USER LEFT JOIN T_BASIS_ORG o ON u.ORG_ID=o.ORG_ID";
		sql +=" WHERE 1=1";
		if(StringUtils.isNotBlank(startTime)){
			sql +=" and bn.NOTICE_TIME>='"+startTime+"' ";
			}
		if(StringUtils.isNotBlank(endTime)){
			sql +=" and bn.NOTICE_TIME<='"+endTime+"' ";
			}
		if(StringUtils.isNotBlank(noticeType)){
		sql +=" and bn.NOTICE_TYPE='"+noticeType+"' ";
		}
		if(StringUtils.isNotBlank(userName)){
		sql +=" and u.user_name='"+userName+"' ";
	    }
		if(StringUtils.isNotBlank(userCode)){
		sql +=" and u.USER_CODE='"+userCode+"' ";
        }
		if(StringUtils.isNotBlank(orgId)){
			sql +=" and BRN.ORG_ID='"+orgId+"' ";
		}else if(StringUtils.isBlank(user.getIsAdmin())){
//			sql +=" and bn.NOTICE_TYPE='2' and BNP.USER_ID ='"+user.getUserId()+"'";
		sql +=" AND BRN.ORG_ID in (select ud.ORG_ID from T_BASIS_USER_DATA ud where ud.USER_ID = '"+user.getUserId()+"')";
		}
		sql +=" order by bn.NOTICE_TIME desc";
		Query sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setFirstResult((pageIndex)*pageSize);  
		sqlQuery.setMaxResults(pageSize);  
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = sqlQuery.list(); 

		return list;
	}

	@Override
	public List<?> queryRoleByOrg(int pageIndex, int pageSize, String orgIds) {
		String hql = "select distinct new map(tor.tBasisRole.roleId as roleId,tor.tBasisRole.roleName as roleName,tor.tBasisRole.roleDesc as roleDesc) from TBasisOrgRole tor where tor.tBasisOrg.orgId in (:orgIds)";
		 Query hqlQuery = this.getCurrentSession().createQuery(hql);
		 hqlQuery.setParameterList("orgIds", orgIds.split(","));
		 hqlQuery.setFirstResult((pageIndex)*pageSize);  
		 hqlQuery.setMaxResults(pageSize);  
		 List<?> list = hqlQuery.list(); 
		 return list;
	}

	@Override
	public String queryRoleByOrg(String orgIds) {
		String hql = "select new map(count(distinct tor.tBasisRole.roleId) as count) from TBasisOrgRole tor where tor.tBasisOrg.orgId in (:orgIds)";
		 Query hqlQuery = this.getCurrentSession().createQuery(hql);
		 hqlQuery.setParameterList("orgIds", orgIds.split(","));
		 Map<String, Long> countMap = (Map<String, Long>) hqlQuery.uniqueResult(); 
		 return countMap.get("count") + "";
	}

	public List<?> queryAllNotice(){
		String hql = "select e.noticeId as noticeId,e.noticeTitle as noticeTitle,e.noticeTime as noticeTime,e.noticeContent as noticeContent from TBasisNotice e where 1=1 order by e.noticeTime desc";
		 Query hqlQuery = this.getCurrentSession().createQuery(hql);
		 hqlQuery.setMaxResults(5);
		 List<?> list = hqlQuery.list(); 
		 return list;
	}

	@Override
	public List<?> queryUserByRole(int indexPage, int indexSize, String orgIds,
			String roleIds, String userName, String userCode) {
		String hql = "select distinct new map(t.userId as id,t.userCode as userCode,t.userName as userName,tbo.orgCode as orgCode,tbo.orgName as orgName) from TBasisUserRole tbur join tbur.TBasisRole tbr join tbur.TBasisUser t join t.TBasisOrg tbo where t.isDisable <> 'true'";
		if(StringUtils.isNotBlank(userCode))
		{
			hql += " and t.userCode = '"+userCode+"'";
		}
		if(StringUtils.isNotBlank(userName))
		{
			hql +=" and t.userName like '%"+userName+"%'";
		}
		if(StringUtils.isNotBlank(orgIds)){
			hql += " and tbo.orgId in(:orgIds)";
		}
		if(StringUtils.isNotBlank(roleIds)){
			hql += " and tbr.roleId in(:roleIds)";
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		if(StringUtils.isNotBlank(orgIds)){
			hqlQuery.setParameterList("orgIds", orgIds.split(","));
		}
		if(StringUtils.isNotBlank(roleIds)){
			hqlQuery.setParameterList("roleIds", roleIds.split(","));
		}
		if(0 != indexSize){
			hqlQuery.setFirstResult((indexPage)*indexSize);  
			hqlQuery.setMaxResults(indexSize);  
		}
		List<?> list = hqlQuery.list(); 		
		return list;
	}
	
	@Override
	public List<?> queryUserByUserId(String userIds) {
		String hql = "select distinct new map(t.userId as id,t.userCode as userCode,t.userName as userName) from TBasisUser t where t.userId in (:userIds)";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameterList("userIds", userIds.split(","));
		return hqlQuery.list(); 		
	}
	
	@Override
	public String queryUserCount(String orgIds,
			String roleIds, String userName, String userCode) {
		String hql = "select new map(count(distinct t.userId) as userCount) from TBasisUserRole tbur join tbur.TBasisRole tbr join tbur.TBasisUser t join t.TBasisOrg tbo where t.isDisable <> 'true'";
		if(StringUtils.isNotBlank(userCode))
		{
			hql += " and t.userCode = '"+userCode+"'";
		}
		if(StringUtils.isNotBlank(userName))
		{
			hql +=" and t.userName like '%"+userName+"%'";
		}
		if(StringUtils.isNotBlank(orgIds)){
			hql += " and tbo.orgId in(:orgIds)";
		}
		if(StringUtils.isNotBlank(roleIds)){
			hql += " and tbr.roleId in(:roleIds)";
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		if(StringUtils.isNotBlank(orgIds)){
			hqlQuery.setParameterList("orgIds", orgIds.split(","));
		}
		if(StringUtils.isNotBlank(roleIds)){
			hqlQuery.setParameterList("roleIds", roleIds.split(","));
		}
		Map<String, Long> userCountMap = (Map<String, Long>) hqlQuery.uniqueResult(); 		
		return userCountMap.get("userCount") + "";
	}
	
	@Override
	public List<?> qryOrgRoleRalate(String orgIds, String roleIds){
		String hql = "select new map(tbor.id as id, tbor.tBasisRole.roleId as roleId, tbor.tBasisOrg.orgId as orgId) from TBasisOrgRole tbor where tbor.tBasisRole.roleId in(:roleIds) and tbor.tBasisOrg.orgId in(:orgIds)";
		Query query = this.getCurrentSession().createQuery(hql).setParameterList("roleIds", roleIds.split(",")).setParameterList("orgIds", orgIds.split(","));
		return query.list();
	}

	@Override
	public List<?> qryNoticeList(String userId, String noticeType, String pageIndex, String indexSize) {
		String hql = "select new map(np.isRead as isRead, tn.noticeTitle as noticeTitle, tn.noticeContent as noticeContent, tn.noticeTime as noticeTime, np.id as pushNoticeId, tn.noticeType as noticeType, tu.userCode as userCode, tu.userName as userName) from TBasisNoticePushlist np join np.TBasisNotice tn join tn.TBasisUser tu where np.userId=:userId and tn.noticeType=:noticeType and tn.expireFlag is null order by tn.noticeTime desc";
		Query query =  this.getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId).setParameter("noticeType", noticeType);
		query.setFirstResult((Integer.parseInt(pageIndex))*Integer.parseInt(indexSize));  
		query.setMaxResults(Integer.parseInt(indexSize)); 
		return query.list();
	}
	
	@Override
	public String qryNoticeCount(String userId, String noticeType, boolean readFlag) {
		String hql = "select new map(count(np.id) as noticeCount) from TBasisNoticePushlist np join np.TBasisNotice tn where np.userId=:userId and tn.expireFlag is null ";
		if(StringUtils.isNotBlank(noticeType)){
			hql += "and tn.noticeType=:noticeType ";
		}
		if(readFlag){
			hql += "and np.isRead is null ";
		}
		Query query =  this.getCurrentSession().createQuery(hql);
		if(StringUtils.isNotBlank(noticeType)){
			query.setParameter("noticeType", noticeType);
		}
		query.setParameter("userId", userId);
		Map<String, Long> noticeCount = (Map<String, Long>) query.uniqueResult();
		return noticeCount.get("noticeCount") + "";
	}
	
	@Override
	public void readNotice(String pushNoticeId) {
		String hql = "update TBasisNoticePushlist set isRead='1' where id='" + pushNoticeId + "'";
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<?> getPushListByNoticeId(String noticeId) {
		String hql="select u.userName from TBasisNoticePushlist p ,TBasisUser u  where u.userId=p.userId and p.TBasisNotice.noticeId=:noticeId";
		Query query =  this.getCurrentSession().createQuery(hql);
		query.setParameter("noticeId", noticeId);
		return query.list();
	}

	@Override
	public List<?> qryNoRedNoticeList(String userId, String noticeType) {
		//String hql = "select new map(np.isRead as isRead, tn.noticeTitle as noticeTitle, tn.noticeContent as noticeContent, tn.noticeTime as noticeTime, np.id as pushNoticeId, tn.noticeType as noticeType, tu.userCode as userCode, tu.userName as userName) from TBasisNoticePushlist np join np.TBasisNotice tn join tn.TBasisUser tu where np.userId=:userId and tn.noticeType=:noticeType and tn.expireFlag is null and np.isRead is null  order by tn.noticeTime desc";
		//信息功耗合体
		String hql = "select new map(np.isRead as isRead, tn.noticeTitle as noticeTitle, tn.noticeContent as noticeContent, tn.noticeTime as noticeTime, np.id as pushNoticeId, tn.noticeType as noticeType, tu.userCode as userCode, tu.userName as userName) from TBasisNoticePushlist np join np.TBasisNotice tn join tn.TBasisUser tu where np.userId=:userId and tn.expireFlag is null order by tn.noticeTime desc";
		Query query =  this.getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId);
		//.setParameter("noticeType", noticeType);
		return query.list();
	}
}
