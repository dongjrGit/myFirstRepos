package com.yinlian.wssc.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.Api_Detail_Specs;
import com.yinlian.api.app.dto.Api_SeachAtrrDto;
import com.yinlian.api.app.dto.ProductDetailedDto;
import com.yinlian.api.app.dto.ProductDto;
import com.yinlian.api.app.dto.SpecsBaseDto;
import com.yinlian.wssc.search.Api_ProductCriteria;
import com.yinlian.wssc.search.ProductCriteria;
import com.yinlian.wssc.web.dto.ProductListDto;
import com.yinlian.wssc.web.interceptor.PageBean;

public interface ProductService {
	/**
	 * 分页查询信息
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	public PageBean getList(ProductCriteria criteria, Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * pc前端获取商品检索列表数据
	 * @param cria
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public PageBean getPcProList(ProductCriteria cria, int index, int size) throws Exception;
	
	List<ProductListDto> getSpuStartWithName(Integer shopid, String name) throws Exception;

	/**
	 * 获取商品列表(api 手机端)
	 * 
	 * @param criteria
	 * @param page
	 * @param size1
	 * @return
	 * @throws Exception
	 */
	PageBean getApiProductList(Api_ProductCriteria criteria, Integer page, Integer size) throws Exception;

	/**
	 * 获取商品列表搜索属性(api 手机端)
	 * 
	 * @param clsid
	 * @return
	 * @throws Exception
	 */
	List<Api_SeachAtrrDto> getApiSeachAtrrList(Integer clsid,Integer usesites) throws Exception;

	/**
	 * 获取商品详细 spuid（api 手机端）
	 * 
	 * @param spuid Integer spuid, 
	 * @param skuid
	 * @return
	 */
	ProductDetailedDto getProductDetailed(Integer spuid,Integer itype,Integer usesite) throws Exception;
	
	/**
	 * 获取商品详细不筛选是否下架 spuid（api 手机端）
	 * 
	 * @param spuid Integer spuid, 
	 * @param skuid
	 * @return
	 */
	ProductDetailedDto getProductDetaileds(Integer spuid,Integer itype,Integer usesite)throws Exception;
	
	/**
	 * 根据skuid商品详细 （api 手机端）
	 * 
	 * @param spuid Integer spuid, 
	 * @param skuid
	 * @return
	 */
	ProductDetailedDto getProductDetailedBySkuId(Integer spuid,Integer skuid,Integer itype,Integer usesite) throws Exception;

	/**
	 * 获取商品详细页中详细 商品描述（手机端description）
	 * @param spuid
	 * @return
	 * @throws Exception
	 */
	String getApiProDetailDesc(Integer spuid) throws Exception;
		/**
	 * 获取商品详细页中详细 商品描述（pc端 spuinfo）
	 * @param spuid
	 * @return
	 * @throws Exception
	 */
	String getPcProductDetailedDesc(Integer spuid) throws Exception;

	String getApigetProductDetailedAfterSaleService(Integer spuid)throws Exception;

	/**
	 * 获取商品详细页中 选择规格(api 手机端)
	 * @param spuid
	 * @return
	 * @throws Exception
	 */
	List<SpecsBaseDto>  getProductDetailedSpecs(Integer spuid) throws Exception;

	/**
	 * 获取商品详细中详细商品规格(api 手机端)
	 * @param skuid
	 * @return
	 * @throws Exception
	 */
	List<Api_Detail_Specs> getProductDetailedSpecsInfo(Integer skuid)throws Exception;
	
	/**
	 * 获取商品页中详细商品规格(pc端)
	 * @param skuid
	 * @return
	 * @throws Exception
	 */
	List<Api_Detail_Specs> getProductSpecs(Integer skuid,String dispaly)throws Exception;
	/**
	 * 根据分类id获取最新商品
	 * @param classid
	 * @throws Exception
	 */
	List<ProductDto> getgetNewsShelvesProByCid(Integer classid,int sites) throws Exception ;

	/**
	 * 根据分类id获取热销商品
	 * @param classid
	 * @throws Exception
	 */
	List<ProductDto> getgetNewsSellingProByCid(Integer classid,int sites) throws Exception ;
	
	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 */
	public ProductDto selectInfoById(Integer id,int webset)throws Exception ;
	
	public PageBean getProListPage_pc(ProductCriteria cria, int index, int size)
			throws Exception;
	
	public PageBean getSerachProductListPage_pc(Api_ProductCriteria criteria, Integer page, Integer size) throws Exception;
	
	
	public PageBean getWapProductList(Api_ProductCriteria criteria, Integer page, Integer size) throws Exception;
}
