package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Special;
import com.yinlian.wssc.web.po.SpecialExample;

public interface SpecialMapper {
    int countByExample(SpecialExample example);

    int deleteByExample(SpecialExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Special record);

    int insertSelective(Special record);

    List<Special> selectByExample(SpecialExample example);

    Special selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Special record, @Param("example") SpecialExample example);

    int updateByExample(@Param("record") Special record, @Param("example") SpecialExample example);

    int updateByPrimaryKeySelective(Special record);

    int updateByPrimaryKey(Special record);
}