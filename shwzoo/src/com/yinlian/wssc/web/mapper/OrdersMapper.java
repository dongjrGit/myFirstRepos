package com.yinlian.wssc.web.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.Api_ListOrderDto;
import com.yinlian.api.app.dto.Api_OrderDetaiBaselDto;
import com.yinlian.api.app.dto.ApplyAfterDto;
import com.yinlian.api.app.dto.OrderCountDto;
import com.yinlian.api.app.dto.OrderMemberDto;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.BusinessbillsDto;
import com.yinlian.wssc.web.dto.OrderDto;
import com.yinlian.wssc.web.dto.SaleOrder;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.OrdersExample;
import com.yinlian.wssc.web.util.CriteriaDdtj;
import com.yinlian.wssc.web.util.OrderCriteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface OrdersMapper {
    int countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Orders record) throws Exception;

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer id);

    //getpcUserListOrderPage(PageBeanUtil pageBeanUtil) throws Exception;
    List<OrderMemberDto> getMemberUserListOrderPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    List<Orders> selectByBuyerId(Integer buyerid);

    List<Orders> selectByStatus(Integer buyerid, Integer status);

    int updateByExampleSelective(@Param("record") Orders record,
                                 @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    Orders getOrderByID(int orderID) throws Exception;

    List<Orders> getOrderByGroupCode(@Param("num") String groupCode) throws Exception;

    List<OrderDto> getP_OrdersList(PageBeanUtil pageBeanUtil) throws Exception;

    List<OrderDto> getOrdersByPage(PageBeanUtil pageBeanUtil) throws Exception;

    List<Api_ListOrderDto> getUserListOrderNoCountPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    List<Api_ListOrderDto> getUserOrderListPage(PageBeanUtil pageBeanUtil) throws Exception;

    int updateStatus(int status, int id) throws Exception;

    /**
     * 批量查询订单
     * @param ids
     * @return
     */
    List<Orders> selectByIds(String[] ids) throws Exception;
    
    BigDecimal getPriceByGroupCode(String groupcode) throws Exception;
    
    List<OrderDto> getAfterOrdersByPage(PageBeanUtil pageBeanUtil) throws Exception;
    
    /**
     * 分页查询直营店铺订单日统计
     * @param pageBeanUtil
     * @return
     */
	List<SaleOrder> selectOrderTjDayByPage(PageBeanUtil pageBeanUtil);
	
	/**
	 * 分页查询直营店铺订单最近一周统计
	 * @param pageBeanUtil
	 * @return
	 */
	List<SaleOrder> selectOrderTjWeekByPage(PageBeanUtil pageBeanUtil);
	
	/**
	 * 分页查询直营店铺订单月统计
	 * @param pageBeanUtil
	 * @return
	 */
	List<SaleOrder> selectOrderTjMonthByPage(PageBeanUtil pageBeanUtil);
	
	/**
	 * 分页查询直营店铺订单按季度统计
	 * @param pageBeanUtil
	 * @return
	 */
	List<SaleOrder> selectOrderTjQuaterByPage(PageBeanUtil pageBeanUtil);

	/**
	 * 分页查询直营店铺订单按年统计
	 * @param pageBeanUtil
	 * @return
	 */
	List<SaleOrder> selectOrderTjYearByPage(PageBeanUtil pageBeanUtil);
	
	/**
     * 分页查询店铺订单日统计
     * @param pageBeanUtil
     * @return
     */
	List<SaleOrder> selectDpOrderTjDayByPage(PageBeanUtil pageBeanUtil);
	
	/**
	 * 分页查询店铺订单详细统计
	 * @param pageBeanUtil
	 * @return
	 */
	List<SaleOrder> selectDpOrderTjDetailByPage(PageBeanUtil pageBeanUtil);
	
	/**
	 * 分页查询店铺订单月统计
	 * @param pageBeanUtil
	 * @return
	 */
	List<SaleOrder> selectDpOrderTjMonthByPage(PageBeanUtil pageBeanUtil);
	
	/**
	 * 分页查询店铺订单按季度统计
	 * @param pageBeanUtil
	 * @return
	 */
	List<SaleOrder> selectDpOrderTjQuaterByPage(PageBeanUtil pageBeanUtil);

	/**
	 * 分页查询店铺订单按年统计
	 * @param pageBeanUtil
	 * @return
	 */
	List<SaleOrder> selectDpOrderTjYearByPage(PageBeanUtil pageBeanUtil);

	/**
	 * 用户根据id删除订单
	 * 
	 * @param _orderid
	 * @return
	 */
	int userDelOrderById(Orders orders);

	
	/**
	 * 查询卖家的全部订单信息
	 * @return
	 */
	List<SaleOrder> selectAllOrder(@Param("userid") Integer userid);

	/**
	 * 统计待付款，待收货，待评价，退款的订单个数
	 * @param userid
	 * @return
	 */
	List<OrderCountDto> queryCount(Integer userid);

	Orders getByCode(String code)throws Exception;
	
	/**
	 * 查询未付款订单
	 * @return
	 * @throws Exception
	 */
	List<Orders> getUnpaidOrders()throws Exception;

	/**
	 * 查询已确认收货订单
	 * @return
	 * @throws Exception
	 */
	List<Orders> getDeliverOrders()throws Exception;
	
	List<OrderDto> getpcUserListOrderPage(PageBeanUtil pageBeanUtil) throws Exception;

	Map<String, Object> selectOrder(Platfrom_SYCriteria criteria) throws Exception;

	OrderCountDto selectGoodCount(int buyerId) throws Exception;

	List<OrderDto> selectOrdersByPage(PageBeanUtil pageBeanUtil);

	com.yinlian.wssc.web.dto.OrderDto getorderdetail(Integer orderid);

	/**
	 * 获取对账订单
	 * @param orderCriteria
	 * @return
	 */
	List<BusinessbillsDto> selectBusinessbills(OrderCriteria orderCriteria);

	List<Api_ListOrderDto> getSHOrderListNoCountPage(PageBeanUtil pageBeanUtil)throws Exception;

	ApplyAfterDto getApplyDetail(Integer orderdetailid)throws Exception;

	List<Orders> getInvalidOrder()throws Exception;

	int updateStatusByGroupcode(Integer status, String gnum)throws Exception;
	
	/** 获取出票失败的订单 用于退款处理
	 * @Company: 安徽因联
	* @author 毛俊贤
	* @date 2017年10月26日 上午10:01:10
	* @return
	* @throws Exception
	 */
	List<Orders> getUntickOrders()throws Exception;
	
	List<OrderDto> getOrdersByDetailPage(PageBeanUtil pageBeanUtil)throws Exception;
	
	//待付款订单
	List<Api_ListOrderDto> getUserListOrderNopayNoCountPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<Orderdetail> selectDetailsByIds(@Param("ids")String ids)throws Exception;
	
	List<Api_OrderDetaiBaselDto> getUserListOrderDetailsByids(@Param("ids")String ids)throws Exception;

	List<SaleOrder> selectDpOrderTjDay(@Param("criteria")CriteriaDdtj criteria)throws Exception;

	List<SaleOrder> selectDpOrderTjDetail(@Param("criteria")CriteriaDdtj criteria)throws Exception;

	List<SaleOrder> selectDpOrderTjMonth(@Param("criteria")CriteriaDdtj criteria)throws Exception;

	List<SaleOrder> selectDpOrderTjQuater(@Param("criteria")CriteriaDdtj criteria)throws Exception;

	List<SaleOrder> selectDpOrderTjYear(@Param("criteria")CriteriaDdtj criteria)throws Exception;
	
	List<Orders> getUntickOrdersReq()throws Exception;
	
	
}