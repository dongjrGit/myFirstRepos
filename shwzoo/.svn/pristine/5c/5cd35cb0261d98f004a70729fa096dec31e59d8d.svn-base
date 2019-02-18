package com.yinlian.api.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.PackageDto;
import com.yinlian.wssc.web.service.SkuPackageService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/app/sku")
public class SkuApiController {
	/**
	 * 日志输出的类
	 */
	//private static final Logger logger = LoggerFactory.getLogger(SkuApiController.class);
	
	@Autowired
	private   SkuPackageService       skuPackageService;
	
	/**
	 * 查询组合商品
	 * @param skuid
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/selectPackage", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectPackage(String skuid,String ch) {
		BaseResult item = new BaseResult();
		try {
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(skuid)<0) {
				item.setCode(-102);
				item.setDesc("库存商品id(skuid)不能为空！");
				return item.toJson();
			}
			
			List<PackageDto>  pklist=new ArrayList<PackageDto>();
			pklist=skuPackageService.queryBySkuId(StringUtilsEX.ToInt(skuid),ActivityUsePlatformEnum.app.getValue());	
//			List<SkuPackage>  list=skuPackageService.queryBySkuId(StringUtilsEX.ToInt(skuid),ActivityUsePlatformEnum.app.getValue());	
//			if(list!=null&list.size()>0){
//				for (int i = 0; i < list.size(); i++) {
//					List<PackageSkuDto>  psdlist=new ArrayList<PackageSkuDto>();
//					PackageDto  packageDto=new PackageDto();
//					int packageid=list.get(i).getPackageid();
//					List<SkuPackage> splist=skuPackageService.queryByPKid(packageid);
//					if(splist!=null&splist.size()>0){
//						for (int j = 0; j < splist.size(); j++) {
//							PackageSkuDto packageSkuDto=new PackageSkuDto();
//							Sku sku=skuService.selectByID(splist.get(j).getSkuid());
//							packageSkuDto.setImgUrl(sku.getImgurl());
//							packageSkuDto.setSkuID(sku.getId());
//							packageSkuDto.setSkuNum(sku.getNum());
//							packageSkuDto.setSkuName(sku.getName());
//							packageSkuDto.setSkuPrice(sku.getPrice());
//							packageSkuDto.setSkuPackPrice(splist.get(j).getSkuprice());
//							psdlist.add(packageSkuDto);
//						}
//						packageDto.setSkus(psdlist);
//					}
//					
//					Package  pk=packageService.selectByParmarykey(packageid);
//					packageDto.setPackageID(pk.getId());
//					packageDto.setPackageNum(pk.getNum());
//					packageDto.setPackageName(pk.getName());
//					packageDto.setSkuPackPrice(pk.getPrice());
//					packageDto.setCount(pk.getCount());		
//					
//					pklist.add(packageDto);
//				}
				
			item.setCode(0);
			item.setData(pklist);
			item.setDesc("查询组合商品列表成功");
				
			
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "查询组合商品异常! 异常信息:{0}",e, "sku/selectPackage");
		}
		return item.toJson();
	}
}
