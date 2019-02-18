package com.yinlian.wssc.platform.controller;

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
import com.yinlian.Enums.StoreLevelEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.StoreLevel;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.StoreLevelService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/shop/sl")
public class StoreLevelController {
    private static final Logger logger = LoggerFactory.getLogger(SpuController.class);
    @Autowired
    private StoreLevelService   storeLevelService;
    
    SessionUser user = null;
    @Autowired
    private OperaterecordsService operaterecordsService ;

    @RequestMapping("/add")
    public @ResponseBody BaseResult add(StoreLevel orm) {
        BaseResult js = new BaseResult();
        try {
            if (orm.getName() == null || ("").equals(orm.getName())) {
                js.setCode(-101);
                js.setDesc("名称不能为空!");
                return js;
            }
            if (orm.getStatus() == null)
                orm.setStatus(StoreLevelEnum.启用.getValue());
            if (orm.getSort() == null)
                orm.setSort(0);
            js.setData(storeLevelService.add(orm));
        } catch (Exception ex) {
        	js.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				js.setDesc("添加店铺等级出错：" + ex.getMessage());
			} else {
				js.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					"添加店铺等级出错! 异常信息:", ex,
					"/platform/shop/sl/add");
        }
        return js;
    }

    @RequestMapping("/update")
    public @ResponseBody BaseResult update(StoreLevel src) {
        BaseResult js = new BaseResult();
        try {
        	user=SessionState.GetCurrentUser();
            StoreLevel orm = storeLevelService.getById(src.getId());
            BeanUtils.copyProperties(src, orm);
            if (orm.getName() == null || ("").equals(orm.getName())) {
                js.setCode(-101);
                js.setDesc("名称不能为空!");
                return js;
            }
            if (orm.getStatus() == null)
                orm.setStatus(StoreLevelEnum.启用.getValue());
            if (orm.getSort() == null)
                orm.setSort(0);
            js.setData(storeLevelService.update(orm));
          //异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "修改店铺等级页面", "/platform/shop/sl/update", "修改店铺等级");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"修改店铺等级操作记录出错! 异常信息:",
								e, "/platform/shop/sl/update");
					}
					
				}
			});
        } catch (Exception ex) {
        	js.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				js.setDesc("修改店铺等级出错：" + ex.getMessage());
			} else {
				js.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					"修改店铺等级出错! 异常信息:", ex,
					"/platform/shop/sl/update");
        }
        return js;
    }

    @RequestMapping("/del")
    public @ResponseBody BaseResult del(Integer id) {
        BaseResult js = new BaseResult();
        try {
        	user=SessionState.GetCurrentUser();
            if (id == null) {
                js.setCode(-101);
                js.setDesc("Id不能为空!");
                return js;
            }
            js.setData(storeLevelService.delById(id));
          //异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "删除店铺等级页面", "/platform/shop/sl/del", "删除店铺等级");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"删除店铺等级操作记录出错! 异常信息:",
								e, "/platform/shop/sl/del");
					}
					
				}
			});
        } catch (Exception ex) {
        	js.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				js.setDesc("删除店铺等级出错：" + ex.getMessage());
			} else {
				js.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					"删除店铺等级出错! 异常信息:", ex,
					"/platform/shop/sl/del");
        }
        return js;
    }

    @RequestMapping("/getbyid")
    public @ResponseBody BaseResult getById(Integer id) {
        BaseResult js = new BaseResult();
        try {
            if (id == null) {
                js.setCode(-101);
                js.setDesc("Id不能为空!");
                return js;
            }
            js.setData(storeLevelService.getById(id));
        } catch (Exception ex) {
        	js.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				js.setDesc("根据店铺等级id获取店铺等级信息出错：" + ex.getMessage());
			} else {
				js.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					"根据店铺等级id获取店铺等级信息出错! 异常信息:", ex,
					"/platform/shop/sl/getbyid");
        }
        return js;
    }

    /**
     * 获取所有店铺等级信息
     * @return
     */
    @RequestMapping("/getalllist")
    public @ResponseBody BaseResult getAllList() {
        BaseResult js = new BaseResult();
        try {
            js.setData(storeLevelService.getAllList());
        } catch (Exception ex) {
        	js.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				js.setDesc("获取所有店铺等级信息出错：" + ex.getMessage());
			} else {
				js.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					"获取所有店铺等级信息出错! 异常信息:", ex,
					"/platform/shop/sl/getalllist");
        }
        return js;
    }

    /**
     * 修改店铺等级状态信息
     * @param status
     * @param id
     * @return
     */
    @RequestMapping("/updatestatus")
    public @ResponseBody BaseResult updateStatusById(Integer status, Integer id) {
        BaseResult js = new BaseResult();
        try {
        	user=SessionState.GetCurrentUser();
            if (status == null) {
                js.setCode(-101);
                js.setDesc("status参数不能为空!");
            }
            if (id == null) {
                js.setCode(-102);
                js.setDesc("id参数不能为空!");
            }
            Boolean b = true;
            for (StoreLevelEnum s : StoreLevelEnum.values()) {
                if (s.getValue() == status) {
                    b = false;
                    break;
                }
            }
            if (b) {
                js.setCode(-103);
                js.setDesc("status参数错误!");
            }
            if (js.getCode() != 0) {
                return js;
            }
            StoreLevelEnum st = StoreLevelEnum.values()[status];
            js.setData(storeLevelService.updateStatusById(st, id));
          //异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "修改店铺等级状态页面", "/platform/shop/sl/updatestatus", "修改店铺等级状态");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"修改店铺等级状态操作记录出错! 异常信息:",
								e, "/platform/shop/sl/updatestatus");
					}
					
				}
			});
        } catch (Exception ex) {
        	js.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				js.setDesc("修改店铺等级状态信息出错：" + ex.getMessage());
			} else {
				js.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					"修改店铺等级状态信息出错! 异常信息:", ex,
					"/platform/shop/sl/updatestatus");
        }
        return js;
    }

    /**
     * 修改店铺等级排序信息
     * @param sort
     * @param id
     * @return
     */
    @RequestMapping("/updateSort")
    public @ResponseBody BaseResult updateSortById(Integer sort, Integer id) {
        BaseResult js = new BaseResult();
        try {
        	user=SessionState.GetCurrentUser();
            if (sort == null) {
                js.setCode(-101);
                js.setDesc("sort参数不能为空!");
            }
            if (id == null) {
                js.setCode(-102);
                js.setDesc("id参数不能为空!");
            }

            if (js.getCode() != 0) {
                return js;
            }
            js.setData(storeLevelService.updateSortById(sort, id));
          //异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "修改店铺等级排序页面", "/platform/shop/sl/updateSort", "修改店铺等级排序");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"修改店铺等级排序操作记录出错! 异常信息:",
								e, "/platform/shop/sl/updateSort");
					}
					
				}
			});
        } catch (Exception ex) {
        	js.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				js.setDesc("修改店铺等级排序信息出错：" + ex.getMessage());
			} else {
				js.setDesc("系统错误！");
			}
			LogHandle.error(LogType.PlatformShopManagement,
					"修改店铺等级排序信息出错! 异常信息:", ex,
					"/platform/shop/sl/updateSort");
        }
        return js;
    }
}
