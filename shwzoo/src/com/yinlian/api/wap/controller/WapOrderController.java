package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ActivityUsePlatformEnum;
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
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_OrderCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Dispatching;
import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.po.Invoice;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.service.OrderUpdaterecordsService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/order")
public class WapOrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@Autowired
	private OrderdetailService orderdetailService;
	
    @Autowired
    private OperaterecordsService operaterecordsService ;
    
    @Autowired
    private  OrderUpdaterecordsService  orderUpdaterecordsService;

	/**
	 * 售后申请
	 * 
	 * @param ch
	 * @param token
	 * @param orderdetailid
	 *            订单详情id
	 * @param applytype
	 *            售后申请类型 1-订单退货退款 2-订单换货 3-订单维修 4-订单退款
	 * @param reason   原因
	 * @param images
	 *            eg: "/dsm/imge1,/dsm/imge2"
	 * @return
	 */
	@RequestMapping(value = "/applyafter", produces = "text/html;charset=UTF-8")
	public @ResponseBody String applyafter(String ch, String orderdetailid,
			String applytype, String reason, String images,
			HttpServletRequest request) {
		ch = "3";
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.ToInt(orderdetailid) < 0) {
				item.setCode(-101);
				item.setDesc("订单详情id(orderdetailid)参数错误：" + orderdetailid);
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if (StringUtils.isBlanck(reason)) {
				item.setCode(-102);
				item.setDesc("申请原因不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
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
			if (type != OrderApplyTypeEnum.订单退货退款.getValue()
					&& type != OrderApplyTypeEnum.订单换货.getValue()
					&& type != OrderApplyTypeEnum.订单维修.getValue()
					&& type != OrderApplyTypeEnum.订单退款.getValue()) {
				item.setCode(-103);
				item.setDesc("申请类型参数错误");
				return item.toJson();
			}
			// int userid=492;
			int result = orderStatusService.updateAfterServiceApply(
					StringUtilsEX.ToInt(orderdetailid), type, user.getUserId(),
					reason, images, item);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("售后申请成功");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"售后申请成功! 订单详情id{0},申请类型{1}", orderdetailid, applytype),
						"/order/applyafter");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "changproduct.html ", "/api/wap/order/applyafter", "售后申请 ");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"售后申请 异常信息:",
    								e, "/api/wap/order/applyafter");
						}
					}
				});
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						String ip = GetIpAddresss.getIpAddr();
    						Orderdetail detail = orderdetailService.queryByID(StringUtilsEX.ToInt(orderdetailid));		
    						int newstatus = 0;
    						OrderApplyTypeEnum applytype = OrderApplyTypeEnum.valueOf(type);
    						/*switch (applytype) {
    						case 订单退款:
    							newstatus=OrderDetailStatusEnum.申请退款.getValue();
    							break;
    						case 订单退货退款:
    							newstatus=OrderDetailStatusEnum.申请退货退款.getValue();
    							break;
    						case 订单换货:
    							newstatus=OrderDetailStatusEnum.申请换货.getValue();
    							break;
    						case 订单维修:
    							newstatus=OrderDetailStatusEnum.申请维修.getValue();
    							break;
    						default:
    							newstatus=detail.getStatus();
    						}*/
    						orderUpdaterecordsService.addOrderUpadateRecords("Status",String.valueOf(detail.getStatus()),String.valueOf(newstatus), StringUtilsEX.ToInt(orderdetailid), user.getUserId(), user.getLoginName(),ip);
    					}
    					catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"售后申请 异常信息:",
    								e, "/api/wap/order/applyafter");
						}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("售后申请失败");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"售后申请错误! 错误信息:订单详情id{0},申请类型{1}", orderdetailid,
						applytype), "/order/applyafter");
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.debug(LogType.wap,
					MessageFormat.format("售后申请异常! 异常信息:{0}", e.toString()),
					"order/applyafter");
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
	public @ResponseBody String delOrder(String id, String ch,
			HttpServletRequest request) {
		ch = "3";
		BaseResult item = new BaseResult();
		try {
			Integer _orderid = StringUtilsEX.ToInt(id);
			if (_orderid < 0) {
				item.setCode(-101);
				item.setDesc("订单id不能为空,id" + _orderid);
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null ||user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			} else {
				Integer userid = user.getUserId();
				// Integer userid=120;
				int result = orderService.delOrder(_orderid, userid);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("删除成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "OrderAll.html", "/api/wap/order/delorder", "删除订单");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"删除订单 异常信息:",
	    								e, "/api/wap/order/delorder");
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("删除失败");
					LogHandle
							.debug(LogType.wap, MessageFormat.format(
									"删除订单失败! 错误信息,{id},", _orderid),
									"order/delOrder");
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.debug(LogType.wap,
					MessageFormat.format("删除订单异常! 异常信息:{0}", e.toString()),
					"order/delOrder");
		}

		return item.toJson();
	}

	/**
	 * 得到订单各状态数量
	 * 
	 * @param token
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/getorderCount", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getorderCount(String ch,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser==null ||sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			Integer userid = sessionUser.getUserId();
			OrderCountDto dto = orderService.selectCount(userid);
			item.setData(dto);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.debug(LogType.wap,
							MessageFormat.format("得到订单各状态数量异常! 异常信息:{0}",
									e.toString()), "order/getorderCount");
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
	public @ResponseBody String cancel(String status, String ch, String reason,
			String orderid, HttpServletRequest request) {
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
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser==null ||sessionUser.getCode() != 0) {
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
				int result = orderStatusService.updateCancelOrder(_orderid,
						type, sessionUser.getUserId(), reason);
				// type, 492, reason);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("取消成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), sessionUser.getId(), sessionUser.getLoginName(), "OrderAll.html", "/api/wap/order/cancel", "取消订单");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"取消订单 异常信息:",
	    								e, "/api/wap/order/cancel");
							}
						}
					});
					//异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						String ip = GetIpAddresss.getIpAddr();
	    						SessionUser user=SessionState.GetCurrentUser();
	    						int newstatus=0;
	    						if (type == OrderStatusEnum.待付款.getValue()) {
	    							
	    							newstatus = OrderStatusEnum.未付款已取消.getValue();
	    							
	    						} else if (type == OrderStatusEnum.待发货.getValue()) {
	    							
	    							newstatus = OrderStatusEnum.已付款取消申请中.getValue();
	    						}
	    						orderUpdaterecordsService.addOrderUpadateRecords("Status",status,String.valueOf(newstatus), _orderid, user.getUserId(), user.getLoginName(),ip);
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"取消付款订单操作记录出错! 异常信息:",
	    								e, "/pc/order/cancel");
	    					}
	    					
	    				}
	    			});
				} else {
					item.setCode(-200);
					item.setDesc("取消失败");
					LogHandle.debug(LogType.wap, MessageFormat.format(
							"取消付款订单失败! 错误信息,{id},{status}", _orderid, status),
							"order/cancel");
				}
				// }
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.debug(LogType.wap,
					MessageFormat.format("取消付款订单异常! 异常信息:{0}", e.toString()),
					"order/cancel");
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
	public @ResponseBody String comfirmReceive(String orderid, String ch,
			HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {

			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}

			Integer _orderid = StringUtilsEX.ToInt(orderid);
			if (_orderid < 0) {
				item.setCode(-101);
				item.setDesc("订单id参数错误,id:" + orderid);
				return item.toJson();
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser==null ||sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用戶未登陆！");
				return item.toJson();
			}
			String userip = GetIpAddresss.getIp();
			int result = orderStatusService.updatecomfirmReceive(_orderid,
					sessionUser, userip);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("确认收货成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), sessionUser.getId(), sessionUser.getLoginName(), "OrderAll.html ", "/api/wap/order/comfirmreceive", "确认收货");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"确认收货 异常信息:",
    								e, "/api/wap/order/comfirmreceive");
						}
					}
				});
				

				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						SessionUser user=SessionState.GetCurrentUser();
    						
    						//orderUpdaterecordsService.addOrderUpadateRecords("Status",OrderStatusEnum.待收货.getValue().toString(),OrderStatusEnum.已确认收货.getValue().toString(), _orderid, user.getUserId(), user.getLoginName(),userip);
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"确认收货操作记录出错! 异常信息:",
    								e, "/pc/order/comfirmreceive");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("确认收货失败");
				LogHandle.debug(LogType.wap, MessageFormat.format(
						"确认收货失败! 参数信息:{orderid}", _orderid),
						"order/comfirmreceive");
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.debug(LogType.wap,
					MessageFormat.format("确认收货异常! 异常信息:{0}", e.toString()),
					"order/comfirmreceive");
		}
		return item.toJson();
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
		 * @param receiverID
		 *            收货地址ID
		 * @param dispType
		 *            配送方式 0快递 1自提
		 * @param dateType
		 *            配送日期类型 0任意 1工作日 2节假日
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
		@RequestMapping(value="/add", produces = "text/html;charset=UTF-8")
		public @ResponseBody String Create(String orderparams, String receiveaddrid,
				String disptype, String datetype, String timetype,
				String invoicetype, String invoicetitletype, String invoicetitle,
				String invoicecontent, String mobile, String email,
				String zyactivityid, String zyconponid, String beans, String scids,
				String ch) {
			BaseResult item = new BaseResult();
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
				checkOrderParams(item, orders, Invoices, disp, invoice,
						orderparams, receiveaddrid, disptype, datetype, timetype,
						invoicetype, invoicetitletype, invoicetitle,
						invoicecontent, mobile, email, zyactivityid, zyconponid,
						receiveID);
				if (item.getCode() < 0) {
					return item.toJson();
				}
				String[] groupCode = new String[2];
				if(beans.indexOf(".")>0)
				{
					beans=beans.split("\\.")[0];
				}
				Integer pbeans = StringUtilsEX.ToIntNull(beans);
				if (pbeans == null || pbeans < 100) {
//					item.setCode(-130);
//					item.setDesc("积分个数不够!");
//					return item.toJson();
					pbeans=0;
				}
				if (orderService.add(orders, disp, Invoices, receiveID[0],
						StringUtilsEX.ToInt(zyconponid),
						StringUtilsEX.ToInt(zyactivityid), user.getUserId(),
						user.getLoginName(), GetIpAddresss.getIpAddr(), pbeans,
						groupCode, scids,ActivityUsePlatformEnum.wap.getValue(),idcardinfo)) {
					item.setDesc("订单提交成功");
					item.setData(groupCode[0]);
					LogHandle.info(LogType.wap,
							MessageFormat.format("添加新订单成功！添加时间:{0},UserID:{1}", new Date(), user.getId()), "order/add");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "OrderAll.html ", "/api/wap/order/add", "操作说明（例：下单 ）");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"下单 异常信息:",
	    								e, "/api/wap/order/add");
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("订单提交失败：" + groupCode[0]);
					LogHandle.warn(LogType.wap, MessageFormat.format(
							"添加新订单失败！添加时间:{0},参数:{1},UserID:{2}", new Date(),
							orderparams, user.getId()), "order/add");
				}
			} catch (Exception ex) {
				item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(ex.toString());
				} else {
					item.setDesc("系统错误!");
				}
				LogHandle.error(LogType.wap, "添加新订单失败{0}", ex, "order/add");
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
		private void checkOrderParams(BaseResult item, List<AddNewOrderDto> orders,
				List<Invoice> Invoices, Dispatching disp, Invoice invoice,
				String orderParams, String receiveAddrID, String dispType,
				String dateType, String timeType, String invoiceType,
				String invoiceTitleType, String invoiceTitle,
				String invoiceContent, String mobile, String email,
				String zyActivityID, String zyConponID, Integer[] receiveID)
				throws Exception {
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
						Invoices.add(invoice);
					}
				}
				// #region receiveID & Dispatching & Invoice
				receiveID[0] = StringUtilsEX.ToInt(receiveAddrID);
				if (receiveID[0] <= 0) {
					item.setCode(-113);
					item.setDesc("收货地址信息错误");
					return;
				}
				disp.setType(StringUtilsEX.ToInt(dispType));
				disp.setDatetype(StringUtilsEX.ToInt(dateType));
				disp.setTimetype(StringUtilsEX.ToInt(timeType));
			} catch (Exception ex) {
				item.setCode(-100);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("参数格式错误，" + ex.toString());
				} else {
					item.setDesc("系统错误!");
				}
				return;
			}
		}
		
		/**
		 * 支付
		 * 
		 * @param ids
		 * @param status
		 * @param userid
		 * @param totalMoney
		 * @param userip
		 * @return
		 */
		@RequestMapping(value = "/pay", produces = "text/html;charset=UTF-8")
		public @ResponseBody String pay(String groupnum, String ch) {
			ReusltItem item = new ReusltItem();
			try {
				if (!StringUtilsEX.isChannelTypeExist(ch)) {
					item.setCode(-108);
					item.setDesc("登录通道参数错误");
					return item.toJson();
				}
				if (StringUtils.isBlanck(groupnum)) {
					item.setCode(-101);
					item.setDesc("订单组单号不能为空");
					return item.toJson();
				}
				SessionUser sessionUser = SessionState.GetCurrentUser();
				if (sessionUser==null || sessionUser.getCode() != 0) {
					item.setCode(-401);
					item.setDesc("用戶未登陆！");
					return item.toJson();
				}
				String userip = GetIpAddresss.getIp();
				int userid = sessionUser.getUserId();
				List<Orders> orders = orderService.getOrderByGroupCode(groupnum);
				if (orders!=null && orders.get(0).getStatus().equals(OrderStatusEnum.待付款.getValue())) {
					int result = orderStatusService.updatePayforBalanceCode(
							groupnum, userid, userip, item);
					if (result > 0) {
						item.setCode(0);
						item.setDesc("付款成功");
						ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), sessionUser.getId(), sessionUser.getLoginName(), "/orderPay.html ", "/api/wap/order/pay", "操作说明（例：余额支付 ）");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"余额支付 异常信息:",
		    								e, "/api/wap/order/pay");
								}
							}
						});
					} else {
						item.setCode(-200);
						item.setDesc("付款失败");
						LogHandle.debug(LogType.wap, MessageFormat.format(
								"付款失败! 参数信息订单组编号:{0}", groupnum), "order/pay");
					}
				} else {
					item.setCode(-103);
					item.setDesc("订单状态不是待付款，不能进行付款");
					return item.toJson();
				}

			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(e.toString());
				} else {
					item.setDesc("系统错误！");
				}
				item.setCode(-900);
				LogHandle.debug(LogType.wap,
						MessageFormat.format("支付订单异常! 异常信息:{0}", e.toString()),
						"order/pay");
			}

			return item.toJson();
		}
		
		
		
		/**
		 * 订单列表
		 * @param page 页
		 * @param status 订单状态
		 * @param size 页大小
		 * @param ch 管道
		 * @return
		 */
		@RequestMapping(value = "/getOrderall", produces = "text/html;charset=UTF-8")
		public @ResponseBody String getOrderall(Integer page,Integer status,Integer size,Integer ch,HttpServletRequest request) {
			ReusltItem item = new ReusltItem();
			try {
				if (page == null || page <= 0) {
					page = 1;
				}
				if (page == null || page <= 0) {
					page = 10;
				}
				SessionUser user = new SessionUser();
				String token = CookieUtils.getTokenFromCookie(request);
				user = SessionState.GetCurrentUser(token);
				Api_OrderCriteria aoc = new Api_OrderCriteria();
				aoc.setOrderByClause("AddOrderDate");
				aoc.setSort("desc");
				aoc.setUserid(user.getUserId());
				aoc.setStatus(status);
				PageBean bean = orderService.getUserListOrder(page, size,aoc);
				item.setData(bean.getBeanList());
				item.setMaxRow(bean.getTr());
				item.setPageIndex(bean.getPc());
				item.setMaxRow(bean.getPs());
				item.setPage(bean.getTp());
				item.setCode(0);
			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(e.toString());
				} else {
					item.setDesc("系统错误！");
				}
				item.setCode(-900);
				LogHandle.debug(LogType.wap,
								MessageFormat.format("获取订单列表异常! 异常信息:{0}",
										e.toString()), "order/getorderCount");
			}
			return item.toJson();
		}
		
}
