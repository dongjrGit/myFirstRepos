package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Receiveinfo;
import com.yinlian.wssc.web.po.ReceiveinfoExample;

public interface ReceiveinfoMapper {
    int countByExample(ReceiveinfoExample example);

    int deleteByExample(ReceiveinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Receiveinfo record);

    int insertSelective(Receiveinfo record);

    List<Receiveinfo> selectByExample(ReceiveinfoExample example);

    Receiveinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Receiveinfo record, @Param("example") ReceiveinfoExample example);

    int updateByExample(@Param("record") Receiveinfo record, @Param("example") ReceiveinfoExample example);

    int updateByPrimaryKeySelective(Receiveinfo record);

    int updateByPrimaryKey(Receiveinfo record);
    
    Receiveinfo selectByOrderGroupId(String  ordergroupcode);
}