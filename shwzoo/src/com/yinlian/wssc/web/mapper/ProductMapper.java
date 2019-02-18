package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.ProductDetailedDto;
import com.yinlian.api.app.dto.ProductDto;
import com.yinlian.pc.dto.PCProListDto;
import com.yinlian.wssc.web.dto.ProductListDto;
import com.yinlian.wssc.web.dto.V_SearchProducts;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ProductMapper  {
	 List<ProductListDto> getListPage(PageBeanUtil pageBeanUtil) throws Exception;
	 
	 List<V_SearchProducts> getPcProListPage(PageBeanUtil util) throws Exception;
	 
	 List<ProductListDto> getSpuStartWithName(Integer shopid,String name ) throws Exception;

	List<ProductDto> getApiProductListPage(PageBeanUtil pageBeanUtil) throws Exception;
	
	ProductDetailedDto getProductDetailed(Integer spuid,@Param("sites") int sites)throws Exception;
	
	ProductDetailedDto getProductDetaileds(Integer spuid,@Param("sites") int sites)throws Exception;

	String getPCProductDetailedDesc(Integer skuid) throws Exception;
    String getApiProDetailDesc(Integer skuid) throws Exception;
	String getApigetProductDetailedAfterSaleService(Integer spuid);

	List<ProductDto> getgetNewsShelvesProByCid(Integer classid,@Param("sites") int sites) throws Exception ;
	
	List<ProductDto> getgetNewsSellingProByCid(Integer classid,@Param("sites") int sites) throws Exception ;

	ProductDetailedDto getProductDetailedBySkuId(Integer skuid) throws Exception;

	ProductDto queryInfoById(Integer id,@Param("webset") int webset)throws Exception;
	
	List<PCProListDto> getProListPage(PageBeanUtil util) throws Exception;
	
	List<PCProListDto> getSerachProductListPage(PageBeanUtil util) throws Exception;
	
	List<ProductDto> getWapProductListPage(PageBeanUtil pageBeanUtil) throws Exception;
}
