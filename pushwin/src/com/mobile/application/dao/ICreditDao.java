package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisCredit;

public interface ICreditDao extends IBaseDAO<TBasisCredit>{
	public List<?> qryCreditCheckList(int indexPage, int indexSize,String name,String creditKind, String status, String startTime, String endTime);
	public String qryCreditCheckCount(String name, String creditKind, String status, String startTime, String endTime);
	public List<?> qrycreditCheckRecord(String creditId);
}
