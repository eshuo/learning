package com.wyci.mogodbdemo.dao;

import com.wyci.mogodbdemo.entity.UserDynamic;
import com.wyci.mogodbdemo.utils.MongodbUtils;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-29 09:49
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDynamicTest {


    @Test
    public void testSave() {

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

        userDynamic.setDataMap(map);

        MongodbUtils.save(userDynamic);


    }

    @Test
    public void update() {
//        UserDynamic userDynamic = new UserDynamic();
//        userDynamic.setId("20220729100549");
//
//        HashMap<String, Object> map = new HashMap<>();
//
//
//        map.put("cName_0", "列444");
//        userDynamic.setDataMap(map);

        MongodbUtils.updateMultiOne("_id", "20220729100549", "dataMap.cName_0", "列4445", MongodbUtils.getDocumentInvokeAnnotation(UserDynamic.class));
    }


    @Test
    public void del() {

        UserDynamic userDynamic = new UserDynamic();
        userDynamic.setId("2022-07-29T09:59:33");

        MongodbUtils.remove(userDynamic);

    }


}
