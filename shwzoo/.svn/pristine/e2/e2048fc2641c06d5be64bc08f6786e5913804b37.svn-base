package com.yinlian.wssc.platform.view.controller;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.dto.SaleOrder;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.util.CriteriaDdtj;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;



/**
 * 订单管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/platform/tj")
public class DdtjViewController {
	
@Autowired
private OrderService    orderService;
	
	/**
	 * 直营店铺订单日统计
	 * @return
	 */
	@RequestMapping("/zyddtj_Day")
	public String ddgl_zyOrderList(HttpServletRequest request) {
		return "platform/tj/zyddtj_Day";
	}
	
	/**
	 * 直营店铺最近一周统计
	 * @return
	 */
	@RequestMapping("/zyddtj_Week")
	public String ddgl_zyOrderListWeek(HttpServletRequest request) {
		return "platform/tj/zyddtj_Week";
	}
	
	/**
	 * 直营店铺月统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/zyddtj_Month")
	public String ddgl_zyOrderListMonth(HttpServletRequest request) {
		return "platform/tj/zyddtj_Month";
	}
	
	/**
	 * 直营店铺季度统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/zyddtj_Quarter")
	public String ddgl_zyOrderListQuarter(HttpServletRequest request) {
		return "platform/tj/zyddtj_Quarter";
	}
	
	/**
	 * 直营店铺年统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/zyddtj_Year")
	public String ddgl_zyOrderListYear(HttpServletRequest request) {
		return "platform/tj/zyddtj_Year";
	}
	
	/**
	 * 店铺订单日统计
	 * @return
	 */
	@RequestMapping("/ddtj_Day")
	public String ddgl_OrderList(HttpServletRequest request) {
		return "platform/tj/ddtj_day";
	}
	
	/**
	 * 店铺最近一周统计
	 * @return
	 */
	@RequestMapping("/ddtj_Week")
	public String ddgl_OrderListWeek(HttpServletRequest request) {
		return "platform/tj/ddtj_Week";
	}
	
	/**
	 * 店铺月统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddtj_Month")
	public String ddgl_OrderListMonth(HttpServletRequest request) {
		return "platform/tj/ddtj_Month";
	}
	
	/**
	 * 店铺季度统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddtj_Quarter")
	public String ddgl_OrderListQuarter(HttpServletRequest request) {
		return "platform/tj/ddtj_Quarter";
	}
	
	/**
	 * 店铺年统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/ddtj_Year")
	public String ddgl_OrderListYear(HttpServletRequest request) {
		return "platform/tj/ddtj_Year";
	}
	
	/**
	 * 销售概况
	 * @param request
	 * @return
	 */
	@RequestMapping("/salesProfile")
	public String salesProfile(HttpServletRequest request) {
		return "platform/tj/SalesProfile";
	}
	/**
	 * 销售明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/salesdetail")
	public String salesdetail(HttpServletRequest request) {
		return "platform/tj/salesdetail";
	}
	
	/**
	 * 客户区域分析
	 * @param request
	 * @return
	 */
	@RequestMapping("/buyerArea")
	public String buyerArea(HttpServletRequest request) {
		return "platform/tj/buyerArea";
	}
	
	/**
	 * 客户明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/buyerDetail")
	public String buyerDetail(HttpServletRequest request) {
		return "platform/tj/buyerDetail";
	}
	
	/**
	 * 优惠卷数据分析
	 * @param request
	 * @return
	 */
	@RequestMapping("/couponUse")
	public String couponUse(HttpServletRequest request) {
		return "platform/tj/couponUse";
	}
	
	/**
	 * 组合商品数据分析
	 * @param request
	 * @return
	 */
	@RequestMapping("/packageTJ")
	public String packageTJ(HttpServletRequest request) {
		return "platform/tj/PackageTJ";
	}
	
	/**
	 * 闪购数据分析
	 * @param request
	 * @return
	 */
	@RequestMapping("/spike_SG")
	public String spike_SG(HttpServletRequest request) {
		return "platform/tj/spike_SG";
	}
	
	/**
	 * 退款退货统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/orderReturn")
	public String orderReturn(HttpServletRequest request) {
		return "platform/tj/zyddtj_TH";
	}
	
	/**
	 * 换货统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/orderExchange")
	public String orderExchange(HttpServletRequest request) {
		return "platform/tj/zyddtj_HH";
	}
	/**
	 * 退款退货统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/dporderReturn")
	public String shoporderReturn(HttpServletRequest request) {
		return "platform/tj/dpddtj_TH";
	}
	
	/**
	 * 换货统计
	 * @param request
	 * @return
	 */
	@RequestMapping("/dporderExchange")
	public String shoporderExchange(HttpServletRequest request) {
		return "platform/tj/dpddtj_HH";
	}
	/**
	 * 商品销量统计 -所有站点
	 * @param request
	 * @return
	 */
	@RequestMapping("/productssale")
	public String productssale(HttpServletRequest request) {
		return "platform/tj/spxltj_list";
	}
	@RequestMapping("/pcproductssale")
	public String productssale_pc(HttpServletRequest request) {
		return "platform/tj/spxltj_pc";
	}
	@RequestMapping("/appproductssale")
	public String productssale_app(HttpServletRequest request) {
		return "platform/tj/spxltj_app";
	}
	@RequestMapping("/wapproductssale")
	public String productssale_wap(HttpServletRequest request) {
		return "platform/tj/spxltj_wap";
	}
	
	
	/**
	 * 详细订单统计导出excel
	 * @param request
	 * @param resp
	 * @param shopid
	 * @param datef
	 * @param datet
	 */
	@RequestMapping("/exportddtjdetail")
	public void exportddtj_Detail(HttpServletRequest request,
			HttpServletResponse resp, String shopid,String datef,
			String datet) {
		try {
			XSSFWorkbook workbook1 = new XSSFWorkbook();
			SXSSFWorkbook wb = new SXSSFWorkbook(workbook1, 100);
			request.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/x-download");

			String fileName = "详细订单统计列表.xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			Sheet sheet = wb.createSheet("详细订单统计");
			sheet.setDefaultRowHeight((short) (2 * 256));
			sheet.setColumnWidth(0, 50 * 120);
			sheet.setColumnWidth(1, 50 * 200);
			sheet.setColumnWidth(2, 50 * 120);
			sheet.setColumnWidth(3, 50 * 120);
			sheet.setColumnWidth(4, 50 * 120);
			sheet.setColumnWidth(5, 50 * 120);
			sheet.setColumnWidth(6, 50 * 120);
			sheet.setColumnWidth(7, 50 * 120);
			sheet.setColumnWidth(8, 50 * 120);
			sheet.setColumnWidth(9, 50 * 120);
			sheet.setColumnWidth(10, 50 * 120);
			Font font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);
			Row row = sheet.createRow((int) 0);
			sheet.createRow((int) 1);
			sheet.createRow((int) 2);
			sheet.createRow((int) 3);
			sheet.createRow((int) 4);
			sheet.createRow((int) 5);
			sheet.createRow((int) 6);
			sheet.createRow((int) 7);
			sheet.createRow((int) 8);
			sheet.createRow((int) 9);
			sheet.createRow((int) 10);
			sheet.createRow((int) 11);
			CellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			Cell cell = row.createCell(0);
			cell.setCellValue("店铺名称 ");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("商品名称");
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("订单日期 ");
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("单价（元）");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("数量");
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("已使用（数量/金额）");
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue("未使用（数量/金额）");
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue("已退款（数量/金额） ");
			cell = row.createCell(8);
			cell.setCellStyle(style);
			cell.setCellValue("待付款（数量/金额） ");
			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue("退款中（数量/金额） ");
			cell = row.createCell(10);
			cell.setCellStyle(style);
			cell.setCellValue("出票中（数量/金额） ");
			cell = row.createCell(11);
			cell.setCellStyle(style);
			//店铺名称	商品名称	订单日期	单价（元）	数量	已使用（数量/金额）	未使用（数量/金额）	已退款（数量/金额）
			CriteriaDdtj criteria=new CriteriaDdtj();
	 	    criteria.setId(StringUtilsEX.ToInt(shopid));
	 	    criteria.setType("2");
	 	    criteria.setDatef(StringUtilsEX.ToShortDate(datef));
	 	    criteria.setDatet(StringUtilsEX.ToShortDate(datet));

	 	   List<SaleOrder> dtolist = orderService.selectDpOrderTj(criteria);
			
			if(dtolist==null || dtolist.size()==0)return;
			BigDecimal Money_YSY=new BigDecimal(0);
			BigDecimal Money_DSY=new BigDecimal(0);
			BigDecimal Money_YTK=new BigDecimal(0);
			BigDecimal Money_DFK=new BigDecimal(0);
			BigDecimal MoneyTKSH=new BigDecimal(0);
			BigDecimal Money_CPZ=new BigDecimal(0);
			for (int i = 0; i < dtolist.size(); i++) {
				Row row1 = sheet.createRow((int) i + 1);
				SaleOrder dto = dtolist.get(i);
				row1.createCell(0).setCellValue(dto.getName());
				row1.createCell(1).setCellValue(dto.getProname());
				row1.createCell(2).setCellValue(dto.get_orderDate());
				row1.createCell(3).setCellValue(dto.getProprice().toString());
				row1.createCell(4).setCellValue(dto.getProcount());
				row1.createCell(5).setCellValue(dto.getCount_YSY()+"/"+dto.getMoney_YSY().toString());
				row1.createCell(6).setCellValue(dto.getCount_DSY()+"/"+dto.getMoney_DSY().toString());
				row1.createCell(7).setCellValue(dto.getCount_YTK()+"/"+dto.getMoney_YTK().toString());
				row1.createCell(8).setCellValue(dto.getCount_DFK()+"/"+dto.getMoney_DFK().toString());
				row1.createCell(9).setCellValue(dto.getCount_TKSH()+"/"+dto.getMoney_TKSH().toString());
				row1.createCell(10).setCellValue(dto.getCount_CPZ()+"/"+dto.getMoney_CPZ().toString());
				Money_YSY=Money_YSY.add(dto.getMoney_YSY());
				Money_DSY=Money_DSY.add(dto.getMoney_DSY());
				Money_YTK=Money_YTK.add(dto.getMoney_YTK());
				Money_DFK=Money_DFK.add(dto.getMoney_DFK());
				MoneyTKSH=MoneyTKSH.add(dto.getMoney_TKSH());
				Money_CPZ=Money_CPZ.add(dto.getMoney_CPZ());
			}
			Row rowt = sheet.createRow((int) dtolist.size() + 1);
			rowt.createCell(0).setCellValue("总计");
			rowt.createCell(1).setCellValue("");
			rowt.createCell(2).setCellValue("");
			rowt.createCell(3).setCellValue("");
			rowt.createCell(4).setCellValue(dtolist.stream().mapToInt(x->x.getProcount()).sum());

			rowt.createCell(5).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YSY()).sum()+"/"
			+Money_YSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

			rowt.createCell(6).setCellValue(dtolist.stream().mapToInt(x->x.getCount_DSY()).sum()+"/"
			+Money_DSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

			rowt.createCell(7).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YTK()).sum()+"/"
			+Money_YTK.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		
			rowt.createCell(8).setCellValue(dtolist.stream().mapToInt(x->x.getCount_DFK()).sum()+"/"
			+Money_DFK.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		
			rowt.createCell(9).setCellValue(dtolist.stream().mapToInt(x->x.getCount_TKSH()).sum()+"/"
			+MoneyTKSH.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	
			rowt.createCell(10).setCellValue(dtolist.stream().mapToInt(x->x.getCount_CPZ()).sum()+"/"
			+Money_CPZ.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e) {
			LogHandle.error(LogType.Order, "订单日统计异常! 异常信息:{0}", e,
					"tj/exportddtjday");
		}
	}
	
	/**
	 * 订单日统计导出excel
	 * @param request
	 * @param resp
	 * @param shopid
	 * @param datef
	 * @param datet
	 */
	@RequestMapping("/exportddtjday")
	public void exportddtj_Day(HttpServletRequest request,
			HttpServletResponse resp, String shopid,String datef,
			String datet) {
		try {
			XSSFWorkbook workbook1 = new XSSFWorkbook();
			SXSSFWorkbook wb = new SXSSFWorkbook(workbook1, 100);
			request.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/x-download");

			String fileName = "订单日统计列表.xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			Sheet sheet = wb.createSheet("订单日统计");
			sheet.setDefaultRowHeight((short) (2 * 256));
			sheet.setColumnWidth(0, 50 * 120);
			sheet.setColumnWidth(1, 50 * 200);
			sheet.setColumnWidth(2, 50 * 120);
			sheet.setColumnWidth(3, 50 * 120);
			sheet.setColumnWidth(4, 50 * 120);
			sheet.setColumnWidth(5, 50 * 120);
			sheet.setColumnWidth(6, 50 * 120);
			sheet.setColumnWidth(7, 50 * 120);
			Font font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);
			Row row = sheet.createRow((int) 0);
			sheet.createRow((int) 1);
			sheet.createRow((int) 2);
			sheet.createRow((int) 3);
			sheet.createRow((int) 4);
			sheet.createRow((int) 5);
			sheet.createRow((int) 6);
			sheet.createRow((int) 7);
			sheet.createRow((int) 8);
			CellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			Cell cell = row.createCell(0);
			cell.setCellValue("店铺名称 ");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("商品名称");
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("订单日期 ");
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("单价（元）");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("数量");
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("已使用（数量/金额）");
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue("未使用（数量/金额）");
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue("已退款（数量/金额） ");
			cell = row.createCell(8);
			cell.setCellStyle(style);
			//店铺名称	商品名称	订单日期	单价（元）	数量	已使用（数量/金额）	未使用（数量/金额）	已退款（数量/金额）
			CriteriaDdtj criteria=new CriteriaDdtj();
	 	    criteria.setId(StringUtilsEX.ToInt(shopid));
	 	    criteria.setType("1");
	 	    criteria.setDatef(StringUtilsEX.ToShortDate(datef));
	 	    criteria.setDatet(StringUtilsEX.ToShortDate(datet));

	 	   List<SaleOrder> dtolist = orderService.selectDpOrderTj(criteria);
			
			if(dtolist==null || dtolist.size()==0)return;
			BigDecimal Money_YSY=new BigDecimal(0);
			BigDecimal Money_DSY=new BigDecimal(0);
			BigDecimal Money_YTK=new BigDecimal(0);
			for (int i = 0; i < dtolist.size(); i++) {
				Row row1 = sheet.createRow((int) i + 1);
				SaleOrder dto = dtolist.get(i);
				row1.createCell(0).setCellValue(dto.getName());
				row1.createCell(1).setCellValue(dto.getProname());
				row1.createCell(2).setCellValue(dto.get_orderDate());
				row1.createCell(3).setCellValue(dto.getProprice().toString());
				row1.createCell(4).setCellValue(dto.getProcount());
				row1.createCell(5).setCellValue(dto.getCount_YSY()+"/"+dto.getMoney_YSY().toString());
				row1.createCell(6).setCellValue(dto.getCount_DSY()+"/"+dto.getMoney_DSY().toString());
				row1.createCell(7).setCellValue(dto.getCount_YTK()+"/"+dto.getMoney_YTK().toString());
				Money_YSY=Money_YSY.add(dto.getMoney_YSY());
				Money_DSY=Money_DSY.add(dto.getMoney_DSY());
				Money_YTK=Money_YTK.add(dto.getMoney_YTK());
			}
			Row rowt = sheet.createRow((int) dtolist.size() + 1);
			rowt.createCell(0).setCellValue("总计");
			rowt.createCell(1).setCellValue("");
			rowt.createCell(2).setCellValue("");
			rowt.createCell(3).setCellValue("");
			rowt.createCell(4).setCellValue(dtolist.stream().mapToInt(x->x.getProcount()).sum());
		
			rowt.createCell(5).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YSY()).sum()+"/"
			+Money_YSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			rowt.createCell(6).setCellValue(dtolist.stream().mapToInt(x->x.getCount_DSY()).sum()+"/"
			+Money_DSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			rowt.createCell(7).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YTK()).sum()+"/"
			+Money_YTK.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e) {
			LogHandle.error(LogType.Order, "订单日统计异常! 异常信息:{0}", e,
					"tj/exportddtjday");
		}
	}
	
	/**
	 * 订单季度统计导出excel
	 * @param request
	 * @param resp
	 * @param shopid
	 * @param datef
	 * @param datet
	 */
	@RequestMapping("/exportddtjquarter")
	public void exportddtj_Quarter(HttpServletRequest request,
			HttpServletResponse resp, String shopid,String datef) {
		try {
			XSSFWorkbook workbook1 = new XSSFWorkbook();
			SXSSFWorkbook wb = new SXSSFWorkbook(workbook1, 100);
			request.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/x-download");

			String fileName = "订单季度统计列表.xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			Sheet sheet = wb.createSheet("订单季度统计");
			sheet.setDefaultRowHeight((short) (2 * 256));
			sheet.setColumnWidth(0, 50 * 120);
			sheet.setColumnWidth(1, 50 * 200);
			sheet.setColumnWidth(2, 50 * 120);
			sheet.setColumnWidth(3, 50 * 120);
			sheet.setColumnWidth(4, 50 * 120);
			sheet.setColumnWidth(5, 50 * 120);
			sheet.setColumnWidth(6, 50 * 120);
			sheet.setColumnWidth(7, 50 * 120);
			Font font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);
			Row row = sheet.createRow((int) 0);
			sheet.createRow((int) 1);
			sheet.createRow((int) 2);
			sheet.createRow((int) 3);
			sheet.createRow((int) 4);
			sheet.createRow((int) 5);
			sheet.createRow((int) 6);
			sheet.createRow((int) 7);
			sheet.createRow((int) 8);
			CellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			Cell cell = row.createCell(0);
			cell.setCellValue("店铺名称 ");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("商品名称");
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("年/季度 ");
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("单价（元）");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("数量");
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("已使用（数量/金额）");
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue("未使用（数量/金额）");
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue("已退款（数量/金额） ");
			cell = row.createCell(8);
			cell.setCellStyle(style);
			//店铺名称	商品名称	订单日期	单价（元）	数量	已使用（数量/金额）	未使用（数量/金额）	已退款（数量/金额）
			CriteriaDdtj criteria=new CriteriaDdtj();
	 	    criteria.setId(StringUtilsEX.ToInt(shopid));
	 	    criteria.setType("4");
	 	    String datet=null;
	        String _datef=null;
	 	    String year=datef.substring(0,4); 
	    	   String t=datef.substring(5, 6);
	    	   if(t.equals("1")){
	    		   
	    		  _datef=year+"-01"+"-01";
	    		  datet=year+"-03"+"-31";
	    		  
	    	   }else if(t.equals("2")){
	    		   
	    		  _datef=year+"-04"+"-01";
	    		 datet=year+"-06"+"-30";
	    		 
	    	   }else if(t.equals("3")){
	    		   
	    		  _datef=year+"-07"+"-01";
	    		 datet=year+"-09"+"-30";
	    		 
	    	   }else if(t.equals("4")){
	    		   
	    		  _datef=year+"-10"+"-01";
	    		 datet=year+"-12"+"-31";
	    		 
	    	   }
	 	    criteria.setDatef(StringUtilsEX.ToShortDate(_datef));
	 	    criteria.setDatet(StringUtilsEX.ToShortDate(datet));

	 	   List<SaleOrder> dtolist = orderService.selectDpOrderTj(criteria);
			
			if(dtolist==null || dtolist.size()==0)return;
			BigDecimal Money_YSY=new BigDecimal(0);
			BigDecimal Money_DSY=new BigDecimal(0);
			BigDecimal Money_YTK=new BigDecimal(0);
			for (int i = 0; i < dtolist.size(); i++) {
				Row row1 = sheet.createRow((int) i + 1);
				SaleOrder dto = dtolist.get(i);
				row1.createCell(0).setCellValue(dto.getName());
				row1.createCell(1).setCellValue(dto.getProname());
				row1.createCell(2).setCellValue(datef);
				row1.createCell(3).setCellValue(dto.getProprice().toString());
				row1.createCell(4).setCellValue(dto.getProcount());
				row1.createCell(5).setCellValue(dto.getCount_YSY()+"/"+dto.getMoney_YSY().toString());
				row1.createCell(6).setCellValue(dto.getCount_DSY()+"/"+dto.getMoney_DSY().toString());
				row1.createCell(7).setCellValue(dto.getCount_YTK()+"/"+dto.getMoney_YTK().toString());
				Money_YSY=Money_YSY.add(dto.getMoney_YSY());
				Money_DSY=Money_DSY.add(dto.getMoney_DSY());
				Money_YTK=Money_YTK.add(dto.getMoney_YTK());
			}
			Row rowt = sheet.createRow((int) dtolist.size() + 1);
			rowt.createCell(0).setCellValue("总计");
			rowt.createCell(1).setCellValue("");
			rowt.createCell(2).setCellValue("");
			rowt.createCell(3).setCellValue("");
			rowt.createCell(4).setCellValue(dtolist.stream().mapToInt(x->x.getProcount()).sum());

			rowt.createCell(5).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YSY()).sum()+"/"
			+Money_YSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

			rowt.createCell(6).setCellValue(dtolist.stream().mapToInt(x->x.getCount_DSY()).sum()+"/"
			+Money_DSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

			rowt.createCell(7).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YTK()).sum()+"/"
			+Money_YTK.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e) {
			LogHandle.error(LogType.Order, "订单季度统计异常! 异常信息:{0}", e,
					"tj/exportddtjquarter");
		}
	}
	
	/**
	 * 订单月统计导出excel
	 * @param request
	 * @param resp
	 * @param shopid
	 * @param datef
	 * @param datet
	 */
	@RequestMapping("/exportddtjmonth")
	public void exportddtj_Month(HttpServletRequest request,
			HttpServletResponse resp, String shopid,String datef) {
		try {
			XSSFWorkbook workbook1 = new XSSFWorkbook();
			SXSSFWorkbook wb = new SXSSFWorkbook(workbook1, 100);
			request.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/x-download");

			String fileName = "订单月统计列表.xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			Sheet sheet = wb.createSheet("订单月统计");
			sheet.setDefaultRowHeight((short) (2 * 256));
			sheet.setColumnWidth(0, 50 * 120);
			sheet.setColumnWidth(1, 50 * 200);
			sheet.setColumnWidth(2, 50 * 120);
			sheet.setColumnWidth(3, 50 * 120);
			sheet.setColumnWidth(4, 50 * 120);
			sheet.setColumnWidth(5, 50 * 120);
			sheet.setColumnWidth(6, 50 * 120);
			sheet.setColumnWidth(7, 50 * 120);
			Font font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);
			Row row = sheet.createRow((int) 0);
			sheet.createRow((int) 1);
			sheet.createRow((int) 2);
			sheet.createRow((int) 3);
			sheet.createRow((int) 4);
			sheet.createRow((int) 5);
			sheet.createRow((int) 6);
			sheet.createRow((int) 7);
			sheet.createRow((int) 8);
			CellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			Cell cell = row.createCell(0);
			cell.setCellValue("店铺名称 ");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("商品名称");
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("年/月 ");
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("单价（元）");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("数量");
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("已使用（数量/金额）");
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue("未使用（数量/金额）");
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue("已退款（数量/金额） ");
			cell = row.createCell(8);
			cell.setCellStyle(style);
			//店铺名称	商品名称	订单日期	单价（元）	数量	已使用（数量/金额）	未使用（数量/金额）	已退款（数量/金额）
			CriteriaDdtj criteria=new CriteriaDdtj();
	 	    criteria.setId(StringUtilsEX.ToInt(shopid));
	 	    criteria.setType("3");
	 	    String datet=null;
 	        String _datef=null;
	 	   _datef=datef+"-01";
	 	    int month=Integer.parseInt(new SimpleDateFormat("MM").format(StringUtilsEX.ToShortDate(_datef)));
	 	    int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(StringUtilsEX.ToShortDate(_datef)));
	 	    if(month==4||month==6||month==9||month==11){
	 		    	    datet=datef+"-30";
		    }else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
		    		    datet=datef+"-31";   
		    }else if(month==2){
		    	      if(year%400==0||(year %4==0&&year%100!=0))
		    	    	  datet=datef+"-29";
		    	      else 
		    	    	  datet=datef+"-28";
		    }     
	 	    criteria.setDatef(StringUtilsEX.ToShortDate(_datef));
	 	    criteria.setDatet(StringUtilsEX.ToShortDate(datet));

	 	   List<SaleOrder> dtolist = orderService.selectDpOrderTj(criteria);
			
			if(dtolist==null || dtolist.size()==0)return;
			BigDecimal Money_YSY=new BigDecimal(0);
			BigDecimal Money_DSY=new BigDecimal(0);
			BigDecimal Money_YTK=new BigDecimal(0);
			for (int i = 0; i < dtolist.size(); i++) {
				Row row1 = sheet.createRow((int) i + 1);
				SaleOrder dto = dtolist.get(i);
				row1.createCell(0).setCellValue(dto.getName());
				row1.createCell(1).setCellValue(dto.getProname());
				row1.createCell(2).setCellValue(datef);
				row1.createCell(3).setCellValue(dto.getProprice().toString());
				row1.createCell(4).setCellValue(dto.getProcount());
				row1.createCell(5).setCellValue(dto.getCount_YSY()+"/"+dto.getMoney_YSY().toString());
				row1.createCell(6).setCellValue(dto.getCount_DSY()+"/"+dto.getMoney_DSY().toString());
				row1.createCell(7).setCellValue(dto.getCount_YTK()+"/"+dto.getMoney_YTK().toString());
				Money_YSY=Money_YSY.add(dto.getMoney_YSY());
				Money_DSY=Money_DSY.add(dto.getMoney_DSY());
				Money_YTK=Money_YTK.add(dto.getMoney_YTK());
			}
			Row rowt = sheet.createRow((int) dtolist.size() + 1);
			rowt.createCell(0).setCellValue("总计");
			rowt.createCell(1).setCellValue("");
			rowt.createCell(2).setCellValue("");
			rowt.createCell(3).setCellValue("");
			rowt.createCell(4).setCellValue(dtolist.stream().mapToInt(x->x.getProcount()).sum());

			rowt.createCell(5).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YSY()).sum()+"/"
			+Money_YSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

			rowt.createCell(6).setCellValue(dtolist.stream().mapToInt(x->x.getCount_DSY()).sum()+"/"
			+Money_DSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

			rowt.createCell(7).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YTK()).sum()+"/"
			+Money_YTK.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e) {
			LogHandle.error(LogType.Order, "订单月统计异常! 异常信息:{0}", e,
					"tj/exportddtjmonth");
		}
	}
	
	/**
	 * 订单日统计导出excel
	 * @param request
	 * @param resp
	 * @param shopid
	 * @param datef
	 * @param datet
	 */
	@RequestMapping("/exportddtjyear")
	public void exportddtj_Year(HttpServletRequest request,
			HttpServletResponse resp, String shopid,String datef) {
		try {
			XSSFWorkbook workbook1 = new XSSFWorkbook();
			SXSSFWorkbook wb = new SXSSFWorkbook(workbook1, 100);
			request.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/x-download");

			String fileName = "订单年份统计列表.xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			resp.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			Sheet sheet = wb.createSheet("订单年份统计");
			sheet.setDefaultRowHeight((short) (2 * 256));
			sheet.setColumnWidth(0, 50 * 120);
			sheet.setColumnWidth(1, 50 * 200);
			sheet.setColumnWidth(2, 50 * 120);
			sheet.setColumnWidth(3, 50 * 120);
			sheet.setColumnWidth(4, 50 * 120);
			sheet.setColumnWidth(5, 50 * 120);
			sheet.setColumnWidth(6, 50 * 120);
			sheet.setColumnWidth(7, 50 * 120);
			Font font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 16);
			Row row = sheet.createRow((int) 0);
			sheet.createRow((int) 1);
			sheet.createRow((int) 2);
			sheet.createRow((int) 3);
			sheet.createRow((int) 4);
			sheet.createRow((int) 5);
			sheet.createRow((int) 6);
			sheet.createRow((int) 7);
			sheet.createRow((int) 8);
			CellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			Cell cell = row.createCell(0);
			cell.setCellValue("店铺名称 ");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			cell.setCellValue("商品名称");
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("年份 ");
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("单价（元）");
			cell.setCellStyle(style);
			cell = row.createCell(4);
			cell.setCellValue("数量");
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("已使用（数量/金额）");
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue("未使用（数量/金额）");
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue("已退款（数量/金额） ");
			cell = row.createCell(8);
			cell.setCellStyle(style);
			//店铺名称	商品名称	订单日期	单价（元）	数量	已使用（数量/金额）	未使用（数量/金额）	已退款（数量/金额）
			CriteriaDdtj criteria=new CriteriaDdtj();
	 	    criteria.setId(StringUtilsEX.ToInt(shopid));
	 	    criteria.setType("5");
	 	    String _datef=datef+"-01"+"-01";
	        String datet=datef+"-12"+"-31";
	 	    criteria.setDatef(StringUtilsEX.ToShortDate(_datef));
	 	    criteria.setDatet(StringUtilsEX.ToShortDate(datet));

	 	   List<SaleOrder> dtolist = orderService.selectDpOrderTj(criteria);
			
			if(dtolist==null || dtolist.size()==0)return;
			BigDecimal Money_YSY=new BigDecimal(0);
			BigDecimal Money_DSY=new BigDecimal(0);
			BigDecimal Money_YTK=new BigDecimal(0);
			for (int i = 0; i < dtolist.size(); i++) {
				Row row1 = sheet.createRow((int) i + 1);
				SaleOrder dto = dtolist.get(i);
				row1.createCell(0).setCellValue(dto.getName());
				row1.createCell(1).setCellValue(dto.getProname());
				row1.createCell(2).setCellValue(datef);
				row1.createCell(3).setCellValue(dto.getProprice().toString());
				row1.createCell(4).setCellValue(dto.getProcount());
				row1.createCell(5).setCellValue(dto.getCount_YSY()+"/"+dto.getMoney_YSY().toString());
				row1.createCell(6).setCellValue(dto.getCount_DSY()+"/"+dto.getMoney_DSY().toString());
				row1.createCell(7).setCellValue(dto.getCount_YTK()+"/"+dto.getMoney_YTK().toString());
				Money_YSY=Money_YSY.add(dto.getMoney_YSY());
				Money_DSY=Money_DSY.add(dto.getMoney_DSY());
				Money_YTK=Money_YTK.add(dto.getMoney_YTK());
			}
			Row rowt = sheet.createRow((int) dtolist.size() + 1);
			rowt.createCell(0).setCellValue("总计");
			rowt.createCell(1).setCellValue("");
			rowt.createCell(2).setCellValue("");
			rowt.createCell(3).setCellValue("");
			rowt.createCell(4).setCellValue(dtolist.stream().mapToInt(x->x.getProcount()).sum());
			//Long Money_YSY=dtolist.stream().mapToLong(x->x.getMoney_YSY().longValue()).sum();
			rowt.createCell(5).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YSY()).sum()+"/"
			+Money_YSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			//Long Money_DSY=dtolist.stream().mapToLong(x->x.getMoney_DSY().longValue()).sum();
			rowt.createCell(6).setCellValue(dtolist.stream().mapToInt(x->x.getCount_DSY()).sum()+"/"
			+Money_DSY.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			
			//Long Money_YTK=dtolist.stream().mapToLong(x->x.getMoney_YTK().longValue()).sum();
			rowt.createCell(7).setCellValue(dtolist.stream().mapToInt(x->x.getCount_YTK()).sum()+"/"
			+Money_YTK.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e) {
			LogHandle.error(LogType.Order, "订单年统计异常! 异常信息:{0}", e,
					"tj/exportddtjyear");
		}
	}
}
