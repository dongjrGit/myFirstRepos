package com.yl.soft.uitl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.time.DateUtils;


/**
 * date 转成string
 * @author Administrator
 * @version $Id: DateUtil.java, v 0.1 2016年3月12日 下午12:41:19 Administrator Exp $
 */
public class DateUtil extends DateUtils {

    /**
     * 将date转换成string
     * pattern yyyy-MM-dd HH:ss:mm
     * @param date
     * @return
     */
    public static String dateConvert(Date date) {
        String tagert = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date != null) {
            tagert = format.format(date);
        }
        return tagert;
    }

    /**
     * date 转String
     * pattern ：yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static String datePattren(Date date) {
        String tagert = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (date != null) {
            tagert = format.format(date);
        }
        return tagert;
    }

    /**
     * String 转date 
     * pattern ：yyyy-MM-dd
     * @param tagert
     * @return
     */
    public static Date stringConvert(String tagert) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (StringUtils.isNotNull(tagert)) {
                date = format.parse(tagert);
            }
        } catch (ParseException e) {
            System.out.println("日期转换异常：" + e.getMessage());
        }
        return date;
    }

    /**
     * 获取当前日期
     * 
     * @param pattern
     * @return
     */
    public static String getCurrentDateStr(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
        return format.format(date);
    }

    public static HashMap<String, Integer> getFormDate(Date date) {
        String birthday = datePattren(date);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        if (StringUtils.isNotNull(birthday)) {
            String[] arry = birthday.split("-");
            map.put("year", Integer.valueOf(arry[0]));
            map.put("month", Integer.valueOf(arry[1]));
            map.put("day", Integer.valueOf(arry[2]));
        }
        return map;
    }

    /**
     * 获取生日的年
     * 
     * @param date
     * @return
     */
    public static Integer getYear(Date date) {

        return getFormDate(date).get("year");
    }

    /**
     * 获取生日 的月
     * 
     * @param date
     * @return
     */
    public static Integer getMonth(Date date) {

        return getFormDate(date).get("month");
    }

    /**
     * 获取生日的日
     * 
     * @param date
     * @return
     */
    public static Integer getDay(Date date) {

        return getFormDate(date).get("day");
    }

    public static Date toDate(Timestamp tt) {
        return new Date(tt.getTime());
    }

    /**
     * 获取当前日期的起一个月日期
     * 
     * @return
     */
    public static Date getPreviousMonth() {
        Date date = new Date();
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        //System.out.println(format.format(calendar.getTime()));
        return calendar.getTime();
    }

    /**
     * 获取当前日期的后一个的日期
     * 
     * @return
     */
    public static Date getAfterMonth() {
        Date date = new Date();
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, +1);
        //System.out.println(format.format(calendar.getTime()));
        return calendar.getTime();
    }

    /**
     * 获取当前日期前一天的日期
     * 
     * @return
     */
    public static Date getPreviousDay() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(format.format(calendar.getTime()));
        return calendar.getTime();
    }

    /**
     * 获取当前日期的后一天日期
     * 
     * @return
     */
    public static Date getAfterDay() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        System.out.println(format.format(calendar.getTime()));
        return calendar.getTime();
    }

    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取一天开始的时间
     *@return
     */
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取一天结束的时间
     *@return
     */
    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 判断传递时间 是否在当天的一天内
     *@return
     */
    public static boolean decideCurrentTime(Date date) {
        boolean bool = false;
        Date startDate = getStartTime();
        Date endDate = getEndTime();
        if (startDate.getTime() < date.getTime() && date.getTime() < endDate.getTime()) {
            bool = true;
        }
        return bool;
    }

    public static void main(String[] args) {

        System.out.println("当天开始的时间：" + getStartTime());
        System.out.println("当天结束的时间：" + getEndTime());
        Date date = new Date();
        System.out.println("当前时间：" + date);
        System.out.println(decideCurrentTime(date));

        System.out.println(decideCurrentTime(getAfterDay()));
    }
}
