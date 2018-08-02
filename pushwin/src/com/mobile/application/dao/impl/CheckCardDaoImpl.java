package com.mobile.application.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.ICheckCardDao;
import com.mobile.application.entity.TBasisCheckmodle;

@Repository
public class CheckCardDaoImpl extends BaseDAOImpl<TBasisCheckmodle> implements ICheckCardDao{

	@Override
	public List<?> qrymodle(String modleCode) {
		String hql= "select new map(bc.modleCode as modleCode,bc.modleName as modleName,bc.modlePath as modlePath) from TBasisCheckmodle  bc";
			if(StringUtils.isNotBlank(modleCode)){
				hql += " where bc.modleCode ='"+modleCode+"' ";	
			}
			Query hqlQuery = this.getCurrentSession().createQuery(hql);
			List<?> list = hqlQuery.list();
			return list;
	}

}
