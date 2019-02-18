package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ProductUpdaterecords;
import com.yinlian.wssc.web.po.ProductUpdaterecordsExample;

public interface ProductUpdaterecordsMapper {
    int countByExample(ProductUpdaterecordsExample example);

    int deleteByExample(ProductUpdaterecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductUpdaterecords record);

    int insertSelective(ProductUpdaterecords record);

    List<ProductUpdaterecords> selectByExample(ProductUpdaterecordsExample example);

    ProductUpdaterecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductUpdaterecords record, @Param("example") ProductUpdaterecordsExample example);

    int updateByExample(@Param("record") ProductUpdaterecords record, @Param("example") ProductUpdaterecordsExample example);

    int updateByPrimaryKeySelective(ProductUpdaterecords record);

    int updateByPrimaryKey(ProductUpdaterecords record);
}