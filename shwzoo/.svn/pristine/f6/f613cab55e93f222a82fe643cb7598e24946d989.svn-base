package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.SkuPackage;
import com.yinlian.wssc.web.po.SkuPackageExample;

public interface SkuPackageMapper {
    int countByExample(SkuPackageExample example);

    int deleteByExample(SkuPackageExample example);

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(SkuPackage record)throws Exception;

    int insertSelective(SkuPackage record);

    List<SkuPackage> selectByExample(SkuPackageExample example);

    SkuPackage selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") SkuPackage record, @Param("example") SkuPackageExample example);

    int updateByExample(@Param("record") SkuPackage record, @Param("example") SkuPackageExample example);

    int updateByPrimaryKeySelective(SkuPackage record);

    int updateByPrimaryKey(SkuPackage record);
    
    int deleteByPackageID(Integer pid)throws Exception;

	List<SkuPackage> getPackSkuList(Integer id);
	
	/**
	 * 通过库存商品查询
	 * @param skuid
	 * @return
	 * @throws Exception
	 */
	List<SkuPackage>   queryBySkuId(Integer skuid,Integer usesite) throws Exception;
	
	/**
	 * 通过组合商品id 查询
	 * @param packageid
	 * @return
	 * @throws Exception
	 */
	List<SkuPackage>   selectByPkid(Integer packageid) throws Exception;
	
	/**
	 * 根据skuid 查询组合商品数量
	 * @param skuid
	 * @return
	 * @throws Exception
	 */
	int getCoutBySkuId(Integer skuid,Integer usesite) throws Exception;

}