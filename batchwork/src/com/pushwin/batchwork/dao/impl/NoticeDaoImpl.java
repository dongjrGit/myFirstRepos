package com.pushwin.batchwork.dao.impl;

import org.springframework.stereotype.Repository;

import com.pushwin.batchwork.dao.INoticeDao;
import com.pushwin.batchwork.model.TBasisProduct;

@Repository
public class NoticeDaoImpl extends BaseDAOImpl<TBasisProduct> implements INoticeDao{
	@Override
	public void noticeExpire(){
		String sql = "UPDATE T_BASIS_NOTICE SET EXPIRE_FLAG='1' WHERE to_date(NOTICE_TIME,'YYYY-MM-DD HH24:MI:SS') <  SYSDATE-30";
		this.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
}
