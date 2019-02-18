package com.pushwin.batchwork.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pushwin.batchwork.dao.IImportProInfoDao;
import com.pushwin.batchwork.model.TBasisiBusSpecialCostAdd;

@Repository
public class ImportProInfoImpl extends BaseDAOImpl<TBasisiBusSpecialCostAdd> implements IImportProInfoDao{

	@Override
	public void deleteProInfo() {
		this.getCurrentSession().createSQLQuery("DELETE FROM T_BASIS_BUS_SPECIALCOST_ADD")
		.executeUpdate();
		
	}

	@Override
	public void SaveProInfo(List<TBasisiBusSpecialCostAdd> listProInfo) {
		for (TBasisiBusSpecialCostAdd tBasisProInfo : listProInfo) {
			this.save(tBasisProInfo);
		}
		
	}

}
