package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ProductTheme;
import com.yinlian.wssc.web.po.ProductThemeExample;

public interface ProductThemeMapper {
    int countByExample(ProductThemeExample example);

    int deleteByExample(ProductThemeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductTheme record);

    int insertSelective(ProductTheme record);

    List<ProductTheme> selectByExample(ProductThemeExample example);

    ProductTheme selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductTheme record, @Param("example") ProductThemeExample example);

    int updateByExample(@Param("record") ProductTheme record, @Param("example") ProductThemeExample example);

    int updateByPrimaryKeySelective(ProductTheme record);

    int updateByPrimaryKey(ProductTheme record);
}