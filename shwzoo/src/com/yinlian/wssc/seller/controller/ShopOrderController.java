package com.yinlian.wssc.seller.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.OrderApplyTypeEnum;
import com.yinlian.Enums.OrderDetailStatusEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.BusinessBillsCriteria;
import com.yinlian.wssc.search.OrderRemindCriteria;
import com.yinlian.wssc.web.dto.OrderDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.interceptor.SameUrlData;
import com.yinlian.wssc.web.mapper.OrdersMapper;
import com.yinlian.wssc.web.po.Applyforcancelorder;
import com.yinlian.wssc.web.po.Businessbills;
import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.po.OrderBills;
import com.yinlian.wssc.web.po.OrderRemind;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.ApplyforcancelorderService;
import com.yinlian.wssc.web.service.BusinessbillsService;
import com.yinlian.wssc.web.service.IdCardinfoService;
import com.yinlian.wssc.web.service.InvoiceService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.OperateLogService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderRemindService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.service.OrderUpdaterecordsService;
import com.yinlian.wssc.web.util.CriteriaOrder;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 卖家订单管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller/shoporder")
public class ShopOrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OperateLogService operateLogService;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private InvoiceService  invoiceService;
	 @Autowired
		private OperaterecordsService operaterecordsService ;
	 
	@Autowired
	private OrderUpdaterecordsService  orderUpdaterecordsService;
	
	@Autowired
	private ApplyforcancelorderService  applyforcancelorderService;
	
	@Autowired
	private IdCardinfoService  idCardinfoService;
	
	
	@Autowired
	private OrderRemindService orderremindService;
	@Autowired
	private MessageService messageService;
	@Autowired
	protected OrdersMapper ordersMapper;
	@Autowired
	private BusinessbillsService businessbillsService;
	
	SessionUser user=null;
	
	/**
	 * 获取店铺订单列表
	 * 
	 * @param num
	 * @param status
	 * @param buyerid
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getOrderList")
	public ReusltItem getOrderList(String num, String status,String logisticscode,
			String buyerid, String start, String end,String sendstart, String sendend,
			String page, String size,String qrbegin,String qrend,String state) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(0); // 非直营
			cOrder.setShopid(user.getShopid());
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cOrder.setOrdercode(num.trim());
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(logisticscode)) {
				cOrder.setLogisticscode(logisticscode.trim());
			}	
			if (StringUtilsEX.ToInt(status) >= 0) {
				cOrder.setStatus(StringUtilsEX.ToInt(status));
			}
			if (StringUtilsEX.ToInt(buyerid) > 0) {
				cOrder.setBuyid(StringUtilsEX.ToInt(buyerid));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(qrbegin)) {
				cOrder.setQrbegintime(qrbegin);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(qrend)) {
				cOrder.setQrendtime(qrend);
			}
			if(StringUtilsEX.ToInt(state) >= 0){
				if(StringUtilsEX.ToInt(state).equals(OrderStatusEnum.审核中.getValue())){
					cOrder.setDetailstatus(OrderDetailStatusEnum.审核中.getValue());
				}
				if(StringUtilsEX.ToInt(state).equals(OrderStatusEnum.待使用.getValue())){
					cOrder.setDetailstatus(OrderDetailStatusEnum.待使用.getValue());
				}
				cOrder.setState(StringUtilsEX.ToInt(state));
				if(StringUtilsEX.ToInt(state).equals(OrderDetailStatusEnum.已使用.getValue())){
					cOrder.setDetailstatus(OrderDetailStatusEnum.已使用.getValue());
					cOrder.setState(null);
				}
			}
			cOrder.setAddbegin(StringUtilsEX.ToShortDate(start));
			cOrder.setAddend(StringUtilsEX.ToShortDate(end));
			cOrder.setSendbegin(StringUtilsEX.ToShortDate(sendstart));
			cOrder.setSendend(StringUtilsEX.ToShortDate(sendend));
			cOrder.setOrderByClause("a.id");
			cOrder.setSort("desc");
			PageBean pageBean = orderService
					.getOrdersByPage(cOrder, Page, Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取店铺订单列表错误：{0}", e,
					"/seller/shoporder/getOrderList");
		}
		return item;
	}
	
	/**
	 * 待发货订单列表
	 * @param num
	 * @param logisticscode
	 * @param buyerid
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getsendOrderList")
	public ReusltItem getsendOrderList(String num,String logisticscode,
			String buyerid, String start, String end,
			String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(0); // 非直营
			cOrder.setShopid(user.getShopid());
			//cOrder.setStatus(OrderStatusEnum.待发货.getValue());
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cOrder.setOrdercode(num.trim());
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(logisticscode)) {
				cOrder.setLogisticscode(logisticscode.trim());
			}		
			if (StringUtilsEX.ToInt(buyerid) > 0) {
				cOrder.setBuyid(StringUtilsEX.ToInt(buyerid));
			}
			cOrder.setAddbegin(StringUtilsEX.ToShortDate(start));
			cOrder.setAddend(StringUtilsEX.ToShortDate(end));
			cOrder.setOrderByClause("a.AddOrderDate");
			cOrder.setSort("desc");
			PageBean pageBean = orderService
					.getOrdersByPage(cOrder, Page, Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取店铺待发货订单列表错误：{0}", e,
					"/seller/shoporder/getsendOrderList");
		}
		return item;
	}
	
	/**
	 * 售后退款退货订单列表
	 * @param num
	 * @param buyerid
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getReturnList")
	public ReusltItem getReturnList(String num,String status,String buyerid, String start, String end,
			String sendstart, String sendend,String page, String size){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			
			CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(0); // 非直营
			cOrder.setShopid(user.getShopid());
			String statuslist="";
			statuslist+=OrderDetailStatusEnum.申请退款.getValue()+",";
			statuslist+=OrderDetailStatusEnum.退款失败.getValue()+",";
			statuslist+=OrderDetailStatusEnum.退款成功.getValue()+",";
			statuslist+=OrderDetailStatusEnum.审核中.getValue()+",";
//			statuslist+=OrderDetailStatusEnum.换货申请被拒绝.getValue()+",";
//			statuslist+=OrderDetailStatusEnum.换货成功.getValue()+",";
//			statuslist+=OrderDetailStatusEnum.申请维修.getValue()+",";
//			statuslist+=OrderDetailStatusEnum.维修申请被拒绝.getValue()+",";
//			statuslist+=OrderDetailStatusEnum.维修成功.getValue()+",";
//			statuslist+=OrderDetailStatusEnum.申请退货退款.getValue()+",";
//			statuslist+=OrderDetailStatusEnum.退货申请被拒绝.getValue()+",";
//			statuslist+=OrderDetailStatusEnum.退货退款成功.getValue();
			cOrder.setStatuss(statuslist.trim());
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cOrder.setOrdercode(num.trim());
			}		
			if (StringUtilsEX.ToInt(status) >= 0) {
				cOrder.setStatus(StringUtilsEX.ToInt(status));
			}
			if (StringUtilsEX.ToInt(buyerid) > 0) {
				cOrder.setBuyid(StringUtilsEX.ToInt(buyerid));
			}
			cOrder.setAddbegin(StringUtilsEX.ToShortDate(start));
			cOrder.setAddend(StringUtilsEX.ToShortDate(end));
			cOrder.setSendbegin(StringUtilsEX.ToShortDate(sendstart));
			cOrder.setSendend(StringUtilsEX.ToShortDate(sendend));
			PageBean pageBean = orderService.getAfterOrdersByPage(cOrder, Page, Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取店铺退款退货订单列表错误：{0}", e,
					"/seller/shoporder/getReturnList");
		}
		return item;
	}
	
	/**
	 * 售后换货订单列表
	 * @param num
	 * @param buyerid
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getExchangeList")
	public ReusltItem getExchangeList(String num,String status,String buyerid, String start, String end,
			String sendstart, String sendend,String page, String size){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(0); // 非直营
			cOrder.setShopid(user.getShopid());
			String statuslist="";
			//statuslist+=OrderDetailStatusEnum.申请换货.getValue()+",";
			//statuslist+=OrderDetailStatusEnum.换货申请被拒绝.getValue()+",";
			//statuslist+=OrderDetailStatusEnum.换货成功.getValue();
			cOrder.setStatuss(statuslist.trim());
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cOrder.setOrdercode(num.trim());
			}		
			if (StringUtilsEX.ToInt(buyerid) > 0) {
				cOrder.setBuyid(StringUtilsEX.ToInt(buyerid));
			}
			if (StringUtilsEX.ToInt(status) >= 0) {
				cOrder.setStatus(StringUtilsEX.ToInt(status));
			}
			cOrder.setAddbegin(StringUtilsEX.ToShortDate(start));
			cOrder.setAddend(StringUtilsEX.ToShortDate(end));
			cOrder.setSendbegin(StringUtilsEX.ToShortDate(sendstart));
			cOrder.setSendend(StringUtilsEX.ToShortDate(sendend));
			PageBean pageBean = orderService
					.getAfterOrdersByPage(cOrder, Page, Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取店铺换货订单列表错误：{0}", e,
					"/seller/shoporder/getExchangeList");
		}
		return item;
	}
	
	/**
	 * 订单发货
	 * 
	 * @param request
	 * @param orderID
	 *            订单ID
	 * @param logisticsCode
	 *            物流单号
	 * @param logisticsName
	 *            物流公司名称
	 * @return
	 */
	@RequestMapping("/sendprofororder")
	public BaseResult sendProForOrder(String orderid, String logisticscode,
			String logisticsname) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user == null)
				user = new SessionUser();
			int oid = StringUtilsEX.ToInt(orderid);
			if (oid <= 0) {
				item.setCode(-101);
				item.setDesc("订单ID参数错误");
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(logisticscode)) {
				item.setCode(-102);
				item.setDesc("物流单号不能为空");
				return item;

			}
			if (StringUtilsEX.IsNullOrWhiteSpace(logisticsname)) {
				item.setCode(-103);
				item.setDesc("物流公司名称不能为空");
				return item;
			}
			Orders order = new Orders();
			String ip = GetIpAddresss.getIpAddr();
			if (orderStatusService.updateSendProForOrder(oid,logisticsname.trim(),
							logisticscode.trim(), ip, user) > 0) {
				item.setDesc("订单发货操作成功");
				SessionUser user1 = SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user1.getUserId(), user1.getLoginName(), "订单发货页面", "/seller/shoporder/sendprofororder", "订单发货");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加订单发货操作记录出错! 异常信息:",
    								e, "/seller/shoporder/sendprofororder");
    					}
    					
    				}
    			});
				// 添加日志
				String desc = MessageFormat.format(
						"订单发货操作成功！订单ID:{0},订单编号:{1},物流单号:{2},物流名称:{3}",
						order.getId(), order.getCode(), logisticscode,
						logisticsname);
				LogHandle.info(LogType.Order, desc,
						"/seller/shoporder/sendprofororder");
				operateLogService.addLog(OperateRecordsTypeEnum.修改,
						OperateRecordsFromEnum.卖家中心, user.getId(),
						user.getLoginName(), ip, "",
						"/seller/shoporder/sendprofororder", desc);
			} else {
				item.setCode(-200);
				item.setDesc("订单发货操作失败");
				LogHandle.warn(LogType.Order, MessageFormat.format(
						"订单发货失败！OrderID:{0},"
								+ "物流公司:{1},物流单号:{2},uid:{3},datetime:{4}",
						orderid, logisticsname, logisticscode, user.getId(),
						new Date()), "/seller/shoporder/sendprofororder");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单发货操作错误：{0}", ex,
					"/seller/shoporder/sendprofororder");

		}
		return item;
	}
	
	/**
	 * 订单取消审核
	 * 
	 * @param orderid
	 * @param isagree
	 *            0-不同意 1-同意
	 * @param reason
	 *            不同意需要写原因
	 * @return
	 */
	@RequestMapping("/confirmCancelOrder")
	public ReusltItem confirmCancelOrder(String orderid, String status,String imgUrl,
			String isagree, String reason) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(orderid) <= 0) {
				item.setCode(-101);
				item.setDesc("订单ID参数错误");
				return item;
			}
			if (StringUtilsEX.ToInt(status) != OrderStatusEnum.审核中.getValue()) {
				item.setCode(-102);
				item.setDesc("订单状态参数错误，不能取消审核");
				return item;
			}
			int isgr = StringUtilsEX.ToInt(isagree);
			if (isgr != 0 && isgr != 1) {
				item.setCode(-103);
				item.setDesc("订单取消审核类型参数错误，只能为0或者1");
				return item;
			}
			Orders[] orders = new Orders[1];

			if (orderStatusService.updateCheckCancel(
					StringUtilsEX.ToInt(orderid), StringUtilsEX.ToInt(status),
					user.getUserId(), isgr, reason, orders,user.getLoginName(),imgUrl) > 0) {
				item.setCode(0);
				item.setDesc("订单取消审核成功");
				String desc = MessageFormat.format(
						"订单取消审核成功！订单ID:{0},订单编号:{1},审核类型:{2}", orderid,
						orders[0].getCode(), isagree);
				LogHandle.info(LogType.Order, desc,
						"/seller/shoporder/sendprofororder");
				String ip = GetIpAddresss.getIpAddr();
				operateLogService.addLog(OperateRecordsTypeEnum.修改,
						OperateRecordsFromEnum.系统后台, user.getUserId(),
						user.getLoginName(), ip, "",
						"/seller/shoporder/confirmCancelOrder", desc);
				//SessionUser user = SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "订单取消审核页面", "/seller/shoporder/confirmCancelOrder", "订单取消审核");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加订单取消审核操作记录出错! 异常信息:",
    								e, "/seller/shoporder/confirmCancelOrder");
    					}
    					
    				}
    			});
    			//异步操作 不影响正常流程
    	        ExecutorService cachedThreadPooll = Executors.newCachedThreadPool();
    	        cachedThreadPooll.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						int newstatus = 0;
    						/*if(isgr == 1){
    							if (StringUtilsEX.ToInt(status) == OrderStatusEnum.未付款取消申请中.getValue()) {
    								newstatus = OrderStatusEnum.未付款已取消.getValue();
    							}else{
    								newstatus = OrderStatusEnum.已付款已取消.getValue();
    							}
    						}else{
    							if (StringUtilsEX.ToInt(status) == OrderStatusEnum.未付款取消申请中.getValue()) {
    								newstatus = OrderStatusEnum.待付款.getValue();
    							}else{
    								newstatus = OrderStatusEnum.待发货.getValue();
    							}
    						}*/
    						orderUpdaterecordsService.addOrderUpadateRecords("Status",String.valueOf(status),String.valueOf(newstatus), StringUtilsEX.ToInt(orderid), user.getUserId(), user.getLoginName(),ip);
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"订单取消审核操作记录出错! 异常信息:",
    								e, "/seller/shoporder/confirmCancelOrder");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("订单取消审核失败");
				LogHandle.warn(LogType.Order, MessageFormat.format(
						"订单取消审核成功！订单ID:{0},订单编号:{1},审核类型:{2}", orderid,
						orders[0].getCode(), isagree),
						"/seller/shoporder/confirmCancelOrder");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单取消审核错误：{0}", e,
					"/seller/shoporder/confirmCancelOrder");
		}
		return item;
	}

	/**
	 * 订单未收货申请退款审核
	 * @param id
	 * @param isagree
	 * @param status
	 * @param reason
	 * @param img
	 * @return
	 */
	@RequestMapping("/confirmReturnOrder")
	public ReusltItem confirmReturnOrder(String id, String isagree,
			String status, String reason, String img) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("订单detailID参数错误");
				return item;
			}
			int isgr = StringUtilsEX.ToInt(isagree);
			if (isgr != 0 && isgr != 1) {
				item.setCode(-103);
				item.setDesc("订单取消审核类型参数错误，只能为0或者1");
				return item;
			}
			if (StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.申请退款.getValue()) 
			{
				item.setCode(-102);
				item.setDesc("订单状态参数错误，不能售后审核");
				return item;
			}
			Orders orders = new Orders();
			if (orderStatusService.updateChekAftermarketNoReceipt(StringUtilsEX.ToInt(id), 
					user.getUserId(), StringUtilsEX.ToInt(status), isgr, reason, img,orders)
					> 0) {
				item.setCode(0);
				item.setDesc("订单售后申请审核成功");
				String desc = MessageFormat.format(
						"订单售后申请审核成功！订单ID:{0},订单编号:{1},审核类型:{2}", orders.getId(),
						orders.getCode(), isagree);
				LogHandle.info(LogType.Order, desc,
						"/seller/shoporder/confirmReturnOrder");
				String ip = GetIpAddresss.getIpAddr();
				operateLogService.addLog(OperateRecordsTypeEnum.修改,
						OperateRecordsFromEnum.系统后台, user.getUserId(),
						user.getLoginName(), ip, "",
						"/seller/shoporder/confirmReturnOrder", desc);
				  //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "订单售后申请审核页面", "/seller/shoporder/confirmReturnOrder", "订单售后申请审核");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加订单售后申请审核操作记录出错! 异常信息:",
    								e, "/seller/shoporder/confirmReturnOrder");
    					}
    					
    				}
    			});
    			//异步操作 不影响正常流程
                ExecutorService cachedThreadPooll = Executors.newCachedThreadPool();
    			cachedThreadPooll.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						int newstatus = 0;
    						if(isgr == 1){
    							//newstatus = OrderStatusEnum.已退款.getValue();
    							
    						}else{
    							Integer applytype = 0;
    							Applyforcancelorder applycancel = applyforcancelorderService
    									.getbyOrderAndType(StringUtilsEX.ToInt(id), applytype);
    							if (applycancel != null) {
    								newstatus = applycancel.getOrderstatus();
    							}
    						}
    						orderUpdaterecordsService.addOrderUpadateRecords("Status",status,String.valueOf(newstatus), StringUtilsEX.ToInt(id), user.getUserId(), user.getLoginName(),ip);
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"订单售后申请审核操作记录出错! 异常信息:",
    								e, "/platform/order/confirmReturnOrder");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("订单售后申请审核失败");
				LogHandle.warn(LogType.Order, MessageFormat.format(
						"订单售后申请审核成功！订单ID:{0},订单编号:{1},审核类型:{2}", orders.getId(),
						orders.getCode(), isagree),
						"/seller/shoporder/confirmReturnOrder");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单退换货审核错误：{0}", e,
					"/seller/shoporder/confirmReturnOrder");
		}
		return item;
	}
	
	/**
	 * 获取订单发票列表
	 * @param code
	 * @param status
	 * @param type
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getInvoiceList")
	public ReusltItem getInvoiceList(String code,String status,String type,String start,String end,
			String sendstart,String sendend,String page,String size){
		ReusltItem item = new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(0); // 非直营
			cOrder.setShopid(user.getShopid());
			if (!StringUtilsEX.IsNullOrWhiteSpace(code)) {
				cOrder.setOrdercode(code.trim());
			}
			if (StringUtilsEX.ToInt(status) >= 0) {
				cOrder.setStatus(StringUtilsEX.ToInt(status));
			}
			if (StringUtilsEX.ToInt(type) >= 0) {
				cOrder.setType(StringUtilsEX.ToInt(type));
			}
			cOrder.setAddbegin(StringUtilsEX.ToShortDate(start));
			cOrder.setAddend(StringUtilsEX.ToShortDate(end));
			cOrder.setSendbegin(StringUtilsEX.ToShortDate(sendstart));
			cOrder.setSendend(StringUtilsEX.ToShortDate(sendend));
			
			PageBean pageBean = invoiceService.getByOrderPage(cOrder, Page, Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		}catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取订单发票列表错误：{0}", e,
					"/seller/shoporder/getInvoiceList");
		}
		return item;
	}
	
	/**
	 * 更新发票打印状态
	 * @param id
	 * @return
	 */
	@RequestMapping("/changeInvStatus")
	public ReusltItem changeInvStatus(String id){
		ReusltItem item = new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("发票ID参数错误");
				return item;
			}
			if(invoiceService.updateStatus(StringUtilsEX.ToInt(id))>0){
				item.setCode(0);
				item.setDesc("更新订单发票打印状态成功");
				LogHandle.info(LogType.Order, MessageFormat.format("更新订单发票打印状态成功,发票ID:{0},操作人ID:{1}", 
						id,user.getUserId()),"/seller/shoporder/changeInvStatus");
				  //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "更新发票打印状态页面", "/seller/shoporder/changeInvStatus", "更新发票打印状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加更新发票打印状态操作记录出错! 异常信息:",
    								e, "/seller/shoporder/changeInvStatus");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("更新订单发票打印状态失败");
				LogHandle.info(LogType.Order, MessageFormat.format("更新订单发票打印状态失败,发票ID:{0}操作人ID:{1}", 
						id,user.getUserId()),"/seller/shoporder/changeInvStatus");
			}
		}
		catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"更新订单发票打印状态错误：{0}", e,
					"/seller/shoporder/changeInvStatus");
		}
		return item;
	}
	
	/**
	 * 订单售后发货 (中绿项目订单售后流程调整，产品需要时请恢复注释)
	 * 
	 * @param request
	 * @param orderdetailid
	 *            子订单ID
	 * @param logisticsCode
	 *            物流单号
	 * @param logisticsName
	 *            物流公司名称
	 * @return
	 */
	/*@RequestMapping("/sendProForAfter")
	public String sendProForAfter(String orderdetailid, String status,
			String logisticscode, String logisticsname) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user == null)
				user = new SessionUser();
			int oid = StringUtilsEX.ToInt(orderdetailid);
			if (oid <= 0) {
				item.setCode(-101);
				item.setDesc("订单详情ID参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-104);
				item.setDesc("订单状态参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.换货商家待发货
					.getValue()
					&& StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.维修商家待发货
							.getValue()) {
				item.setCode(-104);
				item.setDesc("订单状态参数错误");
				return item.toJson();
			}
			int applytype = 0;
			if (StringUtilsEX.ToInt(status) == OrderDetailStatusEnum.换货商家待发货
					.getValue()) {
				applytype = OrderApplyTypeEnum.订单换货.getValue();
			} else if (StringUtilsEX.ToInt(status) == OrderDetailStatusEnum.维修商家待发货
					.getValue()) {
				applytype = OrderApplyTypeEnum.订单维修.getValue();
			}

			if (StringUtilsEX.IsNullOrWhiteSpace(logisticscode)) {
				item.setCode(-102);
				item.setDesc("物流单号不能为空");
				return item.toJson();

			}
			if (StringUtilsEX.IsNullOrWhiteSpace(logisticsname)) {
				item.setCode(-103);
				item.setDesc("物流公司名称不能为空");
				return item.toJson();
			}
			String ip = GetIpAddresss.getIpAddr();
			if (orderStatusService.updateSendProForOrderDetail(oid, applytype,
					AfterServiceSender.商家.getValue(), logisticsname.trim(),
					logisticscode.trim()) > 0) {
				item.setDesc("订单售后发货操作成功");
				// 添加日志
				String desc = MessageFormat.format(
						"订单售后发货操作成功！订单详情ID:{0},物流单号:{1},物流名称:{2}",
						orderdetailid, logisticscode, logisticsname);
				LogHandle.info(LogType.Order, desc,
						"/seller/shoporder/sendProForAfter");
				operateLogService.addLog(OperateRecordsTypeEnum.修改,
						OperateRecordsFromEnum.系统后台, user.getId(),
						user.getLoginName(), ip, "",
						"/seller/shoporder/sendProForAfter", desc);
				
				SessionUser user1 = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user1.getUserId(), user1.getLoginName(), "订单售后发货页面", "/seller/shoporder/sendProForAfter", "订单售后发货");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加订单售后发货操作记录出错! 异常信息:",
    								e, "/seller/shoporder/sendProForAfter");
    					}
    					
    				}
    			});
    			
    			//异步操作 不影响正常流程
                ExecutorService cachedThreadPooll = Executors.newCachedThreadPool();
    			cachedThreadPooll.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						Orderdetail detail = orderdetailService
    								.queryByID(oid);
    						int newstatus=0;
    						OrderDetailStatusEnum detailStatus = OrderDetailStatusEnum
    								.valueOf(detail.getStatus());
    						switch (detailStatus) {
    						case 换货买家待发货:
    							newstatus=OrderDetailStatusEnum.换货商家待收货.getValue();
    							break;
    						case 换货商家待发货:
    							newstatus=OrderDetailStatusEnum.换货买家待收货.getValue();
    							break;
    						case 维修买家待发货:
    							newstatus=OrderDetailStatusEnum.维修商家待收货.getValue();
    							break;
    						case 维修商家待发货:
    							newstatus=OrderDetailStatusEnum.维修买家待收货.getValue();
    							break;
    						case 退货买家待发货:
    							newstatus=OrderDetailStatusEnum.退货商家待收货.getValue();
    							break;
    						default:
    							newstatus=0;
    							
    						}
    						orderUpdaterecordsService.addOrderUpadateRecords("Status",detail.getStatus().toString(),String.valueOf(newstatus), oid, user1.getUserId(), user1.getLoginName(),ip);
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"订单售后发货操作记录出错! 异常信息:",
    								e, "/seller/shoporder/sendProForAfter");
    					}
    					
    				}
    			});
				
			} else {
				item.setCode(-200);
				item.setDesc("订单售后发货操作失败");
				LogHandle.warn(LogType.Order, MessageFormat.format(
						"订单售后发货失败！订单详情ID:{0},"
								+ "物流公司:{1},物流单号:{2},uid:{3},datetime:{4}",
						orderdetailid, logisticsname, logisticscode,
						user.getId(), new Date()),
						"/seller/shoporder/sendProForAfter");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,
					MessageFormat.format("订单售后发货操作错误：{0}", ex.toString()),
					"/seller/shoporder/sendProForAfter");

		}
		return item.toJson();
	}*/

	/**
	 * 订单售后申请审核
	 * 
	 * @param id
	 * @param isagree
	 * @param status
	 * @param reason
	 * @param img
	 * @return
	 */
	@RequestMapping("/checkAfterOrder")
	public ReusltItem checkAfterOrder(String id, String isagree, String status,
			String reason, String img) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("订单detailID参数错误");
				return item;
			}
			int isgr = StringUtilsEX.ToInt(isagree);
			if (isgr != 0 && isgr != 1) {
				item.setCode(-103);
				item.setDesc("订单取消审核类型参数错误，只能为0或者1");
				return item;
			}
			if (StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.申请退款.getValue()) {
				item.setCode(-102);
				item.setDesc("订单状态参数错误，不能售后审核");
				return item;
			}
			int applytype = 0;
			OrderDetailStatusEnum detailStatus = OrderDetailStatusEnum
					.valueOf(StringUtilsEX.ToInt(status));
			switch (detailStatus) {
			case 申请退款:
				applytype = OrderApplyTypeEnum.订单退款.getValue();
				break;
			/*case 申请换货:
				applytype = OrderApplyTypeEnum.订单换货.getValue();
				break;
			case 申请维修:
				applytype = OrderApplyTypeEnum.订单维修.getValue();
				break;
			case 申请退货退款:
				applytype = OrderApplyTypeEnum.订单退货退款.getValue();
				break;*/
			default:
				break;
			}
			int temp=applytype;
			Orders orders=new Orders();
			if(StringUtilsEX.ToInt(status) == OrderDetailStatusEnum.申请退款.getValue()){
				if(orderStatusService.updateChekAftermarketNoReceipt(StringUtilsEX.ToInt(id), user.getUserId(),
						StringUtilsEX.ToInt(status), isgr, reason, img, orders)>0){
					item.setCode(0);
					item.setDesc("订单售后申请审核成功");
					String desc = MessageFormat.format(
							"订单售后申请审核成功！订单ID:{0},订单编号:{1},审核类型:{2}",
							orders.getId(), orders.getCode(), isagree);
					LogHandle.info(LogType.Order, desc,
							"/seller/shoporder/checkAfterOrder");
					String ip = GetIpAddresss.getIpAddr();
					operateLogService.addLog(OperateRecordsTypeEnum.修改,
							OperateRecordsFromEnum.系统后台, user.getUserId(),
							user.getLoginName(), ip, "",
							"/seller/shoporder/checkAfterOrder", desc);
					SessionUser user1 = SessionState.GetCurrentUser();
					  //异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
	                            		user1.getUserId(), user1.getLoginName(), "订单售后申请审核页面", "/seller/shoporder/checkAfterOrder", "订单售后申请审核");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"添加订单售后申请审核操作记录出错! 异常信息:",
	    								e, "/seller/shoporder/checkAfterOrder");
	    					}
	    					
	    				}
	    			});
	    			//异步操作 不影响正常流程
	                ExecutorService cachedThreadPooll = Executors.newCachedThreadPool();
	    			cachedThreadPooll.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						
	    						Integer newstatus=0;
	    						Integer applytype = 0;
	    						if(isgr==1){
	    							newstatus = OrderStatusEnum.已完结.getValue();
	    						}else{
	    							Applyforcancelorder applycancel = applyforcancelorderService
	    									.getbyOrderAndType(StringUtilsEX.ToInt(id), applytype);
	    							if (applycancel != null) {
	    								newstatus = applycancel.getOrderstatus();
	    							}
	    						}
	    						
	    						orderUpdaterecordsService.addOrderUpadateRecords("Status",status,String.valueOf(newstatus), StringUtilsEX.ToInt(id), user.getUserId(), user.getLoginName(),ip);
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"订单退款申请审核操作记录出错! 异常信息:",
	    								e, "/seller/shoporder/checkAfterOrder");
	    					}
	    					
	    				}
	    			});
				} else {
					item.setCode(-200);
					item.setDesc("订单售后申请审核失败");
					LogHandle.warn(LogType.Order, MessageFormat.format(
							"订单售后申请审核成功！订单ID:{0},订单编号:{1},审核类型:{2}",
							orders.getId(), orders.getCode(), isagree),
							"/seller/shoporder/checkAfterOrder");
				}
			}else{
				String ip = GetIpAddresss.getIpAddr();
				if (orderStatusService.updateCheckAfterApply(
						StringUtilsEX.ToInt(id), applytype, isgr,
						reason, img, ip) > 0) {
					item.setCode(0);
					item.setDesc("订单售后申请审核成功");
					String desc = MessageFormat.format(
							"订单售后申请审核成功！订单详情ID:{0},审核类型:{1}",
							id,  isagree);
					LogHandle.info(LogType.Order, desc,
							"/seller/shoporder/checkAfterOrder");
					operateLogService.addLog(OperateRecordsTypeEnum.修改,
							OperateRecordsFromEnum.系统后台, user.getUserId(),
							user.getLoginName(), ip, "",
							"/seller/shoporder/checkAfterOrder", desc);
					SessionUser user1 = SessionState.GetCurrentUser();
					  //异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
	                            		user1.getUserId(), user1.getLoginName(), "订单售后申请审核页面", "/seller/shoporder/checkAfterOrder", "订单售后申请审核");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"添加订单售后申请审核操作记录出错! 异常信息:",
	    								e, "/seller/shoporder/checkAfterOrder");
	    					}
	    					
	    				}
	    			});
	    			
	    			//异步操作 不影响正常流程
	                ExecutorService cachedThreadPooll = Executors.newCachedThreadPool();
	    			cachedThreadPooll.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						
	    						Integer newstatus=0;
	    						
	    						//OrderApplyTypeEnum type = OrderApplyTypeEnum.valueOf(applytype);
	    						switch (OrderApplyTypeEnum.valueOf(temp)) {
	    						
	    						/*case 订单退货退款:
	    							newstatus = isgr == 0 ? OrderDetailStatusEnum.退货申请被拒绝.getValue()
	    									: OrderDetailStatusEnum.退货退款成功.getValue();
	    							break;
	    						case 订单换货:
	    							newstatus = isgr == 0 ? OrderDetailStatusEnum.换货申请被拒绝.getValue()
	    									: OrderDetailStatusEnum.换货成功.getValue();
	    							break;
	    						case 订单维修:
	    							newstatus = isgr == 0 ? OrderDetailStatusEnum.维修申请被拒绝.getValue()
	    									: OrderDetailStatusEnum.维修成功.getValue();
	    							break;*/
	    						default:
	    							newstatus=0;
	    						}
	    						
	    						orderUpdaterecordsService.addOrderUpadateRecords("Status",status,String.valueOf(newstatus), StringUtilsEX.ToInt(id), user.getUserId(), user.getLoginName(),ip);
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"订单退款申请审核操作记录出错! 异常信息:",
	    								e, "/platform/order/checkAfterOrder");
	    					}
	    					
	    				}
	    			});
				} else {
					item.setCode(-200);
					item.setDesc("订单售后申请审核失败");
					LogHandle.warn(LogType.Order, MessageFormat.format(
							"订单售后申请审核成功！订单详情ID:{0},审核类型:{1}",
							id, isagree),
							"/seller/shoporder/checkAfterOrder");
				}
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单售后申请审核错误：{0}", e,
					"/seller/shoporder/checkAfterOrder");
		}
		return item;
	}
	
	/**
	 * 订单售后商家确认(中绿售后流程调整，产品如需要请恢复注释)
	 * @param id
	 * @param status
	 * @return
	 */
	/*@RequestMapping("/afterReceivePro")
	public ReusltItem afterReceivePro(String id,String status){
		ReusltItem item = new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("订单detailID参数错误");
				return item;
			}
			if(StringUtilsEX.ToInt(status)<=0){
				item.setCode(-102);
				item.setDesc("订单详情状态参数错误");
				return item;
			}
			if(StringUtilsEX.ToInt(status)!=OrderDetailStatusEnum.申请换货.getValue()
					&& StringUtilsEX.ToInt(status)!=OrderDetailStatusEnum.申请退货退款.getValue()
					&& StringUtilsEX.ToInt(status)!=OrderDetailStatusEnum.申请维修.getValue()){
				item.setCode(-102);
				item.setDesc("订单详情状态参数错误");
				return item;
			}
			int applytype=0;
			if(StringUtilsEX.ToInt(status)==OrderDetailStatusEnum.申请换货.getValue()){
				applytype=OrderApplyTypeEnum.订单换货.getValue();
			}else if(StringUtilsEX.ToInt(status)==OrderDetailStatusEnum.维修商家待收货.getValue()){
				applytype=OrderApplyTypeEnum.订单维修.getValue();
			}
			else if(StringUtilsEX.ToInt(status)==OrderDetailStatusEnum.退货商家待收货.getValue()){
				applytype=OrderApplyTypeEnum.订单退货退款.getValue();
			}
			String ip=GetIpAddresss.getIpAddr();
			int isselct=orderStatusService.updateAfterServiceReceive(StringUtilsEX.ToInt(id), 
					applytype, AfterServiceSender.商家.getValue(), user, ip);
			if(isselct>0){
				item.setCode(0);
				item.setDesc("订单售后收货成功");
				String desc = MessageFormat.format(
						"订单售后收货成功！订单详情ID:{0}",id);
				LogHandle.info(LogType.Order, desc,
						"/seller/shoporder/afterReceivePro");
				operateLogService.addLog(OperateRecordsTypeEnum.修改,
						OperateRecordsFromEnum.系统后台, user.getUserId(),
						user.getLoginName(), ip, "",
						"/seller/shoporder/afterReceivePro", desc);
				SessionUser user1 = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
              ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
  			cachedThreadPool.execute(new Runnable() {
  				@Override
  				public void run() {
  					try{
  						operaterecordsService.insertOperaterecords(
                          		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                          		user1.getUserId(), user1.getLoginName(), "订单售后收货页面", "/seller/shoporder/afterReceivePro", "订单售后收货");
  					}
  					catch(Exception e){
  						LogHandle.error(LogType.OperateRecords,"添加订单售后收货操作记录出错! 异常信息:",
  								e, "/seller/shoporder/afterReceivePro");
  					}
  					
  				}
  			});

			ExecutorService cachedThreadPooll = Executors.newCachedThreadPool();
  			cachedThreadPooll.execute(new Runnable() {
  				@Override
  				public void run() {
  					try{
  						Orderdetail detail = new Orderdetail();
  						detail = orderdetailService.queryByID(StringUtilsEX.ToInt(id));
  						int newstatus=0;
  						if(detail.getStatus() == OrderDetailStatusEnum.退货商家待收货.getValue()){
  							
  							newstatus=OrderDetailStatusEnum.退货退款成功.getValue();
  							
  						}else if(detail.getStatus() == OrderDetailStatusEnum.换货商家待收货
  								.getValue()){
  							newstatus=OrderDetailStatusEnum.换货商家待发货.getValue();
  							
  						}else if(detail.getStatus() == OrderDetailStatusEnum.维修商家待收货
  								.getValue()){
  							newstatus=OrderDetailStatusEnum.维修商家待发货.getValue();
  						}
  						
  						orderUpdaterecordsService.addOrderUpadateRecords("Status",status,String.valueOf(newstatus), StringUtilsEX.ToInt(id), user.getUserId(), user.getLoginName(),ip);
  					}
  					catch(Exception e){
  						LogHandle.error(LogType.OperateRecords,"订单售后收货操作记录出错! 异常信息:",
  								e, "/seller/shoporder/afterReceivePro");
  					}
  					
  				}
  			});
			}
			else{
				item.setCode(-200);
				item.setDesc("订单售后收货失败");
				String desc = MessageFormat.format(
						"订单售后收货失败！订单详情ID:{0}",id);
				LogHandle.info(LogType.Order, desc,
						"/seller/shoporder/afterReceivePro");
			}
		}
		catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,
					MessageFormat.format("订单售后收货操作错误：{0}", e.toString()),
					"/seller/shoporder/afterReceivePro");
		}
		return item;
	}*/
	
	/**
	 * 催单列表
	 * @param isread 是否受理
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/cdorderList")
	public ReusltItem getlist(boolean isread, Integer page, Integer size){
		ReusltItem item=new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			OrderRemindCriteria criteria = new OrderRemindCriteria();
			criteria.setIsread(isread);
			criteria.setSupplierid(user.getShopid());
			PageBean pageBean=orderremindService.selectOrderRemindPage(criteria, page, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取订单列表错误：{0}", e,"/seller/shoporder/cdorderList");
		}
		return item;
	}
	
	/**
	 * 催单受理
	 * @param orderid
	 * @param sljg
	 * @return
	 */
	@RequestMapping("/slcdResult")
	public ReusltItem slcdResult(String orderid,String sljg,HttpServletRequest request){
		ReusltItem item=new ReusltItem();
		try {
			if(StringUtilsEX.ToInt(orderid)<0){
				item.setCode(-101);
				item.setDesc("订单id不能为空,id" + orderid);
				return item;
			}
			OrderRemind or =orderremindService.selectByPrimaryKey(StringUtilsEX.ToInt(orderid));
			or.setIsread(true);
			or.setRemark(sljg);
			orderremindService.updateByPrimaryKeySelective(or);
			item.setCode(0);
			item.setDesc("处理成功");
			String ids[]=new String[1];
			ids[0]=""+or.getBuyerid();
			messageService.insertBacth(ids, "订单已受理", "尊敬的  "+or.getBuyername()+" "+or.getRemark()+" 订单号："+or.getOrdernum());
		} catch (Exception e) {
			item.setCode(-200);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("订单处理失败"+e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			String desc = MessageFormat.format(
					"订单受理失败！催单ID(订单备份):{0},操作人ID:{1}",orderid,user.getUserId());
			LogHandle.warn(LogType.Order, desc,
					"/seller/shoporder/slcdResult");
		}
		return item;
	}
	
	/***
	 * 订单导出excel
	 * @param response
	 * @param req
	 * @param num
	 * @param status
	 * @param logisticscode
	 * @param buyerid
	 * @param start
	 * @param end
	 * @param sendstart
	 * @param sendend
	 */
	@RequestMapping("/outPutOrderExcel")
	public void outPutOrderExcel(HttpServletResponse response,HttpServletRequest req, String num, String status,String logisticscode,
			String buyerid, String start, String end,String sendstart, String sendend){
		try{  
			String[] head = new String[]
			{"商家名称","顾客姓名","身份信息","联系电话","订单编号","商品名称","商品编号","商品单价","下单时间","付款时间","使用时间","购买数量","订单状态","商品总价"};	
			/*{"用户名","订货人","订货人电话","订货人手机","收货人","收货人电话","收货人手机","省份","城市","详细地址-路/大道","号/弄"," 具体门牌号","送货日期",
			"是否要积分","是否索要发票","发票抬头","发票内容","发票类型","打印时是否显示价格","是否短信通知","前台备注","后台备注","商品","运费","邮政编码",
			"业务员","客服备注","促销","地区","折扣","优惠金额","商品名称"};	*/																																																																																																																																																																																																																										
			/*String[] headers = new String[]//修改过的表头
			{"用户名","订货人","订货人电话","订货人手机","收货人","收货人电话","收货人手机","省份","城市","地区","详细地址-路/大道	号/弄	具体门牌号","积分","是否索要发票","发票抬头"
			,"发票内容","发票类型","订单金额","运费","优惠金额","商品","是否短信通知","前台备注","邮政编码","业务员","客服备注","促销","送货日期"};*/
			int rowIndex = 0;
	        Workbook workbook = new HSSFWorkbook();                                    // 创建一个工作簿
	        Sheet sheet = workbook.createSheet();                                        // 创建一个Sheet页    
	        Row row = sheet.createRow(rowIndex++);                                        // 创建第一行(头部)
	        CreationHelper helper = workbook.getCreationHelper();
	        CellStyle style = workbook.createCellStyle();                                // 设置单元格样式
	        style.setDataFormat(helper.createDataFormat().getFormat(""));        // 格式化日期类型
	        for (int i = 0; i < head.length; i++) {                                     // 输出头部
	            row.createCell(i).setCellValue(head[i]);
	        }     
                         
	        //数据开始
	        CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(0); // 非直营
			cOrder.setShopid(user.getShopid());
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cOrder.setOrdercode(num.trim());
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(logisticscode)) {
				cOrder.setLogisticscode(logisticscode.trim());
			}	
			if (StringUtilsEX.ToInt(status) >= 0) {
				cOrder.setStatus(StringUtilsEX.ToInt(status));
			}
			if (StringUtilsEX.ToInt(buyerid) > 0) {
				cOrder.setBuyid(StringUtilsEX.ToInt(buyerid));
			}
			cOrder.setAddbegin(StringUtilsEX.ToShortDate(start));
			cOrder.setAddend(StringUtilsEX.ToShortDate(end));
			cOrder.setSendbegin(StringUtilsEX.ToShortDate(sendstart));
			cOrder.setSendend(StringUtilsEX.ToShortDate(sendend));
			cOrder.setOrderByClause("a.AddOrderDate");
			cOrder.setSort("desc");
			PageBeanUtil pBeanUtil = new PageBeanUtil(cOrder, 0, 1000);
			List<Idcardinfo> idcardinfo = new ArrayList<Idcardinfo>();
			List<OrderDto> list = ordersMapper.getOrdersByPage(pBeanUtil);
			for (OrderDto orderDto : list) {
				row = sheet.createRow(rowIndex++);
				/*Receiveinfo receiveinfo = new Receiveinfo();
				Invoice invoice = new Invoice();
				receiveinfo = receiveInfoService.queryByOrderGroupId(orderDto.getGroupCode());
				if(receiveinfo!=null){
					receiveinfo.setProvincename(provinceServcice.queryByCode(receiveinfo.getProvince()).getName());
					receiveinfo.setCityname(cityServcie.queryByCode(receiveinfo.getCity()).getName());
					receiveinfo.setAreaname(areaService.queryByCode(receiveinfo.getArea()).getName());
				}
				
				if (orderDto.getIsInvoice()!=null&&orderDto.getIsInvoice() == 1) {
					invoice = invoiceService.selectByOrderId(orderDto.getId());
					row.createCell(14).setCellValue("是");//是否索要发票
					row.createCell(15).setCellValue(StringUtilsEX.ToInt(invoice.getTitle())==1?"个人":"公司名称");//发票抬头
	                row.createCell(16).setCellValue(invoice.getContent());//发票内容
	                row.createCell(17).setCellValue(invoice.getType()==1?"普通发票":"增值税发票");//发票类型
				}else{
					row.createCell(14).setCellValue("否");//是否索要发票
					row.createCell(15).setCellValue("");//发票抬头
	                row.createCell(16).setCellValue("");//发票内容
	                row.createCell(17).setCellValue("");//发票类型
				}*/
				String name="";
				String idcard="";
				String phone="";
				String spstr="";
				String spname="";
				String proprice="";
				String procount="";
				String usetime="";

				for (int i = 0; i < orderDto.getChildren().size(); i++) {
					idcardinfo=idCardinfoService.quertByUserId(orderDto.getChildren().get(i).getBuyerid());
					spstr+=orderDto.getChildren().get(i).getProductnum();
					spname+=orderDto.getChildren().get(i).getProductname();
					proprice+=orderDto.getChildren().get(i).getProductprice();
					procount+=orderDto.getChildren().get(i).getProductcount();
					usetime+=orderDto.getChildren().get(i).getUsetime();
					name+=idcardinfo.get(i).getName();
					idcard+=idcardinfo.get(i).getCard();
					phone+=idcardinfo.get(i).getPhone();
				}
                /*  row.createCell(1).setCellValue("李桂兰 ");//订货人
                row.createCell(2).setCellValue("010-59458673");//订货人电话
                row.createCell(3).setCellValue("18210761732");//订货人手机
                row.createCell(4).setCellValue(orderDto.getBuyerName());//收货人
                row.createCell(5).setCellValue(receiveinfo.getTelphone());//收货人电话
                row.createCell(6).setCellValue(receiveinfo.getTelphone());//收货人手机
                row.createCell(7).setCellValue(receiveinfo.getCityname());//省份
                row.createCell(8).setCellValue(receiveinfo.getProvincename());//城市
                row.createCell(9).setCellValue(receiveinfo.getAddress());//详细地址-路/大道 
                row.createCell(10).setCellValue("");//号/弄
                row.createCell(11).setCellValue("");//具体门牌号
                row.createCell(12).setCellValue("");//送货日期
                row.createCell(13).setCellValue("否");// 是否要积分
                row.createCell(18).setCellValue("否");//打印时是否显示价格
                row.createCell(19).setCellValue("否");//是否短信通知
                row.createCell(20).setCellValue("");//前台备注
                row.createCell(21).setCellValue("");//后台备注
                row.createCell(22).setCellValue(spstr);//商品
                row.createCell(23).setCellValue("");// 运费
                row.createCell(24).setCellValue("");//邮政编码
                row.createCell(25).setCellValue("");//业务员
                row.createCell(26).setCellValue("");//客服备注
                row.createCell(27).setCellValue("");//促销
                row.createCell(28).setCellValue(receiveinfo.getAreaname());//地区
                row.createCell(29).setCellValue("");//折扣
                row.createCell(30).setCellValue(orderDto.getDiscount());//优惠金额
                row.createCell(31).setCellValue(spname);//商品名称*/
                if (orderDto.getShopName()==null) {
                    row.createCell(0).setCellValue("");//"付款时间"
				}else {
	                row.createCell(0).setCellValue(orderDto.getShopName());//"商家名称"
				}
                if (name!=null) {
	                row.createCell(1).setCellValue(name);//"顾客姓名"
				}
                if (idcard!=null) {
                    row.createCell(2).setCellValue(idcard);//"身份信息"
				}
                if (phone!=null) {
                    row.createCell(3).setCellValue(phone);//"联系电话"
				}
                if (orderDto.getCode()==null) {
                    row.createCell(4).setCellValue("");//"付款时间"
				}else {
	                row.createCell(4).setCellValue(orderDto.getCode());//"订单编号"
				}
                if (null!=spname) {
                    row.createCell(5).setCellValue(spname);//"商品名称"
				}
                if (null!=spstr) {
                    row.createCell(6).setCellValue(spstr);//"商品编号"
				}
                if (null!=proprice) {
                    row.createCell(7).setCellValue(proprice);//"商品单价"
				}
                if (orderDto.getAddOrderDate()==null) {
                    row.createCell(8).setCellValue("");//"付款时间"
				}else {
	                row.createCell(8).setCellValue(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(orderDto.getAddOrderDate()));//"下单时间"
				}
                if (orderDto.getPayDate()==null) {
                    row.createCell(9).setCellValue("");//"付款时间"
				}else {
	                row.createCell(9).setCellValue(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(orderDto.getPayDate()));//"付款时间"
				}
                if (usetime!=null) {
                    row.createCell(10).setCellValue(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(usetime)));//"使用时间"
				}
                if (procount!=null) {
                    row.createCell(11).setCellValue(procount);//"购买数量"
				}
                if(orderDto.getStatus() == 0) {
                	row.createCell(12).setCellValue("待付款");//"订单状态"
                }if(orderDto.getStatus() == 1) {
                	row.createCell(12).setCellValue("出票中");//"订单状态"
                }if(orderDto.getStatus() == 2) {
                	row.createCell(12).setCellValue("待使用");//"订单状态"
                }if(orderDto.getStatus() == 3) {
                	row.createCell(12).setCellValue("审核中");//"订单状态"
                }if(orderDto.getStatus() == 9) {
                	row.createCell(12).setCellValue("已完结");//"订单状态"
                }if(orderDto.getStatus() == 4) {
                	row.createCell(12).setCellValue("已取消");//"订单状态"
                }

                if (orderDto.getActualPay()==null) {
                    row.createCell(13).setCellValue("");//"付款时间"
				}else {
	                row.createCell(13).setCellValue(orderDto.getActualPay());//"商品总价"
				}
			}
	        for (int i = 0; i < head.length; i++) {        
		        sheet.autoSizeColumn((short) i);    // 单元格宽度自适应     
	        }     
	        String ddate = new SimpleDateFormat("yyyyMMddhhmmss").format(Calendar.getInstance().getTime());
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        response.setHeader("Content-Disposition", "attachment; filename=" + new String("订单导出".getBytes("gb2312"), "iso8859-1") + "_" + ddate + ".xls");// 设定输出文件头
	        OutputStream output = response.getOutputStream();
	        workbook.write(output);
	        output.flush();
	        output.close();
		}catch(Exception e){
			e.printStackTrace();
			String desc ="订单导出异常";
			LogHandle.warn(LogType.Order, desc,
					"/shoporder/outPutOrderExcel");
		}

	}
	

	/**
	 * 对账列表
	 * @param index
	 * @param size
	 * @param criteria
	 * @return
	 */
	@RequestMapping("/bulllist")
	public ReusltItem bulllist(String index,String size,String status,String begin,String end,String shopId){
		ReusltItem item = new ReusltItem();
		try {
			int Page = StringUtilsEX.ToInt(index);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			BusinessBillsCriteria criteria=new BusinessBillsCriteria();
			if (StringUtilsEX.ToInt(status) >= 0) {
				criteria.setStatus(StringUtilsEX.ToInt(status));
			}
			if (StringUtilsEX.ToInt(shopId) >= 0) {
				criteria.setShopid(StringUtilsEX.ToInt(shopId));
			}
			criteria.setBillstart(StringUtilsEX.ToShortDate(begin));
			criteria.setBillsend(StringUtilsEX.ToShortDate(end));
			criteria.setOrderByClause("orderdate");
			criteria.setSort("desc");
			PageBean pageBean=businessbillsService.getOrderBills(criteria, Page, Size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Businessbills,"获取对账列表错误：", e,
					"/shoporder/bulllist");
		}
		return item;
	}
	
	/**
	 * 修改对账单状态
	 * @param billId
	 * @return
	 */
	@SameUrlData
	@RequestMapping("/setBbillsStatus")
	public ReusltItem setBbillsStatus(String billId) {
		ReusltItem item = new ReusltItem();
		try {
			OrderBills bills=businessbillsService.selectById(StringUtilsEX.ToInt(billId));
			if(bills!=null){
				bills.setStatus(1);
			}
			int i=businessbillsService.updateBills(bills);
			if(i>0){
				item.setDesc("处理成功");
				item.setCode(0);
			}else{
				item.setDesc("处理失败");
				item.setCode(-200);
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Businessbills,"修改对账单状态错误：", e,
					"/shoporder/setBbillsStatus");
		}
		return item;
	}
	
	/**
	 * 对账订单列表
	 * @param shopid
	 * @param orderdate
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getBbillsorderList")
	public ReusltItem getBbillsorderList(String shopid, String orderdate,
			String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			BusinessBillsCriteria criteria=new BusinessBillsCriteria();
			criteria.setShopid(StringUtilsEX.ToInt(shopid));
			criteria.setOrderdate(orderdate);
			criteria.setIsfree(0);
			criteria.setStatus(OrderDetailStatusEnum.已使用.getValue());
			
			criteria.setOrderByClause("o.AddOrderDate");
			criteria.setSort("desc");
			PageBean pageBean =businessbillsService.getBillsOrderList(criteria, Page, Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Businessbills,"获取对账单订单列表错误：", e,
					"/shoporder/getBbillsorderList");
		}
		return item;
	}
	
	@RequestMapping("/getBillByID")
	public ReusltItem getBillByID(Integer id){
		ReusltItem item = new ReusltItem();
		try {
			item.setData(businessbillsService.findById(id));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取对账信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Businessbills,"获取对账信息出错! 异常信息:", e,"/platform/businessbillsAjax/getBillByID");
		}
		return item;
	}
	
	@RequestMapping("/editBusinessBill")
	public ReusltItem editBusinessBill(Integer bID,String remark,String opertor,String bankname,String bankusername,String banknum,String remitType,String transferDate,String transferMoney){
		ReusltItem item = new ReusltItem();
		try {
			Businessbills businessbills=new Businessbills();
			businessbills.setId(bID);
			businessbills.setRemark(remark);
			businessbills.setRemittype(remitType);
			businessbills.setTransferdate(StringUtilsEX.ToShortDate(transferDate));
			businessbills.setTransfermoney(new BigDecimal(transferMoney));
			businessbills.setStatus(1);
			businessbills.setBankusername(bankusername);
			businessbills.setBanknum(banknum);
			businessbills.setBankname(bankname);
			businessbills.setTransfername(opertor);
			if(businessbillsService.updateById(businessbills)>0){
				item.setCode(0);
				item.setDesc("处理成功");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("处理对账信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Businessbills,"处理对账信息出错! 异常信息:", e,"/platform/businessbillsAjax/editBusinessBill");
		}
		return item;
	}
	
	/**
	 * 组订单退款
	 * @param groupnum
	 * @return
	 */
	@RequestMapping("/updateOrderTK")
	public ReusltItem updateOrderTK(String groupnum){
		ReusltItem item = new ReusltItem();
		try {
			if(StringUtilsEX.IsNullOrWhiteSpace(groupnum)){
				item.setCode(-100);
				item.setDesc("组订单编号错误");
				return item;
			}
			if(orderStatusService.updateOrderTK(groupnum, null, item) > 0){
				item.setCode(0);
				item.setDesc("退款成功");
				return item;
			}else{
				return item;
			}
		} catch (Exception e) {
			item.setCode(-200);
			item.setDesc("组订单退款失败");
			LogHandle.warn(LogType.Order, MessageFormat.format(
					"组订单退款失败！组订单编号:{0}", groupnum),
					"/seller/shoporder/updateOrderTK");
		}
		return item;
	}
	
	/**
	 * 子订单退款
	 * @param orderdetailid
	 * @return
	 */
	@RequestMapping("/updatePartTK")
	public ReusltItem updatePartTK(String orderdetailid){
		ReusltItem item = new ReusltItem();
		try {
			if(StringUtilsEX.ToInt(orderdetailid) < 0){
				item.setCode(-100);
				item.setDesc("子订单id参数错误");
				return item;
			}
			if(orderStatusService.updatePartTK(StringUtilsEX.ToInt(orderdetailid), null, item) > 0){
				item.setCode(0);
				item.setDesc("退款成功");
				return item;
			}else{
				return item;
			}
		} catch (Exception e) {
			item.setCode(-200);
			item.setDesc("子订单退款失败");
			LogHandle.warn(LogType.Order, MessageFormat.format(
					"子订单退款失败！子订单ID:{0}", orderdetailid),
					"/seller/shoporder/updatePartTK");
		}
		return item;
	}
	
}
