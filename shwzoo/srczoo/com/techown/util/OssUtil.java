package com.techown.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;

public class OssUtil {
	private static Logger logger = LoggerFactory.getLogger(OssUtil.class);

	private static String endpoint;
	private static String accessKeyId;
	private static String accessKeySecret;
	private static String domainName;
	private static String defaultBucketName;
	static {
		try {
			Properties prop = new Properties();
			InputStream inStream = OssUtil.class.getClassLoader()
					.getResourceAsStream("aliyun-oss.properties");
			prop.load(inStream);
			endpoint = prop.getProperty("endpoint");
			accessKeyId = prop.getProperty("accessKeyId");
			accessKeySecret = prop.getProperty("accessKeySecret");
			domainName = prop.getProperty("domainName");
			defaultBucketName = prop.getProperty("defaultBucketName");
		} catch (Exception e) {
			logger.error("init aliyun-oss.properties fail", e);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param bucketName
	 * @param in
	 * @param key
	 * @return
	 */
	public static String upload(String bucketName, InputStream in, String key) {
		OSSClient ossClient = null;
		if("".equals(bucketName)||null==bucketName){
			bucketName = defaultBucketName;
		}
		try {
			String url = "http://" + endpoint;
			ossClient = new OSSClient(url, accessKeyId, accessKeySecret);
			ossClient.putObject(new PutObjectRequest(bucketName, key, in));
		} catch (OSSException oe) {
			logger.error(
					"Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason. \r\n Error Message: {} \r\n Error Code:{} \r\n Request ID: {} \r\n Host ID: {}",
					oe.getErrorCode(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
			key = null;
		} catch (ClientException ce) {
			logger.error(
					"Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network. \r\n Error Message: {}",
					ce.getMessage());
			key = null;
		} finally {
			/*
			 * Do not forget to shut down the client finally to release all allocated resources.
			 */
			if (null != ossClient) {
				ossClient.shutdown();
			}
		}
		// http://bucket.<endpoint>/object
		return "http://" + domainName + "/" + key;
	}

	/**
	 * 
	 * @param bucketName
	 * @param input
	 * @param key
	 * @param metadata
	 * @return
	 */
	public static String upload(String bucketName, InputStream input, String key,
			ObjectMetadata metadata) {
		OSSClient ossClient = null;
		try {
			String url = "http://" + endpoint;
			ossClient = new OSSClient(url, accessKeyId, accessKeySecret);
			ossClient.putObject(new PutObjectRequest(bucketName, key, input, metadata));
		} catch (OSSException oe) {
			logger.error(
					"Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason. \r\n Error Message: {} \r\n Error Code:{} \r\n Request ID: {} \r\n Host ID: {}",
					oe.getErrorCode(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
			key = null;
		} catch (ClientException ce) {
			logger.error(
					"Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network. \r\n Error Message: {}",
					ce.getMessage());
			key = null;
		} finally {
			/*
			 * Do not forget to shut down the client finally to release all allocated resources.
			 */
			if (null != ossClient) {
				ossClient.shutdown();
			}
		}
		// http://bucket.<endpoint>/object
		return "http://" + bucketName + "." + endpoint + "/" + key;
	}

	/**
	 * 
	 * @param bucketName
	 *            可以为空，为空时使用默认值
	 * @param key
	 *            key值
	 * @param type
	 *            可以为空，当等于“CDN”时表示下载地址使用CND加速的域名
	 * @return
	 */
	public static byte[] download(String bucketName, String key, String type) {
		OSSClient ossClient = null;
		byte[] bytes = null;
		try {
			if ("".equals(bucketName) || null == bucketName) {
				bucketName = defaultBucketName;
			}
			String url = null;
			if ("CDN".equals(type)) {
				url = "http://" + domainName;
			} else {
				url = "http://" + endpoint;
			}
			ossClient = new OSSClient(url, accessKeyId, accessKeySecret);
			OSSObject object = ossClient.getObject(bucketName, key);
			InputStream input = object.getObjectContent();
			try {
				bytes = IOUtils.toByteArray(input);
			} catch (IOException e) {
				logger.error("input to byteArray error", e);
			}
		} catch (OSSException oe) {
			logger.error(
					"Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason. \r\n Error Message: {} \r\n Error Code:{} \r\n Request ID: {} \r\n Host ID: {}",
					oe.getErrorCode(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
		} catch (ClientException ce) {
			logger.error(
					"Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network. \r\n Error Message: {}",
					ce.getMessage());
		} finally {
			if (null != ossClient) {
				ossClient.shutdown();
			}
		}
		return bytes;
	}

	/**
	 * 删除文件
	 * 
	 * @param bucketName
	 * @param key
	 * @return
	 */
	public Boolean deleteObject(String bucketName, String key) {
		OSSClient ossClient = null;
		Boolean flag = false;
		try {
			String url = "http://" + endpoint;
			ossClient = new OSSClient(url, accessKeyId, accessKeySecret);
			ossClient.deleteObject(bucketName, key);
			flag = true;
		} catch (OSSException oe) {
			logger.error(
					"Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason. \r\n Error Message: {} \r\n Error Code:{} \r\n Request ID: {} \r\n Host ID: {}",
					oe.getErrorCode(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());

		} catch (ClientException ce) {
			logger.error(
					"Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network. \r\n Error Message: {}",
					ce.getMessage());
		} finally {
			if (null != ossClient) {
				ossClient.shutdown();
			}
		}
		return flag;
	}

	/**
	 * 根据key批量删除文件
	 * 
	 * @param bucketName
	 * @param keys
	 * @return
	 */
	public static Boolean DeleteObjects(String bucketName, List<String> keys) {
		OSSClient ossClient = null;
		Boolean flag = false;
		String url = "http://" + endpoint;
		try {
			ossClient = new OSSClient(url, accessKeyId, accessKeySecret);
			ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
			flag = true;
		} catch (OSSException oe) {
			logger.error(
					"Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason. \r\n Error Message: {} \r\n Error Code:{} \r\n Request ID: {} \r\n Host ID: {}",
					oe.getErrorCode(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());

		} catch (ClientException ce) {
			logger.error(
					"Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network. \r\n Error Message: {}",
					ce.getMessage());
		} finally {
			if (null != ossClient) {
				ossClient.shutdown();
			}
		}
		return flag;
	}

	public static String uploadSTS(String bucketName, InputStream in, String key) {
		OSSClient ossClient = null;
		try {
			String accessKeyId = "STS.EXHzwxtk9A5jhx5sjCrcyQuSa";
			String securityToken = "CAISvAJ1q6Ft5B2yfSjIpJv9Mc3MmbQY9veBal6El2oWfuxVvrD4gzz2IHFIdXJsCOgdvvw/nWBU7/4blq9oS4BXQkuc3l/VHQIUo22beIPkl5GfC9Vm4cTX+QHxZjf/2MjNGbSbKPrWZvaqbX3diyZ32sGUXD6+XlujQ9Tr7Jl8dYYvQxWfZzhLD8s0Xgx5s502OH/cKfuKOBzniXaqICgOgAdnjn5l4qmS29CV7gGk7Gf30egIvY/8UNjGFsBnJ8V4SMznnr40JLjG13YJiWND/7wki7dfq3WV/IOPBF4JulLEEZKNrIY+cFYhP/BnQ/Aa9qatr5Ai5LyPzbaQ4g1WIORYXx7YQI2d28beEIurTI1oKeegZyWViYDXZsCl71p8OGhhMQRAet4sLWRrFRsvRzfALauq6KVk2qhFwjTvGoABZmqeusi90JQlvf/VqmaKug+T66eYLV+MmETID1tlQuqMao/X5hMVaDQWKscn/dJ/oQkzQgLdsZLTPZUfCbW0Dys/ZxQ09UoPLrTkkAhon4PdQuy99FcQ9om5gPkfauYm8YgIh19pNZkeERmNggiNIExfmX9A44e1FmZ2fUTmyIs=";
			String accessKeySecret = "H9V4yCNwegxY6dXgBqCBf2ZyZzEdhG9Vm242sQjmFVqX";
			String url = "http://" + endpoint;
			ossClient = new OSSClient(url, accessKeyId, accessKeySecret, securityToken);
			ossClient.putObject(new PutObjectRequest(bucketName, key, in));
		} catch (OSSException oe) {
			logger.error(
					"Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason. \r\n Error Message: {} \r\n Error Code:{} \r\n Request ID: {} \r\n Host ID: {}",
					oe.getErrorCode(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
			key = null;
		} catch (ClientException ce) {
			logger.error(
					"Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network. \r\n Error Message: {}",
					ce.getMessage());
			key = null;
		} finally {
			/*
			 * Do not forget to shut down the client finally to release all allocated resources.
			 */
			if (null != ossClient) {
				ossClient.shutdown();
			}
		}
		// http://bucket.<endpoint>/object
		return "http://" + bucketName + "." + endpoint + "/" + key;
	}

	public static Boolean deleteObjectSTS(String bucketName, String key) {
		OSSClient ossClient = null;
		Boolean flag = false;
		try {
			String accessKeyId = "STS.EXHzwxtk9A5jhx5sjCrcyQuSa";
			String securityToken = "CAISvAJ1q6Ft5B2yfSjIpJv9Mc3MmbQY9veBal6El2oWfuxVvrD4gzz2IHFIdXJsCOgdvvw/nWBU7/4blq9oS4BXQkuc3l/VHQIUo22beIPkl5GfC9Vm4cTX+QHxZjf/2MjNGbSbKPrWZvaqbX3diyZ32sGUXD6+XlujQ9Tr7Jl8dYYvQxWfZzhLD8s0Xgx5s502OH/cKfuKOBzniXaqICgOgAdnjn5l4qmS29CV7gGk7Gf30egIvY/8UNjGFsBnJ8V4SMznnr40JLjG13YJiWND/7wki7dfq3WV/IOPBF4JulLEEZKNrIY+cFYhP/BnQ/Aa9qatr5Ai5LyPzbaQ4g1WIORYXx7YQI2d28beEIurTI1oKeegZyWViYDXZsCl71p8OGhhMQRAet4sLWRrFRsvRzfALauq6KVk2qhFwjTvGoABZmqeusi90JQlvf/VqmaKug+T66eYLV+MmETID1tlQuqMao/X5hMVaDQWKscn/dJ/oQkzQgLdsZLTPZUfCbW0Dys/ZxQ09UoPLrTkkAhon4PdQuy99FcQ9om5gPkfauYm8YgIh19pNZkeERmNggiNIExfmX9A44e1FmZ2fUTmyIs=";
			String accessKeySecret = "H9V4yCNwegxY6dXgBqCBf2ZyZzEdhG9Vm242sQjmFVqX";
			String url = "http://" + endpoint;
			ossClient = new OSSClient(url, accessKeyId, accessKeySecret, securityToken);
			ossClient.deleteObject(bucketName, key);
			flag = true;
		} catch (OSSException oe) {
			logger.error(
					"Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason. \r\n Error Message: {} \r\n Error Code:{} \r\n Request ID: {} \r\n Host ID: {}",
					oe.getErrorCode(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());

		} catch (ClientException ce) {
			logger.error(
					"Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network. \r\n Error Message: {}",
					ce.getMessage());
		} finally {
			if (null != ossClient) {
				ossClient.shutdown();
			}
		}
		return flag;
	}

	/**
	 * 下载文件
	 * 
	 * @param bucketName
	 * @param key
	 * @return
	 */
	public static InputStream downloadSts(String bucketName, String key) {
		OSSClient ossClient = null;
		InputStream input = null;
		try {
			String accessKeyId = "STS.EXHzwxtk9A5jhx5sjCrcyQuSa";
			String securityToken = "CAISvAJ1q6Ft5B2yfSjIpJv9Mc3MmbQY9veBal6El2oWfuxVvrD4gzz2IHFIdXJsCOgdvvw/nWBU7/4blq9oS4BXQkuc3l/VHQIUo22beIPkl5GfC9Vm4cTX+QHxZjf/2MjNGbSbKPrWZvaqbX3diyZ32sGUXD6+XlujQ9Tr7Jl8dYYvQxWfZzhLD8s0Xgx5s502OH/cKfuKOBzniXaqICgOgAdnjn5l4qmS29CV7gGk7Gf30egIvY/8UNjGFsBnJ8V4SMznnr40JLjG13YJiWND/7wki7dfq3WV/IOPBF4JulLEEZKNrIY+cFYhP/BnQ/Aa9qatr5Ai5LyPzbaQ4g1WIORYXx7YQI2d28beEIurTI1oKeegZyWViYDXZsCl71p8OGhhMQRAet4sLWRrFRsvRzfALauq6KVk2qhFwjTvGoABZmqeusi90JQlvf/VqmaKug+T66eYLV+MmETID1tlQuqMao/X5hMVaDQWKscn/dJ/oQkzQgLdsZLTPZUfCbW0Dys/ZxQ09UoPLrTkkAhon4PdQuy99FcQ9om5gPkfauYm8YgIh19pNZkeERmNggiNIExfmX9A44e1FmZ2fUTmyIs=";
			String accessKeySecret = "H9V4yCNwegxY6dXgBqCBf2ZyZzEdhG9Vm242sQjmFVqX";
			String url = "http://" + endpoint;
			ossClient = new OSSClient(url, accessKeyId, accessKeySecret, securityToken);
			OSSObject object = ossClient.getObject(bucketName, key);
			System.out.println("Content-Type: " + object.getObjectMetadata().getContentType());
			input = object.getObjectContent();
		} catch (OSSException oe) {
			logger.error(
					"Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason. \r\n Error Message: {} \r\n Error Code:{} \r\n Request ID: {} \r\n Host ID: {}",
					oe.getErrorCode(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
		} catch (ClientException ce) {
			logger.error(
					"Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network. \r\n Error Message: {}",
					ce.getMessage());
		} finally {
			if (null != ossClient) {
				ossClient.shutdown();
			}
		}
		return input;
	}

	public static Boolean copyObject(String bucketName, String sourceKey, String destinationKey) {
		OSSClient ossClient = null;
		CopyObjectResult Result = null;
		try {
			String url = "http://" + endpoint;
			ossClient = new OSSClient(url, accessKeyId, accessKeySecret);
			Result = ossClient.copyObject(bucketName, sourceKey, bucketName, destinationKey);
		} catch (OSSException oe) {
			logger.error(
					"Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason. \r\n Error Message: {} \r\n Error Code:{} \r\n Request ID: {} \r\n Host ID: {}",
					oe.getErrorCode(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
		} catch (ClientException ce) {
			logger.error(
					"Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network. \r\n Error Message: {}",
					ce.getMessage());
		} finally {
			if (null != ossClient) {
				ossClient.shutdown();
			}
		}

		return null;
	}

	@Test
	public void test() throws IOException {
		// FileInputStream in = new FileInputStream(new File("E:/domain.png"));
		// OssUtil.upload("zoo01", in, "domain.png");
		// OssUtil.deleteObjectSTS("zoo01", "sts.png");
		// OssUtil.downloadSts("zoo01", "sts.png");
		// copyObject("zoo01", "temp/1.PNG", "zoo/1.PNG");
	}
}
