package com.yinlian.pc.controller;

import java.text.MessageFormat;
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
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;


@Controller
@RequestMapping("/pc/pcbrowsehistory")
public class PCBrowseHistoryController {
		@Autowired
		private     BrowsehistoryService   browsehistoryServise;
		@Autowired
		private OperaterecordsService operaterecordsService;
		
		
@RequestMapping(value = "/deletehistory", produces = "text/html;charset=UTF-8")
public @ResponseBody String deletehistory(String id,HttpServletRequest request){
			ReusltItem item = new ReusltItem();
			try{
				if(StringUtilsEX.IsNullOrWhiteSpace(id)){
					item.setCode(-201);
					item.setDesc("id不能为空");
					return item.toJson();
				}
				
				SessionUser user=SessionState.GetCurrentUser();
				if (user.getCode() != 0) {
					item.setCode(-401);
					item.setDesc("请先登录");
					return item.toJson();
				} else {
					int historyid = Integer.parseInt(id);
					browsehistoryServise.deleteByPrimaryKey(historyid);
					item.setCode(0);
					item.setDesc("删除浏览记录成功");
					ExecutorService executorService=Executors.newCachedThreadPool();					
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							try {								
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.pc前台.getValue(), user.getId(), user.getLoginName(), "BrowseHistory.html", "/pc/pcbrowsehistory/deletehistory", "删除浏览记录");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords,"删除浏览记录 异常信息:",
	    								e, "/pc/pcbrowsehistory/deletehistory");
							}
						}
					});
					
				}
			}catch(Exception e){
				item.setCode(-900);
				if(DebugConfig.BLUETOOTH_DEBUG){
					item.setDesc("删除用户浏览信息错误:"+e.toString());
				}else {
					item.setDesc("系统错误");
				}
				
				LogHandle.error(LogType.pc,
						MessageFormat.format("删除用户浏览信息失败：{0}", e.toString()));
			}
			return item.toJson();
		}
	}


