package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.Api_ProductsDto;
import com.yinlian.pc.dto.PcSpuDto;
import com.yinlian.wap.dto.ShevelSpuDto;
import com.yinlian.wssc.search.Api_SpuCriteria;
import com.yinlian.wssc.web.dto.FindRelationDto;
import com.yinlian.wssc.web.dto.TopicRelateInfo;
import com.yinlian.wssc.web.dto.VSkuShopName;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.SpuExample;
import com.yinlian.wssc.web.po.SpuUpdateStatus;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.util.CriteriaShopSheive;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface SpuMapper {
    int countByExample(SpuExample example);

    int deleteByExample(SpuExample example);

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(SpuWithBLOBs record) throws Exception;

    int insertSelective(SpuWithBLOBs record);

    List<SpuWithBLOBs> selectByExampleWithBLOBs(SpuExample example);

    List<Spu> selectByExample(SpuExample example);

    SpuWithBLOBs selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") SpuWithBLOBs record,
                                 @Param("example") SpuExample example);

    int updateByExampleWithBLOBs(@Param("record") SpuWithBLOBs record,
                                 @Param("example") SpuExample example);

    int updateByExample(@Param("record") Spu record, @Param("example") SpuExample example);

    int updateByPrimaryKeySelective(SpuWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SpuWithBLOBs record) throws Exception;

    int updateByPrimaryKey(Spu record) throws Exception;

    int updateById(Integer id) throws Exception;

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<SpuWithBLOBs> selectDirectPage(PageBeanUtil pageBeanUtil) throws Exception;

    int insertSpu(SpuWithBLOBs record) throws Exception;

    int updateBatchStatus(SpuUpdateStatus sus) throws Exception;

    int updateShelves(SpuUpdateStatus sus) throws Exception;

    int updateShelve(int status, int id) throws Exception;

    int updatePrice(Spu record) throws Exception;

    List<Spu> getSpuStartWithName(Spu record) throws Exception;

    Integer getCountByShopId(Integer shopid) throws Exception;

    /**
     * 查询全部商品
     * @return
     * @throws Exception
     */
    List<Spu> selectAll(Integer status) throws Exception;
    /**
     * 根据商品id查询商品
     * @param pageBeanUtil
     * @return
     * @throws Exception
     */
    List<Api_ProductsDto> getSpuByBidPage(PageBeanUtil pageBeanUtil) throws Exception;
    /**
     * 分頁查詢商品主題
     * 
     * @param pageBeanUtil
     * @return
     */
    List<TopicRelateInfo> selectTopicByPage(PageBeanUtil pageBeanUtil) throws Exception;

    /**
     * 
     * @param pageBeanUtil
     * @return
     */
    List<Spu> selectDirectAppPage(PageBeanUtil pageBeanUtil) throws Exception;
    /**
     * 获取店铺商品列表（pc）
     * @param pageBeanUtil
     * @return
     */
    List<Spu> selectShopSpuPage(PageBeanUtil pageBeanUtil) throws Exception;
    List<Spu> getBySkuIDs(List<Integer> list) throws Exception;
    /**
     * 获取spu 集合
     * @param spuids
     * @return
     * @throws Exception
     */
    List<Spu> getBySpuIDs(List<Integer> spuids) throws Exception;
    int updateSalesCount(List<Spu> list) throws Exception;

	List<Spu> getSpuStartWithNames(String name);

	Integer updateAppPrice(Spu spu) throws Exception;

	Integer updateWapPrice(Spu spu1) throws Exception;

	Integer updateChatPrice(Spu spu2) throws Exception;

	List<Spu> queryByPageShop(PageBeanUtil pageBeanUtil);

	List<PcSpuDto> querySpuList(Integer shopid) throws Exception;

	List<ShevelSpuDto> getShevelSpuPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<ShevelSpuDto> queryShevelSpuList(CriteriaShopSheive aoc) throws Exception;

	List<Spu> getSpuStartWithNamesIsby(String name) throws Exception;

	List<Spu> getSpuStartWithNamesIszk(@Param("name")String name) throws Exception;
	
	List<VSkuShopName> getBySkuName(@Param("name")String name) throws Exception;

	VSkuShopName getBySkuId(Integer id) throws Exception;


	List<FindRelationDto> seleteFindRelationByPage(PageBeanUtil pageBeanUtil) throws Exception;

	
	List<Spu> platgetSspuStartWithName(@Param("name")String name,@Param("shopid")Integer shopid)throws Exception;
	
	Spu getByName(@Param("id")Integer id, @Param("name")String name) throws Exception;

	List<Spu> getProByShopIdByPage(PageBeanUtil beanUtil)throws Exception;

	List<Spu> getProByNameByPage(PageBeanUtil beanUtil)throws Exception;

	int deleteSpuByShop(Integer shopid)throws Exception;

	List<Spu> getProByShopId(@Param("criteria")Api_SpuCriteria criteria)throws Exception;
}