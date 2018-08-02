package com.mobile.application.service.checkcard;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.vo.CommonVO;

public interface ICheckCardService {

	void exportModle(HttpServletResponse response, String modleCode) throws BusinessException;

	CommonVO importmodle(MultipartFile file, HttpSession session,
			HttpServletRequest request, String modleCode) throws BusinessException, IOException;

	List<?> qrymodle();
}
