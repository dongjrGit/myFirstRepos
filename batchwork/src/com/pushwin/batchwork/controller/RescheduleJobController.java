package com.pushwin.batchwork.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pushwin.batchwork.service.IRescheduleJobService;
import com.pushwin.batchwork.utils.ParamCheck;
import com.pushwin.batchwork.utils.PropertiesMgr;
import com.pushwin.batchwork.vo.BantchConfigVO;
import com.pushwin.batchwork.vo.common.CommonVO;

@Controller
@RequestMapping("/task")
public class RescheduleJobController {
	@Autowired
	private Scheduler scheduler;
//	@Autowired
//	private StdSchedulerFactory stdSchedulerFactory;
	@Autowired
	private IRescheduleJobService service;

	Logger logger = Logger.getLogger(RescheduleJobController.class);
	
	@RequestMapping("/tasklist")
	@ResponseBody
	public CommonVO queryTaskList(){
		CommonVO vo = service.queryTaskList();
		return vo;
	}
	@RequestMapping("/taskhis")
	@ResponseBody
	public CommonVO queryTaskHistory(@RequestParam String taskID){
		CommonVO vo = service.queryTaskHistory(taskID);
		return vo;
	}
//立刻执行	
//	public void startNow() {
//		String[] groupNames = this.scheduler.getJobGroupNames();
//		for (String groupName : groupNames) {
//			System.out.println("groupNames = : " + groupName);
//			String[] jobNames = this.scheduler.getJobNames(groupName);
//			for (String jobName : jobNames) {
//				System.out.println("jobNames = : " + jobName);
//				this.scheduler.triggerJob(jobName, groupName);
//			}
//		}
//	}
	
	@RequestMapping("/onoff")
	@ResponseBody
	public CommonVO userOpt(HttpServletRequest request, HttpSession session){
		try {
			if("start".equals(request.getParameter("optType"))){
				scheduler.resumeTrigger(request.getParameter("taskID"), Scheduler.DEFAULT_GROUP);
				//scheduler.scheduleJob(new CronTriggerBean());
			} else {
				scheduler.pauseTrigger(request.getParameter("taskID"), Scheduler.DEFAULT_GROUP);
				//scheduler.shutdown();
			}
		}catch (SchedulerException e) {
				e.printStackTrace();
				return new CommonVO(false, "操作失败！");
		}
		return new CommonVO(true, "操作成功！");
	}
	
	@RequestMapping("/taskconf")
	@ResponseBody
	public CommonVO taskConf(BantchConfigVO vo, HttpSession session){
		String cron = "0/10 * * * * ?";
		String taskTimes = "";
		if("true".equals(vo.getMonth()) && !ParamCheck.strEmptyCHK(vo.getMonthDays()) && !ParamCheck.strEmptyCHK(vo.getMonthTime())){
			cron = formatTime("HH:mm:ss", "s m H", vo.getMonthTime()) + " " + vo.getMonthDays() + " * ?";
			taskTimes = "每月" + vo.getMonthDays() + "日" + vo.getMonthTime();
		} else if ("true".equals(vo.getWeek()) && !ParamCheck.strEmptyCHK(vo.getWeekDays()) && !ParamCheck.strEmptyCHK(vo.getWeekTime())){
			cron = formatTime("HH:mm:ss", "s m H", vo.getWeekTime()) + " ? * " + vo.getWeekDays();
			taskTimes = "每周" + vo.getWeekDays() + vo.getWeekTime();
		} else if ("true".equals(vo.getDay()) && null != vo.getDayTime() && vo.getDayTime().length > 0){
			String ms = formatTime("HH:mm:ss", "s m", vo.getDayTime()[0]);
			String hour = "";
			for(int i = 0; i < vo.getDayTime().length; i ++){
				hour += formatTime("HH:mm:ss", "H", vo.getDayTime()[i]);
				if(i < vo.getDayTime().length - 1)
				hour += ",";
			}
			cron = ms + " " + hour + " * * ?";
			taskTimes = "每日" + hour + "时" + formatTime("HH:mm:ss", "m", vo.getDayTime()[0]) + "分" + formatTime("HH:mm:ss", "s", vo.getDayTime()[0]) + "秒";
		} else if ("true".equals(vo.getLoop()) && !ParamCheck.strEmptyCHK(vo.getLoopType()) && !ParamCheck.strEmptyCHK(vo.getInterval())){
			if("H".equals(vo.getLoopType())){
				cron = "0 0 0/" + vo.getInterval() + " * * ?";
				taskTimes = "每" + vo.getInterval() + "小时";
			} else if("M".equals(vo.getLoopType())){
				cron = "0 0/" + vo.getInterval() + " * * * ?";
				taskTimes = "每" + vo.getInterval() + "分钟";
			} else if("S".equals(vo.getLoopType())){
				cron = "0/" + vo.getInterval() + " * * * * ?";
				taskTimes = "每" + vo.getInterval() + "秒";
			}
		} else if ("true".equals(vo.getUserDefine()) && !ParamCheck.strEmptyCHK(vo.getDefineTime())){
			cron = formatTime("yyyy-MM-dd HH:mm:ss", "s m H d M ? yyyy", vo.getDefineTime());
			taskTimes = vo.getDefineTime();
		}
		PropertiesMgr.setProperties("/bantchwork.properties", vo.getTaskID(), cron);
		//TODO this cron should insert into the database. 
		//TODO the taskTimes need insert into the database, which is needed by the task_list.jsp page.
//		TaskListVO inputVO = new TaskListVO(vo.getTaskID(), null, null, null, null, new Date(), taskTimes);
//		service.updateBantchJob(inputVO);
		try {
			updateNotificationInterval(vo.getTaskID(), cron, false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new CommonVO(true, "操作成功！");
	}
	/**
	 * 自定义定时器调度时间
	 * 
	 * @param triggerName
	 *            触发器名称
	 * @throws java.text.ParseException 
	 * @throws Exception
	 */
	public void updateNotificationInterval(String triggerName, String cronExpression, boolean status)
			throws SchedulerException, ParseException, java.text.ParseException {
		// 得到trigger
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(
				triggerName, Scheduler.DEFAULT_GROUP);
		// 得到cron expression
		//String cronExpression = "0/10 * * * * ?";
		// 设置trigger的时间规则
		trigger.setCronExpression(cronExpression);
		// 重置job
		scheduler.rescheduleJob(triggerName, Scheduler.DEFAULT_GROUP, trigger);
		if(status)
			scheduler.pauseTrigger(triggerName, Scheduler.DEFAULT_GROUP);
	}
	public String formatTime(String form1, String form2, String time){
		SimpleDateFormat formatter = new SimpleDateFormat(form1);
		try {
			return new SimpleDateFormat(form2).format(formatter.parse(time));
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
}
