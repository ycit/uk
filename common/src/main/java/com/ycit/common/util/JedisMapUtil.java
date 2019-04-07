package com.ycit.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * jedis map util
 *
 * @author uk
 * 2019/4/6 12:09
 */
@Slf4j
public class JedisMapUtil {

    private static class JedisHolder {
        private static final JedisMapUtil instance = new JedisMapUtil();
    }

    private JedisMapUtil(){

    }

    public static JedisMapUtil getInstance() {
        return JedisHolder.instance;
    }

    private static Map<String, JedisPool> poolMap = new HashMap<>();

    public JedisPool getPool(String ip, Integer port) {
        Assert.notNull(ip, "ip 不能为空");
        Assert.notNull(port, "port 不能为空");
        String key = ip.concat(":").concat(String.valueOf(port));
        if (poolMap.containsKey(key)) {
            return poolMap.get(key);
        }
        JedisPool jedisPool = new JedisPool(poolConfig(), ip, port, Constant.REDIS_TIMEOUT);
        poolMap.put(key, jedisPool);
        return jedisPool;
    }

    private JedisPoolConfig poolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(Constant.REDIS_MAX_IDLE);
        poolConfig.setMaxTotal(Constant.REDIS_MAX_ACTIVE);
        poolConfig.setMaxWaitMillis(Constant.REDIS_MAX_WAIT);
        return poolConfig;
    }

    public Jedis getJedis(String ip, Integer port) {
        JedisPool jedisPool = getPool(ip, port);
        Jedis jedis = null;
        int retries = 0;
        do {
            try {
                jedis = jedisPool.getResource();
            } catch (Exception e) {
               log.error("get redis connection fail", e);
            }
        }while (jedis != null && retries < Constant.REDIS_RETRY_NUM);
        return jedis;
    }

    public void closeJedis(Jedis jedis, String ip, Integer port) {
        if (jedis != null) {
            getPool(ip, port).destroy();
        }
    }

}
