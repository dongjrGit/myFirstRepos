package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.OrderdetailDto;
import com.yinlian.wssc.search.BillsOrderCriteria;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.SyShopDto;
import com.yinlian.wssc.web.dto.SySpuDto;
import com.yinlian.wssc.web.dto.V_saleproducts;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.OrderdetailExample;
import com.yinlian.wssc.web.util.CriteriaDdtj;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface OrderdetailMapper {
    int countByExample(OrderdetailExample example);

    int deleteByExample(OrderdetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    List<Orderdetail> selectByExample(OrderdetailExample example);

    Orderdetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Orderdetail record, @Param("example") OrderdetailExample example);

    int updateByExample(@Param("record") Orderdetail record, @Param("example") OrderdetailExample example);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);

	List<Orderdetail> getDetailsByOrderID(int orderID) throws  Exception;
	
	int updateStatus(int status,int id) throws Exception;
	
	List<Orderdetail> queryByOrderid(Integer orderid) throws Exception;
	
	List<OrderdetailDto> queryByDtoOrderid(Integer orderid) throws Exception;

	List<SySpuDto> querySales(Platfrom_SYCriteria criteria) throws Exception;

	List<SyShopDto> queryShopSales(Platfrom_SYCriteria criteria) throws Exception;

	List<SySpuDto> querySellerSales(Platfrom_SYCriteria criteria) throws Exception;

	SyShopDto querySellerCount(Platfrom_SYCriteria criteria) throws Exception;

	SyShopDto querySellerCountDay(Platfrom_SYCriteria criteria) throws Exception;
	
	List<Orderdetail> getDetailsAnalysis(PageBeanUtil pBeanUtil)throws Exception;
	
	List<V_saleproducts> getSalePros(PageBeanUtil pBeanUtil)throws Exception;
	
	Orderdetail getDetailsByProcode(String procode) throws  Exception;

	List<Orderdetail> selectNoBills(@Param("criteria")BillsOrderCriteria criteria)throws Exception;
	
	List<Orderdetail> getDetailsByDate(@Param("criteria")CriteriaDdtj criteria)throws Exception;

	int cancelOrder(Integer id,Integer status)throws Exception;
	
}