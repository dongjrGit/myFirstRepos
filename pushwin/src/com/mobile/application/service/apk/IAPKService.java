package com.mobile.application.service.apk;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.entity.TBasisApk;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

public interface IAPKService {


	CommonVO qryApks(int indexPage, int indexSize, String dealName);

	CommonVO qryApkById(String dealId);

	CommonVO qryChannel(String busintypeid);

	void saveApkInfo(MultipartFile file, TBasisApk tBasisApk, SessionVO attribute) throws BusinessException, IOException;

	CommonVO removeApkInfo(String dealId);

	CommonVO checkDealName(TBasisApk tBasisApk);

}
