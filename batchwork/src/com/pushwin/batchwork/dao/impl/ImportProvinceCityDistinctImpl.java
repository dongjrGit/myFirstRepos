package com.pushwin.batchwork.dao.impl;

import java.util.List;

import org.hibernate.criterion.Distinct;
import org.springframework.stereotype.Repository;

import com.pushwin.batchwork.dao.IImportProvinceCityDistinctDao;
import com.pushwin.batchwork.model.TBasisCity;
import com.pushwin.batchwork.model.TBasisDistrict;
import com.pushwin.batchwork.model.TBasisProvinces;
import com.pushwin.batchwork.model.TBasisUser;

@Repository
public class ImportProvinceCityDistinctImpl extends
		BaseDAOImpl<TBasisProvinces> implements IImportProvinceCityDistinctDao {

	@Override
	public void deleteProvinceCityDistinct() {
		this.getCurrentSession().createSQLQuery("delete from T_BASIS_DISTRICT")
				.executeUpdate();
		this.getCurrentSession().createSQLQuery("delete from T_BASIS_CITY")
				.executeUpdate();
		this.getCurrentSession()
				.createSQLQuery("delete from T_BASIS_PROVINCES")
				.executeUpdate();

	}

	@Override
	public void SaveProvince(List<TBasisProvinces> listTBasisProvinces) {
		for (TBasisProvinces tBasisProvinces : listTBasisProvinces) {
			this.save(tBasisProvinces);
		}

	}

	@Override
	public void SaveCity(List<TBasisCity> listTBasisCity) {
		for (TBasisCity tBasisCity : listTBasisCity) {
			this.save(tBasisCity);
		}

	}

	@Override
	public void SaveDistinct(List<TBasisDistrict> listTBasisDistrict) {
		for (TBasisDistrict tBasisDistrict : listTBasisDistrict) {
			this.save(tBasisDistrict);
		}
	}

}
