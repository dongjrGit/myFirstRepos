package com.mobile.application.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IDebitDao;
import com.mobile.application.entity.TBasisDebit;

@Repository
public class DebitDaoImpl extends BaseDAOImpl<TBasisDebit> implements IDebitDao{
	@Override
	public List<?> qryDebitCheckList(int indexPage, int indexSize,String name,String status, String startTime, String endTime,String accountType) {
		String hql = "select new map(tbd.id as id, tbd.name as name, tbd.idNo as idNo, tbd.phone as homePhone, case tbd.status when '1' then '待审核' when '2' then '审核通过' when '3' then '审核不通过' end as status, tbd.updateTime as updateTime, u.userId as userId, u.userCode as userCode, u.userName as userName, d.businname as accountType) from TBasisDebit tbd,TBasisUser u,TBasisDict d where u.userId = tbd.userId and d.id.busintypeid='ACCOUNT' and d.id.businid= tbd.accountType ";
		if(StringUtils.isNotBlank(name)){
			hql += " and  u.userName='"+name+"'";
		}
		if(StringUtils.isNotBlank(status)){
			hql += " and  tbd.status='"+status+"'";
		}
		if(StringUtils.isNotBlank(startTime)){
			hql += " and  tbd.updateTime >= '"+startTime+"'";
		}
		if(StringUtils.isNotBlank(endTime)){
			hql += " and  tbd.updateTime<='"+endTime+"'";
		}
		if(StringUtils.isNotBlank(accountType)){
			hql += " and  tbd.accountType='"+accountType+"'";
		}
		Query query = this.getCurrentSession().createQuery(hql);
		query.setFirstResult((indexPage)*indexSize);
		query.setMaxResults(indexSize);
		return query.list();
	}

	@Override
	public String qryDebitCheckCount(String name,String status, String startTime, String endTime,String accountType) {
		String hql = "select new map(count(tbd.id) as tbdcount) from TBasisDebit tbd ,TBasisUser u where u.userId = tbd.userId";
		if(StringUtils.isNotBlank(name)){
			hql += " and  u.userName='"+name+"'";
		}
		if(StringUtils.isNotBlank(status)){
			hql += " and  tbd.status='"+status+"'";
		}
		if(StringUtils.isNotBlank(startTime)){
			hql += " and  tbd.updateTime >= '"+startTime+"'";
		}
		if(StringUtils.isNotBlank(endTime)){
			hql += " and  tbd.updateTime<='"+endTime+"'";
		}
		if(StringUtils.isNotBlank(accountType)){
			hql += " and  tbd.accountType='"+accountType+"'";
		}
		Query query = this.getCurrentSession().createQuery(hql);
		Map<String, Long> userCountMap = (Map<String, Long>) query.uniqueResult(); // 		
		return userCountMap.get("tbdcount").toString();
	}

	@Override
	public List<?> qryDebitCheckRecord(String debitId) {
		String hql = "select new map(tcc.id as id, case tcc.checkResult when '1' then '待审核' when '2' then '审核通过' when '3' then '审核不通过' end as checkResult, tcc.checkOpinion as checkOpinion, tbu.userCode as userCode, tbu.userName as userName, tcc.updateTime as updateTime) from TBasisDebitCheckrecord tcc, TBasisUser tbu where tcc.updateUser=tbu.userId and tcc.TBasisDebit.id=:debitId order by tcc.updateTime";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("debitId", debitId);
		return query.list();
	}

}
