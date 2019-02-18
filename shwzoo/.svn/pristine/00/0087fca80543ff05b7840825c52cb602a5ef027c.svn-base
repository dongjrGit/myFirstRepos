package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.Api_FreightDto;
import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.po.FreightExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface FreightMapper {
    int countByExample(FreightExample example);

    int deleteByExample(FreightExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Freight record);

    int insertSelective(Freight record);

    List<Freight> selectByExample(FreightExample example);

    Freight selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Freight record,
                                 @Param("example") FreightExample example);

    int updateByExample(@Param("record") Freight record, @Param("example") FreightExample example);

    int updateByPrimaryKeySelective(Freight record);

    int updateByPrimaryKey(Freight record);

    List<Freight> selectByShop(Integer shopid) throws Exception;

    List<Freight> getByShopPage(PageBeanUtil pageBeanUtil) throws Exception;

    List<Freight> selectByPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 根据店铺id查询模板列表
     * @param pageBeanUtil
     * @return
     */
    List<Freight> selectByShopIdPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    /**
     * 根据店铺id和省名称查询运费模板
     * @param shopid
     * @param provinceName
     * @return
     */
    List<Api_FreightDto> queryByShopid(Integer shopid, String provinceName);
	//List<Api_FreightDto> queryByShopid(Map<String, Object> map);
	
	/**
	 * 查询默认模板
	 * @param map
	 * @return
	 */
	Api_FreightDto queryDefaultFreight(Integer shopid);

	
}