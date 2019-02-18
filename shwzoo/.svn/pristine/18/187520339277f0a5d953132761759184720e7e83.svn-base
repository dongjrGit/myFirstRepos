package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Financerecords;
import com.yinlian.wssc.web.po.FinancerecordsExample;

public interface FinancerecordsMapper {
    int countByExample(FinancerecordsExample example);

    int deleteByExample(FinancerecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Financerecords record);

    int insertSelective(Financerecords record);

    List<Financerecords> selectByExample(FinancerecordsExample example);

    Financerecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Financerecords record, @Param("example") FinancerecordsExample example);

    int updateByExample(@Param("record") Financerecords record, @Param("example") FinancerecordsExample example);

    int updateByPrimaryKeySelective(Financerecords record);

    int updateByPrimaryKey(Financerecords record);
}