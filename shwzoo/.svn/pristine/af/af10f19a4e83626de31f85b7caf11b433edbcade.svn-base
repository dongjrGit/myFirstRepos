/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.po.FreightAttr;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.service.FreightAttrService;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaFreightAttr;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 卖家的店铺运费模板控制类
 * ShopFreightController.java
 * @author Administrator
 * @version $Id: ShopFreightController.java, v 0.1 2016年3月17日 下午6:09:39 Administrator Exp $
 */
@Controller
@RequestMapping("/seller/freight")
public class ShopFreightController {
    /**
     * 日志类
     */
    private static final Logger logger = LoggerFactory.getLogger(ShopFreightController.class);
    @Autowired
    private ShopService         shopService;
    @Autowired
    private FreightService      freightService;
    @Autowired
    private FreightAttrService  freightAttrService;
    @Autowired
   	private OperaterecordsService operaterecordsService ;

    SessionUser                 user   = null;

    /**
     * 根据userid查询该用户下的所有店铺
     * 
     * @param userid 用户id
     * @return
     */
    @RequestMapping("/queryListByUser")
    public @ResponseBody ReusltItem queryListByUser(String userid) {
        ReusltItem item = new ReusltItem();
        try {
            //        if (StringUtilsEX.ToInt(userid) < 0) {
            //            item.setCode(-101);
            //            item.setDesc("参数userid错误");
            //            return item;
            //        }
            user = SessionState.GetCurrentUser();
            List<Shop> list = shopService.selectListByUserId(user.getUserId());
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询用户下所有店铺出现的异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"获取添加一条回复的信息出错! 异常信息:{0}",
					e, "/seller/freight/queryListByUser");
        }
        return item;
    }

    /**
     * 根据店铺id查询模板分页信息
     * 
     * @param shopid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryFreightListByCriteria")
    public @ResponseBody ReusltItem queryFreightListByCriteria(String shopid, String page,
                                                               String size) {
        ReusltItem item = new ReusltItem();
        //        if (StringUtilsEX.ToInt(shopid) < 0) {
        //            item.setCode(-101);
        //            item.setDesc("参数shopid错误");
        //            return item;
        //        }
        user = SessionState.GetCurrentUser();
        if (StringUtilsEX.ToInt(page) <= 0) {
            item.setCode(-102);
            item.setDesc("分页参数错误，pageindex：" + page);
            return item;
        }
        if (StringUtilsEX.ToInt(size) <= 0) {
            item.setCode(-103);
            item.setDesc("分页参数错误，pageindex：" + size);
            return item;
        }
        PageBean pageBean;
        try {
            Criteria criteria = new Criteria();
            criteria.setShopid(user.getShopid());
            pageBean = freightService.selectListByPage(criteria, StringUtilsEX.ToInt(page),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("根据店铺id查询模板分页信息：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"根据店铺id查询模板分页! 异常信息:{0}",
					e, "/seller/freight/queryFreightListByCriteria");
        }
        return item;
    }

    /**
     * 添加店铺模板
     * 
     * @param shopid
     * @param name
     * @param isExemptionPostage
     * @param pricingModel
     * @param transportModel
     * @param setNum
     * @return
     */
    @RequestMapping("/add")
    public @ResponseBody ReusltItem add(String shopid, String name, String isExemptionPostage,
                                        String pricingModel, String transportModel, String setNum,String iscondition,
                                        String description) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            checkParam(shopid, name, isExemptionPostage, pricingModel, transportModel, setNum, iscondition,item);
            if (item.getCode() < 0) {
                return item;
            }
            int result = freightService.insert(user.getShopid(), name,
                StringUtilsEX.ToInt(isExemptionPostage), StringUtilsEX.ToInt(pricingModel),
                StringUtilsEX.ToInt(transportModel), StringUtilsEX.ToInt(setNum),StringUtilsEX.ToInt(iscondition), description);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "添加店铺模板页面", "/seller/freight/add", "添加店铺模板");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加添加店铺模板操作记录出错! 异常信息:",
    								e, "/seller/freight/add");
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
            	item.setDesc("添加店铺模板异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"添加店铺模板! 异常信息:{0}",
					e, "/seller/freight/queryFreightListByCriteria");
        }
        return item;
    }

    /**
     * 校验参数
     * @param shopid
     * @param name
     * @param isExemptionPostage
     * @param pricingModel
     * @param transportModel
     * @param setNum
     * @param item
     */
    private void checkParam(String shopid, String name, String isExemptionPostage,
                            String pricingModel, String transportModel, String setNum,String iscondition,
                            ReusltItem item) {
        //        if (StringUtilsEX.ToInt(shopid) < 0) {
        //            item.setCode(-101);
        //            item.setDesc("参数shopid错误");
        //        }
        if (!StringUtils.isNotNull(name)) {
            item.setCode(-102);
            item.setDesc("请输入模板名称");
        }
       
        if(isExemptionPostage.equals("1")){
        	 if (!StringUtils.isNotNull(setNum)) {
                 item.setCode(-103);
                 item.setDesc("请输入计价方式数值");
             }
             if (!StringUtils.isDigit(setNum)) {
             	item.setCode(-103);
             	item.setDesc("计价方式数值请填不小于零的数字");
             }
             if (StringUtilsEX.ToInt(iscondition) < 0) {
                 item.setCode(-104);
                 item.setDesc("请选择是否小于数值");
             }
        }
        if (StringUtilsEX.ToInt(isExemptionPostage) < 0) {
            item.setCode(-104);
            item.setDesc("请选择是否包邮");
        }
        if (StringUtilsEX.ToInt(pricingModel) < 0) {
            item.setCode(-105);
            item.setDesc("请选择是计件方式");
        }
        if (StringUtilsEX.ToInt(transportModel) < 0) {
            item.setCode(-106);
            item.setDesc("请选择运送方式");
        }
    }

    /**
     * 设为默认的
     * 
     * @param id
     * @param shopid
     * @return
     */
    @RequestMapping("/setDefault")
    public @ResponseBody ReusltItem setDefault(String id, String shopid) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("模板id错误");
                return item;
            }
            if (StringUtilsEX.ToInt(shopid) < 0) {
                item.setCode(-102);
                item.setDesc("店铺id错误");
                return item;
            }

            int result = freightService.updateStatusDefault(StringUtilsEX.ToInt(id),
                user.getShopid());
            if (result > 0) {
                item.setCode(0);
                item.setDesc("设置成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改模板为默认模板页面", "/seller/freight/setDefault", "修改模板为默认模板");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改模板为默认模板操作记录出错! 异常信息:",
    								e, "/seller/freight/setDefault");
    					}
    					
    				}
    			});
                
            } else {
                item.setCode(-200);
                item.setDesc("设置失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("设为默认的异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"设为默认的! 异常信息:{0}",
					e, "/seller/freight/setDefault");
        }
        return item;
    }

    /**
     * 根据id查询模板信息
     * 
     * @param id
     * @return
     */
    @RequestMapping("/qeuryFreightById")
    public @ResponseBody ReusltItem qeuryFreightById(String id) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("模板id错误");
            return item;
        }
        try {
            Freight freight = freightService.selectById(StringUtilsEX.ToInt(id));
            item.setCode(0);
            item.setData(freight);
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("设为默认的异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"根据id查询模板信息! 异常信息:{0}",
					e, "/seller/freight/qeuryFreightById");
        }
        return item;
    }

    /**
     * 获取模板详细信息
     * 
     * @param id
     * @return
     */
    @RequestMapping("/qeuryFreightAttrById")
    public @ResponseBody ReusltItem qeuryFreightAttrById(String id) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("模板id错误");
            return item;
        }
        try {
            List<FreightAttr> list = freightAttrService.selectByFreightId(StringUtilsEX.ToInt(id));
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取模板详细信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"获取模板详细信息! 异常信息:{0}",
					e, "/seller/freight/qeuryFreightAttrById");
        }
        return item;
    }

    /**
     * 修改模板
     * 
     * @param id
     * @param shopid
     * @param name
     * @param isExemptionPostage
     * @param pricingModel
     * @param transportModel
     * @param setNum
     * @param json_attrList
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody ReusltItem update(String id, String shopid, String name,
                                           String isExemptionPostage, String pricingModel,
                                           String transportModel, String setNum,String iscondition,
                                           String json_attrList, String description, String areas,
                                           String firstcount, String firstprice, String elsecount,
                                           String elseprice, String attrid) {
        ReusltItem item = new ReusltItem();
        try {
            user = SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("模板id错误");
                return item;
            }
            checkParam(shopid, name, isExemptionPostage, pricingModel, transportModel, setNum, iscondition, item);
            if (item.getCode() < 0) {
                return item;
            }
            List<FreightAttr> attrs = new ArrayList<FreightAttr>();
           if (StringUtils.isNotNull(json_attrList)) {
        	   String[] errey = json_attrList.split("\\|");
               for (String string : errey) {
                   String[] array = string.split(";");
                   String areasString = array[0];
                   areas = areasString.substring(areasString.indexOf(":") + 2,
                       areasString.length() - 1);
                   String firstcountsString = array[1];
                   firstcount = firstcountsString.substring(firstcountsString.indexOf(":") + 2,
                       firstcountsString.length() - 1);
                   String firstpriceString = array[2];
                   firstprice = firstpriceString.substring(firstpriceString.indexOf(":") + 2,
                       firstpriceString.length() - 1);
                   String elsecountString = array[3];
                   elsecount = elsecountString.substring(elsecountString.indexOf(":") + 2,
                       elsecountString.length() - 1);
                   String elsepriceString = array[4];
                   elseprice = elsepriceString.substring(elsepriceString.indexOf(":") + 2,
                       elsepriceString.length() - 1);
                   String attridString = array[5];
                   attrid = attridString.substring(attridString.indexOf(":") + 2,
                       attridString.length() - 1);
                   String statussString = array[5];
                   String status = statussString.substring(statussString.indexOf(":") + 2,
                       statussString.length() - 1);
                   FreightAttr freightAttr = new FreightAttr();
                   freightAttr.setId(StringUtilsEX.ToInt(attrid));
                   freightAttr.setAreas(areas);
                   freightAttr.setFirstcount(StringUtilsEX.ToInt(firstcount));
                   freightAttr.setFirstprice(StringUtilsEX.ToFloat(firstprice));
                   freightAttr.setElsecount(StringUtilsEX.ToInt(elsecount));
                   freightAttr.setElseprice(StringUtilsEX.ToFloat(elseprice));
                   freightAttr.setStatus(StringUtilsEX.ToInt(status));
                   attrs.add(freightAttr);
               }
			
	    	}
           

            int result = freightService.update(StringUtilsEX.ToInt(id),
                StringUtilsEX.ToInt(shopid), name, StringUtilsEX.ToInt(isExemptionPostage),
                StringUtilsEX.ToInt(pricingModel), StringUtilsEX.ToInt(transportModel),
                StringUtilsEX.ToInt(setNum),StringUtilsEX.ToInt(iscondition), description, attrs);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改模板页面", "/seller/freight/update", "修改模板");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改模板操作记录出错! 异常信息:",
    								e, "/seller/freight/update");
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
            	item.setDesc("修改模板异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"修改模板! 异常信息:{0}",
					e, "/seller/freight/update");
        }
        return item;
    }

    /**
     * 删除模板
     * 
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public @ResponseBody ReusltItem delete(String id) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("模板id错误");
            return item;
        }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
        try {
            int result = freightService.deleteFreightManagerById(StringUtilsEX.ToInt(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除模板页面", "/seller/freight/delete", "删除模板");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加删除模板操作记录出错! 异常信息:",
    								e, "/seller/freight/delete");
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
            	item.setDesc("删除模板异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"删除模板! 异常信息:{0}",
					e, "/seller/freight/delete");
        }
        return item;
    }

    /**
     * 添加模板详细配置
     * 
     * @param freightid
     * @param areas
     * @param firstcount
     * @param firstprice
     * @param elsecount
     * @param elseprice
     * @param atInNum
     * @return
     */
    @RequestMapping("/addAttr")
    public @ResponseBody ReusltItem addAttr(String freightid, String areas, String firstcount,
                                            String firstprice, String elsecount, String elseprice,
                                            String atInNum) {
        ReusltItem item = new ReusltItem();
        checkParamAttr(freightid, areas, firstcount, firstprice, elsecount, elseprice, atInNum,
            item);
        if (item.getCode() < 0) {
            return item;
        }
        try {
            int result = freightService.insertAttr(StringUtilsEX.ToInt(freightid), areas,
                StringUtilsEX.ToInt(firstcount), StringUtilsEX.ToFloat(firstprice),
                StringUtilsEX.ToInt(elsecount), StringUtilsEX.ToFloat(elseprice)
                );
            if (result > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "添加模板详细配置页面", "/seller/freight/addAttr", "添加模板详细配置");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加添加模板详细配置操作记录出错! 异常信息:",
    								e, "/seller/freight/addAttr");
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
            	item.setDesc("添加模板详细配置异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"添加模板详细配置! 异常信息:{0}",
					e, "/seller/freight/addAttr");
        }
        return item;
    }

    /**
     * 校验参数attr
     * @param freightid
     * @param areas
     * @param firstcount
     * @param firstprice
     * @param elsecount
     * @param elseprice
     * @param atInNum
     * @param item
     */
    private void checkParamAttr(String freightid, String areas, String firstcount,
                                String firstprice, String elsecount, String elseprice,
                                String atInNum, ReusltItem item) {
        if (StringUtilsEX.ToInt(freightid) < 0) {
            item.setCode(-101);
            item.setDesc("参数模板id错误");
        }
        if (!StringUtils.isNotNull(areas)) {
            item.setCode(-102);
            item.setDesc("请选择区域");
        }
        if (StringUtilsEX.ToInt(firstcount) < 0) {
            item.setCode(-103);
            item.setDesc("请选择首件数量");
        }
        if (StringUtilsEX.ToFloat(firstprice) < 0) {
            item.setCode(-104);
            item.setDesc("请填写首件价格");
        }
        if (StringUtilsEX.ToInt(elsecount) < 0) {
            item.setCode(-103);
            item.setDesc("请选择续件数量");
        }
        if (StringUtilsEX.ToFloat(elseprice) < 0) {
            item.setCode(-104);
            item.setDesc("请填写续件价格");
        }
    }

    /**
     * 删除模板详细信息根据id
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteAttr")
    public @ResponseBody ReusltItem deleteAttr(String id) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("详细模板id错误");
            return item;
        }
        try {
            int result = freightService.deleteAttr(StringUtilsEX.ToInt(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除模板详细信息页面", "/seller/freight/deleteAttr", "根据id删除模板详细信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加根据id删除模板详细信息操作记录出错! 异常信息:",
    								e, "/seller/freight/deleteAttr");
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
            	item.setDesc("删除模板详细信息根据id异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"删除模板详细信息根据id! 异常信息:{0}",
					e, "/seller/freight/deleteAttr");
        }
        return item;
    }
    
    /**
     * 获取子模板列表
     * @param ftid
     * @param page
     * @param size
     * @param request
     * @return
     */
    @RequestMapping("/querySellerFreightAttrList")
	public @ResponseBody ReusltItem querySellerFreightAttrList(String ftid,String page, String size, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(ftid) < 0) {
				item.setCode(-101);
				item.setDesc("参数id错误,id" + ftid);
				return item;
			
			}
			CriteriaFreightAttr criteria = new CriteriaFreightAttr();
			
			criteria.setFreightid(StringUtilsEX.ToInt(ftid));
			PageBean pageBean = freightAttrService.queryAttrByFrightId(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setPageIndex(pageBean.getPc());
			item.setMaxRow(pageBean.getTr());
			item.setPageSize(pageBean.getPs());

		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询子模板异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.freight,"查询子模板异常! 异常信息:{0}", e,
					"/seller/freightmanager/queryFreightAttrList");
		}
		return item;
	}

}
