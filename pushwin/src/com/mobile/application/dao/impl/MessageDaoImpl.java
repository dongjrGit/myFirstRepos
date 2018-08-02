package com.mobile.application.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IMessageDao;
import com.mobile.application.entity.TBasisMessage;

@Repository
public class MessageDaoImpl extends BaseDAOImpl<TBasisMessage> implements IMessageDao {

	@Override
	public List<TBasisMessage> qryUnreadMessage(String userId, String sendUserId) {
		String hql = "from TBasisMessage tbm where tbm.receiveUserId=:userId and tbm.sendUserId=:sendUserId and tbm.isRead = '0'";
		
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId).setParameter("sendUserId", sendUserId);
		List<TBasisMessage> tBasisMessages = query.list();
		return tBasisMessages;
	}
	@Override
	public void updateReadMsg(String userId, String sendUserId) {
		String hql = "update TBasisMessage tbm set tbm.isRead='1' where tbm.receiveUserId=:userId and tbm.sendUserId=:sendUserId and tbm.isRead = '0'";
		Query updateMsgQuery = this.getCurrentSession().createQuery(hql);
		updateMsgQuery.setParameter("userId", userId).setParameter("sendUserId", sendUserId);
		updateMsgQuery.executeUpdate();
	}
	@Override
	public void saveMessage(TBasisMessage tBasisMessage) {
		this.getCurrentSession().save(tBasisMessage);
	}
	
	@Override
	public String qryUserCount(String userCode,String userName) {
		String hql = "select new map(count(userId) as userCount) from TBasisUser t where t.isDisable <> 'true' and t.isAdmin is null ";
		
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
	
	@Override
	public List<?> qryUser(int indexPage, int indexSize, String userCode,String userName) {
		{
			String hql = "select new map(t.userId as id,t.userCode as userCode,t.userName as userName,t.TBasisOrg.orgCode as orgCode,t.TBasisOrg.orgName as orgName) from TBasisUser t where t.isDisable <> 'true' and t.isAdmin is null";
			if(!"".equals(userCode) && userCode != null )
			{
				hql += " and t.userCode like '%"+userCode+"%'";
			}
			if(!"".equals(userName) && userName != null )
			{
				hql +=" and t.userName like '%"+userName+"%'";
			}
			Query hqlQuery = this.getCurrentSession().createQuery(hql);
			hqlQuery.setFirstResult((indexPage)*indexSize);  
			hqlQuery.setMaxResults(indexSize);  
			List<?> list = hqlQuery.list(); 		
			return list;
		}
	}
}
