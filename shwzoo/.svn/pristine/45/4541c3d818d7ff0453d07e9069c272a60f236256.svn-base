package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.CommentBaseDto;
import com.yinlian.wssc.web.dto.MemberCommentDto;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.CommentAbr;
import com.yinlian.wssc.web.po.CommentExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface CommentMapper {
    int countByExample(CommentExample example) throws Exception;

    int deleteByExample(CommentExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Comment record) throws Exception;

    int insertSelective(Comment record) throws Exception;

    List<Comment> selectByExample(CommentExample example) throws Exception;

    Comment selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Comment record,
                                 @Param("example") CommentExample example) throws Exception;

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example)
                                                                                                  throws Exception;

    int updateByPrimaryKeySelective(Comment record) throws Exception;

    int updateByPrimaryKey(Comment record) throws Exception;
    

    /**
     * 根据分页查询会员评论
     * @param pageBeanUtil
     * @return
     */
    List<MemberCommentDto> selectMemberCommentByPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 根据id查询评论明细
     * @param id
     * @return
     */
    MemberCommentDto selectById(Integer id) throws Exception;

    CommentAbr getCommentAbr(int spuid) throws Exception;

    List<CommentBaseDto> getApiCmtListPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    Comment selectByOrderId(Integer orderid) throws Exception;

	List<CommentBaseDto> getComBySpuIdByPage(PageBeanUtil beanUtil)throws Exception;


}