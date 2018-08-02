package com.mobile.application.service.device;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WriteException;

public interface IMapService {

	List<?> qryDevicePosition(String userId, String date);

	void exportExcel(HttpServletResponse response,String excelData) throws IOException, WriteException;

	List<?> qryRealTimePosition(HttpSession session, HttpServletRequest request, String orgId);

}
