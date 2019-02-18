package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Hispass;
import com.yinlian.wssc.web.po.HispassExample;

public interface HispassMapper {
    int countByExample(HispassExample example);

    int deleteByExample(HispassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hispass record);

    int insertSelective(Hispass record);

    List<Hispass> selectByExample(HispassExample example);

    Hispass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hispass record, @Param("example") HispassExample example);

    int updateByExample(@Param("record") Hispass record, @Param("example") HispassExample example);

    int updateByPrimaryKeySelective(Hispass record);

    int updateByPrimaryKey(Hispass record);
}