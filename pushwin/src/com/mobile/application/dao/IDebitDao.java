package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisDebit;

public interface IDebitDao extends IBaseDAO<TBasisDebit> {

	List<?> qryDebitCheckList(int indexPage, int indexSize,String name, String status, String startTime, String endTime, String accountType);

	String qryDebitCheckCount(String name,String status, String startTime, String endTime,String accountType);

	List<?> qryDebitCheckRecord(String debitId);

}
