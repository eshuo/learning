package com.wyci.mogodbdemo.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyci.mogodbdemo.entity.UserDynamic;
import com.wyci.mogodbdemo.utils.GsonUtil;
import com.wyci.mogodbdemo.utils.MongodbUtils;
import com.wyci.mogodbdemo.utils.ReflectUtil;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-29 09:49
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoDynamicTest {


    @Autowired
    private ObjectMapper objectMapper;

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

//        userDynamic.setDataMap(map);




//        final Object target = ReflectUtil.getTarget(userDynamic, map);


        final HashMap<String, Object> propertiesMap = ReflectUtil.getPropertiesMap(userDynamic, map);


        assert (propertiesMap != null);

//        MongodbUtils.save(userDynamic);
        MongodbUtils.save(propertiesMap, MongodbUtils.getCollectionName(UserDynamic.class));


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

        MongodbUtils.updateMultiOne("_id", "20220729100549", "dataMap.cName_0", "列4445", MongodbUtils.getCollectionName(UserDynamic.class));
    }


    @Test
    public void del() {

        UserDynamic userDynamic = new UserDynamic();
        userDynamic.setId("2022-07-29T09:59:33");

        MongodbUtils.remove(userDynamic);

    }

    @Test
    public void test1() throws JsonProcessingException {

//        UserDynamic dynamic = objectMapper.readValue("{\n" +
//                "    \n" +
//                "        \"id\": \"20220729140489\",\n" +
//                "        \"name\": \"testSaveName88\",\n" +
//                "        \"message\": \"testSaveMessage88\",\n" +
//                "        \"cName_0\": \"列1\",\n" +
//                "        \"cName_1\": \"列2\",\n" +
//                "        \"cName_2\": \"列3\",\n" +
//                "        \"cName_3\": \"列4\",\n" +
//                "        \"cName_4\": \"列5\"\n" +
//                "   \n" +
//                "}", UserDynamic.class);
//
//        System.err.println(dynamic);


        Map<String, Object> map = new HashMap<>();

        map.put("id", "20220729140489");
        map.put("name", "testSaveName88");
        map.put("message", "testSaveMessage88");
        map.put("cName_0", "列1");
        map.put("cName_1", "列2");
        map.put("cName_2", "列3");
        map.put("cName_3", "列4");
        map.put("cName_4", "列5");

        final UserDynamic dynamic = objectMapper.readValue(GsonUtil.toJson(map), UserDynamic.class);
        assert (null != dynamic);

//        final UserDynamic mapToObject = GsonUtil.mapToObject(map, UserDynamic.class);
//
//        assert (null != mapToObject);


    }


    @Test
    public void get() {


//        final Map byId = MongodbUtils.findById("20220802151437", Map.class);
        final UserDynamic dynamic = MongodbUtils.findById("20220802151437", UserDynamic.class);


        Map<String, Object> map = (Map<String, Object>) MongodbUtils.findOne(new HashMap<String, Object>(), new String[]{"_id"}, new Object[]{"20220802151437"}, MongodbUtils.getCollectionName(UserDynamic.class));


        assert (null != map);


    }


}
