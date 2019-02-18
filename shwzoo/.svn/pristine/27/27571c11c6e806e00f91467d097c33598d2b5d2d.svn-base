package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
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
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.ShopViolationTypeEnum;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.MemberVo;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.OperateFromDTo;
import com.yinlian.wssc.web.dto.OperateTypeDTo;
import com.yinlian.wssc.web.dto.PageMarkDTo;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.VillationDTo;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Advertimgdictionary;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.CriteriaAdvering;
import com.yinlian.wssc.web.util.CriteriaAdvert;
import com.yinlian.wssc.web.util.CriteriaOperater;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 广告的
 * AdvertimgController.java
 * @author sssssssl.m
 * @version $Id: AdvertimgController.java, v 0.1 2016年4月13日 下午5:03:19 Administrator Exp $
 */
@Controller
@RequestMapping("/platform/userOperater")
public class UserOperaterController {

    @Autowired
    private     OperaterecordsService     operaterecordsService;
    
    @Autowired
    private     AccountsService           accountsService;

    SessionUser                 user   = null;
    
    @RequestMapping("/getType")
    public @ResponseBody ReusltItem getType() {
        ReusltItem item = new ReusltItem();
        try {
        	OperateRecordsTypeEnum[] array = OperateRecordsTypeEnum.values();

            List<OperateTypeDTo> list = new ArrayList<OperateTypeDTo>();
            for (int i = 0; i < array.length; i++) {
            	OperateTypeDTo dTo = new OperateTypeDTo();
            	dTo.setCode(OperateRecordsTypeEnum.values()[i].getValue());
            	dTo.setName(OperateRecordsTypeEnum.values()[i].name());
                list.add(dTo);
            }
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("获取所有的操作记录类型异常：" + e.getMessage());
            } else {
                item.setDesc("系统错误！");
            }
            item.setCode(-900);
            LogHandle
                .error(LogType.userrecords, "获取所有的操作记录类型异常! 异常信息:{0}", e,
                    "/platform/userOperater/getType");
        }
        return item;
    }
    
    @RequestMapping("/getScoure")
    public @ResponseBody ReusltItem getScoure() {
        ReusltItem item = new ReusltItem();
        try {
        	OperateRecordsFromEnum[] array = OperateRecordsFromEnum.values();

            List<OperateFromDTo> list = new ArrayList<OperateFromDTo>();
            for (int i = 0; i < array.length; i++) {
            	OperateFromDTo dTo = new OperateFromDTo();
            	dTo.setCode(OperateRecordsFromEnum.values()[i].getValue());
            	dTo.setName(OperateRecordsFromEnum.values()[i].name());
                list.add(dTo);
            }
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("获取所有的操作记录来源异常：" + e.getMessage());
            } else {
                item.setDesc("系统错误！");
            }
            item.setCode(-900);
             LogHandle
                .error(LogType.userrecords,"获取所有的操作记录来源异常! 异常信息:{0}", e,
                    "/platform/userOperater/getScoure");
        }
        return item;
    }
    
    @RequestMapping("/getName")
    public @ResponseBody ReusltItem getName(String scoure,String name) {
      ReusltItem item = new ReusltItem();
        try {
        	List<Accounts> accountslist=new ArrayList<Accounts>();
        	if(!StringUtilsEX.isBlank(scoure)){
        		Integer[] array= new Integer[2];
        		if(StringUtilsEX.ToInt(scoure)==2){
        			
        			array[0]=UserTypeEnum.Buyer.getValue();
            		
            	}else if(StringUtilsEX.ToInt(scoure)==1){
            		
            		array[0]=UserTypeEnum.Seller.getValue();
            		array[1]=UserTypeEnum.Employee.getValue();
            		
            	}else if(StringUtilsEX.ToInt(scoure)==0){
            		
            		array[0]=UserTypeEnum.SupAdmin.getValue();
            		array[1]=UserTypeEnum.Admin.getValue();
            	}
        		
        		accountslist=accountsService.queryUsers(array,name);
        	}else{
        		accountslist=accountsService.selectName(name);
        	}
        	item.setCode(0);
        	item.setData(accountslist);
        	
        } catch (Exception e) {
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("获取操作用户异常：" + e.getMessage());
            } else {
                item.setDesc("系统错误！");
            }
            item.setCode(-900);
            LogHandle
                .error(LogType.userrecords,"获取操作用户记录来源异常! 异常信息:{0}", e,
                    "/platform/userOperater/getScoure");
        }
        return item;
    }
    
    
    /**
     *
     * @param page
     * @param size
     * @param type
     * @param scoure
     * @param username
     * @param description
     * @param createtime
     * @return
     */
    @RequestMapping("/queryOperater")
    public @ResponseBody ReusltItem queryAdvert(String page, String size, String type,String scoure,
    		String userid,String description,String createtime,String endtime) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(page) <= 0) {
            item.setCode(-101);
            item.setDesc("分页参数错误：" + page);
            return item;
        }
        if (StringUtilsEX.ToInt(size) <= 0) {
            item.setCode(-102);
            item.setDesc("分页参数错误：" + size);
            return item;
        }
        try {
        	CriteriaOperater criteria = new CriteriaOperater();
        	criteria.setType(StringUtilsEX.ToInt(type));
            criteria.setScoure(StringUtilsEX.ToInt(scoure));
            criteria.setUserid(StringUtilsEX.ToInt(userid));
            criteria.setDescription(description);
           criteria.setBeginTime(createtime);
           criteria.setEndTime(endtime);
            PageBean pageBean = operaterecordsService.selectRecordsbyPage(criteria, StringUtilsEX.ToInt(page),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询分页用户操作出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.advertimg,
                "查询用户操作片出错! 异常信息:", e, "/platform/userOperater/queryOperater");
 
        }
        return item;
    }
    
    @RequestMapping("/deleteOperter")
    public @ResponseBody ReusltItem deleteOperter(String type,String scoure,String createtime,HttpServletRequest  request) {
        ReusltItem item = new ReusltItem();
        try {
        	SessionUser users = SessionUtil.getSessionUser(request);
            int result = operaterecordsService.deleteAll(StringUtilsEX.ToInt(type), StringUtilsEX.ToInt(scoure), StringUtilsEX.ToDate(createtime), users);
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
                            		users.getUserId(), users.getLoginName(), "UserOperater.jsp", "/platform/userOperater/deleteOperter", "根据id删除图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除图片操作记录出错! 异常信息:",
    								e, "/platform/userOperater/deleteOperter");
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
            	item.setDesc("根据id删除图片出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.advertimg,
                "根据id删除图片出错! 异常信息:", e, "/platform/userOperater/deleteOperter");
 
        }
        return item;
    }
    

  
}
