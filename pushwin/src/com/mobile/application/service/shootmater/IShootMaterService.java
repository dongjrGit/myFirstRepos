package com.mobile.application.service.shootmater;

import javax.servlet.http.HttpSession;

import com.mobile.application.vo.CommonVO;

import java.util.List;

public interface IShootMaterService {


	List<?> qryShootMater(HttpSession session, String type);

	CommonVO saveShootMater(HttpSession session, String materId,
			String materPid, String materLevel, String materName,
			String shootRequire,String materType);

	CommonVO delShootMater(HttpSession session, String materId,
			String materIdPid);
	public List<?> qryShootingDict(String lastUpdateTime);
}
