package com.yinlian.wssc.web.util;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

public class ConfigUtil {
	private ConfigUtil() {

	}

	private static ConfigUtil _instances;

	public static ConfigUtil get_instances() {
		if (_instances == null) {
			_instances = new ConfigUtil();
		}
		return _instances;
	}

	public String fileUrl() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
			pro.load(inStream);
			path = pro.getProperty("image_src");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	public String voucherpath() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
			pro.load(inStream);
			path = pro.getProperty("voucherpath");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	public Boolean isDEBUG() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
			pro.load(inStream);
			path = pro.getProperty("is_debug");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Boolean.valueOf(path);
	}

	/**
	 * 
	 * @return
	 */
	public String getuploadext() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
			pro.load(inStream);
			path = pro.getProperty("upload_ext");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	/**
	 * 获取用户 redis Ip信息
	 * 
	 * @return
	 */
	public String getRedisUserInfoIp() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			pro.load(inStream);
			path = pro.getProperty("redis_userinfo_ip");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	/**
	 * 获取用户 redis 端口信息
	 * 
	 * @return
	 */
	public int getRedisUserInfoPort() {
		String port = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			pro.load(inStream);
			port = pro.getProperty("redis_userinfo_port");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.parseInt(port);
	}

	/**
	 * 获取用户 redis Auth 验证信息
	 * 
	 * @return
	 */
	public String getRedisUserInfoAuth() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			pro.load(inStream);
			path = pro.getProperty("redis_userinfo_auth");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	/**
	 * 获取用户Token redis超时时间
	 * 
	 * @return
	 */
	public int getTokenTimeOut() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			pro.load(inStream);
			path = pro.getProperty("token_time_out");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.parseInt(path);
	}

	/**
	 * 获取短信验证码redis超时时间
	 * 
	 * @return
	 */
	public int getSmsCodeTmeOut() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			pro.load(inStream);
			path = pro.getProperty("token_time_out");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(path);
	}

	/**
	 * 获取用户 redis Ip信息
	 * 
	 * @return
	 */
	public String getRedisRequestDataIp() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			pro.load(inStream);
			path = pro.getProperty("redis_requestdata_ip");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	/**
	 * 获取用户 redis 端口信息
	 * 
	 * @return
	 */
	public int getRedisRequestDataPort() {
		String port = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			pro.load(inStream);
			port = pro.getProperty("redis_requestdata_port");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.parseInt(port);
	}

	/**
	 * 获取用户 redis Auth 验证信息
	 * 
	 * @return
	 */
	public String getRedisRequestDataAuth() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("redis.properties");
			pro.load(inStream);
			path = pro.getProperty("redis_requestdata_auth");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	public String getWwebservice_basepath() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
			pro.load(inStream);
			path = pro.getProperty("webservice_basepath");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	public String getS_BackUrl() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
			pro.load(inStream);
			path = pro.getProperty("receiveurl");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	public String getSmsUrl() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
			pro.load(inStream);
			path = pro.getProperty("smsurl");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}
	public String getSmsName() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
			pro.load(inStream);
			path = pro.getProperty("smsusername");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}
	public String getSmsPwd() {
		String path = "";
		try {
			Properties pro = new Properties();
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("cfg.properties");
			pro.load(inStream);
			path = pro.getProperty("smspassword");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}
	public static void main(String[] arg) {
		try {
			Image targetImage = ImageIO.read(new FileInputStream("E:\\1.jpg"));//"E:\\公司\\YL_JAVA\\trunk\\参考文档\\经开商城2-25\\经开商城2-22\\抵用券.jpg")
		int []is=	ScaleImageUtils.getSize(320 ,909,targetImage);
			ScaleImageUtils.resize(is[0], is[1], 1F, "E:\\2.jpg", targetImage);
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}
	}
}
