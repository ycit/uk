package com.ycit.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis util
 *
 * @author uk
 * 2019/4/7 11:55
 */
@Slf4j
public class JedisUtil {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;


    private JedisPool jedisPool;

    private static class JedisHolder {
        private static final JedisUtil instance = new JedisUtil();
    }

    private JedisUtil(){

    }

    public static JedisUtil getInstance() {
        return JedisUtil.JedisHolder.instance;
    }

    public JedisPool getPool() {
        if (jedisPool == null) {
            jedisPool = new JedisPool(poolConfig(), host, port, Constant.REDIS_TIMEOUT);
        }
        return jedisPool;
    }

    private JedisPoolConfig poolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(Constant.REDIS_MAX_IDLE);
        poolConfig.setMaxTotal(Constant.REDIS_MAX_ACTIVE);
        poolConfig.setMaxWaitMillis(Constant.REDIS_MAX_WAIT);
        return poolConfig;
    }

    public Jedis getJedis() {
        JedisPool jedisPool = getPool();
        int retries = 0;
        Jedis jedis = null;
        do {
            try {
                jedis = jedisPool.getResource();
            } catch (Exception e) {
                log.error("get redis connection fail {}", e);
            }
        }while (jedis != null && retries < Constant.REDIS_RETRY_NUM);
        return jedis;
    }

    public void closeJedis(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }

    public String set(String key, String value) {
        return getJedis().set(key, value);
    }

    public String get(String key) {
        return getJedis().get(key);
    }

    public long delete(String ... keys) {
        return getJedis().del(keys);
    }

    public String setex(String key, int seconds, String value) {
        return getJedis().setex(key, seconds, value);
    }

    public boolean exists(String key) {
        return getJedis().exists(key);
    }

}
