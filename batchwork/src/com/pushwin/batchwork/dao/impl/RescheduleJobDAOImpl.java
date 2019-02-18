package com.pushwin.batchwork.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pushwin.batchwork.dao.IRescheduleJobDAO;
import com.pushwin.batchwork.model.HisBantchwork;
import com.pushwin.batchwork.model.TBantchwork;

@Repository
public class RescheduleJobDAOImpl extends BaseDAOImpl<TBantchwork> implements IRescheduleJobDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> query(String taskID){
		String sql = "SELECT TRIGGER_NAME, TRIGGER_GROUP, JOB_NAME, NEXT_FIRE_TIME, PREV_FIRE_TIME, START_TIME, CASE TRIGGER_STATE WHEN 'ACQUIRED' THEN 'Õý³£Ö´ÐÐ' WHEN 'PAUSED' THEN 'ÔÝÍ£' WHEN 'WAITING' THEN 'µÈ´ý' WHEN 'BLOCKED' THEN '×èÈû ' WHEN 'ERROR' THEN '´íÎó' END AS TRIGGER_STATE FROM QRTZ_TRIGGERS";
		Query sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	@Override
	public void delTaskHistory(String taskID){
		String sql = "DELETE FROM HIS_BANTCHWORK WHERE TASK_ID=:taskID AND TO_DATE(TO_CHAR(END_TM,'YYYY-MM-DD'),'YYYY-MM-DD')";
		super.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	@Transactional
	public void addHistory(HisBantchwork hisBantchwork) {
		String hql = "update TBantchwork tbw set ";
		if(null != hisBantchwork.getExeRslt())
			hql += "tbw.lastExerslt = '" + hisBantchwork.getExeRslt() + "',";
		if(null != hisBantchwork.getStartTm())
			hql += "tbw.lastStartTm = '" + hisBantchwork.getStartTm() + "',";
		if(null != hisBantchwork.getEndTm())
			hql += "tbw.lastEndTm = '" + hisBantchwork.getEndTm() + "',";
		if(null != hisBantchwork.getExeUsr())
			hql += "tbw.updateUsre = '" + hisBantchwork.getExeUsr() + "',";
		hql = hql.substring(0, hql.length() - 1);
		hql += " where tbw.bantchworkId = '" + hisBantchwork.getTBantchwork().getBantchworkId() + "'";
		Session session = this.getCurrentSession();
		session.createQuery(hql).executeUpdate();
		session.clear();
		session.save(hisBantchwork);
	}
}
