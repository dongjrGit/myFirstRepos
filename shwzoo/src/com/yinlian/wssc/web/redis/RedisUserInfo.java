package com.yinlian.wssc.web.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.yinlian.wssc.web.util.ConfigUtil;
import com.yinlian.wssc.web.util.StringUtils;

public class RedisUserInfo {
	private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);
	// Redis服务器IP
	private static String ADDR = ConfigUtil.get_instances().getRedisUserInfoIp();

	// Redis的端口号
	private static int PORT = ConfigUtil.get_instances().getRedisUserInfoPort();
	// Redis的端口号
	private static String AUTH = ConfigUtil.get_instances().getRedisUserInfoAuth();
	// Token 超时时间
	private static int Timeout = ConfigUtil.get_instances().getTokenTimeOut();

	private static JedisPool jedisPool = RedisUtil.getJedisPool(ADDR, PORT, AUTH);

	// static {
	// try {
	// jedis = new RedisUtil(ADDR, PORT, AUTH).getJedis();
	// if (!StringUtilsEX.IsNullOrWhiteSpace(AUTH))
	// jedis.auth(AUTH);
	// } catch (Exception e) {
	// logger.error("初始化toekn_redis 出现异常：" + e.getMessage(), e);
	// }
	// }

	public static Jedis getJedis() {
		synchronized (jedisPool) {
			Jedis jedis = null;
			if (jedis == null && jedisPool != null) {
				jedis = jedisPool.getResource();
				if (StringUtils.isNotNull(AUTH)) {
					jedis.auth(AUTH);
				}
				jedis.connect();
			}
			return jedis;
		}
	}

	/**
	 * 设置或者更新Token
	 * 
	 * @param key
	 * @param val
	 */
	public static void SetToken(String key, String val) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.setex(key, Timeout, val);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (jedis != null && jedisPool != null) {
				jedis.disconnect();
				jedisPool.returnResource(jedis);
			}
		}
	}

	/**
	 * 设置通用键值
	 * 
	 * @param key
	 * @param val
	 */
	public static void Set(String key, String val) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.set(key, val);
			// System.out.println("set jedis:" + jedis);
		} catch (Exception e) {
			logger.error("设置通用键值异常：", e);
		} finally {
			if (jedis != null && jedisPool != null) {
				jedis.disconnect();
				jedisPool.returnResource(jedis);
			}
		}
	}

	/**
	 * 设置通用键值
	 * 
	 * @param key
	 * @param val
	 * @param timeout
	 *            超时时间 单位：秒
	 */
	public static void Set(String key, String val, int timeout) {
		Jedis jedis = null;
		try {
			if (timeout <= 0) {
				Set(key, val);
				return;
			}
			jedis = getJedis();
			jedis.setex(key, timeout, val);
		} catch (Exception e) {
			logger.error("设置通用键值异常：", e);
		} finally {
			if (jedis != null && jedisPool != null) {
				jedis.disconnect();
				jedisPool.returnResource(jedis);
			}
		}
	}

	/**
	 * 获取通用键值
	 * 
	 * @param key
	 * @return
	 */
	public static String Get(String key) {
		String value = "";
		Jedis jedis = null;
		try {
			jedis = getJedis();
			value = jedis.get(key);
			// System.out.println("get jedis:" + jedis);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (jedis != null && jedisPool != null) {
				jedis.disconnect();
				jedisPool.returnResource(jedis);
			}
		}
		return value;
	}

	/**
	 * 删除
	 * 
	 * @param key
	 * @return
	 */
	public static void Del(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key);
		} catch (Exception e) {
			logger.error("删除key异常：", e);
		} finally {
			if (jedis != null && jedisPool != null) {
				jedis.disconnect();
				jedisPool.returnResource(jedis);
			}
		}
	}

	/**
	 * 延迟Token时间
	 */
	public static void DelayTokenTimeOut(String key) {
		Jedis jedis = null;
		try {
			String val = GetToken(key);
			jedis = getJedis();
			jedis.setex(key, Timeout, val);
		} catch (Exception e) {
			logger.error("延长token时间异常：", e);
		} finally {
			if (jedis != null && jedisPool != null) {
				jedis.disconnect();
				jedisPool.returnResource(jedis);
			}
		}
	}

	/**
	 * 删除Token
	 * 
	 * @param key
	 */
	public static void DetToken(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key);
		} catch (Exception e) {
			logger.error("删除key异常：", e);
		} finally {
			if (jedis != null && jedisPool != null) {
				jedis.disconnect();
				jedisPool.returnResource(jedis);
			}
		}
	}

	/**
	 * 获取Token
	 * 
	 * @param key
	 * @return
	 */
	public static String GetToken(String key) {
		String value = "";
		Jedis jedis = null;
		try {
			jedis = getJedis();
			value = jedis.get(key);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			if (jedis != null && jedisPool != null) {
				jedis.disconnect();
				jedisPool.returnResource(jedis);
			}
		}
		return value;
	}

	public static void main(String[] args) {
		Set("name", "zhangsan");
		System.out.println("第一次：" + Get("name"));
		Set("name", "lisi");
		System.out.println("第二次：" + Get("name"));

	}
}
