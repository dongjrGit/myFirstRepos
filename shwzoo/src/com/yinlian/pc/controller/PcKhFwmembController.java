/*
 * @(#) PcActivityViewController.java 2016年7月12日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.pc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_OrderCriteria;
import com.yinlian.wssc.search.Pc_GoodConsultCriteria;
import com.yinlian.wssc.search.Pc_OrderCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.service.AfterService;
import com.yinlian.wssc.web.service.ApplyforcancelorderService;
import com.yinlian.wssc.web.service.FeedBackService;
import com.yinlian.wssc.web.service.GoodConsultService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderStatusService;
import com.yinlian.wssc.web.service.OrderUpdaterecordsService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.util.CriteriaMemberFeedBack;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/khFwmemb")
public class PcKhFwmembController {

	@Autowired
	private  FeedBackService     feedBackService;

	@Autowired
	private   OrderService    orderService;
	
	@Autowired
	private ApplyforcancelorderService applyforcancelorderService;

	@Autowired
	private SkuService     skuService;
	
	@Autowired
	private AfterService     afterService;
	
	@Autowired
	private GoodConsultService    goodConsultService;
	
	@Autowired
    private  OrderStatusService    orderStatusService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;
	
	@Autowired
	private OrderdetailService  orderdetailService;
	
	@Autowired
	private  OrderUpdaterecordsService   orderUpdaterecordsService;
	
	/**
	 * 查询当前顾客的全部订单
	 * 
	 * @param buyerid
	 * @return
	 */
	@RequestMapping(value = "/getorder", produces = "text/html;charset=UTF-8")
	public String  getorder(String SearchStr, String page, String size,
			 String ch,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			
			Integer  userid=SessionUtil.getSessionUserId(request);
			if(userid!=null){
				Integer page1 = StringUtilsEX.ToIntNull(page);
				Integer size11 = StringUtilsEX.ToIntNull(size);
				if (page1 == null || page1 <= 0) {
					page1 = 1;
				}
				if (size11 == null || size11 <= 0) {
					size11 = 3;
				}
				item.setCode(0);
				Pc_OrderCriteria aoc = new Pc_OrderCriteria();
				aoc.setOrderByClause("AddOrderDate");
				aoc.setSort("desc");
				aoc.setUserid(userid);
				aoc.setSeachstr(SearchStr);
				PageBean list = orderService.getPcUserListOrder(page1, size11,
						aoc);
			
				item.setData(list.getBeanList());
				//item.setData(1);
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
			item.setDesc("查询订单异常：" + e.getMessage());
			LogHandle.error(LogType.pc, "查询订单异常! 异常信息:{0}", e,
					"khFwmemb/getorder");
		}
		System.out.println(JSON.toJSONString(item));
		return JSON.toJSONString(item);
	}

	/***
	 * 根据skuid获取spuid
	 * @param skuid
	 * @return
	 */
	@RequestMapping(value = "/getspuid", produces = "text/html;charset=UTF-8")
	public String  getspuid(String skuid) {
		ReusltItem item = new ReusltItem();
		try {
			if(StringUtilsEX.ToInt(skuid)>0){
				Sku sku=skuService.selectByID(StringUtilsEX.ToInt(skuid));
				if(sku!=null){
					Integer spuid=sku.getSpuId();
					item.setCode(0);
					item.setData(spuid);
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("查询订单异常：" + e.getMessage());
			LogHandle.error(LogType.pc, "查询订单异常! 异常信息:{0}", e,
					"khFwmemb/getspuid");
		}
		return item.toJson();
	}
	
	
	@RequestMapping(value = "/getAfterservice", produces = "text/html;charset=UTF-8")
	public String  getAfterservice(String status, String page, String size,
			 String ch,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			
			Integer  userid=SessionUtil.getSessionUserId(request);
			if(userid!=null){
				Integer page1 = StringUtilsEX.ToIntNull(page);
				Integer size11 = StringUtilsEX.ToIntNull(size);
				if (page1 == null || page1 <= 0) {
					page1 = 1;
				}
				if (size11 == null || size11 <= 0) {
					size11 = 3;
				}
				item.setCode(0);
				Api_OrderCriteria criteria = new Api_OrderCriteria();
				criteria.setOrderByClause("createtime");
				criteria.setSort("desc");
				criteria.setUserid(userid);
				if(status==null||status.equals("")){
					criteria.setStatus(-2);
				}else{
					criteria.setStatus(StringUtilsEX.ToInt(status));
				}
				
				PageBean list = afterService.AfterServiceListByPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			
				item.setData(list.getBeanList());
				//item.setData(1);
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
			item.setDesc("查询订单异常：" + e.getMessage());
			LogHandle.error(LogType.pc, "查询订单异常! 异常信息:{0}", e,
					"khFwmemb/getAfterservice");
		}
		return item.toJson();
	}
	
	/**
	 * 查询咨询列表
	 * @param page
	 * @param size
	 * @param ch
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getGoodConsult", produces = "text/html;charset=UTF-8")
	public String  getGoodConsult( String page, String size,
			 String ch,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			
			Integer  userid=SessionUtil.getSessionUserId(request);
			if(userid!=null){
				Integer page1 = StringUtilsEX.ToIntNull(page);
				Integer size11 = StringUtilsEX.ToIntNull(size);
				if (page1 == null || page1 <= 0) {
					page1 = 1;
				}
				if (size11 == null || size11 <= 0) {
					size11 = 3;
				}
				item.setCode(0);
				Pc_GoodConsultCriteria criteria = new Pc_GoodConsultCriteria();
				criteria.setOrderByClause("createtime");
				criteria.setSort("desc");
				criteria.setUserid(userid);
				
				PageBean list = goodConsultService.queryUserGoodConsultByCriteria(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			
				item.setData(list.getBeanList());
				//item.setData(1);
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
			item.setDesc("查询咨询列表异常：" + e.getMessage());
			LogHandle.error(LogType.pc, "查询咨询列表异常! 异常信息:{0}", e,
					"khFwmemb/getGoodConsult");
		}
		return item.toJson();
	}

	
	/**
	 * 买家售后发货 （中绿项目售后流程调整，产品如需要请恢复注释）
	 * @param ch
	 * @param orderdetailid
	 * @param status
	 * @param lname
	 * @param lcode
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value = "/sendpro", produces = "text/html;charset=UTF-8")
	public String sendpro(String ch,String orderdetailid,
			String status, String lname, String lcode,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.ToInt(orderdetailid) < 0) {
				item.setCode(-101);
				item.setDesc("订单详情id(orderdetailid)参数错误");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser user = SessionUtil.getSessionUser(request);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-102);
				item.setDesc("订单状态参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.换货买家待发货
					.getValue()
					&& StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.维修买家待发货
							.getValue()
					&& StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.退货买家待发货
							.getValue()) {
				item.setCode(-102);
				item.setDesc("订单状态参数错误,不能发货");
				return item.toJson();
			}
			int applytype = 0;
			if (StringUtilsEX.ToInt(status) == OrderDetailStatusEnum.换货买家待发货
					.getValue()) {
				applytype = OrderApplyTypeEnum.订单换货.getValue();
			} else if (StringUtilsEX.ToInt(status) == OrderDetailStatusEnum.维修买家待发货
					.getValue()) {
				applytype = OrderApplyTypeEnum.订单维修.getValue();
			} else if (StringUtilsEX.ToInt(status) == OrderDetailStatusEnum.退货买家待发货
					.getValue()) {
				applytype = OrderApplyTypeEnum.订单退货退款.getValue();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(lcode)) {
				item.setCode(-103);
				item.setDesc("物流单号不能为空");
				return item.toJson();

			}
			if (StringUtilsEX.IsNullOrWhiteSpace(lname)) {
				item.setCode(-104);
				item.setDesc("物流公司名称不能为空");
				return item.toJson();
			}
			int result = orderStatusService.updateSendProForOrderDetail(
					StringUtilsEX.ToInt(orderdetailid), applytype,
					AfterServiceSender.买家.getValue(), lname.trim(),
					lcode.trim());
			if (result > 0) {
				item.setCode(0);
				item.setDesc("买家发货成功");
				LogHandle.info(LogType.pc, MessageFormat.format(
						"买家发货成功!订单详情id{0},物流编号{1},物流公司名称{2}", orderdetailid,
						lcode, lname), "khFwmemb/sendpro");
				 ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user=SessionState.GetCurrentUser();
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "AfterServiceDetail.html", "/pc/coupon/sendpro", "买家发货");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"买家发货 异常信息:",
	    								e, "/pc/coupon/sendpro");
							}
						}
					});
					
					//异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						String userip = GetIpAddresss.getIp();
	    						SessionUser user=SessionState.GetCurrentUser();
	    						Orderdetail detail = orderdetailService.queryByID(StringUtilsEX.ToInt(orderdetailid));
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
	    							newstatus=detail.getStatus();
	    						}		
	    						orderUpdaterecordsService.addOrderUpadateRecords("Status",String.valueOf(detail.getStatus()),String.valueOf(newstatus), StringUtilsEX.ToInt(orderdetailid), user.getUserId(), user.getLoginName(),userip);
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"买家发货操作记录出错! 异常信息:",
	    								e, "order/sendpro");
	    					}
	    					
	    				}
	    			});
			} else {
				item.setCode(-200);
				item.setDesc("买家发货失败");
				LogHandle.debug(LogType.pc, MessageFormat.format(
						"买家发货错误! 错误信息:订单详情id{0},物流编号{1},物流公司名称{2}",
						orderdetailid, lcode, lname), "khFwmemb/sendpro");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("买家发货异常：" + e.getMessage());
			LogHandle.debug(LogType.pc,
					MessageFormat.format("买家发货异常! 异常信息:{0}", e.toString()),
					"khFwmemb/sendpro");
		}
		return item.toJson();
	}*/
	
	
	/**
	 * 买家售后确认收货（中绿项目售后流程调整，产品如需要时请恢复注释）
	 * @param ch
	 * @param orderdetailid
	 * @param status
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value = "/receive", produces = "text/html;charset=UTF-8")
	public String receive(String ch,String orderdetailid,
			String status,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.ToInt(orderdetailid) <= 0) {
				item.setCode(-101);
				item.setDesc("订单详情id(orderdetailid)参数错误");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser user = SessionUtil.getSessionUser(request);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(status) <= 0) {
				item.setCode(-102);
				item.setDesc("订单详情状态参数错误");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.换货买家待收货
					.getValue()
					&& StringUtilsEX.ToInt(status) != OrderDetailStatusEnum.维修买家待收货
							.getValue()) {
				item.setCode(-103);
				item.setDesc("订单详情状态参数错误");
				return item.toJson();
			}
			int applytype = 0;
			if (StringUtilsEX.ToInt(status) == OrderDetailStatusEnum.换货买家待收货
					.getValue())
				applytype = OrderApplyTypeEnum.订单换货.getValue();
			if (StringUtilsEX.ToInt(status) == OrderDetailStatusEnum.维修买家待收货
					.getValue())
				applytype = OrderApplyTypeEnum.订单维修.getValue();
			
			int temp=applytype;
			
			if (orderStatusService.updateAfterServiceReceive(
					StringUtilsEX.ToInt(orderdetailid), applytype,
					AfterServiceSender.买家.getValue(), user,
					GetIpAddresss.getIp()) > 0) {
				item.setCode(0);
				item.setDesc("买家收货成功");
				LogHandle.info(LogType.Api, MessageFormat.format(
						"买家收货成功! 订单详情id{0}", orderdetailid), "order/receive");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "AfterServiceDetail.html", "/pc/coupon/receive", "操作说明（例：买家收货）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"买家收货 异常信息:",
    								e, "/pc/coupon/receive");
						}
					}
				});
				
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						String userip = GetIpAddresss.getIp();
    						SessionUser user=SessionState.GetCurrentUser();
    						Orderdetail detail = orderdetailService.queryByID(StringUtilsEX.ToInt(orderdetailid));
    						int newstatus=0;
    						OrderApplyTypeEnum type = OrderApplyTypeEnum.valueOf(temp);
    						switch (type) {
    						case 订单换货:	
    							newstatus=OrderDetailStatusEnum.换货成功.getValue();
    							break;
    							
    						case 订单维修:	
    							newstatus=OrderDetailStatusEnum.维修成功.getValue();
    							break;
    							
    						default:
    							newstatus=detail.getStatus();
    							break;
    						}
    						
    						
    						orderUpdaterecordsService.addOrderUpadateRecords("Status",String.valueOf(detail.getStatus()),String.valueOf(newstatus), StringUtilsEX.ToInt(orderdetailid), user.getUserId(), user.getLoginName(),userip);
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"买家发货操作记录出错! 异常信息:",
    								e, "order/sendpro");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("买家收货成功");
				LogHandle.debug(LogType.Api, MessageFormat.format(
						"买家收货错误! 错误信息:订单详情id{0}", orderdetailid),
						"khFwmemb/receive");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "AfterServiceDetail.html", "/pc/coupon/receive", "操作说明（例：买家收货）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"买家收货 异常信息:",
    								e, "/pc/coupon/receive");
						}
					}
				});
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("买家收货异常：" + e.getMessage());
			LogHandle.debug(LogType.pc,
					MessageFormat.format("买家收货异常! 异常信息:{0}", e.toString()),
					"khFwmemb/receive");
		}

		return item.toJson();
	}*/
	
	/***
	 * 查询会员反馈分页列表
	 * @param page
	 * @param size
	 * @param req
	 * @return
	 */
	@RequestMapping("/queryMemberFeedBack")
	public ReusltItem queryMemberComment(String page,String size,HttpServletRequest req){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0) {
				item.setCode(-101);
				item.setDesc("当前页参数错误pageindex"+page);
			}
			if (StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("每页记录数参数错误pagesize"+size);
			}
			Integer  userid=SessionUtil.getSessionUserId(req);
			CriteriaMemberFeedBack criteria = new CriteriaMemberFeedBack();
			criteria.setUserid(userid);
			criteria.setOrderByClause("creattime");
			criteria.setSort("desc");
			PageBean pageBean = feedBackService.queryMemberFeedBackByCriteria(criteria,
					StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("根据分页查询会员反馈列表的信息出错：" + e.getMessage());
			LogHandle.error(LogType.MemberCenterManage, "根据分页查询会员反馈列表的信息出错! 异常信息:",
					e, "/platform/memberfeedback/queryMemberComment");
		}
		return item;
	}
}
