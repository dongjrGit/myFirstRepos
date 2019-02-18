package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Pointsrule;
import com.yinlian.wssc.web.po.PointsruleExample;

public interface PointsruleMapper {
    int countByExample(PointsruleExample example);

    int deleteByExample(PointsruleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pointsrule record);

    int insertSelective(Pointsrule record);

    List<Pointsrule> selectByExample(PointsruleExample example);

    Pointsrule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pointsrule record, @Param("example") PointsruleExample example);

    int updateByExample(@Param("record") Pointsrule record, @Param("example") PointsruleExample example);

    int updateByPrimaryKeySelective(Pointsrule record);

    int updateByPrimaryKey(Pointsrule record);
}