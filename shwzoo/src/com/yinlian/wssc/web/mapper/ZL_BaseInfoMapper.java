package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ZL_BaseInfo;
import com.yinlian.wssc.web.po.ZL_BaseInfoExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ZL_BaseInfoMapper {
    int countByExample(ZL_BaseInfoExample example);

    int deleteByExample(ZL_BaseInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZL_BaseInfo record);

    List<ZL_BaseInfo> selectListPage (PageBeanUtil util);
    
    int insertSelective(ZL_BaseInfo record);

    List<ZL_BaseInfo> selectByExample(ZL_BaseInfoExample example);

    ZL_BaseInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZL_BaseInfo record, @Param("example") ZL_BaseInfoExample example);

    int updateByExample(@Param("record") ZL_BaseInfo record, @Param("example") ZL_BaseInfoExample example);

    int updateByPrimaryKeySelective(ZL_BaseInfo record);

    int updateByPrimaryKey(ZL_BaseInfo record);
    
    List<ZL_BaseInfo> selectGroupBy();
}