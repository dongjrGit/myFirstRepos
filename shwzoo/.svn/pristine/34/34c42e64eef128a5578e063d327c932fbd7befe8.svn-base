package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.po.UserslevelExample;

public interface UserslevelMapper {
    int countByExample(UserslevelExample example);

    int deleteByExample(UserslevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Userslevel record);

    int insertSelective(Userslevel record);

    List<Userslevel> selectByExample(UserslevelExample example);

    Userslevel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Userslevel record, @Param("example") UserslevelExample example);

    int updateByExample(@Param("record") Userslevel record, @Param("example") UserslevelExample example);

    int updateByPrimaryKeySelective(Userslevel record);

    int updateByPrimaryKey(Userslevel record);
    
    List<Userslevel> getlevelList()throws Exception;
    
    Userslevel levelOrder() throws Exception;
}