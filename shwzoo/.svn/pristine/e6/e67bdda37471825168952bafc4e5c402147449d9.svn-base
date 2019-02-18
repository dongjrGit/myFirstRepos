package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ShopClass;
import com.yinlian.wssc.web.po.ShopClassExample;

public interface ShopClassMapper {
    int countByExample(ShopClassExample example);

    int deleteByExample(ShopClassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopClass record);

    int insertSelective(ShopClass record);

    List<ShopClass> selectByExample(ShopClassExample example);

    ShopClass selectByPrimaryKey(Integer id);
    
    int deleteByshopid(Integer shopid)throws Exception;

    int updateByExampleSelective(@Param("record") ShopClass record, @Param("example") ShopClassExample example);

    int updateByExample(@Param("record") ShopClass record, @Param("example") ShopClassExample example);

    int updateByPrimaryKeySelective(ShopClass record);

    int updateByPrimaryKey(ShopClass record);
}