package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Spikeshop;
import com.yinlian.wssc.web.po.SpikeshopExample;

public interface SpikeshopMapper {
    int countByExample(SpikeshopExample example);

    int deleteByExample(SpikeshopExample example);

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(Spikeshop record)throws Exception;

    int insertSelective(Spikeshop record);

    List<Spikeshop> selectByExample(SpikeshopExample example);

    Spikeshop selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") Spikeshop record, @Param("example") SpikeshopExample example);

    int updateByExample(@Param("record") Spikeshop record, @Param("example") SpikeshopExample example);

    int updateByPrimaryKeySelective(Spikeshop record);

    int updateByPrimaryKey(Spikeshop record)throws Exception;
    
    int updateCheck(int id,int status)throws Exception;
    
    int updateCheckList(List<Spikeshop> list)throws Exception;
    
    List<Spikeshop> getBySpikeID(int spikeid)throws Exception;
}