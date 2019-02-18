package com.pushwin.batchwork.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import com.pushwin.batchwork.model.TBasisBusUser;
import com.pushwin.batchwork.model.TBasisOrg;
import com.pushwin.batchwork.model.TBasisUser;

public interface IImportUser  extends IBaseDAO<TBasisUser>{
	public void saveUser(List<TBasisUser> TBasisUserlist);
	
	public TBasisOrg queryOrg(String orgCode);
	public void saveOrg(TBasisOrg tbasisOrg);
	public Map<String,TBasisUser>  queryUserAll(String userCode);
	
	public boolean getUserFlag();
	public List<TBasisBusUser> getUserAllBus();

}
