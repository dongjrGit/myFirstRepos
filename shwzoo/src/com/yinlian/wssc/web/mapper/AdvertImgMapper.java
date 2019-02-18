package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.AdvertImgAppDto;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.AdvertImgExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface AdvertImgMapper {
    int countByExample(AdvertImgExample example) throws Exception;

    int deleteByExample(AdvertImgExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(AdvertImg record) throws Exception;

    int insertSelective(AdvertImg record) throws Exception;

    List<AdvertImg> selectByExample(AdvertImgExample example) throws Exception;

    AdvertImg selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") AdvertImg record,
                                 @Param("example") AdvertImgExample example) throws Exception;

    int updateByExample(@Param("record") AdvertImg record,
                        @Param("example") AdvertImgExample example) throws Exception;

    int updateByPrimaryKeySelective(AdvertImg record) throws Exception;

    int updateByPrimaryKey(AdvertImg record) throws Exception;

    List<AdvertImg> getListByPage(PageBeanUtil pageBeanUtil) throws Exception;

    int deleteAdvert(Integer id) throws Exception;

    int updateOrder(Integer sort, Integer id) throws Exception;

    int updateStatus(Integer status, Integer id) throws Exception;
    
    List<AdvertImgAppDto> getListByType(@Param(value="type") Integer type,@Param(value="position") Integer position) throws Exception;

	List<AdvertImgAppDto> getListByTypes(Integer type, Integer position);
	
	List<AdvertImg> getListByShopId(Integer shopid)throws Exception;
	
}