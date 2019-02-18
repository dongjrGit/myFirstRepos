package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.Api_MessageDto;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.po.MessagesExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface MessagesMapper {
    int countByExample(MessagesExample example) throws Exception;

    int deleteByExample(MessagesExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Messages record) throws Exception;

    int insertSelective(Messages record) throws Exception;

    List<Messages> selectByExampleWithBLOBs(MessagesExample example) throws Exception;

    List<Messages> selectByExample(MessagesExample example) throws Exception;

    Messages selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Messages record,
                                 @Param("example") MessagesExample example) throws Exception;

    int updateByExampleWithBLOBs(@Param("record") Messages record,
                                 @Param("example") MessagesExample example) throws Exception;

    int updateByExample(@Param("record") Messages record, @Param("example") MessagesExample example)
                                                                                                    throws Exception;

    int updateByPrimaryKeySelective(Messages record) throws Exception;

    int updateByPrimaryKeyWithBLOBs(Messages record) throws Exception;

    int updateByPrimaryKey(Messages record) throws Exception;

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Messages> selectMessageByUserIdPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 批量删除
     * @param array
     * @return
     */
    int deleteByIds(String[] array) throws Exception;

    /**
     * 批量更新
     * @param array
     * @return
     */
    int updateByIds(String[] array) throws Exception;

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBacth(List<Messages> list) throws Exception;
    
    /**
     * 未读消息数量
     * @param userid
     * @return
     * @throws Exception
     */
	int selectCount(int userid) throws Exception;
	
	int deleteByUserid(int userid) throws Exception;

	List<Messages> selectByUserId(Integer userid) throws Exception;

	List<Api_MessageDto> getMessagesByUserPage(PageBeanUtil pageBeanUtil);
	/**
	 * 设置账户信息已读
	 * @param uid 用户id
	 * @return
	 * @throws Exception
	 */
	int updateSetMes(@Param("uid") Integer uid) throws Exception;
}