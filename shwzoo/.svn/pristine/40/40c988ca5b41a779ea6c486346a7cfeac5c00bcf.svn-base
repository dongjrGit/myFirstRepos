package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Payset;
import com.yinlian.wssc.web.po.PaysetExample;

public interface PaysetMapper {
    int countByExample(PaysetExample example);

    int deleteByExample(PaysetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Payset record);

    int insertSelective(Payset record);

    List<Payset> selectByExample(PaysetExample example);

    Payset selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Payset record, @Param("example") PaysetExample example);

    int updateByExample(@Param("record") Payset record, @Param("example") PaysetExample example);

    int updateByPrimaryKeySelective(Payset record);

    int updateByPrimaryKey(Payset record);
}