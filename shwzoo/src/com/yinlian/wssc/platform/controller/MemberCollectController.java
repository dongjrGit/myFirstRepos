package com.yinlian.wssc.platform.controller;


import java.util.Date;
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
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.UsercollectService;
import com.yinlian.wssc.web.util.CriteriaCollect;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;
/**
 * 会员收藏的控制类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/membercollect")
public class MemberCollectController {
	
	@Autowired
	private UsercollectService usercollectService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
	/**
	 * 分页查询会员商品收藏
	 * @param pageindex
	 * @param pagesize
	 * @return
	 */
	@RequestMapping("/queryMemberColler")
	public ReusltItem queryMemberColler(String title, String pageindex, String pagesize){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(pageindex) <= 0) {
				item.setCode(-101);
				item.setDesc("当前页参数错误pageindex"+pageindex);
			}
			if (StringUtilsEX.ToInt(pagesize) <= 0) {
				item.setCode(-102);
				item.setDesc("每页记录数参数错误pagesize"+pagesize);
			}
			CriteriaCollect criteria = new CriteriaCollect();
			criteria.setTitle(title);
			PageBean pageBean = usercollectService.queryMemberCollerByCriteria(criteria,StringUtilsEX.ToInt(pageindex),StringUtilsEX.ToInt(pagesize));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取分页查询会员商品收藏的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,"获取分页查询会员商品收藏的信息出错! 异常信息:",
					e, "/platform/membercollect/queryMemberColler");
		}
		return item;
	}
	/**
	 * 分页查询会员店铺收藏
	 * @param pageindex
	 * @param pagesize
	 * @return
	 */
	@RequestMapping("/queryMemberCollerShop")
	public ReusltItem queryMemberCollerShop(String username, String shopname, String pageindex, String pagesize){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(pageindex) <= 0) {
				item.setCode(-101);
				item.setDesc("当前页参数错误pageindex"+pageindex);
			}
			if (StringUtilsEX.ToInt(pagesize) <= 0) {
				item.setCode(-102);
				item.setDesc("每页记录数参数错误pagesize"+pagesize);
			}
			CriteriaCollect criteria = new CriteriaCollect();
			criteria.setUsername(username);
			criteria.setShopname(shopname);
			PageBean pageBean = usercollectService.queryMemberCollerShopByCriteria(criteria,StringUtilsEX.ToInt(pageindex),StringUtilsEX.ToInt(pagesize));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("分页查询会员店铺收藏的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "分页查询会员店铺收藏的信息出错! 异常信息:",
					e, "/platform/membercollect/queryMemberCollerShop");
		}
		return item;
	}
	/**
	 * 根据id删除会员收藏
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteMemberCollectById")
	public ReusltItem deleteMemberCollectById(String id){
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("参数错误id"+id);
			}
			Usercollect usercollect = usercollectService.queryById(StringUtilsEX.ToInt(id));
			usercollect.setVaildflag(1);
			usercollect.setDeltime(new Date());
			int result = usercollectService.updateMemberCollectById(usercollect);
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
                            		user.getUserId(), user.getLoginName(), "member_collect.jsp", "/platform/membercollect/deleteMemberCollectById", "根据id删除会员收藏");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除会员收藏操作记录出错! 异常信息:",
    								e, "/platform/membercollect/deleteMemberCollectById");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
				
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id删除会员收藏的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage,"根据id删除会员收藏的信息出错! 异常信息:",
					e, "/platform/membercollect/deleteMemberCollectById");
		}
		return item;
	}
}
