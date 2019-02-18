package com.pushwin.batchwork.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 功能描述：日期工具类
 * 
 * @author 王本�?
 * @Date 2013-11-5
 * @version 1.0
 */
public class DateUtil {

	public static Date date = null;

	public static DateFormat dateFormat = null;

	public static Calendar calendar = null;

	/**
	 * 功能描述：格式化日期
	 * 用法：parseDate("2014-1-4 15:57:00", "yyyy/MM/dd HH:mm:ss")
	 * 
	 * @param dateStr
	 *            String 字符型日�?
	 * @param format
	 *            String 格式
	 * @return Date 日期
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			dateFormat = new SimpleDateFormat(format);
			String dt = dateStr.replaceAll("-", "/");
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
						"0");
			}
			date = (Date) dateFormat.parse(dt);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * 功能描述：格式化日期
	 * 
	 * @param dateStr
	 *            String 字符型日期：YYYY-MM-DD 格式
	 * @return Date
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy/MM/dd");
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时�?yyyy/MM/dd HH:mm:ss 格式
	 */
	public static Date getDateTime(String dateStr) {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return (Date)dateFormat.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}
	
	/**
	 * 功能描述：格式化输出日期
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return 返回字符型日�?
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				dateFormat = new SimpleDateFormat(format);
				result = dateFormat.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}
	
	/**
	 * 功能描述�?
	 * 
	 * @param date
	 *            Date 日期
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 功能描述：返回年�?
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 功能描述：返回月�?
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日�?
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小�?
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分�?
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}
	
	/**
	 * 将YYYYMMDD格式的日期修改为YYYY年MM月DD格式
	 * 
	 * @param date YYYYMMDD格式的日期字符串
	 * @return 格式化后的日�?
	 */
	public static String parseFormat(String date) {
		String d = "";
		if(date != null && date.length() == 8) {
			String year = date.substring(0,4);
			String month = date.substring(4,6);
			String day = date.substring(6);
			if(day.length() == 1) {
				day = "0" + day;
			}
			d = year + "-" + month + "-" + day;
		}else {
			d = format(new Date(), "yyyy-MM-dd");
		}
		return d;
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫�?
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 功能描述：返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日�?yyyy/MM/dd 格式
	 */
	public static String getDate(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 功能描述：返回字符型时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型时�?HH:mm:ss 格式
	 */
	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 功能描述：返回字符型日期时间
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回字符型日期时�?yyyy/MM/dd HH:mm:ss 格式
	 */
	public static String getDateTime(Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 功能描述：日期相�?
	 * 
	 * @param date
	 *            Date 日期
	 * @param day
	 *            int 天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		calendar = Calendar.getInstance();
		long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	/**
	 * 功能描述：日期相�?
	 * 
	 * @param date
	 *            Date 日期
	 * @param date1
	 *            Date 日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}

	/**
	 * 功能描述：取得指定月份的第一�?
	 * 
	 * @param strdate
	 *            String 字符型日�?
	 * @return String yyyy-MM-dd 格式
	 */
	public static String getMonthBegin(String strdate) {
		date = parseDate(strdate);
		return format(date, "yyyy-MM") + "-01";
	}

	/**
	 * 功能描述：取得指定月份的�?���?��
	 * 
	 * @param strdate
	 *            String 字符型日�?
	 * @return String 日期字符�?yyyy-MM-dd格式
	 */
	public static String getMonthEnd(String strdate) {
		date = parseDate(getMonthBegin(strdate));
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		return formatDate(calendar.getTime());
	}

	/**
	 * 功能描述：常用的格式化日�?
	 * 
	 * @param date
	 *            Date 日期
	 * @return String 日期字符�?yyyy-MM-dd格式
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 功能描述：以指定的格式来格式化日�?
	 * 
	 * @param date
	 *            Date 日期
	 * @param format
	 *            String 格式
	 * @return String 日期字符�?
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static String getCurDateTimeStr() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		return new StringBuffer().append(datetime).toString();
	}

	public static String getCurDateTime() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		return (timestamp.toString()).substring(0, 19);
	}

	public static String getCurrentDate() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currentdate = (timestamp.toString()).substring(0, 4) + "-"
				+ (timestamp.toString()).substring(5, 7) + "-"
				+ (timestamp.toString()).substring(8, 10);
		return currentdate;
	}

	// 返回"yyyyMMdd"格式日期
	public static String getDateYYYYMMDD() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currentdate = (timestamp.toString()).substring(0, 4)
				+ (timestamp.toString()).substring(5, 7)
				+ (timestamp.toString()).substring(8, 10);
		return currentdate;
	}

	// 返回"yyMMdd"格式日期
	public static String getDateYYMMDD() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currentdate = (timestamp.toString()).substring(2, 4)
				+ (timestamp.toString()).substring(5, 7)
				+ (timestamp.toString()).substring(8, 10);
		return currentdate;
	}

    //获取�?��时间和结束时间之间的天数
   	public static long getDisDays (String datebegin, String dateend) {
   		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
   		try {
   			Date dateBegin=sdf.parse(datebegin);
   	   		Date dateEnd=sdf.parse(dateend);
   	   		return (dateEnd.getTime()-dateBegin.getTime())/(3600*24*1000) + 1;	
		} catch (Exception e) {
			return 0;
		}
   	}
   	
   	/**
   	 * 比较两个日期相差的分钟数
   	 * 
   	 * @param startDate �?��日期
   	 * @param endDate 结束日期
   	 * @return 结束日期和开始日期之间的分钟�?
   	 */
   	public static long compareMinite(Date startDate, Date endDate){
   		long startMinute = 0;
   		if(startDate != null) {
   			startMinute = startDate.getTime()/(1000*60);
   		}
   		long endMinute = 0;
   		if(endDate != null) {
   			endMinute = endDate.getTime()/(1000*60);
   		}
   		return endMinute - startMinute;
   	}
   	
	/**
   	 * 比较两个日期相差的秒�?
   	 * 
   	 * @param startDate �?��日期
   	 * @param endDate 结束日期
   	 * @return 结束日期和开始日期之间的秒数
   	 */
   	public static long compareSecond(Date startDate, Date endDate){
   		long startMinute = startDate.getTime()/(1000);
   		long endMinute = endDate.getTime()/(1000);
   		return endMinute - startMinute;
   	}
   	
	/**
	 * 日期转化字符�?
	 * @param date
	 * @param format
	 * @param defaultStr
	 * @return
	 */
	public static String dateToString(Date date, String format, String defaultStr){
		String ds = defaultStr;
		if(date == null){
			return defaultStr;
		}else{
			try{
			SimpleDateFormat fmt = new SimpleDateFormat(format);
			ds = fmt.format(date);
			}catch(Exception e){
				return defaultStr;
			}
		}
		return ds;
	}
	
	
	
	/**
	 * �Ͼ���������ʱ��YYMMDD-1
	 * @param date
	 * @param format
	 * @param defaultStr
	 * @return
	 */
	public static String batchTime(){
		 Date dat6 = new Date (new Date().getTime()- (1000L*60L*60L*24L));
		 String date5 = new
		  SimpleDateFormat("yyyyMMdd").format(dat6);
		return date5;
	}
	
	/**
	 * �Ͼ���������ʱ��YYMMDD-1
	 * @param date
	 * @param format
	 * @param defaultStr
	 * @return
	 */
	public static String batchTimeOrg(){
		 Date dat6 = new Date (new Date().getTime()- (1000L*60L*60L*24L));
		 String date5 = new
		  SimpleDateFormat("yyyy-MM-dd").format(dat6);
		return date5;
	}

	public static void main(String[] args) {
		
		//Date date = parseDate("2014-1-4 15:57:00", "yyyy/MM/dd hh:mm:ss");
		Date date = parseDate("2014-1-4");
		System.out.println(date);
		
		 Date dat6 = new Date (new Date().getTime()- (1000L*60L*60L*24L));
		 String date5 = new
		  SimpleDateFormat("yyyyMMdd").format(dat6);
		 String date4 = new
		  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dat6);
		 System.out.println(date4  + date5 );
		 System.out.println(batchTime());
	}

}
