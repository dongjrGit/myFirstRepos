package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SkuExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface SkuMapper {
    int countByExample(SkuExample example);

    int deleteByExample(SkuExample example);

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(Sku record)throws Exception;

    int insertSelective(Sku record);

    List<Sku> selectByExampleWithBLOBs(SkuExample example);

    List<Sku> selectByExample(SkuExample example);

    Sku selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") Sku record, @Param("example") SkuExample example);

    int updateByExampleWithBLOBs(@Param("record") Sku record, @Param("example") SkuExample example);

    int updateByExample(@Param("record") Sku record, @Param("example") SkuExample example);

    int updateByPrimaryKeySelective(Sku record)throws Exception;

    int updateByPrimaryKeyWithBLOBs(Sku record)throws Exception;

    int updateByPrimaryKey(Sku record)throws Exception;

    int updatePrice(Sku record) throws Exception;

    int updateOldPrice(Sku record) throws Exception;

    int updateAppPrice(Sku record) throws Exception;

    List<Sku> getPageList(PageBeanUtil pageBeanUtil) throws Exception;

    List<Sku> getList(Integer spuid) throws Exception;

    List<Sku> getListByIds(List<Integer> ids) throws Exception;

    int addSku(Sku record) throws Exception;

    Double getMinPriceBySpu(Integer spuid) throws Exception;
    
    Double getMinWapPrice(Integer spuid) throws Exception;
    
    Double getMinWeChatPrice(Integer spuid) throws Exception;
    
    int updateStockById(Integer skuId, Integer stock);
    
    int updateSetStockById(@Param("skuId") Integer skuId,@Param("stock") Integer stock);
    
    List<Sku> getSkuStartWithName(Integer shopid, @Param("name")String name) throws Exception;
    
    int updateStockList(List<Sku> list) throws Exception;
    
    List<Sku> getActSkuStartWithName(Integer shopid, @Param("name")String name) throws Exception;

    Double getMinPrice(Integer spuId) throws Exception;

	int updatewapPrice(Sku sku) throws Exception;

	int updateCharPrice(Sku sku) throws Exception;

	List<Sku> getUserListOrderSpuPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<Sku> getWarnSKUPage(PageBeanUtil pageBeanUtil) throws Exception;
	
	Sku getByTicketnum(@Param("id")Integer id, @Param("tnum")String tnum) throws Exception;
	
	int deleteSkuBySpuID(Integer spuid)throws Exception;

	Sku getByShopTicketnum(@Param("shopid")Integer shopid, @Param("tnum")String tnum,@Param("id")Integer id)throws Exception;

	Sku getBySpuId(Integer spuid)throws Exception;

	int deleteSkuByShop(Integer shopid)throws Exception;
}