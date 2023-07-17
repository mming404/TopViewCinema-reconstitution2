package com.ysm.www.util.impl;


import com.ysm.www.util.IDataStore;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * @author Albumen
 * @date 2020/3/2
 */

/**
 * @author Albumen
 * @date 2020/3/2
 */
@Component
public class CustomDataStore implements IDataStore {
    private StringRedisTemplate stringRedisTemplate;

    private RedisTemplate<String,String> redisTemplate;

    public CustomDataStore(StringRedisTemplate redisTemplate) {
        this.stringRedisTemplate = redisTemplate;
    }

    @Override
    public boolean put(String key, String value, long expire) {
        stringRedisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public boolean remove(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.delete(key));
    }

    @Override
    public boolean refresh(String key, long expire) {
        return Boolean.TRUE.equals(stringRedisTemplate.expire(key, expire, TimeUnit.MILLISECONDS));
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void increment(String key) {
        stringRedisTemplate.opsForValue().increment(key);
    }

    @Override
    public Long getExpireTime(String key) {
        return stringRedisTemplate.opsForValue().getOperations().getExpire(key);
    }

//    @Override
//    public long getLastTokenTime(String key, Long currentTime) {
//        long lastTokenTime = (long) stringRedisTemplate.opsForValue().getOrDefault(key, currentTime);
//        stringRedisTemplate.opsForValue().
//    }
}