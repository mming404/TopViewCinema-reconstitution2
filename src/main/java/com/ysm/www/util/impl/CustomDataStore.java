package com.ysm.www.util.impl;


import com.ysm.www.util.IDataStore;
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
    private StringRedisTemplate redisTemplate;

    public CustomDataStore(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean put(String key, String value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public boolean remove(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    @Override
    public boolean refresh(String key, long expire) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS));
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void increment(String key) {
        redisTemplate.opsForValue().increment(key);
    }
}