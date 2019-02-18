package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Searchrecords;
import com.yinlian.wssc.web.po.SearchrecordsExample;

public interface SearchrecordsMapper {
    int countByExample(SearchrecordsExample example);

    int deleteByExample(SearchrecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Searchrecords record);

    int insertSelective(Searchrecords record);

    List<Searchrecords> selectByExample(SearchrecordsExample example);

    Searchrecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Searchrecords record, @Param("example") SearchrecordsExample example);

    int updateByExample(@Param("record") Searchrecords record, @Param("example") SearchrecordsExample example);

    int updateByPrimaryKeySelective(Searchrecords record);

    int updateByPrimaryKey(Searchrecords record);
}