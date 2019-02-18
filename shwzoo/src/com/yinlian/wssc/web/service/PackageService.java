package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.web.dto.PackageDto;
import com.yinlian.wssc.web.dto.SkuPackageDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuPackage;
import com.yinlian.wssc.web.util.Criteria;

public interface PackageService {

	    int insert(Package record)throws Exception; 
	
	    int update(Package record)throws Exception; 
	    
	    Package getByID(Integer id)throws Exception; 
	    
	    int delete(Integer id)throws Exception; 
	    
	    int updateStatus(Integer id,Integer status)throws Exception; 
	    
	    int updateCheck(Integer id, Boolean ischeck)throws Exception; 
	    
	    int updateCheckList(List<Integer> idlist,Boolean ischeck)throws Exception; 
	    
	    PageBean getPackageByPage(Criteria criteria,Integer page,Integer size)throws Exception; 
	    
	    List<SkuPackageDto> getByPackageID(Integer packageid)throws Exception; 
	    
	    int addSkuPackage(SkuPackage record)throws Exception; 
	    
	    int deleteSkuPackage(Integer id)throws Exception; 
	    
	    SkuPackage getspByID(Integer id)throws Exception;

		List<SkuPackage> getPackSkuList(Integer id) throws Exception;

		/**
		 * 根据 组合商品Ids 获取组合 商品信息
		 * @param ids
		 * @return
		 * @throws Exception
		 */
		List<Package> getByIDs(List<Integer> ids)throws Exception;

		/**
		 * 根据ids修改组合商品数量
		 * @param pckids
		 * @return
		 */
		int updateCountByids(List<UpDateActDto> pckids)throws Exception;
	    
	    List<Sku> getSkuStartwithName(Integer shopid,Integer packageid,String name) throws Exception; 
	    
	    PackageDto GetPackageDtoByID(int id)throws Exception; 
	    
	    /**
	     * 
	     * @param packageid
	     * @return
	     * @throws Exception
	     */
	    Package  selectByParmarykey(Integer packageid) throws Exception;
}
