package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.SatisfactionGoodCountDto;
import com.yinlian.api.app.dto.ShopAppDto;
import com.yinlian.wssc.web.dto.TopicRelateInfo;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopExample;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ShopMapper {
	int countByExample(ShopExample example);

	int deleteByExample(ShopExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Shop record);

	int insertSelective(Shop record);

	List<Shop> selectByExample(ShopExample example);

	Shop selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Shop record, @Param("example") ShopExample example);

	int updateByExample(@Param("record") Shop record, @Param("example") ShopExample example);

	int updateByPrimaryKeySelective(Shop record);

	int updateByPrimaryKey(Shop record);

	/**
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<Shop> selectShopPage(PageBeanUtil pageBeanUtil) throws Exception;

	/**
	 * 
	 * @param shop
	 * @return
	 */
	int insertShop(Shop record) throws Exception;

	List<Shop> getShopStartWithName(String name) throws Exception;

	/**
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<Shop> selectShopViolationPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<SatisfactionGoodCountDto> getSfGoodCoutByShopId(Integer shopid) throws Exception;

	List<Shop> getByIds(List<Integer> list) throws Exception;

	/**
	 * 查询一定范围内的店铺
	 * 
	 * @param map
	 * @return
	 */
	List<Shop> selectBylatAndlogitPage(PageBeanUtil pageBeanUtil) throws Exception;

	/**
	 * 查询全部的店铺
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Shop> queryAll() throws Exception;

	/**
	 * 分頁查詢店鋪主題
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<TopicRelateInfo> selectTopicByPage(PageBeanUtil pageBeanUtil) throws Exception;

	Shop selectByUserid(Integer userid) throws Exception;

	Shop getOwnedShop() throws Exception;

	Shop getShopBySpuID(Integer spuid) throws Exception;

	List<Shop> getShopListByName(@Param("name") String name) throws Exception;

	List<ShopAppDto> getResShopListByPage(PageBeanUtil beanUtil) throws Exception;

	List<Shop> getTicketCenter()throws Exception;

	List<ShopAppDto> getShopByNameByPage(PageBeanUtil beanUtil)throws Exception;

	List<Shop> getShopListByIds(@Param("ids")String ids) throws Exception;

	List<Shop> getZooScenicByIds(List<Integer> shopids) throws Exception;

	Shop getTopicshop(int value) throws Exception;

}