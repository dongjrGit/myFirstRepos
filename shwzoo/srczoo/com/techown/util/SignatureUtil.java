package com.techown.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加载需要OSS签名用到的RAM信息
 * 
 * @author lenovo
 *
 */
public class SignatureUtil {
	private static Logger logger = LoggerFactory.getLogger(SignatureUtil.class);
	public static String endpoint;
	public static String accessId;
	public static String accessKey;
	public static String bucket;
	public static String domainName;
	public static String uploadUrl;
	
	static {
		try {
			Properties prop = new Properties();
			InputStream inStream = OssUtil.class.getClassLoader()
					.getResourceAsStream("aliyun-oss-sign.properties");
			prop.load(inStream);
			endpoint = prop.getProperty("sign.endpoint");
			accessId = prop.getProperty("sign.accessId");
			accessKey = prop.getProperty("sign.accessKey");
			bucket = prop.getProperty("sign.bucket");
			domainName= prop.getProperty("sign.domainName");
			uploadUrl = prop.getProperty("sign.uploadUrl");
		} catch (Exception e) {
			logger.error("init aliyun-oss.properties fail", e);
		}
	}
}
