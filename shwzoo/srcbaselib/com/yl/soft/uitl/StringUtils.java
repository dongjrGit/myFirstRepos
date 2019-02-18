package com.yl.soft.uitl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StringUtils {

    private static int counter = 0;

    /**
     * 判断字符串不为空 返回 true
     *            为空 返回  false
     * @param charset
     * @return
     */
    public static boolean isNotNull(String charset) {
        boolean bool = false;
        if (charset != null && !"".endsWith(charset.trim()) && !"null".equals(charset)) {
            bool = true;
        }
        return bool;
    }

    /**
     * 判断字符串为空 返回true
     *           
     * @param charset
     * @return
     */
    public static boolean isBlanck(String charset) {
        boolean bool = false;
        if (!isNotNull(charset)) {
            bool = true;
        }
        return bool;
    }

    /**
     * 判断包含特殊字符的个数
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static int countStr(String soruce, String tagert) {
        if (soruce.indexOf(tagert) == -1) {
            return 0;
        } else if (soruce.indexOf(tagert) != -1) {
            counter++;
            countStr(soruce.substring(soruce.indexOf(tagert) + tagert.length()), tagert);
            return counter;
        }
        return 0;
    }

    /**
     * 判断是否含有字符串
     * 
     * @param source
     * @return
     */
    public static boolean decide(String source) {
        boolean bool = false;
        if (isNotNull(source)) {
            Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
            bool = pattern.matcher(source.trim()).find();
        }
        return bool;
    }

    public static int calidator(String source) {
        if (isNotNull(source)) {
            String[] a = source.split(",");
            return a.length;
        }
        return 0;
    }

    /**
     * 判断字符串是否为数字
     * 
     * @param source
     * @return
     */
    public static boolean isInteger(String source) {
        boolean bool = false;
        if (isNotNull(source)) {
            try {
                Integer x = Integer.parseInt(source.trim());
                if (x != null) {
                    bool = true;
                }
            } catch (NumberFormatException e) {
                return bool;
            }
        }
        return bool;
    }
    /**
     * 网址验证
     * 
     * @param url
     * @return
     */
    public static boolean checkUrl(String url){
		 
		 return url.matches("^((https|http|ftp|rtsp|mms)?://)"
		      + "+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
		      + "(([0-9]{1,3}\\.){3}[0-9]{1,3}"
		      + "|"
		      + "([0-9a-z_!~*'()-]+\\.)*"
		      + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."
		      + "[a-z]{2,6})"
		      + "(:[0-9]{1,4})?"
		      + "((/?)|"
		      + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");
		 }
    /**
     * 正则判断是否都是数字
     * 
     * @param strNum
     * @return
     */
    public static boolean isDigit(String strNum) {  
	    return strNum.matches("[0-9]{1,}");  
	} 

    public static void main(String[] args) {

        String s = "a:1;b:2;c:3|e:4;f:5;h:6|";

        String[] array = s.split("\\|");
        for (String da : array) {
            String[] erry = da.split(";");
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (String string : erry) {
                Map<String, String> map = new HashMap<String, String>();
                int index = string.indexOf(":");
                String t = string.substring(0, index);
                System.out.println(t);
                string = string.substring(index + 1);
                System.out.println(string);
                map.put(t, string);
                list.add(map);
                System.out.println(map);
            }
            System.out.println(list.size());
        }

    }

}
