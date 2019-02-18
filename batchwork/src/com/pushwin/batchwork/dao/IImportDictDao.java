package com.pushwin.batchwork.dao;

import java.util.List;

import com.pushwin.batchwork.model.TBasisDict;
import com.pushwin.batchwork.model.TBasisDictBranch;
import com.pushwin.batchwork.model.TBasisDictCompabymemo;
import com.pushwin.batchwork.model.TBasisDictIgroup;
import com.pushwin.batchwork.model.TBasisDictPlanstore;
import com.pushwin.batchwork.model.TBasisDictPlanstoregood;
import com.pushwin.batchwork.model.TBasisDictPlantype;
import com.pushwin.batchwork.model.TBasisDictStoregoogs;
import com.pushwin.batchwork.model.TBasisDictStores;
import com.pushwin.batchwork.model.TBasisOrg;

public interface IImportDictDao  {

	public void saveListDict(List<TBasisDict> listTBasisDict);

	public void saveTBasisDictBranch(List<TBasisDictBranch> listTBasisDictBranch);
	
	public void saveListTBasisDictCompabymemo(List<TBasisDictCompabymemo> listTBasisDictCompabymemo);
	
	public void saveListTBasisDictPlantype(List<TBasisDictPlantype> listTBasisDictPlantype);
	

	public void saveListDictTBasisDictPlanstore(List<TBasisDictPlanstore> listTBasisDictTBasisDictPlanstore);
	public void saveLisTBasisDictStores(List<TBasisDictStores> listTBasisDictStores);
	public void saveLisTBasisDictIgroup(List<TBasisDictIgroup> TBasisDictIgroup);
	public void saveTBasisDictIgroup(TBasisDictIgroup tBasisDictIgroup);
	
	
	public void saveLisTplanStoreGood(List<TBasisDictPlanstoregood> tBasisDictPlanstoregood);
	
	public void saveLisTBasisDictStoregoogs(List<TBasisDictStoregoogs> tBasisDictStoregoogs);
}
