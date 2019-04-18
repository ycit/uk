package com.ycit.common.util;

/**
 * 常量汇集地
 *
 * @author uk
 * 2019/4/6 19:52
 */
public class Constant {

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    public static int REDIS_MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    public static int REDIS_MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    public static int REDIS_MAX_WAIT = 10000;

    public static int REDIS_TIMEOUT = 10000;

    public static int REDIS_RETRY_NUM = 5;

    public static final long TOKEN_EXPIRATION_MS = 30*60*60*1000;

    public static final int TOKEN_EXPIRATION_SECOND = 30*60*60;

//    public static final String TOKEN_SIGN_KEY = "xiaoruixisssddwdwsawsdwwqqqqwdadwfadqfwasdafwafsddddddddddddddddddddddddddddd";
    public static final String TOKEN_SIGN_KEY = "mzIHYyThEF3XsCwPFgvA/+ncdbTQWoVFM+AgZ6BJ3FE=";

}
