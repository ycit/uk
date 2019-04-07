package com.ycit.common.util;

/**
 * jedis util
 *
 * @author uk
 * 2019/4/7 11:55
 */
public class JedisUtil {

    private static class JedisHolder {
        private static final JedisUtil instance = new JedisUtil();
    }

    private JedisUtil(){

    }

    public static JedisUtil getInstance() {
        return JedisUtil.JedisHolder.instance;
    }



}
