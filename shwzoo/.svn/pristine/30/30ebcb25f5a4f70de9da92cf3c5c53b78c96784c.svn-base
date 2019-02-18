package com.yinlian.wssc.platform.controller;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Goodconsult;
import com.yinlian.wssc.web.service.GoodConsultService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaGoodConsult;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 咨询列表的控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/platform/goodconsult")
public class GoodConsultController {
	
	@Autowired
	private GoodConsultService goodConsultService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
	/**
	 * 分页查询咨询列表
	 * @return
	 */
	@RequestMapping("/queryGoodConsult")
	public @ResponseBody ReusltItem queryGoodConsult(String title, String pronumstr, String pageindex, String pagesize){
		ReusltItem item = new ReusltItem();
		try {
			CriteriaGoodConsult criteria = new CriteriaGoodConsult();
			criteria.setTitle(title);
			criteria.setPronumstr(pronumstr);
			PageBean pageBean = goodConsultService.queryGoodConsultByCriteria(criteria,StringUtilsEX.ToInt(pageindex),StringUtilsEX.ToInt(pagesize));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				 item.setDesc("获取分页查询咨询列表的信息异常：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.GoodConsult, "获取分页查询咨询列表的信息出错! 异常信息:",
					e, "/platform/goodconsult/queryGoodConsult");
		}
		return item;
	}

	/**
	 * 商品咨询删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteGoodConsultById")
	public @ResponseBody ReusltItem deleteGoodConsultById(String id,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			Goodconsult goodconsult = goodConsultService.selectGoodConsultById(StringUtilsEX.ToInt(id));
			goodconsult.setVaildflag(1);
			goodconsult.setDeldate(new Date());
			int result = goodConsultService.updateById(goodconsult);
			if (result>0) {
				item.setCode(0);
				item.setDesc("删除成功");
				user=SessionUtil.getSessionUser(request);
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goodconsult_list.jsp", "/platform/goodconsult/deleteGoodConsultById", "删除商品咨询");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除商品咨询操作记录出错! 异常信息:",
    								e, "/platform/goodconsult/deleteGoodConsultById");
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
				item.setDesc("获取商品咨询删除的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.GoodConsult, "获取商品咨询删除的信息出错! 异常信息:",
					e, "/platform/goodconsult/deleteGoodConsultById");
		}
		return item;
	}
	/**
	 * 商品咨询回复
	 */
	@RequestMapping("/replyGoodConsult")
	public @ResponseBody ReusltItem replyGoodConsult(String id,String replycontent,HttpServletRequest request){
		ReusltItem item = new ReusltItem();
		try {
			Goodconsult goodconsult = goodConsultService.selectGoodConsultById(StringUtilsEX.ToInt(id));
			goodconsult.setStatus(1);
			goodconsult.setReplycontent(replycontent);
			goodconsult.setReplydate(new Date());
			int result = goodConsultService.updateById(goodconsult);
			if (result>0) {
				item.setCode(0);
				item.setDesc("回复成功");
				user=SessionUtil.getSessionUser(request);
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "reply_goodconsult.jsp", "/platform/goodconsult/replyGoodConsult", "添加商品咨询回复");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加商品咨询回复操作记录出错! 异常信息:",
    								e, "/platform/goodconsult/replyGoodConsult");
    					}
    					
    				}
    			});
			}else {
				item.setCode(0);
				item.setDesc("回复失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取商品咨询回复的信息出错：" + e.getMessage());
				} else {
					item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.GoodConsult, "获取商品咨询回复的信息出错! 异常信息:",
					e, "/platform/goodconsult/replyGoodConsult");
		}
		return item;
	}
}
