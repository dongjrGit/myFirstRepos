package com.mobile.application.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.dao.IShootMaterDao;
import com.mobile.application.entity.TBasisShootMater;

@Repository
public class ShootMaterDaoImpl extends BaseDAOImpl<TBasisShootMater> implements IShootMaterDao{

	@Override
	public List<?> qryShootMater(String type) {
		String hql = "select new map(t.materId as materId, t.materPid as materPid, t.materName as materName,t.materLevel as materLevel,t.shootRequire as shootRequire, t.materType as materType,u.userCode as userName,t.creatTime as creatTime) from TBasisShootMater t ,TBasisUser u where u.userId = t.creatUser";
		if(StringUtils.isNotBlank(type)){
			hql += " and t.materType='"+type+"' and t.materLevel = '2' ";
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list =hqlQuery.list();
		return list;
	}

	@Override
	public List<?> qryMaterbypid(String materType, String materName) {
		String hql = "select t.materId from TBasisShootMater t where t.materType=:materType and t.materName=:materName ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("materName", materName);
		hqlQuery.setParameter("materType", materType);
		List<?> list =hqlQuery.list();
		return list;
	}

	@Override
	public List<?> qryShootingDict(String lastUpdateTime) {
			String updateTimeHql = "select new map(max(creatTime) as creatTime) from TBasisShootMater";
			Query updateTimeQuery = this.getCurrentSession().createQuery(updateTimeHql);
			Map<String, String> updateTimeMap = (Map<String, String>) updateTimeQuery.uniqueResult();
			Date updatetime = DateUtil.getDateTime(updateTimeMap.get("creatTime"));
			if(StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime()){
				String hql = "select new map(shoot.materId as materId, shoot.materName as materName, shoot.materPid as materPid,shoot.materType as materType,shoot.shootRequire as shootRequire) from TBasisShootMater shoot";
				Query query = this.getCurrentSession().createQuery(hql);
				return query.list();
			} else {
				return null;
			}
			
		}
	

}
