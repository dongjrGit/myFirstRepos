package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.api.app.dto.ShopCategoryDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ShopcategoryMapper;
import com.yinlian.wssc.web.po.Shopcategory;
import com.yinlian.wssc.web.service.ShopcategoryService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("shopcategoryService")
public class ShopcategoryServiceImpl implements ShopcategoryService {
    @Autowired
    private ShopcategoryMapper shopcategoryMapper;

    // 分页查询商品分类
    @Override
    public PageBean queryShopCategoryList(Criteria criteria, Integer pc, Integer ps)
                                                                                    throws Exception {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
                                                                       // 设置其他的参数
                                                                       // 多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Shopcategory> beanList = shopcategoryMapper.selectShopCategoryByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /**
     * 删除商品分类
     * 
     * @see com.yinlian.wssc.web.service.ShopcategoryService#delShopCategory(java.lang.Integer)
     */
    @Override
    public int delShopCategory(Integer id) throws Exception {
        Shopcategory shopcategory = shopcategoryMapper.selectByPrimaryKey(id);
        shopcategory.setIsdel(true);
        return shopcategoryMapper.updateByPrimaryKey(shopcategory);
    }

    /**
     * 根据id查询店铺
     * 
     * @see com.yinlian.wssc.web.service.ShopcategoryService#queryById(java.lang.Integer)
     */
    @Override
    public Shopcategory queryById(Integer id) throws Exception {

        return shopcategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 编辑店铺分类
     * 
     * @see com.yinlian.wssc.web.service.ShopcategoryService#updatCategoryById(java.lang.Integer)
     */
    @Override
    public int updatCategoryById(Integer id, String categoryName) throws Exception {
        Shopcategory shopcategory = shopcategoryMapper.selectByPrimaryKey(id);
        shopcategory.setName(categoryName);
        shopcategory.setCreattime(new Date());
        return shopcategoryMapper.updateByPrimaryKey(shopcategory);
    }

    /**
     * 根据店铺id查询分类
     * 
     * @see com.yinlian.wssc.web.service.ShopcategoryService#queryByShopId(java.lang.Integer)
     */
    @Override
    public Shopcategory queryByName(String name) throws Exception {

        return shopcategoryMapper.selectByShopName(name);
    }

    @Override
    public List<ShopCategoryDto> getAllList() throws Exception {
        List<ShopCategoryDto> dtos = new ArrayList<ShopCategoryDto>();
        List<Shopcategory> list = shopcategoryMapper.getAllList();
        if (list != null) {
            for (Shopcategory shopcategory : list) {
                ShopCategoryDto dto = new ShopCategoryDto();
                BeanUtils.copyProperties(shopcategory, dto);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    /**
     * 添加店铺
     * 
     * @see com.yinlian.wssc.web.service.ShopcategoryService#addCategoryName(java.lang.String)
     */
    @Override
    public int addCategoryName(String name) throws Exception {
        Shopcategory shopcategory = new Shopcategory();
        shopcategory.setName(name);
        shopcategory.setCreattime(new Date());
        shopcategory.setIsdel(false);
        return shopcategoryMapper.insert(shopcategory);
    }

    /**
     * 获取第三级分类
     */
	@Override
	public List<ShopCategoryDto> getThreeAllList() throws Exception {
		 List<ShopCategoryDto> dtos = new ArrayList<ShopCategoryDto>();
	        List<Shopcategory> list = shopcategoryMapper.getThreeAllList();
	        if (list != null) {
	            for (Shopcategory shopcategory : list) {
	                ShopCategoryDto dto = new ShopCategoryDto();
	                BeanUtils.copyProperties(shopcategory, dto);
	                dtos.add(dto);
	            }
	        }
	        return dtos;
	}

	@Override
	public List<Shopcategory> queryByShopId(Integer integer) {
		return shopcategoryMapper.queryByShopId(integer);
	}

	@Override
	public Shopcategory getByName(String name, Integer id) throws Exception {

		return shopcategoryMapper.getByName(id, name);
	}

}
