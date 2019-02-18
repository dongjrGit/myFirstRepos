package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.UserCollectTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.po.users_news;
import com.yinlian.wssc.web.po.users_newsExample;
import com.yinlian.wssc.web.po.users_newsExample.Criteria;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.service.users_newsService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/concern")
public class WapConcernController {

	@Autowired
	private UsercollectService usercollectService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;
	@Autowired
	private users_newsService userNewsService;
	
	@Autowired
	private NewsService snewsService;
	/**
	 * 收藏商品
	 * 
	 * @param token
	 * @param spuId
	 * @param ch
	 *            wap:3
	 * @return
	 */
	@RequestMapping(value = "/addspuconcern", produces = "text/html;charset=UTF-8")
	public @ResponseBody String collectSpu(String spuId, String ch,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {

			if (StringUtilsEX.IsNullOrWhiteSpace(spuId)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser  sessionUser = new SessionUser();			
			sessionUser = SessionState.GetCurrentUser();
			if (sessionUser==null||sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int buyerId = sessionUser.getUserId();
				Usercollect usercollect = new Usercollect();
				usercollect.setUserid(buyerId);
				usercollect.setSpuid(StringUtilsEX.ToInt(spuId));
				usercollect.setType(UserCollectTypeEnum.商品.getValue());
				usercollect.setVaildflag(0);
				usercollect.setCreatetime(new Date());

				List<Usercollect> list = usercollectService.selectSpu(buyerId,
						StringUtilsEX.ToInt(spuId));
				if (list != null && list.size() >= 1) {
					item.setCode(-104);
					item.setDesc("你已收藏该商品了");
				} else {
					int temp = usercollectService.add(usercollect);
					if (temp == 0) {
						item.setCode(-104);
						item.setDesc("收藏商品失败");
					} else {
						item.setCode(0);
						item.setDesc("收藏商品成功");
						ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {
									SessionUser user=SessionState.GetCurrentUser();
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "pro_foot.html", "/api/wap/concern/addspuconcern", "收藏商品");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"收藏商品 异常信息:",
		    								e, "/api/wap/concern/addspuconcern");
								}
							}
						});
					}
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("收藏商品异常：" + e.getMessage());
			LogHandle.error(LogType.wap,
					MessageFormat.format("收藏商品异常! 异常信息:{0}", e.toString()),
					"concern/collectSpu");
		}

		return item.toJson();
	}
	/**
	 * 收藏店铺
	 * 
	 * @param shopid 店铺id
	 * @param ch
	 *            wap:3
	 * @return
	 */
	@RequestMapping(value = "/addshopconcern", produces = "text/html;charset=UTF-8")
	public @ResponseBody String collectShop(String shopid, String ch) {
		BaseResult item = new BaseResult();
		try {
            Integer sid=StringUtilsEX.ToInt(shopid);
			if (sid<0) {
				item.setCode(-101);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser  sessionUser= SessionState.GetCurrentUser();
			if (sessionUser==null||sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int buyerId = sessionUser.getUserId();
				Usercollect usercollect = new Usercollect();
				usercollect.setUserid(buyerId);
				usercollect.setShopid(sid);
				usercollect.setType(UserCollectTypeEnum.店铺.getValue());
				usercollect.setVaildflag(0);
				usercollect.setCreatetime(new Date());

				List<Usercollect> list = usercollectService.selectShop(buyerId,sid);
				if (list != null && list.size() >= 1) {
					item.setCode(-104);
					item.setDesc("你已收藏该商品了");
				} else {
					int temp = usercollectService.add(usercollect);
					if (temp == 0) {
						item.setCode(-104);
						item.setDesc("收藏店铺失败");
					} else {
						item.setCode(0);
						item.setDesc("收藏店铺成功");
						ExecutorService executorService=Executors.newCachedThreadPool();
						executorService.execute(new Runnable() {
							@Override
							public void run() {
								try {
									SessionUser user=SessionState.GetCurrentUser();
									operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "/shop/index.html", "/api/wap/concern/addshopconcern", "收藏店铺");
								} catch (Exception e) {
									LogHandle.error(LogType.OperateRecords,"收藏店铺 异常信息:",
		    								e, "/api/wap/concern/addshopconcern");
								}
							}
						});
					}
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("收藏店铺异常：" + e.getMessage());
			LogHandle.error(LogType.wap,
					MessageFormat.format("收藏店铺异常! 异常信息:{0}", e.toString()),
					"concern/addshopconcern");
		}

		return item.toJson();
	}

	/**
	 * 删除收藏的商品
	 * 
	 * @param token
	 * @param spuId
	 * @param ch
	 *            wap:3
	 * @return
	 */
	@RequestMapping(value = "/cancelspuconcern", produces = "text/html;charset=UTF-8")
	public @ResponseBody String delCollectSpu( String spuId,
			String ch,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {

			if (StringUtilsEX.IsNullOrWhiteSpace(spuId)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser  sessionUser = new SessionUser();			
			sessionUser = SessionState.GetCurrentUser();
			if (sessionUser==null||sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int buyerId = sessionUser.getUserId();

				Date delTime = new Date();

				int temp = usercollectService.delSpu(buyerId,
						StringUtilsEX.ToInt(spuId), delTime);
				if (temp == 0) {
					item.setCode(-104);
					item.setDesc("取消收藏商品失败");
				} else {
					item.setCode(0);
					item.setDesc("取消收藏商品成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user=SessionState.GetCurrentUser();
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "pro_foot.html", "/api/wap/concern/addshopconcern", "取消收藏商品成功");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"取消收藏商品成功 异常信息:",
	    								e, "/api/wap/concern/addshopconcern");
							}
						}
					});
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除收藏商品异常：" + e.getMessage());
			LogHandle.error(LogType.Api,
					MessageFormat.format("删除收藏商品异常! 异常信息:{0}", e.toString()),
					"concern/delCollectSpu");
		}

		return item.toJson();
	}
	/**
	 * 删除收藏的商品
	 * 
	 * @param shopid 店铺id
	 * @param ch
	 *            wap:3
	 * @return
	 */
	@RequestMapping(value = "/cancelshopconcern", produces = "text/html;charset=UTF-8")
	public @ResponseBody String delCollectShop( String shopid,String ch) {
		BaseResult item = new BaseResult();
		try {

			if (StringUtilsEX.ToInt(shopid)<0) {
				item.setCode(-101);
				item.setDesc("店铺id参数错误");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser  sessionUser= SessionState.GetCurrentUser();
			if (sessionUser==null||sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int buyerId = sessionUser.getUserId();

				Date delTime = new Date();
				int temp = usercollectService.delShop(buyerId,StringUtilsEX.ToInt(shopid), delTime);
				if (temp == 0) {
					item.setCode(-103);
					item.setDesc("取消收藏店铺失败");
				} else {
					item.setCode(0);
					item.setDesc("取消收藏店铺成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								SessionUser user=SessionState.GetCurrentUser();
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "/shop/index.html", "/api/wap/concern/addshopconcern", "取消收藏店铺成功");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"取消收藏店铺成功 异常信息:",
	    								e, "/api/wap/concern/addshopconcern");
							}
						}
					});
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除收藏店铺异常：" + e.getMessage());
			LogHandle.error(LogType.wap,
					MessageFormat.format("删除收藏店铺异常! 异常信息:{0}", e.toString()),
					"concern/delCollectShop");
		}

		return item.toJson();
	}
	/**
	 * 菜谱收藏
	 * @param newsid 菜谱ID
	 * @return
	 */
	@RequestMapping(value = "/newssccp",produces = "text/html;charset=UTF-8")
	public @ResponseBody String newssccp(String newsid){
		ReusltItem item = new ReusltItem();
		try{
			SessionUser user=SessionState.GetCurrentUser();
			if(user.getCode()<0){
				item.setCode(-0);
				item.setDesc("未登录");
				return item.toJson();
			}
			users_newsExample exm=new users_newsExample();
			Criteria cr=exm.createCriteria();
			cr.andNewsidEqualTo(StringUtilsEX.ToInt(newsid));
			cr.andUseridEqualTo(user.getId());
			List<users_news> list=userNewsService.selectByExample(exm);
			if(list!=null&&list.size()>0){
				item.setCode(0);
				item.setDesc("已收藏");
				return item.toJson();
			}
			SnewsWithBLOBs snews =snewsService.getById(StringUtilsEX.ToInt(newsid));
			users_news news=new users_news();
			news.setCreatetime(new Date());
			news.setNewsid(StringUtilsEX.ToInt(newsid));
			news.setNewsname(snews.getTitle());
			news.setType(0);
			news.setUserid(user.getUserId());
			news.setUsername(user.getName());
			item.setCode(0);
			item.setDesc("收藏成功");
			SnewsWithBLOBs snew=snewsService.selSlistById(StringUtilsEX.ToInt(newsid));
			if(snew.getEx1()==null){
				snew.setEx1(1);
			}else{
				snew.setEx1(snew.getEx1()+1);
			}
			snewsService.updateListById(snew);
			userNewsService.insert(news);
		}catch(Exception e){
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("收藏菜谱失败：" + e.getMessage());
			}else {
				item.setDesc("收藏菜谱失败");
			}
			item.setDesc("收藏菜谱失败：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("收藏菜谱失败! 异常信息:{0}", e.getMessage()),
					"/usersnews/newssccp");
		}
		return item.toJson();
	}
}
