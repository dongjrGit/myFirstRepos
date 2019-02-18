package com.yinlian.wssc.platform.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.BrowseHistoryDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Browsehistory;
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaBrowsehistory;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 会员浏览记录的控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/platform/memberbrowserhistory")
public class MemberBrowserHistoryController {
	
	@Autowired
	private BrowsehistoryService browsehistoryService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
	/**
	 *  分页查询会员最新浏览历史列表
	 * @param pageIndex
	 * @param pageSize
	 * @param username
	 * @return
	 */
	@RequestMapping("/queryBrowsehistory")
	private @ResponseBody ReusltItem queryBrowsehistory(String pageIndex,String pageSize,String username){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(pageIndex) <= 0) {
				item.setCode(-101);
				item.setDesc("当前页参数错误pageIndex"+pageIndex);
			}
			if (StringUtilsEX.ToInt(pageSize) <= 0) {
				item.setCode(-102);
				item.setDesc("每页记录数参数错误pageSize"+pageSize);
			}
			if (username == null) {
				item.setCode(-102);
				item.setDesc("用户名参数错误username"+username);
			}
		
			CriteriaBrowsehistory criteria = new CriteriaBrowsehistory();
			criteria.setUsername(username);
			PageBean pageBean = browsehistoryService.queryBrowsehistoryByCriteria(criteria,StringUtilsEX.ToInt(pageIndex),
					StringUtilsEX.ToInt(pageSize) );
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询会员最新浏览历史列表的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "分页查询会员最新浏览历史列表的信息出错! 异常信息:",
					e, "/platform/memberbrowserhistory/queryBrowsehistory");
		}
		return item;
	}
	/**
	 * 查询会员浏览记录明细
	 * 
	 * @param member
	 * @return
	 */
	@RequestMapping("/queryBrowseHistoryDetail")
	public @ResponseBody ReusltItem queryBrowseHistoryDetail(String memberid){
		ReusltItem item = new ReusltItem();
		try {
			if (memberid == null && StringUtilsEX.ToInt(memberid) > 0) {
				item.setCode(-101);
				item.setDesc("参数memberid错误");
			}
		
			List<BrowseHistoryDto> list = browsehistoryService.queryDetailByUserId(StringUtilsEX.ToInt(memberid));
			if (list.size()>0) {
				item.setCode(0);
				item.setData(list);
				item.setDesc("查询成功");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询会员浏览记录明细的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "查询会员浏览记录明细的信息出错! 异常信息:",
					e, "/platform/memberbrowserhistory/queryBrowseHistoryDetail");
		}
		return item;
	}
	/**
	 * 清除浏览记录
	 * 
	 * @param memberid
	 * @return
	 */
	
	@RequestMapping("/deleteBrowsehistory")
	private @ResponseBody ReusltItem deleteBrowsehistory(String memberid){
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(memberid) <= 0) {
				item.setCode(-101);
				item.setDesc("参数错误memberid"+memberid);
			}
			int result = 0;
		
			List<Browsehistory> list = browsehistoryService.queryByUserId(StringUtilsEX.ToInt(memberid));
			for (Browsehistory browsehistory : list) {
				browsehistory.setVaildflag(1);
				browsehistory.setDeldate(new Date());
				result = browsehistoryService.updateBrowsehistoryByUserId(browsehistory);
			}
			if (result>0) {
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
                            		user.getUserId(), user.getLoginName(), "member_browserhistory.jsp", "/platform/memberbrowserhistory/deleteBrowsehistory", "删除浏览记录");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除浏览记录操作记录出错! 异常信息:",
    								e, "/platform/memberbrowserhistory/deleteBrowsehistory");
    					}
    					
    				}
    			});
			}else {
				item.setCode(0);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("清除浏览记录的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "清除浏览记录的信息出错! 异常信息:",
					e, "/platform/memberbrowserhistory/deleteBrowsehistory");
		}
		return item;
	}

}
