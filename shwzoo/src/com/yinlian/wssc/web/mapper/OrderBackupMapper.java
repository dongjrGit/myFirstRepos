package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.OrderBackup;
import com.yinlian.wssc.web.po.OrderBackupExample;

public interface OrderBackupMapper {
    int countByExample(OrderBackupExample example);

    int deleteByExample(OrderBackupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderBackup record);

    int insertSelective(OrderBackup record);

    List<OrderBackup> selectByExample(OrderBackupExample example);

    OrderBackup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderBackup record, @Param("example") OrderBackupExample example);

    int updateByExample(@Param("record") OrderBackup record, @Param("example") OrderBackupExample example);

    int updateByPrimaryKeySelective(OrderBackup record);

    int updateByPrimaryKey(OrderBackup record);
}