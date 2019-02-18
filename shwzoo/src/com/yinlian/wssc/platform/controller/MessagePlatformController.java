/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.app.jpush.PushService;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.service.MessageRecordService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.CommonUtils;
import com.yinlian.wssc.web.util.CriteriaMessage;
import com.yinlian.wssc.web.util.CriteriaUser;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 平台的站内信控制类
 * MessagePlatformController.java
 * @author Administrator
 * @version $Id: MessagePlatformController.java, v 0.1 2016年3月22日 下午2:33:05 Administrator Exp $
 */
@RestController
@RequestMapping("/platform/message")
public class MessagePlatformController {

    @Autowired
    private UserService          userService;
    @Autowired
    private MessageService       messageService;
    @Autowired
    private MessageRecordService messageRecordService;
    
    SessionUser                user = null;
	@Autowired
    private OperaterecordsService operaterecordsService ;

    /**
     * 分页查询会员信息列表
     * 
     * @param rName
     * @param telNum
     * @param startTime
     * @param endTime
     * @param uType
     * @param index
     * @param size
     * @return
     */
    @RequestMapping("/queryMemberListByCriteria")
    public ReusltItem queryMemberListByCriteria(String rName, String telNum, String startTime,
                                                String endTime, String uType, String page,
                                                String size, String email) {
        ReusltItem item = new ReusltItem();
        try {
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
	        PageBean pageBean;
            CriteriaUser criteria = new CriteriaUser();
            criteria.setBegin(DateUtil.stringConvert(startTime));
            criteria.setEnd(DateUtil.stringConvert(endTime));
            criteria.setUsername(rName);
            criteria.setMobile(telNum);
            criteria.setUsertype(StringUtilsEX.ToInt(uType));
            criteria.setEmail(email);
            pageBean = userService.selectMemberListByPage(criteria, StringUtilsEX.ToInt(page),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取分页查询会员信息列表的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "获取分页查询会员信息列表的信息出错! 异常信息:",
					e, "/platform/message/queryMemberListByCriteria");
        }
        return item;
    }

    /**
     * 添加站内信息记录
     * 
     * @param userid
     * @return
     */
    @RequestMapping("/addMessageRecords")
    public ReusltItem addMessageRecords(String type,String receiveid, String title, String content,
    		HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
	        if (!StringUtils.isNotNull(receiveid)) {
	            item.setCode(-101);
	            item.setDesc("会员id错误");
	            return item;
	        }
	        SessionUser	sessionUser=SessionUtil.getSessionUser(request);
	        String[] array = receiveid.split(",");
            int result = messageRecordService.insertBacth(StringUtilsEX.ToInt(type), sessionUser,array, title, content);

            if (result > 0) {
                item.setCode(0);
                item.setDesc("发送成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		sessionUser.getUserId(), sessionUser.getLoginName(), "sendMesInfo.jsp", "/platform/message/addMessageRecords", "添加站内信息记录");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加站内信息记录操作记录出错! 异常信息:",
    								e, "/platform/message/addMessageRecords");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("发送失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加站内信息记录的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.SendMessage, "添加站内信息记录的信息出错! 异常信息:",
					e, "/platform/message/addMessageRecords");
        }

        return item;
    }

    /**
     * 发送站内信
     * 
     * @param receiveid
     * @param title
     * @param content
     * @return
     */
    /* @RequestMapping("/addMessageList")
     public ReusltItem addMessageList(String receiveid, String title, String content,
                                      HttpSession session) {
         ReusltItem item = new ReusltItem();
         try {
	         if (!StringUtils.isNotNull(receiveid)) {
	             item.setCode(-101);
	             item.setDesc("会员id错误");
	             return item;
	         }
	         String[] array = receiveid.split(",");
             int result = messageService.insertBacth(array, title, content);
             if (result > 0) {
                 item.setCode(0);
                 item.setDesc("发送成功");
             } else {
                 item.setCode(-200);
                 item.setDesc("发送失败");
             }
         } catch (Exception e) {
             item.setCode(-900);
			item.setDesc("获取发送站内信的信息出错：" + e.getMessage());
			LogHandle.error(LogType.SendMessage, MessageFormat.format("获取发送站内信的信息出错! 异常信息:{0}",
					e.getMessage()), "message/addMessageList");
         }
         return item;
     }*/

    /**
     * 查询已发送的分页站内信
     * 
     * @param rName
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/querySendMesByCriteria")
    public ReusltItem querySendMesByCriteria(String rName, String startTime, String endTime,
                                             String page, String size, Integer type) {
        ReusltItem item = new ReusltItem();
        try {
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
	        PageBean pageBean;
            CriteriaMessage criteria = new CriteriaMessage();
            criteria.setUsername(rName);
            criteria.setBegin(DateUtil.stringConvert(startTime));
            criteria.setEnd(DateUtil.stringConvert(endTime));
            criteria.setType(type);
            criteria.setOrderByClause("sendtime");
			criteria.setSort("desc");
            pageBean = messageRecordService.selectMessagesListByPage(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询已发送的分页站内信的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "查询已发送的分页站内信的信息出错! 异常信息:",
					e, "/platform/message/querySendMesByCriteria");
        }
        return item;
    }

    /**
     * 根据id删除站内信
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteRecordById")
    public ReusltItem deleteRecordById(String id) {
        ReusltItem item = new ReusltItem();
        try {
        	user= SessionState.GetCurrentUser();
	        if (StringUtilsEX.ToInt(id) < 0) {
	            item.setCode(-101);
	            item.setDesc("参数id错误");
	            return item;
	        }
           // int result = messageRecordService.deleteById(StringUtilsEX.ToInt(id));
            int result = messageService.deleteById(StringUtilsEX.ToInt(id));
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
                            		user.getUserId(), user.getLoginName(), "sendedMessage.jsp", "/platform/message/deleteRecordById", "根据id删除站内信");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除站内信操作记录出错! 异常信息:",
    								e, "/platform/message/deleteRecordById");
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
				item.setDesc("根据id删除站内信的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "根据id删除站内信的信息出错! 异常信息:",
					e, "/platform/message/deleteRecordById");
        }
        return item;
    }

    /**
     * 批量删除站内信
     * 
     * @param ids
     * @return
     */
    @RequestMapping("/bacthDeleteRecords")
    public ReusltItem bacthDeleteRecords(String ids) {
        ReusltItem item = new ReusltItem();
        try {
        	user= SessionState.GetCurrentUser();
	        if (!StringUtils.isNotNull(ids)) {
	            item.setCode(-101);
	            item.setDesc("参数ids错误");
	            return item;
	        }
            String[] array = ids.split(",");
            int result = messageRecordService.deleteBacth(array);
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
                            		user.getUserId(), user.getLoginName(), "sendedMessage.jsp", "/platform/message/bacthDeleteRecords", "批量删除站内信");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量删除站内信操作记录出错! 异常信息:",
    								e, "/platform/message/bacthDeleteRecords");
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
				item.setDesc("批量删除站内信的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages,"批量删除站内信的信息出错! 异常信息:",
					e, "/platform/message/bacthDeleteRecords");
        }
        return item;
    }

    /**
     * 根据id查询
     * 
     * @param id
     * @return
     */
    @RequestMapping("/queryMesRecById")
    public ReusltItem queryMesRecById(String id) {
        ReusltItem item = new ReusltItem();
        try {
	        if (StringUtilsEX.ToInt(id) < 0) {
	            item.setCode(-101);
	            item.setDesc("参数id错误");
	            return item;
	        }
            Messages messages = messageService.queryById(StringUtilsEX.ToInt(id));
            item.setCode(0);
            item.setData(messages);
            
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id查询的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "根据id查询的信息出错! 异常信息:",
					e, "/platform/message/queryMesRecById");
        }
        return item;
    }

    /**
     * 批量发送邮件
     * 
     * @param memberemailstr
     * @return
     */
    @RequestMapping("/sendEmail")
    public ReusltItem sendEmail(String emails, String title, String content) {
        ReusltItem item = new ReusltItem();
        try {
        	user= SessionState.GetCurrentUser();
	        if (!StringUtils.isNotNull(emails)) {
	            item.setCode(-101);
	            item.setDesc("邮件号不能为空");
	            return item;
	        }
            // 发送邮件 
            String[] array = emails.split(",");
            for (String email : array) {
                CommonUtils.sendEmail(email, title, content);
            }
            item.setCode(0);
            item.setDesc("发送成功");
          //异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "sendEmail_Add.jsp", "/platform/message/sendEmail", "批量发送邮件");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"批量发送邮件操作记录出错! 异常信息:",
								e, "/platform/message/sendEmail");
					}
					
				}
			});
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("批量发送邮件的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages,"批量发送邮件的信息出错! 异常信息:",
					e, "/platform/message/sendEmail");
        }

        return item;
    }

    /**
     * 发送一个邮件
     * 
     * @param email
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/sendEmailOne")
    public ReusltItem sendEmailOne(String email, String title, String content) {
        ReusltItem item = new ReusltItem();
        try {
        	user= SessionState.GetCurrentUser();
	        if (!StringUtils.isNotNull(email)) {
	            item.setCode(-101);
	            item.setDesc("邮件号不能为空");
	            return item;
	        }
	        boolean istrue = CommonUtils.sendEmail(email, title, content);
            if (istrue == true) {
            	item.setCode(0);
                item.setDesc("发送成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "sendEmail_Add.jsp", "/platform/message/sendEmailOne", "发送一个邮件");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"发送一个邮件操作记录出错! 异常信息:",
    								e, "/platform/message/sendEmailOne");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
                item.setDesc("发送失败");
			}
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("发送一个邮件的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages,"发送一个邮件的信息出错! 异常信息:",
					e, "/platform/message/sendEmailOne");
        }

        return item;
    }

    /**
     * 查询已发送的手机短息列表
     * 
     * @param rName
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @param type
     * @param sendname
     * @return
     */
    @RequestMapping("/querySendMesMobileByCriteria")
    public ReusltItem querySendMesMobileByCriteria(String telNum,String rName, String startTime, String endTime,
                                                   String page, String size, Integer type,
                                                   String sendname) {
        ReusltItem item = new ReusltItem();
        try {
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
	        PageBean pageBean;
            CriteriaMessage criteria = new CriteriaMessage();
            criteria.setMobile(telNum);
            criteria.setUsername(rName);
            criteria.setBegin(DateUtil.stringConvert(startTime));
            criteria.setEnd(DateUtil.stringConvert(endTime));
           // criteria.setType(type);
            criteria.setSendname(sendname);
            pageBean = messageRecordService.selectMessagescordsListByPage(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询已发送的手机短息列表的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "查询已发送的手机短息列表的信息出错! 异常信息:",
					e, "/platform/message/querySendMesMobileByCriteria");
        }
        return item;
    }

    /**
     * 批量发送短信
     * 
     * @param type
     * @param mobiles
     * @param content
     * @param ids
     * @return
     */
    @RequestMapping("/addMessageMobileRecords")
    public ReusltItem addMessageMobileRecords(Integer type, String mobiles, String content,
                                              String ids, String title,String usernames,HttpServletRequest request) {

        ReusltItem item = new ReusltItem();
        try {
        	user= SessionState.GetCurrentUser();
	        if (!StringUtils.isNotNull(mobiles)) {
	            item.setCode(-101);
	            item.setDesc("手机号不能为空");
	            return item;
	        }
	        if (!StringUtils.isNotNull(content)) {
	            item.setCode(-102);
	            item.setDesc("短息内容不能为空");
	            return item;
	        }
	        if (!StringUtils.isNotNull(ids)) {
	            item.setCode(-103);
	            item.setDesc("用户的id不能为空");
	            return item;
	        }
	        SessionUser	sessionUser=SessionUtil.getSessionUser(request);
            String[] moblieArray = mobiles.split(",");
            String[] idArray = ids.split(",");
            int result = messageRecordService
                .insertList(sessionUser,type, moblieArray, content, idArray, title);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("发送成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "sendMobileMessage_Add.jsp", "/platform/message/querySendMesMobileByCriteria", "批量发送短信");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量发送短信操作记录出错! 异常信息:",
    								e, "/platform/message/querySendMesMobileByCriteria");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("发送失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("批量发送短信的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "批量发送短信的信息出错! 异常信息:",
					e, "/platform/message/querySendMesMobileByCriteria");
        }
        return item;
    }

    /**
     * 发送一条短息
     * 
     * @param type
     * @param mobile
     * @param content
     * @param userid
     * @param title
     * @return
     */
    @RequestMapping("/addMessageMobileRecord")
    public ReusltItem addMessageMobileRecord(Integer type, String mobile, String content,
                                             String userid, String title,HttpServletRequest request) {

        ReusltItem item = new ReusltItem();
        try {
        	user= SessionState.GetCurrentUser();
	        if (!StringUtils.isNotNull(mobile)) {
	            item.setCode(-101);
	            item.setDesc("手机号不能为空");
	            return item;
	        }
	        if (!StringUtils.isNotNull(content)) {
	            item.setCode(-102);
	            item.setDesc("短息内容不能为空");
	            return item;
	        }
	        if (StringUtilsEX.ToInt(userid) < 0) {
	            item.setCode(-103);
	            item.setDesc("用户的id不能为空");
	            return item;
	        }
	        SessionUser	sessionUser=SessionUtil.getSessionUser(request);
            int result = messageRecordService.insertOne(sessionUser,type, mobile, content,
                StringUtilsEX.ToInt(userid), title);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("发送成功");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "sendMobileMessage_Add.jsp", "/platform/message/addMessageMobileRecord", "发送一条短信");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"发送一条短信操作记录出错! 异常信息:",
    								e, "/platform/message/addMessageMobileRecord");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("发送失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("发送一条短信的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "获取发送一条短信的信息出错! 异常信息:",
					e, "/platform/message/addMessageMobileRecord");
        }
        return item;
    }
    /**
     * 分页查询站内消息
     * @param userid
     * @param status
     * @param page
     * @param size
     * @param datef
     * @param datet
     * @param sendname
     * @return
     */
    @RequestMapping("/queryMemberMessageByCriteria")
    public ReusltItem queryMemberMessageByCriteria(String usertype,String username,String sendid,String status,String page,
    		String size,String datef,String datet,String sendname) {
    	ReusltItem item = new ReusltItem();
    	try {
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
			CriteriaMessage criteria = new CriteriaMessage();
			criteria.setUsertype(StringUtilsEX.ToInt(usertype));
			criteria.setUsername(username);
			criteria.setSendid(sendid);
			criteria.setStatus(StringUtilsEX.ToInt(status));
			criteria.setBegin(StringUtilsEX.ToShortDate(datef));
			criteria.setEnd(StringUtilsEX.ToShortDate(datet));
			criteria.setSendname(sendname);
			PageBean pageBean = messageService.selectMessagesByUserIdPage(criteria,StringUtilsEX.ToInt(page),
																StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询站内消息的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "分页查询站内消息的信息出错! 异常信息:",
					e, "/platform/message/queryMemberMessageByCriteria");
		}
        return item;
    }
    /**
     * 批量设置已读
     * @param idlist
     * @return
     */
    @RequestMapping("/readMes")
    public ReusltItem readMes(String idlist){
    	ReusltItem item = new ReusltItem();
    	try {
    		user= SessionState.GetCurrentUser();
	    	String[] ids = idlist.split(",");
	    	int result = 0;
	    	if (!StringUtils.isNotNull(idlist)) {
				item.setCode(-101);
				item.setDesc("参数的id不能为空");
			}
			for (int i = 0; i < ids.length; i++) {
				Messages messages = messageService.queryById(StringUtilsEX.ToInt(ids[i]));
				messages.setStatus(0);
				result = messageService.updateById(messages);
			}
			if (result>0) {
				item.setCode(0);
				item.setDesc("批量设置成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "p_Message.jsp", "/platform/message/readMes", "批量设置已读");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量设置已读操作记录出错! 异常信息:",
    								e, "/platform/message/readMes");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("批量设置失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("批量设置已读的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "批量设置已读的信息出错! 异常信息:",
					e, "/platform/message/readMes");
		}
    	return item;
    	
    }
    /**
     * 批量删除
     * @param idlist
     * @return
     */
    @RequestMapping("/delList")
    public ReusltItem delList(String idlist){
    	ReusltItem item = new ReusltItem();
    	try {
    		user= SessionState.GetCurrentUser();
	    	String[] ids = idlist.split(",");
	    	int result = 0;
	    	if (!StringUtils.isNotNull(idlist)) {
				item.setCode(-101);
				item.setDesc("参数的id不能为空");
			}
			for (int i = 0; i < ids.length; i++) {
				
				result = messageService.deleteById(StringUtilsEX.ToInt(ids[i]));
			}
			if (result>0) {
				item.setCode(0);
				item.setDesc("批量删除成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "p_Message.jsp", "/platform/message/delList", "批量删除");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量删除操作记录出错! 异常信息:",
    								e, "/platform/message/delList");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("批量删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("批量删除的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "批量删除的信息出错! 异常信息:",
					e, "/platform/message/delList");
		}
    	return item;
    	
    }
    
    /**
     * 推送消息
     * @param memberid
     * @param content
     * @return
     */
    @RequestMapping("/pushmessage")
    public ReusltItem pushmessage(String memberid,String content){
    	ReusltItem item = new ReusltItem();
    	try {
    		if(StringUtilsEX.IsNullOrWhiteSpace(memberid)){
    			item.setCode(-101);
    			item.setDesc("用户id参数错误");
    			return item;
    		}
    		PushService.sendPushAlias("", content, memberid);
			item.setCode(0);
			item.setDesc("发送成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("推送消息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "推送消息出错! 异常信息:",
					e, "/platform/message/pushmessage");
		}
    	return item;
    }
    
    /**
     * 批量推送消息
     * @param idlist
     * @param content
     * @return
     */
    @RequestMapping("/pushmessages")
    public ReusltItem pushmessages(String idlist,String content){
    	ReusltItem item = new ReusltItem();
    	try {
    		if(StringUtilsEX.IsNullOrWhiteSpace(idlist)){
    			item.setCode(-101);
    			item.setDesc("用户id参数错误");
    			return item;
    		}
    		String[] ids = idlist.split(",");
    		List<String> memberids = new ArrayList<String>();
    		for(int i = 0; i < ids.length; i++){
    			memberids.add(ids[i]);
    		}
    		PushService.sendPushAliass("", content, memberids);
			item.setCode(0);
			item.setDesc("发送成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("批量推送消息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Messages, "批量推送消息出错! 异常信息:",
					e, "/platform/message/pushmessages");
		}
    	return item;
    }
    
}