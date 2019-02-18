package com.pushwin.batchwork.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pushwin.batchwork.dao.IImportUser;
import com.pushwin.batchwork.model.TBasisBusUser;
import com.pushwin.batchwork.model.TBasisOrg;
import com.pushwin.batchwork.model.TBasisUser;
import com.pushwin.batchwork.utils.DateUtil;
@Repository
public class ImportUser extends BaseDAOImpl<TBasisUser> implements IImportUser {
	
	@Override
	public void saveUser(List<TBasisUser> TBasisUserlist) {
			for (TBasisUser tBasisUser : TBasisUserlist) {
				this.saveOrUpdate(tBasisUser);
			}
		System.out.println("----------------");
		
	}

	@Override
	public TBasisOrg queryOrg(String orgCode) {
		TBasisOrg org =	(TBasisOrg) this.getCurrentSession().createQuery("from TBasisOrg o where o.orgCode=:orgCode")
							.setParameter("orgCode", orgCode)
								.uniqueResult();
		return org;
	}

	@Override
	public void saveOrg(TBasisOrg tbasisOrg) {
		this.save(tbasisOrg);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,TBasisUser> queryUserAll(String userCode) {
		Map<String, TBasisUser> mapTBasisUser =  new HashMap<String, TBasisUser>();
		List<TBasisUser> org =	 this.getCurrentSession().createQuery("from TBasisUser")
							//.setParameter("userCode", userCode)
								.list();
		for (TBasisUser tBasisUser : org) {
			mapTBasisUser.put(tBasisUser.getUserCode(), tBasisUser);
		}
		return mapTBasisUser;
	}

	@Override
	public boolean getUserFlag() {
		String time = DateUtil.batchTimeOrg();
		TBasisBusUser user = (TBasisBusUser) this.getCurrentSession().createQuery ("from TBasisBusUser as A where A.updaTime=:time")
									.setParameter("time", time)
									 .setMaxResults(1)
									 .uniqueResult();
									  
		return null == user ? false : true;
	}

	@Override
	public List<TBasisBusUser> getUserAllBus() {
		List<TBasisBusUser> org =	 this.getCurrentSession().createQuery("from TBasisBusUser")
				//.setParameter("userCode", userCode)
					.list();
		return org;
	}


}
