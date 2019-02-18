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
import com.yinlian.wssc.web.dto.MemberCommentDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Comment;
import com.yinlian.wssc.web.po.Commentreply;
import com.yinlian.wssc.web.po.Commentshowimg;
import com.yinlian.wssc.web.service.CommentService;
import com.yinlian.wssc.web.service.CommentreplyService;
import com.yinlian.wssc.web.service.CommentshowimgService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaMemberComment;
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
@RequestMapping("/platform/membercommen")
public class MemberCommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentreplyService commentreplyService;
	@Autowired
	private CommentshowimgService commentshowimgServers;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
	
	/**
	 * 根据分页查询会员评论列表
	 * @param pageindex
	 * @param pagesize
	 * @param shopname
	 * @param proname
	 * @param ordernum
	 * @param buyername
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@RequestMapping("/queryMemberComment")
	public ReusltItem queryMemberComment(String pageindex,String pagesize,String shopname,
			String proname,String ordernum,String buyername,String starttime,String endtime,String status){
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
			CriteriaMemberComment criteria = new CriteriaMemberComment();
			criteria.setBuyername(buyername);
			criteria.setShopname(shopname);
			criteria.setProname(proname);
			criteria.setOrdernum(ordernum);
			if(!StringUtilsEX.IsNullOrWhiteSpace(status)){
				criteria.setStatus(StringUtilsEX.ToInt(status));
			}
			criteria.setStarttime(StringUtilsEX.ToShortDate(starttime));
			criteria.setEndtime(StringUtilsEX.ToShortDate(endtime));
			criteria.setOrderByClause("date");
			criteria.setSort("desc");
			PageBean pageBean = commentService.queryMemberCommentByCriteria(criteria,
					StringUtilsEX.ToInt(pageindex),StringUtilsEX.ToInt(pagesize));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据分页查询会员评论列表的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "根据分页查询会员评论列表的信息出错! 异常信息:",
					e, "/platform/membercommen/queryMemberComment");
		}
		return item;
	}
	/**
	 * 修改评价审核状态
	 * @param comstatus
	 * @param comid
	 * @return
	 */
	@RequestMapping("/comCheck")
	public ReusltItem comCheck (String comstatus,String comid){
		ReusltItem item = new ReusltItem();
		try {
			if(StringUtilsEX.ToInt(comid) <= 0) {
				item.setCode(-102);
				item.setDesc("评论id参数错误");	
				return item;
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(comstatus)){
				item.setCode(-103);
				item.setData("评论状态不能为空");
				return item;
			}
			Comment comment = commentService.queryCommentById(StringUtilsEX.ToInt(comid));
			comment.setStatus(StringUtilsEX.ToInt(comstatus));
			if(commentService.updateCommentById(comment) > 0){
				item.setCode(0);
				item.setDesc("修改成功");
			}else{
				item.setCode(-200);
				item.setDesc("修改失败");
				return item;
			}
		}catch (Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id修改评价审核状态的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "修改评价审核状态的信息出错! 异常信息:",
					e, "/platform/membercommen/comCheck");
		}
		return item;
	}
	
	/**
	 * 根据id查询评论明细
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryCommentDetail")
	public ReusltItem queryCommentDetail(String id){
		ReusltItem item = new ReusltItem();
		try {
			MemberCommentDto memberCommentDto = commentService.queryById(StringUtilsEX.ToInt(id));
			
			if (memberCommentDto != null) {
				item.setCode(0);
				item.setData(memberCommentDto);
				item.setDesc("查询成功");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据id查询订单明细的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "根据id查询订单明细的信息出错! 异常信息:",
					e, "/platform/membercommen/queryCommentDetail");
		}
		return item;
	}
	/**
	 * 添加一条回复
	 * @param commentid
	 * @param replytype
	 * @param parentid
	 * @param byreplyuserid
	 * @param content
	 * @return
	 */
	@RequestMapping("/addReply")
	public ReusltItem addReply(String commentid,String replytype,String parentid,
			String byreplyuserid,String content,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(parentid) <= 0) {
				item.setCode(-102);
				item.setDesc("参数错误:parentid"+parentid);
			}
			if (StringUtilsEX.ToInt(byreplyuserid) <= 0) {
				item.setCode(-103);
				item.setDesc("参数错误:byreplyuserid"+byreplyuserid);
			}
			if (content == null) {
				item.setCode(-104);
				item.setDesc("内容不能为空");
			}
			SessionUser user = SessionUtil.getSessionUser(request);
				
			Integer userid=user.getUserId();
			Commentreply commentreply = new Commentreply();
			commentreply.setType(StringUtilsEX.ToInt(replytype));
			commentreply.setParentid(StringUtilsEX.ToInt(parentid));
			commentreply.setByreplyuserid(StringUtilsEX.ToInt(byreplyuserid));
			commentreply.setContent(content);
			commentreply.setCreatetime(new Date());
			commentreply.setCreateuserid(userid);
			commentreply.setShowname(0);
			commentreply.setVaildflag(0);
			int result = commentreplyService.addReply(commentreply);
			if (result>0) {
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
                            		user.getUserId(), user.getLoginName(), "memberCommentDetail.jsp", "/platform/membercommen/addReply", "添加一条回复");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加一条回复操作记录出错! 异常信息:",
    								e, "/platform/membercommen/addReply");
    					}
    					
    				}
    			});
			}else {
				item.setCode(-200);
				item.setDesc("添加失败");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加一条回复的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "添加回复的信息出错! 异常信息:",
					e, "/platform/membercommen/addReply");
		}
		return item;
	}
	/**
	 * 根据id删除评论照片
	 * @param imgid
	 * @return
	 */
	@RequestMapping("/deleteImgById")
	public @ResponseBody ReusltItem deleteImgById(String imgid){
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(imgid) <=0) {
				item.setCode(-101);
				item.setDesc("参数错误:imgid"+imgid);
			}
			Commentshowimg commentshowimg = commentshowimgServers.queryById(StringUtilsEX.ToInt(imgid));
			commentshowimg.setVaildflag(1);
			commentshowimg.setDeldate(new Date());
			int result = commentshowimgServers.updateById(commentshowimg);
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
                            		user.getUserId(), user.getLoginName(), "删除评论照片页面", "/platform/membercommen/deleteImgById", "根据id删除评论照片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"根据id删除评论照片操作记录出错! 异常信息:",
    								e, "/platform/membercommen/deleteImgById");
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
				item.setDesc("根据id删除评论照片的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "根据id删除评论照片的信息出错! 异常信息:",
					e, "/platform/membercommen/deleteImgById");
		}
		return item;
	}
	/**
	 * 删除一条评论
	 * @return
	 */
	@RequestMapping("/delComment")
	public @ResponseBody ReusltItem delComment(String commentid){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(commentid) <=0) {
				item.setCode(-101);
				item.setDesc("参数错误:commentid"+commentid);
			}
			Comment comment = commentService.queryCommentById(StringUtilsEX.ToInt(commentid));
			comment.setVaildflag(1);
			comment.setDeldate(new Date());
			int result = commentService.updateCommentById(comment);
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
                            		user.getUserId(), user.getLoginName(), "membercomment_list.jsp", "/platform/membercommen/delComment", "删除一条评论");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除一条评论操作记录出错! 异常信息:",
    								e, "/platform/membercommen/delComment");
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
				item.setDesc("删除一条评论的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.MemberCenterManage, "删除一条评论的信息出错! 异常信息:",
					e, "/platform/membercommen/delComment");
		}
		
		return item;
		
	}
	

	
	
}
	
