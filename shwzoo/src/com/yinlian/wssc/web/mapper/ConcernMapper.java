package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Concern;
import com.yinlian.wssc.web.po.ConcernExample;

public interface ConcernMapper {
    int countByExample(ConcernExample example);

    int deleteByExample(ConcernExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Concern record);

    int insertSelective(Concern record);

    List<Concern> selectByExample(ConcernExample example);

    Concern selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Concern record, @Param("example") ConcernExample example);

    int updateByExample(@Param("record") Concern record, @Param("example") ConcernExample example);

    int updateByPrimaryKeySelective(Concern record);

    int updateByPrimaryKey(Concern record);
}