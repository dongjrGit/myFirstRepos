/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author Administrator
 * @version $Id: RedisUtil.java, v 0.1 2016年2月25日 上午10:57:05 Administrator Exp $
 */
public class RedisTest {

    /**
     * 日志输出
     */
    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);
    /**
     * jedis客户端
     */
    private static Jedis        jedis;

    /**
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        //        //init();
        //        jedis = new RedisUtil("192.168.1.53", 5379, "admin").getJedis();
        //        setString("name", "admin");
        //        System.out.println(jedis.get("name"));
        ShardedJedis_User.set("name1", "zhangs");
        System.out.println(ShardedJedis_User.get("name1"));
    }

    /**
     * 初始化 Jedis
     * 
     * @throws Exception
     */
    public static void init() throws Exception {

    }

    /**
     * 添加字符串到redis中
     * 
     * @throws Exception
     */
    public static void setString(String key, String value) throws Exception {
        if (key != null && key != "") {
            jedis.set(key, value);
        }

    }

    /**
     * 获取redis中字符串的数据
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public String getString(String key) throws Exception {
        if (key != null && key != "") {
            return jedis.get(key);
        }
        return null;
    }

    /**
     * 删除某个key
     * 
     * @param key
     * @throws Exception
     */
    public void deleteKey(String key) throws Exception {
        if (key == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("传入的key为null!");
                throw new IllegalArgumentException("key 为空");
            }
        }
        jedis.del(key);
    }

    public String appendString(String key, String append) throws Exception {
        if (key != null && key != "") {
            jedis.append(key, append);
            return jedis.get(key);
        }
        return null;
    }

    /**
     * 设置集合的
     * 
     * @throws Exception
     */
    public static void setList() throws Exception {
        //开始前，先移除所有的内容  
        jedis.del("java framework");
        System.out.println(jedis.lrange("java framework", 0, -1));
        //先向key java framework中存放三条数据  
        jedis.lpush("java framework", "spring");
        jedis.lpush("java framework", "struts");
        jedis.lpush("java framework", "hibernate");
        //再取出所有数据jedis.lrange是按范围取出，  
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
        System.out.println(jedis.lrange("java framework", 0, -1));

        jedis.del("java framework");
        jedis.rpush("java framework", "spring");
        jedis.rpush("java framework", "struts");
        jedis.rpush("java framework", "hibernate");
        System.out.println(jedis.lrange("java framework", 0, -1));
    }

    /**
     * 设置set 集合
     * 
     * @throws Exception
     */
    public static void testSet() throws Exception {

        jedis.sadd("user", "admin");
        jedis.sadd("age", "12");
        jedis.sadd("user", "xinxin");
        jedis.sadd("user", "ling");

        jedis.srem("user", "ling");
        System.out.println(jedis.smembers("user"));//获取所有加入的value  
        System.out.println(jedis.sismember("user", "admin"));//判断 who 是否是user集合的元素  
        System.out.println(jedis.scard("user"));//返回集合的元素个数 
    }

}
