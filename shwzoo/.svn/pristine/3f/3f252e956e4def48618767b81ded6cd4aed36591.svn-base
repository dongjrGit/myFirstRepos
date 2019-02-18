package com.yinlian.wssc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.CommentshowimgMapper;
import com.yinlian.wssc.web.po.Commentshowimg;
import com.yinlian.wssc.web.service.CommentshowimgService;
/**
 * 晒单照片业务类的实现接口
 * @author Administrator
 *
 */
@Component("commentshowimgServers")
public class CommentshowimgServersImpl implements CommentshowimgService {

	@Autowired
	private CommentshowimgMapper commentshowimgMapper;
	/**
	 * 根据id查询晒单照片
	 * @see com.yinlian.wssc.web.service.CommentshowimgService#queryById(java.lang.Integer)
	 */
	@Override
	public Commentshowimg queryById(Integer id) throws Exception {
		
		return commentshowimgMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据id修改晒单照片
	 * @see com.yinlian.wssc.web.service.CommentshowimgService#updateById(com.yinlian.wssc.web.po.Commentshowimg)
	 */
	@Override
	public int updateById(Commentshowimg commentshowimg) throws Exception {
		
		return commentshowimgMapper.updateByPrimaryKey(commentshowimg);
	}
	@Override
	public  int insert(Commentshowimg commentshowimg) throws Exception{
		return commentshowimgMapper.insert(commentshowimg);
	}
}
