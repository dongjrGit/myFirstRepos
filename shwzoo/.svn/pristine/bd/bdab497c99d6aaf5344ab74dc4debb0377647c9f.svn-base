package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Messagerecords;
import com.yinlian.wssc.web.po.MessagerecordsExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface MessagerecordsMapper {
    int countByExample(MessagerecordsExample example);

    int deleteByExample(MessagerecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Messagerecords record);

    int insertSelective(Messagerecords record);

    List<Messagerecords> selectByExampleWithBLOBs(MessagerecordsExample example);

    List<Messagerecords> selectByExample(MessagerecordsExample example);

    Messagerecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Messagerecords record,
                                 @Param("example") MessagerecordsExample example);

    int updateByExampleWithBLOBs(@Param("record") Messagerecords record,
                                 @Param("example") MessagerecordsExample example);

    int updateByExample(@Param("record") Messagerecords record,
                        @Param("example") MessagerecordsExample example);

    int updateByPrimaryKeySelective(Messagerecords record);

    int updateByPrimaryKeyWithBLOBs(Messagerecords record);

    int updateByPrimaryKey(Messagerecords record);

    /**
     * 批量插入
     * @param array
     * @param title
     * @param content
     * @return
     */
    int insertBacth(List<Messagerecords> list) throws Exception;

    /**
     * 查询已发送的站内信
     * @param pageBeanUtil
     * @return
     */
    List<Messagerecords> selectMessagerecordsByPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 批量删除
     * @param array
     * @return
     */
    int deleteByIds(String[] array) throws Exception;

    /**
     * 查询已发送短信
     * 
     * @param pageBeanUtil
     * @return
     */
	List<Messagerecords> selectMessagerecordByPage(PageBeanUtil pageBeanUtil);
}