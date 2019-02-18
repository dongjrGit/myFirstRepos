package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Advertimgdictionary;
import com.yinlian.wssc.web.po.AdvertimgdictionaryExample;

public interface AdvertimgdictionaryMapper {
    int countByExample(AdvertimgdictionaryExample example);

    int deleteByExample(AdvertimgdictionaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Advertimgdictionary record);

    int insertSelective(Advertimgdictionary record);

    List<Advertimgdictionary> selectByExample(AdvertimgdictionaryExample example);

    Advertimgdictionary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Advertimgdictionary record, @Param("example") AdvertimgdictionaryExample example);

    int updateByExample(@Param("record") Advertimgdictionary record, @Param("example") AdvertimgdictionaryExample example);

    int updateByPrimaryKeySelective(Advertimgdictionary record);

    int updateByPrimaryKey(Advertimgdictionary record);
}