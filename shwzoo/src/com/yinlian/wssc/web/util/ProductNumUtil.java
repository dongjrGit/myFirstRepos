package com.yinlian.wssc.web.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ProductNumUtil {

    public static synchronized String GetOrderNum() {
        String proNum = "";
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YY");
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        proNum = df.format(now) + cal.get(Calendar.DAY_OF_YEAR) + cal.get(Calendar.MILLISECOND);
        long rand = GetRand(15 - proNum.length());
        proNum += (rand == -1 ? "" : String.valueOf(rand));
        return proNum;
    }

    private static int GetRand(int n) {
        int max = (int) Math.pow(10, n);
        int min = n == 1 ? 0 : (int) Math.pow(10, n - 1);
        return new Random().nextInt(max) % (max - min + 1) + min;

    }

    /**
     * 获取商品编号 yy+日期(+毫秒)+6位随机数
     * @return
     */
    public static String getSkuNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYMMddSSS");
        Random rd = new Random();
        System.currentTimeMillis();
        Integer sjs = rd.nextInt(900000) + 100000;
        String randomNum = "yy" + df.format(date) + sjs.toString();

        return randomNum;

    }

    /**
     * 生成shopNum
     * 
     * @return
     */
    public static String getShopNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYMMddSSS");
        Random rd = new Random();
        Integer sjs = rd.nextInt(900000) + 100000;
        String randomNum = df.format(date) + sjs.toString();
        return randomNum;
    }

    /**
     * 生成CouponNum
     * 
     * @return
     */
    public static String getCouponNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYMMddSSS");
        Random rd = new Random();
        Integer sjs = rd.nextInt(900000) + 100000;
        String randomNum = "cc" + df.format(date) + sjs.toString();
        return randomNum;
    }

    /**
     * 随机生成4位验证码
     * 
     * @return
     */
    public static String randomValidata() {
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B",
                "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        System.out.print(result);
        return result;
    }

    public static void main(String[] args) {
        //randomValidata();
        System.out.println(getRandNum());
    }

    public static String getRandNum() {
        return getRandomString(6);
    }

    public static String getRandomString(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    
    public static String getBatchNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYYYMMdd");
        Random rd = new Random();
        System.currentTimeMillis();
        Integer sjs = rd.nextInt(900000) + 100000;
        String randomNum = df.format(date) + sjs.toString();

        return randomNum;

    }
}
