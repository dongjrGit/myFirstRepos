package com.mobile.application.service.device.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.dao.IMapDao;
import com.mobile.application.dao.impl.BaseDAOImpl;
import com.mobile.application.entity.TBasisPosition;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.service.device.IMapService;
import com.mobile.application.vo.session.SessionVO;


@Service
public class MapServiceImpl extends BaseDAOImpl<TBasisPosition> implements IMapService{
	
	@Autowired
	IMapDao mapDao;
	
	@Override
	@Transactional
	public List<?> qryDevicePosition(String userId,String date){
		return mapDao.qryDevicePosition(userId, date);
	}

	@Override
	public void exportExcel(HttpServletResponse response,String excelData) throws IOException, WriteException {
		OutputStream os = response.getOutputStream();// 取得输出流
		response.reset();// 清空输出流
		response.setContentType("application/msexcel");// 定义输出类型

		WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
		WritableSheet wsheet = wbook.createSheet("设备轨迹列表", 0); // sheet名称

		// 设置excel标题
		WritableFont wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat wcfFC = new WritableCellFormat(wfont);
		wcfFC.setBackground(Colour.AQUA);
		
		wsheet.addCell(new Label(0, 2, "所属部门", wcfFC));
		wsheet.addCell(new Label(1, 2, "所在城市", wcfFC));
		wsheet.addCell(new Label(2, 2, "用户账号", wcfFC));
		wsheet.addCell(new Label(3, 2, "用户姓名", wcfFC));
		wsheet.addCell(new Label(4, 2, "记录时间", wcfFC));
		wsheet.addCell(new Label(5, 2, "对应地址", wcfFC));
		JSONArray dateArray = JSONArray.fromObject(excelData);
		response.setHeader("Content-disposition",
				"attachment; filename="+dateArray.optJSONObject(0).optString("USER_CODE")+".xls");// 设定输出文件头
		for (int i = 0; i < dateArray.size(); i++) {
			wsheet.addCell(new Label(0, i + 3, dateArray.optJSONObject(i).optString("ORG_NAME"))); // 所属分行
			wsheet.addCell(new Label(1, i + 3, dateArray.optJSONObject(i).optString("CITY"))); // 所在城市
			wsheet.addCell(new Label(2, i + 3, dateArray.optJSONObject(i).optString("USER_CODE"))); // 用户账号
			wsheet.addCell(new Label(3, i + 3, dateArray.optJSONObject(i).optString("USER_NAME"))); // 用户姓名
			wsheet.addCell(new Label(4, i + 3, dateArray.optJSONObject(i).optString("POSITION_TIME"))); // 记录时间
			wsheet.addCell(new Label(5, i + 3, dateArray.optJSONObject(i).optString("ADDRESS"))); // 记录对应地址
		}
		// 主体内容生成结束
		wbook.write(); // 写入文件
		wbook.close();
		os.close(); // 关闭流
		
	}

	@Override
	@Transactional
	public List<?> qryRealTimePosition(HttpSession session,
			HttpServletRequest request,String orgId) {
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		String day = DateUtil.format(new Date(), "yyyy-MM-dd");
		return mapDao.qryRealTimePosition(day,orgId,sessionVO);
	}
}
