package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.AdvertisingExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface AdvertisingMapper {
    int countByExample(AdvertisingExample example);

    int deleteByExample(AdvertisingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Advertising record);

    int insertSelective(Advertising record);

    List<Advertising> selectByExample(AdvertisingExample example);

    Advertising selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Advertising record, @Param("example") AdvertisingExample example);

    int updateByExample(@Param("record") Advertising record, @Param("example") AdvertisingExample example);

    int updateByPrimaryKeySelective(Advertising record);

    int updateByPrimaryKey(Advertising record);

	List<Advertising> getListByPage(PageBeanUtil pBeanUtil) throws Exception;

	int updateStatus(Integer status, Integer id) throws Exception;

	List<Advertising> getListByType(int type,int webset) throws Exception;
	
	List<Advertising> getListByTypeAndDisplay(int type,int display,int webset,@Param("shopid")Integer shopid) throws Exception;

	Advertising getByAdvertact(int type,@Param("status")Integer status) throws Exception;
}