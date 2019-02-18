package com.pushwin.batchwork.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Date：2013-11-5
 * Author：王本源
 * Description：加密工具类。
 * Others： 
 * History：
*/
public class MD5Util {

	/**
	 * md5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] byteDigest = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < byteDigest.length; offset++) {
				i = byteDigest[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
			// 16位的加密
//			return buf.toString().substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getMD5String(byte[] data) {
		try {
			
			StringBuffer buf = new StringBuffer();
			for (byte ch : encodeAsMD5(data)) {
				String s = Integer.toHexString(ch & 0xff);
				buf.append(s.length() == 1 ? s = "0" + s : s);
			}
			
			return buf.toString();
		} catch (Exception e) {
		}
		return "";
	}

	public static String getMD5String(String text) {
	
		return getMD5String(text.getBytes());
	}

	public static byte[] getMd5(String text) throws Exception {
		byte[]bt= encodeAsMD5(text.getBytes("UTF-8"));
//		bt=Base64.encode(bt, Base64.DEFAULT);
		//String st=Base64.encodeToString(bt, 0);
		return bt;
	}

	public static byte[] encodeAsMD5(byte[] data)
			throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(data);
		return md5.digest();
	}

	public static void main(String[] args) {
		System.out.println(md5("admin"));;
	}
}
