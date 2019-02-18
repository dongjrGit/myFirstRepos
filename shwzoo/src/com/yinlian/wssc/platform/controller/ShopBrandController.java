/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

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
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ShopBrandService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaCommodity;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 操作ShopBrand表的控制类
 * @author Administrator
 * @version $Id: ShopBrandController.java, v 0.1 2016年3月11日 下午5:45:58 Administrator Exp $
 */
@Controller
@RequestMapping("/platform/shopbrand")
public class ShopBrandController {

    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(ShopBrandController.class);
    @Autowired
    private ShopBrandService    shopBrandService;
    @Autowired
    private ShopService         shopService;
    @Autowired
    private BrandService        branService;
    
    SessionUser user=null;
    @Autowired
    private OperaterecordsService operaterecordsService ;

    /**
     * 根据店铺id查询品牌列表 分页数据
     * 
     * @param shopid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryBrandCriteriaByShopid")
    public @ResponseBody ReusltItem queryBrandListByShopid(String shopid, String page, String size) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(shopid) < 0) {
            item.setCode(-101);
            item.setDesc("店铺的id不能为空,id" + shopid);
        }
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
        Criteria criteria = new Criteria();
        criteria.setShopid(StringUtilsEX.ToInt(shopid));
        PageBean pageBean;

        try {
            pageBean = shopBrandService.selectShopBrandPage(criteria, StringUtilsEX.ToInt(page),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("更新已退款异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand, "更新已退款异常! 异常信息:{0}", e,
					"/platform/shopbrand/queryBrandCriteriaByShopid");
        }

        return item;
    }

    /**
     * 根据店铺id和品牌id分页查询
     * 
     * @param shopid
     * @param brandid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryCriteria")
    public @ResponseBody ReusltItem queryCriteria(String shopid, String brandid, String page,
                                                  String size) {
        ReusltItem item = new ReusltItem();

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
        CriteriaCommodity criteria = new CriteriaCommodity();
        criteria.setShopid(StringUtilsEX.ToInt(shopid));
        criteria.setBrandid(StringUtilsEX.ToInt(brandid));
        PageBean pageBean;

        try {
            pageBean = shopBrandService.selectPage(criteria, StringUtilsEX.ToInt(page),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(" 根据店铺id和品牌id分页查询异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand, " 根据店铺id和品牌id分页查询! 异常信息:{0}", e,
					"/platform/shopbrand/queryCriteria");
        }

        return item;
    }

    /**
     * 根据id删除品牌店铺
     * 
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public @ResponseBody ReusltItem delete(String id, HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("品牌店铺id参数错误");
        }
        try {
        	user=SessionState.GetCurrentUser();
        	Integer userid =0;
        	if(user!=null&&user.getId()>0){
        		userid =user.getUserId();
        	}
            
            int result = shopBrandService.deleteById(StringUtilsEX.ToInt(id), userid);
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
                            		user.getUserId(), user.getLoginName(), "goods_brand_list.jsp", "/platform/shopbrand/delete", "根据id删除");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除操作记录出错! 异常信息:",
    								e, "/platform/shopbrand/delete");
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
				item.setDesc("根据id删除品牌店铺异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand, " 根据id删除品牌店铺! 异常信息:{0}", e,
					"/platform/shopbrand/delete");
        }
        return item;
    }

    /**
     * 保存店铺品牌
     * 
     * @param shopid
     * @param brandid
     * @param id
     * @return
     */
    @RequestMapping("/save")
    public @ResponseBody ReusltItem save(String shopid, String brandid, String id) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(shopid) < 0) {
            item.setCode(-101);
            item.setDesc("店铺id不能为空,id" + shopid);
            return item;
        }
        if (StringUtilsEX.ToInt(brandid) < 0) {
            item.setCode(-102);
            item.setDesc("品牌id不能为空,id" + shopid);
            return item;
        }

        try {
        	user=SessionState.GetCurrentUser();
            if ("".equals(id) || id == null) {
                //新增
                int result = shopBrandService.insert(StringUtilsEX.ToInt(shopid),
                    StringUtilsEX.ToInt(brandid));
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
                                		user.getUserId(), user.getLoginName(), "goods_brand_add.jsp", "/platform/shopbrand/save", "添加店铺品牌");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加店铺品牌操作记录出错! 异常信息:",
        								e, "/platform/shopbrand/save");
        					}
        					
        				}
        			});
                } else if (result == -2) {
                    item.setCode(-201);
                    item.setDesc("该品牌已经关联，不能重复申请");
                } else {
                    item.setCode(-200);
                    item.setDesc("添加失败");
                }
            } else {
                // 更新
                int result = shopBrandService.update(StringUtilsEX.ToInt(shopid),
                    StringUtilsEX.ToInt(brandid), StringUtilsEX.ToInt(id));
                if (result > 0) {
                    item.setCode(0);
                    item.setDesc("更新成功");
                  //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "goods_brand_update.jsp", "/platform/shopbrand/save", "修改店铺品牌");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改店铺品牌操作记录出错! 异常信息:",
        								e, "/platform/shopbrand/save");
        					}
        					
        				}
        			});
                } else {
                    item.setCode(-200);
                    item.setDesc("更新失败");
                }
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id删除品牌店铺异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand, " 根据id删除品牌店铺! 异常信息:{0}", e,
					"/platform/shopbrand/save");
        }
        return item;
    }

    /**
     * 修改状态
     * 
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/checkStatus")
    public @ResponseBody ReusltItem checkStatus(String id, String status) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("shopbrandid不能为空,id" + id);
            return item;
        }
        if (StringUtilsEX.ToInt(status) < 0) {
            item.setCode(-102);
            item.setDesc("品牌状态不能为空,status" + status);
            return item;
        }

        try {
        	user=SessionState.GetCurrentUser();
            int result = shopBrandService.updateStatus(StringUtilsEX.ToInt(id),
                StringUtilsEX.ToInt(status));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改状态成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_brand_list.jsp", "/platform/shopbrand/checkStatus", "修改状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改状态操作记录出错! 异常信息:",
    								e, "/platform/shopbrand/checkStatus");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改状态失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改状态异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand, " 修改状态! 异常信息:{0}", e,
					"/platform/shopbrand/checkStatus");
        }

        return item;
    }

}
