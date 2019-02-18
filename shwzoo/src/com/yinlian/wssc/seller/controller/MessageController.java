/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.seller.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.CriteriaMessage;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 卖家站内信的控制类
 * MessageController.java
 * @author Administrator
 * @version $Id: MessageController.java, v 0.1 2016年3月21日 下午7:52:30 Administrator Exp $
 */
@RestController
@RequestMapping("/seller/message")
public class MessageController {

    /**
     * 日子输出类
     */
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private MessageService      messageService;
    @Autowired
    private AccountsService     accountsService;
    @Autowired
    private UsercapitalService  usercapitalService;
    @Autowired
    private OperaterecordsService operaterecordsService ;

    /**
     * 根据userid分页查询站内信
     * 
     * @param userid
     * @param status
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryMessagesByUserIdCriteria")
    public ReusltItem queryMessagesByUserIdCriteria(String userid, String status, String page,
                                                    String size) {
        ReusltItem item = new ReusltItem();
        try {
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
            if (StringUtilsEX.ToInt(userid) < 0) {
                item.setCode(-101);
                item.setDesc("参数userid错误");
                return item;
            }

            PageBean pageBean;

            CriteriaMessage criteria = new CriteriaMessage();
            criteria.setUserid(StringUtilsEX.ToInt(userid));
            criteria.setStatus(StringUtilsEX.ToInt(status));
            pageBean = messageService.selectMessagesByUserIdPage(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("根据userid分页查询站内信异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
        	LogHandle.error(
					LogType.Messages,"根据userid分页查询站内信异常：",
							e, "/seller/message/queryMessagesByUserIdCriteria");
        }
        return item;
    }

    /**
     * 删除一个站内信
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteMessage")
    public ReusltItem deleteMessage(String id,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("参数id不能为空");
                return item;
            }

            int result = messageService.deleteById(StringUtilsEX.ToInt(id));
            SessionUser user=SessionUtil.getSessionUser(request);
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
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除一条站内信页面", "/seller/message/deleteMessage", "删除一条站内信");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加删除一条站内信操作记录出错! 异常信息:",
    								e, "/seller/message/deleteMessage");
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
        		item.setDesc(" 删除一个站内信异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
        	LogHandle.error(
					LogType.Messages," 删除一个站内信异常：",
							e, "/seller/message/deleteMessage");
        }
        return item;
    }

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    @RequestMapping("/deleteMesList")
    public ReusltItem deleteMesList(String ids,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
            if (!StringUtils.isNotNull(ids)) {
                item.setCode(-101);
                item.setDesc("参数ids不能为空");
                return item;
            }

            int result = messageService.deleteByIds(ids);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
                SessionUser user=SessionUtil.getSessionUser(request);
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "批量删除站内信页面", "/seller/message/deleteMesList", "批量删除站内信");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加批量删除站内信操作记录出错! 异常信息:",
    								e, "/seller/message/deleteMessage");
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
        		item.setDesc("批量删除站内信异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
        	LogHandle.error(
					LogType.Messages," 批量删除站内信异常：",
							e, "/seller/message/deleteMesList");
        }
        return item;
    }

    /**
     * 批量设置已读
     * 
     * @param ids
     * @return
     */
    @RequestMapping("/readMesList")
    public ReusltItem readMesList(String ids,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
            if (!StringUtils.isNotNull(ids)) {
                item.setCode(-101);
                item.setDesc("参数ids不能为空");
                return item;
            }

            int result = messageService.updateByIds(ids);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("更新成功");
                SessionUser user=SessionUtil.getSessionUser(request);
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "批量设置站内信已读页面", "/seller/message/readMesList", "批量设置站内信已读");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加批量设置站内信已读操作记录出错! 异常信息:",
    								e, "/seller/message/readMesList");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("批量设置已读异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
        	LogHandle.error(
					LogType.Messages," 批量设置已读异常：",
							e, "/seller/message/readMesList");
        }
        return item;
    }

    /**
     * 根据id查询站内信
     * 
     * @param id
     * @return
     */
    @RequestMapping("/queryMessageById")
    public ReusltItem queryMessageById(String id) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("参数id不能为空");
                return item;
            }

            Messages messages = messageService.queryById(StringUtilsEX.ToInt(id));
            item.setCode(0);
            item.setData(messages);
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("根据id查询站内信异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
        	LogHandle.error(
					LogType.Messages," 根据id查询站内信异常：",
							e, "/seller/message/queryMessageById");
        }
        return item;
    }

    /**
     * 密码修改
     * 
     * @param userid
     * @param oldpwd
     * @param newpwd
     * @param newpwdAgain
     * @return
     */
    @RequestMapping("/updateUserPassword")
    public ReusltItem updateUserPassword(String userid, String oldpwd, String newpwd,
                                         String newpwdAgain,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	SessionUser user=SessionUtil.getSessionUser(request);
            if (!StringUtils.isNotNull(oldpwd)) {
                item.setCode(-101);
                item.setDesc("原始密码不能为空");
                return item;
            }
            Accounts accounts = accountsService.queryByuserid(user.getUserId());
            if (!DEndecryptUtil.get_instances().passwordEncrypt(oldpwd).equals(accounts.getPassword())){
            	item.setCode(-105);
            	item.setDesc("原始密码错误");
                return item;
            }
            if (!StringUtils.isNotNull(newpwd)) {
                item.setCode(-102);
                item.setDesc("新密码不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(newpwdAgain)) {
                item.setCode(-103);
                item.setDesc("确认密码不能为空");
                return item;
            }
            if (!newpwd.equals(newpwdAgain)) {
                item.setCode(-104);
                item.setDesc("两次密码不一致");
                return item;
            }
            int result = accountsService
                .updatePasswordByUserId(StringUtilsEX.ToInt(userid), newpwd);
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
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改密码页面", "/seller/message/updateUserPassword", "修改密码");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加批修改用户密码操作记录出错! 异常信息:",
    								e, "/seller/message/updateUserPassword");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("密码修改异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
        	LogHandle.error(
					LogType.Messages,"密码修改异常：",
							e, "/seller/message/updateUserPassword");
        }
        return item;
    }

    /**
     * 账户充值
     * 
     * @param money
     * @param recharge_type
     * @return
     */
    @RequestMapping("/recharge")
    public ReusltItem recharge(String userid, String money, String recharge_type,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
            String userip = GetIpAddresss.getIp();
            if (StringUtilsEX.ToInt(userid) < 0) {
                item.setCode(-101);
                item.setDesc("参数userid错误");
                return item;
            }
            if (StringUtilsEX.ToFloat(money) < 0) {
                item.setCode(-102);
                item.setDesc("充值额不能为空");
                return item;
            }
            if (StringUtilsEX.ToInt(recharge_type) < 0) {
                item.setCode(-103);
                item.setDesc("充值的类型不能为空");
                return item;
            }

            int result = usercapitalService.recharge(StringUtilsEX.ToDouble(money),
                recharge_type, StringUtilsEX.ToInt(userid), userip);

            if (result > 0) {
                item.setCode(0);
                item.setDesc("充值成功");
                SessionUser user=SessionUtil.getSessionUser(request);
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "账户充值页面", "/seller/message/recharge", "账户充值");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加账户充值操作记录出错! 异常信息:",
    								e, "/seller/message/recharge");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("充值失败");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("账户充值异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
        	LogHandle.error(
					LogType.Messages,"账户充值异常：",
							e, "/seller/message/recharge");
        }
        return item;
    }

}
