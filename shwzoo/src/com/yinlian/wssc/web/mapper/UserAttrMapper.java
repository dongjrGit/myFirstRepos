package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.UserAttrExample;

public interface UserAttrMapper {
    int countByExample(UserAttrExample example);

    int deleteByExample(UserAttrExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAttr record);

    int insertSelective(UserAttr record);

    List<UserAttr> selectByExample(UserAttrExample example);

    UserAttr selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserAttr record, @Param("example") UserAttrExample example);

    int updateByExample(@Param("record") UserAttr record, @Param("example") UserAttrExample example);

    int updateByPrimaryKeySelective(UserAttr record);

    int updateByPrimaryKey(UserAttr record);
}