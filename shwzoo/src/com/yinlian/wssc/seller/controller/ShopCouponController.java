package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.CouponGetTypeEnum;
import com.yinlian.Enums.CouponIssueTypeEnum;
import com.yinlian.Enums.CouponTypeEnum;
import com.yinlian.Enums.CouponUseTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.controller.CouponController;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.CriteriaCoupon;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/seller/ShopCoupon")
public class ShopCouponController {
	/**
	 * 日志输出的类
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(ShopCouponController.class);
	@Autowired 
	private CouponService couponService;
	@Autowired 
	private UserslevelService userslevelService;
	@Autowired
	private OperaterecordsService operaterecordsService ;
	SessionUser user=null;
/**
 * 添加优惠卷
 * @param name
 * @param facevalue
 * @param count
 * @param usetype
 * @param usetypeid
 * @param coupontype
 * @param mjprice
 * @param status
 * @param starttime
 * @param endtime
 * @param endday
 * @param userlevel
 * @param gettype
 * @param getcount
 * @return
 */
	@RequestMapping("/addCoupon")
	public @ResponseBody ReusltItem addCoupon(String name, String facevalue, String count, String usetype, String usetypeid, 
			String coupontype,String mjprice, String status, String starttime, String endtime, 
			String endday, String userlevel, String gettype, String getcount,String useplatform){
		ReusltItem item=new ReusltItem();
		try{
			Coupon coupon =new Coupon();
			
			coupon=checkParam(name, facevalue, count, usetype, usetypeid, coupontype, mjprice, 
					status, starttime, endtime, endday, userlevel, gettype, getcount,useplatform, "0", item);
			if(item.getCode()<0){
				return item;
			}
			if(couponService.insert(coupon)>0){
				item.setCode(0);
				item.setDesc("添加优惠卷成功!");
				logger.info(String.format("添加优惠卷成功! 编号:{0}", coupon.getCouponnum()));
				 SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "添加优惠卷页面", "/seller/ShopCoupon/addCoupon", "添加优惠卷");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加添加优惠卷操作记录出错! 异常信息:",
    								e, "/seller/ShopCoupon/addCoupon");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("添加优惠卷失败");
				logger.info(String.format("添加优惠卷失败! 名称:{0}", coupon.getCouponname()));
			}
			
		}catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("添加优惠卷异常："+e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Coupon, "添加优惠卷异常:",e,
					"/seller/ShopCoupon/addCoupon");
		}
		return item;
	}
	
	/**
	 * 编辑优惠卷
	 * @param id
	 * @param name
	 * @param facevalue
	 * @param count
	 * @param usetype
	 * @param usetypeid
	 * @param coupontype
	 * @param mjprice
	 * @param status
	 * @param starttime
	 * @param endtime
	 * @param endday
	 * @param userlevel
	 * @param gettype
	 * @param getcount
	 * @return
	 */
	@RequestMapping("/updateCoupon")
	public @ResponseBody ReusltItem updateCoupon(String id,String name, String facevalue, String count, String usetype, String usetypeid, 
			String coupontype,String mjprice, String status, String starttime, String endtime, 
			String endday, String userlevel, String gettype, String getcount,String useplatform){
		ReusltItem item=new ReusltItem();
		try{
             Coupon coupon =new Coupon();
			
			coupon=checkParam(name, facevalue, count, usetype, usetypeid, coupontype, mjprice, 
					status, starttime, endtime, endday, userlevel, gettype, getcount,useplatform, id, item);
			if(item.getCode()<0){
				return item;
			}
			if(couponService.updateByID(coupon)>0){
				item.setCode(0);
				item.setDesc("编辑优惠卷成功!");
				logger.info(String.format("编辑优惠卷成功! id：{0},编号:{1}", id,coupon.getCouponnum()));
				 SessionUser user=SessionState.GetCurrentUser();
	                //异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "编辑优惠卷页面", "/seller/ShopCoupon/updateCoupon", "编辑优惠卷");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"添加编辑优惠卷操作记录出错! 异常信息:",
	    								e, "/seller/ShopCoupon/updateCoupon");
	    					}
	    					
	    				}
	    			});
			}else{
				item.setCode(-200);
				item.setDesc("编辑优惠卷失败");
				logger.info(String.format("编辑优惠卷失败! id：{0},编号:{1}", id,coupon.getCouponnum()));
			}
		}catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
    			item.setDesc("编辑优惠卷异常："+e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Coupon, "编辑优惠卷异常:",e,
					"/seller/ShopCoupon/updateCoupon");
		}
		return item;
	}
	
	private Coupon checkParam(String name, String facevalue, String count, String usetype, String usetypeid, 
			String coupontype,String mjprice, String status, String starttime, String endtime, 
			String endday, String userlevel, String gettype, String getcount,String useplatform, String id,ReusltItem item
			)throws Exception{
		user=SessionState.GetCurrentUser();
		 Coupon coupon =new Coupon();
		 if (StringUtilsEX.IsNullOrWhiteSpace(name))
         {
			 item.setCode(-102);
			 item.setDesc("优惠卷名称不能为空");
             return null;
         }
         if (StringUtilsEX.ToFloat(facevalue) < 0)
         {
        	 item.setCode(-103);
        	 item.setDesc("优惠卷面值参数错误：" + facevalue);
             return null;
         }
         if (StringUtilsEX.ToInt(count) <= 0)
         {
        	 item.setCode (-104);
        	 item.setDesc ("优惠卷数量参数错误：" + count);
             return null;
         }
         //if (issustype.ToInt(-1) < 0)
         //{
         //    model.Code = -105;
         //    model.Desc = "发放类型参数错误：" + issustype;
         //    return null;
         //}
         String usetypename = "";
         if (StringUtilsEX.ToInt(usetype) < 0)
         {
        	 item.setCode(-106);
        	 item.setDesc("使用对象参数错误：" + usetype);
             return null;
         }
         else if (StringUtilsEX.ToInt(usetype) < CouponUseTypeEnum.通用.getValue())
         {
             switch (StringUtilsEX.ToInt(usetype))
             {
                 case 0: usetypename = "商品"; break;
                 case 1: usetypename ="分类" ; break;
                 case 2: usetypeid =String.valueOf(user.getShopid()); break;//所属店铺ID 默认为 1
                 default: break;
             }
             if (StringUtilsEX.ToInt(usetypeid) <= 0)
             {
            	 item.setCode(-107);
            	 item.setDesc(usetypename + "ID参数错误：" + usetypeid);
                 return null;
             }
         }

         if (StringUtilsEX.ToInt(coupontype) < 0)
         {
        	 item.setCode(-108);
        	 item.setDesc("优惠卷类型参数错误：" + coupontype);
             return null;
         }
         else if (StringUtilsEX.ToInt(coupontype) == CouponTypeEnum.金额限制.getValue())
         {
             if (StringUtilsEX.ToFloat(mjprice) < 0)
             {
            	 item.setCode (-109);
            	 item.setDesc ("满减金额参数错误：" + mjprice);
                 return null;
             }
         }
         if (StringUtilsEX.ToInt(status) < 0)
         {
        	 item.setCode(-110);
        	 item.setDesc("优惠卷状态参数错误：" + status);
             return null;
         }
         if (StringUtilsEX.IsNullOrWhiteSpace(starttime))
         {
        	 item.setCode(-111);
        	 item.setDesc("发放时间参数错误：" + starttime);
             return null;

         }
         if (StringUtilsEX.IsNullOrWhiteSpace(endtime))
         {
        	 item.setCode (-112);
        	 item.setDesc ("过期时间参数错误：" + endtime);
             return null;
         }
         if (!StringUtilsEX.IsNullOrWhiteSpace(endday))
         {
             if (StringUtilsEX.ToInt(endday) < 0)
             {
            	 item.setCode (-113);
            	 item.setDesc ("有效期参数错误：" + endday);
                 return null;
             }
         }
         if (StringUtilsEX.ToInt(userlevel) < 0)
         {
        	 item.setCode (-114);
        	 item.setDesc ("用户等级参数错误：" + userlevel);
             return null;
         }
         if (StringUtilsEX.ToInt(gettype) < CouponGetTypeEnum.买家领取.getValue() || 
        		 StringUtilsEX.ToInt(gettype) > CouponGetTypeEnum.抽奖专用.getValue())
         {
        	 item.setCode (-115);
        	 item.setDesc ("领用方式参数错误：" + gettype);
             return null;
         }
         if (StringUtilsEX.ToInt(getcount) < 0)
         {
        	 item.setCode (-116);
        	 item.setDesc ("每人限领参数错误：" + getcount);
             return null;
         }
         
         if (StringUtilsEX.IsNullOrWhiteSpace(useplatform)) {
 			item.setCode(-117);
 			item.setDesc("请选择优惠劵使用平台");
 			return null;
 		}
         if (StringUtilsEX.ToInt(id) < 0)
         {
        	 item.setCode (-101);
        	 item.setDesc ("优惠卷ID参数错误：" + id);
             return null;
         }
         if (StringUtilsEX.ToInt(id) == 0)
         {
             coupon.setIsdel(false); 
             coupon.setCreatetime(new Date());
             coupon.setCreateuserid(user.getUserId());    //创建人ID 默认为1
             coupon.setShopid(user.getShopid());          //所属店铺ID 默认为1
             coupon.setCouponnum(ProductNumUtil.getCouponNum()); //自动生成优惠卷编号
         }
         else
         {
             coupon = couponService.getByID(StringUtilsEX.ToInt(id));
             if (coupon == null)
             {
                 logger.error(String.format("修改优惠卷错误，根据ID未能检索到数据.ID:{0}", id), "Coupon/updateCoupon");
                 item.setCode (-401);
                 item.setDesc ("根据ID未能检索到数据");
                 return null;
             }
         }
         coupon.setCouponname(name.trim());
         coupon.setFacevalue(StringUtilsEX.ToFloat(facevalue)); 
         coupon.setConponcount(StringUtilsEX.ToInt(count));
         coupon.setCouponusetype(StringUtilsEX.ToInt(usetype));
         coupon.setCouponissuetype(CouponIssueTypeEnum.店铺.getValue());
         if(StringUtilsEX.ToInt(usetypeid)>0){
 			coupon.setUsetypeid(StringUtilsEX.ToInt(usetypeid));
 		}else{
 			coupon.setUsetypeid(null);
 		}
 		
 		coupon.setCoupontype(StringUtilsEX.ToInt(coupontype));
 		if(StringUtilsEX.ToFloat(mjprice)>0){
 			coupon.setFullreductionvalue(StringUtilsEX.ToFloat(mjprice));
 		}else{
 			coupon.setFullreductionvalue(null);
 		}
 		coupon.setStatus(StringUtilsEX.ToInt(status));
 		if(StringUtilsEX.ToInt(endday)>0){
 			coupon.setEndday(StringUtilsEX.ToInt(endday));
 		}else{
 			coupon.setEndday(0);
 		}
         coupon.setGetuserlevel(StringUtilsEX.ToInt(userlevel));
         coupon.setProvidetime(StringUtilsEX.ToDate(starttime)); 
         coupon.setEndtime(StringUtilsEX.ToDate(endtime));
         coupon.setGettype(StringUtilsEX.ToInt(gettype)); 
         coupon.setGetcount(StringUtilsEX.ToInt(getcount)); 	
         coupon.setUseplatform(useplatform);
		 return coupon;
	}
	/**
	 * 删除优惠卷
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCoupon")
	public @ResponseBody ReusltItem deleteCoupon(String id){
		ReusltItem item=new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setData("优惠卷ID参数错误，id："+id);
				return item;
			}
			Coupon coupon=new Coupon();
			coupon.setId(StringUtilsEX.ToInt(id));
			coupon.setDeluserid(user.getUserId()); //操作人ID 默认为1
			coupon.setDeltime(new Date());
			if(couponService.deleteCoupon(coupon)>0){
				item.setCode(0);
				item.setDesc("删除优惠卷成功");
				logger.info(String.format("删除优惠卷成功! id:{0}", id));
				 SessionUser user=SessionState.GetCurrentUser();
	                //异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "删除优惠卷页面", "/seller/ShopCoupon/deleteCoupon", "删除优惠卷");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"添加删除优惠卷操作记录出错! 异常信息:",
	    								e, "/seller/ShopCoupon/deleteCoupon");
	    					}
	    					
	    				}
	    			});
			}else{
				item.setCode(-200);
				item.setDesc("删除优惠卷失败");
				logger.warn(String.format("删除优惠卷失败! id:{0}", id));
			}
		}catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("删除优惠卷异常："+e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Coupon, "删除优惠卷异常:",e,
					"/seller/ShopCoupon/deleteCoupon");
		}
		return item;
	}

	/**
	 * 获取优惠卷列表
	 * @param num
	 * @param name
	 * @param ctype
	 * @param usetype
	 * @param startf
	 * @param startt
	 * @param endf
	 * @param endt
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getShopCouponList")
	public @ResponseBody ReusltItem getShopCouponList(String num, String name,
			String ctype, String usetype, String startf, String startt,
			String endf, String endt, String page, String size){
		ReusltItem item=new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			CriteriaActivity cActivity = new CriteriaActivity();
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cActivity.setNum(num);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
				cActivity.setName(name);
			}
			if (StringUtilsEX.ToInt(ctype) >= 0) {
				cActivity.setCouponType(StringUtilsEX.ToInt(ctype));
			}
			if (StringUtilsEX.ToInt(usetype) >= 0) {
				cActivity.setUserType(StringUtilsEX.ToInt(usetype));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(startf)) {
				cActivity.setStartFrom(StringUtilsEX.ToShortDate(startf));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(startt)) {
				cActivity.setStartTo(StringUtilsEX.ToShortDate(startt));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endf)) {
				cActivity.setEndFrom(StringUtilsEX.ToShortDate(endf));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endt)) {
				cActivity.setEndTo(StringUtilsEX.ToShortDate(endt));
			}
            cActivity.setShopid(user.getShopid());  //所属店铺id 默认为1

			
			PageBean pBean=couponService.getList(cActivity, StringUtilsEX.ToInt(page), 
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		}catch(Exception e){
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取店铺优惠卷异常："+e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Coupon, "获取店铺优惠卷异常:",e,
					"/seller/ShopCoupon/getShopCouponList");
		}
		return item;
	}
	
	/**
	 * 编辑优惠卷状态  公开 不公开
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public @ResponseBody ReusltItem updateStatus(String id,String status) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("id参数错误，id:" + id);
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-102);
				item.setDesc("优惠卷状态参数错误，status:" + status);
				return item;
			}
			if(couponService.updateStatus(StringUtilsEX.ToInt(status), StringUtilsEX.ToInt(id))>0){
				item.setCode(0);
				item.setDesc("编辑优惠卷状态成功");
				logger.info(String.format("编辑优惠卷状态成功! id:{0}", id));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改优惠卷状态页面", "/seller/ShopCoupon/updateStatus", "编辑优惠卷状态  公开 不公开");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加编辑优惠卷状态  公开 不公开操作记录出错! 异常信息:",
    								e, "/seller/ShopCoupon/updateStatus");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("编辑优惠卷状态失败");
				logger.warn(String.format("编辑优惠卷状态失败! id:{0}", id));
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("编辑优惠卷状态异常："+e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Coupon, "编辑优惠卷状态异常:",e,
					"/seller/ShopCoupon/updateStatus");
		}
		return item;
	}
	/**
	 * 模糊检索优惠卷
	 * @param name
	 * @return
	 */
	@RequestMapping("/getCouponStartwithName")
	public @ResponseBody ReusltItem getCouponStartwithName(String name){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
//			int shopid=1;  //所属店铺ID 默认为1
			item.setData(couponService.getCouponStartwithName(user.getShopid(), name));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("编辑优惠卷状态异常："+e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Coupon, "模糊检索优惠卷异常:",e,
					"/seller/ShopCoupon/getCouponStartwithName");
		}
		return item;
	}
	/**
	 * 获取优惠卷领用列表
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getByCouponID")
	public @ResponseBody ReusltItem getByCouponID(String id, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("优惠卷id参数错误，id:" + id);
				return item;
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaCoupon cCoupon = new CriteriaCoupon();
			cCoupon.setCouponid(StringUtilsEX.ToInt(id));

			PageBean pBean = couponService.getByCouponIDPage(cCoupon,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取优惠卷领用列表异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Coupon,"获取优惠卷领用列表异常， 异常信息：{0}",
							e, "/seller/ShopCoupon/getByCouponID");
		}
		return item;
	}

	/**
	 * 获取会员列表
	 * 
	 * @param couponid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getMenberList")
	public @ResponseBody ReusltItem getMenberList(String couponid,
			String username, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(couponid) <= 0) {
				item.setCode(-101);
				item.setDesc("优惠卷id参数错误，id:" + couponid);
				return item;
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误");
				return item;
			}
			CriteriaCoupon cCoupon = new CriteriaCoupon();

			cCoupon.setCouponid(StringUtilsEX.ToInt(couponid));
			if (!StringUtilsEX.IsNullOrWhiteSpace(username)) {
				cCoupon.setName(username);
			}
			PageBean pBean = couponService.getMenberListPage(cCoupon,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
    			item.setDesc("获取会员列表异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Coupon,"获取会员列表异常， 异常信息：{0}", e,
					"/seller/ShopCoupon/getMenberList");
		}
		return item;
	}

	@RequestMapping("/addUClist")
	public @ResponseBody ReusltItem addUClist(String couponid, String userids) {
		ReusltItem item = new ReusltItem();
		try {
			List<Integer> idList = new ArrayList<Integer>();
			if (StringUtilsEX.ToInt(couponid) <= 0) {
				item.setCode(-101);
				item.setDesc("优惠卷id参数错误，id:" + couponid);
				return item;
			}
			for (String id : userids.split(",")) {
				if (StringUtilsEX.ToInt(id) <= 0) {
					item.setCode(-101);
					item.setDesc("会员id参数错误，userid:" + id);
					return item;
				}
				idList.add(StringUtilsEX.ToInt(id));
			}
			if (couponService.addUCouponList(idList,
					StringUtilsEX.ToInt(couponid)) > 0) {
				item.setCode(0);
				item.setDesc("系统优惠卷添加赠送会员成功");
				LogHandle.info(LogType.Coupon, MessageFormat.format(
						"系统优惠卷添加赠送会员成功! 优惠卷id：{0},用户ID集合:{1}", couponid,
						userids), "ShopCoupon/addUClist");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "系统优惠卷添加赠送会员页面", "/seller/ShopCoupon/addUClist", "系统优惠卷添加赠送会员");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加系统优惠卷添加赠送会员操作记录出错! 异常信息:",
    								e, "/seller/ShopCoupon/addUClist");
    					}
    					
    				}
    			});
			} else {
				
				item.setCode(-200);
				item.setDesc("系统优惠卷添加赠送会员失败");
				LogHandle.info(LogType.Coupon, MessageFormat.format(
						"系统优惠卷添加赠送会员失败! 优惠卷id：{0},用户ID集合:{1}", couponid,
						userids), "ShopCoupon/addUClist");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("系统优惠卷添加赠送会员异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(
					LogType.Coupon,"系统优惠卷添加赠送会员异常， 异常信息：{0}",
							e, "/seller/ShopCoupon/addUClist");
		}
		return item;
	}
	/**
	 * 获取会员等级下拉框
	 * @return
	 */
	@RequestMapping("/getLevelList")
	public @ResponseBody ReusltItem getLevelList(){
		ReusltItem item = new ReusltItem();
		try {
			List<Userslevel> list = userslevelService.getlevelList();
			if (list.size()>0) {
				item.setCode(0);
				item.setData(list);
			}
			
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取添加一条回复的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,"获取添加一条回复的信息出错! 异常信息:{0}",
					e, "/seller/ShopCoupon/getLevelList");
		}
		
		return item;
	}
}
