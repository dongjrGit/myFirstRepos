package com.mobile.application.dao.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IAPKDao;
import com.mobile.application.entity.TBasisApk;

@Repository
public class APKDaoImpl extends BaseDAOImpl<TBasisApk> implements IAPKDao {

	@Autowired
	ServletContext servletContext;
	
	@Override
	public List<?> qryApks(int indexPage, int indexSize, String dealName) {
		StringBuffer sql = new StringBuffer("select new map(e.dealId as dealId," +
				"e.dealChannel as dealChannel," +
				"e.dealCode as dealCode," +
				"e.dealName as dealName," +
				"e.dealDescription as dealDescription," +    
				" e.relatedApk as relatedApk," +
				" e.pageApk as pageApk," +
				" e.dealType as dealType," +
				" e.isOffline as isOffline," +
				" e.delFlag as delFlag," +
				" e.updateUserId as updateUserId," +
				" e.updateTime as updateTime," +
				" e.imgPath as imgPath" +
				"  ) from TBasisApk e where e.dealType <> '1' and e.dealType <> '2'");
		
		if(!"".equals(dealName) && dealName != null)
		{
			sql.append(" and e.dealName like'%"+ dealName + "%'");
		}
//		sql.append(" order by e.updateTime desc");
		Query hqlQuery = this.getCurrentSession().createQuery(sql.toString());
		hqlQuery.setFirstResult((indexPage) * indexSize);
		hqlQuery.setMaxResults(indexSize);
		List<Map<String, String>> list = hqlQuery.list();
		//转换业务字典
		Map<String, Map<String, String>> dictMap = (Map<String, Map<String, String>>) servletContext.getAttribute("dictMap");
		Map<String, String> apkType =  dictMap.get("BASIS_APK_TYPE");
		Map<String, String> apkChannel =  dictMap.get("BASIS_APK_CHANNEL");
		for (Map<String, String> item : list) {
			item.put("dealTypeName", apkType.get(item.get("dealType")));
			item.put("dealChannelName", apkChannel.get(item.get("dealChannel")));
		}
		return list;
	}
	
	@Override
	public String qryApksCount(String dealName) {
		StringBuffer sql = new StringBuffer("select new map(count(dealId) as apkCount) from TBasisApk e where 1=1 and e.dealType <> '1' and e.dealType <> '2'");
		
		if(!"".equals(dealName) && dealName != null)
		{
			sql.append(" and e.dealName like'%"+ dealName + "%'");
		}
//		sql.append(" order by e.updateTime desc");
		Query hqlQuery = this.getCurrentSession().createQuery(sql.toString());
		Map<String, Long> apkCountMap = (Map<String, Long>) hqlQuery.uniqueResult();
		return apkCountMap.get("apkCount").toString();
	}
	
	/**
	 * 根据交易编号查询交易本信息 
	 * */
	@Override
	public List<?> qryApkById(String dealId) {
		String hql = "select new map(deal.dealName as uDealName" +
				", deal.dealChannel as uDealChannel" +
				", deal.dealCode as uDealCode" +
				", deal.relatedApk as updApk" +
				", deal.dealDescription as uDealDescription" +
				", deal.dealType as uDealType" +
				", deal.imgPath as dealImg)" +
				" from TBasisApk deal" +
				" where deal.dealId = '" + dealId + "'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list = hqlQuery.list(); 
		return list;
	}

	@Override
	public List<?> qryChannel(String busintypeid) {
		String hql = "select new map(tbd.id.businid as dictId, tbd.businname as businname) from TBasisDict tbd where tbd.id.busintypeid='" + busintypeid + "'";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void removeApkInfo(String dealId) {
		
		String delRoleApkHql = "delete from TBasisRoleApk tbra where tbra.TBasisApk.dealId =:dealId";
		Query delRoleApkQuery = this.getCurrentSession().createQuery(delRoleApkHql).setParameter("dealId", dealId);
		delRoleApkQuery.executeUpdate();
		
		String hql = "delete from TBasisApk tba where tba.dealId =:dealId";
		Query query = this.getCurrentSession().createQuery(hql).setParameter("dealId", dealId);
		query.executeUpdate();
	}

	@Override
	public Long checkDealName(TBasisApk tBasisApk) {
		String hql = " select count(deal.dealName) from TBasisApk deal where deal.dealName = '" + tBasisApk.getDealName() + "' ";
		if(tBasisApk.getDealId()!= null){
		    hql += " and deal.dealId <> '"+tBasisApk.getDealId()+"' ";
		}
		Query query  = this.getCurrentSession().createQuery(hql);
		Long count = (Long) query.uniqueResult();
		return count;
	}
}
