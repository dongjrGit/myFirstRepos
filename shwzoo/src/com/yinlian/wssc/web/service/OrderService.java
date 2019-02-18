package com.yinlian.wssc.web.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yinlian.Enums.MessagesTypeEnum;
import com.yinlian.Enums.PayTypeEnum;
import com.yinlian.Enums.TemplateTagEnum;
import com.yinlian.api.app.dto.AddNewOrderDto;
import com.yinlian.api.app.dto.OrderCommentCountDto;
import com.yinlian.api.app.dto.OrderCountDto;
import com.yinlian.api.app.dto.OrderMemberDto;
import com.yinlian.api.app.dto.OrderPayDto;
import com.yinlian.api.app.dto.OrdersDto;
import com.yinlian.wssc.search.Api_OrderCriteria;
import com.yinlian.wssc.search.P_OrderListCriteria;
import com.yinlian.wssc.search.Pc_OrderCriteria;
import com.yinlian.wssc.search.Platfrom_SYCriteria;
import com.yinlian.wssc.web.dto.OrderInfo;
import com.yinlian.wssc.web.dto.SaleOrder;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Dispatching;
import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.po.Invoice;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.V_Freights;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaDdtj;
import com.yinlian.wssc.web.util.CriteriaOrder;
import com.yinlian.wssc.web.util.OrderCriteria;

/**
 * 订单接口业务类
 * 
 * @author mjx
 *
 */
public interface OrderService {
	/**
	 * 创建订单
	 * 
	 * @param orders
	 * @param disp
	 * @param invoices
	 * @param receiveAddrID
	 * @param zyConponID
	 * @param zyActivityID
	 * @param userId
	 * @param desc
	 * @param sites  站点 1-pc 2-app 3-wap 4-wechat
	 * @return
	 */
	boolean add(List<AddNewOrderDto> orders, Dispatching disp, List<Invoice> invoices, int receiveAddrID,
			int zyConponID, int zyActivityID, int userId, String username, String Ip, Integer beans,String[] desc,
		    String scids,Integer sites,Idcardinfo idcardinfo) throws Exception;

	/**
	 * 根据顾客id查询订单
	 * 
	 * @param buyerid
	 * @return
	 * @throws Exception
	 */
	public List<Orders> queryAllByUserId(Integer buyerid) throws Exception;

	
	/**
	 * 根据顾客id和订单状态查询订单
	 * 
	 * @param buyerid
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<Orders> queryByStatus(Integer buyerid, Integer status) throws Exception;

	/**
	 * 计算运费
	 * 
	 * @param fre
	 *            运费信息
	 * @param ProductCount
	 *            商品数量
	 * @return
	 * @throws Exception
	 */
//	double freightMoney(V_Freights fre, int ProductCount) throws Exception;

	/**
	 * 删除订单
	 * 
	 * @param orderID
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	int delbyId(int orderID, int userid) throws Exception;

	/**
	 * 根据订单id获取订单
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Orders getOrderByID(int orderID) throws Exception;
	
	OrderInfo getOrderInfoById(int orderId) throws Exception;

	/**
	 * 根据订单组编号获取订单列表
	 * 
	 * @param groupCode
	 *            订单组编号
	 * @return
	 * @throws Exception
	 */
	List<Orders> getOrderByGroupCode(String groupCode) throws Exception;

	/**
	 * 根据订单id获取订单明细列表
	 * 
	 * @param orderID
	 *            订单id
	 * @return
	 * @throws Exception
	 */
	List<Orderdetail> getDetailsByOrderID(int orderID) throws Exception;

	/**
	 * 根据订单明细获取订单明细
	 * 
	 * @param detailID
	 *            订单明细ID
	 * @return
	 * @throws Exception
	 */
	Orderdetail getDetailByID(int detailID) throws Exception;

	/**
	 * 余额支付
	 * 
	 * @param groupCode
	 *            订单组编号
	 * @param totalMoney
	 *            支付总金额
	 * @param desc
	 * @return
	 * @throws Exception
	 */
	boolean updatePayForBalance(String groupCode, Double totalMoney, String ip, SessionUser user, String[] desc)
			throws Exception;

	/**
	 * 订单状态更新操作记录
	 * 
	 * @param Filed
	 *            更新字段
	 * @param OldValue
	 *            更新前字段值
	 * @param NewValue
	 *            更新后字段值
	 * @param orders
	 *            订单列表
	 * @param order
	 *            订单信息
	 * @param user
	 *            登陆用户
	 * @param userip
	 *            操作人ip
	 * @return
	 * @throws Exception
	 */
	int addOrderRecords(String Filed, String OldValue, String NewValue, List<Orders> orders, Orders order,
			SessionUser user, String userip) throws Exception;

	/**
	 * 订单支付成功处理
	 * 
	 * @param orderID
	 *            订单ID
	 * @param payType
	 *            支付方式
	 * @param money
	 *            支付金额
	 * @param couponID
	 *            优惠券ID
	 * @param desc
	 * @return
	 * @throws Exception
	 */
	boolean updatePayForOrderSuccess(int orderID, PayTypeEnum payType, float money, int couponID, String userip,
			String[] desc) throws Exception;

	/**
	 * 订单发货
	 * 
	 * @param orderID
	 *            订单ID
	 * @param logisticsName
	 *            物流公司名称
	 * @param logisticsCode
	 *            物流单号
	 * @return
	 * @throws Exception
	 */
	boolean updateSendProForOrder(int orderID, String logisticsName, String logisticsCode, String ip, SessionUser user,
			Orders order) throws Exception;

	/**
	 * 订单取消
	 * 
	 * @param orderID
	 *            订单ID
	 * @param reason
	 *            取消原因
	 * @param img
	 *            图片
	 * @param desc
	 *            返回错误说明
	 * @return
	 * @throws Exception
	 */
	boolean updateCancelOrder(int orderID, String reason, String img, String ip, SessionUser user, String[] desc)
			throws Exception;

	/**
	 * 确认收货
	 * 
	 * @param orderID
	 *            订单ID
	 * @param ip
	 * @param desc
	 * @return
	 * @throws Exception
	 */
	boolean updateConfirmReceivePro(int orderID, SessionUser user, String ip, String[] desc) throws Exception;

	void addOrderBackup(List<Orders> orders) throws Exception;

	/**
	 * 根据检索条件获取订单分页列表(平台)
	 * 
	 * @param index
	 * @param pageRow
	 * @param criteria
	 *            查询条件类
	 * @return
	 * @throws Exception
	 */
	PageBean getP_OrdersList(int index, int pageRow, P_OrderListCriteria criteria) throws Exception;

	/**
	 * 根据id查询订单
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Orders queryByID(Integer id) throws Exception;

	/**
	 * 更新订单
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	int update(Orders order) throws Exception;

	/**
	 * 根据检索条件获取订单分页列表(api)
	 * 
	 * @param index
	 * @param page
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	PageBean getUserListOrder(int page, int size, Api_OrderCriteria criteria) throws Exception;

	/**
	 * 平台或者卖家获取订单列表分页
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean getOrdersByPage(CriteriaOrder criteria, int page, int size) throws Exception;

	/**
	 * 卖家售后获取订单列表分页
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean getAfterOrdersByPage(Criteria criteria, int page, int size) throws Exception;

	/**
	 * 根据id查询订单
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Orders queryById(Integer id) throws Exception;

	/**
	 * @param value
	 * @return
	 */
	List<Orders> selectByStatus(Integer status) throws Exception;

	/**
	 * 直营订单统计
	 * 
	 * @param criteria
	 * @param toInt
	 * @param toInt2
	 * @return
	 */
	PageBean selectOrderTjByPage(CriteriaDdtj criteria, Integer pc, Integer ps) throws Exception;

	/**
	 * 根据id删除订单 买家删除订单
	 * 
	 * @param _orderid
	 * @return
	 * @throws Exception
	 */
	int delOrder(Integer _orderid, Integer userid) throws Exception;

	/**
	 * 根据组订单编号修改订单状态为已支付
	 * 
	 * @param orderGroup
	 * @return
	 */
	void updateByGroupCode(String orderGroup, String money) throws Exception;

	/**
	 * 店铺订单统计
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 */
	PageBean selectDpOrderTjByPage(CriteriaDdtj criteria, Integer pc, Integer ps)throws Exception;

	/**
	 * 统计待付款，待收货，待评价，退款的订单个数
	 * 
	 * @param userid
	 * @return
	 */
	OrderCountDto selectCount(Integer userid);

	OrderPayDto orderPay(String num) throws Exception;
	
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
	
	/**
	 * app获取订单详情
	 * @param orderid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	OrdersDto getorderdetail(int orderid,int userid)throws Exception;
	

	com.yinlian.wssc.web.dto.OrderDto getorderdetail(int orderid)throws Exception;
	
	PageBean getBillOrdersList(OrderCriteria criteria,Integer page, Integer size) throws Exception;
	
	/**
	 * 计算运费
	 * 
	 * @param fre
	 *            运费信息
	 * @param ProductCount
	 *            商品数量
	 * @param ProductPrice
	 *            商品金额
	 * @return
	 * @throws Exception
	 */
	double freightMoney(V_Freights fre, int ProductCount,BigDecimal ProductPrice,int postcount) throws Exception;
	
	
	public PageBean getMemberUserListOrder(Integer page1, Integer size11,Pc_OrderCriteria aoc) throws Exception;
	
	public PageBean pcselectByStatus (Integer page1, Integer size11,Pc_OrderCriteria aoc) throws Exception;
	
	public PageBean cancelorder(Integer page1, Integer size11,Pc_OrderCriteria aoc) throws Exception;
	
	public PageBean commentorder(Integer page1, Integer size11,Pc_OrderCriteria aoc) throws Exception;

	public List<OrderCommentCountDto> queryCount(Integer buyerid ) throws Exception;
	
	public List<OrderMemberDto> byorderidcomment(Integer buyerid ,Integer orderid) throws Exception;


	PageBean getPcUserListOrder(Integer page1, Integer size11,Pc_OrderCriteria aoc) throws Exception;


	public List<OrderMemberDto> getMemberBytimeOrder(Integer buyerid,String start,String end) throws Exception;

	Map<String, Object>  queryOrders(Platfrom_SYCriteria criteria) throws Exception;
	
	public PageBean getMemberListOrderPage(Integer page1, Integer size11,Pc_OrderCriteria aoc) throws Exception;
	
	/**
	 * 查询卖家的全部订单信息
	 * @return
	 */
	List<SaleOrder> selectAllOrder(Integer userid,Integer shopid) throws Exception;

	OrderCountDto selectDetailCount(int buyerId) throws Exception;
	/**
	 * 发送推送消息
	 * 
	 * @param userid 用户id
	 * @param ordercode 订单id
	 * @param ordertime 订单时间
	 * @param logisticsname 物流公司名称
	 * @param logisticscode 物流编码
	 * @param ttype 模板类型 (0邮件 1短信 2系统短信 3系统推送)
	 * @param ctype 内容类型(0订单 1促销)
	 * @param tag 模板标识 （详细见枚举TemplateTagEnum）
	 * @param mtype 消息类型
	 * @throws Exception
	 */
	void sendMessage(int userid, String ordercode, Date ordertime,
			String logisticsname,String logisticscode,int ttype,int ctype,TemplateTagEnum tag,MessagesTypeEnum mtype) throws Exception;

	PageBean getSHOrderList(Integer page1, Integer size11, Api_OrderCriteria aoc)throws Exception;
	
	/**
	 * 获取待付款使用时间小于今天的订单
	 * @return
	 * @throws Exception
	 */
	List<Orders> getInvalidOrder()throws Exception;

	/**
	 * 订单统计导出
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	List<SaleOrder> selectDpOrderTj(CriteriaDdtj criteria)throws Exception;
		
}
