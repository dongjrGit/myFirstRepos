package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.ShoppingNewCartDto.CartDto;
import com.yinlian.api.app.dto.ShoppingNewCartDto.CookieDto;
import com.yinlian.api.app.dto.V_ShopCart;
import com.yinlian.wssc.web.po.Shopcartpros;


public interface CartService {

	/**
	 * 添加购物车
	 * @param vc
	 * @return
	 * @throws Exception
	 */
	int addCart(V_ShopCart vc)throws Exception;
	
	/**
	 * 清空购物车
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	int deleteByUser(int userid)throws Exception;
	
	/**
	 * 清空购物车中过期的商品
	 */
	int deleteByUserTime(Shopcartpros shopcartpros)throws Exception;
	
	/**
	 * 删除购物车某个商品
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteByID(int id)throws Exception;
	
//	/**
//	 * 获取购物车总金额
//	 * @param userid
//	 * @return
//	 * @throws Exception
//	 */
//	Float getTotalMoney(int userid)throws Exception;
	
	/**
	 * 获取购物车商品数据
	 * @return
	 * @throws Exception
	 */
	CartDto getByUser(int userid,int sites)throws Exception;
	/**
	 * 获取购物车结算商品数据
	 * @return
	 * @throws Exception
	 */
	CartDto getBuyCart(int userid,int sites)throws Exception;
	/**
	 * 根据cookie获取购物车商品数据
	 * @return
	 * @throws Exception
	 */
	CartDto getByCookieCart(List<CookieDto>  cartlist,int sites)throws Exception;
	/**
	 * 获取购物车选择商品数量
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	int getCountByUser(int userid)throws Exception;
	
	int deleteByIds(List<Integer> list)throws Exception;
	
	int updateCount(int count,int id)throws Exception;
	
	int updateSelect(int id,int sel)throws Exception;
	
	public int addCarts(List<V_ShopCart> vclist) throws Exception; 
	
	public int updateSelectList(List<Integer> idlist,List<Integer> issel)throws Exception;
	
	public CartDto getByCookie(List<V_ShopCart> cartlist,int sites,int userid) throws Exception;

	public int updateSelectShop(List<Integer> shopidList, Integer userid, Integer sel);

	public String getProStatusByID(Integer id)throws Exception; 

}
