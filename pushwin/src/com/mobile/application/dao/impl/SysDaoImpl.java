package com.mobile.application.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mobile.application.dao.ISysDao;
import com.mobile.application.entity.TBasisSystem;

@Repository
public class SysDaoImpl extends BaseDAOImpl<TBasisSystem> implements ISysDao{
	@Override
	public List<?> qrySysSetting(){
		String hql = "select new map(id as id, logoTxt as logoTxt, logoImg as logoImg , bankName as bankName) from TBasisSystem";
		List<?> list = this.getCurrentSession().createQuery(hql).list();
		return list;
	}
}
