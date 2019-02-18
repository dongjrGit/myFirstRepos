package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.FloorApp;
import com.yinlian.wssc.web.po.FloorAppExample;

public interface FloorAppMapper {
    int countByExample(FloorAppExample example);

    int deleteByExample(FloorAppExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FloorApp record);

    int insertSelective(FloorApp record);

    List<FloorApp> selectByExample(FloorAppExample example);

    FloorApp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FloorApp record, @Param("example") FloorAppExample example);

    int updateByExample(@Param("record") FloorApp record, @Param("example") FloorAppExample example);

    int updateByPrimaryKeySelective(FloorApp record);

    int updateByPrimaryKey(FloorApp record);
}