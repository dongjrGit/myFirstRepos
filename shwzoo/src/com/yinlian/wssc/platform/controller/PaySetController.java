package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Payset;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.PaySetService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 支付方式的控制类
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/platform/payset")
public class PaySetController {

    /**
     * 输出日志的控制类
     */
    private static final Logger logger = LoggerFactory.getLogger(PaySetController.class);

    @Autowired
    private PaySetService       paySetService;
    
    SessionUser user = null;
	@Autowired
    private OperaterecordsService operaterecordsService ;

    /**
     * 显示所有的支付方式
     * @return
     */

    @RequestMapping("/queryPaySetList")
    public @ResponseBody ReusltItem queryPaySetList() {
        ReusltItem item = new ReusltItem();

        try {
            List<Payset> list = paySetService.queryPaySetList();
            if (list.size() > 0) {
                item.setCode(0);
                item.setDesc("查询成功");
                item.setData(list);
            }
        } catch (Exception e) {
        	item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取显示所有的支付方式的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
            LogHandle.error(LogType.Pay,"获取显示所有的支付方式的信息出错! 异常信息:{0}", e,
                "/platform/payset/queryPaySetList");
        }
        return item;

    }

    /**
     * 根据id删除支付方式
     * 
     * @param id
     * @return
     */

    @RequestMapping("/deletePaySetById")
    public @ResponseBody ReusltItem deletePaySetById(Integer id) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            int result = paySetService.deletePaySetById(id);
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
                            		user.getUserId(), user.getLoginName(), "PaySet_list.jsp", "/platform/payset/deletePaySetById", "根据id删除支付方式");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除支付方式操作记录出错! 异常信息:",
    								e, "/platform/payset/deletePaySetById");
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
				 item.setDesc("获取根据id删除支付方式的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
            LogHandle.error(LogType.Pay,
                MessageFormat.format("获取根据id删除支付方式的信息出错! 异常信息:{0}", e.getMessage()),
                "/platform/payset/deletePaySetById");
        }
        return item;
    }

    /**
     * 根据id修改支付方式
     * @param id
     * @param payName
     * @param moneyName
     * @param poundage
     * @param isPersent
     * @param isOnline
     * @param interfaceType
     * @param display
     * @param discription
     * @return
     */
    @RequestMapping("/updatPaySetById")
    public @ResponseBody ReusltItem updatPaySetById(String id, String payName, String moneyName,
                                                    String poundage, String isPersent,
                                                    String isOnline, String interfaceType,
                                                    String display, String discription) {
        ReusltItem item = new ReusltItem();

        try {
        	user = SessionState.GetCurrentUser();
            Payset record = paySetService.selectPaySetById(id);
            Payset payset = new Payset();
            payset.setDiscription(discription);
            payset.setDisplay(StringUtilsEX.ToInt(display));
            payset.setInterfacetype(StringUtilsEX.ToInt(interfaceType));
            payset.setIsonline(StringUtilsEX.ToBoolean(isOnline));
            payset.setIspersent(StringUtilsEX.ToBoolean(isPersent));
            payset.setMoneyname(moneyName);
            payset.setPayname(payName);
            payset.setPoundage(StringUtilsEX.ToFloat(poundage));
            BeanUtils.copyProperties(payset, record);
            int result = paySetService.updatePaySetById(record);
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
                            		user.getUserId(), user.getLoginName(), "PaySet_add.jsp", "/platform/payset/updatPaySetById", "根据id修改支付方式");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id修改支付方式操作记录出错! 异常信息:",
    								e, "/platform/payset/updatPaySetById");
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
				 item.setDesc("获取根据id修改支付方式的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
             LogHandle.error(LogType.Pay,"获取根据id修改支付方式的信息出错! 异常信息:{0}", e,
                "/platform/payset/updatPaySetById");
        }
        return item;
    }

    /**
     * 根据id查询支付方式
     * @param id
     * @return
     */
    @RequestMapping("/selectPaySetById")
    public @ResponseBody ReusltItem selectPaySetById(String id) {
        ReusltItem item = new ReusltItem();
        try {
            Payset record = paySetService.selectPaySetById(id);
            if (record.getId() > 0) {
                item.setCode(0);
                item.setData(record);
            }

        } catch (Exception e) {
        	item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取根据id查询支付方式的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
             LogHandle.error(LogType.Pay,
               "获取根据id查询支付方式的信息出错! 异常信息:{0}", e,
                "/platform/payset/selectPaySetById");
        }

        return item;
    }

    /**
     * 添加支付方式
     * @param payName
     * @param moneyName
     * @param poundage
     * @param isPersent
     * @param isOnline
     * @param interfaceType
     * @param display
     * @param discription
     * @return
     */
    @RequestMapping("/addPaySet")
    public @ResponseBody ReusltItem addPaySet(String payName, String moneyName, String poundage,
                                              String isPersent, String isOnline,
                                              String interfaceType, String display,
                                              String discription) {
        ReusltItem item = new ReusltItem();
        try {
        	user = SessionState.GetCurrentUser();
            Payset payset = new Payset();
            payset.setDiscription(discription);
            payset.setDisplay(StringUtilsEX.ToInt(display));
            payset.setInterfacetype(StringUtilsEX.ToInt(interfaceType));
            payset.setIsonline(StringUtilsEX.ToBoolean(isOnline));
            payset.setIspersent(StringUtilsEX.ToBoolean(isPersent));
            payset.setMoneyname(moneyName);
            payset.setPayname(payName);
            payset.setPoundage(StringUtilsEX.ToFloat(poundage));

            int result = paySetService.addPaySet(payset);

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
                            		user.getUserId(), user.getLoginName(), "PaySet_add.jsp", "/platform/payset/addPaySet", "添加支付方式");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加支付方式操作记录出错! 异常信息:",
    								e, "/platform/payset/addPaySet");
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
				 item.setDesc("获取添加支付方式的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
            LogHandle
                .error(LogType.Pay,"获取添加支付方式的信息出错! 异常信息:{0}", e,
                    "/platform/payset/addPaySet");
        }
        return item;

    }
}