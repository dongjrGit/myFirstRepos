package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.PackageDto;
import com.yinlian.wssc.web.po.SkuPackage;

public interface SkuPackageService {
	
	/**
	 * 通过库存商品查询组合商品
	 * @param skuid
	 * @return
	 * @throws Exception
	 */
	public List<PackageDto>  queryBySkuId(Integer skuid,Integer usesite) throws Exception;
	
	/**
	 * 通过组合商品id查询组合商品关联信息
	 * @param packageid
	 * @return
	 * @throws Exception
	 */
	public List<SkuPackage>  queryByPKid(Integer packageid) throws Exception;
}
