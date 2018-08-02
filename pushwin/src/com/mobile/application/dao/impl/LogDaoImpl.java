package com.mobile.application.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.ILogDao;
import com.mobile.application.entity.TBasisLogDetail;

@Repository
public class LogDaoImpl extends BaseDAOImpl<TBasisLogDetail> implements ILogDao {

	@Override
	public List<?> qryLogType(String modelName) {
		String hql = "select new map(tblt.url as url, tblt.modelName as modelName, tblt.operaName as operaName, tblt.description as description) from TBasisLogType tblt";
		if(StringUtils.isNotBlank(modelName)){
			hql += " where tblt.modelName like '%" + modelName + "%'";
		}
		
		return this.getCurrentSession().createQuery(hql).list();
	}


	@Override
	public List<?> qryLogDetail(int indexPage, int indexSize, String modelName,String userCode, String startTime,String endTime) {
		StringBuffer hql = new StringBuffer("select new map(tld.id as id,tld.TBasisLogType.url as url,tld.TBasisLogType.modelName as modelName,tld.description as description,tld.userCode as userCode,tld.userName as userName,tld.userIp as userIP,tld.updateTime as updateTime) from TBasisLogDetail tld where 1=1 ");
		if(!"".equals(modelName) && modelName != null){
			hql.append(" and tld.TBasisLogType.modelName like '%"+modelName+"%'");
		}
		if(!"".equals(userCode) && userCode != null){
			hql.append(" and tld.userCode like '%"+userCode+"%'");
		}
		if(!"".equals(startTime) && startTime != null)
		{
			hql.append(" and SUBSTR(tld.updateTime,1,10) >= '"+startTime+"'");
		}
		if(!"".equals(endTime) && endTime != null)
		{
			hql.append(" and SUBSTR(tld.updateTime,1,10) <= '"+endTime+"'");
		}
		hql.append(" order by tld.updateTime desc");
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		hqlQuery.setFirstResult((indexPage)*indexSize);
		hqlQuery.setMaxResults(indexSize);
		List<?> list = hqlQuery.list();
		return list;
	}

	@Override
	public List<?> qryLogDetail(String modelName, String userCode,String startTime,String endTime) {
		StringBuffer hql = new StringBuffer("select new map(tld.id as id,tld.TBasisLogType.url as url,tld.TBasisLogType.modelName as modelName,tld.description as description,tld.userCode as userCode,tld.userName as userName,tld.userIp as userIP) from TBasisLogDetail tld where 1=1 ");
		if(!"".equals(modelName) && modelName != null){
			hql.append(" and tld.TBasisLogType.modelName like '%"+modelName+"%'");
		}
		if(!"".equals(userCode) && userCode != null){
			hql.append(" and tld.userCode like '%"+userCode+"%'");
		}
		if(!"".equals(startTime) && startTime != null)
		{
			hql.append(" and SUBSTR(tld.updateTime,1,10) >= '"+startTime+"'");
		}
		if(!"".equals(endTime) && endTime != null)
		{
			hql.append(" and SUBSTR(tld.updateTime,1,10) <= '"+endTime+"'");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		List<?> list = hqlQuery.list();
		return list;

	}


	@Override
	public List<?> getExportLog(String modelName, String userCode,String startTime,String endTime) {
		StringBuffer hql = new StringBuffer("select tld.id from TBasisLogDetail tld where 1=1 ");
		if(!"".equals(modelName) && modelName != null){
			hql.append(" and tld.TBasisLogType.modelName like '%"+modelName+"%'");
		}
		if(!"".equals(userCode) && userCode != null){
			hql.append(" and tld.userCode like '%"+userCode+"%'");
		}
		if(!"".equals(startTime) && startTime != null)
		{
			hql.append(" and SUBSTR(tld.updateTime,1,10) >= '"+startTime+"'");
		}
		if(!"".equals(endTime) && endTime != null)
		{
			hql.append(" and SUBSTR(tld.updateTime,1,10) <= '"+endTime+"'");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		List<?> list = hqlQuery.list();
		return list;
	}


}
