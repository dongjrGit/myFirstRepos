package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Logistics;
import com.yinlian.wssc.web.po.LogisticsExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface LogisticsMapper {
    int countByExample(LogisticsExample example);

    int deleteByExample(LogisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Logistics record);

    int insertSelective(Logistics record);

    List<Logistics> selectByExample(LogisticsExample example);

    Logistics selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Logistics record, @Param("example") LogisticsExample example);

    int updateByExample(@Param("record") Logistics record, @Param("example") LogisticsExample example);

    int updateByPrimaryKeySelective(Logistics record);

    int updateByPrimaryKey(Logistics record);

	List<Logistics> getListByPage(PageBeanUtil pageBeanUtil) throws Exception ;
	
	String getByCode(String logistcode)throws Exception ;
}