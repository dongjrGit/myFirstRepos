package com.yinlian.wssc.platform.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.LogisticsService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaLogistics;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/platform/logistics")
public class LogisticsController {

	@Autowired
	private LogisticsService logisticsService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;

	@RequestMapping("/save")
	public ReusltItem save(String id, String name, String code, String sort) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtils.isNotNull(name)) {
				item.setCode(-101);
				item.setDesc("名称不能为空！");
				return item;
			}
			if (!StringUtils.isNotNull(code)) {
				item.setCode(-101);
				item.setDesc("编号不能为空！");
				return item;
			}
			Integer orderby=0;
			if(StringUtilsEX.ToInt(sort)>0){
				orderby=StringUtilsEX.ToInt(sort);
			}
			logisticsService.svae(StringUtilsEX.ToIntNull(id), name.trim(), code.trim(), orderby);
			user = SessionState.GetCurrentUser();
			//异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "edit.jsp", "/platform/logistics/save", "保存物流公司信息");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"保存物流公司信息操作记录出错! 异常信息:",
								e, "/platform/logistics/save");
					}
					
				}
			});
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("保存物流公司信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Data,"保存物流公司信息异常! 异常信息:", e,
					"/platform/logistics/save");
		}
		return item;
	}

	@RequestMapping("/pagelist")
	public ReusltItem pagelist(String name, String code, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaLogistics cl = new CriteriaLogistics();
			cl.setCode(code);
			cl.setName(name);
			Integer spage = StringUtilsEX.ToInt(page);
			if (spage <= 0) {
				spage = 1;
			}
			Integer ssize = StringUtilsEX.ToInt(size);
			if (ssize <= 0) {
				ssize = 10;
			}
			PageBean pBean = logisticsService.getList(cl, spage, ssize);
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		} catch (Exception ex) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取物流公司列表异常：" + ex.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Data,"获取物流公司列表异常! 异常信息:", ex,
					"/platform/logistics/pagelist");
		}
		return item;
	}

	@RequestMapping("/deletebyid")
	public ReusltItem deletebyid(String id) {
		ReusltItem item = new ReusltItem();
		try {
			Integer id1 = StringUtilsEX.ToInt(id);
			if (id1 == null || id1 <= 0) {
				item.setCode(-1001);
				item.setDesc("id不能为空！");
				return item;
			}
			logisticsService.deletebyid(id1);
			user = SessionState.GetCurrentUser();
			//异步操作 不影响正常流程
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                        		user.getUserId(), user.getLoginName(), "list.jsp", "/platform/logistics/deletebyid", "删除物流公司信息");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"删除物流公司信息操作记录出错! 异常信息:",
								e, "/platform/logistics/deletebyid");
					}
					
				}
			});
		} catch (Exception ex) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("删除物流公司信息异常：" + ex.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Data,"删除物流公司信息异常! 异常信息:", ex,
					"/platform/logistics/deletebyid");
			
		}
		return item;
	}
}
