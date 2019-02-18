package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Actionrecords;
import com.yinlian.wssc.web.po.ActionrecordsExample;

public interface ActionrecordsMapper {
    int countByExample(ActionrecordsExample example);

    int deleteByExample(ActionrecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Actionrecords record);

    int insertSelective(Actionrecords record);

    List<Actionrecords> selectByExample(ActionrecordsExample example);

    Actionrecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Actionrecords record, @Param("example") ActionrecordsExample example);

    int updateByExample(@Param("record") Actionrecords record, @Param("example") ActionrecordsExample example);

    int updateByPrimaryKeySelective(Actionrecords record);

    int updateByPrimaryKey(Actionrecords record);
}