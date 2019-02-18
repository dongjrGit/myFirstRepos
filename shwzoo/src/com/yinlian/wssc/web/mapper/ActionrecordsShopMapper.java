package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ActionrecordsShop;
import com.yinlian.wssc.web.po.ActionrecordsShopExample;

public interface ActionrecordsShopMapper {
    int countByExample(ActionrecordsShopExample example);

    int deleteByExample(ActionrecordsShopExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ActionrecordsShop record);

    int insertSelective(ActionrecordsShop record);

    List<ActionrecordsShop> selectByExample(ActionrecordsShopExample example);

    ActionrecordsShop selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ActionrecordsShop record, @Param("example") ActionrecordsShopExample example);

    int updateByExample(@Param("record") ActionrecordsShop record, @Param("example") ActionrecordsShopExample example);

    int updateByPrimaryKeySelective(ActionrecordsShop record);

    int updateByPrimaryKey(ActionrecordsShop record);
}