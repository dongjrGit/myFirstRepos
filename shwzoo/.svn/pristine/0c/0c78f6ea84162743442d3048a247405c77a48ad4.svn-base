package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.TopicRelateMapper;
import com.yinlian.wssc.web.po.TopicRelate;
import com.yinlian.wssc.web.service.TopicRelateService;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 首页主题关联表service
 * @author admin
 *
 */
@Component("topicRelateService")
public class TopicRelateServiceImpl implements TopicRelateService {

	private static final Logger  logger = LoggerFactory.getLogger(AccountsServiceImpl.class);
	
	@Autowired
	private TopicRelateMapper     topicRelateMapper;

	@Override
	public List<TopicRelate> selectByTopicId(Integer topicId) throws Exception {
		
		return topicRelateMapper.queryByTopicId(topicId);
	}

	/**
	 * 分页查询专题关联的数据
	 * @see com.yinlian.wssc.web.service.TopicRelateService#queryTopicRelateListByCriteria(com.yinlian.wssc.web.util.CriteriaTopic, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<TopicRelate> beanList = topicRelateMapper.selectTopicRelateByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
	}

	/**
	 * 
	 * @see com.yinlian.wssc.web.service.TopicRelateService#insert(com.yinlian.wssc.web.po.TopicRelate)
	 */
	@Override
	public int insert(TopicRelate topicRelate) throws Exception {
		return topicRelateMapper.insertSelective(topicRelate);
	}

	/**
	 * 
	 * @see com.yinlian.wssc.web.service.TopicRelateService#queryById(java.lang.Integer)
	 */
	@Override
	public TopicRelate queryById(Integer id) throws Exception {
		
		return topicRelateMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * @see com.yinlian.wssc.web.service.TopicRelateService#update(com.yinlian.wssc.web.po.TopicRelate)
	 */
	@Override
	public int update(TopicRelate topicRelate) throws Exception {
		
		return topicRelateMapper.updateByTopicRelate(topicRelate);
	}

	/**
	 * 根据id删除一个专题
	 * @see com.yinlian.wssc.web.service.TopicRelateService#deleteById(java.lang.Integer)
	 */
	@Override
	public int deleteById(Integer id) throws Exception {
		
		return topicRelateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TopicRelate> getRelatedidAndreleteId(Integer topicid,
			Integer relatedid) {
		return topicRelateMapper.getRelatedidAndreleteId(topicid, relatedid);
	}
	
	
	
}
