package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisApk;

public interface IAPKDao extends IBaseDAO<TBasisApk> {

	List<?> qryApks(int indexPage, int indexSize, String dealName);

	String qryApksCount(String dealName);

	List qryApkById(String dealId);

	List<?> qryChannel(String busintypeid);

	void removeApkInfo(String substring);

	Long checkDealName(TBasisApk tBasisApk);


}
