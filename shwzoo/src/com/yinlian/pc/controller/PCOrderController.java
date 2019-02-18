package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.ImageTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.OrderApplyTypeEnum;
import com.yinlian.Enums.OrderDetailStatusEnum;
import com.yinlian.Enums.OrderStatusEnum;
import com.yinlian.Enums.ShopCartProType;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.AddNewDetailDto;
import com.yinlian.api.app.dto.AddNewOrderDto;
import com.yinlian.api.app.dto.OrderCountDto;
import com.yinlian.api.app.dto.OrderMemberDto;
import com.yinlian.api.app.dto.OrderdetailDto;
import com.yinlian.api.app.dto.TrackQueryDto;
import com.yinlian.pc.dto.CommentDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.KdniaoTrackQueryAPI;
import com.yinlian.wssc.search.Pc_OrderCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.Dispatching;
import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.po.Invoice;
import com.yinlian.wssc.web.po.OrderRemind;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Satisfaction;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.DispatchingService;
import com.yinlian.wssc.web.service.ImagesService;
import com.yinlian.wssc.web.service.InvoiceService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderRemindService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.service.OrderUpdaterecordsService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.ReceiveAddressService;
import com.yinlian.wssc.web.service.ReceiveInfoService;
import com.yinlian.wssc.web.service.SatisfactionService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/order")
public class PCOrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ReceiveInfoService receiveInfoService;

	@Autowired
	private ReceiveAddressService receiveAddressService;

	@Autowired
	private DispatchingService dispatchingService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private OrderdetailService orderdetailService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private OperaterecordsService operaterecordsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private SatisfactionService satisfactionService;
	@Autowired
	private ImagesService imagesService;
	@Autowired
	private UserService userservice;
	@Autowired
	private OrderRemindService orderremindService;
	@Autowired
	private OrderUpdaterecordsService orderUpdaterecordsService;

	/**
	 * 售后申请
	 * 
	 * @param ch
	 * @param token
	 * @param orderdetailid
	 *            订单详情id
	 * @param applytype
	 *            售后申请类型 1-订单退货退款 2-订单换货 3-订单维修 4-订单退款
	 * @param reason
	 * @param images
	 *            eg: "/dsm/imge1,/dsm/imge2"
	 * @return
	 */
	@RequestMapping(value = "/applyafter", produces = "text/html;charset=UTF-8")
	public String applyafter(String orderdetailid, String applytype, String reason, String images,
			HttpServletRequest request) {

		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.ToInt(orderdetailid) < 0) {
				item.setCode(-101);
				item.setDesc("订单详情id(orderdetailid)参数错误：" + orderdetailid);
				return item.toJson();
			}

			if (StringUtils.isBlanck(reason)) {
				item.setCode(-102);
				item.setDesc("申请原因不能为空");
				return item.toJson();
			}
			String token = CookieUtils.getTokenFromCookie(request);
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(applytype) < 0) {
				item.setCode(-103);
				item.setDesc("申请类型参数错误");
				return item.toJson();
			}
			int type = StringUtilsEX.ToInt(applytype);
			if (type != OrderApplyTypeEnum.订单退货退款.getValue() && type != OrderApplyTypeEnum.订单换货.getValue()
					&& type != OrderApplyTypeEnum.订单维修.getValue() && type != OrderApplyTypeEnum.订单退款.getValue()) {
				item.setCode(-103);
				item.setDesc("申请类型参数错误");
				return item.toJson();
			}
			// int userid=492;
			String ip = GetIpAddresss.getIpAddr();
			int result = orderStatusService.updateAfterServiceApply(StringUtilsEX.ToInt(orderdetailid), type,
					user.getUserId(),
					// StringUtilsEX.ToInt(orderdetailid), type, userid,
					reason, images, item);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("售后申请成功");
				LogHandle.info(LogType.pc, MessageFormat.format("售后申请成功! 订单详情id{0},申请类型{1}", orderdetailid, applytype),
						"/order/applyafter");
				ExecutorService executorService = Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {							
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(),
									"RepairOrReturn.html", "/pc/order/applyafter", "售后申请");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "售后申请 异常信息:", e, "/pc/order/applyafter");
						}
					}
				});
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Orderdetail detail = orderdetailService.queryByID(StringUtilsEX.ToInt(orderdetailid));
							int newstatus = 0;
							OrderApplyTypeEnum applytype = OrderApplyTypeEnum.valueOf(type);
							/*switch (applytype) {
							case 订单退款:
								newstatus = OrderDetailStatusEnum.申请退款.getValue();
								break;
							case 订单退货退款:
								newstatus = OrderDetailStatusEnum.申请退货退款.getValue();
								break;
							case 订单换货:
								newstatus = OrderDetailStatusEnum.申请换货.getValue();
								break;
							case 订单维修:
								newstatus = OrderDetailStatusEnum.申请维修.getValue();
								break;
							default:
								newstatus = detail.getStatus();
							}*/
							orderUpdaterecordsService.addOrderUpadateRecords("Status",
									String.valueOf(detail.getStatus()), String.valueOf(newstatus),
									StringUtilsEX.ToInt(orderdetailid), user.getUserId(), user.getLoginName(), ip);
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "售后申请 操作记录出错! 异常信息:", e, "/pc/order/applyafter");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("售后申请失败");
				LogHandle.info(LogType.pc,
						MessageFormat.format("售后申请错误! 错误信息:订单详情id{0},申请类型{1}", orderdetailid, applytype),
						"/order/applyafter");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("申请退款异常：" + e.getMessage());
			}else {
				item.setDesc("申请退款异常");
			}			
			LogHandle.debug(LogType.pc, MessageFormat.format("售后申请异常! 异常信息:{0}", e.toString()), "order/applyafter");
		}
		return item.toJson();
	}
	
	/**
     * 查询物流信息
     *@param expcode
     *@param expno
     *@return
     */
    @RequestMapping(value = "/querytrack")
    public String querytrack(String orderid) {
        ReusltItem item = new ReusltItem();
        Gson gson = new Gson();
        try {
            if (StringUtils.isBlanck(orderid)) {
                item.setCode(-102);
                item.setDesc("订单错误{id}:" + orderid);
                return item.toJson();
            }
            Orders orders = orderService.getOrderByID(StringUtilsEX.ToInt(orderid));
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            String expcode = orders.getLogisticsname(); //物流的公司编码 请填写对应的编码 例如：顺丰：SF
            String expno = orders.getLogisticscode(); // 物流单号
            String result = api.getOrderTracesByJson(orders.getCode(), expcode, expno); // 得到物流结果
            gson = new Gson();
            TrackQueryDto dto = gson.fromJson(result, TrackQueryDto.class);
            item.setCode(0);
            item.setData(dto);
        } catch (Exception e) {
            if (DebugConfig.BLUETOOTH_DEBUG) {
                item.setDesc(e.getMessage());
            } else {
                item.setDesc("系统错误！");
            }
            item.setCode(-900);
            LogHandle.error(LogType.Order, MessageFormat.format("获取物流信息错误：{0}, orderid:{1}", e.getMessage(), orderid),
                "PCOrder/querytrack");
        }
        return item.toJson();
    }

	/**
	 * 删除订单
	 * 
	 * @param token
	 * @param ch
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delorder", produces = "text/html;charset=UTF-8")
	public String delOrder(String id, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			Integer _orderid = StringUtilsEX.ToInt(id);
			if (_orderid < 0) {
				item.setCode(-101);
				item.setDesc("订单id不能为空,id" + _orderid);
				return item.toJson();
			}

			SessionUser user = SessionState.GetCurrentUser();
			if (user==null||user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			}
				Integer userid = user.getUserId();
				int result = orderService.delOrder(_orderid, userid);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("删除成功");
					ExecutorService executorService = Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
										OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(),
										"OrderList.html", "/pc/order/delorder", "删除订单");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "删除订单 异常信息:", e, "/pc/order/delorder");
							}
						}
					});

				} else {
					item.setCode(-200);
					item.setDesc("删除失败");
					LogHandle.debug(LogType.pc, MessageFormat.format("取消付款订单失败! 错误信息,{id},", _orderid),
							"order/delOrder");
				}
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("删除订单异常：" + e.getMessage());
			}else {
				item.setDesc("删除订单异常");
			}			
			LogHandle.debug(LogType.pc, MessageFormat.format("删除订单异常! 异常信息:{0}", e.toString()), "order/delOrder");
		}

		return item.toJson();
	}

	/**
	 * 取消订单
	 * 
	 * @param token
	 * @param status
	 * @param ch
	 * @param reason
	 * @param id
	 * @return
	 */
	/*@RequestMapping(value = "/cancel", produces = "text/html;charset=UTF-8")
	public String cancel(String status, String reason, String orderid, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			Integer _orderid = StringUtilsEX.ToInt(orderid);
			if (_orderid < 0) {
				item.setCode(-101);
				item.setDesc("订单id不能为空,id" + orderid);
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(status)) {
				item.setCode(-102);
				item.setDesc("订单类型(status)不能为空！");
				return item.toJson();
			}

			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			} else {
				Integer type = StringUtilsEX.ToInt(status);
				// if (type != OrderStatusEnum.待付款.getValue()
				// && type != OrderStatusEnum.待发货.getValue()) {
				// item.setCode(-103);
				// item.setDesc("当前状态不允许取消");
				// } else {
				// int USERID=492;
				String ip = GetIpAddresss.getIpAddr();
				int result = orderStatusService.updateCancelOrder(_orderid, type, user.getUserId(), reason);
				// type, 492, reason);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("取消成功");

					ExecutorService executorService = Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
										OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(),
										"OrderList.html", "/pc/order/cancel", "取消订单");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "取消订单 异常信息:", e, "/pc/order/cancel");
							}
						}
					});

					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user = SessionState.GetCurrentUser();
								int newstatus = 0;
								if (type == OrderStatusEnum.待付款.getValue()) {

									newstatus = OrderStatusEnum.未付款已取消.getValue();

								} else if (type == OrderStatusEnum.待发货.getValue()) {

									newstatus = OrderStatusEnum.已付款取消申请中.getValue();
								}
								orderUpdaterecordsService.addOrderUpadateRecords("Status", status,
										String.valueOf(newstatus), _orderid, user.getUserId(), user.getLoginName(), ip);
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "取消付款订单操作记录出错! 异常信息:", e, "/pc/order/cancel");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("取消失败");
					LogHandle.debug(LogType.pc, MessageFormat.format("取消付款订单失败! 错误信息,{id},{status}", _orderid, status),
							"order/cancel");
				}
				// }
			}
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("取消付款订单异常：" + e.getMessage());
			}else {
				item.setDesc("取消付款订单异常");
			}			
			LogHandle.debug(LogType.pc, MessageFormat.format("取消付款订单异常! 异常信息:{0}", e.toString()), "order/cancel");
		}

		return item.toJson();
	}*/

	/**
	 * 确认收货
	 * 
	 * @param orderid
	 * @param userip
	 * @param token
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/comfirmreceive", produces = "text/html;charset=UTF-8")
	public String comfirmReceive(String orderid, String ch) {
		ReusltItem item = new ReusltItem();
		try {

			Integer _orderid = StringUtilsEX.ToInt(orderid);
			if (_orderid < 0) {
				item.setCode(-101);
				item.setDesc("订单id参数错误,id:" + orderid);
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser();
			if (user == null || user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			String userip = GetIpAddresss.getIp();			
			int result = orderStatusService.updatecomfirmReceive(_orderid, user, userip);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("确认收货成功");

				ExecutorService executorService = Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(),
									"OrderList.html", "/pc/order/comfirmreceive", "确认收货");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "确认收货 异常信息:", e, "/pc/order/comfirmreceive");
						}
					}
				});

				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							/*orderUpdaterecordsService.addOrderUpadateRecords("Status",
									OrderStatusEnum.待收货.getValue().toString(),
									OrderStatusEnum.已确认收货.getValue().toString(), _orderid, user.getUserId(),
									user.getLoginName(), userip);*/
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "确认收货操作记录出错! 异常信息:", e, "/pc/order/comfirmreceive");
						}

					}
				});

			} else {
				item.setCode(-200);
				item.setDesc("确认收货失败");
				LogHandle.debug(LogType.pc, MessageFormat.format("确认收货失败! 参数信息:{0}", _orderid),
						"order/comfirmreceive");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG)
			{
				item.setDesc("确认收货异常：" + e.getMessage());
			}else{
				item.setDesc("确认收货异常");
			}
			
			LogHandle.debug(LogType.pc, MessageFormat.format("确认收货异常! 异常信息:{0}", e.toString()), "order/comfirmreceive");
		}
		return item.toJson();
	}

	@RequestMapping(value = "/byorderidcomment", produces = "text/html;charset=UTF-8")
	public String byorderidcomment(String orderid, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(orderid)) {
				item.setCode(-201);
				item.setDesc("訂單ID不能為空");
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
			} else {
				int buyerId = sessionUser.getUserId();
				int orderids = Integer.parseInt(orderid);
				List<OrderMemberDto> list = orderService.byorderidcomment(buyerId, orderids);
				item.setData(list);
				item.setCode(0);
				item.setDesc("获取评价消息成功");

			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询评价订单异常：" + e.getMessage());
			}else {
				item.setDesc("查询评价订单异常");
			}			
			LogHandle.error(LogType.pc, MessageFormat.format("查询评价订单异常! 异常信息:{0}", e.toString()),
					"order/byorderidcomment");

		}
		return item.toJson();
	}

	/**
	 * 
	 * @param item
	 * @param orders
	 * @param Invoices
	 * @param disp
	 * @param invoice
	 * @param orderParams
	 * @param receiveAddrID
	 * @param dispType
	 * @param dateType
	 * @param timeType
	 * @param invoiceType
	 * @param invoiceTitleType
	 * @param invoiceTitle
	 * @param invoiceContent
	 * @param mobile
	 * @param email
	 * @param zyActivityID
	 * @param zyConponID
	 * @param receiveID
	 */
	private void checkOrderParams(BaseResult item, List<AddNewOrderDto> orders, List<Invoice> Invoices,
			Dispatching disp, Invoice invoice, String orderParams, String receiveAddrID, String dispType,
			String dateType, String timeType, String invoiceType, String invoiceTitleType, String invoiceTitle,
			String invoiceContent, String mobile, String email, String zyActivityID, String zyConponID,
			Integer[] receiveID) throws Exception {
		try {
			String[] ordersStr = orderParams.split(";");
			for (int i = 0; i < ordersStr.length; i++) {
				// OrderDto参数验证

				AddNewOrderDto orderDto = new AddNewOrderDto();
				String[] orderStr = ordersStr[i].split(",");
				int shopID = StringUtilsEX.ToInt(orderStr[0].split(":")[1]);
				if (shopID <= 0) {
					item.setCode(-102);
					item.setDesc("店铺id参数错误");
					return;
				}
				orderDto.setShopID(shopID);
				Double totalMoney = StringUtilsEX.ToDouble(orderStr[1].split(":")[1]);
				if (totalMoney < 0) {
					item.setCode(-103);
					item.setDesc("订单总金额参数错误");
					return;
				}
				orderDto.setTotalMoney(totalMoney);
				double delMoney = StringUtilsEX.ToDouble(orderStr[2].split(":")[1]);
				if (delMoney < 0) {
					item.setCode(-104);
					item.setDesc("优惠金额参数错误");
					return;
				}
				orderDto.setDelMoney(delMoney);
				double freightMoney = StringUtilsEX.ToDouble(orderStr[3].split(":")[1]);
				if (freightMoney < 0) {
					item.setCode(-105);
					item.setDesc("运费参数错误");
					return;
				}
				orderDto.setFreightMoney(freightMoney);
				int couponID = StringUtilsEX.ToInt(orderStr[4].split(":")[1]);
				if (couponID < 0) {
					item.setCode(-106);
					item.setDesc("优惠券id参数错误");
					return;
				}
				orderDto.setCouponID(couponID);
				int activityID = StringUtilsEX.ToInt(orderStr[5].split(":")[1]);
				if (activityID < 0) {
					item.setCode(-107);
					item.setDesc("活动id参数错误");
					return;
				}
				orderDto.setActivityID(activityID);

				int isInvoice = StringUtilsEX.ToInt(orderStr[6].split(":")[1]);
				if (isInvoice < 0 || isInvoice > 1) {
					item.setCode(-108);
					item.setDesc("isInvoice参数错误");
					return;
				}
				orderDto.setIsInvoice(isInvoice);
				String remark = orderStr[7].split(":")[1];
				orderDto.setRemark(remark);
				// #endregion

				List<AddNewDetailDto> details = new ArrayList<AddNewDetailDto>();
				// #region DetailDto参数验证

				String[] detailsStr = orderStr[8].split("\\|");
				for (int j = 0; j < detailsStr.length; j++) {
					AddNewDetailDto detailDto = new AddNewDetailDto();
					String[] detailStr = detailsStr[j].split("-");
					int skuID = StringUtilsEX.ToInt(detailStr[0].split(":")[1]);
					if (skuID <= 0) {
						item.setCode(-109);
						item.setDesc("商品ID参数错误");
						return;
					}
					detailDto.setSkuID(skuID);
					int proCount = StringUtilsEX.ToInt(detailStr[1].split(":")[1]);
					if (proCount <= 0) {
						item.setCode(-110);
						item.setDesc("商品数量参数错误");
						return;
					}
					detailDto.setProCount(proCount);
					int packageID = StringUtilsEX.ToInt(detailStr[2].split(":")[1]);
					if (packageID < 0) {
						item.setCode(-111);
						item.setDesc("组合商品ID参数错误");
						return;

					}
					detailDto.setPackageID(packageID);
					int marketID = StringUtilsEX.ToInt(detailStr[3].split(":")[1]);
					if (marketID < 0) {
						item.setCode(-112);
						item.setDesc("商品活动ID参数错误");
						return;
					}
					detailDto.setMarketID(marketID);
					if (packageID <= 0) {
						int spuid = StringUtilsEX.ToInt(detailStr[4].split(":")[1]);
						// if (spuid <= 0)
						// {
						// model.Code = -112;
						// model.Desc = "标准商品ID参数错误";
						// return model.ToJson();
						// }
						detailDto.setSpuID(spuid);
					} else {
						detailDto.setSpuID(0);
					}
					detailDto.setType(ShopCartProType.values()[StringUtilsEX.ToInt(detailStr[6].split(":")[1])]);
					detailDto.setSpikeID(StringUtilsEX.ToInt(detailStr[5].split(":")[1]));
					details.add(detailDto);
				}
				// #endregion
				orderDto.setDetails(details);
				orders.add(orderDto);
				// 发票信息
				int invType = StringUtilsEX.ToInt(invoiceType);

				if (invType > 0) {
					invoice = new Invoice();
					invoice.setType(invType);
					invoice.setTitletype(StringUtilsEX.ToInt(invoiceTitleType));
					if (StringUtils.isNotNull(invoiceTitle))
						invoice.setTitle(invoiceTitle.trim());
					if (StringUtils.isNotNull(invoiceContent))
						invoice.setContent(invoiceContent.trim());
					if (StringUtils.isNotNull(mobile))
						invoice.setMobile(mobile.trim());
					if (StringUtils.isNotNull(email))
						invoice.setEmail(email.trim());
					invoice.setStatus(0);
					invoice.setVaildflag(0);
					invoice.setCreatedate(new Date());
					;
					Invoices.add(invoice);
				}
			}
			// #region receiveID & Dispatching & Invoice
			receiveID[0] = StringUtilsEX.ToInt(receiveAddrID);
			if (receiveID[0] <= 0) {
				item.setCode(-113);
				item.setDesc("请选择收获地址或添加收获地址");
				return;
			}
			disp.setType(StringUtilsEX.ToInt(dispType));
			disp.setDatetype(StringUtilsEX.ToInt(dateType));
			disp.setTimetype(StringUtilsEX.ToInt(timeType));
			// int invType = invoiceType);
			// if (invType > 0) {
			// invoice = new Invoice();
			// invoice.Type = invType;
			// invoice.TitleType = titleType;
			// invoice.Title = title.Trim();
			// invoice.Content = content.Trim();
			// invoice.Mobile = mobile.Trim();
			// invoice.Email = email.Trim();
			// invoice.Status = 0;
			// }
			// #endregion
		} catch (Exception ex) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("参数格式错误，" + ex.toString());
			} else {
				item.setDesc("系统错误!");
			}
			return;
		}
	}

	/**
	 * 
	 * @param orderParams
	 *            shopID:x,totalMoney:x,delMoney:x,freightMoney:x,
	 *            couponID:x,activityid:x,isInvoice:x,remark:x,skuID:y-proCount:
	 *            y-packageid:y-marketid:
	 *            y-spikeid:y-type:y|skuID:y-proCount:y-packageid:y-marketid:y-
	 *            spuid:y-spikeid:y-type:y;
	 *            shopID:x,totalMoney:x,delMoney:x,freightMoney:x,couponID:x,
	 *            activityid:x,isInvoice:x,remark:x,skuID:y-proCount:y-packageid
	 *            :y-marketid:y|skuID:y-proCount:y-packageid:y-marketid:y-spuid:
	 *            y 参数示例说明: 以分号区分主订单，以逗号区分主订单各参数(子订单集合作为主订单参数之一)，
	 *            以竖线|区分子订单，以短杠区分子订单各参数，以冒号区分键值
	 * @param receiveAddrID
	 *            收货地址ID
	 * @param dispType
	 *            配送方式 0快递 1自提
	 * @param dateType
	 *            >配送日期类型 0任意 1工作日 2节假日
	 * @param timeType
	 *            配送时段类型 0任意 1 9:00-18:00 2 18:00-22:00
	 * @param invoiceType
	 *            发票类型 0不需要发票 1普通发票 2电子发票 3增值税发票
	 * @param invoiceTitleType
	 *            抬头类型 0个人 1公司
	 * @param invoiceTitle
	 *            发票抬头
	 * @param invoiceContent
	 *            发票内容
	 * @param mobile
	 *            手机号（预留电子发票用）
	 * @param email
	 *            邮箱（预留电子发票用）
	 * @param zyActivityID
	 *            平台全场通用活动Id
	 * @param zyConponID
	 *            平台全场通用优惠卷Id
	 * @param token
	 *            用户登陆凭证
	 * @return
	 */
	@RequestMapping(value = "/add", produces = "text/html;charset=UTF-8")
	public String Create(String orderparams, String receiveaddrid, String disptype, String datetype, String timetype,
			String invoicetype, String invoicetitletype, String invoicetitle, String invoicecontent, String mobile,
			String email, String zyactivityid, String zyconponid, String beans, String scids, String ch) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser();
			if (user == null || user.getCode() < 0) {
				item.setCode(-201);
				item.setDesc("用户未登陆");
				return item.toJson();
			}
			List<AddNewOrderDto> orders = new ArrayList<AddNewOrderDto>();
			List<Invoice> Invoices = new ArrayList<Invoice>();
			Dispatching disp = new Dispatching();
			Invoice invoice = null;
			Integer[] receiveID = new Integer[1];
			Idcardinfo idcardinfo = new Idcardinfo();
			checkOrderParams(item, orders, Invoices, disp, invoice, orderparams, receiveaddrid, disptype, datetype,
					timetype, invoicetype, invoicetitletype, invoicetitle, invoicecontent, mobile, email, zyactivityid,
					zyconponid, receiveID);
			if (item.getCode() < 0) {
				return item.toJson();
			}
			String[] groupCode = new String[2];
			Integer pbeans = StringUtilsEX.ToIntNull(beans);
			if (pbeans == null || pbeans < 100) {
				pbeans = 0;
			}
			if (orderService.add(orders, disp, Invoices, receiveID[0], StringUtilsEX.ToInt(zyconponid),
					StringUtilsEX.ToInt(zyactivityid), user.getUserId(), user.getLoginName(), GetIpAddresss.getIpAddr(),
					pbeans, groupCode, scids, ActivityUsePlatformEnum.pc.getValue(),idcardinfo)) {
				item.setDesc("订单提交成功");
				item.setData(groupCode[0]);
				item.setSum(StringUtilsEX.ToInt(groupCode[1]));
				LogHandle.info(LogType.pc,
						MessageFormat.format("添加新订单成功！添加时间:{0},UserID:{1}", new Date(), user.getId()), "order/add");
				ExecutorService executorService = Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(),
									"order_jiesuan.html", "/pc/order/comfirmreceive", "添加订单");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加订单 异常信息:", e, "/pc/order/comfirmreceive");
						}
					}
				});

				// 添加日志
				// String desc =MessageFormat.format("添加新订单成功！订单组编号:{0}",
				// groupCode[0]);
				// OperateLogService.AddLog(OperateRecordsTypeEnum.添加,
				// OperateRecordsFromEnum.web前台, user.ID, user.UserName,
				// PublicClass.GetUserIp, "order_jiesuan.cshtml", "Order/Add",
				// desc);
			} else {
				item.setCode(-200);
				item.setDesc("订单提交失败：" + groupCode[0]);
				LogHandle.warn(LogType.pc, MessageFormat.format("添加新订单失败！添加时间:{0},参数:{1},UserID:{2}", new Date(),
						orderparams, user.getId()), "/pc/order/add");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			ex.printStackTrace();
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误!");
			}
			LogHandle.error(LogType.pc, "添加新订单失败{0}", ex, "/pc/order/add");
		}
		return item.toJson();
	}

	@RequestMapping(value = "/getorderlist", produces = "text/html;charset=UTF-8")
	public String getorderlist(String type, String status, String page, String size, String ch) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser();
			if (user == null || user.getCode() < 0) {
				item.setCode(-201);
				item.setDesc("用户未登陆");
				return item.toJson();
			}
			Integer page1 = StringUtilsEX.ToIntNull(page);
			Integer size11 = StringUtilsEX.ToIntNull(size);
			if (page1 == null || page1 <= 0) {
				page1 = 1;
			}
			if (size11 == null || size11 <= 0) {
				size11 = 10;
			}
			Pc_OrderCriteria aoc = new Pc_OrderCriteria();
			aoc.setUserid(user.getUserId());
			aoc.setStatus(StringUtilsEX.ToInt(status));
			if (type == null) {
				// 所有的订单
			} else {
				int types = Integer.parseInt(type);
				if (types == 0) {
					// 三个月以内的订单
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String end = formatter.format(calendar.getTime());
					calendar.add(Calendar.MONTH, -3);
					String start = formatter.format(calendar.getTime());
					aoc.setStartdate(StringUtilsEX.ToShortDate(start));
					aoc.setEnddate(StringUtilsEX.ToShortDate(end));
				} else if (types == 1) {
					// 今年内的订单
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String end = formatter.format(calendar.getTime());
					calendar.set(Calendar.MONTH, 0);
					calendar.set(Calendar.DATE, 0);
					calendar.set(Calendar.HOUR, 12);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					String start = formatter.format(calendar.getTime());
					aoc.setStartdate(StringUtilsEX.ToShortDate(start));
					aoc.setEnddate(StringUtilsEX.ToShortDate(end));
				} else if (types == 2) {
					// 去年的
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					calendar.set(Calendar.MONTH, 0);
					calendar.set(Calendar.DATE, 0);
					calendar.set(Calendar.HOUR, 12);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					String end = formatter.format(calendar.getTime());
					calendar.add(Calendar.YEAR, -1);
					calendar.set(Calendar.MONTH, 0);
					calendar.set(Calendar.DATE, 1);
					calendar.set(Calendar.HOUR, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					String start = formatter.format(calendar.getTime());
					aoc.setStartdate(StringUtilsEX.ToShortDate(start));
					aoc.setEnddate(StringUtilsEX.ToShortDate(end));
				} else if (types == 3) {
					// 前年的订单
					Calendar calendar = Calendar.getInstance();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					calendar.add(Calendar.YEAR, -1);
					calendar.set(Calendar.MONTH, 0);
					calendar.set(Calendar.DATE, 1);
					calendar.set(Calendar.HOUR, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					String end = formatter.format(calendar.getTime());
					calendar.add(Calendar.YEAR, -2);
					calendar.set(Calendar.MONTH, 0);
					calendar.set(Calendar.DATE, 1);
					calendar.set(Calendar.HOUR, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					String start = formatter.format(calendar.getTime());
					aoc.setStartdate(StringUtilsEX.ToShortDate(start));
					aoc.setEnddate(StringUtilsEX.ToShortDate(end));
				}
			}

			PageBean orders = orderService.getMemberListOrderPage(page1, size11, aoc);
			OrderCountDto dto = orderService.selectCount(user.getUserId());
			Object[] objects = new Object[2];
			objects[0] = orders.getBeanList();
			objects[1] = dto;
			item.setData(objects);
			item.setMaxRow(orders.getTr());
			item.setPageIndex(orders.getPc());

		} catch (Exception ex) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误!");
			}
			LogHandle.error(LogType.pc, "获取会员中心订单列表失败{0}", ex, "/pc/order/getorderlist");
		}
		return item.toJson();
	}

	@RequestMapping(value = "/getcancelorder", produces = "text/html;charset=UTF-8")
	public String getcancelorder(String page, String size, String ch) {
		ReusltItem item = new ReusltItem();
		try {

			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("通道(ch)不正确！");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser();
			if (user == null || user.getCode() < 0) {
				item.setCode(-401);
				item.setDesc("用户未登陆");
				return item.toJson();
			}
			Integer page1 = StringUtilsEX.ToIntNull(page);
			Integer size11 = StringUtilsEX.ToIntNull(size);
			if (page1 == null || page1 <= 0) {
				page1 = 1;
			}
			if (size11 == null || size11 <= 0) {
				size11 = 10;
			}
			Pc_OrderCriteria aoc = new Pc_OrderCriteria();
			aoc.setUserid(user.getUserId());
			PageBean list = orderService.cancelorder(page1, size11, aoc);
			item.setData(list.getBeanList());
			item.setPageIndex(list.getPc());
			item.setMaxRow(list.getTr());

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("系统异常");
			LogHandle.error(LogType.pc, "获取取消订单异常!", e, "order/cancelorder");
		}
		return item.toJson();
	}

	/**
	 * 得到评价晒单列表
	 * 
	 * @param buyerid
	 * @return
	 */
	@RequestMapping(value = "/getcommentorder", produces = "text/html;charset=UTF-8")
	public String getcommentorder(String page, String size, String Type, String ch, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {

			Integer userid = SessionUtil.getSessionUserId(request);
			if (userid != null) {
				Integer page1 = StringUtilsEX.ToIntNull(page);
				Integer size11 = StringUtilsEX.ToIntNull(size);
				if (page1 == null || page1 <= 0) {
					page1 = 1;
				}
				if (size11 == null || size11 <= 0) {
					size11 = 10;
				}
				Pc_OrderCriteria aoc = new Pc_OrderCriteria();
				aoc.setUserid(userid);
				aoc.setSeachstr(Type);
				/*
				 * aoc.setOrderByClause("AddOrderDate"); aoc.setSort("desc");
				 */
				PageBean list = orderService.commentorder(page1, size11, aoc);
				// List<OrderMemberDto> beanList=new
				// ArrayList<OrderMemberDto>();
				if (StringUtilsEX.ToInt(Type) == 1) {
					List<OrderMemberDto> listf = (List<OrderMemberDto>) list.getBeanList();

					for (OrderMemberDto orderMemberDto : listf) {
						List<OrderdetailDto> detaillist = orderMemberDto.getChildren();
						orderMemberDto.setChildren(detaillist.stream().filter(a -> a.getDetailIsComment() == 1)
								.collect(Collectors.toList()));
					}

					// beanList=listf.stream().filter(a->a.getChildren().)
					list.setBeanList(listf);
				}

				if (StringUtilsEX.ToInt(Type) == 0) {
					List<OrderMemberDto> listf = (List<OrderMemberDto>) list.getBeanList();
					for (OrderMemberDto orderMemberDto : listf) {
						List<OrderdetailDto> subdetaillist = new ArrayList<OrderdetailDto>();
						List<OrderdetailDto> detaillist = orderMemberDto.getChildren();
						subdetaillist = detaillist.stream()
								.filter(a -> a.getDetailIsComment() == 0 || a.getDetailIsComment() == null)
								.collect(Collectors.toList());
						orderMemberDto.setChildren(subdetaillist);
					}
					list.setBeanList(listf);
					// beanList=listf.stream().filter(a->a.getChildren().)
				}

				item.setData(list.getBeanList());
				// item.setData(1);
				if (list.getTr() == null) {
					item.setMaxRow(0);
				} else {
					item.setMaxRow(list.getTr());
				}
				item.setPageIndex(list.getPc());
				item.setPageSize(list.getPs());
				item.setDesc("查询成功");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询订单异常：" + e.getMessage());
			}else {
				item.setDesc("查询订单异常");
			}			
			LogHandle.error(LogType.pc, "查询订单异常! 异常信息:{0}", e, "/pc/order/getcommentorder");
		}
		return item.toJson();
	}

	/**
	 * 
	 * 
	 * @param buyerid
	 * @return
	 */
	@RequestMapping(value = "/getcommentDetail", produces = "text/html;charset=UTF-8")
	public String getcommentDetail(String orderdetailID, String ch, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			Comment comment = commentService.selectByOrderId(StringUtilsEX.ToInt(orderdetailID));
			CommentDto dto = new CommentDto();
			if (comment != null) {
				dto.setId(comment.getId());
				dto.setOrderdetailid(comment.getOrderdetailid());
				dto.setStar(comment.getStar());
				dto.setContent(comment.getContent());
				dto.setShowname(comment.getShowname());
				dto.setStarDepict(comment.getStarDepict());
				dto.setStarService(comment.getStarService());
				dto.setStarSpeed(comment.getStarSpeed());
				List<Images> list = imagesService.queryImages(comment.getId(), ImageTypeEnum.买家评价图片.getValue());
				if (list != null && list.size() > 0) {
					dto.setShowImgList(list);
				}
			}
			Satisfaction satisfaction = satisfactionService.selectByDetailId(StringUtilsEX.ToInt(orderdetailID));
			if (satisfaction != null) {
				if (dto.getStarDepict() == 0) {
					dto.setStarDepict(satisfaction.getGooddescription());
				}
				if (dto.getStarService() == 0) {
					dto.setStarService(satisfaction.getSellerattitude());
				}
				if (dto.getStarSpeed() == 0) {
					dto.setStarSpeed(satisfaction.getLogisticsspeed());
				}
			}
			item.setData(dto);

		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询评论详情异常：" + e.getMessage());
			}else {
				item.setDesc("查询评论详情异常：" + e.getMessage());
			}
			LogHandle.error(LogType.pc, "查询订单评论详情异常! 异常信息:{0}", e, "/pc/order/getcommentDetail");
		}
		return JSON.toJSONString(item);
	}
	/**
	 * 订单催单
	 * 
	 * @param orderid
	 * @param token
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/cd")
	public BaseResult getOrdercdMessage(String orderid) {
		BaseResult item = new BaseResult();
		if (StringUtilsEX.IsNullOrWhiteSpace(orderid)) {// orderid为空
			item.setCode(-101);
			item.setDesc("催单失败,订单ID为空");
			return item;
		}
		//SessionUser user = SessionState.GetCurrentUser(token);
		try {
			Orders ors = orderService.queryById(StringUtilsEX.ToInt(orderid));//订单编号规则改变了不能用int类型的
			Users user=userservice.queryById(ors.getBuyerid());
			OrderRemind remind = new OrderRemind();// 催单表
			if (ors != null) {// 判断订单状态 如果订单状态为带受理则催单
				remind.setBuyerid(ors.getBuyerid());
				remind.setBuyername(user.getUsername());
				remind.setIsread(false);
				remind.setOrderid(ors.getId());
				remind.setSupplierid(ors.getShopid());
				remind.setOrdernum(ors.getCode());
				remind.setOrdertime(new Date());
				remind.setCtratetime(new Date());
				String[] array = new String[1];
				array[0] = ors.getBuyerid().toString();
				ors.setIsremind(true);
				orderService.update(ors);
				orderremindService.insert(remind);//每三小时催一次单
				item.setDesc("催单成功");
			/*	orderService.sendMessage(ors.getBuyerid(),ors.getShopid(), ors.getCode(), ors.getPaydate(), "", "", 3, 0,TemplateTagEnum.催单成功,
						MessagesTypeEnum.订单提醒);*/
				LogHandle.info(LogType.pc, MessageFormat.format(
						"催单成功！添加时间:{0},UserID:{1}", new Date(),
						user.getId()), "order/cd");
			}

		} catch (Exception ex) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.pc, "订单催单操作错误 ", ex, "/order/cd");
		}
		return item;
	}
}
