package com.yinlian.wssc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.CommentreplyMapper;
import com.yinlian.wssc.web.po.Commentreply;
import com.yinlian.wssc.web.service.CommentreplyService;
/**
 * 回复表业务接口的实现类
 * @author Administrator
 *
 */
@Component("commentreplyService")
public class CommentreplyServiceImpl implements CommentreplyService {

	@Autowired
	private CommentreplyMapper commentreplyMapper;
	/**
	 * 添加回复表一条数据
	 * @see com.yinlian.wssc.web.service.CommentreplyService#addReply(com.yinlian.wssc.web.po.Commentreply)
	 */
	@Override
	public int addReply(Commentreply commentreply) throws Exception {
		return commentreplyMapper.insert(commentreply);
	}

}
