/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Administrator
 * @version $Id: LogUtil.java, v 0.1 2016年3月5日 上午9:56:31 Administrator Exp $
 */
public class LoggerUtil {

    public static final String ADMIN  = "admin";

    public static final String SELLER = "seller";

    private static Properties  properties;

    private static String      path;

    /**
     * debug
     * 
     * @param clazz
     * @param message
     * @throws Exception 
     */
    @SuppressWarnings("resource")
    public static void debug(String position, String message) {
        try {
            properties = PropertiesUtil.getProperties("log4j.properties");
            path = (String) properties.get("file_path");
            contentToTxt(path + position, message);
        } catch (IOException e) {
            System.out.println("Properties异常：" + e.getMessage());
        }

    }

    /**
     * info
     * 
     * @param clazz
     * @param message
     */
    public static void info(String position, Class clazz, String message) {
        File file = new File(path + position);
    }

    /**
     * error
     * 
     * @param clazz
     * @param message
     */
    public static void error(Class clazz, String message) {

    }

    /**
     * warn
     * 
     * @param clazz
     * @param message
     */
    public static <T> void warn(Class<T> clazz, String message) {

    }

    public static void contentToTxt(String filePath, String content) {
        String str = new String(); //原有txt内容  
        String s1 = new String();//内容更新  
        try {
            File f = new File(filePath, "log.txt");
            if (f.exists()) {
                if (f.length() > 1024) { // 判断文件大小
                    f.delete();
                    f.getParentFile().mkdirs(); //先创建目录
                    f.createNewFile();// 不存在则创建  
                }
            } else {
                f.getParentFile().mkdirs(); //先创建目录
                f.createNewFile();// 不存在则创建  
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((str = input.readLine()) != null) {
                s1 += str + "\n";
            }
            System.out.println(s1);
            input.close();
            s1 += content;

            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.write(s1);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        Exception exception = new IllegalArgumentException("参数错误，文件大于1kb时删除以前的数据");
        LoggerUtil.debug(ADMIN, exception.getMessage().toString() + ",--->在admin下");
        LoggerUtil.debug(SELLER, exception.getMessage().toString() + ",--->在seller下");
    }
}
