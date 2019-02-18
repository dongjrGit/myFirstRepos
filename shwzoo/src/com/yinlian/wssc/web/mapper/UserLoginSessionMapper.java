package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.UserLoginSession;
import com.yinlian.wssc.web.po.UserLoginSessionExample;

public interface UserLoginSessionMapper {
    int countByExample(UserLoginSessionExample example);

    int deleteByExample(UserLoginSessionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginSession record);

    int insertSelective(UserLoginSession record);

    List<UserLoginSession> selectByExample(UserLoginSessionExample example);

    UserLoginSession selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserLoginSession record, @Param("example") UserLoginSessionExample example);

    int updateByExample(@Param("record") UserLoginSession record, @Param("example") UserLoginSessionExample example);

    int updateByPrimaryKeySelective(UserLoginSession record);

    int updateByPrimaryKey(UserLoginSession record);
}