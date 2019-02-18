package com.yinlian.wssc.platform.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.CouponShopDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.LotteryParam;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.LotteryDictionaryService;
import com.yinlian.wssc.web.service.LotteryParamService;
import com.yinlian.wssc.web.service.LotteryService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 抽奖规则的控制类
 * LotteryRulePlatformController.java
 * @author Administrator
 * @version $Id: LotteryRulePlatformController.java, v 0.1 2016年5月11日 上午10:28:13 Administrator Exp $
 */

@RestController
@RequestMapping("/platform/litteryrule")
public class LotteryRulePlatformController {
    @Autowired
    private LotteryService           lotteryService;
    @Autowired
    private LotteryDictionaryService lotteryDictionaryService;
    @Autowired
    private LotteryParamService      lotteryParamService;
    @Autowired
    private CouponService            couponService;
    @Autowired
    private SpuService               spuService;
    
    SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
           
    /**
     * 分页查询抽奖规则
     * 
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryAll")
    public @ResponseBody ReusltItem queryAll(String page, String size) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(page) <= 0) {
                item.setCode(-101);
                item.setDesc("当前页参数错误page" + page);
            }
            if (StringUtilsEX.ToInt(size) <= 0) {
                item.setCode(-102);
                item.setDesc("每页记录数参数错误size" + size);
            }

            Criteria criteria = new Criteria();
            PageBean pageBean = lotteryDictionaryService.queryAllByCriteria(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询抽奖规则异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "分页查询抽奖规则异常! 异常信息:", e,
                "/platform/litteryrule/queryAll");
        }
        return item;
    }

    /**
     * 根据id编辑规则
     * @param id
     * @param name
     * @param description
     * @param probability
     * @return
     */
    @RequestMapping("/updatLotteryRuleById")
    public @ResponseBody ReusltItem updatLotteryRuleById(String id, String name,
                                                         String description, String probability,
                                                         String value,String mark) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            int result = lotteryDictionaryService.updatLotteryRuleById(StringUtilsEX.ToInt(id),
                name, description, probability, value,mark);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
    			//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "lottery_rule_add.jsp", "/platform/litteryrule/updatLotteryRuleById", "根据id编辑规则");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id编辑规则操作记录出错! 异常信息:",
    								e, "/platform/litteryrule/updatLotteryRuleById");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("根据id编辑规则异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "根据id编辑规则异常! 异常信息:", e,
                "/platform/litteryrule/updatLotteryRuleById");
        }
        return item;
    }

    /**
     * 添加规则
     * @param id
     * @param name
     * @param description
     * @param probability
     * @return
     */
    @RequestMapping("/addLotteryRule")
    public @ResponseBody ReusltItem addLotteryRule(String name, String description,
                                                   String probability, String value,String mark) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            int result = lotteryDictionaryService.addLotteryRule(name, description, probability,
                value,mark);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
    			//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "lottery_rule_add.jsp", "/platform/litteryrule/addLotteryRule", "添加规则");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加规则操作记录出错! 异常信息:",
    								e, "/platform/litteryrule/addLotteryRule");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("添加失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加规则异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "添加规则异常! 异常信息:", e,
                "/platform/litteryrule/addLotteryRule");
        }
        return item;
    }

    /**
     * 分页查询中奖名单
     * 
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryLotteryAll")
    public @ResponseBody ReusltItem queryLotteryAll(String page, String size) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(page) <= 0) {
                item.setCode(-101);
                item.setDesc("当前页参数错误page" + page);
            }
            if (StringUtilsEX.ToInt(size) <= 0) {
                item.setCode(-102);
                item.setDesc("每页记录数参数错误size" + size);
            }

            Criteria criteria = new Criteria();
            PageBean pageBean = lotteryService.queryLotteryAllByCriteria(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询中奖名单异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "分页查询中奖名单异常! 异常信息:", e,
                "/platform/litteryrule/queryLotteryAll");
        }
        return item;
    }

    /**
     * 删除中奖人
     * 
     * @param id
     * @return
     */
    @RequestMapping("/delLottery")
    public @ResponseBody ReusltItem delLottery(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            int result = lotteryService.delLottery(StringUtilsEX.ToInt(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
    			//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "lottery.jsp", "/platform/litteryrule/delLottery", "删除中奖人");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除中奖人操作记录出错! 异常信息:",
    								e, "/platform/litteryrule/delLottery");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除中奖人异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
             LogHandle.error(LogType.Configset,
                    "删除中奖人异常! 异常信息:", e,
                    "/platform/litteryrule/delLottery");
        }
        return item;
    }

    /**
     * 获取奖项信息
     *@return
     */
    @RequestMapping("/queryLotteryParams")
    public @ResponseBody ReusltItem queryLotteryParams() {
        ReusltItem item = new ReusltItem();
        try {
            List<LotteryParam> list = lotteryParamService.selectList();
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取奖项信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
             LogHandle.error(LogType.Configset,
                "获取奖项信息异常! 异常信息:", e,
                "/platform/litteryrule/queryLotteryParams");
        }
        return item;
    }

    /**
     * 编辑信息异常
     *@param id
     *@param type
     *@param name
     *@param description
     *@param value
     *@return
     */
    @RequestMapping("/edit")
    public @ResponseBody ReusltItem edit(String id,String name,String ruleid, String description,
                                         String value, String groupid) {
        ReusltItem item = new ReusltItem();
        LotteryParam param = new LotteryParam();

        try {
        	user = SessionState.GetCurrentUser();
        
          /*  if (StringUtils.isBlanck(name)) {
                item.setCode(-102);
                item.setDesc("请完善奖项名称");
                return item;
            }*/
            param.setDescription(description);
            param.setName(name);
            param.setRuleid(StringUtilsEX.ToInt(ruleid));
            if (StringUtils.isNotNull(value)) {
                param.setValue(Integer.valueOf(value));
            }
            if (StringUtils.isNotNull(groupid)) {
                param.setGroupid(Integer.valueOf(groupid));
                Coupon  coupon=couponService.getByID(Integer.valueOf(groupid));
                param.setName(coupon.getCouponname());
            }
            if (StringUtils.isBlanck(id)) {
                int code = lotteryParamService.insert(param);
                if (code > 0) {
                    item.setCode(0);
                    item.setDesc("添加成功");
                    //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "lottery_param_edit.jsp", "/platform/litteryrule/edit", "添加奖项信息");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加奖项信息操作记录出错! 异常信息:",
        								e, "/platform/litteryrule/edit");
        					}
        					
        				}
        			});
                } else if (code == -1) {
                    item.setDesc("该类型奖项已经存在,请先删除在添加新奖项");
                    item.setCode(-103);
                } else {
                    item.setDesc("添加失败");
                    item.setCode(-200);
                }
            } else {
                param.setId(Integer.valueOf(id));
                int code = lotteryParamService.update(param);
                if (code > 0) {
                    item.setCode(0);
                    item.setDesc("修改成功");
                  //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "lottery_param_edit.jsp", "/platform/litteryrule/edit", "修改奖项信息");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改奖项信息操作记录出错! 异常信息:",
        								e, "/platform/litteryrule/edit");
        					}
        					
        				}
        			});
                } else {
                    item.setDesc("修改失败");
                    item.setCode(-200);
                }
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("编辑奖项信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
           LogHandle.error(LogType.Configset,
                "编辑奖项信息异常! 异常信息:", e, "/platform/litteryrule/edit");
        }
        return item;
    }

    /**
     * 获取代金卷
     *@return
     */
    @RequestMapping("/queryGroup")
    public @ResponseBody ReusltItem queryGroup() {
        ReusltItem item = new ReusltItem();
        try {
            CouponShopDto dto = couponService.getLotteryCoupon();
            item.setCode(0);
            item.setData(dto);
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取代金卷信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "获取代金卷信息异常! 异常信息:", e,
                "/platform/litteryrule/queryGroup");
        }
        return item;
    }

    /**
     * 删除奖项信息
     *@param id
     *@return
     */
    @RequestMapping("/deleteParamById")
    public @ResponseBody ReusltItem deleteParamById(Integer id) {
        ReusltItem item = new ReusltItem();
        try {
            int result = lotteryParamService.deleteById(id);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "lottery_param.jsp", "/platform/litteryrule/deleteParamById", "删除奖项");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除奖项操作记录出错! 异常信息:",
    								e, "/platform/litteryrule/deleteParamById");
    					}
    					
    				}
    			});
            } else {
                item.setDesc("删除失败");
                item.setCode(-200);
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除奖项异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,"删除奖项异常! 异常信息:", e,
                "/platform/litteryrule/deleteParamById");
        }
        return item;
    }
    
    
    /**
     * 删除抽奖规则
     *@param id
     *@return
     */
   @RequestMapping("/delLotterRule")
   public @ResponseBody ReusltItem delLotterRule(Integer id) {
       ReusltItem item = new ReusltItem();
       try {
           int result = lotteryDictionaryService.deleteById(id);
           if (result > 0) {
               item.setCode(0);
               item.setDesc("删除成功");
             //异步操作 不影响正常流程
               ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
   			cachedThreadPool.execute(new Runnable() {
   				@Override
   				public void run() {
   					try{
   						operaterecordsService.insertOperaterecords(
                           		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                           		user.getUserId(), user.getLoginName(), "lottery_rule.jsp", "/platform/litteryrule/delLotterRule", "删除抽奖规则");
   					}
   					catch(Exception e){
   						LogHandle.error(LogType.OperateRecords,"删除抽奖规则操作记录出错! 异常信息:",
   								e, "/platform/litteryrule/delLotterRule");
   					}
   					
   				}
   			});
           } else {
               item.setDesc("删除失败");
               item.setCode(-200);
           }
       } catch (Exception e) {
    	   item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("删除抽奖规则异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
           LogHandle.error(LogType.Configset,"删除抽奖规则异常! 异常信息:", e,
               "/platform/litteryrule/delLotterRule");
       }
       return item;
   }
    
    /**
     * 获取优惠卷
     *@param id
     *@return
     */
    @RequestMapping("/getCouponList")
    public @ResponseBody ReusltItem getCouponList() {
        ReusltItem item = new ReusltItem();
        try {
        	List<CouponShopDto> list=couponService.getLotteryCouponList();
			item.setData(list);
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取优惠卷异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
	             LogHandle.error(LogType.Configset,"获取优惠卷异常! 异常信息:", e,
	                "/platform/litteryrule/getCouponList");

		}
        return item;
    }
}
