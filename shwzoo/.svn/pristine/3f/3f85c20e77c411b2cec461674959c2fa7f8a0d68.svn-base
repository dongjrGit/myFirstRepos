package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.CircleDto;
import com.yinlian.wssc.web.po.Circle;
import com.yinlian.wssc.web.po.CircleExample;
import com.yinlian.wssc.web.po.Shopcategory;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface CircleMapper {
    int countByExample(CircleExample example);

    int deleteByExample(CircleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Circle record);

    int insertSelective(Circle record);

    List<Circle> selectByExample(CircleExample example);

    Circle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Circle record,
                                 @Param("example") CircleExample example);

    int updateByExample(@Param("record") Circle record, @Param("example") CircleExample example);

    int updateByPrimaryKeySelective(Circle record);

    int updateByPrimaryKey(Circle record);

    List<CircleDto> queryAllByPage(PageBeanUtil pageBeanUtil);

    /**
     * 分页查询商圈列表
     * 
     * @param pageBeanUtil
     * @return
     * @throws Exception
     */
    List<Shopcategory> selectBusinessDistrictByPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     *@param circlename
     *@return
     */
    Circle queryByName(String circlename) throws Exception;

    /**
     *@param record
     */
    int insertForId(Circle record) throws Exception;

}