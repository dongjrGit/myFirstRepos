/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * ShardedJedis_User.java
 * @author Administrator
 * @version $Id: ShardedJedis_User.java, v 0.1 2016年3月28日 下午12:12:34 Administrator Exp $
 */
public class ShardedJedis_User {

    private static ShardedJedisPool pool   = RedisShardPool.getShardedJedisPool();

    private static final Logger     logger = LoggerFactory.getLogger(ShardedJedis_User.class);

    private static ShardedJedis     shardedJedis;

    /**
     * 设置或者更新Token
     * 
     * @param key
     * @param val
     */
    public static void setToken(String key, String value) {
        try {
            shardedJedis = pool.getResource();
            shardedJedis.setex(key, 300, value);
        } catch (Exception e) {
            logger.error("设置token异常：", e.getMessage());
        } finally {
            if (pool != null) {
                pool.returnResource(shardedJedis);
            }
        }
    }

    /**
     * 设置通用键值
     * 
     * @param key
     * @param val
     */
    public static void set(String key, String value) {
        try {
            shardedJedis = pool.getResource();
            shardedJedis.set(key, value);
        } catch (Exception e) {
            logger.error("设置通用键值异常：", e.getMessage());
        } finally {
            if (pool != null) {
                pool.returnResource(shardedJedis);
            }
        }
    }

    /**
    * 获取通用键值
    * 
    * @param key
    * @return
    */
    public static String get(String key) {
        String value = "";
        try {
            shardedJedis = pool.getResource();
            value = shardedJedis.get(key);
        } catch (Exception e) {
            logger.error("获取通用键值异常：", e.getMessage());
        } finally {
            if (pool != null) {
                pool.returnResource(shardedJedis);
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
    public static void del(String key) {
        try {
            shardedJedis = pool.getResource();
            shardedJedis.decr(key);
        } catch (Exception e) {
            logger.error("删除键值异常：", e.getMessage());
        } finally {
            if (pool != null) {
                pool.returnResource(shardedJedis);
            }
        }
    }

    /**
     * 延迟Token时间
     */
    public static void delayTokenTimeOut(String key, int seconds) {

        try {
            shardedJedis = pool.getResource();
            String value = shardedJedis.get(key);
            shardedJedis.setex(key, seconds, value);
        } catch (Exception e) {
            logger.error("删除Token异常：", e.getMessage());
        } finally {
            if (pool != null) {
                pool.returnResource(shardedJedis);
            }
        }
    }

    /**
     * 删除Token
     * 
     * @param key
     */
    public static void detToken(String key) {
        try {
            shardedJedis = pool.getResource();
            shardedJedis.del(key);
        } catch (Exception e) {
            logger.error("删除Token异常：", e.getMessage());
        } finally {
            if (pool != null) {
                pool.returnResource(shardedJedis);
            }
        }
    }

    /**
     * 获取Token
     * 
     * @param key
     * @return
     */
    public static String getToken(String key) {
        String token = "";
        try {
            shardedJedis = pool.getResource();
            token = shardedJedis.get(key);
        } catch (Exception e) {
            logger.error("获取Token异常：", e.getMessage());
        } finally {
            if (pool != null) {
                pool.returnResource(shardedJedis);
            }
        }
        return token;
    }

    public static void main(String[] args) {
        set("title", "cesi");
        System.out.println(get("title"));
    }
}
