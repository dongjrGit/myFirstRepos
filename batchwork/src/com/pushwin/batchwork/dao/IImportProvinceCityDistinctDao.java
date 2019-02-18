package com.pushwin.batchwork.dao;

import java.util.List;

import com.pushwin.batchwork.model.TBasisCity;
import com.pushwin.batchwork.model.TBasisDistrict;
import com.pushwin.batchwork.model.TBasisProvinces;


public interface IImportProvinceCityDistinctDao  {
	
	public void deleteProvinceCityDistinct();
		
	public void SaveProvince(List<TBasisProvinces> listTBasisProvinces);
	public void SaveDistinct(List<TBasisDistrict> listTBasisDistrict);
	public void SaveCity(List<TBasisCity> listTBasisCity);

}
