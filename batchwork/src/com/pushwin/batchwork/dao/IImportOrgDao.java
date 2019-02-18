package com.pushwin.batchwork.dao;

import java.util.List;

import com.pushwin.batchwork.model.TBasisBusBank;
import com.pushwin.batchwork.model.TBasisBusSubbank;
import com.pushwin.batchwork.model.TBasisOrg;

public interface IImportOrgDao extends IBaseDAO<TBasisOrg>{
	
	
	public List<TBasisBusBank> listTBasisBusBank();
	public List<TBasisBusSubbank> listTBasisBusSubbank();
	
	public boolean listTBasisBusBankFlag();
	public boolean listTBasisBusSubbankFlag();
	

}
