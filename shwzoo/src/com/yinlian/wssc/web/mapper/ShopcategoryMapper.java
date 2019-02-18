package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Shopcategory;
import com.yinlian.wssc.web.po.ShopcategoryExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ShopcategoryMapper {
    int countByExample(ShopcategoryExample example);

    int deleteByExample(ShopcategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Shopcategory record);

    int insertSelective(Shopcategory record);

    List<Shopcategory> selectByExample(ShopcategoryExample example);

    Shopcategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Shopcategory record, @Param("example") ShopcategoryExample example);

    int updateByExample(@Param("record") Shopcategory record, @Param("example") ShopcategoryExample example);

    int updateByPrimaryKeySelective(Shopcategory record);

    int updateByPrimaryKey(Shopcategory record);
    /**
     * 分页查询店铺分类
     * 
     * @param pageBeanUtil
     * @return
     */
	List<Shopcategory> selectShopCategoryByPage(PageBeanUtil pageBeanUtil);
	/**
	 * 根据shopid查询分类
	 * 
	 * @param shopid
	 * @return
	 */
	Shopcategory selectByShopName(String name);

	List<Shopcategory> getAllList() throws Exception;

	List<Shopcategory> getThreeAllList();

	List<Shopcategory> queryByShopId(Integer id);
	
	Shopcategory getByName(@Param("id")Integer id,@Param("name")String name)throws Exception;
}