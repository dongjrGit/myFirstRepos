package com.yinlian.wssc.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.OrderdetailDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.AfterServiceOrderDto;
import com.yinlian.wssc.web.dto.SyShopDto;
import com.yinlian.wssc.web.dto.SySpuDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaDdtj;
import com.yinlian.wssc.web.util.CriteriaSaleProduct;

public interface OrderdetailService {
	
	/**
	 * 根据id查询订单明细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Orderdetail  queryByID(Integer id) throws Exception;
	
	/**
	 * 更新订单明细
	 * @param orderdetail
	 * @return
	 * @throws Exception
	 */
	int update (Orderdetail orderdetail) throws Exception;
	
	/**
	 * 查询某个订单下的订单明细
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	List<OrderdetailDto> selectByOrderid(Integer orderid) throws Exception;

	public List<SySpuDto> selectSaleasCount(Platfrom_SYCriteria criteria) throws Exception;

	public List<SyShopDto> selectShopSaleasCount(Platfrom_SYCriteria criteria) throws Exception;

	public List<SySpuDto> selectSellerSaleasCount(Platfrom_SYCriteria criteria) throws Exception;

	public SyShopDto selectSellerCount(Platfrom_SYCriteria criteria) throws Exception;

	public SyShopDto selectSellerCountDay(Platfrom_SYCriteria criteria) throws Exception;
	
	/**
	 * 查询售后订单
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	List<AfterServiceOrderDto> getDetailsAnalysis(CriteriaDdtj criteria,Integer page,Integer size,ReusltItem item)throws Exception;
	
	/**
	 * 查询卖家售后订单
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	AfterServiceOrderDto getDetailsAnalysisByShop(CriteriaDdtj criteria)throws Exception;
	
	
	PageBean getSaleProsPage(CriteriaSaleProduct criteria,Integer page,Integer size)throws Exception;

	/**
	 * 根据主订单号查询商品单号
	 * @param groupcode
	 * @return
	 * @throws Exception
	 */
	public String getProCode(String groupcode)throws Exception;

	/**
	 * 删除单个商品订单
	 * @param _orderid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public int delOrderDetail(Integer _orderid, Integer userid)throws Exception;
		
}
