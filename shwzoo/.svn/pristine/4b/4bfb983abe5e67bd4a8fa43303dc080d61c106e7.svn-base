package com.yinlian.api.app.controller;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.ProStatusEnum;
import com.yinlian.Enums.UserCollectTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.CollectDto;
import com.yinlian.api.app.dto.SatisfactionGoodCountDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.search.userNewsCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.SpuExample;
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.po.users_news;
import com.yinlian.wssc.web.po.users_newsExample;
import com.yinlian.wssc.web.po.users_newsExample.Criteria;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ShopAuthenticationService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.service.giftcardService;
import com.yinlian.wssc.web.service.users_newsService;
import com.yinlian.wssc.web.util.CriteriaCollect;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/app/concern")
public class ConcernController {
	/**
	 * 日志输出的类
	 */
	//private static final Logger logger = LoggerFactory.getLogger(ConcernController.class);
	
	@Autowired
	private   UsercollectService        usercollectService;
	
	@Autowired
	private   ShopService               shopService;
	
	@Autowired
	private   SpuService                spuService;
	@Autowired
	private users_newsService userNewsService;
	
	@Autowired
	private NewsService snewsService;
	 @Autowired
	 private giftcardService  giftcardService;
	 @Autowired
	 private OperaterecordsService operaterecordsService;
	 @Autowired
	 private ShopMapper shopMapper;
	 @Autowired
	 private ShopAuthenticationService shopAuthenticationService;
	 @Autowired
	 private SpuMapper spuMapper;
	/**
	 * 收藏店铺
	 * @param token
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value = "/collectShop", produces = "text/html;charset=UTF-8")
	public @ResponseBody String collectShop(String token,String shopId,String ch, HttpServletResponse response) {
		BaseResult item = new BaseResult();
        response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			
			if (StringUtilsEX.IsNullOrWhiteSpace(shopId)) {
				item.setCode(-102);
				item.setDesc("店铺id不能为空！");
				return item.toJson();
			}
			
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if(user.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆");
				return item.toJson();
			} 
			int  buyerId=user.getUserId();
			Usercollect usercollect=new Usercollect();
			usercollect.setUserid(buyerId);
			usercollect.setShopid(StringUtilsEX.ToInt(shopId));
			usercollect.setType(UserCollectTypeEnum.店铺.getValue());
			usercollect.setVaildflag(0);
			usercollect.setCreatetime(new Date());
			List<Usercollect> list=usercollectService.selectShop(buyerId, StringUtilsEX.ToInt(shopId));
			if(list!=null&&list.size()>=1){
				item.setCode(-104);
				item.setDesc("你已收藏该店铺了");
			}else{
				int temp=usercollectService.add(usercollect);
				if(temp<=0){
					item.setCode(-105);
					item.setDesc("收藏店铺失败");
				}else{
					item.setCode(0);
					item.setDesc("收藏店铺成功");
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("收藏店铺异常：" + e.getMessage());
			LogHandle.error(LogType.Api, MessageFormat.format("收藏店铺异常! 异常信息:{0}",
					e.toString()), "concern/collectShop");
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
	public @ResponseBody String delCollectShop(String token,String shopId,String ch, HttpServletResponse response) {
		BaseResult item = new BaseResult();
        response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			
			if (StringUtilsEX.IsNullOrWhiteSpace(shopId)) {
				item.setCode(-102);
				item.setDesc("店铺id不能为空！");
				return item.toJson();
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser=new SessionUser();
			sessionUser=SessionState.GetCurrentUser(token);
			if(sessionUser.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆！");
			}else{
				int buyerId=sessionUser.getUserId();
				Date delTime=new Date();
				
					int temp=usercollectService.delShop(buyerId, StringUtilsEX.ToInt(shopId), delTime);
					if(temp==0){
						item.setCode(-104);
						item.setDesc("取消收藏店铺失败");
					}else{
						item.setCode(0);
						item.setDesc("取消收藏店铺成功");
					}
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"删除收藏店铺异常! 异常信息:{0}",
					e, "/concern/delCollectShop");
		}
		
		return item.toJson();
	}
	
	
	
	/**
	 * 收藏商品
	 * @param token
	 * @param skuId
	 * @return
	 */
	@RequestMapping(value = "/collectSpu", produces = "text/html;charset=UTF-8")
	public @ResponseBody String collectSpu(String token,String spuId,String ch) {
		BaseResult item = new BaseResult();
		try {
			
			if (StringUtilsEX.IsNullOrWhiteSpace(spuId)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser=new SessionUser();
			sessionUser=SessionState.GetCurrentUser(token);
			if(sessionUser.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆！");
			}else{
				int buyerId=sessionUser.getUserId();
				Usercollect usercollect=new Usercollect();
				usercollect.setUserid(buyerId);
				usercollect.setSpuid(StringUtilsEX.ToInt(spuId));
				usercollect.setType(UserCollectTypeEnum.商品.getValue());
				usercollect.setVaildflag(0);
				usercollect.setCreatetime(new Date());
				
					List<Usercollect> list=usercollectService.selectSpu(buyerId, StringUtilsEX.ToInt(spuId));
					if(list!=null&&list.size()>=1){
						item.setCode(-104);
						item.setDesc("你已收藏该商品了");
					}else{
						int temp=usercollectService.add(usercollect);
						if(temp==0){
							item.setCode(-107);
							item.setDesc("收藏商品失败");
						}else{
							item.setCode(0);
							item.setDesc("收藏商品成功");
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
				LogHandle.error(LogType.Api,"收藏商品异常! 异常信息:{0}",
						e, "/concern/collectSpu");
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
	public @ResponseBody String delCollectSpu(String token,String spuId,String ch) {
		BaseResult item = new BaseResult();
		try {
			
			if (StringUtilsEX.IsNullOrWhiteSpace(spuId)) {
				item.setCode(-102);
				item.setDesc("商品id不能为空！");
				return item.toJson();
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser=new SessionUser();
			sessionUser=SessionState.GetCurrentUser(token);
			if(sessionUser.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆！");
			}else{
				int buyerId=sessionUser.getUserId();
				
				Date delTime=new Date();
				
					int temp=usercollectService.delSpu(buyerId, StringUtilsEX.ToInt(spuId), delTime);
					if(temp==0){
						item.setCode(-104);
						item.setDesc("取消收藏商品失败");
					}else{
						item.setCode(0);
						item.setDesc("取消收藏商品成功");
					}
				}
			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(e.toString());
				} else {
					item.setDesc("系统错误！");
				}
				item.setCode(-900);
				LogHandle.error(LogType.Api,"删除收藏商品异常! 异常信息:{0}",
						e, "concern/delCollectSpu");
			}
		
		return item.toJson();
	}
	
	
	/**
	 * 获取商品收藏/店铺收藏
	 * @param token
	 * @param type  0表示商品，1表示店铺
	 * @param pageindex
	 * @param pagesize
	 * @param ch
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectCollect", produces = "text/html;charset=UTF-8")
	public @ResponseBody String selectCollect(String token,String type,String pageindex,String pagesize,String ch) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser sessionUser=new SessionUser();
			sessionUser=SessionState.GetCurrentUser(token);
			if(sessionUser.getCode()!=0){
				item.setCode(-401);
				item.setDesc("请先登陆！");
				return item.toJson();
			}
		    Integer index=StringUtilsEX.ToInt(pageindex);
		    Integer size=StringUtilsEX.ToInt(pagesize);
		    if(index<=0){
		    	index=1;
		    }
		    if(size<0){
		    	size=10;
		    }
			if (StringUtilsEX.IsNullOrWhiteSpace(type)) {
				item.setCode(-102);
				item.setDesc("收藏类型(type)不能为空！");
				return item.toJson();
			}
			if(!StringUtilsEX.isChannelTypeExist(ch)){
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			int collectType=StringUtilsEX.ToInt(type);
			if(collectType!=UserCollectTypeEnum.商品.getValue()&&collectType!=UserCollectTypeEnum.店铺.getValue()){
				item.setCode(-104);
				item.setDesc("查询类型有误");
				return item.toJson();
			}
			CriteriaCollect criteria=new CriteriaCollect();
			int buyerId=sessionUser.getUserId();
			criteria.setUserid(buyerId);
			criteria.setType(collectType);
			criteria.setOrderByClause("CreateTime");
			criteria.setSort("desc");
			PageBean pageBean =usercollectService.selectPage(criteria, index, size);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setPage(pageBean.getPs());
			List<Usercollect> list=(List<Usercollect>)pageBean.getBeanList();
			List<CollectDto> collectDtoList = new ArrayList<CollectDto>();
			if(list!=null&&list.size()<1){
				item.setCode(0);
				return item.toJson();
			}else{
				if(collectType==UserCollectTypeEnum.商品.getValue()){
					for (int i = 0; i < list.size(); i++) {
						CollectDto collectDto = new CollectDto();
						Spu spu=spuService.queryById(list.get(i).getSpuid());
						if(spu!=null){
							collectDto.setName(spu.getName());
							collectDto.setPrice(spu.getPrice());
							collectDto.setSpuid(spu.getId());
							collectDto.setImgurl(spu.getImgurl());
							
							Date date=new Date();
							Integer year=DateUtil.getYear(date);
							Integer month=DateUtil.getMonth(date);
							Integer day=DateUtil.getDay(date);
							SkuShowtime skutime=spuService.getSkuTime(spu.getId(), year, month, day);
							if(skutime!=null && skutime.getPrice().doubleValue()>0){
								collectDto.setPrice(skutime.getPrice());
							}
							
						}
						collectDtoList.add(collectDto);
					}
					item.setCode(0);
					item.setDesc("查询收藏成功");
					item.setData(collectDtoList);
				}else{
					for (int i = 0; i <list.size(); i++) {
						CollectDto collectDto = new CollectDto();
						Shop shop=shopService.queryById(list.get(i).getShopid());
						ShopAuthentication authentication = shopAuthenticationService.queryByShopId(list.get(i).getShopid());
						if(shop!=null){
							if(authentication != null){
								collectDto.setMarketingscope(authentication.getMarketingscope());
								collectDto.setBewrite(authentication.getBewrite());
							}
							SpuExample example1 = new SpuExample();
							SpuExample.Criteria criteria1 = example1.createCriteria();
							criteria1.andShopidEqualTo(shop.getId());
							criteria1.andIsdelEqualTo(false);
							criteria1.andStatusEqualTo(ProStatusEnum.上架.getValue());
							int count = spuMapper.countByExample(example1);
							collectDto.setProcount(count);
							collectDto.setName(shop.getName());
							collectDto.setShopid(shop.getId());
							collectDto.setImgurl(shop.getImgurl());
							List<SatisfactionGoodCountDto> dtos = shopMapper.getSfGoodCoutByShopId(shop.getId());
							if (dtos != null && dtos.size() > 0) {
								int commentSize = dtos.size();
								int stars = 0;
								for (SatisfactionGoodCountDto comm : dtos) {
									stars += comm.getAttitude();
								}
								double starr = Math.ceil(stars / commentSize);
								collectDto.setStar(starr + "");
							} else {
								collectDto.setStar("5");
							}
//							List<SatisfactionGoodCountDto> satisfactiondtos = shopMapper.getSfGoodCoutByShopId(shop.getId());
//							double goodrate = satisfactiondtos.stream().filter(x->x.getAttitude()>3).mapToInt(y->y.getCount()).sum();
//							double all = satisfactiondtos.stream().mapToInt(x->x.getCount()).sum();
//							if (goodrate > 0 && all > 0) {
//								collectDto.setGoodrate(new BigDecimal(goodrate/all * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "%");
//							}else {
//								collectDto.setGoodrate("0.00%");
//							}	
							collectDto.setGoodrate(getrate() + "%");
						}
						collectDtoList.add(collectDto);
					}
					item.setCode(0);
					item.setDesc("查询收藏成功");
					item.setData(collectDtoList);
				}	
			}				
			
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询收藏异常：" + e.getMessage());
			}else {
				item.setDesc("查询收藏异常");
			}			
			LogHandle.error(LogType.Api,"查询收藏的店铺或商品! 异常信息:{0}",
					e, "concern/selectCollect");
		}
		return item.toJson();
	}
	 
	/**
	 *  收藏菜谱
	 * @param newsid  菜谱ID
	 * @param token	  获取用户登陆信息
	 * @return
	 */
	@RequestMapping(value = "/newssccp", produces = "text/html;charset=UTF-8")
	public @ResponseBody String newssccp(String newsid,String token){
		ReusltItem item = new ReusltItem();
		SessionUser user = SessionState.GetCurrentUser(token);
		if(user.getCode()<0){
			item.setCode(-101);
 			item.setDesc("请登录");
			return item.toJson();
		}
		try{
			
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
			if(snew.getYconut()==null){
				snew.setYconut(1);
			}else{
				snew.setYconut(snew.getYconut()+1);
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
			LogHandle.error(LogType.news,"收藏菜谱失败! 异常信息:{0}", e,
					"/usersnews/newssccp");
		}
		return item.toJson();
	}
	
	/**
	 * 取消收藏菜谱
	 * @param newsid  菜谱ID
	 * @param token	  获取用户登陆信息
	 * @return
	 */
	@RequestMapping(value = "/newsqxsccp", produces = "text/html;charset=UTF-8")
	public @ResponseBody String newsqxsccp(String newsid,String token,String ch){
		ReusltItem item = new ReusltItem();
		SessionUser user = SessionState.GetCurrentUser(token);
		if(user.getCode()<0){
			item.setCode(-101);
 			item.setDesc("请登录");
			return item.toJson();
		}
		if(!StringUtilsEX.isChannelTypeExist(ch)){
			item.setCode(-108);
			item.setDesc("登录通道参数错误");
			return item.toJson();
		}
		try{
			SnewsWithBLOBs snew=snewsService.selSlistById(StringUtilsEX.ToInt(newsid));
			snew.setYconut(snew.getYconut()-1);//收藏数量-1
			snewsService.updateListById(snew);
			userNewsService.deleteByuseridAndNewsid(user.getUserId(), StringUtilsEX.ToInt(newsid));
			item.setDesc("取消收藏成功");
			item.setCode(0);
		}catch(Exception e){
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.news, "收藏菜谱失败! 异常信息:{0}", e,
					"/usersnews/newssccp");
		}
		return item.toJson();
	}
	/**
	 * 获取菜谱列表数据
	 * @param token 登录唯一标识
	 * @param page 
	 * @param size
	 * @param ch 通道
	 * @return
	 */
	@RequestMapping(value = "/usercpcslist", produces = "text/html;charset=UTF-8")
	public @ResponseBody String usercpcslist(String token,Integer page,Integer size,String ch){
		ReusltItem item = new ReusltItem();
		SessionUser user = SessionState.GetCurrentUser(token);
		if(user.getCode()<0){
			item.setCode(-101);
 			item.setDesc("请登录");
 			
			return item.toJson();
		}
		if(!StringUtilsEX.isChannelTypeExist(ch)){
			item.setCode(-108);
			item.setDesc("登录通道参数错误");
			return item.toJson();
		}
		try{
			if(page==null||page==0){
				page=1;
			}
			if(size==null||size==0){
				size=10;
			}
			userNewsCriteria cri=new userNewsCriteria(); 
			cri.setUserid(user.getUserId());
			PageBean bean=userNewsService.usersNewsPagelist(cri,page,size);
			item.setPage(page);
			item.setPageSize(size);
			item.setData(bean.getBeanList());
			item.setDesc("获取菜谱列表数据成功");
		}catch(Exception e){
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取菜谱列表数据失败：" + e.getMessage());
			}else {
				item.setDesc("获取菜谱列表数据失败");
			}
			item.setDesc("获取菜谱列表数据失败：" + e.getMessage());
			LogHandle.error(LogType.news, MessageFormat.format("获取菜谱列表数据失败! 异常信息:{0}", e.getMessage()),
					"/usersnews/newssccp");
		}
		return item.toJson();
	}
	
	
	
	
	
	/**
	 * 礼品卡充值
	 * @param cardnum  礼品卡号
	 * @param cardpwd 礼品卡密码
	 * @param phonecode 手机验证码
	 * @param mobile 手机号
	 * @param token
	 * @return
	 */
		@RequestMapping(value = "/chargecard",produces = "text/html;charset=UTF-8")
		public @ResponseBody String chargeCard(String cardnum,String cardpwd,String phonecode,String mobile,String  token,String ch){
			BaseResult item=new BaseResult();
			try {
				SessionUser user=SessionState.GetCurrentUser(token);
				if(!StringUtilsEX.isChannelTypeExist(ch)){
					item.setCode(-402);
					item.setDesc("登录通道参数错误");
					return item.toJson();
				}
				if (user==null || user.getCode() <0) {
					item.setCode(-401);
					item.setDesc("用户未登录");
					return item.toJson();
				}
				if (StringUtilsEX.IsNullOrWhiteSpace(cardnum)) {
					item.setCode(-101);
					item.setDesc("充值卡号不能为空！");
					return item.toJson();
				}
				if (StringUtilsEX.IsNullOrWhiteSpace(cardpwd)) {
					item.setCode(-102);
					item.setDesc("充值卡密码不能为空！");
					return item.toJson();
				}
				if (StringUtilsEX.IsNullOrWhiteSpace(phonecode)) {
					item.setCode(-103);
					item.setDesc("手机验证码不能为空！");
					return item.toJson();
				}
				
				String smss = RedisUserInfo.Get("I" + mobile);
	            if (!phonecode.equals(smss)) {
	                item.setCode(-104);
	                item.setDesc("短信验证码错误");
	                return item.toJson();
	            }
	            
	           Integer userid=user.getUserId();
			   int temp=giftcardService.giftcardRecharge(cardnum.trim(), cardpwd, userid, mobile);
			   if(temp==-1){
				   item.setCode(-105);
	                item.setDesc("礼品卡错误");
	                return item.toJson();
			   }
			   if(temp==-2){
				   item.setCode(-106);
	                item.setDesc("礼品卡密码错误");
	                return item.toJson();
			   }
			   if(temp==-3){
				   item.setCode(-107);
	                item.setDesc("礼品卡已使用");
	                return item.toJson();
			   }
		       if(temp>0){
		    	   item.setCode(0);
		    	   item.setDesc("充值成功");
		    	   ExecutorService executorService=Executors.newCachedThreadPool();
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {							
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "cardcharge.html", "/user/chargecard", "礼品卡充值");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"礼品卡充值记录异常:",
										e, "/user/chargecard");
							}
						}
					});
		       }else{
		    	   item.setCode(-108);
		    	   item.setDesc("礼品卡充值失败");
		       }
	         
			} catch (Exception e) {
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc(e.toString());
				} else {
					item.setDesc("系统错误！");
				}
				item.setCode(-900);
				LogHandle.error(LogType.pc,"礼品卡充值异常! 异常信息:{0}",
						e, "user/chargecard");
			}
			
			return item.toJson();
		}
		private int getrate() {
			int max = 100;
			int min = 90;
			java.util.Random random = new java.util.Random();

			int s = random.nextInt(max) % (max - min + 1) + min;
			return s;
		}
}
