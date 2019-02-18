/*
 * @(#) WapQiangController.java 2016年7月6日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jpush.api.report.UsersResult.User;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.CommentTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wap.dto.ShevelSpuDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Api_OrderCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.ShopShevelSpuDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.showarticle;
import com.yinlian.wssc.web.po.showuser;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.showarticleService;
import com.yinlian.wssc.web.service.showuserService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.CriteriaShopSheive;
import com.yinlian.wssc.web.util.CriteriaSku;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

import data.ParseUtil;

/**
 * 
 * @Description 
 * 
 * @author kh.wang
 * @version 1.0
 * @since 2016年7月6日
 */
@Controller
@RequestMapping("/api/wap/showfan")
public class WapShowFanontroller {
	
	@Autowired
	private SkuService skuService;
	@Autowired 
	private showarticleService  showarticleService;
	@Autowired
	private showuserService   showuserService;
	@Autowired
	private  UserService       userservice;
	@Autowired
	private  CommentService     commentService;
	@Autowired
	private  SpuService     spuService;
	@Autowired
	private  ShopService     shopService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;

	
	@RequestMapping("/skuList")
	public String index(String page, String size,Model model,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionUtil.getSessionUser(request);
			if(user.getCode()!= 0){
				return "/template/wap/userinfo/login";
			}else{
				CriteriaSku aoc = new CriteriaSku();
				aoc.setUserid(user.getUserId());
				PageBean pBean = skuService.getUserListOrderSpu(StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size),
						aoc);
				item.setData(pBean.getBeanList());
				item.setMaxRow(pBean.getTr());
				item.setPageIndex(pBean.getPc());
				item.setMaxRow(pBean.getPs());
				item.setPage(pBean.getTp());
				item.setCode(0);
				model.addAttribute("pageData",item);
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/spuList");
		}
		return "/template/wap/discover/shaifan_sku_list";
	}
	
	
	@RequestMapping("/addShowarticle")
	public @ResponseBody String  addShowarticle(String skuid,String content,String imgsrc,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionUtil.getSessionUser(request);
			showarticle article=new showarticle();
			article.setContent(content);
			article.setUserid(user.getUserId());
			article.setNickname(user.getLoginName());
			article.setProid(StringUtilsEX.ToInt(skuid));
			String [] imgs=ParseUtil.parseArray(imgsrc, ",");
			if (imgs!=null) {
				for (int i = 0; i < imgs.length; i++) {
					if (i==0) {
						article.setImgone(imgs[0]);
					}else if (i==1) {
						article.setImgtwo(imgs[1]);
					}else if (i==2) {
						article.setImgthr(imgs[2]);
					}else{
						break;
					}
				}
			}
			Sku sku=skuService.selectByID(StringUtilsEX.ToInt(skuid));
			if(sku!=null){
				article.setProname(sku.getName());
				article.setProprice(sku.getPrice().floatValue());
				article.setProimg(sku.getImgurlApp());
				
			}
			article.setCreatetime(new Date());
			int i=showarticleService.insertshowarticle(article);
			if(i>=0){
				item.setCode(0);
				item.setDesc("发布成功");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/spuList");
		}
		return item.toJson();
	}
	
	@RequestMapping("/showList")
	public String showList(String page, String size,Model model,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			
			CriteriaSku aoc = new CriteriaSku();
			PageBean pBean = showarticleService.getAllShow(StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size),aoc);	
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setMaxRow(pBean.getPs());
			item.setPage(pBean.getTp());
			item.setCode(0);
			model.addAttribute("pageData",item);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/spuList");
		}
		return "/template/wap/discover/shaifan_show_list";
	}
	
	
	@RequestMapping("/showPublishList")
	public String showPublishList(String page, String size,Model model,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
		
			CriteriaSku aoc = new CriteriaSku();
			SessionUser user=SessionUtil.getSessionUser(request);
			aoc.setUserid(user.getUserId());
		
			PageBean pBean = showarticleService.getPublishList(StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size),aoc);
				
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setMaxRow(pBean.getPs());
			item.setPage(pBean.getTp());
			item.setCode(0);
			model.addAttribute("pageData",item);
			
			
		} catch (Exception e) {
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/spuList");
		}
		return "/template/wap/discover/shaifan_publish_list";
	}
	
	
	/**
	 * 我喜欢的
	 * @param skuid
	 * @param content
	 * @param imgsrc
	 * @param request
	 * @return
	 */
	@RequestMapping("/addLike")
	public @ResponseBody String  addLike(String showid,String userid,String nickname,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			SessionUser sessionuser=SessionUtil.getSessionUser(request);
			showuser user=new showuser();
			user.setLikenickname(sessionuser.getLoginName());
			user.setLikeuserid(sessionuser.getUserId());
			user.setLiketime(new Date());
			user.setShowuserid(StringUtilsEX.ToInt(userid));
			user.setShownickname(nickname);
			user.setShowid(StringUtilsEX.ToInt(showid));
			
			int i=showuserService.insertshowuser(user);
			if(i>=0){
				item.setCode(0);
				item.setDesc("发布成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "fandetail.html", "/api/wap/showfan/addLike", "操作说明（例：我喜欢的）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"我喜欢的 异常信息:",
									e, "/api/wap/showfan/addLike");
						}
					}
				});
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/addLike");
		}
		return item.toJson();
	}
	

	@RequestMapping("/showLikeList")
	public String showLikeList(String page, String size,Model model,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
		
			CriteriaSku aoc = new CriteriaSku();
			SessionUser user=SessionUtil.getSessionUser(request);
			aoc.setUserid(user.getUserId());
		
			PageBean pBean = showuserService.getLikeList(StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size), aoc);
				
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setMaxRow(pBean.getPs());
			item.setPage(pBean.getTp());
			item.setCode(0);
			model.addAttribute("pageData",item);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/showLikeList");
		}
		return "/template/wap/discover/shaifan_like_list";
	}
	
	@RequestMapping("/updateName")
	public @ResponseBody String  updateName(String name,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			Integer userid=SessionUtil.getSessionUserId(request);
			Users user=userservice.selectByPrimaryKey(userid);
			user.setUsername(name);
			int i=userservice.updateInfo(user);
			if(i>=0){
				item.setCode(0);
				item.setDesc("修改成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "updname.html", "/api/wap/showfan/updateName", "操作说明（例：修改名称）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"修改名称 异常信息:",
									e, "/api/wap/showfan/addLike");
						}
					}
				});
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/updateName");
		}
		return item.toJson();
	}
	
	@RequestMapping("/updateImgurl")
	public @ResponseBody String  updateImgurl(String imgurl,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			Integer userid=SessionUtil.getSessionUserId(request);
			Users user=userservice.selectByPrimaryKey(userid);
			user.setImgurl(imgurl);
			int i=userservice.updateInfo(user);
			if(i>=0){
				item.setCode(0);
				item.setDesc("修改成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "mypublish.html", "/api/wap/showfan/updateImgurl", "操作说明（例：修改头像）");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"修改头像 异常信息:",
									e, "/api/wap/showfan/updateImgurl");
						}
					}
				});
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/updateName");
		}
		return item.toJson();
	}
	
	
	@RequestMapping("/addComent")
	public @ResponseBody String  addComent(String showid,String userid,String content,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			SessionUser sessionuser=SessionUtil.getSessionUser(request);
			Comment cmment=new Comment();
			cmment.setBuyerid(sessionuser.getUserId());  //评论人id
			cmment.setShopid(StringUtilsEX.ToInt(userid)); //被评论人id
			cmment.setOrderdetailid(StringUtilsEX.ToInt(showid)); //被评论的文章id
			cmment.setContent(content);
			cmment.setDate(new Date());
			cmment.setType(CommentTypeEnum.商品晒范.getValue());
			int i=commentService.insertUserComm(cmment);
			if(i>=0){
				item.setCode(0);
				item.setDesc("评论成功");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "评论文章", "/api/wap/showfan/addComent", "评论文章");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"评论文章 异常信息:",
									e, "/api/wap/showfan/addComent");
						}
					}
				});
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/addComent");
		}
		return item.toJson();
	}
	
	
	@RequestMapping("/showShopList")
	public String showShopList(String page, String size,Model model,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
		
			CriteriaShopSheive aoc = new CriteriaShopSheive();
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String end = formatter.format(calendar.getTime());
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH,1);
			String start = formatter.format(DateUtil.getPreviousMonth());
			aoc.setEndTime(end);
			aoc.setStartTime(start);
			CriteriaShop criteria=new CriteriaShop();
			PageBean pBean =shopService.selectShopPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			List<Shop> shoplist=(List<Shop>)pBean.getBeanList();
			List<ShopShevelSpuDto> shevelList=new ArrayList<ShopShevelSpuDto>();	
			for (Shop shop : shoplist) {
				aoc.setShopid(shop.getId());
				List<ShevelSpuDto> spulist=spuService.queryShevelSpu(aoc);
				if(spulist!=null && spulist.size()>0){
					ShopShevelSpuDto  dto=new ShopShevelSpuDto();
					dto.setShopid(shop.getId());
					dto.setShopName(shop.getName());
					dto.setShopImg(shop.getImgurl());
					dto.setShopDescript(shop.getDescription());
					dto.setChildren(spulist);
					dto.setCount(spulist.size());
					shevelList.add(dto);
				}
				
			}

			item.setData(shevelList);
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setMaxRow(pBean.getPs());
			item.setPage(pBean.getTp());
			item.setCode(0);
			model.addAttribute("pageData",item);
			
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/showShopList");
		}
		return "/template/wap/discover/shaifan_spushevel_list";
	}
	
	
	@RequestMapping("/showMoreSpuList")
	public String showMoreSpuList(String page, String size,String shopid,Model model,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
		
			CriteriaShopSheive aoc = new CriteriaShopSheive();
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String end = formatter.format(calendar.getTime());
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH,1);
			String start = formatter.format(DateUtil.getPreviousMonth());
			aoc.setEndTime(end);
			aoc.setStartTime(start);
			aoc.setShopid(StringUtilsEX.ToInt(shopid));
			PageBean pBean=spuService.queryShevelSpuPage(aoc,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));

			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setMaxRow(pBean.getPs());
			item.setPage(pBean.getTp());
			item.setCode(0);
			model.addAttribute("pageData",item);
			model.addAttribute("shopid", shopid);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			item.setCode(-900);
			MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e, "/api/wap/showfan/showShopList");
		}
		return "/template/wap/discover/shaifan_spuMoreshevel_list";
	}
	
}
