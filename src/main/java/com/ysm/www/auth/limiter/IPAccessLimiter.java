package com.ysm.www.auth.limiter;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/17
 * @Version V1.0
 **/
@Component
@Data
@ConfigurationProperties(prefix = "limit")
public class IPAccessLimiter {

    
    public static final String IP_PREFIX = "ip:";
    private StringRedisTemplate redisTemplate;
    private long windowSizeInMilliseconds = 60 * 1000L;

    private long maxCount = 60;

    public IPAccessLimiter(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean ifAllowed(String ip) {
        String key = IP_PREFIX + ip;
        Object times = redisTemplate.opsForValue().get(key);
        if (!Objects.nonNull(times)) {
            redisTemplate.opsForValue().set(key, "1", windowSizeInMilliseconds, TimeUnit.MILLISECONDS);
            return true;
        }
        Long increment = redisTemplate.opsForValue().increment(key);
        return increment < maxCount;
    }

}
