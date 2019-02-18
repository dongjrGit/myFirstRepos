package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.ExcitingUserDto;
import com.yinlian.wssc.web.po.UserSpike;
import com.yinlian.wssc.web.po.UserSpikeExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface UserSpikeMapper {
    int countByExample(UserSpikeExample example)throws Exception;

    int deleteByExample(UserSpikeExample example)throws Exception;

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(UserSpike record)throws Exception;

    int insertSelective(UserSpike record)throws Exception;

    List<UserSpike> selectByExample(UserSpikeExample example)throws Exception;

    UserSpike selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") UserSpike record, @Param("example") UserSpikeExample example);

    int updateByExample(@Param("record") UserSpike record, @Param("example") UserSpikeExample example);

    int updateByPrimaryKeySelective(UserSpike record)throws Exception;

    int updateByPrimaryKey(UserSpike record)throws Exception;
    
    UserSpike getCode(int userid,int spikeid)throws Exception;
    
    List<ExcitingUserDto> getbySpikeIDPage(PageBeanUtil pageBeanUtil)throws Exception;
}