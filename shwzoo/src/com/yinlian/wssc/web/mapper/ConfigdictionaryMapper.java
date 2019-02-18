package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.ConfigdictionaryExample;

public interface ConfigdictionaryMapper {
    int countByExample(ConfigdictionaryExample example);

    int deleteByExample(ConfigdictionaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Configdictionary record);

    int insertSelective(Configdictionary record);

    List<Configdictionary> selectByExample(ConfigdictionaryExample example);

    Configdictionary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Configdictionary record,
                                 @Param("example") ConfigdictionaryExample example);

    int updateByExample(@Param("record") Configdictionary record,
                        @Param("example") ConfigdictionaryExample example);

    int updateByPrimaryKeySelective(Configdictionary record);

    int updateByPrimaryKey(Configdictionary record);

    Configdictionary selectByPrimaryType(Integer type) throws Exception;
}