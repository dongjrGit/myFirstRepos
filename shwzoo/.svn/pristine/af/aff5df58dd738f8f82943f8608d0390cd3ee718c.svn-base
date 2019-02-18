package com.yinlian.api.wap.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_CommentDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Satisfaction;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.SatisfactionService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/comment")
public class WapCommentControoller {

	@Autowired
	private SatisfactionService satisfactionService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private CommentService commentService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderdetailService orderdetailService;


    @Autowired
    private OperaterecordsService operaterecordsService ;

	/**
	 * 添加满意度
	 * 
	 * @param orderdetaileid 订单详情ID
	 * @param token
	 * @param shopid     店铺ID
	 * @param gooddescription  描述相符星级数
	 * @param sellerattitude   服务态度星级数
	 * @param logisticsspeed  物流服务星级数
	 * @return
	 */
	@RequestMapping(value = "/addsatisfaction", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addSatisfaction(String orderdetaileid,
			String shopid, String gooddescription, String sellerattitude,
			String logisticsspeed, String ch, HttpServletRequest request) {
		BaseResult item = new BaseResult();

		try {
			int logistics = StringUtilsEX.ToInt(logisticsspeed);
			int gooddesc = StringUtilsEX.ToInt(gooddescription);
			int selleratt = StringUtilsEX.ToInt(sellerattitude);
			if (logistics < 1 || logistics > 5) {
				item.setCode(-101);
				item.setDesc("物流服务参数错误，请输入1~5中任意整数");
				return item.toJson();
			}
			if (gooddesc < 1 || gooddesc > 5) {
				item.setCode(-102);
				item.setDesc("描述相符参数错误，请输入1~5中任意整数");
				return item.toJson();
			}
			if (selleratt < 1 || selleratt > 5) {
				item.setCode(-103);
				item.setDesc("服务态度参数错误，请输入1~5中任意整数");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(orderdetaileid)) {
				item.setCode(-104);
				item.setDesc("订单明细id不能为空！");
				return item.toJson();
			}

			if (StringUtilsEX.IsNullOrWhiteSpace(shopid)) {
				item.setCode(-106);
				item.setDesc("卖家id不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-109);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			// String token="377de9a6-6694-45cc-bad9-14ba71cf8e06";
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int buyerId = sessionUser.getUserId();
				// int buyerId=120;
				Satisfaction satisfaction = new Satisfaction();
				satisfaction.setBuyerid(buyerId);
				satisfaction.setShopid(StringUtilsEX.ToInt(shopid));
				satisfaction.setOrderdetailid(StringUtilsEX
						.ToInt(orderdetaileid));
				satisfaction.setLogisticsspeed(logistics);
				satisfaction.setGooddescription(gooddesc);
				satisfaction.setSellerattitude(selleratt);
				satisfaction.setStaffattitude(0);
				satisfaction.setVaildflag(0);// 默认0 不删除 1 删除
				int i = satisfactionService.insertSatisfa(satisfaction);
				if (i == 0) {
					item.setCode(-108);
					item.setDesc("插入数据失败");
					
				} else {
					item.setCode(0);
					item.setDesc("插入数据成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user=SessionState.GetCurrentUser();
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "AddComment.html", "/api/wap/comment/addsatisfaction", "操作说明（例：添加满意度）");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"添加满意度 异常信息:",
	    								e, "/api/wap/comment/addsatisfaction");
							}
						}
					});
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"添加满意度异常! 异常信息:{0}", e,
					"comment/addsatisfaction");
		}

		return item.toJson();
	}

	/**
	 * 添加评论
	 * 
	 * @param orderdetaileid  订单ID
	 * @param token
	 * @param shopid 店铺ID
	 * @param type   评论类型
	 * @param skuid    产品id (订单信息存储的是skuid)
	 * @param star     商品评级星级数
	 * @param title    评价标题
	 * @param content   评价内容
	 * @param showname   是否匿名
	 * @return
	 */
	@RequestMapping(value = "/addComment", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addComment(String orderdetaileid,
			String shopid, String type, String skuid, String star,
			String title, String content, String showname,
			String gooddescription, String sellerattitude,
			String logisticsspeed, String ch, HttpServletRequest request) {
		ch = "3";
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(orderdetaileid)) {
				item.setCode(-101);
				item.setDesc("订单详细id不能为空！");
				return item.toJson();
			}

			if (StringUtilsEX.IsNullOrWhiteSpace(shopid)) {
				item.setCode(-103);
				item.setDesc("卖家id不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(type)) {
				item.setCode(-104);
				item.setDesc("评论类型不能为空！");
				return item.toJson();
			}
			int commType = StringUtilsEX.ToInt(type);
			if (commType < 1 || commType > 3) {
				item.setCode(-105);
				item.setDesc("评论类型有误，请输入1~3之间的任意整数");
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(skuid)) {
				item.setCode(-106);
				item.setDesc("产品id不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(star)) {
				item.setCode(-107);
				item.setDesc("评论星数不能为空！");
				return item.toJson();
			}
			int commStar = StringUtilsEX.ToInt(star);
			if (commStar < 1 || commStar > 5) {
				item.setCode(-108);
				item.setDesc("评论星级输入有误，请输入1-5之间的任意整数");
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(gooddescription)) {
				item.setCode(-125);
				item.setDesc("评论星数不能为空！");
				return item.toJson();
			}
			int gooddescript = StringUtilsEX.ToInt(gooddescription);
			if (gooddescript < 1 || gooddescript > 5) {
				item.setCode(-124);
				item.setDesc("评论星级输入有误，请输入1-5之间的任意整数");
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(sellerattitude)) {
				item.setCode(-123);
				item.setDesc("评论星数不能为空！");
				return item.toJson();
			}
			int sellerattitud = StringUtilsEX.ToInt(sellerattitude);
			if (sellerattitud < 1 || sellerattitud > 5) {
				item.setCode(-122);
				item.setDesc("评论星级输入有误，请输入1-5之间的任意整数");
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(logisticsspeed)) {
				item.setCode(-121);
				item.setDesc("评论星数不能为空！");
				return item.toJson();
			}
			int logistic = StringUtilsEX.ToInt(logisticsspeed);
			if (logistic < 1 || logistic > 5) {
				item.setCode(-120);
				item.setDesc("评论星级输入有误，请输入1-5之间的任意整数");
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(title)) {
				item.setCode(-109);
				item.setDesc("评论标题不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(content)) {
				item.setCode(-110);
				item.setDesc("评论内容不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(showname)) {
				item.setCode(-111);
				item.setDesc("是否匿名操作不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-115);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			int showName = StringUtilsEX.ToInt(showname);
			if (showName != 0 && showName != 1) {
				item.setCode(-112);
				item.setDesc("匿名操作参数输入错误，请输入0或1");
			}
			SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆!");
			} else {
				int buyerId = sessionUser.getUserId();
				// int buyerId =120;
				Comment comment = new Comment();
				comment.setBuyerid(buyerId);
				comment.setOrderdetailid(StringUtilsEX.ToInt(orderdetaileid));
				comment.setShopid(StringUtilsEX.ToInt(shopid));
				comment.setType(commType);
				int spuid=skuService.selectByID(StringUtilsEX.ToInt(skuid)).getSpuId();
				comment.setSpuid(spuid);
				comment.setStar(StringUtilsEX.ToInt(star));
				comment.setTitle(title);
				comment.setContent(content);
				comment.setDate(new Date());
				comment.setReplycount(0);
				comment.setShowname(showName);
				comment.setVaildflag(0);
				comment.setStarDepict(gooddescript);
				comment.setStarService(sellerattitud);
				comment.setStarSpeed(logistic);
				 Orderdetail orderdetail = orderdetailService
	    					.queryByID(StringUtilsEX.ToInt(orderdetaileid));
	    			if(orderdetail!=null){
	    				if(orderdetail.getIscomment().equals("1")){
	    					item.setCode(-118);
	    					item.setDesc("该订单已评论");
	    					return item.toJson();
	    				}
	    			}
				if (spuid >0) {
					int i = commentService.insertComm(comment,spuid);
					if (i == 0) {
						item.setCode(-114);
						item.setDesc("添加评论失败");
					} else {
						
						orderdetail.setIscomment(1);
						
						orderdetailService.update(orderdetail);
						
						int orderid = orderdetail.getOrderid();
						Orders order = orderService.queryByID(orderid);
						order.setIscomment(1);
						orderService.update(order);
						item.setCode(0);
						item.setDesc("添加评论成功");
						ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {
									SessionUser user=SessionState.GetCurrentUser();
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "addcomment.html", "/api/wap/comment/addComment", "操作说明（例：添加评论）");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"添加评论 异常信息:",
		    								e, "/api/wap/comment/addComment");
								}
							}
						});
					}
				} else {
					int i = commentService.insertComm(comment);
					if (i == 0) {
						item.setCode(-114);
						item.setDesc("添加评论失败");
					} else {
						
						orderdetail.setIscomment(1);
						orderdetailService.update(orderdetail);
						int orderid = orderdetail.getOrderid();
						Orders order = orderService.queryByID(orderid);
						order.setIscomment(1);
						orderService.update(order);
						item.setCode(0);
						item.setDesc("添加评论成功");
						ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {
									SessionUser user=SessionState.GetCurrentUser();
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "添加评论", "/api/wap/comment/addComment", "操作说明（例：添加评论）");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"添加评论 异常信息:",
		    								e, "/api/wap/comment/addComment");
								}
							}
						});
					}
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"添加评论异常! 异常信息:{0}", e,
					"comment/addComment");
		}

		return item.toJson();
	}
	

    /**
     * 整单评论
     * @param token
     * @param ch
     * @param shopid
     * @param orderid
     * @param commentstr
     * @param gooddescription
     * @param sellerattitude
     * @param logisticsspeed
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addordercomment", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addOrderComment( String ch,
			String shopid, String orderid, String commentstr,
			String gooddescription, String sellerattitude, String logisticsspeed) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆!");
			}
			int logistics = StringUtilsEX.ToInt(logisticsspeed);
			int gooddesc = StringUtilsEX.ToInt(gooddescription);
			int selleratt = StringUtilsEX.ToInt(sellerattitude);
			if (logistics < 1 || logistics > 5) {
				item.setCode(-101);
				item.setDesc("物流服务(logisticsspeed)参数错误，请输入1~5中任意整数");
				return item.toJson();
			}
			if (gooddesc < 1 || gooddesc > 5) {
				item.setCode(-102);
				item.setDesc("描述相符(gooddescription)参数错误，请输入1~5中任意整数");
				return item.toJson();
			}
			if (selleratt < 1 || selleratt > 5) {
				item.setCode(-103);
				item.setDesc("服务态度(sellerattitude)参数错误，请输入1~5中任意整数");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(shopid)) {
				item.setCode(-106);
				item.setDesc("卖家id不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(commentstr)) {
				item.setCode(-107);
				item.setDesc("评论信息为空！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(orderid) <= 0) {
				item.setCode(-109);
				item.setDesc("订单ID参数错误！");
				return item.toJson();
			}
			List<HashMap> list = JSON.parseArray(commentstr, HashMap.class);
			List<Api_CommentDto> listc = new ArrayList<Api_CommentDto>();
			/*List<String>  imgList=new ArrayList<String>();*/
			Api_CommentDto comment = null;
			for (HashMap object : list) {
				comment = new Api_CommentDto();
				int detailid = 0;
				// 订单明细ID
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get(
						"orderdetaileid").toString())
						|| StringUtilsEX.ToInt(object.get("orderdetaileid")
								.toString()) <= 0) {
					item.setCode(-110);
					item.setDesc("订单明细ID参数错误！");
					return item.toJson();
				}
				detailid = StringUtilsEX.ToInt(object.get("orderdetaileid")
						.toString());
				// 评论类型
				int commType = 0;
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("type")
						.toString())
						|| StringUtilsEX.ToInt(object.get("type").toString()) <= 0) {
					item.setCode(-111);
					item.setDesc("评论类型参数错误！");
					return item.toJson();
				}
				commType = StringUtilsEX.ToInt(object.get("type").toString());
				if (commType < 1 || commType > 5) {
					item.setCode(-111);
					item.setDesc("评论类型有误，请输入1~5之间的任意整数");
				}

				// 产品ID
				int spuid = 0;
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("spuid")
						.toString())
						|| StringUtilsEX.ToInt(object.get("spuid").toString()) < 0) {
					item.setCode(-112);
					item.setDesc("产品ID参数错误！");
					return item.toJson();
				}
				spuid = StringUtilsEX.ToInt(object.get("spuid").toString());
				// 评论星数（1-5星）
				int star = 0;
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("star")
						.toString())
						|| StringUtilsEX.ToInt(object.get("star").toString()) <= 0) {
					item.setCode(-113);
					item.setDesc("评论星数参数错误！");
					return item.toJson();
				}
				star = StringUtilsEX.ToInt(object.get("star").toString());
				if (star < 1 || star > 5) {
					item.setCode(-113);
					item.setDesc("评论星数有误，请输入1~5之间的任意整数");
				}
				// 评论标题
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("title")
						.toString())) {
					item.setCode(-114);
					item.setDesc("评论标题参数错误！");
					//return item.toJson();
				}
				comment.setTitle(object.get("title").toString());
				// 评论内容
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("content")
						.toString())) {
					item.setCode(-115);
					item.setDesc("评论内容参数错误！");
					return item.toJson();
				}
				comment.setContent(object.get("content").toString());
				
				//评论图片
				String img = object.get("commimg")
						.toString();
				comment.setImgs(img);
				
				// 匿名操作(0：不匿名 1：匿名)
				int showname = 0;
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("showname")
						.toString())
						|| StringUtilsEX.ToInt(object.get("showname")
								.toString()) < 0) {
					item.setCode(-116);
					item.setDesc("匿名操作参数错误！");
					return item.toJson();
				}
				showname = StringUtilsEX.ToInt(object.get("showname")
						.toString());
				if (showname > 1) {
					item.setCode(-116);
					item.setDesc("匿名操作参数错误！请输入0或者1");
					return item.toJson();
				}
				
				comment.setOrderdetailid(detailid);
				comment.setBuyerid(user.getUserId());
				comment.setShopid(StringUtilsEX.ToInt(shopid));
				comment.setType(commType);
				comment.setShowname(showname);
				comment.setSpuid(spuid);
				comment.setStar(star);
				comment.setDate(new Date());
				comment.setReplycount(0);
				comment.setVaildflag(0);
				comment.setStarDepict(0);
				comment.setStarService(0);
				comment.setStarSpeed(0);
				
				listc.add(comment);
			}
			if (listc.size() > 0) {
				int i = commentService.insertOrderComm(listc,
						StringUtilsEX.ToInt(orderid), gooddesc,
						selleratt,logistics);
				if (i == 0) {
					item.setCode(-200);
					item.setDesc("添加评论失败");
				} else {
					item.setCode(0);
					item.setDesc("添加评论成功");
				}
			}
			item.setData(listc);

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "添加订单评论异常! ", e,
					"comment/addOrderComment");
		}
		return item.toJson();
	}
    
}
