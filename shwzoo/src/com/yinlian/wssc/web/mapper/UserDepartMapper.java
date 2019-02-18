package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.UserDepart;
import com.yinlian.wssc.web.po.UserDepartExample;

public interface UserDepartMapper {
    int countByExample(UserDepartExample example);

    int deleteByExample(UserDepartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDepart record);

    int insertSelective(UserDepart record);

    List<UserDepart> selectByExample(UserDepartExample example);

    UserDepart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserDepart record, @Param("example") UserDepartExample example);

    int updateByExample(@Param("record") UserDepart record, @Param("example") UserDepartExample example);

    int updateByPrimaryKeySelective(UserDepart record);

    int updateByPrimaryKey(UserDepart record);
    
    int deleteByUser(Integer userid)throws Exception;
}