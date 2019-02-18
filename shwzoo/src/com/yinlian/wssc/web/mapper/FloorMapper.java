package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.FloorProsDto;
import com.yinlian.wssc.web.po.Floor;
import com.yinlian.wssc.web.po.FloorExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface FloorMapper {
    int countByExample(FloorExample example);

    int deleteByExample(FloorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Floor record);

    int insertSelective(Floor record);

    List<Floor> selectByExample(FloorExample example);

    Floor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Floor record, @Param("example") FloorExample example);

    int updateByExample(@Param("record") Floor record, @Param("example") FloorExample example);

    int updateByPrimaryKeySelective(Floor record);

    int updateByPrimaryKey(Floor record);
    /**
     * 查询所有的楼层
     * @return 
     */
    List<Floor> selectAll();
    /**
     * 根据站点类型查询所有的楼层
     * @return 
     */
    List<FloorProsDto> selectAllByWebSet(@Param("webset") String webset);
    /**
     * 分页查询楼层
     * 
     * @param pageBeanUtil
     * @return
     */
	List<Floor> selectFloorByPage(PageBeanUtil pageBeanUtil);
}