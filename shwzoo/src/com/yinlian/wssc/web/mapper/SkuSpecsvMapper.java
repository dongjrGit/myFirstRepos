package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.Sku_SpecsvDto;
import com.yinlian.wssc.web.po.SkuSpecsv;
import com.yinlian.wssc.web.po.SkuSpecsvExample;

public interface SkuSpecsvMapper {
	int countByExample(SkuSpecsvExample example);

	int deleteByExample(SkuSpecsvExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(SkuSpecsv record);

	int insertSelective(SkuSpecsv record);

	List<SkuSpecsv> selectByExample(SkuSpecsvExample example);

	SkuSpecsv selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") SkuSpecsv record,
			@Param("example") SkuSpecsvExample example);

	int updateByExample(@Param("record") SkuSpecsv record,
			@Param("example") SkuSpecsvExample example);

	int updateByPrimaryKeySelective(SkuSpecsv record);

	int updateByPrimaryKey(SkuSpecsv record);

	int insertList(List<SkuSpecsv> list);

	int deleteList(List<Integer> ids);
	
	int deleteBySkuID(Integer skuid);
	/**
	 * 查询添加、编辑sku商品时是否已存在该规格商品
	 * @param spuid 标准商品id
	 * @param ids 规格id集合
	 * @return
	 * @throws Exception
	 */
	List<Sku_SpecsvDto> isHaveSpecsv(@Param("spuid") Integer spuid,@Param("ids")List<Integer> ids)throws Exception;
}