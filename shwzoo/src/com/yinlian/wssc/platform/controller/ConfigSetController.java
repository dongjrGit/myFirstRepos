package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Pointsrule;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.PointsruleService;
import com.yinlian.wssc.web.service.UserslevelService;
import com.yinlian.wssc.web.util.BasicConvert;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 配置设置信息的控制类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/configset")
public class ConfigSetController {

    @Autowired
    private ConfigSetService    configsetService;

    @Autowired
    private UserslevelService   userslevelService;

    @Autowired
    private PointsruleService   pointsruleService;
    
    @Autowired
    private OperaterecordsService operaterecordsService ;

    /**
     * 根据type修改配置设置
     * @param setType
     * @param setname
     * @param setValue
     * @param setDesc
     * @return
     */

    @RequestMapping("/updateConfigSetByType")
    public ReusltItem updateConfigSetByType(String setType, String setKey,
                                                          String setValue, String setDesc,
                                                          String setStatus) {
        ReusltItem item = new ReusltItem();

        try {
        	SessionUser user=SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(setType) < 0) {
                item.setCode(-101);
                item.setDesc("配置设置类型参数错误,type:" + setType);
                return item;
            }

            Configdictionary record = configsetService.showConfigSetByType(
            		StringUtilsEX.ToInt(setType));
            Configdictionary configdictionary = new Configdictionary();
            configdictionary.setKeyword(setKey);
            configdictionary.setValue(setValue);
            configdictionary.setDescription(setDesc);
            configdictionary.setType(StringUtilsEX.ToInt(setType));
            BeanUtils.copyProperties(configdictionary, record);
            int resule = configsetService.updateConfigSetByType(record);
            if (resule > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
            	LogHandle.info(
						LogType.Configset,
						MessageFormat.format("修改配置成功! type:{0},用户ID:{1}",
								setType, user.getUserId()),
						"/platform/configset/updateConfigSetByType");
            	//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Config_Set.jsp", "/platform/configset/updateConfigSetByType", "根据type修改配置设置");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改配置操作记录出错! 异常信息:",
    								e, "/platform/configset/updateConfigSetByType");
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
            	item.setDesc("获取根据type修改配置设置的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "获取根据type修改配置设置的信息出错! 异常信息:", e,
                "/platform/configset/updateConfigSetByType");
        }
        return item;
    }

    /**
     * 根据Type获取表单
     * @param stype
     * @return
     */

    @RequestMapping("/showConfigSetByType")
    public ReusltItem showConfigSetByType(String stype) {
        ReusltItem item = new ReusltItem();

        try {
            if (BasicConvert.string2Integer(stype) < 0) {
                item.setCode(-101);
                item.setDesc("配置设置类型参数错误,stype:" + stype);
                return item;
            }

            Configdictionary configdictionary = configsetService.showConfigSetByType(
            		StringUtilsEX.ToInt(stype));
            if (configdictionary!=null&&configdictionary.getId() > 0) {
                item.setCode(0);
                item.setData(configdictionary);
            } else {
                item.setCode(-200);
                item.setDesc("数据库没有取到值");
            }

        } catch (Exception e) {
            item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取根据Type获取表单的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "获取根据Type获取表单的信息出错! 异常信息:", e,
                "/platform/configset/showConfigSetByType");
        }
        return item;

    }

    /**
     * 添加配置
     * @param setKey
     * @param setValue
     * @param setType
     * @param setDesc
     * @param setStatus
     * @return
     */
    @RequestMapping("/addConfigSet")
    public ReusltItem addConfigSet(String setKey, String setValue, String setType,
                                                 String setDesc, String setStatus) {
        ReusltItem item = new ReusltItem();

        try {
        	SessionUser user=SessionState.GetCurrentUser();
            if (StringUtilsEX.ToInt(setType) < 0) {
                item.setCode(-101);
                item.setDesc("配置设置类型参数错误,setType:" + setType);
                return item;
            }

            Configdictionary configdictionary = new Configdictionary();
            configdictionary.setKeyword(setKey);
            configdictionary.setValue(setValue);
            configdictionary.setType(StringUtilsEX.ToInt(setType));
            configdictionary.setDescription(setDesc);
            configdictionary.setStatus(0);
            int resule = configsetService.addConfigSet(configdictionary);
            if (resule > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
                LogHandle.info(
						LogType.Configset,
						MessageFormat.format("添加配置成功! type:{0},用户ID:{1}",
								setType, user.getUserId()),
						"/platform/configset/addConfigSet");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Config_Set.jsp", "/platform/configset/addConfigSet", "添加配置");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加配置操作记录出错! 异常信息:",
    								e, "/platform/configset/addConfigSet");
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
            	item.setDesc("获取添加配置的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            
            LogHandle.error(LogType.Configset,
                "获取添加配置的信息出错! 异常信息:", e,
                "/platform/configset/addConfigSet");
        }
        return item;
    }

    /**
     * 获取用户等级
     * @return
     */
    @RequestMapping("/queryUsersLevel")
    public ReusltItem queryUsersLevel() {
        ReusltItem item = new ReusltItem();
        try {
            List<Userslevel> list = userslevelService.queryAllLevel();
            if (list.size() > 0) {
                item.setCode(0);
                item.setData(list);
                item.setDesc("获取成功");
            } else {
                item.setCode(-200);
                item.setDesc("数据库没有取到值");
            }
        } catch (Exception e) {
            item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取获取用户等级的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "获获取用户等级的信息出错! 异常信息:", e,
                "/platform/configset/queryUsersLevel");
        }
        return item;
    }

    /**
     * 获取积分设置表单
     * @param levelid
     * @return
     */
    @RequestMapping("/querPointsruleByUserLevel")
    public ReusltItem querPointsruleByUserLevel(String levelid) {
        ReusltItem item = new ReusltItem();
        try {
            List<Pointsrule> list = pointsruleService.querPointsruleByUserLevel(StringUtilsEX
                .ToInt(levelid));
            if (list.size() > 0) {
                item.setCode(0);
                item.setData(list);
                item.setDesc("获取积分规则成功");
            } else {
                item.setCode(-200);
                item.setDesc("数据库没有取到值");
            }
        } catch (Exception e) {
            item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取获取积分设置表单的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "获取获取积分设置表单的信息出错! 异常信息:", e,
                "/platform/configset/querPointsruleByUserLevel");
        }
        return item;
    }

    /**
     * 添加积分设置
     * @param type
     * @param name
     * @param value
     * @param points
     * @param levelid
     * @return
     */
    @RequestMapping("/addPointsrule")
    public ReusltItem addPointsrule(String type, String name, String value,
                                                  String points, String levelid) {
        ReusltItem item = new ReusltItem();
        try {
        	SessionUser user=SessionState.GetCurrentUser();
            Pointsrule pointsrule = new Pointsrule();
            pointsrule.setName(name);
            pointsrule.setType(StringUtilsEX.ToInt(type));
            pointsrule.setValue(StringUtilsEX.ToLong(value));
            pointsrule.setPoints(StringUtilsEX.ToInt(points));
            pointsrule.setUserlevel(StringUtilsEX.ToInt(levelid));
            pointsrule.setCreatetime(new Date());
            int result = pointsruleService.addPointsrule(pointsrule);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
                LogHandle.info(
						LogType.Configset,
						MessageFormat.format("添加积分设置成功! points:{0},用户ID:{1}",
								points, user.getUserId()),
						"/platform/configset/addPointsrule");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Config_Set.jsp", "/platform/configset/addPointsrule", "添加积分设置");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加积分设置操作记录出错! 异常信息:",
    								e, "/platform/configset/addPointsrule");
    					}
    					
    				}
    			});
            } else {
                item.setCode(0);
                item.setDesc("添加失败");
            }
        } catch (Exception e) {
            item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取添加积分设置的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "获取添加积分设置的信息出错! 异常信息:", e,
                "/platform/configset/addPointsrule");
        }
        return item;
    }

    /**
     * 编辑积分配置 
     * @param name
     * @param value
     * @param points
     * @param levelid
     * @return
     */
    @RequestMapping("/updatePointsruleByUserLevel")
    public ReusltItem updatePointsruleByUserLevel(String name, String value,
                                                                String points, String levelid) {
        ReusltItem item = new ReusltItem();
        try {
        	SessionUser user=SessionState.GetCurrentUser();
            List<Pointsrule> list = pointsruleService.querPointsruleByUserLevelAndName(
                StringUtilsEX.ToInt(levelid), name);
            Pointsrule pointsrule = new Pointsrule();
            if (list.size() > 0) {
            	pointsrule = list.get(0);
			}else {
				pointsrule.setId(0);
				pointsrule.setCreatetime(new Date());
				if (name.equals("消费")) {
					pointsrule.setType(1);
				}else {
					pointsrule.setType(0);
				}
			}
            pointsrule.setName(name);
            pointsrule.setValue(StringUtilsEX.ToLong(value));
            pointsrule.setPoints(StringUtilsEX.ToInt(points));
            pointsrule.setUserlevel(StringUtilsEX.ToInt(levelid));
            
            int result = pointsrule.getId() > 0 ? pointsruleService.updatePointsruleByUserLevel(pointsrule) : pointsruleService.addPointsrule(pointsrule);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("编辑成功");
                LogHandle.info(
						LogType.Configset,
						MessageFormat.format("编辑积分规则设置成功! points:{0},用户ID:{1}",
								points, user.getUserId()),
						"/platform/configset/updatePointsruleByUserLevel");
              //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "Config_Set.jsp", "/platform/configset/updatePointsruleByUserLevel", "修改编辑积分配置");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改编辑积分配置操作记录出错! 异常信息:",
    								e, "/platform/configset/updatePointsruleByUserLevel");
    					}
    				}
    			});
            } else {
                item.setCode(0);
                item.setDesc("编辑失败");
            }
        } catch (Exception e) {
            item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("编辑积分规则设置信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.Configset,
                "编辑积分规则设置信息出错! 异常信息:", e,
                "/platform/configset/updatePointsruleByUserLevel");
        }
        return item;
    }
}
