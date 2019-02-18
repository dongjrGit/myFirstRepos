/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.redis;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import com.yinlian.wssc.web.util.ConstanValue;

/**
 * RedisShardPool.java
 * @author Administrator
 * @version $Id: RedisShardPool.java, v 0.1 2016年3月28日 上午11:42:47 Administrator Exp $
 */
public class RedisShardPool {

    private static ShardedJedisPool pool;

    private static ShardedJedis     redis;

    private static final Logger     logger = LoggerFactory.getLogger(RedisShardPool.class);

    public static ShardedJedisPool getShardedJedisPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxActive(ConstanValue.MaxActive);
            config.setMaxIdle(ConstanValue.MaxIdle);
            config.setMaxWait(ConstanValue.MaxWait);
            config.setTestOnBorrow(true);
            //Properties properties = PropertiesUtil.getProperties("redis.properties");
            // String host = properties.getProperty("redis_userinfo_ip");
            //String port = properties.getProperty("redis_userinfo_port");
            //String hostB = "10.10.224.48";
            // int portB = 6379;

            List<JedisShardInfo> jdsInfoList = new ArrayList<JedisShardInfo>();
            JedisShardInfo info = new JedisShardInfo(ConstanValue.REDIS_SERVER_HOST,
                ConstanValue.PORT);
            //JedisShardInfo infoB = new JedisShardInfo(hostB, portB);
            //infoB.setPassword("redis.360buy");
            jdsInfoList.add(info);
            //jdsInfoList.add(infoB);

            pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH,
                Sharded.DEFAULT_KEY_TAG_PATTERN);
        } catch (Exception e) {
            logger.error("异常：", e.getMessage());
        }
        return pool;
    }

    public static void main(String[] args) {

        set("name", "zhangsan");
        System.out.println(get("name"));
    }

    /**
     * 获取redis
     * 
     * @return
     */
    public static ShardedJedis getRedis() {
        pool = getShardedJedisPool();
        if (redis == null) {
            synchronized (pool) {
                if (redis == null && pool != null) {
                    redis = pool.getResource();
                }
            }
        }
        return redis;
    }

    public static void set(String key, String value) {
        try {
            redis = getRedis();
            redis.set(key, value);
            System.out.println("set redis:" + redis);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (pool != null) {
                pool.returnResource(redis);
            }
        }
    }

    public static String get(String key) {
        String value = "";
        try {
            redis = getRedis();
            value = redis.get(key);
            System.out.println("get redis:" + redis);

        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (pool != null) {
                pool.returnResource(redis);
            }
        }
        return value;
    }
}
