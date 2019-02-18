package com.pushwin.batchwork.dao;

import java.util.List;

import com.pushwin.batchwork.model.HisBantchwork;
import com.pushwin.batchwork.model.TBantchwork;

public interface IRescheduleJobDAO extends IBaseDAO<TBantchwork>{
	public List<?> query(String taskID);
	public void delTaskHistory(String taskID);
	public void addHistory(HisBantchwork hisBantchwork);
}
