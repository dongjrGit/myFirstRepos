package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.FeedbackDto;
import com.yinlian.wssc.web.dto.GoodConsultDto;
import com.yinlian.wssc.web.po.Feedback;
import com.yinlian.wssc.web.po.FeedbackExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface FeedbackMapper {
    int countByExample(FeedbackExample example);

    int deleteByExample(FeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    List<Feedback> selectByExample(FeedbackExample example);

    Feedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByExample(@Param("record") Feedback record, @Param("example") FeedbackExample example);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

	List<FeedbackDto> selectFeedBackByPage(PageBeanUtil pageBeanUtil) throws Exception;
}