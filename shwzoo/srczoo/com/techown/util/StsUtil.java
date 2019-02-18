package com.techown.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;

/**
 * 使用STS服务临时授权
 * 
 * @author licd
 *
 */
public class StsUtil {
	private static Logger logger = LoggerFactory.getLogger(OssUtil.class);
	// 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
	private static String region_cn_hangzhou;
	// 当前 STS API 版本
	private static String sts_api_version;

	private static String accessKeyId;
	private static String accessKeySecret;
	private static String roleArn;
	private static String roleSessionName;
	private static String policy;
	private static String endpoint;
	private static String bucketName;
	private static String domainName;
	static {
		try {
			Properties prop = new Properties();
			InputStream inStream = OssUtil.class.getClassLoader()
					.getResourceAsStream("aliyun-sts.properties");
			prop.load(inStream);
			region_cn_hangzhou = prop.getProperty("REGION_CN_HANGZHOU");
			sts_api_version = prop.getProperty("STS_API_VERSION");
			accessKeyId = prop.getProperty("accessKeyId");
			accessKeySecret = prop.getProperty("accessKeySecret");
			roleArn = prop.getProperty("roleArn");
			roleSessionName = prop.getProperty("roleSessionName");
			policy = prop.getProperty("policy");
			endpoint = prop.getProperty("endpoint");
			bucketName = prop.getProperty("bucketName");
			domainName = prop.getProperty("domainName");

		} catch (Exception e) {
			logger.error("init aliyun-sts.properties fail", e);
		}
	}

	/**
	 * 获取STS token
	 * 
	 * @return
	 */
	public static Map<String, String> assumeRole() {
		Map<String, String> map = null;
		try {
			// 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
			IClientProfile profile = DefaultProfile.getProfile(region_cn_hangzhou, accessKeyId,
					accessKeySecret);
			DefaultAcsClient client = new DefaultAcsClient(profile);

			// 创建一个 AssumeRoleRequest 并设置请求参数
			final AssumeRoleRequest request = new AssumeRoleRequest();
			request.setVersion(sts_api_version);
			request.setMethod(MethodType.POST);
			request.setProtocol(ProtocolType.HTTPS);

			request.setRoleArn(roleArn);
			request.setRoleSessionName(roleSessionName);
			request.setPolicy(policy);

			// 发起请求，并得到response
			final AssumeRoleResponse response = client.getAcsResponse(request);
			map = new HashMap<String, String>();
			map.put("expiration", response.getCredentials().getExpiration());
			map.put("accessKeyId", response.getCredentials().getAccessKeyId());
			map.put("accessKeySecret", response.getCredentials().getAccessKeySecret());
			map.put("securityToken", response.getCredentials().getSecurityToken());
			map.put("endpoint",endpoint);
			map.put("bucketName",bucketName);
			map.put("domainName",domainName);
		} catch (ClientException e) {
			logger.error("Failed to get a token. \r\n Error code: {} \r\n Error message{}",
					e.getErrCode(), e.getErrMsg());
		}
		return map;
	}

	@Test
	public void test() {
		Map<String, String> map = StsUtil.assumeRole();
		for (Entry<String, String> entry : map.entrySet()) {
			System.out.println("String " + entry.getKey() + "=\"" + entry.getValue() + "\";");
		}
	}
}
