/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.Enums.SiteType;
import com.yinlian.api.app.dto.ApiShopAboutInfoBaseDto;
import com.yinlian.api.app.dto.ShopAppDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.ShopClass;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.CriteriaTopic;

/**
 * 店铺的业务类
 * 
 * @author Administrator
 * @version $Id: ShopService.java, v 0.1 2016年3月9日 下午9:19:28 Administrator Exp $
 */
public interface ShopService {

	/**
	 * 根据名称模糊查询
	 * 
	 * @param name
	 * @return
	 */
	List<Shop> seletcByLikeName(String name) throws Exception;

	/**
	 * 多条件查询分页信息
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	PageBean selectShopPage(CriteriaShop criteria, Integer pc, Integer ps) throws Exception;

	/**
	 * 插入一条shop数据
	 * 
	 * @param shop
	 * @param shopClass
	 * @param users
	 * @param authentication
	 * @param accounts
	 * @return
	 */
	int insertShop(Shop shop, ShopClass shopClass, Users users, ShopAuthentication authentication, Accounts accounts)
			throws Exception;

	/**
	 * 
	 * @param shopid
	 * @return
	 */
	Shop queryById(Integer shopid) throws Exception;

	/**
	 * 更新shop表 同时更新 users accounts shopclass shopAuthentication
	 * 
	 * @param users
	 * @param shop
	 * @param authentication
	 * @param accounts
	 * @param shopClass
	 * @param classidList
	 * @param fullpathList
	 * @return
	 */
	int update(Users users, Shop shop, ShopAuthentication authentication, Accounts accounts) throws Exception;

	/**
	 * 
	 * @param toInt
	 * @param newpwd
	 * @param repwd
	 * @return
	 */
	int updatePwd(Integer toInt, String newpwd, String repwd) throws Exception;

	/**
	 * 修改店铺的配置参数
	 * 
	 * @param shopid
	 * @param maxEmployee
	 * @param maxRole
	 * @param margin
	 * @return
	 */
	int updateParam(Integer shopid, Integer maxEmployee, Integer maxRole, float margin) throws Exception;

	/**
	 * 充值
	 * 
	 * @param shopid
	 * @param money
	 * @param type
	 * @return
	 */
	int recharge(Integer shopid, double money, Integer type) throws Exception;

	/**
	 * 删除审核状态
	 * 
	 * @param toInt
	 * @param toInt2
	 * @return
	 */
	int deleteCheck(Integer shopid, Integer status) throws Exception;

	/**
	 * 更新状态
	 * 
	 * @param shopid
	 * @param status
	 * @param lastStatus
	 * @param string
	 * @param userid
	 * @return
	 */
	int updateStatus(Integer shopid, Integer status, Integer lastStatus, Integer userid, String username)
			throws Exception;

	List<Shop> getShopStartWithName(String name) throws Exception;

	/**
	 * 查询违规店铺的分页数据
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	PageBean selectShopViolationPage(CriteriaShop criteria, Integer pc, Integer ps) throws Exception;

	/**
	 * 更新直营店铺
	 * 
	 * @param shop
	 * @return
	 */
	int updateOwned(Shop shop) throws Exception;

	public int updateOwned(Shop shop, ShopAuthentication authentication, Accounts accounts, Users users)
			throws Exception;

	/**
	 * 添加直营店铺
	 * 
	 * @param shop
	 * @return
	 */
	int insertOwned(Shop shop) throws Exception;

	int insertOwned(Shop shop, ShopAuthentication authentication, Accounts accounts, Users users) throws Exception;

	/**
	 * 设置旗舰店的状态
	 * 
	 * @param brandid
	 * @param shopid
	 * @return
	 */
	int updateFlag(Integer brandid, Integer shopid) throws Exception;

	/**
	 * 修改卖家的店铺状态
	 * 
	 * @param id
	 * @param status
	 * @param reason
	 * @param username
	 * @param userid
	 * @return
	 */
	int updateSellerStatus(Integer id, Integer status, String reason, Integer userid, String username) throws Exception;

	/**
	 * 更新店铺的状态
	 * 
	 * @param id
	 * @param status
	 * @param username
	 * @param userid
	 * @return
	 */
	int updateShopStatus(Integer id, Integer status, Integer userid, String username) throws Exception;

	/**
	 * 根据userid查询所有的店铺
	 * 
	 * @param userid
	 * @return
	 */
	List<Shop> selectListByUserId(Integer userid) throws Exception;

	/**
	 * 查询店铺信息为app提供 包含了 优惠卷, 广告位
	 * 
	 * @param id
	 * @return
	 */
	ShopAppDto queryByIdForApp(Integer id, SiteType siteType,ReusltItem item) throws Exception;

	/**
	 * 获取店铺简介 信息
	 * 
	 * @param shopid
	 * @throws Exception
	 */
	ApiShopAboutInfoBaseDto getApiShopAboutInfo(Integer shopid) throws Exception;

	/**
	 * 添加店铺 为app端的服务
	 * 
	 * @param paypassword
	 * @param password
	 * @param principalname
	 * @param shop
	 * @param authentication
	 * @param shopClass
	 * @param authentication
	 * @param classidList
	 * @param fullpathList
	 * @param userid
	 * @return
	 */
	int insertShopApp(String mobile, String shopname, String shopnum, String username, String provincecode,
			String citycode, String areacode, String address, String password, String paypassword, String principalname)
			throws Exception;

	/**
	 * 根据经纬度查询一定范围的店铺
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	PageBean queryBylatAndlogitCriteria(Criteria criteria, Integer pc, Integer ps) throws Exception;

	/**
	 * 查询全部未删除的店铺
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Shop> queryAll() throws Exception;

	/**
	 * 分页查询店鋪的数据
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	public PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria, Integer pc, Integer ps) throws Exception;

	/**
	 * 通过id查询店铺
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	Shop queryByUserId(Integer userid) throws Exception;

	/* boolean SyncUp(Integer shopId, String[] desc); */

	Shop getOwnedShop() throws Exception;

	/**
	 * 修改支付密码
	 * 
	 * @param shopid
	 * @param newpwd
	 * @param repwd
	 * @return
	 */
	int updatePayPwd(Integer shopid, String newpwd, String repwd) throws Exception;

	/**
	 * 导入excel表格数据
	 * 
	 * @param arrayList
	 * @param cicleList
	 * @return
	 */
	int insertShopInfoByExcle(List<String[]> arrayList, List<String> cicleList) throws Exception;

	/**
	 * 根据spuid获取店铺信息
	 * 
	 * @param spuid
	 * @return
	 * @throws Exception
	 */
	Shop getShopBySpuID(Integer spuid) throws Exception;

	int updateByPrimaryKey(Shop record) throws Exception;

	/**
	 * 根据店铺名模糊查询营业状态的店铺列表
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	List<Shop> getShopListByName(String name) throws Exception;

	/**
	 * 查询餐饮店铺列表
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 */
	PageBean getRestaurantShopList(CriteriaShop criteria, Integer page, Integer size) throws Exception;

	/**
	 * 获取票务中心列表
	 * @return
	 * @throws Exception
	 */
	List<Shop> getTicketCenter()throws Exception;

	/**
	 * 根据店铺名称模糊查询店铺列表
	 * @param criteria
	 * @param pageindex
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	PageBean getShopByName(CriteriaShop criteria, Integer pageindex, Integer pagesize)throws Exception;

	/** 店铺删除
	 * 
	 * @param shopid
	 * @return
	 * @throws Exception
	 */
	int deleteShop(Integer shopid)throws Exception;

	Shop gettopicshop(int value)throws Exception;

}
