package com.example.qlexpressdemo.service.impl;

import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.service.IConditionInfoService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-24 16:16
 * @Version V1.0
 */
@SpringBootTest
class ConditionInfoServiceImplTest {

    @Autowired
    private IConditionInfoService iConditionInfoService;

    @Test
    public void testAll(){





        final List<ConditionInfo> all =


                iConditionInfoService.getAll();


        Assert.assertNotNull(all);

    }



}