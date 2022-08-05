package com.wyci.mogodbdemo.service;

import com.wyci.mogodbdemo.entity.UserDynamic;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-29 14:02
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoDynamicServiceTest {

    @Autowired
    private UserDynamicService userDynamicService;

    @Test
    public void defaultSave() {

        UserDynamic userDynamic = new UserDynamic();
        userDynamic.setId(DateUtil.formatAsDatetime(new Date()).replaceAll("-", "").replaceAll(":", "").replaceAll("T", ""));
        userDynamic.setName("testSaveName");
        userDynamic.setMessage("testSaveMessage");

        HashMap<String, Object> map = new HashMap<>();
        int i = 0;
        map.put("cName_" + i++, "列1");
        map.put("cName_" + i++, "列2");
        map.put("cName_" + i++, "列3");
        map.put("cName_" + i++, "列4");
        map.put("cName_" + i++, "列5");
//        userDynamic.setDataMap(map);

        final UserDynamic userDynamic1 = userDynamicService.defaultSave(userDynamic);

        assertNotNull(userDynamic1);


    }

    @Test
    public void defaultUpdate() {

    }

    @Test
    public void defaultDelete() {
    }

    @Test
    public void defaultDeleteById() {
    }

    @Test
    public void find() {
        final UserDynamic userDynamic = userDynamicService.find("20220729140416");

        assertNotNull(userDynamic);

    }

    @Test
    public void findAll() {
    }
}