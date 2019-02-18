package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.dto.FeedbackDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Feedback;
import com.yinlian.wssc.web.util.CriteriaMemberFeedBack;

public interface FeedBackService {
	/**
	 * 添加一条服务反馈反馈
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public int insert(Feedback feedback) throws Exception;

	public PageBean queryMemberFeedBackByCriteria(CriteriaMemberFeedBack criteria, Integer pc, Integer ps) throws Exception;

	public Feedback queryById(Integer id) throws Exception;

	public int updateFeedBack(Feedback dto) throws Exception;

	public int deleteFeedBack(Integer id) throws Exception;
}
