package com.example.redisdemo;

import com.example.redisdemo.utils.RedisUtils;
import java.util.ArrayList;
import java.util.List;
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

//        System.err.println("1111");
//
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 5; j++) {
//                redisUtils.hashPutOne("hashDemo", "key" + i, "value" + j);
//            }
//        }
//
//        final Map<Object, Object> hashDemo = redisUtils.hashGetAll("hashDemo");
//
//        Assert.notNull(hashDemo, "error");

//        List<Object> ssoAccessTokenList =  new ArrayList<>();
//        ssoAccessTokenList.add("fb54b42edafa4f308270c94d8f37dec1");
//        ssoAccessTokenList.add("fb54b42edafa4f308270c94d8f37dec2");
//        ssoAccessTokenList.add("fb54b42edafa4f308270c94d8f37dec4");
//        ssoAccessTokenList.add("fb54b42edafa4f308270c94d8f37dec4");
//        ssoAccessTokenList.add("fb54b42edafa4f308270c94d8f37dec4");
//
//        redisUtils.del(ssoAccessTokenList.stream().distinct().map(String::valueOf).toArray(String[]::new));

        //删除单点accessToken
        final String redisKey = "AUTH_SSO_TOKEN_KEY:".concat("user:Android:fb54b42edafa4f308270c94d8f37dec4");


        redisUtils.listAddInEnd(redisKey,"demo1");
        redisUtils.listAddInEnd(redisKey,"demo2");

        redisUtils.expireKey(redisKey,1000);

        List<Object> ssoAccessTokenList = redisUtils.listGetByRange(redisKey, 0, -1);
        if (null != ssoAccessTokenList) {
//            SsoConstants.SSO_CACHE_OAUTH_ACCESS_TOKEN.concat(accessToken)

            redisUtils.del(ssoAccessTokenList.stream().distinct().map(s -> "sso:oauthAccessToken:".concat(s.toString())).toArray(String[]::new));
//            ssoAccessTokenList.stream().distinct().map(String::valueOf).forEach(s -> {
            redisUtils.listRemove(redisKey, 0, -1);
//            });
        }
        if (redisUtils.exists(redisKey)) {
            ssoAccessTokenList = redisUtils.listGetByRange(redisKey, 0, -1);
            Assert.notNull(ssoAccessTokenList, "删除失败");
        } else {
            System.out.println("不存在");
        }
//        9c8eb3eb321b42679cb474d46ceb6967

    }

}
