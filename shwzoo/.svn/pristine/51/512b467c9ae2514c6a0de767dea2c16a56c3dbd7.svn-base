package com.yl.soft.uitl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.commons.lang3.StringUtils;
/**
 * 基本类型转换的工具类
 * @author Administrator
 * @version $Id: BasicConvert.java, v 0.1 2016年3月2日 上午9:49:07 Administrator Exp $
 */
public class BasicConvert {

    /**
     * String转Integer类型
     * 
     * @param source
     * @return
     * @throws NumberFormatException
     */
    public static Integer string2Integer(String source) {
        String proxy = source.trim();
        Integer target = -1;
        try {
            if (proxy != null && StringUtils.isNotBlank(proxy)) {
                target = Integer.valueOf(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * Integer转String类型
     * 
     * @param source
     * @return
     */
    public static String integer2String(Integer source) {
        String target = "";
        if (source != null) {
            target = String.valueOf(source);
        }
        return target;
    }

    /**
     * String 转Date
     * 
     * @param source
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String source) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String proxy = source;
        Date date = null;
        if (proxy != null && StringUtils.isNotBlank(proxy)) {
            date = sdf.parse(proxy);
        }
        return date;
    }

    /**
     * 日期类型的转String
     * 
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String target = "";
        if (date != null) {
            target = sdf.format(date);
        }
        return target;
    }

    /**
     * String 转BigDecimal
     * 
     * @param source
     * @return
     */
    public static BigDecimal string2BigDecimal(String source) {
        String proxy = source;
        BigDecimal decimal = null;
        if (proxy != null && StringUtils.isNotBlank(proxy)) {
            decimal = new BigDecimal(proxy);
        }
        return decimal;
    }

    /**
     * BigDecimal 转String
     * 
     * @param source
     * @return
     */
    public static String bigDecimal2String(BigDecimal source) {
        String target = null;
        if (source != null) {
            source = source.setScale(2, BigDecimal.ROUND_HALF_UP);
            target = source.toString();
        }
        return target;
    }

    /**
     * string转float
     * 
     * @param source
     * @return
     */
    public static float toFloat(String source) {
        float f = 0f;
        if (com.yl.soft.uitl.StringUtils.isNotNull(source)) {
            f = Float.valueOf(source);
        }
        return f;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(string2Integer(""));
        System.out.println(integer2String(1));
        System.out.println(string2BigDecimal("23.254"));
        System.out.println(string2BigDecimal("23.254"));
    }
}