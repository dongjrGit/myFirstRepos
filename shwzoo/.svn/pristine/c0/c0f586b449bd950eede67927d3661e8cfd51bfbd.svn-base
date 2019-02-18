package com.yinlian.api.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yinlian.Enums.CommentTypeEnum;
import com.yinlian.Enums.ImageTypeEnum;
import com.yinlian.Enums.ShopTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_CommentDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_CommentCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ImagesMapper;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Satisfaction;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.SatisfactionService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/app/comment")
public class CommentController {
	/**
	 * 日志输出的类
	 */
	// private static final Logger logger =
	// LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private SatisfactionService satisfactionService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderdetailService orderdetailService;

	@Autowired
	private ImagesMapper imagesMapper;
	
	@Autowired
	private ShopService shopService;

	/**
	 * 添加满意度
	 * 
	 * @param orderdetaileid
	 * @param token
	 * @param sellerid
	 * @param gooddescription
	 * @param sellerattitude
	 * @param logisticsspeed
	 * @return
	 */
	@RequestMapping(value = "/addsatisfaction", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addSatisfaction(String orderdetaileid, String token, String shopid,
			String gooddescription, String sellerattitude, String logisticsspeed, String ch) {
		BaseResult item = new BaseResult();

		try {
			// int logistics = StringUtilsEX.ToInt(logisticsspeed);
			// int gooddesc = StringUtilsEX.ToInt(gooddescription);
			int selleratt = StringUtilsEX.ToInt(sellerattitude);
			/*
			 * if (logistics < 1 || logistics > 5) { item.setCode(-101);
			 * item.setDesc("物流服务(logisticsspeed)参数错误，请输入1~5中任意整数"); return
			 * item.toJson(); } if (gooddesc < 1 || gooddesc > 5) {
			 * item.setCode(-102);
			 * item.setDesc("描述相符(gooddescription)参数错误，请输入1~5中任意整数"); return
			 * item.toJson(); }
			 */
			if (selleratt < 1 || selleratt > 5) {
				item.setCode(-103);
				item.setDesc("服务态度(sellerattitude)参数错误，请输入1~5中任意整数");
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
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int buyerId = sessionUser.getUserId();
				Satisfaction satisfaction = new Satisfaction();
				satisfaction.setBuyerid(buyerId);
				satisfaction.setShopid(StringUtilsEX.ToInt(shopid));
				satisfaction.setOrderdetailid(StringUtilsEX.ToInt(orderdetaileid));
				// satisfaction.setLogisticsspeed(logistics);
				// satisfaction.setGooddescription(gooddesc);
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
				}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "添加满意度异常! 异常信息:{0}", e, "comment/addsatisfaction");
		}

		return item.toJson();
	}

	/**
	 * 添加评论
	 * 
	 * @param orderdetaileid
	 * @param token
	 * @param sellerid
	 * @param type
	 * @param spuid
	 * @param star
	 * @param title
	 * @param content
	 * @param showname
	 * @return
	 */
	@RequestMapping(value = "/addComment", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addComment(String orderdetaileid, String token, String shopid, String type,
			String spuid, String star, String title, String content, String showname, String ImgStrs, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(orderdetaileid)) {
				item.setCode(-101);
				item.setDesc("订单明细id不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(shopid)) {
				item.setCode(-103);
				item.setDesc("卖家id不能为空！");
				return item.toJson();
			}
//			if (StringUtilsEX.IsNullOrWhiteSpace(type)) {
//				item.setCode(-104);
//				item.setDesc("评论类型不能为空！");
//				return item.toJson();
//			}
//			int commType = StringUtilsEX.ToInt(type);
//			if (commType < 1 || commType > 3) {
//				item.setCode(-105);
//				item.setDesc("评论类型有误，请输入1~3之间的任意整数");
//			}
			if (StringUtilsEX.IsNullOrWhiteSpace(spuid)) {
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
			/*if (StringUtilsEX.IsNullOrWhiteSpace(title)) {
				item.setCode(-109);
				item.setDesc("评论标题不能为空！");
				return item.toJson();
			}*/
			if (StringUtilsEX.IsNullOrWhiteSpace(content)) {
				item.setCode(-110);
				item.setDesc("评论内容不能为空！");
				return item.toJson();
			}
//			if (StringUtilsEX.IsNullOrWhiteSpace(showname)) {
//				item.setCode(-111);
//				item.setDesc("是否匿名操作不能为空！");
//				return item.toJson();
//			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-115);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
//			int showName = StringUtilsEX.ToInt(showname);
//			if (showName != 0 && showName != 1) {
//				item.setCode(-112);
//				item.setDesc("匿名操作参数输入错误，请输入0或1");
//			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆!");
			} else {
				int buyerId = sessionUser.getUserId();
				Comment comment = new Comment();
				comment.setBuyerid(buyerId);
				comment.setOrderdetailid(StringUtilsEX.ToInt(orderdetaileid));
				comment.setShopid(StringUtilsEX.ToInt(shopid));
				comment.setType(1);
				comment.setSpuid(StringUtilsEX.ToInt(spuid));
				comment.setStar(StringUtilsEX.ToInt(star));
				comment.setTitle(title);
				comment.setContent(content);
				comment.setDate(new Date());
				comment.setReplycount(0);
				comment.setShowname(0);
				comment.setVaildflag(0);
				comment.setStarDepict(0);
				comment.setStarService(0);
				comment.setStarSpeed(0);
				Shop shop = shopService.queryById(StringUtilsEX.ToInt(shopid));
				if(shop != null){
					if(shop.getShoptype() == ShopTypeEnum.餐饮票.getValue()){
						comment.setStatus(0);
					}else{
						comment.setStatus(1);
					}
				}
				Orderdetail orderdetail = orderdetailService.queryByID(StringUtilsEX.ToInt(orderdetaileid));
				if (orderdetail != null) {
					if (orderdetail.getIscomment().equals("1")) {
						item.setCode(-118);
						item.setDesc("该订单已评论");
						return item.toJson();
					}
				}
				if (spuid != null) {
					int i = commentService.insertComm(comment, StringUtilsEX.ToInt(spuid));
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
						if (ImgStrs != null && !StringUtilsEX.IsNullOrWhiteSpace(ImgStrs)) {
							List<Images> imglist = new ArrayList<Images>();
							Images images = null;
							for (String img : ImgStrs.split(",")) {
								images = new Images();
								images.setCreatetime(new Date());
								images.setFkid(i);
								images.setStatus(0);
								images.setSort(0);
								images.setImgurl(img);
								images.setType(ImageTypeEnum.买家评价图片.getValue());
								imglist.add(images);
							}
							imagesMapper.insertList(imglist);
						}
						item.setCode(0);
						item.setDesc("添加评论成功");
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
						if (ImgStrs != null && !StringUtilsEX.IsNullOrWhiteSpace(ImgStrs)) {
							List<Images> imglist = new ArrayList<Images>();
							Images images = null;
							for (String img : ImgStrs.split(",")) {
								images = new Images();
								images.setCreatetime(new Date());
								images.setFkid(i);
								images.setStatus(0);
								images.setSort(0);
								images.setImgurl(img);
								images.setType(ImageTypeEnum.买家评价图片.getValue());
								imglist.add(images);
							}
							imagesMapper.insertList(imglist);
						}
						item.setCode(0);
						item.setDesc("添加评论成功");
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
			LogHandle.error(LogType.Api, "添加评论异常! 异常信息:{0}", e, "comment/addComment");
		}

		return item.toJson();
	}

	/**
	 * 添加团购评论
	 * 
	 * @param token
	 * @param kfid
	 * @param star
	 * @param title
	 * @param content
	 * @param showname
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/addGroupbuyComment", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addGroupbuyComment(String token, String kfid, String star, String title, String content,
			String showname, String ch) {
		BaseResult item = new BaseResult();
		try {

			if (StringUtilsEX.IsNullOrWhiteSpace(kfid)) {
				item.setCode(-106);
				item.setDesc("团购id(kfid)不能为空！");
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
			if (StringUtilsEX.IsNullOrWhiteSpace(title)) {
				item.setCode(-109);
				item.setDesc("评论标题(title)不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(content)) {
				item.setCode(-110);
				item.setDesc("评论内容(content)不能为空！");
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
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆!");
			} else {
				int buyerId = sessionUser.getUserId();
				Comment comment = new Comment();
				comment.setFkid(StringUtilsEX.ToInt(kfid));
				comment.setBuyerid(buyerId);
				comment.setType(CommentTypeEnum.团购评价.getValue());
				comment.setStar(StringUtilsEX.ToInt(star));
				comment.setTitle(title);
				comment.setContent(content);
				comment.setDate(new Date());
				comment.setReplycount(0);
				comment.setShowname(showName);
				comment.setVaildflag(0);
				comment.setStarDepict(0);
				comment.setStarService(0);
				comment.setStarSpeed(0);
				int i = commentService.insertComm(comment);
				if (i == 0) {
					item.setCode(-114);
					item.setDesc("添加评论失败");
				} else {

					item.setCode(0);
					item.setDesc("添加评论成功");
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加团购评论异常：" + e.getMessage());
			LogHandle.error(LogType.Api, "添加团购评论异常! 异常信息:{0}", e, "comment/addGroupbuyComment");
		}
		return item.toJson();
	}

	/**
	 * 整单评论
	 * 
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
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/addordercomment", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addOrderComment(String token, String ch, String shopid, String orderid,
			String commentstr, String gooddescription, String sellerattitude, String logisticsspeed) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆!");
			}
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
			if (StringUtilsEX.IsNullOrWhiteSpace(shopid)) {
				item.setCode(-106);
				item.setDesc("店铺id不能为空！");
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
			/* List<String> imgList=new ArrayList<String>(); */
			Api_CommentDto comment = null;
			for (HashMap object : list) {
				comment = new Api_CommentDto();
				int detailid = 0;
				// 订单明细ID
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("orderdetaileid").toString())
						|| StringUtilsEX.ToInt(object.get("orderdetaileid").toString()) <= 0) {
					item.setCode(-110);
					item.setDesc("订单明细ID参数错误！");
					return item.toJson();
				}
				detailid = StringUtilsEX.ToInt(object.get("orderdetaileid").toString());
				// 评论类型
				int commType = 0;
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("type").toString())
						|| StringUtilsEX.ToInt(object.get("type").toString()) <= 0) {
					item.setCode(-111);
					item.setDesc("评论类型参数错误！");
					return item.toJson();
				}
				commType = StringUtilsEX.ToInt(object.get("type").toString());
				if (commType < 1 || commType > 3) {
					item.setCode(-120);
					item.setDesc("评论类型有误，请输入1~3之间的任意整数");
				}

				// 产品ID
				int spuid = 0;
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("spuid").toString())
						|| StringUtilsEX.ToInt(object.get("spuid").toString()) < 0) {
					item.setCode(-112);
					item.setDesc("产品ID参数错误！");
					return item.toJson();
				}
				spuid = StringUtilsEX.ToInt(object.get("spuid").toString());
				// 评论星数（1-5星）
				int star = 0;
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("star").toString())
						|| StringUtilsEX.ToInt(object.get("star").toString()) <= 0) {
					item.setCode(-113);
					item.setDesc("评论星数参数错误！");
					return item.toJson();
				}
				star = StringUtilsEX.ToInt(object.get("star").toString());
				if (star < 1 || star > 5) {
					item.setCode(-122);
					item.setDesc("评论星数有误，请输入1~5之间的任意整数");
				}
				// 评论标题
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("title").toString())) {
					item.setCode(-114);
					item.setDesc("评论标题参数错误！");
					return item.toJson();
				}
				comment.setTitle(object.get("title").toString());
				// 评论内容
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("content").toString())) {
					item.setCode(-115);
					item.setDesc("评论内容参数错误！");
					return item.toJson();
				}
				comment.setContent(object.get("content").toString());

				// 评论图片
				String img = object.get("commimg").toString();
				comment.setImgs(img);

				// 匿名操作(0：不匿名 1：匿名)
				int showname = 0;
				if (StringUtilsEX.IsNullOrWhiteSpace(object.get("showname").toString())
						|| StringUtilsEX.ToInt(object.get("showname").toString()) < 0) {
					item.setCode(-116);
					item.setDesc("匿名操作参数错误！");
					return item.toJson();
				}
				showname = StringUtilsEX.ToInt(object.get("showname").toString());
				if (showname > 1) {
					item.setCode(-121);
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
				int i = commentService.insertOrderComm(listc, StringUtilsEX.ToInt(orderid), gooddesc, selleratt,
						logistics);
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
			LogHandle.error(LogType.Api, "添加订单评论异常! ", e, "/comment/addOrderComment");
		}
		return item.toJson();
	}

	/**
	 * 根据spuid获取评论列表
	 * @param ch
	 * @param spuid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/getCommentBySpuId", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getCommentBySpuId(String ch, String spuid, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(spuid)){
				item.setCode(-102);
				item.setDesc("商品id参数错误");
				return item.toJson();
			}
			Integer pageindex = StringUtilsEX.ToInt(page) <= 0 ? 1 : StringUtilsEX.ToInt(page);
			Integer pagesize = StringUtilsEX.ToInt(size) <= 0 ? 4 : StringUtilsEX.ToInt(size);
			Api_CommentCriteria criteria = new Api_CommentCriteria();
			criteria.setSpuid(StringUtilsEX.ToInt(spuid));
			criteria.setStatus(1);
			criteria.setOrderByClause("c.Date");
			criteria.setSort("desc");
			PageBean pageBean = commentService.getCommentBySpuId(criteria,pageindex,pagesize);
			item.setPage(pageBean.getTp());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setPageSize(pageBean.getPs());
			item.setData(pageBean.getBeanList());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "根据spuid获取评论列表异常! ", e, "/comment/getCommentBySpuId");
		}
		return item.toJson();
	}

}
