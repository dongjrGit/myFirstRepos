package com.mobile.application.service.debit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.service.debit.impl.DebitServiceImpl;

@Service
public class DebitExcelModel {
	public JSONArray readDebitExcelModel() throws IOException{
		String debitModelFile = SpringProperty.props.getProperty("RootPath") + SpringProperty.props.getProperty("debitModelFile");
		Workbook readwb = null;
		InputStream instream = null;
		try
		{
			// 构建Workbook对象, 只读Workbook对象
			// 直接从本地文件创建Workbook
			instream = new FileInputStream(debitModelFile);

			readwb = Workbook.getWorkbook(instream);

			// Sheet的下标是从0开始
			// 获取第一张Sheet表
			Sheet readsheet = readwb.getSheet(0);

//			// 获取Sheet表中所包含的总列数
//			int rsColumns = readsheet.getColumns();

			// 获取Sheet表中所包含的总行数
			int rsRows = readsheet.getRows();

			// 获取指定单元格的对象引用
			JSONArray dataArray = new JSONArray();
			for (int j = 1; j < rsRows; j ++){
				JSONObject dataJson = new JSONObject();
				Cell cell_7 = readsheet.getCell(7, j);
				if(!"Y".equals(cell_7.getContents()))
				continue;
				Cell cell_0 = readsheet.getCell(0, j);
				dataJson.put("ROW_NUM", cell_0.getContents());
				
				Cell cell_1 = readsheet.getCell(1, j);
				dataJson.put("DATA_SOURCE", cell_1.getContents());
				
				Cell cell_2 = readsheet.getCell(2, j);
				dataJson.put("EN_NAME", cell_2.getContents());
				
				Cell cell_3 = readsheet.getCell(3, j);
				dataJson.put("CN_NAME", cell_3.getContents());
				
				Cell cell_4 = readsheet.getCell(4, j);
				dataJson.put("REMARK", cell_4.getContents());
				
				Cell cell_6 = readsheet.getCell(6, j);
				dataJson.put("DB_COLUMN", cell_6.getContents());
				
//				dataJson.put("VALUE", zipMap.get(cell_2.getContents()));
				dataArray.add(dataJson);
			}
			return dataArray;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != readwb)
				readwb.close();
			if(null != instream)
				instream.close();
		}
		return null;
	}
	
//	public static void main(String[] args) throws BusinessException, IOException {
//		DebitExcelModel debitExcelModel = new DebitExcelModel();
//		debitExcelModel.readDebitExcelModel();
//		}
}
