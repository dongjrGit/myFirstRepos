package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Specialproduct;
import com.yinlian.wssc.web.po.SpecialproductExample;

public interface SpecialproductMapper {
    int countByExample(SpecialproductExample example);

    int deleteByExample(SpecialproductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Specialproduct record);

    int insertSelective(Specialproduct record);

    List<Specialproduct> selectByExample(SpecialproductExample example);

    Specialproduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Specialproduct record, @Param("example") SpecialproductExample example);

    int updateByExample(@Param("record") Specialproduct record, @Param("example") SpecialproductExample example);

    int updateByPrimaryKeySelective(Specialproduct record);

    int updateByPrimaryKey(Specialproduct record);
}