package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.SkuPoints;
import com.yinlian.wssc.web.po.SkuPointsExample;

public interface SkuPointsMapper {
    int countByExample(SkuPointsExample example);

    int deleteByExample(SkuPointsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SkuPoints record);

    int insertSelective(SkuPoints record);

    List<SkuPoints> selectByExample(SkuPointsExample example);

    SkuPoints selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SkuPoints record, @Param("example") SkuPointsExample example);

    int updateByExample(@Param("record") SkuPoints record, @Param("example") SkuPointsExample example);

    int updateByPrimaryKeySelective(SkuPoints record);

    int updateByPrimaryKey(SkuPoints record);
}