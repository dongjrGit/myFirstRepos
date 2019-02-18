package com.yinlian.Extended;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonStatus_Model extends JsonStatus_Base_Model   {
	public JsonStatus_Model() {
		TitleList = "";
		Page = "";	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		StartTime = df.format(new Date());// new Date()为获取当前系统时间
	}
//
	private String Page;
	private int PageSize;
	private int MaxRow;
	private int PageIndex;
	private String TitleList;
	private String StartTime;
	private String Sum;

	public int getMaxRow() {
		return MaxRow;
	}

	public void setMaxRow(int maxRow) {
		MaxRow = maxRow;
	}

	public int getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(int pageIndex) {
		PageIndex = pageIndex;
	}

	public String getTitleList() {
		return TitleList;
	}

	public void setTitleList(String titleList) {
		TitleList = titleList;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getSum() {
		return Sum;
	}

	public void setSum(String sum) {
		Sum = sum;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public String getPage() {
		return Page;
	}

	public void setPage(String page) {
		Page = page;
	}

}
