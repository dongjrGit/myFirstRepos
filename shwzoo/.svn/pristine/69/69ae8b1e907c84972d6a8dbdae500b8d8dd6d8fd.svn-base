/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;

/**
 * 基类的工具
 * @author Administrator
 * @version $Id: CommonUtils.java, v 0.1 2016年3月11日 下午2:44:56 Administrator Exp $
 */
public class CommonUtils {

    /**
     * 循环遍历删除list中重复的元素
     * 
     * @param list
     */
    public static List<String> removeDuplicateWithOrder(List<String> list) {
        if (list != null) {
            Set<String> set = new HashSet<String>();
            List<String> newList = new ArrayList<String>();
            for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
                String element = iter.next();
                if (set.add(element))
                    newList.add(element);
            }
            list.clear();
            list.addAll(newList);
            System.out.println(" remove duplicate " + list);
        }
        return list;
    }

    /** 
     * 将输入流 转换成字符串
     * @param inStream
     * @return
     */
    public static String readFileInputStream(FileInputStream inStream) {
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[256];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, length);
            }
            outStream.close();
            inStream.close();
            return outStream.toString();
        } catch (IOException e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    /** 
     * 将输入流 转换成字符串
     * @param inStream
     * @return
     */
    public static String readInStream(InputStream inStream) {
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[256];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, length);
            }
            outStream.close();
            inStream.close();
            return outStream.toString();
        } catch (IOException e) {
            System.out.println("" + e.getMessage());
        }
        return null;
    }

    /**
     * 计算百分比
     * 
     * @param list
     * @param denominator
     * @return
     */
    public static String caligrate(List<Integer> list) {
        DecimalFormat df = new DecimalFormat("######0.00");
        float result = 0.00f;
        Integer total = 0;
        Integer fenmu = 0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                total += list.get(i);
                fenmu += ConstanValue.COMMON_STAT;
            }

        }
        if (fenmu != 0) {
            result = (float) total / (float) fenmu;
        }

        return df.format(result * 10);
    }

    public static List<String> spilt(String s, List<String> list) {

        if (StringUtils.isNotNull(s) && s.indexOf("|") >= 0) {
            int index = s.indexOf("|");
            System.out.println(s.substring(0, index));
            list.add(s.substring(0, index));
            spilt(s.substring(index + 1), list);
        } else if (StringUtils.isNotNull(s)) {
            list.add(s);
            System.out.println(s);
        }
        return list;
    }

    /**
     * 发送邮件
     * 
     * @param eamil
     * @param title
     * @param content
     * @throws MessagingException
     * @throws IOException
     */
    public static boolean sendEmail(String eamil, String title, String content)
                                                                            throws MessagingException,
                                                                            IOException {

        Properties properties = PropertiesUtil.getProperties("cfg.properties");
        String smtp = properties.getProperty("email_smtp");// smtp服务器
        String from = properties.getProperty("email_from");// 邮件显示名称
        String username = properties.getProperty("email_username");// 发件人真实的账户名
        String password = properties.getProperty("email_password");// 发件人密码

        String to = eamil;// 收件人的邮件地址，必须是真实地址
        String copyto = "";// 抄送人邮件地址
        String subject = title;// 邮件标题

      return Mail.sendAndCc(smtp, from, to, copyto, subject, content, username, password);

    }

    public static void main(String[] args) throws MessagingException, IOException {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(4);

        String d = caligrate(list);
        System.out.println("结果：" + d);
    }
}
