package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Timeset;
import com.yinlian.wssc.web.po.TimesetExample;

public interface TimesetMapper {
    int countByExample(TimesetExample example);

    int deleteByExample(TimesetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Timeset record);

    int insertSelective(Timeset record);

    List<Timeset> selectByExample(TimesetExample example);

    Timeset selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Timeset record, @Param("example") TimesetExample example);

    int updateByExample(@Param("record") Timeset record, @Param("example") TimesetExample example);

    int updateByPrimaryKeySelective(Timeset record);

    int updateByPrimaryKey(Timeset record);
}