package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.UsersRole;
import com.yinlian.wssc.web.po.UsersRoleExample;

public interface UsersRoleMapper {
    int countByExample(UsersRoleExample example);

    int deleteByExample(UsersRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UsersRole record);

    int insertSelective(UsersRole record);

    List<UsersRole> selectByExample(UsersRoleExample example);

    UsersRole selectByPrimaryKey(Integer id);
    
    UsersRole selectByUserId(Integer accountid);

    int updateByExampleSelective(@Param("record") UsersRole record, @Param("example") UsersRoleExample example);

    int updateByExample(@Param("record") UsersRole record, @Param("example") UsersRoleExample example);

    int updateByPrimaryKeySelective(UsersRole record);

    int updateByPrimaryKey(UsersRole record);
}