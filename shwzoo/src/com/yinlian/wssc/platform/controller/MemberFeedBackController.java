package com.yinlian.wssc.platform.controller;



import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.FeedbackDto;
import com.yinlian.wssc.web.dto.MemberCommentDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.Commentreply;
import com.yinlian.wssc.web.po.Commentshowimg;
import com.yinlian.wssc.web.po.Feedback;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.CommentreplyService;
import com.yinlian.wssc.web.service.CommentshowimgService;
import com.yinlian.wssc.web.service.FeedBackService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaMemberComment;
import com.yinlian.wssc.web.util.CriteriaMemberFeedBack;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 评论列表的控制类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/memberfeedback")
public class MemberFeedBackController {
	
	@Autowired
	private  FeedBackService     feedBackService;

	@Autowired
    private OperaterecordsService operaterecordsService ;
	
	SessionUser user=null;
	
	/**
	 * 会员反馈列表
	 * @param pageindex
	 * @param pagesize
	 * @param buyername
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@RequestMapping("/queryMemberFeedBack")
	public ReusltItem queryMemberComment(String pageindex,String pagesize,
			String buyername,String starttime,String endtime){
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
			CriteriaMemberFeedBack criteria = new CriteriaMemberFeedBack();
			criteria.setBuyername(buyername);
			criteria.setStarttime(StringUtilsEX.ToShortDate(starttime));
			criteria.setEndtime(StringUtilsEX.ToShortDate(endtime));
			criteria.setOrderByClause("creattime");
			criteria.setSort("desc");
			PageBean pageBean = feedBackService.queryMemberFeedBackByCriteria(criteria,
					StringUtilsEX.ToInt(pageindex),StringUtilsEX.ToInt(pagesize));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据分页查询会员反馈列表的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "根据分页查询会员反馈列表的信息出错! 异常信息:",
					e, "/platform/memberfeedback/queryMemberComment");
		}
		return item;
	}
	/**
	 * 根据id查询反馈明细
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryFeedBackById")
	public ReusltItem queryFeedBackDetail(String id){
		ReusltItem item = new ReusltItem();
		try {
			Feedback dto = feedBackService.queryById(StringUtilsEX.ToInt(id));
			
			if (dto != null) {
				item.setCode(0);
				item.setData(dto);
				item.setDesc("查询成功");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id查询反馈明细信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "根据id查询反馈明细的信息出错! 异常信息:",
					e, "/platform/memberfeedback/queryFeedBackById");
		}
		return item;
	}
	
	@RequestMapping("/updateStatus")
	public ReusltItem updateStatus(String id){
		ReusltItem item = new ReusltItem();
		try {
			Feedback dto = feedBackService.queryById(StringUtilsEX.ToInt(id));
			if(dto!=null){
				dto.setStatus(1);
			}
			int i=feedBackService.updateFeedBack(dto);
			if(i>0){
				item.setCode(0);
				item.setDesc("修改成功");
				 ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "feedBack_list.jsp", "/platform/memberfeedback/updateStatus", "修改反馈状态");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"修改反馈状态出错! 异常信息:",
	    								e, "/platform/memberfeedback/queryFeedBackById");
	    					}
	    					
	    				}
	    			});
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改反馈状态出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "修改反馈状态出错! 异常信息:",
					e, "/platform/memberfeedback/queryFeedBackById");
		}
		return item;
	}

	/**
	 * 删除一条评论
	 * @return
	 */
	@RequestMapping("/delFeedBack")
	public @ResponseBody ReusltItem delFeedBack(String id){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <=0) {
				item.setCode(-101);
				item.setDesc("参数错误:id"+id);
			}
			int result= feedBackService.deleteFeedBack(StringUtilsEX.ToInt(id));
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
                            		user.getUserId(), user.getLoginName(), "feedBack_list.jsp", "/platform/memberfeedback/delFeedBack", "删除反馈信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除反馈信息出错! 异常信息:",
    								e, "/platform/memberfeedback/delFeedBack");
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
				item.setDesc("删除反馈信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "删除反馈信息出错! 异常信息:",
					e, "/platform/memberfeedback/delFeedBack");
		}
		
		return item;
		
	}
}
