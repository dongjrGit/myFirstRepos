package com.yinlian.wssc.web.mapper;

import com.yinlian.wssc.web.po.SpecialProtype;
import com.yinlian.wssc.web.po.SpecialProtypeExample;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.TopicCriteria;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecialProtypeMapper {
   
    int countByExample(SpecialProtypeExample example)throws Exception;

    int deleteByExample(SpecialProtypeExample example)throws Exception;

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(SpecialProtype record)throws Exception;

    int insertSelective(SpecialProtype record)throws Exception;

    List<SpecialProtype> selectByExample(SpecialProtypeExample example)throws Exception;

    SpecialProtype selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") SpecialProtype record, 
    		@Param("example") SpecialProtypeExample example)throws Exception;

    int updateByExample(@Param("record") SpecialProtype record, 
    		@Param("example") SpecialProtypeExample example)throws Exception;

    int updateByPrimaryKeySelective(SpecialProtype record)throws Exception;

    int updateByPrimaryKey(SpecialProtype record)throws Exception;
    
    List<SpecialProtype> selectBySpecialProtypePage(PageBeanUtil pageBeanUtil)throws Exception;

    /**
     * 专题页分类查询
     * @param criteria
     * @return
     * @throws Exception
     */
	List<SpecialProtype> selectByTopicCruteria(TopicCriteria criteria)throws Exception;

}