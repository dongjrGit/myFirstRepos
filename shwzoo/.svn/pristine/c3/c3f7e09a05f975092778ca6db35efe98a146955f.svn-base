/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.FileRecords;
import com.yinlian.wssc.web.po.ImgrecordsExample;

/**
 * 图片服务的mapper接口
 * ImgrecordsMapper.java
 * @author Administrator
 * @version $Id: ImgrecordsMapper.java, v 0.1 2016年3月23日 下午5:19:36 Administrator Exp $
 */
public interface FileRecordsMapper {

    int countByExample(ImgrecordsExample example);

    int deleteByExample(ImgrecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FileRecords record);

    int insertSelective(FileRecords record);

    List<FileRecords> selectByExample(ImgrecordsExample example);

    FileRecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FileRecords record,
                                 @Param("example") ImgrecordsExample example);

    int updateByExample(@Param("record") FileRecords record,
                        @Param("example") ImgrecordsExample example);

    int updateByPrimaryKeySelective(FileRecords record);

    int updateByPrimaryKey(FileRecords record);
}
