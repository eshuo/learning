package com.example.mybatisdemo.service;

import com.example.mybatisdemo.entity.Clazz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-27 15:28
 * @Version V1.0
 */
@SpringBootTest
class IClassServiceTest {

    @Autowired
    private IClassService iClassService;

    @Test
    void getClazz() {

        final Clazz clazz = iClassService.getClazz(1);
        final Clazz clazz2 = iClassService.getClazz2(2);

        System.out.println("clazz==>" + clazz.toString());

    }

    @Test
    void getClazz2() {
    }

    @Test
    void getTeacher() {
    }

    @Test
    void getStuList() {
    }
}