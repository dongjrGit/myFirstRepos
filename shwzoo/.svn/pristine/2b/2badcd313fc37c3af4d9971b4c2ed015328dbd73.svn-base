package com.yinlian.wssc.web.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.FeedbackDto;
import com.yinlian.wssc.web.dto.GoodConsultDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.FeedbackMapper;
import com.yinlian.wssc.web.po.Feedback;
import com.yinlian.wssc.web.service.FeedBackService;
import com.yinlian.wssc.web.util.CriteriaMemberFeedBack;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {
	
	private static final Logger  logger = LoggerFactory.getLogger(FeedBackServiceImpl.class);
	 
	@Autowired
	private   FeedbackMapper          feedbackmapper;
	 
	@Override
	public int insert(Feedback feedback) throws Exception {
		
		return feedbackmapper.insert(feedback);
	}

	@Override
	public PageBean queryMemberFeedBackByCriteria(CriteriaMemberFeedBack criteria, Integer pc, Integer ps)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<FeedbackDto> beanList = feedbackmapper.selectFeedBackByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public Feedback queryById(Integer id) throws Exception {
		
		return feedbackmapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateFeedBack(Feedback dto) throws Exception {
		
		return feedbackmapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public int deleteFeedBack(Integer id) throws Exception {
		
		return feedbackmapper.deleteByPrimaryKey(id);
	}

}
