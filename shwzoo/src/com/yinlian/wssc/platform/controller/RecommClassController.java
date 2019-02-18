package com.yinlian.wssc.platform.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Recommclass;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.RecommclassService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 推荐分类管理
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/RecommClass")
public class RecommClassController {

	@Autowired
	private RecommclassService recommclassService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
	/**
	 * 添加关联分类
	 * 
	 * @param classid
	 * @param orderby
	 * @return
	 */
	@RequestMapping("/add")
	public ReusltItem add(String classid, String orderby,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
//			user=SessionState.GetCurrentUser();
			user=SessionUtil.getSessionUser(request);
			if (StringUtilsEX.ToInt(classid) <= 0) {
				item.setCode(-101);
				item.setDesc("分类ID参数错误，classid：" + classid);
				return item;
			}
			if (StringUtilsEX.ToInt(orderby) < 0) {
				item.setCode(-102);
				item.setDesc("排序参数错误，orderby：" + orderby);
				return item;
			}
			Recommclass rc=new Recommclass();
			rc.setClassid(StringUtilsEX.ToInt(classid));
			rc.setRecommendid(1);  //默认为 1
			rc.setOrderby(StringUtilsEX.ToInt(orderby));
			rc.setCreatetime(new Date());
//			rc.setCreateuserid(user.getId());
			rc.setCreateuserid(1);
			if(recommclassService.insert(rc)>0){
				item.setCode(0);
				item.setDesc("添加关联分类成功");
//				LogHandle.info(LogType.Activity, MessageFormat.format("添加关联分类成功! 关联分类ID:{0},userID:{1}",
//						rc.getClassid(),user.getId()), "RecommClass/add");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_RecommClassAdd.jsp", "/platform/RecommClass/add", "添加关联分类");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加关联分类操作记录出错! 异常信息:",
    								e, "/platform/RecommClass/add");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("添加关联分类失败");
//				LogHandle.info(LogType.Activity, MessageFormat.format("添加关联分类失败! 关联分类ID:{0},userID:{1}",
//						rc.getClassid(),user.getId()), "RecommClass/add");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("添加关联分类异常，信息：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle
					.info(LogType.Activity,
							"添加关联分类异常，信息：" + e.getLocalizedMessage(),
							"/platform/RecommClass/add");
		}
		return item;
	}

	/**
	 * 删除关联分类
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public ReusltItem delete(String id,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
//			user=SessionState.GetCurrentUser();
			user=SessionUtil.getSessionUser(request);
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误，id：" + id);
				return item;
			}
			if(recommclassService.deleteByPrimaryKey(StringUtilsEX.ToInt(id))>0){
				item.setCode(0);
				item.setDesc("删除关联分类成功");
//				LogHandle.info(LogType.Activity, MessageFormat.format("删除关联分类成功! ID:{0},userID:{1}",
//						id,user.getId()), "RecommClass/delete");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_RecommClassList.jsp", "/platform/RecommClass/delete", "删除关联分类");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除关联分类操作记录出错! 异常信息:",
    								e, "/platform/RecommClass/delete");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("删除关联分类失败");
//				LogHandle.info(LogType.Activity, MessageFormat.format("删除关联分类失败! ID:{0},userID:{1}",
//						id,user.getId()), "RecommClass/delete");
			}
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("删除关联分类异常，信息：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Activity,
					"删除关联分类异常，信息：" ,e,
					"/plaform/RecommClass/delete");
		}
		return item;
	}

	/**
	 * 获取关联分类列表
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getList")
	public ReusltItem getList(String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，page：" + page + ",size：" + size);
				return item;
			}
			Criteria criteria=new Criteria();
			criteria.setSort("desc");
			criteria.setOrderByClause("OrderBy");
			PageBean pBean=recommclassService.getListByPage(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取关联分类列表异常，信息：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Activity,
					"获取关联分类列表异常，信息：",e,
					"/platform/RecommClass/getList");
		}
		return item;
	}

	/**
	 * 编辑关联分类排序
	 * @param id
	 * @param orderby
	 * @return
	 */
	@RequestMapping("/updateOrder")
	public ReusltItem updateOrder(String id, String orderby,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
//			user=SessionState.GetCurrentUser();
			user=SessionUtil.getSessionUser(request);
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误，id：" + id);
				return item;
			}
			if (StringUtilsEX.ToInt(orderby) <= 0) {
				item.setCode(-101);
				item.setDesc("排序参数错误，orderby：" + orderby);
				return item;
			}
			if(recommclassService.updateOrder(StringUtilsEX.ToInt(orderby),StringUtilsEX.ToInt(id))>0){
				item.setCode(0);
				item.setDesc("更新关联分类排序成功");
//				LogHandle.info(LogType.Activity, MessageFormat.format("更新关联分类排序成功! ID:{0},userID:{1}",
//						id,user.getId()), "RecommClass/updateOrder");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_RecommClassList.jsp", "/platform/RecommClass/updateOrder", "修改关联分类排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改关联分类排序操作记录出错! 异常信息:",
    								e, "/platform/RecommClass/updateOrder");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("更新关联分类排序失败");
//				LogHandle.info(LogType.Activity, MessageFormat.format("更新关联分类排序失败! ID:{0},userID:{1}",
//						id,user.getId()), "RecommClass/updateOrder");
			}
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("编辑关联分类排序异常，信息：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Activity,
					"编辑关联分类排序异常，信息：" ,e,
					"/platform/RecommClass/updateOrder");
		}
		return item;
	}

	/**
	 * 批量更新关联分类排序
	 * @param ids
	 * @param orderbys
	 * @return
	 */
	@RequestMapping("/updateOrderList")
	public ReusltItem updateOrderList(String ids, String orderbys,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
//			user=SessionState.GetCurrentUser();
			user=SessionUtil.getSessionUser(request);
			List<Integer> idlist = new ArrayList<Integer>();
			List<Integer> orderlist = new ArrayList<Integer>();
			for (String id : ids.split(",")) {
				if (StringUtilsEX.ToInt(id) <= 0) {
					item.setCode(-101);
					item.setDesc("ID参数错误，id：" + id);
					return item;
				}
				idlist.add(StringUtilsEX.ToInt(id));
			}
			for (String ob : orderbys.split(",")) {
				if (StringUtilsEX.ToInt(ob) < 0) {
					item.setCode(-101);
					item.setDesc("排序参数错误，ob：" + ob);
					return item;
				}
				orderlist.add(StringUtilsEX.ToInt(ob));
			}
			if(recommclassService.updateOrderList(idlist, orderlist)>0){
				item.setCode(0);
				item.setDesc("批量更新关联分类排序成功");
//				LogHandle.info(LogType.Activity, MessageFormat.format("批量更新关联分类排序成功! ID集合:{0},userID:{1}",
//						ids,user.getId()), "RecommClass/updateOrder");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_RecommClassList.jsp", "/platform/RecommClass/updateOrderList", "批量更新关联分类排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量更新关联分类排序操作记录出错! 异常信息:",
    								e, "/platform/RecommClass/updateOrderList");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("批量更新关联分类排序失败");
//				LogHandle.info(LogType.Activity, MessageFormat.format("批量更新关联分类排序失败! ID集合:{0},userID:{1}",
//						ids,user.getId()), "RecommClass/updateOrder");
			}
		} catch (Exception e) {
			item.setCode(-900);
			 if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("批量更新关联分类排序异常，信息：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Activity,
					"批量更新关联分类排序异常，信息：" ,e,
					"/platform/RecommClass/updateOrderList");
		}
		return item;
	}
}
