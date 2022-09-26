package com.example.mybatisdemo.service;

import com.example.mybatisdemo.dao.ILoginService;
import com.example.mybatisdemo.dao.impl.LoginServiceImpl;
import com.example.mybatisdemo.entity.Clazz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-27 15:28
 * @Version V1.0
 */
@SpringBootTest
class LogServiceTest {

    @Autowired
    private ILoginService loginService;

    @Test
    public void test() throws IOException {

        loginService.csv();
//        loginService.init();
    }
}