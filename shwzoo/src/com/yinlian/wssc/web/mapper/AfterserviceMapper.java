package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.pc.dto.AfterserviceDto;
import com.yinlian.wssc.web.po.Afterservice;
import com.yinlian.wssc.web.po.AfterserviceExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface AfterserviceMapper {
    int countByExample(AfterserviceExample example)throws Exception;

    int deleteByExample(AfterserviceExample example)throws Exception;

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(Afterservice record)throws Exception;

    int insertSelective(Afterservice record)throws Exception;

    List<Afterservice> selectByExampleWithBLOBs(AfterserviceExample example)throws Exception;

    List<Afterservice> selectByExample(AfterserviceExample example)throws Exception;

    Afterservice selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") Afterservice record, @Param("example") AfterserviceExample example)throws Exception;

    int updateByExampleWithBLOBs(@Param("record") Afterservice record, @Param("example") AfterserviceExample example)throws Exception;

    int updateByExample(@Param("record") Afterservice record, @Param("example") AfterserviceExample example)throws Exception;

    int updateByPrimaryKeySelective(Afterservice record)throws Exception;

    int updateByPrimaryKeyWithBLOBs(Afterservice record)throws Exception;

    int updateByPrimaryKey(Afterservice record)throws Exception;
    int updatenoneExpress(Afterservice record)throws Exception;
    Afterservice getByOrderAndType(int orderid,int type,int status)throws Exception;
    int updatebExpress(Afterservice record)throws Exception;
    /**
	 * 分页查询售后服务
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<AfterserviceDto> selectAfterByPage(PageBeanUtil pageBeanUtil) throws Exception;

	Afterservice getAfterserviceByid(Integer id) throws Exception;

}