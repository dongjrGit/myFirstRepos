package com.pushwin.batchwork.vo;

public class BantchConfigVO {
	//璺戞壒浠诲姟ID
	private String taskID;
	
	//姣忔湀鏍囪瘑
	private String month;
	//閫夋嫨鏃ユ湡
	private String monthDays;
	//閫夋嫨鏃堕棿
	private String monthTime;
	
	//姣忓懆鏍囪瘑
	private String week;
	//閫夋嫨鍛ㄥ嚑
	private String weekDays;
	//閫夋嫨鏃堕棿
	private String weekTime;
	
	//姣忓ぉ瀹氭椂
	private String day;
	private String[] dayTime;
	
	//杞惊
	private String loop;
	private String loopType;
	private String interval;
	
	//鐢ㄦ埛鑷畾涔�
	private String userDefine;
	private String defineTime;
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonthDays() {
		return monthDays;
	}
	public void setMonthDays(String monthDays) {
		this.monthDays = monthDays;
	}
	public String getMonthTime() {
		return monthTime;
	}
	public void setMonthTime(String monthTime) {
		this.monthTime = monthTime;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getWeekDays() {
		return weekDays;
	}
	public void setWeekDays(String weekDays) {
		this.weekDays = weekDays;
	}
	public String getWeekTime() {
		return weekTime;
	}
	public void setWeekTime(String weekTime) {
		this.weekTime = weekTime;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getUserDefine() {
		return userDefine;
	}
	public void setUserDefine(String userDefine) {
		this.userDefine = userDefine;
	}
	public String getDefineTime() {
		return defineTime;
	}
	public void setDefineTime(String defineTime) {
		this.defineTime = defineTime;
	}
	public String getLoopType() {
		return loopType;
	}
	public void setLoopType(String loopType) {
		this.loopType = loopType;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String[] getDayTime() {
		return dayTime;
	}
	public void setDayTime(String[] dayTime) {
		this.dayTime = dayTime;
	}
	public String getLoop() {
		return loop;
	}
	public void setLoop(String loop) {
		this.loop = loop;
	}
}
