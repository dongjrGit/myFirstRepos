package com.pushwin.batchwork.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pushwin.batchwork.dao.IImportDictDao;
import com.pushwin.batchwork.model.TBasisDict;
import com.pushwin.batchwork.model.TBasisDictBranch;
import com.pushwin.batchwork.model.TBasisDictCompabymemo;
import com.pushwin.batchwork.model.TBasisDictIgroup;
import com.pushwin.batchwork.model.TBasisDictPlanstore;
import com.pushwin.batchwork.model.TBasisDictPlanstoregood;
import com.pushwin.batchwork.model.TBasisDictPlantype;
import com.pushwin.batchwork.model.TBasisDictStoregoogs;
import com.pushwin.batchwork.model.TBasisDictStores;
@Repository
public class ImportDictDaoImpl  extends BaseDAOImpl<TBasisDict> implements IImportDictDao{

	@Override
	public void saveListDict(List<TBasisDict> listTBasisDict) {
		for (TBasisDict tBasisDict : listTBasisDict) {
			this.saveOrUpdate(tBasisDict);
		}
		
	}

	@Override
	public void saveTBasisDictBranch(List<TBasisDictBranch> listTBasisDictBranch) {
		for (TBasisDictBranch tBasisDictBranch : listTBasisDictBranch) {
			this.saveOrUpdate(tBasisDictBranch);
		}
		
	}

	@Override
	public void saveListTBasisDictCompabymemo(
			List<TBasisDictCompabymemo> listTBasisDictCompabymemo) {
		for (TBasisDictCompabymemo tBasisDictCompabymemo : listTBasisDictCompabymemo) {
			this.saveOrUpdate(tBasisDictCompabymemo);
		}
		
	}

	@Override
	public void saveListTBasisDictPlantype(
			List<TBasisDictPlantype> listTBasisDictPlantype) {
		for (TBasisDictPlantype tBasisDictPlantype : listTBasisDictPlantype) {
			this.saveOrUpdate(tBasisDictPlantype);
		}
		
	}

	@Override
	public void saveListDictTBasisDictPlanstore(
			List<TBasisDictPlanstore> listTBasisDictTBasisDictPlanstore) {
	  for (TBasisDictPlanstore tBasisDictPlanstore : listTBasisDictTBasisDictPlanstore) {
		this.saveOrUpdate(tBasisDictPlanstore);
	}
		
	}

	@Override
	public void saveLisTBasisDictStores(
			List<TBasisDictStores> listTBasisDictStores) {
	 for (TBasisDictStores tBasisDictStores : listTBasisDictStores) {
		this.saveOrUpdate(tBasisDictStores);
	}
		
	}

	@Override
	public void saveLisTBasisDictIgroup(List<TBasisDictIgroup> tBasisDictIgroup) {
		for (TBasisDictIgroup tBasisDictIgroup2 : tBasisDictIgroup) {
			 saveOrUpdate(tBasisDictIgroup2);
		}
		
	}

	@Override
	public void saveLisTplanStoreGood(
			List<TBasisDictPlanstoregood> tBasisDictPlanstoregood) {
		for (TBasisDictPlanstoregood tBasisDictPlanstoregood2 : tBasisDictPlanstoregood) {
			saveOrUpdate(tBasisDictPlanstoregood2);
		}
		
	}

	@Override
	public void saveLisTBasisDictStoregoogs(
			List<TBasisDictStoregoogs> tBasisDictStoregoogs) {
		for (TBasisDictStoregoogs tBasisDictStoregoogs2 : tBasisDictStoregoogs) {
			saveOrUpdate(tBasisDictStoregoogs2);
		}
		
	}

	@Override
	public void saveTBasisDictIgroup(TBasisDictIgroup tBasisDictIgroup) {
		this.saveOrUpdate(tBasisDictIgroup);
		
	}

}
