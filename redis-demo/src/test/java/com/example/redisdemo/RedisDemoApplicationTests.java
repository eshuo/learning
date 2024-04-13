package com.example.redisdemo;

import com.example.redisdemo.utils.RedisUtils;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class RedisDemoApplicationTests {

//    @Autowired
//    private RedisTestService redisTestService;


    @Autowired
    private RedisUtils redisUtils;

//  @Autowired
//  private RedisUtils redisUtils;

    @Test
    void contextLoads() {
//        redisTestService.test();
//        redisTestService.test();
//        redisTestService.cacheable();
//        redisTestService.demo();
//        redisTestService.cacheableKey("demo");
//        redisTestService.cacheableKey("demo1");
//        redisTestService.cacheableKeyValue("demodemo");
//        redisTestService.cacheableKey("demo2");
//
//        redisTestService.updateCacheableKey("demo");
//        redisTestService.updateCacheableKey("demo1");
//        redisTestService.updateCacheableKey("demo2");
//
//        redisTestService.deleteCacheableKey("demo");
//        redisTestService.deleteCacheableKeyAll("demo1");
//
//        redisTestService.updateCacheableKey("demo");
//        redisTestService.cacheableKey("demo1");
//
//        redisTestService.deleteCacheable();
//        ;
//
//        redisTestService.updateCacheableKey("demo2");
//
//        redisTestService.caching("demo2");

        System.err.println("1111");

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                redisUtils.hashPutOne("hashDemo", "key" + i, "value" + j);
            }
        }

        final Map<Object, Object> hashDemo = redisUtils.hashGetAll("hashDemo");

        Assert.notNull(hashDemo, "error");


    }

}
