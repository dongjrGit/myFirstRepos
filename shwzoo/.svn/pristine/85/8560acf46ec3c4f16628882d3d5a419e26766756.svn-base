package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.pc.dto.ReceiveaddrDto;
import com.yinlian.wssc.web.po.Receiveaddress;


public interface ReceiveAddressService {
	/**
	 * 插入一条收货人地址
	 * @param receiveaddress
	 * @return
	 * @throws Exception
	 */
	public int insert(String userId,String name, String mobile, String provinceCode,String provinceName,
			String CityCode,String cityName,String AreaCode,String areaName, String address, Integer isDefault)throws Exception;
	/**
	 * 通过userId查询该用户的所有的收货人地址
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Receiveaddress> queryAllByUserId(String userId)throws Exception;
	
	/**
	 * 通过id删除对应的收货人地址
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delByID(String id)throws Exception;
	
	/**
	 * 根据Id获取收货地址
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Receiveaddress selectByPrimaryKey(int id)throws Exception;
	
	/**
	 * 通过userId查询该用户的默认收货人地址
	 * @param userId 用户id
	 * @return 用户设置了默认收货地址则返回该默认地址，如没有设置则返回第一条收货地址信息
	 * @throws Exception
	 */
	public Receiveaddress getDefaultAddress(int userId)throws Exception;
	
	public void updateReceiver(Receiveaddress receiveaddress) throws Exception;
	
	public int inserAddress(Receiveaddress receiveaddress) throws Exception;
	
	public List<ReceiveaddrDto> getByUser(int userid)throws Exception;

}
