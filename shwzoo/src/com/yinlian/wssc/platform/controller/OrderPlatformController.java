package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.OrderApplyTypeEnum;
import com.yinlian.Enums.OrderDetailStatusEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.OrderRemindCriteria;
import com.yinlian.wssc.search.P_OrderListCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Applyforcancelorder;
import com.yinlian.wssc.web.po.OrderRemind;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.ApplyforcancelorderService;
import com.yinlian.wssc.web.service.InvoiceService;
import com.yinlian.wssc.web.service.OperateLogService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderRemindService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.service.OrderUpdaterecordsService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.util.CriteriaOrder;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/platform/order")
public class OrderPlatformController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OperateLogService operateLogService;

	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private InvoiceService invoiceService;

	SessionUser user = null;
	@Autowired
	private OperaterecordsService operaterecordsService;

	@Autowired
	private OrderUpdaterecordsService orderUpdaterecordsService;

	@Autowired
	private ApplyforcancelorderService applyforcancelorderService;

	@Autowired
	private OrderdetailService orderdetailService;
	@Autowired
	private OrderRemindService orderremindService;

	/**
	 * 平台获取店铺订单列表
	 * 
	 * @param page
	 *            起始页
	 * @param size
	 *            每页行数
	 * @param status
	 *            开始时间
	 * @param ordercode
	 *            买家Id
	 * @param begin
	 *            结束时间
	 * @param end
	 *            订单编号
	 * @param shopid
	 *            店铺Id
	 * @param buyerid
	 *            订单状态
	 * @return {"Data":[{"ID":137,"Code":"yy296485","GroupCode":"yy296477",
	 *         "Status":0,"PayType":0,"Price":666.00,"Freight":0.00,"SellerID":
	 *         21,"BuyerID":70,
	 *         "CouponID":0,"LogisticsCode":null,"AddOrderDate":
	 *         "2015-10-23 11:15:58"
	 *         ,"PayDate":null,"DeliverDate":null,"DeliverConfirmDate":null,
	 *         "IsOwned":true,
	 *         "BuyerName":"1@qq.com","SellerName":"admin1","CancelReason":null,
	 *         "Children":[{"ID":133,"OrderID":137,"OrderCode":"yy296485",
	 *         "BuyerID":70,"SellerID":21,
	 *         "SKU_ID":11,"ProductNum":"yy266716","ProductImg":null,
	 *         "ProductName":"昆虫2","ProductPrice":666.00,"ProductCount":1,
	 *         "ProductWeight":0.00,"VaildFlag":0,
	 *         "DelDate":null,"DelUserID":null,"IsComment":0,"IsShowImg":null,
	 *         "IsBackComment":0}]}],...,"Code":0,"Desc":""}
	 *         {...,"Code":-101,"Desc":"分页参数错误"} {...,"Code":-900,"Desc":"系统错误"}
	 */
	@RequestMapping(value = "/getshoplist", produces = "text/html;charset=UTF-8")
	public String getshopList(String page, String size, String status,
			String ordercode, String begin, String end, String shopid,
			String buyerid) {
		ReusltItem item = new ReusltItem();
		try {
			int index = StringUtilsEX.ToInt(page), pageRow = StringUtilsEX
					.ToInt(size);
			Integer Status = null;
			if (index <= 0 || pageRow <= 0) {
				index = 1;
				pageRow = 10;
			}
			int statustemp = StringUtilsEX.ToInt(status);
			if (statustemp >= 0) {
				Status = statustemp;
			}
			P_OrderListCriteria criteria = new P_OrderListCriteria();
			criteria.setBegin(StringUtilsEX.ToShortDate(begin));
			criteria.setEnd(StringUtilsEX.ToShortDate(end));
			criteria.setOrderByClause("AddOrderDate");
			criteria.setSort("desc");
			criteria.setStatus(Status);
			criteria.setBid(StringUtilsEX.ToInt(buyerid));
			criteria.setSid(StringUtilsEX.ToInt(shopid));
			if (!StringUtilsEX.IsNullOrWhiteSpace(ordercode)) {
				ordercode = ordercode.trim();
			}
			criteria.setOrdercode(ordercode);
			PageBean list = orderService.getP_OrdersList(index, pageRow,
					criteria);
			item.setCode(0);
			item.setData(list.getBeanList());
			if (list.getTr() == null) {
				item.setMaxRow(0);
			} else {
				item.setMaxRow(list.getTr());
			}
			item.setPageIndex(list.getPc());
			item.setPageSize(list.getPs());
		} catch (Exception ex) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,
					MessageFormat.format("平台获取店铺订单列表错误：{0}", ex.toString()),
					"/platform/order/getshoplist");
		}
		return item.toJson();
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
	public ReusltItem sendProForOrder(String orderid, String logisticscode,
			String logisticsname) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
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
			if (orderStatusService.updateSendProForOrder(oid,
					logisticsname.trim(), logisticscode.trim(), ip, user) > 0) {
				item.setDesc("订单发货操作成功");

				// 添加日志
				String desc = MessageFormat.format(
						"订单发货操作成功！订单ID:{0},订单编号:{1},物流单号:{2},物流名称:{3}",
						order.getId(), order.getCode(), logisticscode,
						logisticsname);
				LogHandle.info(LogType.Order, desc,
						"/platform/order/sendprofororder");
				operateLogService.addLog(OperateRecordsTypeEnum.修改,
						OperateRecordsFromEnum.系统后台, user.getId(),
						user.getLoginName(), ip, "",
						"/platform/order/sendprofororder", desc);
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors
						.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(
									OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(),
									user.getUserId(), user.getLoginName(),
									"ddgl_dpOrderList.jsp",
									"/platform/order/sendprofororder", "订单发货");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,
									"订单发货操作记录出错! 异常信息:", e,
									"/platform/order/sendprofororder");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("订单发货操作失败");
				LogHandle.warn(LogType.Order, MessageFormat.format(
						"订单发货失败！OrderID:{0},"
								+ "物流公司:{1},物流单号:{2},uid:{3},datetime:{4}",
						orderid, logisticsname, logisticscode, user.getId(),
						new Date()), "/platform/order/sendprofororder");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单发货操作错误：{0}", ex,
					"/platform/order/sendprofororder");

		}
		return item;
	}

	/**
	 * 获取直营订单列表
	 * 
	 * @param num
	 * @param status
	 * @param buyid
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getZyOrderList")
	public ReusltItem getZyOrderList(String num, String status, String buyerid,
			String start, String end, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(1); // 直营
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
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取店铺订单列表错误：{0}", e,
					"/platform/order/getZyOrderList");
		}
		return item;
	}

	/**
	 * 获取店铺订单列表
	 * 
	 * @param num
	 * @param status
	 * @param shopid
	 * @param buyerid
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getShopOrderList")
	public ReusltItem getShopOrderList(String num, String status,
			String shopid, String buyerid, String start, String end,
			String page, String size,String state) {
		ReusltItem item = new ReusltItem();
		try {
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(0); // 非直营
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cOrder.setOrdercode(num.trim());
			}
			if (StringUtilsEX.ToInt(status) >= 0) {
				cOrder.setStatus(StringUtilsEX.ToInt(status));
			}
			if (StringUtilsEX.ToInt(state) >= 0) {
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
			if (StringUtilsEX.ToInt(shopid) > 0) {
				cOrder.setShopid(StringUtilsEX.ToInt(shopid));
			}
			if (StringUtilsEX.ToInt(buyerid) > 0) {
				cOrder.setBuyid(StringUtilsEX.ToInt(buyerid));
			}
			cOrder.setAddbegin(StringUtilsEX.ToShortDate(start));
			cOrder.setAddend(StringUtilsEX.ToShortDate(end));
			cOrder.setOrderByClause("a.id");
			cOrder.setSort("desc");
			PageBean pageBean = orderService.getOrdersByPage(cOrder, Page, Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取店铺订单列表错误：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取店铺订单列表错误：{0}", e,
					"/platform/order/getShopOrderList");
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
	public ReusltItem confirmCancelOrder(String orderid, String status,
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
					user.getUserId(), isgr, reason, orders,
					user.getLoginName(), "") > 0) {
				item.setCode(0);
				item.setDesc("订单取消审核成功");
				String desc = MessageFormat.format(
						"订单取消审核成功！订单ID:{0},订单编号:{1},审核类型:{2}", orderid,
						orders[0].getCode(), isagree);
				LogHandle.info(LogType.Order, desc,
						"/platform/order/sendprofororder");
				String ip = GetIpAddresss.getIpAddr();
				operateLogService.addLog(OperateRecordsTypeEnum.修改,
						OperateRecordsFromEnum.系统后台, user.getUserId(),
						user.getLoginName(), ip, "",
						"platform/order/confirmCancelOrder", desc);
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors
						.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(
									OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(),
									user.getUserId(), user.getLoginName(),
									"ddgl_dpOrderList.jsp",
									"/platform/order/confirmCancelOrder",
									"订单取消审核");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,
									"订单取消审核操作记录出错! 异常信息:", e,
									"/platform/order/confirmCancelOrder");
						}

					}
				});
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPooll = Executors
						.newCachedThreadPool();
				cachedThreadPooll.execute(new Runnable() {
					@Override
					public void run() {
						try {
							int newstatus = 0;
							if (isgr == 1) {
								if (StringUtilsEX.ToInt(status) == OrderStatusEnum.审核中
										.getValue()) {
									newstatus = OrderStatusEnum.已取消
											.getValue();
								}
							} else {
								if (StringUtilsEX.ToInt(status) == OrderStatusEnum.审核中
										.getValue()) {
									newstatus = OrderStatusEnum.待付款.getValue();
								}
							}
							orderUpdaterecordsService.addOrderUpadateRecords(
									"Status", String.valueOf(status),
									String.valueOf(newstatus),
									StringUtilsEX.ToInt(orderid),
									user.getUserId(), user.getLoginName(), ip);
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,
									"订单取消审核操作记录出错! 异常信息:", e,
									"/platform/order/confirmCancelOrder");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("订单取消审核失败");
				LogHandle.warn(LogType.Order, MessageFormat.format(
						"订单取消审核成功！订单ID:{0},订单编号:{1},审核类型:{2}", orderid,
						orders[0].getCode(), isagree),
						"/platform/order/confirmCancelOrder");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("订单取消审核错误：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单取消审核错误：{0}", e,
					"/platform/order/confirmCancelOrder");
		}
		return item;
	}

	/**
	 * 订单未收货申请退款审核
	 * 
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
			if (StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.申请退款.getValue()) {
				item.setCode(-102);
				item.setDesc("订单状态参数错误，不能售后审核");
				return item;
			}
			Orders orders = new Orders();
			if (orderStatusService.updateChekAftermarketNoReceipt(
					StringUtilsEX.ToInt(id), user.getUserId(),
					StringUtilsEX.ToInt(status), isgr, reason, img, orders) > 0) {
				item.setCode(0);
				item.setDesc("订单售后申请审核成功");
				String desc = MessageFormat.format(
						"订单售后申请审核成功！订单ID:{0},订单编号:{1},审核类型:{2}",
						orders.getId(), orders.getCode(), isagree);
				LogHandle.info(LogType.Order, desc,
						"/platform/order/confirmReturnOrder");
				String ip = GetIpAddresss.getIpAddr();
				operateLogService.addLog(OperateRecordsTypeEnum.修改,
						OperateRecordsFromEnum.系统后台, user.getUserId(),
						user.getLoginName(), ip, "",
						"platform/order/confirmReturnOrder", desc);
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors
						.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							int newstatus = 0;
							if (isgr == 1) {
								newstatus = OrderStatusEnum.已取消.getValue();

							} else {
								Integer applytype = 0;
								Applyforcancelorder applycancel = applyforcancelorderService
										.getbyOrderAndType(
												StringUtilsEX.ToInt(id),
												applytype);
								if (applycancel != null) {
									newstatus = applycancel.getOrderstatus();
								}
							}
							orderUpdaterecordsService.addOrderUpadateRecords(
									"Status", status,
									String.valueOf(newstatus),
									StringUtilsEX.ToInt(id), user.getUserId(),
									user.getLoginName(), ip);
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,
									"订单售后申请审核操作记录出错! 异常信息:", e,
									"/platform/order/confirmReturnOrder");
						}

					}
				});

				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPooll = Executors
						.newCachedThreadPool();
				cachedThreadPooll.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(
									OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(),
									user.getUserId(), user.getLoginName(),
									"grouporder_list.jsp",
									"/platform/order/confirmReturnOrder",
									"订单售后申请审核");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,
									"订单售后申请审核操作记录出错! 异常信息:", e,
									"/platform/order/confirmReturnOrder");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("订单售后申请审核失败");
				LogHandle.warn(LogType.Order, MessageFormat.format(
						"订单售后申请审核成功！订单ID:{0},订单编号:{1},审核类型:{2}",
						orders.getId(), orders.getCode(), isagree),
						"/platform/order/confirmReturnOrder");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("订单退换货审核错误：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单退换货审核错误：{0}", e,
					"/platform/order/confirmReturnOrder");
		}
		return item;
	}

	/**
	 * 获取直营订单发票列表
	 * 
	 * @param code
	 * @param status
	 * @param type
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getzyInvoiceList")
	public ReusltItem getzyInvoiceList(String code, String status, String type,
			String start, String end, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(1); // 直营
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

			PageBean pageBean = invoiceService.getByOrderPage(cOrder, Page,
					Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取订单发票列表错误：{0}", e,
					"/platform/order/getzyInvoiceList");
		}
		return item;
	}

	/**
	 * 获取店铺订单发票列表
	 * 
	 * @param code
	 * @param shopid
	 * @param status
	 * @param type
	 * @param start
	 * @param end
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getdpInvoiceList")
	public ReusltItem getdpInvoiceList(String code, String shopid,
			String status, String type, String start, String end, String page,
			String size) {
		ReusltItem item = new ReusltItem();
		try {
			int Page = StringUtilsEX.ToInt(page);
			int Size = StringUtilsEX.ToInt(size);
			if (Page <= 0 || Size <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaOrder cOrder = new CriteriaOrder();
			cOrder.setIsowned(0); // 非直营
			if (!StringUtilsEX.IsNullOrWhiteSpace(code)) {
				cOrder.setOrdercode(code.trim());
			}
			if (StringUtilsEX.ToInt(status) >= 0) {
				cOrder.setStatus(StringUtilsEX.ToInt(status));
			}
			if (StringUtilsEX.ToInt(type) >= 0) {
				cOrder.setType(StringUtilsEX.ToInt(type));
			}
			if (StringUtilsEX.ToInt(shopid) > 0) {
				cOrder.setShopid(StringUtilsEX.ToInt(shopid));
			}
			cOrder.setAddbegin(StringUtilsEX.ToShortDate(start));
			cOrder.setAddend(StringUtilsEX.ToShortDate(end));

			PageBean pageBean = invoiceService.getByOrderPage(cOrder, Page,
					Size);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"获取订单发票列表错误：{0}", e,
					"/platform/order/getdpInvoiceList");
		}
		return item;
	}

	/**
	 * 更新发票打印状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/changeInvStatus")
	public ReusltItem changeInvStatus(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("发票ID参数错误");
				return item;
			}
			if (invoiceService.updateStatus(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("更新订单发票打印状态成功");
				LogHandle.info(LogType.Order,
						MessageFormat.format("更新订单发票打印状态成功,发票ID:{0},操作人ID:{1}",
								id, user.getUserId()),
						"/platform/order/changeInvStatus");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors
						.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(
									OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(),
									user.getUserId(), user.getLoginName(),
									"ddgl_dpInvoiceList.jsp",
									"/platform/order/changeInvStatus",
									"更新订单发票打印状态");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,
									"更新订单发票打印状态操作记录出错! 异常信息:", e,
									"/platform/order/changeInvStatus");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("更新订单发票打印状态失败");
				LogHandle.info(LogType.Order,
						MessageFormat.format("更新订单发票打印状态失败,发票ID:{0}操作人ID:{1}",
								id, user.getUserId()),
						"/platform/order/changeInvStatus");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"更新订单发票打印状态错误：{0}", e,
					"/platform/order/changeInvStatus");
		}
		return item;
	}

	
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
				item.setDesc("订单ID参数错误");
				return item;
			}
			int isgr = StringUtilsEX.ToInt(isagree);
			int applytype = 0;
			if (isgr != 0 && isgr != 1) {
				item.setCode(-103);
				item.setDesc("订单审核类型参数错误，只能为0或者1");
				return item;
			}
			if (StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.申请退款.getValue()) {
				item.setCode(-102);
				item.setDesc("订单状态参数错误，不能售后审核");
				return item;
			}

			Orders orders = new Orders();
			if (StringUtilsEX.ToInt(status) == OrderDetailStatusEnum.申请退款.getValue()) {
				if (orderStatusService.updateChekAftermarketNoReceipt(
						StringUtilsEX.ToInt(id), user.getUserId(),
						StringUtilsEX.ToInt(status), isgr, reason, img, orders) > 0) {
					item.setCode(0);
					item.setDesc("订单退款申请审核成功");
					String desc = MessageFormat.format(
							"订单退款申请审核成功！订单ID:{0},订单编号:{1},审核类型:{2}",
							orders.getId(), orders.getCode(), isagree);
					LogHandle.info(LogType.Order, desc,
							"/platform/order/checkAfterOrder");
					String ip = GetIpAddresss.getIpAddr();
					operateLogService.addLog(OperateRecordsTypeEnum.修改,
							OperateRecordsFromEnum.系统后台, user.getUserId(),
							user.getLoginName(), ip, "",
							"platform/order/checkAfterOrder", desc);
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors
							.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(
										OperateRecordsTypeEnum.修改.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(),
										user.getUserId(), user.getLoginName(),
										"ddgl_thh.jsp",
										"/platform/order/checkAfterOrder",
										"订单退款申请审核");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,
										"订单退款申请审核操作记录出错! 异常信息:", e,
										"/platform/order/checkAfterOrder");
							}

						}
					});

					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPooll = Executors
							.newCachedThreadPool();
					cachedThreadPooll.execute(new Runnable() {
						@Override
						public void run() {
							try {

								Integer newstatus = 0;
								Integer applytype = 0;
								if (isgr == 1) {
									newstatus = OrderStatusEnum.已取消.getValue();
								} else {
									Applyforcancelorder applycancel = applyforcancelorderService
											.getbyOrderAndType(
													StringUtilsEX.ToInt(id),
													applytype);
									if (applycancel != null) {
										newstatus = applycancel
												.getOrderstatus();
									}
								}

								orderUpdaterecordsService
										.addOrderUpadateRecords("Status",
												status,
												String.valueOf(newstatus),
												StringUtilsEX.ToInt(id),
												user.getUserId(),
												user.getLoginName(), ip);
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,
										"订单退款申请审核操作记录出错! 异常信息:", e,
										"/platform/order/checkAfterOrder");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("订单退款申请审核失败");
					LogHandle.warn(LogType.Order, MessageFormat.format(
							"订单退款申请审核失败！订单ID:{0},订单编号:{1},审核类型:{2}",
							orders.getId(), orders.getCode(), isagree),
							"/platform/order/checkAfterOrder");
				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单售后申请审核错误：{0}", e,
					"/platform/order/checkAfterOrder");
		}
		return item;
	}

	/**
	 * 订单删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delOrder")
	public ReusltItem delOrder(String orderid) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(orderid) <= 0) {
				item.setCode(-101);
				item.setDesc("订单ID参数错误");
				return item;
			}
			if (orderService.delbyId(StringUtilsEX.ToInt(orderid),
					user.getUserId()) > 0) {
				item.setCode(0);
				item.setDesc("订单删除成功");
				String desc = MessageFormat.format("订单删除成功！订单ID:{0}", orderid);
				LogHandle.info(LogType.Order, desc, "/platform/order/delOrder");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors
						.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(
									OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(),
									user.getUserId(), user.getLoginName(),
									"ddgl_dpOrderList.jsp",
									"/platform/order/delOrder", "订单删除");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,
									"订单删除操作记录出错! 异常信息:", e,
									"/platform/order/delOrder");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("订单删除失败");
				String desc = MessageFormat.format("订单删除失败！订单ID:{0}", orderid);
				LogHandle.info(LogType.Order, desc, "/platform/order/delOrder");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单售后收货操作错误：{0}", e,
					"/platform/order/delOrder");
		}
		return item;

	}

	/**
	 * 催单列表
	 * 
	 * @param isread
	 *            是否受理
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/cdorderList")
	public ReusltItem getlist(boolean isread, Integer page, Integer size) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			OrderRemindCriteria criteria = new OrderRemindCriteria();
			criteria.setIsread(isread);
			criteria.setSupplierid(user.getShopid());
			PageBean pageBean = orderremindService.selectOrderRemindPage(
					criteria, page, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order, "获取订单列表错误：{0}", e,
					"/platofrm/shoporder/cdorderList");
		}
		return item;
	}

	/**
	 * 催单受理
	 * 
	 * @param orderid
	 * @param sljg
	 * @return
	 */
	@RequestMapping("/slcdResult")
	public ReusltItem slcdResult(String orderid, String sljg) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(orderid) < 0) {
				item.setCode(-101);
				item.setDesc("订单id不能为空,id" + orderid);
				return item;
			}
			OrderRemind or = orderremindService
					.selectByPrimaryKey(StringUtilsEX.ToInt(orderid));
			or.setIsread(true);
			or.setRemark(sljg);
			orderremindService.updateByPrimaryKeySelective(or);
			item.setCode(0);
			item.setDesc("处理成功");
			/*
			 * orderService.sendMessage(or.getOrderid(), or.getOrdernum(),
			 * or.getOrdertime(),"","", 3, 0,
			 * TemplateTagEnum.受理成功,MessagesTypeEnum.订单提醒);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-200);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("订单处理失败" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			String desc = MessageFormat.format(
					"订单受理失败！催单ID(订单备份):{0},操作人ID:{1}", orderid,
					user.getUserId());
			LogHandle.warn(LogType.Order, desc, "/platform/shoporder/slcdResult");
		}
		return item;
	}

	/**
	 * 订单售后纠纷处理
	 * 
	 * @param id 订单id
	 * @param status 订单状态
	 * @param reason 原因说明
	 * @param img 说名图片
	 * @return
	 */
	@RequestMapping("/dealafterorder")
	public ReusltItem dealafterorder(String id, String status, String reason,
			String img) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("订单ID参数错误");
				return item;
			}
			int isgr = StringUtilsEX.ToInt("1");
			int applytype = 0;

			/*if (StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.退货申请被拒绝	.getValue()					
				&&StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.退款申请被拒绝.getValue()
				&& StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.换货申请被拒绝.getValue()
				&& StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.维修申请被拒绝.getValue()) {
				item.setCode(-102);
				item.setDesc("订单状态参数错误，不能售后审核");
				return item;
			}*/

			OrderDetailStatusEnum detailStatus = OrderDetailStatusEnum
					.valueOf(StringUtilsEX.ToInt(status));
			switch (detailStatus) {
			/*case 退款申请被拒绝:
				applytype = OrderApplyTypeEnum.订单退款.getValue();
				break;
			case 换货申请被拒绝:
				applytype = OrderApplyTypeEnum.订单换货.getValue();
				break;
			case 维修申请被拒绝:
				applytype = OrderApplyTypeEnum.订单维修.getValue();
				break;
			case 退货申请被拒绝:
				applytype = OrderApplyTypeEnum.订单退货退款.getValue();
				break;*/
			default:
				break;
			}
				int temp = applytype;
				String ip = GetIpAddresss.getIpAddr();
				if (orderStatusService.dealAfterApply(StringUtilsEX.ToInt(id), applytype, reason, img,ip) > 0) {
					item.setCode(0);
					item.setDesc("订单售后审核成功");
					String desc = MessageFormat.format(
							"订单售后审核成功！订单详情ID:{0},审核类型:{1}", id, isgr);
					LogHandle.info(LogType.Order, desc,
							"/platform/order/dealafterorder");

					operateLogService.addLog(OperateRecordsTypeEnum.修改,
							OperateRecordsFromEnum.系统后台, user.getUserId(),
							user.getLoginName(), ip, "",
							"platform/order/dealafterorder", desc);
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors
							.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(
										OperateRecordsTypeEnum.修改.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(),
										user.getUserId(), user.getLoginName(),
										"ddgl_thh.jsp",
										"/platform/order/dealafterorder",
										"订单售后申请处理审核");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,
										"订单售后处理审核操作记录出错! 异常信息:", e,
										"/platform/order/dealafterorder");
							}

						}
					});

					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPooll = Executors
							.newCachedThreadPool();
					cachedThreadPooll.execute(new Runnable() {
						@Override
						public void run() {
							try {

								Integer newstatus = 0;
								switch (OrderApplyTypeEnum.valueOf(temp)) {

								/*case 订单退货退款:
									newstatus = OrderDetailStatusEnum.退货退款成功.getValue();
									break;
								case 订单换货:
									newstatus = OrderDetailStatusEnum.换货成功.getValue();
									break;
								case 订单维修:
									newstatus = OrderDetailStatusEnum.维修成功.getValue();
									break;*/
								default:
									newstatus = 0;
								}

								orderUpdaterecordsService
										.addOrderUpadateRecords("Status",
												status,
												String.valueOf(newstatus),
												StringUtilsEX.ToInt(id),
												user.getUserId(),
												user.getLoginName(), ip);
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,
										"订单退款审核操作记录出错! 异常信息:", e,
										"/platform/order/dealafterorder");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("订单售后审核失败");
					LogHandle.warn(LogType.Order, MessageFormat.format(
							"订单售后审核失败！订单详情ID:{0},审核类型:{1}", id, isgr),
							"/platform/order/dealafterorder");
				}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Order,"订单售后审核错误：{0}", e,
					"/platform/order/dealafterorder");
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
					"/platform/order/updateOrderTK");
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
					"/platform/order/updatePartTK");
		}
		return item;
	}
	
}
