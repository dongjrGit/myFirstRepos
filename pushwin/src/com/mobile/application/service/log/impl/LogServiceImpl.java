package com.mobile.application.service.log.impl;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.dao.ILogDao;
import com.mobile.application.entity.TBasisLogDetail;
import com.mobile.application.entity.TBasisLogType;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.service.log.ILogService;
import com.mobile.application.vo.CommonVO;

@Service
public class LogServiceImpl implements ILogService {
	@Autowired
	private ILogDao logDao;

	/* (non-Javadoc)
	 * @see com.mobile.application.service.log.ILogService#qryLogType()
	 */
	@Transactional(readOnly = true)
	@Override
	public CommonVO qryLogType(String modelName) {
		List<?> tBasisLogTypes =  logDao.qryLogType(modelName);
		return new CommonVO(true, tBasisLogTypes, null);
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.log.ILogService#qryLogTypeMap()
	 */
	@Transactional(readOnly = true)
	@Override
	public Map<String, TBasisLogType> qryLogTypeMap() {
		Map<String, TBasisLogType> logTypeMap = new HashMap<String, TBasisLogType>();
		List<TBasisLogType> tBasisLogTypes =  (List<TBasisLogType>) logDao.get(TBasisLogType.class);
		for (TBasisLogType tBasisLogType : tBasisLogTypes) {
			logTypeMap.put(tBasisLogType.getUrl(), tBasisLogType);
		}
		return logTypeMap;
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.log.ILogService#saveLogType(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public CommonVO saveLogType(String url, String modelName, String operaName,
			String description, String optioin) {
		TBasisLogType tBasisLogType = new TBasisLogType(url, modelName, operaName, description);
		if("edit".equals(optioin)){
			logDao.update(tBasisLogType);
		} else if("add".equals(optioin)) {
			logDao.save(tBasisLogType);
		}
		return new CommonVO(true, "操作成功");
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.log.ILogService#removeLogType(java.lang.String)
	 */
	@Override
	@Transactional
	public CommonVO removeLogType(String uri) {
		TBasisLogType tBasisLogType = new TBasisLogType(uri);
		logDao.delete(tBasisLogType);
		return new CommonVO(true, "操作成功");
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.log.ILogService#saveLogDetail(com.mobile.application.entity.TBasisLogDetail)
	 */
	@Override
	@Transactional
	public void saveLogDetail(TBasisLogDetail tBasisLogDetail) {
		logDao.save(tBasisLogDetail);
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.log.ILogService#qryLogDetail(com.mobile.application.entity.TBasisLogDetail)
	 */
	@Override
	@Transactional
	public Map<String, Object> qryLogDetail(int indexPage,int indexSize,String modelName, String userCode,String startTime,String endTime) {
		List<?> list = this.logDao.qryLogDetail(indexPage,indexSize,modelName,userCode,startTime,endTime);
		List<?> listSize = this.logDao.qryLogDetail(modelName,userCode,startTime,endTime);
		Map<String ,Object> map = new HashMap<String ,Object>();
		map.put("data", list);
		map.put("total", listSize.size());
		return map;
	}

	@Override
	@Transactional
	public void exportLogReportExcel(HttpServletResponse response,String modelName,String userCode,String startTime,String endTime) {
		 WritableWorkbook wwb;
	      try {    
	    	  response.setHeader("Content-disposition", "attachment; filename=Excel.xls");// 设定输出文件头   
	    	  response.setContentType("application/vnd.ms-excel");// 定义输出类型 
	    	  OutputStream os = response.getOutputStream();// 取得输出流   
	    	  wwb = Workbook.createWorkbook(os);
	    	  WritableSheet ws = wwb.createSheet("操作日志表",10);// 创建一个工作表
	    	  //设置单元格的文字格式
	    	  WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false);
	    	  WritableFont wf_top = new WritableFont(WritableFont.createFont("宋体"),11, WritableFont.NO_BOLD, false);
	    	  WritableCellFormat wcf = new WritableCellFormat(wf);
	    	  WritableCellFormat wcf_top = new WritableCellFormat(wf_top);
	    	  wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
	    	  wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
	    	  wcf.setAlignment(Alignment.LEFT); 
	    	  wcf_top.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
	    	  wcf_top.setVerticalAlignment(VerticalAlignment.CENTRE); 
	    	  wcf_top.setAlignment(Alignment.CENTRE); 
	    	  wcf_top.setWrap(true);  
	    	  ws.setColumnView(0,25);
			  ws.setColumnView(1,25);
			  ws.setColumnView(2,25);
			  ws.setColumnView(3,25);
			  ws.setColumnView(4,25);
			  ws.setColumnView(5,25);
	    	  int vv=0;
	    	  ws.addCell(new Label(vv,0,"模块名称",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"操作描述",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"操作人工号",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"操作人",wcf_top));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"操作时间",wcf_top));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"IP",wcf_top));
	    	  vv++;
	    	  List<?> list = this.logDao.getExportLog(modelName, userCode, startTime, endTime);
	    	  if(list != null && list.size()>0){
	    		  for (int i = 0; i < list.size(); i++){
	    			  int vvv=0;
	    			  String id = list.get(i).toString();
	    			  TBasisLogDetail tlog = (TBasisLogDetail)logDao.getByID(TBasisLogDetail.class, id);
	    			  ws.addCell(new Label(vvv,i+1,tlog.getTBasisLogType().getModelName()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,tlog.getDescription()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,tlog.getUserCode()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,tlog.getUserName()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,tlog.getUpdateTime()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,tlog.getUserIp()+"",wcf));
	    			  vvv++;
	    		}
	    	  }
	    	  wwb.write();
           wwb.close();
	      }catch(Exception e){
	    	  e.printStackTrace();
	      } 
		
	}
}
