package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.util.CriteriaCommodity;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface CategoryMapperCustom {

    /**
     * 查询所有的一级分类
     * 
     * @return
     * @throws Exception
     */
    public List<CategoryDTO> selectAllCategorys(Integer fatherid) throws Exception;
    
    
    /**
     * 查询所有分类
     * 
     * @return
     * @throws Exception
     */
    public List<CategoryDTO> selectAll(@Param("fatherid")int fatherid) throws Exception;
    
    
    /**
     * 查询所有的子级分类
     * 
     * @return
     * @throws Exception
     */
    public List<CategoryDTO> selectSonCategorys(Integer fatherid) throws Exception;
    
    /**
     * 添加分类信息
     * @param category
     * @return
     * @throws Exception
     */
    public int addCategory(Category category) throws Exception;
    
    /**
     * 修改分类全路径
     * @param category
     * @return
     * @throws Exception
     */
    public int updateFullpath(Category category) throws Exception;
    
    /**
     * 删除分类
     * @param category
     * @return
     * @throws Exception
     */
    public int delCategory(Category category) throws Exception;
    
    /**
     * 根据店铺获取店铺下对应分类
     * @param shopid
     * @return
     * @throws Exception
     */
    public List<CategoryDTO> selectCategoryByShop(Integer shopid)throws Exception;
    
    /**
     * 根据分类和店铺ID获取自定义分类
     * @param classid
     * @param shopid
     * @return
     * @throws Exception
     */
    public List<CategoryDTO> getByClassShop(Integer classid,Integer shopid)throws Exception;
    
    
    /**
     * 根据店铺ID获取自定义分类（分页）
     * @param pageBeanUtil
     * @return
     * @throws Exception
     */
    public List<CategoryDTO> getClassByShopPage(PageBeanUtil pageBeanUtil)throws Exception;
    
    
    public int updateStatus(Category category) throws Exception;
    
    public List<CategoryDTO> getByIDs(CriteriaCommodity c)throws Exception;
    
    
    public int updateStatusList(List<Category> list) throws Exception;
    
    public List<CategoryDTO> getCustomCheckPage(PageBeanUtil pageBeanUtil)throws Exception;


    /**
     * 营销范围获取集合
     * @param ids
     * @param i 分类等级
     * @param fatherid 父ID 0是首
     * @return
     * @throws Exception
     */
	public List<CategoryDTO> getByDtoIds(@Param("ids")String[] ids,@Param("i")int i,@Param("fatherid")Integer fatherid)throws Exception;

	/**
     * 营销范围获取集合 一级
     * @param ids
     * @param fatherid 父ID 0是首
     * @return
     * @throws Exception
     */
	public List<CategoryDTO> getByDtoIds1(@Param("ids")List<String> ids1,@Param("fatherid") Integer fatherid)throws Exception;
    
	/**
     * 营销范围获取集合 二级
     * @param ids
     * @param fatherid 父ID 0是首
     * @return
     * @throws Exception
     */
	public List<CategoryDTO> getByDtoIds2(@Param("ids")List<String> ids1,@Param("fatherid") Integer fatherid)throws Exception;
    
	/**
     * 营销范围获取集合 三级
     * @param ids
     * @param fatherid 父ID 0是首
     * @return
     * @throws Exception
     */
	public List<CategoryDTO> getByDtoIds3(@Param("ids")List<String> ids1,@Param("fatherid") Integer fatherid)throws Exception;
    
}