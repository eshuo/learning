package com.example.redisdemo.service;

import java.util.Random;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author wangshuo
 * @Date 2024-07-11 17:04
 * @Version V1.0
 */
@SpringBootTest
public class RedisCountServiceTest {



    @Test
    public void test2() {
        Random random = new Random();
        System.out.println(random.nextInt(66));
    }

}
