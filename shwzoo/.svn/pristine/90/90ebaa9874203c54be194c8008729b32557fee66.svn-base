package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Sendtemplate;
import com.yinlian.wssc.web.po.SendtemplateExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface SendtemplateMapper {
    int countByExample(SendtemplateExample example) throws Exception;

    int deleteByExample(SendtemplateExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Sendtemplate record) throws Exception;

    int insertSelective(Sendtemplate record) throws Exception;

    List<Sendtemplate> selectByExampleWithBLOBs(SendtemplateExample example) throws Exception;

    List<Sendtemplate> selectByExample(SendtemplateExample example) throws Exception;

    Sendtemplate selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Sendtemplate record,
                                 @Param("example") SendtemplateExample example) throws Exception;

    int updateByExampleWithBLOBs(@Param("record") Sendtemplate record,
                                 @Param("example") SendtemplateExample example) throws Exception;

    int updateByExample(@Param("record") Sendtemplate record,
                        @Param("example") SendtemplateExample example) throws Exception;

    int updateByPrimaryKeySelective(Sendtemplate record) throws Exception;

    int updateByPrimaryKeyWithBLOBs(Sendtemplate record) throws Exception;

    int updateByPrimaryKey(Sendtemplate record) throws Exception;

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Sendtemplate> selectPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 
     * @param ids
     * @return
     */
    int deleteList(String[] array) throws Exception;

    List<Sendtemplate> getSendtemplateByPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    
    Sendtemplate getByType(int type,int ctype, int tag) throws Exception;
}