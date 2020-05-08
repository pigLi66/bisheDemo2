package com.lsl.demo.utils.redis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author lisiliang
 * @since 2020/4/2
 */
public class RedisUtil {

    private static final JedisPool JEDIS_POOL;

    static {
        JEDIS_POOL =
                new JedisPool(new GenericObjectPoolConfig(), "47.115.126.5", 6379, 30000, "1998", 0);
    }

    public static void set(String key, Object value) {
        try(Jedis jedis = getJedis()) {
            jedis.set(key, JSON.toJSONString(value));
        }
    }

    public static <T> T get(String key, Class<T> clazz) {
        try (Jedis jedis = getJedis()){
            return JSON.parseObject(jedis.get(key), clazz);
        }
    }

    public static Jedis getJedis() {
        return JEDIS_POOL.getResource();
    }

}
