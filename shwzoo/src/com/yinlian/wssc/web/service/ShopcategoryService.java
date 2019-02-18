package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.ShopCategoryDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Shopcategory;
import com.yinlian.wssc.web.util.Criteria;

public interface ShopcategoryService {
    /**
     * 分页查询店铺分类
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     */
    PageBean queryShopCategoryList(Criteria criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 删除店铺分类
     * 
     * @return
     * @throws Exception
     */
    int delShopCategory(Integer id) throws Exception;

    /**
     * 根据id查询店铺
     * 
     * @param id
     * @return
     * @throws Exception
     */
    Shopcategory queryById(Integer id) throws Exception;

    /**
     * 编辑店铺分类
     * 
     * @param id
     * @return
     * @throws Exception
     */
    int updatCategoryById(Integer id, String categoryName) throws Exception;

    /**
     * 根据店铺id查询分类
     * 
     * @param name
     * @return
     */
    Shopcategory queryByName(String name) throws Exception;

    /**
     * 添加店铺
     * 
     * @param name
     * @return
     */
    int addCategoryName(String name) throws Exception;

    List<ShopCategoryDto> getAllList() throws Exception;
    
    List<ShopCategoryDto> getThreeAllList() throws Exception;

	List<Shopcategory> queryByShopId(Integer sid)throws Exception;
	
	/**
	 * 查询是否有重名的店铺分类
	 * @param name
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Shopcategory getByName(String name,Integer id) throws Exception;

}
