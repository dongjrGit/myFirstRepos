package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.ApplyforcancelorderService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaSellerSh;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;
/**
 * 卖家售后
 * @author admin
 *
 */
@RestController
@RequestMapping("/seller/sh")
public class ShopShouhouServiceController {

	 /**
     * 日志输出的类
     */
	private static final Logger logger = LoggerFactory
			.getLogger(ShopShouhouServiceController.class);

	@Autowired
	private ApplyforcancelorderService  applyforcancelorderService;
	@Autowired
	private OperaterecordsService     operaterecordsService ;
   /**
    * 得到退款换货列表
    * @param order
    * @param time
    * @param page
    * @param size
    * @return
    */
    @RequestMapping("/getReturnTradeList")
    public @ResponseBody ReusltItem getReturnTradeList(String order,String shopid,String timef,String timet,String page,String size){
    	 ReusltItem item = new ReusltItem();
    	 try{
    		 if (StringUtilsEX.ToInt(page) <= 0) {
  	            item.setCode(-101);
  	            item.setDesc("分页参数错误，pageindex：" + page);
  	            return item;
  	        }
  	        if (StringUtilsEX.ToInt(size) <= 0) {
  	            item.setCode(-102);
  	            item.setDesc("分页参数错误，pageindex：" + size);
  	            return item;
  	        }
  	        if(order.equals("请输入订单号")){
  	        	order="";
  	        }
  	        CriteriaSellerSh criteria=new CriteriaSellerSh();
  	        criteria.setCode(order);
  	        criteria.setShopid(StringUtilsEX.ToInt(shopid));
  	        criteria.setDatef(StringUtilsEX.ToShortDate(timef));
	        criteria.setDatet(StringUtilsEX.ToShortDate(timet));
  	        criteria.setType(3);
    		PageBean  pageBean=applyforcancelorderService.selectSellerThListByPage(criteria,StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
    		item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
    	 }
    	 catch(Exception e){
    		 item.setCode(-900);
 			item.setDesc("获取退款和退货列表异常：" + e.getMessage());
 			LogHandle.error(LogType.Coupon,
 					MessageFormat.format("获取退款和退货列表异常! 异常信息:{0}", e.getMessage()),
 					"sh/getReturnTradeList");
    	 }
    	 return item;
    }
    
    /**
     * 删除退款退货记录
     * @param orderid
     * @return
     */
    @RequestMapping("/delReturnTrade")
    public @ResponseBody ReusltItem delReturnTrade(String id){
    	 ReusltItem item = new ReusltItem();
    	 try {
    		 if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
   	            item.setCode(-101);
   	            item.setDesc("id不能为空");
   	            return item;
   	        }
    		 if(applyforcancelorderService.del(StringUtilsEX.ToInt(id))>0){
 				item.setCode(0);
 				item.setDesc("删除退款退货记录成功");
 				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), " 删除退款退货记录页面", "/seller/sh/delReturnTrade", " 删除退款退货记录");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加 删除退款退货记录操作记录出错! 异常信息:",
    								e, "/seller/sh/delReturnTrade");
    					}
    					
    				}
    			});
 			}else{
 				item.setCode(-200);
 				item.setDesc("删除退款退货记录失败");
 			}
		} catch (Exception e) {
			 item.setCode(-900);
	 			item.setDesc("删除退款退货记录异常：" + e.getMessage());
	 			LogHandle.error(LogType.Coupon,
	 					MessageFormat.format("删除退款退货记录异常! 异常信息:{0}", e.getMessage()),
	 					"sh/delReturnTrade");
		}
    	 return item;
    }
	
	/**
	 * 添加备注
	 * 
	 * @param id
	 * @param note
	 * @return
	 */
	@RequestMapping("/editnote")
	public @ResponseBody ReusltItem editNote(String id, String note) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-101);
				item.setDesc("id不能为空");
				return item;
			}
			if (applyforcancelorderService.editNote(StringUtilsEX.ToInt(id), note) > 0) {
				item.setCode(0);
				item.setDesc("保存成功!");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), " 添加备注页面", "/seller/sh/editnote", "添加备注");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加添加备注操作记录出错! 异常信息:",
    								e, "/seller/sh/editnote");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("保存失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("保存记录异常：" + e.getMessage());
			LogHandle.error(LogType.Sys, MessageFormat.format("保存异常! 异常信息:{0}", e.getMessage()), "sh/editnote");
		}
		return item;
	}

	/**
	 * 查看 备注
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getnote")
	public @ResponseBody ReusltItem getNote(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(id)) {
				item.setCode(-101);
				item.setDesc("id不能为空");
				return item;
			}
			item.setCode(0);
			item.setData(applyforcancelorderService.getNote(StringUtilsEX.ToInt(id)));

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc(" 查看 备注异常：" + e.getMessage());
			LogHandle.error(LogType.Sys, MessageFormat.format(" 查看 备注异常! 异常信息:{0}", e.getMessage()), "sh/getnote");
		}
		return item;
	}

}
