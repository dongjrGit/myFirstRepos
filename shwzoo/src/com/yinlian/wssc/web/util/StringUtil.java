package com.yinlian.wssc.web.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wizzer.cn
 * @time 2012-9-13 上午10:54:04
 */
public class StringUtil {
    private static StringUtil stringutil;

    public static synchronized StringUtil getInstance() {
        if (stringutil == null) {
            stringutil = new StringUtil();
        }
        return stringutil;
    }

    /**
     * 返回文件大小，单位MB
     *
     * @param filesize
     * @param scale
     * @return
     */
    public static double getFileSize(long filesize, int scale) {
        BigDecimal bd1 = new BigDecimal(Long.toString(filesize));
        BigDecimal bd2 = new BigDecimal(Long.toString(1024));
        return bd1.divide
                (bd2, scale, BigDecimal.ROUND_HALF_UP).divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 返回文件大小，单位MB
     *
     * @param filesize
     * @param scale
     * @return
     */
    public static double getFileSizeKB(long filesize, int scale) {
        BigDecimal bd1 = new BigDecimal(Long.toString(filesize));
        BigDecimal bd2 = new BigDecimal(Long.toString(1024));
        return bd1.divide
                (bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将字符串类型转换为整数类型
     *
     * @param str
     */
    public static int StringToInt(String str) {
        return StringToInt(str, 0);
    }

    /**
     * 将字符串类型转换为整数类型，出错时有def值返回
     *
     * @param str
     * @param def
     */
    public static int StringToInt(String str, int def) {
        int intRet = def;
        try {
            if (str == null || str.trim().equals(""))
                str = def + "";
            intRet = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return intRet;
    }

    /**
     * 将字符串类型转换为浮点类型
     *
     * @param str
     */
    public static float StringToFloat(String str) {
        return StringToFloat(str, 0);
    }

    /**
     * 将字符串类型转换为浮点类型，出错时有def值返回
     *
     * @param str
     * @param def
     */
    public static float StringToFloat(String str, float def) {
        float fRet = def;
        try {
            if (str == null || str.trim().equals(""))
                str = "0";
            fRet = Float.parseFloat(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return fRet;
    }


    /**
     * 将字符串类型转换为双精度类型
     *
     * @param str
     */
    public static double StringToDouble(String str) {
        return StringToDouble(str, 0);
    }


    /**
     * 将字符串类型转换为双精度类型，出错时有def值返回
     *
     * @param str
     * @param def
     */
    public static double StringToDouble(String str, double def) {
        double dRet = def;
        try {
            if (str == null || str.trim().equals(""))
                str = "0";
            dRet = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return dRet;
    }

    /**
     * 将字符串类型转换为双精度类型
     *
     * @param str
     */
    public static long StringToLong(String str) {
        return StringToLong(str, 0);
    }

    /**
     * 将字符串类型转换为双精度类型，出错时有def值返回
     *
     * @param str
     * @param def
     */
    public static long StringToLong(String str, long def) {
        long dRet = def;
        try {
            if (str == null || str.trim().equals(""))
                str = "0";
            dRet = Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return dRet;
    }

      

    public static String getUtf8FromGBK(String s) {
        String str = "";
        try {
            if (s != null)
                str = new String(s.getBytes("GBK"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        return str;

    }

    public static String getGBKFromUtf8(String s) {
        String str = "";
        try {
            if (s != null)
                str = new String(s.getBytes("UTF-8"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        return str;

    }

    /**
     * 将对象转换为字符型
     *
     * @param s
     */
    public static String null2String(Object s) {
        return s == null || s.equals("null") || s.equals("NULL") ? "" : s.toString();
    }

    /**
     * 将对象转换为字符型
     *
     * @param s
     */
    public static String null2StringToZero(Object s) {
        return s == null || s.equals("null") || s.equals("NULL") || s.equals("") ? "0" : s.toString();
    }

    /**
     * 将字符串数组转换为（'a','b'）的格式后返回，来方便数据库的操作
     *
     * @param names
     * @return String
     */
    public static String getStrsplit(String[] names) {
        if (names == null || names.length == 0)
            return "()";
        String result = "(";
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1)
                result = result + "'" + names[i] + "'";
            else
                result = result + "'" + names[i] + "',";
        }
        result = result + ")";
        return result;
    }

    /**
     * 将字符串数组转换为（'a','b'）的格式后返回，来方便数据库的操作
     *
     * @param names
     * @return String
     */
    public static String getStrsplit(List<String> names) {
        if (names == null || names.size() == 0)
            return "('')";
        String result = "(";
        for (int i = 0; i < names.size(); i++) {
            if (i == names.size() - 1)
                result = result + "'" + names.get(i) + "'";
            else
                result = result + "'" + names.get(i) + "',";
        }
        result = result + ")";
        return result;
    }

    /**
     * 将整型数组转换为（1，2）的格式后返回，来方便数据库的操作
     *
     * @param ids
     * @return String
     */
    public static String getIdsplit(Integer[] ids) {
        if (ids == null || ids.length == 0)
            return "()";
        String result = "(";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1)
                result = result + ids[i];
            else
                result = result + ids[i] + ",";
        }
        result = result + ")";
        return result;
    }

    /**
     * 将整型数组转换为（1，2）的格式后返回，来方便数据库的操作
     *
     * @param ids
     * @return String
     */
    public static String getIdsplit(String[] ids) {
        if (ids == null || ids.length == 0)
            return "('')";
        String result = "(";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1)
                result = result + ids[i];
            else
                result = result + ids[i] + ",";
        }
        result = result + ")";
        return result;
    }

    /**
     * 将向量转换为（1，2）的格式后返回，来方便数据库的操作
     *
     * @param ids
     * @return String
     */
    public static String getIdsplit(ArrayList<String> ids) {
        if (ids == null || ids.size() == 0)
            return "()";
        String result = "(";
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size() - 1)
                result = result + ids.get(i);
            else
                result = result + ids.get(i) + ",";
        }
        result = result + ")";
        return result;
    }

    /**
     * 将向量转换为（1，2）的格式后返回，来方便数据库的操作
     *
     * @param ids
     * @return String
     */
    public static String getIdsplit(List<String> ids) {
        if (ids == null || ids.size() == 0)
            return "()";
        String result = "(";
        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size() - 1)
                result = result + ids.get(i);
            else
                result = result + ids.get(i) + ",";
        }
        result = result + ")";
        return result;
    }

    /**
     * 把一个含有“,”分隔符的字符串解析为List<String>
     *
     * @param vlas
     * @return
     */
    public static List<String> getStringSplit(String vlas) {
        List<String> s = new ArrayList<String>();
        if (vlas != null && !vlas.equals("") && vlas.indexOf(",") > 0) {
            String[] vas = vlas.split(",");
            for (String v : vas) {
                s.add(v);
            }
        } else {
            s.add(vlas);
        }
        return s;
    }

    /**
     * 把一个含有“,”分隔符的字符串解析为数组，
     * 并将数组内指定的index位置的是数据合计到sum，并返回合计结果
     *
     * @param vlas
     * @param sum
     * @param index
     * @return
     */
    public static String getAddUpNum(String vlas, String sum, String index) {
        int ind = StringUtil.StringToInt(index);
        String[] vas = vlas.split(",");
        return addUpStr(sum, vas[ind]);
    }

    public static String addUpStr(String c, String vlas) {
        if (c != null && vlas != null) {
            return "" + (StringUtil.StringToInt(c) + StringUtil.StringToInt(vlas));
        }
        return "0";
    }

    /**
     * 把格式为:文件名1%文件路径1;文件名2%文件路径2...
     * 的字符串解析为List<List<String>>
     *
     * @param filePathes
     * @return
     */
    public static List<List<String>> analyzeFiles(String filePathes) {
        if (filePathes != null && !filePathes.equals("")) {
            List<List<String>> global_filesList = new ArrayList<List<String>>();
            String files = filePathes;
            if (files.indexOf(";") > 0) {
                String[] fftemp = files.split(";");
                for (String temp : fftemp) {
                    List<String> tlist = new ArrayList<String>();
                    if (temp.indexOf("%") > 0) {
                        String[] ftemp = temp.split("%");
                        tlist.add(ftemp[0]);
                        tlist.add(ftemp[1]);
                    } else {
                        tlist.add(temp);
                    }
                    global_filesList.add(tlist);
                }

            } else {
                List<String> tlist = new ArrayList<String>();
                if (files.indexOf("%") > 0) {
                    String[] ftemp = files.split("%");
                    tlist.add(ftemp[0]);
                    tlist.add(ftemp[1]);
                } else {
                    tlist.add(files);
                }
                global_filesList.add(tlist);
            }
            return global_filesList;
        }
        return null;
    }

    /**
     * 将字符串在指定的长度内显示，超出后以..代替
     *
     * @param str  in string
     * @param iLen specify length out string
     */
    public static String substr(String str, int iLen) {
        if (str == null)
            return "";
        if (iLen > 2) {
            if (str.length() > iLen - 2) {
                str = str.substring(0, iLen - 2) + "..";
            }

        }
        return str;
    }

    static public String getMysqlSaveString(String strField, String def) {
        if (strField == null)
            return (def == null) ? "\'\'" : def;
        if (strField.indexOf('\'') != -1)
            return "'" + replaceCharacterWithString('\'', "\'\'", strField) + "'";
        return strField;
    }

    static public String replaceCharacterWithString(char character, String replacement, String source) {
        StringBuffer myStringBuffer = new StringBuffer(source);
        int length = myStringBuffer.length();
        int replacementLen = replacement.length();

        for (int indexOf = 0; indexOf < length; indexOf++) {
            if (myStringBuffer.charAt(indexOf) == character) {
                myStringBuffer.replace(indexOf, indexOf + 1, replacement);
                length = myStringBuffer.length();
                indexOf += replacementLen - 1;
            }
        }
        return myStringBuffer.toString();
    }

    /**
     * 返回一个不为空的字符串
     *
     * @param str
     */
    public static String isNull(String str) {
        return isNull(str, "&nbsp;");
    }

    /**
     * 返回一个不为空的字符串，当为空时返回def
     *
     * @param str
     * @param def
     */
    public static String isNull(String str, String def) {
        if (str == null)
            return def;
        else if (str.length() == 0)
            return def;
        else
            return str;
    }

    /**
     * 正则表达式判断
     */
    public static boolean isPattern(String str, String regEx) {
        try {
            Pattern pat = Pattern.compile(regEx);
            Matcher mat = pat.matcher(str);
            boolean rs = mat.find();
            return rs;
        } catch (Exception e) {
            return false;
        }
    }
}