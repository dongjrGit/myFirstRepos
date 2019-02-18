package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.web.dto.PackageSkuDto;
import com.yinlian.wssc.web.dto.SkuPackageDto;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.PackageExample;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface PackageMapper {
	int countByExample(PackageExample example);

	int deleteByExample(PackageExample example);

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insert(Package record) throws Exception;

	int insertSelective(Package record);

	List<Package> selectByExample(PackageExample example);

	Package selectByPrimaryKey(Integer id) throws Exception;

	int updateByExampleSelective(@Param("record") Package record,
			@Param("example") PackageExample example);

	int updateByExample(@Param("record") Package record,
			@Param("example") PackageExample example);

	int updateByPrimaryKeySelective(Package record);

	int updateByPrimaryKey(Package record) throws Exception;

	int updatePrice(Package record) throws Exception;

	int updateStatus(Package record) throws Exception;

	int updateCheck(Package record) throws Exception;

	int updateCheckList(List<Package> list) throws Exception;

	List<Package> getPackageByPage(PageBeanUtil pBeanUtil) throws Exception;

	List<SkuPackageDto> getByPackageID(Integer packageid) throws Exception;

	List<Package> getByIDs(List<Integer> ids) throws Exception;

	int updateCountByids(List<UpDateActDto> pckids) throws Exception;

	List<Sku> getSkuStartwithName(Integer shopid, Integer packageid,@Param("name") String name)
			throws Exception;

	List<PackageSkuDto> getPackSkuDtoByPackID(Integer packageid)
			throws Exception;

}