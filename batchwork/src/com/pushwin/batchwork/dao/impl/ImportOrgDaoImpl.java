package com.pushwin.batchwork.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pushwin.batchwork.dao.IImportOrgDao;
import com.pushwin.batchwork.model.TBasisBusBank;
import com.pushwin.batchwork.model.TBasisBusSubbank;
import com.pushwin.batchwork.model.TBasisOrg;
import com.pushwin.batchwork.utils.DateUtil;

@Repository
public class ImportOrgDaoImpl extends BaseDAOImpl<TBasisOrg> implements IImportOrgDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TBasisBusBank> listTBasisBusBank() {
		
		return this.getCurrentSession().createQuery("from TBasisBusBank").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TBasisBusSubbank> listTBasisBusSubbank() {
		return this.getCurrentSession().createQuery("from TBasisBusSubbank").list();
	}

	@Override
	public boolean listTBasisBusBankFlag() {
        String time = DateUtil.batchTimeOrg();
        TBasisBusBank list = (TBasisBusBank) this.getCurrentSession().createQuery("from TBasisBusBank AS A where A.updaTime=:time").setParameter("time", time).setMaxResults(1).uniqueResult();
        return null == list ? false :true ;
	}

	@Override
	public boolean listTBasisBusSubbankFlag() {
		  String time = DateUtil.batchTimeOrg();
		   TBasisBusSubbank list = (TBasisBusSubbank) this.getCurrentSession().createQuery("from TBasisBusSubbank AS A where A.updaTime=:time ").setParameter("time", time).setMaxResults(1).uniqueResult();
		   return null == list ? false :true ;
	}

}
