package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Commentreply;
import com.yinlian.wssc.web.po.CommentreplyExample;

public interface CommentreplyMapper {
    int countByExample(CommentreplyExample example);

    int deleteByExample(CommentreplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Commentreply record);

    int insertSelective(Commentreply record);

    List<Commentreply> selectByExample(CommentreplyExample example);

    Commentreply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Commentreply record, @Param("example") CommentreplyExample example);

    int updateByExample(@Param("record") Commentreply record, @Param("example") CommentreplyExample example);

    int updateByPrimaryKeySelective(Commentreply record);

    int updateByPrimaryKey(Commentreply record);
    /**
     * 根据parentid查询回复
     * @param parentid
     * @return
     */
	List<Commentreply> selectByParentId(Integer parentid);
}