package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Dispatching;
import com.yinlian.wssc.web.po.DispatchingExample;

public interface DispatchingMapper {
    int countByExample(DispatchingExample example);

    int deleteByExample(DispatchingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dispatching record);

    int insertSelective(Dispatching record);

    List<Dispatching> selectByExample(DispatchingExample example);

    Dispatching selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Dispatching record, @Param("example") DispatchingExample example);

    int updateByExample(@Param("record") Dispatching record, @Param("example") DispatchingExample example);

    int updateByPrimaryKeySelective(Dispatching record);

    int updateByPrimaryKey(Dispatching record);
    
    Dispatching selectGroupCode(String ordergroupcode);
}