package com.pushwin.batchwork.dao;

import java.util.List;

import com.pushwin.batchwork.model.TBasisiBusSpecialCostAdd;

public interface IImportProInfoDao {
	
	public void deleteProInfo();
	public void SaveProInfo(List<TBasisiBusSpecialCostAdd> listProInfo);
}
