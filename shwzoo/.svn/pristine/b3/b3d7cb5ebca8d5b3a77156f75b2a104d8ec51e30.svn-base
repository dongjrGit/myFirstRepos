package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.PackageDto;
import com.yinlian.wssc.web.dto.PackageSkuDto;
import com.yinlian.wssc.web.mapper.SkuPackageMapper;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuPackage;
import com.yinlian.wssc.web.service.PackageService;
import com.yinlian.wssc.web.service.SkuPackageService;
import com.yinlian.wssc.web.service.SkuService;

@Component("skuPackageService")
public class SkuPackageServiceImpl implements SkuPackageService {
	
	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory.getLogger(SkuPackageServiceImpl.class);
	
	@Autowired
	private   SkuPackageMapper      skuPackageMapper;
	@Autowired private SkuService skuService;
	@Autowired private PackageService packageService;
	@Override
	public List<PackageDto> queryBySkuId(Integer skuid,Integer usesite) throws Exception {
		
		List<PackageDto>  pklist=new ArrayList<PackageDto>();
		
		List<SkuPackage>  list=skuPackageMapper.queryBySkuId(skuid,usesite);	
		if(list!=null&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				List<PackageSkuDto>  psdlist=new ArrayList<PackageSkuDto>();
				PackageDto  packageDto=new PackageDto();
				int packageid=list.get(i).getPackageid();
				List<SkuPackage> splist=skuPackageMapper.selectByPkid(packageid);
				if(splist!=null&splist.size()>0){
					for (int j = 0; j < splist.size(); j++) {
						PackageSkuDto packageSkuDto=new PackageSkuDto();
						Sku sku=skuService.selectByID(splist.get(j).getSkuid());
						packageSkuDto.setImgUrl(sku.getImgurl());
						packageSkuDto.setSkuID(sku.getId());
						packageSkuDto.setSkuNum(sku.getNum());
						packageSkuDto.setSkuName(sku.getName());
						packageSkuDto.setSkuPrice(sku.getPrice().floatValue());
						packageSkuDto.setSkuPackPrice(splist.get(j).getSkuprice());
						packageSkuDto.setSpuid(sku.getSpuId());
						psdlist.add(packageSkuDto);
					}
					packageDto.setSkus(psdlist);
				}
				
				Package  pk=packageService.selectByParmarykey(packageid);
				packageDto.setPackageID(pk.getId());
				packageDto.setPackageNum(pk.getNum());
				packageDto.setPackageName(pk.getName());
				packageDto.setSkuPackPrice(pk.getPrice());
				packageDto.setCount(pk.getCount());		
				
				pklist.add(packageDto);
			}
		}
		return pklist;
//	   skuPackageMapper.queryBySkuId(skuid,usesite);
	}

	@Override
	public List<SkuPackage> queryByPKid(Integer packageid) throws Exception {
		
		return skuPackageMapper.selectByPkid(packageid);
	}

}
