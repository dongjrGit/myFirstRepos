package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.Api_CommentDto;
import com.yinlian.pc.dto.CommentDto;
import com.yinlian.wssc.search.Api_CommentCriteria;
import com.yinlian.wssc.search.Wap_CommentCriteria;
import com.yinlian.wssc.web.dto.MemberCommentDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 会员列表的业务类
 * 
 * @author Administrator
 *
 */
public interface CommentService {

	/**
	 * 分页查询会员评论列表
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	PageBean queryMemberCommentByCriteria(Criteria criteria, Integer pc, Integer ps) throws Exception;

	public int insertComm(Comment comment, Integer spuid) throws Exception;

	public int insertComm(Comment comment) throws Exception;

	public int insertUserComm(Comment comment) throws Exception;

	/**
	 * 根据id查询评论明细
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	MemberCommentDto queryById(Integer id) throws Exception;

	/**
	 * 根据id查询评论信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Comment queryCommentById(Integer id) throws Exception;

	/**
	 * 更改一条评论信息
	 * 
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	int updateCommentById(Comment comment) throws Exception;

	/**
	 * 获取商品详细评论列表
	 * 
	 * @param acc
	 * @param pindex
	 * @param psize
	 * @return
	 * @throws Exception
	 */
	PageBean getProductDetailedCommentList(Api_CommentCriteria acc, Integer pindex, Integer psize) throws Exception;

	/**
	 * 获取商品详细评论列表（wap）
	 * 
	 * @param acc
	 * @param pindex
	 * @param psize
	 * @return
	 * @throws Exception
	 */
	PageBean getProductDetailedCommentList(Wap_CommentCriteria acc, Integer pindex, Integer psize) throws Exception;

	Comment selectByOrderId(Integer orderid) throws Exception;

	public int insertOrderComm(List<Api_CommentDto> comment, Integer orderid, Integer gooddescription,
			Integer sellerattitude, Integer logisticsspeed) throws Exception;

	/**
	 * 根据spuid获取评论列表
	 * @param criteria
	 * @param pageindex
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	PageBean getCommentBySpuId(Api_CommentCriteria criteria, Integer pageindex, Integer pagesize) throws Exception;
}
