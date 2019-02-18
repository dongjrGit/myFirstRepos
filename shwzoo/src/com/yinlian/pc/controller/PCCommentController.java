package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yinlian.Enums.ImageTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.Api_CommentDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.mapper.ImagesMapper;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.po.Orderdetail;
import com.yinlian.wssc.web.po.Orders;
import com.yinlian.wssc.web.po.Satisfaction;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.CommentshowimgService;
import com.yinlian.wssc.web.service.GroupBuyService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.OrderdetailService;
import com.yinlian.wssc.web.service.SatisfactionService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;


@RestController
@RequestMapping("/pc/comment")
public class PCCommentController {

	
	@Autowired
	private SatisfactionService satisfactionService;

	@Autowired
	private SpuService spuService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderdetailService orderdetailService;

	@Autowired
	private GroupBuyService groupbuyservice;
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private CommentshowimgService commentshowService;
	@Autowired
	private OperaterecordsService operaterecordsService;
	@Autowired
	private SkuService    skuService;
	@Autowired
	private  ImagesMapper   imagesMapper;
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
	public String addSatisfaction(String orderdetaileid,
			String shopid, String gooddescription, String sellerattitude,
			String logisticsspeed, HttpServletRequest request) {
		BaseResult item = new BaseResult();

		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<=0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
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
						
				int buyerId = user.getUserId();
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
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "CommentList.html", "/pc/comment/addsatisfaction", "添加满意度");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"添加满意度 异常信息:",
	    								e, "/pc/comment/addsatisfaction");
							}
						}
					});
					return item.toJson();
				} else {
					item.setCode(0);
					item.setDesc("插入数据成功");
					return item.toJson();
				}
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc(e.getMessage());
			}else {
				item.setDesc("添加满意度异常");
			}			
			LogHandle.error(LogType.pc,
					MessageFormat.format("添加满意度异常! 异常信息:{0}", e.toString()),
					"comment/addsatisfaction");
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
	public String addComment(String OrderDetailID,
			String SellerID, String SKUID, String Star, String Content,
			String ShowName, String ImgStrs, String StarDepict,
			String StarService, String StarSpeed,
			String ch, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<=0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(OrderDetailID)) {
				item.setCode(-101);
				item.setDesc("订单明显id不能为空！");
				return item.toJson();
			}
			
			if (StringUtilsEX.IsNullOrWhiteSpace(SKUID)) {
				item.setCode(-106);
				item.setDesc("产品id不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(Star)) {
				item.setCode(-107);
				item.setDesc("评论星数不能为空！");
				return item.toJson();
			}
			
			if (StringUtilsEX.IsNullOrWhiteSpace(StarDepict)) {
				item.setCode(-125);
				item.setDesc("评论星数不能为空！");
				return item.toJson();
			}
			
			if (StringUtilsEX.IsNullOrWhiteSpace(StarService)) {
				item.setCode(-123);
				item.setDesc("评论星数不能为空！");
				return item.toJson();
			}
			
			if (StringUtilsEX.IsNullOrWhiteSpace(StarSpeed)) {
				item.setCode(-121);
				item.setDesc("评论星数不能为空！");
				return item.toJson();
			}
			
			if (StringUtilsEX.IsNullOrWhiteSpace(Content)) {
				item.setCode(-110);
				item.setDesc("评论内容不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(ShowName)) {
				item.setCode(-111);
				item.setDesc("是否匿名操作不能为空！");
				return item.toJson();
			}
		
		    Integer userid=user.getUserId();
		    Comment comment = new Comment();
		    Sku sku=skuService.selectByID(StringUtilsEX.ToInt(SKUID));
			Integer spuid=sku.getSpuId();
			Orderdetail orderdetail = orderdetailService
					.queryByID(StringUtilsEX.ToInt(OrderDetailID));
			if(orderdetail!=null){
				comment.setShopid(orderdetail.getShopid());
			}
			
			comment.setBuyerid(userid);
			comment.setOrderdetailid(StringUtilsEX.ToInt(OrderDetailID));
			
			comment.setType(1);
			comment.setSpuid(sku.getSpuId());
			
			comment.setStar(StringUtilsEX.ToInt(Star));
		
			comment.setContent(Content);
			comment.setDate(new Date());
			comment.setReplycount(0);
			comment.setShowname(StringUtilsEX.ToInt(ShowName));
			comment.setVaildflag(0);
			comment.setStarDepict(StringUtilsEX.ToInt(StarDepict));
			comment.setStarService(StringUtilsEX.ToInt(StarService));
			comment.setStarSpeed(StringUtilsEX.ToInt(StarSpeed));
			
			if(orderdetail!=null){
				if(orderdetail.getIscomment().equals("1")){
					item.setCode(-118);
					item.setDesc("该订单已评论");
					return item.toJson();
				}
			}
			if (spuid != null) {
				int i = commentService.insertComm(comment,
						spuid);
				if (i <= 0) {
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
					//if (image != null && !StringUtilsEX.IsNullOrWhiteSpace(image)) {
					//	Commentshowimg commentshow = new Commentshowimg();
						//for (String n : image.split(",")) {
						//	commentshow.setCommentid(comment.getId());
							//commentshow.setImgurl(n);
						//	commentshow.setVaildflag(0);
							
						//	commentshowService.insert(commentshow);
						//}
						item.setCode(0);
						item.setDesc("添加评论成功");
						ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {									
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "CommentList.html", "/pc/comment/addComment", "添加评论成功");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"添加评论成功 异常信息:",
		    								e, "/pc/comment/addComment");
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
		
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("添加评论异常：" + e.getMessage());
			}else {
				item.setDesc("添加评论异常");
			}			
			LogHandle.error(LogType.pc,
					MessageFormat.format("添加评论异常! 异常信息:{0}", e.toString()),
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
    @RequestMapping(value = "/addordercomment", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addOrderComment(String ch,
			String shopid, String orderid, String commentstr,
			String gooddescription, String sellerattitude, String logisticsspeed) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser user =SessionState.GetCurrentUser();
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
			List<String>  imgList=new ArrayList<String>();
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
				if (commType < 1 || commType > 3) {
					item.setCode(-111);
					item.setDesc("评论类型有误，请输入1~3之间的任意整数");
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
				if (star < 1 || star >5) {
					item.setCode(-113);
					item.setDesc("评论星数有误，请输入1~5之间的任意整数");
					return item.toJson();
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
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("添加订单评论异常：" + e.getMessage());
			}else {
				item.setDesc("添加订单评论异常");
			}			
			LogHandle.error(LogType.Api, "添加订单评论异常! ", e,
					"comment/addOrderComment");
		}
		return item.toJson();
	}
    
}
