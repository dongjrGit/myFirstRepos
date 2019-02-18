package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ShopAttr;
import com.yinlian.wssc.web.po.ShopAttrExample;

public interface ShopAttrMapper {
    int countByExample(ShopAttrExample example);

    int deleteByExample(ShopAttrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopAttr record);

    int insertSelective(ShopAttr record);

    List<ShopAttr> selectByExample(ShopAttrExample example);

    ShopAttr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopAttr record, @Param("example") ShopAttrExample example);

    int updateByExample(@Param("record") ShopAttr record, @Param("example") ShopAttrExample example);

    int updateByPrimaryKeySelective(ShopAttr record);

    int updateByPrimaryKey(ShopAttr record);
}