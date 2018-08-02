package com.mobile.application.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.ICreditDao;
import com.mobile.application.entity.TBasisCredit;

@Repository
public class CreditDaoImpl  extends BaseDAOImpl<TBasisCredit> implements ICreditDao{

	@Override
	public List<?> qryCreditCheckList(int indexPage, int indexSize, String name,String creditKind, String status, String startTime, String endTime) {
		String hql = "select new map(tbc.id as id, tbc.name as name, tbc.idNo as idNo, tbc.homePhone as homePhone, case tbc.status when '1' then '待审核' when '2' then '审核通过' when '3' then '审核不通过' end as status, tbc.updateTime as updateTime, tbc.TBasisUser.userId as userId, tbc.TBasisUser.userCode as userCode, tbc.TBasisUser.userName as userName,d.businname as creditKind) from TBasisCredit tbc , TBasisDict d where d.id.busintypeid='CREDIT_CARD_TYPE' and d.id.businid= tbc.creditKind ";
		if(StringUtils.isNotBlank(name)){
			hql += " and  tbc.TBasisUser.userName='"+name+"'";
		}
		if(StringUtils.isNotBlank(creditKind)){
			hql += " and  tbc.creditKind='"+creditKind+"'";
		}
		if(StringUtils.isNotBlank(status)){
			hql += " and  tbc.status='"+status+"'";
		}
		if(StringUtils.isNotBlank(startTime)){
			hql += " and  tbc.updateTime >= '"+startTime+"'";
		}
		if(StringUtils.isNotBlank(endTime)){
			hql += " and  tbc.updateTime<='"+endTime+"'";
		}
		Query query = this.getCurrentSession().createQuery(hql);
		query.setFirstResult((indexPage)*indexSize);
		query.setMaxResults(indexSize);
		return query.list();
	}

	@Override
	public String qryCreditCheckCount(String name,String creditKind, String status, String startTime, String endTime) {
		String hql = "select new map(count(tbc.id) as tbccount) from TBasisCredit tbc where  1=1 ";
		if(StringUtils.isNotBlank(name)){
			hql += " and  tbc.TBasisUser.userName='"+name+"'";
		}
		if(StringUtils.isNotBlank(creditKind)){
			hql += " and  tbc.creditKind='"+creditKind+"'";
		}
		if(StringUtils.isNotBlank(status)){
			hql += " and  tbc.status='"+status+"'";
		}
		if(StringUtils.isNotBlank(startTime)){
			hql += " and  tbc.updateTime >= '"+startTime+"'";
		}
		if(StringUtils.isNotBlank(endTime)){
			hql += " and  tbc.updateTime<='"+endTime+"'";
		}
		Query query = this.getCurrentSession().createQuery(hql);
		Map<String, Long> userCountMap = (Map<String, Long>) query.uniqueResult(); // 		
		return userCountMap.get("tbccount").toString();
	}

	@Override
	public List<?> qrycreditCheckRecord(String creditId) {
		String hql = "select new map(tcc.id as id, case tcc.checkResult when '1' then '待审核' when '2' then '审核通过' when '3' then '审核不通过' end as checkResult, tcc.checkOpinion as checkOpinion, tbu.userCode as userCode, tbu.userName as userName, tcc.updateTime as updateTime) from TBasisCreditCheckrecord tcc, TBasisUser tbu where tcc.updateUser=tbu.userId and tcc.TBasisCredit.id=:creditId order by tcc.updateTime";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("creditId", creditId);
		return query.list();
	}
}
