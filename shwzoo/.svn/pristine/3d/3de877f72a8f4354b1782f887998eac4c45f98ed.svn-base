package com.yinlian.wssc.web.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.yinlian.wssc.web.util.ConfigUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

public class Redis_RequestData {
	private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);
	// Redis服务器IP
	private static String ADDR = ConfigUtil.get_instances().getRedisRequestDataIp();

	// Redis的端口号
	private static int PORT = ConfigUtil.get_instances().getRedisRequestDataPort();
	// Redis的端口号
	private static String AUTH = ConfigUtil.get_instances().getRedisRequestDataAuth();


	private static Jedis jedis;
	static {
		try {
			jedis = new RedisUtil(ADDR, PORT, AUTH).getJedis();
			if (!StringUtilsEX.IsNullOrWhiteSpace(AUTH))
				jedis.auth(AUTH);
		} catch (Exception e) {
			logger.error("初始化toekn_redis 出现异常：" + e.getMessage(), e);
		}
	}

	

	/**
	 * 设置通用键值
	 * 
	 * @param key
	 * @param val
	 */
	public static void Set(String key, String val) {
		jedis.set(key, val);
	}

	/**
	 * 设置键值
	 * 
	 * @param key
	 * @param val
	 * @param timeout
	 *            超时时间 单位：秒
	 */
	public static void Set(String key, String val, int timeout) {
		if (timeout <= 0) {
			Set(key, val);
			return;
		}
		jedis.setex(key, timeout, val);
	}

	/**
	 * 获取键值
	 * 
	 * @param key
	 * @return
	 */
	public static String Get(String key) {
		return jedis.get(key);
	}

	/**
	 * 删除
	 * 
	 * @param key
	 * @return
	 */
	public static String Del(String key) {
		return jedis.get(key);
	}

}
