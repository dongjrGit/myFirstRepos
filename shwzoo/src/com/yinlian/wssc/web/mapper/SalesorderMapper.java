package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Salesorder;
import com.yinlian.wssc.web.po.SalesorderExample;

public interface SalesorderMapper {
    int countByExample(SalesorderExample example);

    int deleteByExample(SalesorderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Salesorder record);

    int insertSelective(Salesorder record);

    List<Salesorder> selectByExample(SalesorderExample example);

    Salesorder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Salesorder record, @Param("example") SalesorderExample example);

    int updateByExample(@Param("record") Salesorder record, @Param("example") SalesorderExample example);

    int updateByPrimaryKeySelective(Salesorder record);

    int updateByPrimaryKey(Salesorder record);
}