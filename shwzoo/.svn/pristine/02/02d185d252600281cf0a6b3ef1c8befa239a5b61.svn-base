package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.TopicRelate;
import com.yinlian.wssc.web.po.TopicRelateExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface TopicRelateMapper {
    int countByExample(TopicRelateExample example);

    int deleteByExample(TopicRelateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TopicRelate record);

    int insertSelective(TopicRelate record);

    List<TopicRelate> selectByExample(TopicRelateExample example);

    TopicRelate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TopicRelate record,
                                 @Param("example") TopicRelateExample example);

    int updateByExample(@Param("record") TopicRelate record,
                        @Param("example") TopicRelateExample example);

    int updateByPrimaryKeySelective(TopicRelate record);

    int updateByPrimaryKey(TopicRelate record);

    /**
     * 通过专题id查询专题关联信息
     * @param topicId
     * @return
     */
    List<TopicRelate> queryByTopicId(Integer topicId);

    /**
     * 分页查询专题关联的数据
     * 
     * @param pageBeanUtil
     * @return
     */
	List<TopicRelate> selectTopicRelateByPage(PageBeanUtil pageBeanUtil);

	int updateByTopicRelate(TopicRelate topicRelate);
	
	/**
	 * 根据专题id查询关联ID
	 * @param id
	 * @return
	 */
	List<Integer> getRelatedid(Integer id);
	
	List<TopicRelate> getRelatedidAndreleteId(Integer topicid,Integer relatedid);
}