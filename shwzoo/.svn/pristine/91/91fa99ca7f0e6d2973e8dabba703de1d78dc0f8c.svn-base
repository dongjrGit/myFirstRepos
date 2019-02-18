package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Floorproduct;
import com.yinlian.wssc.web.po.FloorproductExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface FloorproductMapper {
    int countByExample(FloorproductExample example);

    int deleteByExample(FloorproductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Floorproduct record);

    int insertSelective(Floorproduct record);

    List<Floorproduct> selectByExample(FloorproductExample example);

    Floorproduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Floorproduct record, @Param("example") FloorproductExample example);

    int updateByExample(@Param("record") Floorproduct record, @Param("example") FloorproductExample example);

    int updateByPrimaryKeySelective(Floorproduct record);

    int updateByPrimaryKey(Floorproduct record);
    
    /**
     * 通过楼层id查询楼层关联信息
     * @param floorid
     * @return
     */
    int deleteByFloorId(Integer floorid);
    
    /**
     * 分页查询楼层关联信息
     * 
     * @param pageBeanUtil
     * @return
     */
	List<Floorproduct> selectFloorByPage(PageBeanUtil pageBeanUtil);
}