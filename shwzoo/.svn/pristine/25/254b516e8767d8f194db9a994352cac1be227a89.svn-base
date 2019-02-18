package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ProductActivity;
import com.yinlian.wssc.web.po.ProductActivityExample;

public interface ProductActivityMapper {
    int countByExample(ProductActivityExample example);

    int deleteByExample(ProductActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductActivity record);

    int insertSelective(ProductActivity record);

    List<ProductActivity> selectByExample(ProductActivityExample example);

    ProductActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductActivity record, @Param("example") ProductActivityExample example);

    int updateByExample(@Param("record") ProductActivity record, @Param("example") ProductActivityExample example);

    int updateByPrimaryKeySelective(ProductActivity record);

    int updateByPrimaryKey(ProductActivity record);
}