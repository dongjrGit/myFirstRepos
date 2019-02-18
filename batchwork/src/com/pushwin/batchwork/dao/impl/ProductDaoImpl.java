package com.pushwin.batchwork.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.pushwin.batchwork.dao.IProductDao;
import com.pushwin.batchwork.model.TBasisProduct;
import com.pushwin.batchwork.utils.DateUtil;

@Repository
public class ProductDaoImpl extends BaseDAOImpl<TBasisProduct> implements IProductDao {

	@Override
	public void productOffOnline() {
		String nowDate = DateUtil.formatDate(new Date());
		String onlineHql = "update TBasisProduct set status = '5' where status='3' and onlineTime <= :nowDate and offlineTime >:nowDate";
		this.getCurrentSession().createQuery(onlineHql).setParameter("nowDate", nowDate).executeUpdate();
		String offlineHql = "update TBasisProduct set status = '6' where status='5' and offlineTime <=:nowDate";
		this.getCurrentSession().createQuery(offlineHql).setParameter("nowDate", nowDate).executeUpdate();
	}
}
