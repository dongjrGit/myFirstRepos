package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.RoleMenus;
import com.yinlian.wssc.web.po.RoleMenusExample;

public interface RoleMenusMapper {
    int countByExample(RoleMenusExample example);

    int deleteByExample(RoleMenusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenus record);

    int insertSelective(RoleMenus record);

    List<RoleMenus> selectByExample(RoleMenusExample example);
    

    RoleMenus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleMenus record, @Param("example") RoleMenusExample example);

    int updateByExample(@Param("record") RoleMenus record, @Param("example") RoleMenusExample example);

    int updateByPrimaryKeySelective(RoleMenus record);

    int updateByPrimaryKey(RoleMenus record);
    
    int insertList(List<RoleMenus> list)throws Exception;
    
    int deleteByRoleID(Integer roleid)throws Exception;
    
    List<RoleMenus> selectByRoleID(Integer roleid)throws Exception;
    
}