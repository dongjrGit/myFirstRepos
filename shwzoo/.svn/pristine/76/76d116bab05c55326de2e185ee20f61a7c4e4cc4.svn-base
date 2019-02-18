package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Satisfaction;
import com.yinlian.wssc.web.po.SatisfactionExample;

public interface SatisfactionMapper {
    int countByExample(SatisfactionExample example) throws Exception;

    int deleteByExample(SatisfactionExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Satisfaction record) throws Exception;

    int insertSelective(Satisfaction record) throws Exception;

    List<Satisfaction> selectByExample(SatisfactionExample example) throws Exception;

    Satisfaction selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Satisfaction record, @Param("example") SatisfactionExample example) throws Exception;

    int updateByExample(@Param("record") Satisfaction record, @Param("example") SatisfactionExample example) throws Exception;

    int updateByPrimaryKeySelective(Satisfaction record) throws Exception;

    int updateByPrimaryKey(Satisfaction record)  throws Exception;

	Satisfaction selectBydetailId(Integer orderdetailId) throws Exception;
}