package com.xzit.rental.utils;


import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * redis工具类配置
 */
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;



    //设置值
    public void set(String key, String value,Long timeout) {
        redisTemplate.opsForValue().set(key, value,timeout,TimeUnit.SECONDS);
    }


    //获取值
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    //删除值
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
