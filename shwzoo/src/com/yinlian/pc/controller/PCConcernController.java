package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.UserCollectTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.pc.dto.PcSpuDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.Pc_CollectCriteria;
import com.yinlian.wssc.search.userNewsCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.po.users_news;
import com.yinlian.wssc.web.po.users_newsExample;
import com.yinlian.wssc.web.po.users_newsExample.Criteria;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.service.users_newsService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/concern")
public class PCConcernController {

	@Autowired
	private   UsercollectService        usercollectService;
	
	@Autowired
	private   ShopService               shopService;
	
	@Autowired
	private   SpuService                spuService;
	
	@Autowired
	private OperaterecordsService operaterecordsService;
	
	@Autowired
	private users_newsService userNewsService;
	
	@Autowired
	private NewsService snewsService;
	/**
	 * 查询收藏的商品
	 * @param token
	 * @param spuId
	 * @return
	 */
	@RequestMapping(value = "/selectSpuCollect", produces = "text/html;charset=UTF-8")
	public  String selectSpuCollect(String page,String size,String ch,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			Integer userid=SessionUtil.getSessionUserId(request);
			if(userid!=null){
				Pc_CollectCriteria criteria=new Pc_CollectCriteria();
				criteria.setUserid(userid);
				criteria.setType(UserCollectTypeEnum.商品.getValue());
				criteria.setOrderByClause("CreateTime");
				criteria.setSort("desc");
				PageBean list = usercollectService.spuListByPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
				item.setData(list.getBeanList());
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
				item.setDesc("查询收藏的商品异常：" + e.getMessage());
			}else {
				item.setDesc("查询收藏的商品异常");
			}
			
			LogHandle.error(LogType.pc, "查询收藏的商品异常! 异常信息:{0}", e,
					"concern/selectCollect");
		}
		
		return item.toJson();
	}
	
	
	/**
	 * 查询收藏的店铺
	 * @param token
	 * @param spuId
	 * @return
	 */
	@RequestMapping(value = "/selectShopCollect", produces = "text/html;charset=UTF-8")
	public  String selectShopCollect(String page, String size,String ch,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			Integer userid=SessionUtil.getSessionUserId(request);
			if(userid!=null){
				Pc_CollectCriteria criteria=new Pc_CollectCriteria();
				criteria.setUserid(userid);
				criteria.setType(UserCollectTypeEnum.店铺.getValue());
				criteria.setOrderByClause("CreateTime");
				criteria.setSort("desc");
				PageBean list = usercollectService.ShopListByPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
				item.setData(list.getBeanList());
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
				item.setDesc("查询藏的店铺异常：" + e.getMessage());
			}else {
				item.setDesc("查询藏的店铺异常");
			}
			
			LogHandle.error(LogType.pc, "查询收藏的店铺异常! 异常信息:{0}", e,
					"concern/selectCollect");
		}
		
		return item.toJson();
	}
	
	
	
	
	
	/**
	 * 查询热销商品
	 * @param token
	 * @param spuId
	 * @return
	 */
	@RequestMapping(value = "/GetSpuByShopID", produces = "text/html;charset=UTF-8")
	public  String getSpuByShopID(String shopid,String ch) {
		ReusltItem item = new ReusltItem();
		
		try{
			
			List<PcSpuDto>  list=spuService.querySpuList(StringUtilsEX.ToInt(shopid));
			item.setData(list);
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询热销异常：" + e.getMessage());
			}else{
				item.setDesc("查询热销异常");
			}			
			LogHandle.error(LogType.pc, "查询热销异常! 异常信息:{0}", e,
					"concern/GetSpuByShopID");
		}
		
		return item.toJson();
	}
	
	
	/**
	 * 删除收藏的店铺
	 * @param token
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/delCollectShop", produces = "text/html;charset=UTF-8")
	public  String delCollectShop(String shopId, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<=0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(shopId)) {
				item.setCode(-102);
				item.setDesc("店铺id不能为空！");
				return item.toJson();
			}
			Integer userid=user.getUserId();
			if(userid!=null){
				
				Date delTime=new Date();
				
				int temp=usercollectService.delShop(userid, StringUtilsEX.ToInt(shopId), delTime);
				if(temp==0){
					item.setCode(-104);
					item.setDesc("取消收藏店铺失败");
				}else{
					item.setCode(0);
					item.setDesc("取消收藏店铺成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "取消收藏店铺", "/pc/concern/delCollectShop", "取消收藏店铺");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"取消收藏店铺 异常信息:",
	    								e, "/pc/concern/delCollectShop");
							}
						}
					});
				}
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("删除收藏店铺异常：" + e.getMessage());
			}else {
				item.setDesc("删除收藏店铺异常");
			}			
			LogHandle.error(LogType.pc, MessageFormat.format("删除收藏店铺异常! 异常信息:{0}",
					e.toString()), "concern/delCollectShop");
		}
		
		return item.toJson();
	}
	
	/**
	 * 删除收藏的商品
	 * @param token
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/delCollectSpu", produces = "text/html;charset=UTF-8")
	public @ResponseBody String delCollectSpu(String spuId, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<=0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(spuId)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}
			Integer userid=user.getUserId();
			if(userid!=null){
				Date delTime=new Date();
				
				int temp=usercollectService.delSpu(userid, StringUtilsEX.ToInt(spuId), delTime);
				if(temp==0){
					item.setCode(-104);
					item.setDesc("取消收藏商品失败");
				}else{
					item.setCode(0);
					item.setDesc("取消收藏商品成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "concernlist.html", "/pc/concern/delCollectSpu", "取消收藏商品");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"取消收藏商品记录 异常信息:",
	    								e, "/pc/concern/delCollectSpu");
							}
						}
					});
				}
			}
			
			
			} catch (Exception e) {
				item.setCode(-900);
				if(DebugConfig.BLUETOOTH_DEBUG){
					item.setDesc("删除收藏商品异常：" + e.getMessage());
				}else {
					item.setDesc("删除收藏商品异常");
				}				
				LogHandle.error(LogType.pc, MessageFormat.format("删除收藏商品异常! 异常信息:{0}",
						e.toString()), "concern/delCollectSpu");
			}
		
		return item.toJson();
	}
	
	
	/**
	 * 批量删除收藏的商品
	 * @param token
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/delMoreCollectSpu", produces = "text/html;charset=UTF-8")
	public @ResponseBody String delMoreCollectSpu(String cIDList, String ch,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<=0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(cIDList)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}			
			Integer userid = user.getUserId();
			String[] ids = cIDList.split(",");
			List<Integer>spuids=new ArrayList<Integer>();
			Date delTime=new Date();
			if(userid!=null){
				
				for (int i = 0; i < ids.length; i++) {
					spuids.add(StringUtilsEX.ToInt(ids[i]));
					
				}
//				int temp=usercollectService.delSpu(userid, StringUtilsEX.ToInt(ids[i]),delTime);
				int temp=usercollectService.delCollectSpus(userid, spuids, delTime);
					
				if (temp<0) {
					item.setCode(-200);
					item.setDesc("删除收藏的商品失败");
				} else {
					item.setCode(0);
					item.setDesc("删除收藏的商品成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "concernlist.html", "/pc/concern/delMoreCollectSpu", "批量删除收藏的商品");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"删除收藏的商品记录 异常信息:",
	    								e, "/pc/concern/delMoreCollectSpu");
							}
						}
					});
				}
			}
			} catch (Exception e) {
				item.setCode(-900);
				if(DebugConfig.BLUETOOTH_DEBUG){
					item.setDesc("删除收藏的商品异常：" + e.getMessage());
				}else {
					item.setDesc("删除收藏的商品异常");
				}
				
				LogHandle.error(LogType.pc, MessageFormat.format("删除收藏的商品异常! 异常信息:{0}",
						e.toString()), "concern/delMoreCollectSpu");
			}
		
		return item.toJson();
	}
	
	
	/**
	 * 批量删除收藏的店铺
	 * @param token
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/delMoreCollectShop", produces = "text/html;charset=UTF-8")
	public @ResponseBody String delMoreCollectShop(String cIDList, String ch,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if(user==null||user.getId()<=0){
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(cIDList)) {
				item.setCode(-102);
				item.setDesc("店铺id不能为空！");
				return item.toJson();
			}
			
			Integer userid = user.getUserId();
			String[] ids = cIDList.split(",");
			List<Integer>shopids=new ArrayList<Integer>();
			Date delTime=new Date();
			if(userid!=null){
				
				for (int i = 0; i < ids.length; i++) {
					shopids.add(StringUtilsEX.ToInt(ids[i]));
				}
//					int temp=usercollectService.delSpu(userid, StringUtilsEX.ToInt(ids[i]), delTime);
				int temp=usercollectService.delCollectShops(userid, shopids, delTime);
				if (temp <0) {
					item.setCode(-200);
					item.setDesc("删除收藏的店铺失败");
				} else {
					item.setCode(0);
					item.setDesc("删除收藏的店铺成功");
					ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "concernlist.html", "/pc/concern/delMoreCollectShop", "批量删除收藏的店铺");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"删除收藏的店铺记录 异常信息:",
	    								e, "/pc/concern/delMoreCollectShop");
							}
						}
					});
				}
			}
			} catch (Exception e) {
				item.setCode(-900);
				if(DebugConfig.BLUETOOTH_DEBUG){
					item.setDesc("删除收藏的店铺异常：" + e.getMessage());
				}else {
					item.setDesc("删除收藏的店铺异常");
				}
				LogHandle.error(LogType.pc, MessageFormat.format("删除收藏的店铺异常! 异常信息:{0}",
						e.toString()), "concern/delMoreCollectShop");
			}
		
		return item.toJson();
	}
	/**
	 * 菜谱收藏列表
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 */
	@RequestMapping("/usernewList")
	@ResponseBody
	public  ReusltItem usernewList(Integer page,Integer size,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try{
			SessionUser user=SessionUtil.getSessionUser(request);
			userNewsCriteria cri=new userNewsCriteria();
			cri.setUserid(user.getId());
			PageBean pageBean =userNewsService.usersNewslistPage(cri, page, size); 
			item.setCode(0);
			item.setDesc("获取收藏菜谱列表成功!");
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		}catch(Exception e){
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取收藏菜谱列表失败：" + e.getMessage());
			}else {
				item.setDesc("获取收藏菜谱列表失败");
			}
			
			LogHandle.error(LogType.news, MessageFormat.format("收藏菜谱失败! 异常信息:{0}", e.getMessage()),
					"/usersnews/newssccp");
		}
		return item;
	}
	/**
	 * 取消菜谱收藏
	 * @param id
	 * @return
	 */
	@RequestMapping("/delusernew")
	public ReusltItem delusernew(String id){
		ReusltItem item = new ReusltItem();
		try{
			userNewsService.deleteByPrimaryKey(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setDesc("已取消");
		}catch(Exception e){
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("取消收藏菜谱失败：" + e.getMessage());
			}else {
				item.setDesc("取消收藏菜谱失败");
			}
			LogHandle.error(LogType.news, MessageFormat.format("取消收藏菜谱失败! 异常信息:{0}", e.getMessage()),
					"/usersnews/newssccp");
		}
		return item;
	}
	/**
	 * 收藏菜谱
	 * @param newsid
	 * @param newsname
	 * @param req
	 * @return
	 */
	@RequestMapping("/newssccp")
	public ReusltItem newssccp(String newsid,String newsname,HttpServletRequest req){
		ReusltItem item = new ReusltItem();
		try{
			SessionUser user = SessionUtil.getSessionUser(req);
			users_newsExample exm=new users_newsExample();
			Criteria cr=exm.createCriteria();
			cr.andNewsidEqualTo(StringUtilsEX.ToInt(newsid));
			cr.andUseridEqualTo(user.getId());
			List<users_news> list=userNewsService.selectByExample(exm);
			if(list!=null&&list.size()>0){
				item.setCode(0);
				item.setDesc("已收藏");
				return item;
			}
			users_news news=new users_news();
			news.setCreatetime(new Date());
			news.setNewsid(StringUtilsEX.ToInt(newsid));
			news.setNewsname(newsname);
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
		return item;
	}
}
