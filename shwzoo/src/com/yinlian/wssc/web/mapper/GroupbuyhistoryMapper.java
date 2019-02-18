package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Groupbuyhistory;
import com.yinlian.wssc.web.po.GroupbuyhistoryExample;
import com.yinlian.wssc.web.po.GroupbuyhistoryWithBLOBs;

public interface GroupbuyhistoryMapper {
    int countByExample(GroupbuyhistoryExample example)throws Exception;

    int deleteByExample(GroupbuyhistoryExample example)throws Exception;

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(GroupbuyhistoryWithBLOBs record)throws Exception;

    int insertSelective(GroupbuyhistoryWithBLOBs record)throws Exception;

    List<GroupbuyhistoryWithBLOBs> selectByExampleWithBLOBs(GroupbuyhistoryExample example)throws Exception;

    List<Groupbuyhistory> selectByExample(GroupbuyhistoryExample example)throws Exception;

    GroupbuyhistoryWithBLOBs selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") GroupbuyhistoryWithBLOBs record, @Param("example") GroupbuyhistoryExample example);

    int updateByExampleWithBLOBs(@Param("record") GroupbuyhistoryWithBLOBs record, @Param("example") GroupbuyhistoryExample example);

    int updateByExample(@Param("record") Groupbuyhistory record, @Param("example") GroupbuyhistoryExample example);

    int updateByPrimaryKeySelective(GroupbuyhistoryWithBLOBs record)throws Exception;

    int updateByPrimaryKeyWithBLOBs(GroupbuyhistoryWithBLOBs record)throws Exception;

    int updateByPrimaryKey(Groupbuyhistory record)throws Exception;
    
    int countByHaCode(String hacode)throws Exception;
    
    GroupbuyhistoryWithBLOBs getByHaCode(String hacode)throws Exception;
}