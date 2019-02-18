package com.yinlian.wssc.web.util;

import java.security.MessageDigest;


/**
 * MD5加密工具
 * MD5Util.java
 * @author Administrator
 * @version $Id: MD5Util.java, v 0.1 2016年3月14日 下午6:00:04 Administrator Exp $
 */
public class MD5Util {

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f"   };

    /**
     * cipher password
     * 
     * @param inputString
     * @return
     */
    public static String generatePassword(String inputString) {
        return encodeByMD5(inputString);
    }

    /**
     * validate password
     * 
     * @param password
     * @param inputString
     * @return
     */
    public static boolean validatePassword(String password, String inputString) {
        if (password.equals(encodeByMD5(inputString))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * encode
     * 
     * @param originString
     * @return
     */
    public static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(originString.getBytes("utf-8"));
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * change the Byte[] to hex string
     * 
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * change a byte to hex string
     * 
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    /**
     * encode md5 16位
     * 
     * @param originString
     * @return
     */
    public static String encodeByMD516(String originString) {
        if (originString != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(originString.getBytes());
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase().substring(8,24);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String pwd1 = "111111";
        String pwd2 = "";
        MD5Util cipher = new MD5Util();
        System.out.println("加密前:" + pwd1);

        pwd2 = cipher.generatePassword(pwd1);
        System.out.println("加密后:" + pwd2);

        System.out.print("验证:");
        if (cipher.validatePassword(pwd2, "123456")) {
            System.out.println("正确");
        } else {
            System.out.println("错误");
        }
        String uuidString=java.util.UUID.randomUUID().toString();
        System.out.println(uuidString);
        System.out.println(encodeByMD5(uuidString));
        System.out.println(encodeByMD516(uuidString).replaceAll("\\w{4}(?!$)", "$0 "));
        
    }
}