package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.web.dto.PackageDto;
import com.yinlian.wssc.web.dto.SkuPackageDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.PackageMapper;
import com.yinlian.wssc.web.mapper.SkuPackageMapper;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuPackage;
import com.yinlian.wssc.web.service.PackageService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("packageService")
public class PackageServiceImpl implements PackageService {

	@Autowired
	private PackageMapper packageMapper;
	@Autowired
	private SkuPackageMapper skuPackageMapper;

	/**
	 * 添加组合商品
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int insert(Package record) throws Exception {
		return packageMapper.insert(record);
	}

	/**
	 * 修改组合商品
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int update(Package record) throws Exception {
		return packageMapper.updateByPrimaryKey(record);
	}

	/**
	 * 根据ID获取组合商品信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Package getByID(Integer id) throws Exception {
		return packageMapper.selectByPrimaryKey(id);
	}

	/**
	 * 删除组合商品
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Integer id) throws Exception {
		Integer ret=0;
		//先删除关联表数据
		ret=skuPackageMapper.deleteByPackageID(id);
		//再删除组合商品数据
		ret=packageMapper.deleteByPrimaryKey(id);
		
		 return ret;
	}

	public int updateStatus(Integer id, Integer status) throws Exception {
		Package pack = new Package();
		pack.setId(id);
		pack.setStatus(status);
		return packageMapper.updateStatus(pack);
	}

	public int updateCheck(Integer id, Boolean ischeck) throws Exception {
		Package pack = new Package();
		pack.setId(id);
		pack.setIscheck(ischeck);
		return packageMapper.updateCheck(pack);
	}

	public int updateCheckList(List<Integer> idlist, Boolean ischeck) throws Exception {
		List<Package> list = new ArrayList<Package>();
		Package pack = null;
		for (Integer id : idlist) {
			pack = new Package();
			pack.setId(id);
			pack.setIscheck(ischeck);
			list.add(pack);
		}
    	return packageMapper.updateCheckList(list);
    }
    
	public PageBean getPackageByPage(Criteria criteria,Integer page,Integer size)throws Exception{
    	PageBeanUtil pBeanUtil=new PageBeanUtil(criteria,page,size);
    	PageBean pBean=pBeanUtil.getPage();
    	List<Package> list=packageMapper.getPackageByPage(pBeanUtil);
    	pBean.setBeanList(list);
    	return pBean;
    }
    
	public List<SkuPackageDto> getByPackageID(Integer packageid)throws Exception{
    	return packageMapper.getByPackageID(packageid);
    }
    
	public int addSkuPackage(SkuPackage sp)throws Exception{	
		  int returns=0;
		  Package pack = packageMapper.selectByPrimaryKey(sp.getPackageid());
          List<SkuPackageDto> splist = packageMapper.getByPackageID(pack.getId());
          if (splist.size() > 0)
          {
        	  Float sumPrice=0.0f;
        	  for (SkuPackageDto Dto : splist) {
        		  sumPrice+=Dto.getSkuPackPrice();
			}
			pack.setPrice(sumPrice + sp.getSkuprice());

		} else {
			pack.setPrice(sp.getSkuprice());
		}
		pack.setIscheck(false);

		returns = packageMapper.updatePrice(pack);
		returns = skuPackageMapper.insert(sp);
		return returns;
	}

	public int deleteSkuPackage(Integer id) throws Exception {
		int returns = 0;
		SkuPackage sp = skuPackageMapper.selectByPrimaryKey(id);
		Package pack = packageMapper.selectByPrimaryKey(sp.getPackageid());
        List<SkuPackageDto> splist = packageMapper.getByPackageID(pack.getId());
        if (splist.size() > 0)
        {
      	  Float sumPrice=0.0f;
      	  for (SkuPackageDto Dto : splist) {
      		  sumPrice+=Dto.getSkuPackPrice();
			}
			pack.setPrice(sumPrice - sp.getSkuprice());
		}
		pack.setIscheck(false);

		returns = packageMapper.updatePrice(pack);
		returns = skuPackageMapper.deleteByPrimaryKey(id);
		return returns;
	}

	public SkuPackage getspByID(Integer id) throws Exception {
		return skuPackageMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据店铺ID，组合商品ID和名称模糊检索sku商品列表
	 * @param shopid
	 * @param packageid
	 * @param name
	 */
	public List<Sku> getSkuStartwithName(Integer shopid,Integer packageid,String name) throws Exception{
		return packageMapper.getSkuStartwithName(shopid, packageid, name);
	}

	public List<SkuPackage> getPackSkuList(Integer id) {
		return skuPackageMapper.getPackSkuList(id);
	}

	@Override
	public List<Package> getByIDs(List<Integer> ids) throws Exception {
		return packageMapper.getByIDs(ids);
	}

	@Override
	public int updateCountByids(List<UpDateActDto> pckids) throws Exception {
		return packageMapper.updateCountByids(pckids);
	}
	
	public PackageDto GetPackageDtoByID(int id)throws Exception{
		PackageDto dto=new PackageDto();
		Package p=packageMapper.selectByPrimaryKey(id);
		if(p!=null && p.getId()>0)
		{
			if(p.getEndtime().getTime()<=new Date().getTime()){
				dto=null;
				return dto;
			}
			dto.setCount(p.getCount());
			dto.setPackageID(p.getId());
			dto.setPackageName(p.getName());
			dto.setPackageNum(p.getNum());
			dto.setSkuPackPrice(p.getPrice());
			dto.setSkus(packageMapper.getPackSkuDtoByPackID(p.getId()));			
		}
		else{
			dto=null;
		}		
		return dto;
		
	}

	@Override
	public Package selectByParmarykey(Integer packageid) throws Exception {
		
		return packageMapper.selectByPrimaryKey(packageid);
	}
}
