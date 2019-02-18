package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Dictionaries;
import com.yinlian.wssc.web.po.DictionariesExample;

public interface DictionariesMapper {
    int countByExample(DictionariesExample example);

    int deleteByExample(DictionariesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dictionaries record);

    int insertSelective(Dictionaries record);

    List<Dictionaries> selectByExample(DictionariesExample example);

    Dictionaries selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Dictionaries record, @Param("example") DictionariesExample example);

    int updateByExample(@Param("record") Dictionaries record, @Param("example") DictionariesExample example);

    int updateByPrimaryKeySelective(Dictionaries record);

    int updateByPrimaryKey(Dictionaries record);
}