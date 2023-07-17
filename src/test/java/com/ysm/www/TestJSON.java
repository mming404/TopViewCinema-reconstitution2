package com.ysm.www;

import com.alibaba.fastjson.JSONArray;
import com.ysm.www.auth.limiter.IPAccessLimiter;
import com.ysm.www.util.IDataStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@SpringBootTest
public class TestJSON {

    @Resource
    private IDataStore  dataStore;

    @Resource
    private StringRedisTemplate stringRedisTemplate;




    @Test
    void testLimiter() throws InterruptedException {
        IPAccessLimiter limiter = new IPAccessLimiter(stringRedisTemplate);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            if (limiter.ifAllowed("123.123.123.123")){
                System.out.println("ip访问通过");
            }else {
                System.out.println("ip访问被拒绝");
            }
        }
    }

}
