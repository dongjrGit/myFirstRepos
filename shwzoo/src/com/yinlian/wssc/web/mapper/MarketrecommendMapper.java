package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Marketrecommend;
import com.yinlian.wssc.web.po.MarketrecommendExample;

public interface MarketrecommendMapper {
    int countByExample(MarketrecommendExample example);

    int deleteByExample(MarketrecommendExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Marketrecommend record);

    int insertSelective(Marketrecommend record);

    List<Marketrecommend> selectByExample(MarketrecommendExample example);

    Marketrecommend selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Marketrecommend record, @Param("example") MarketrecommendExample example);

    int updateByExample(@Param("record") Marketrecommend record, @Param("example") MarketrecommendExample example);

    int updateByPrimaryKeySelective(Marketrecommend record);

    int updateByPrimaryKey(Marketrecommend record);
}