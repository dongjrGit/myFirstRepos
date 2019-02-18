package com.yinlian.wssc.web.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.yinlian.Enums.ChannelTypeEnum;

public class StringUtilsEX extends StringUtils {

    /**
     * 字符串转Integer
     * 
     * @param source
     * @return 值或-1
     */
    public static Integer ToInt(String source) {
        if (source == null) {
            return -1;
        }
        String proxy = source.trim();
        Integer target = -1;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Integer.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转Integer
     * 
     * @param source
     * @return 值或null
     */
    public static Integer ToIntNull(String source) {
        if (source == null) {
            return null;
        }
        String proxy = source.trim();
        Integer target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Integer.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成日期+时间格式
     * 
     * @param source
     * @return
     * @throws ParseException
     */
    public static Date ToDate(String source) throws ParseException {

        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String proxy = source;
        Date date = null;
        if (proxy != null && isNotBlank(proxy)) {
            date = dataformat.parse(proxy);
        }
        return date;
    }

    /**
     * 字符串转换成日期+时间格式
     * 
     * @param source
     * @return
     * @throws ParseException
     */
    public static Date ToDateNull(String source) throws ParseException {

        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String proxy = source;
        Date date = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                date = dataformat.parse(proxy);
            }
        } catch (Exception ex) {

        }
        return date;
    }

    /**
     * 字符串转换成日期格式
     * 
     * @param source
     * @return
     * @throws ParseException
     */
    public static Date ToShortDate(String source) throws ParseException {
        SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
        String proxy = source;
        Date date = null;
        if (proxy != null && isNotBlank(proxy)) {
            date = dataformat.parse(proxy);
        }
        return date;
    }

    /**
     * 日期转换成Long
     * 
     * @param source
     * @return 返回不能为空
     */
    public static Long ToLong(String source) {
        String proxy = source;
        Long target = -1l;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Long.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 日期转换成Long
     * 
     * @param source
     * @return 返回可能为空
     */
    public static Long ToLongNull(String source) {
        String proxy = source;
        Long target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Long.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成double
     * 
     * @param source
     * @return 返回值不能为空
     */
    public static Double ToDouble(String source) {
        String proxy = source;
        Double target = -1.0;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Double.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成double
     * 
     * @param source
     * @return 返回值可能为空
     */
    public static Double ToDoubleNull(String source) {
        String proxy = source;
        Double target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Double.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成ToDecimal（用于金额）
     * 
     * @param source
     * @return 返回值不能为空
     */
    public static BigDecimal ToDecimal(String source) {
        BigDecimal df = new BigDecimal("0.00");
        String proxy = source;
        Double target = -1.00;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Double.valueOf(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        df = new BigDecimal(target);
        df = df.setScale(2, BigDecimal.ROUND_HALF_UP);
        return df;
    }

    /**
     * 字符串转换成ToDecimal（用于金额）
     * 
     * @param source
     * @return 返回值可能为空
     */
    public static BigDecimal ToDecimalNull(String source) {
        BigDecimal df = new BigDecimal("0.00");
        String proxy = source;
        Double target = 0.00;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Double.valueOf(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        df = new BigDecimal(target);
        df = df.setScale(2, BigDecimal.ROUND_HALF_UP);
        return df;
    }

    /**
     * 字符串转换成ToBoolean
     * 
     * @param source
     * @return 返回值不能为空
     */
    public static Boolean ToBoolean(String source) {
        String proxy = source;
        Boolean target = false;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Boolean.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 0 ，1 转换成功boolean
     * 
     * @param source
     * @return
     */
    public static Boolean toBooleanForOS(String source) {
        String proxy = source;
        Boolean target = false;
        if (com.yinlian.wssc.web.util.StringUtils.isNotNull(proxy)) {
            if ("0".equals(proxy)) {
                target = false;
            } else if ("1".equals(proxy)) {
                target = true;
            }
        }
        return target;
    }

    /**
     * 字符串转换成ToBoolean
     * 
     * @param source
     * @return 返回值能为空
     */
    public static Boolean ToBooleanNull(String source) {
        String proxy = source;
        Boolean target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Boolean.valueOf(proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 字符串转换成float
     * 
     * @param source
     * @return 返回值不能为空
     */
    public static Float ToFloat(String source) {
        if (source == null) {
            return -1.0f;
        }
        String proxy = source;
        Float target = -1.0f;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Float.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 字符串转换成float
     * 
     * @param source
     * @return 返回值可能为空
     */
    public static Float ToFloatNull(String source) {
        String proxy = source;
        Float target = null;
        try {
            if (proxy != null && isNotBlank(proxy)) {
                target = Float.valueOf(proxy);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }

    /**
     * 判断字符串是否Null或空字符串
     * 
     * @param source
     * @return 返回值为True 是否Null或空字符串 否则返回false
     */
    public static boolean IsNullOrWhiteSpace(String source) {
        if (source == null || ("").equals(source)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(BasicConvert.string2Integer("4 "));
    }

    /**
     * 判断通道是否存在
     * @param ch
     * @return
     */
    public static boolean isChannelTypeExist(String ch) {
        Integer v = ToIntNull(ch);
        if (v == null)
            return false;
        for (ChannelTypeEnum e : ChannelTypeEnum.values()) {
            if (e.getValue() == v)
                return true;
        }
        return false;
    }
    public static boolean isDate(String dateString){
    	boolean isret=false;
    	try{
    	    SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		dataformat.parse(dateString);
    		isret=true;
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return isret;
    }
    
    /**
     * 检验用户名是否合法
     * @param username
     * @return
     */
    public static Boolean checkUserName(String username) {
    	String  regx="^[a-zA-Z][a-zA-Z0-9_]{2,16}$";
    	Pattern	p = Pattern.compile(regx); 
    	return p.matcher(username).matches();
	}
    /**
     * Date类型转换String类型
     * @param date
     * @return
     */
    public static String toStringDateTime(Date date){
    	 SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	 if (date!=null) {
			return dataformat.format(date);
    	 }else{
			return "";
    	 }
    }
    
    /*
    * Date类型转换String类型
    * @param date
    * @return
    */
   public static String toShortStringDateTime(Date date){
   	 SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
   	 if (date!=null) {
			return dataformat.format(date);
   	 }else{
			return "";
   	 }
   }

    /**
     * 字符串转数组 逗号分割
     * @param val
     * @return
     */
	public static String [] toArray(String val) {
		try {
			return val.split(",");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
