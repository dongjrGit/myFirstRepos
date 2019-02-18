package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.ProductSpecs;
import com.yinlian.wssc.web.po.ProductSpecsCustom;
import com.yinlian.wssc.web.po.ProductSpecsExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ProductSpecsMapper {
    int countByExample(ProductSpecsExample example);

    int deleteByExample(ProductSpecsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpecs record);

    int insertSelective(ProductSpecs record);

    List<ProductSpecs> selectByExample(ProductSpecsExample example);

    ProductSpecs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductSpecs record,
                                 @Param("example") ProductSpecsExample example);

    int updateByExample(@Param("record") ProductSpecs record,
                        @Param("example") ProductSpecsExample example);

    int updateByPrimaryKeySelective(ProductSpecs record);

    int updateOrderBy(Integer orderby, Integer id);

    int updateStatus(Integer status, Integer id);

    int updateByPrimaryKey(ProductSpecs record);

    /**
     * 根据分类id查询商品规格 分页
     * 
     * @param pageBeanUtil
     * @return
     * @throws Exception
     */
    List<ProductSpecs> selectProductSpecsByClassIdPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    
    /**
     * 根据分类id查询商品规格 分页
     * 
     * @param pageBeanUtil
     * @return
     * @throws Exception
     */
    List<ProductSpecs> seletcProductSpecsPageBySonClassId(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 修改可输入状态
     * @param ProductSpecs对象
     * @return
     */
    int updateIsEntry(ProductSpecs record);

    /**
     * 
     * @param classid
     * @return
     */
    List<ProductSpecsCustom> selectSpecsByClassid(Integer classid) throws Exception;

    
    List<ProductSpecs> getByClassId(Integer classid);

	List<ProductSpecs> getByFatherClassId(Integer classid);

	List<ProductSpecs> seletcProductSpecsPageByClassFatherId(PageBeanUtil pageBeanUtil) throws Exception;

	List<ProductSpecs> seletcProductByClassId(PageBeanUtil pageBeanUtil);

    
}