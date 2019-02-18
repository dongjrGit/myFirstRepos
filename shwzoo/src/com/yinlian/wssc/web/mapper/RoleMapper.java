package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Role;
import com.yinlian.wssc.web.po.RoleExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(Role record)throws Exception;

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record)throws Exception;
    
    List<Role> getRoleByPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    List<Role> getRoleStartWithName(Integer shopid,String name)throws Exception;
    
    int updateStatus(Role record)throws Exception;
    
}