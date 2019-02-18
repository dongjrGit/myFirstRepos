package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.TopicRelate;
import com.yinlian.wssc.web.util.CriteriaTopic;

public interface TopicRelateService {
	
	/**
	 * 通过专题id查询专题关联信息
	 * @param topicId
	 * @return
	 * @throws Exception
	 */
	public List<TopicRelate> selectByTopicId(Integer topicId) throws Exception;

	/**
	 * 分页查询专题关联的数据
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	public PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps);
	
	/**
	 * 插入一条记录
	 * @param topicRelate
	 * @return
	 * @throws Exception
	 */
	public int insert(TopicRelate  topicRelate) throws Exception;
	
	/**
	 * 通过id查询专题的关联
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TopicRelate queryById(Integer id) throws Exception;
	
	/**
	 * 修改专题关联数据
	 * @param topicRelate
	 * @return
	 * @throws Exception
	 */
	public int update(TopicRelate  topicRelate) throws Exception;

	/**
	 * 根据id删除一个专题
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteById(Integer id) throws Exception;
	
	List<TopicRelate> getRelatedidAndreleteId(Integer topicid,Integer relatedid);
}
