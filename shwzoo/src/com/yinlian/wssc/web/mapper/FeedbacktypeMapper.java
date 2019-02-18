package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Feedbacktype;
import com.yinlian.wssc.web.po.FeedbacktypeExample;

public interface FeedbacktypeMapper {
    int countByExample(FeedbacktypeExample example);

    int deleteByExample(FeedbacktypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Feedbacktype record);

    int insertSelective(Feedbacktype record);

    List<Feedbacktype> selectByExample(FeedbacktypeExample example);
    
    List<Feedbacktype> selectAll();

    Feedbacktype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Feedbacktype record, @Param("example") FeedbacktypeExample example);

    int updateByExample(@Param("record") Feedbacktype record, @Param("example") FeedbacktypeExample example);

    int updateByPrimaryKeySelective(Feedbacktype record);

    int updateByPrimaryKey(Feedbacktype record);
}