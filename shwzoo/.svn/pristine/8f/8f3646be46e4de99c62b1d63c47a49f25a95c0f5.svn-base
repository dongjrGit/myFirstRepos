package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ProductImgs;
import com.yinlian.wssc.web.po.ProductImgsExample;

public interface ProductImgsMapper {
    int countByExample(ProductImgsExample example);

    int deleteByExample(ProductImgsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductImgs record);

    int insertSelective(ProductImgs record);

    List<ProductImgs> selectByExample(ProductImgsExample example);

    ProductImgs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductImgs record,
                                 @Param("example") ProductImgsExample example);

    int updateByExample(@Param("record") ProductImgs record,
                        @Param("example") ProductImgsExample example);

    int updateByPrimaryKeySelective(ProductImgs record);

    int updateByPrimaryKey(ProductImgs record);

    List<ProductImgs> selectBySku(Integer skuid);

    List<ProductImgs> selectBySpu(Integer spuid);
}