package com.wyci.mogodbdemo.utils;

import com.wyci.mogodbdemo.entity.UserDynamic;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-03 16:17
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBHelperTest {


    @Autowired
    private MongoDBHelper mongoDBHelper;


    @Test
    public void getMongoTemplate() {
    }

    @Test
    public void createCollection() {
    }

    @Test
    public void createIndex() {
    }

    @Test
    public void getAllIndexes() {
    }

    @Test
    public void insert() {

        UserDynamic userDynamic = new UserDynamic();


        userDynamic.setId(DateUtil.formatAsDatetime(new Date()).replaceAll("-", "").replaceAll(":", "").replaceAll("T", ""));

        userDynamic.setName("testSaveName");
        userDynamic.setMessage("testSaveMessage");

        HashMap<String, Object> map = new HashMap<>();


        int i = 10;
        map.put("cName_" + i++, "列1");
        map.put("cName_" + i++, "列2");
        map.put("cName_" + i++, "列3");
        map.put("cName_" + i++, "列4");
        map.put("cName_" + i++, "列5");

        userDynamic.setDataMap(map);


        mongoDBHelper.insert(userDynamic);

    }

    @Test
    public void testInsert() {
    }

    @Test
    public void insertMulti() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void testUpdateById() {
    }


    @Test
    public void update() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void createUpdate() {
    }

    @Test
    public void deleteById() {

        final boolean deleteById = mongoDBHelper.deleteById("20220803162802", UserDynamic.class);


        assertTrue(deleteById);

    }

    @Test
    public void testDeleteById() {
    }

    @Test
    public void selectById() {


        final UserDynamic dynamic = mongoDBHelper.selectById("20220803162802", UserDynamic.class);

        assertNotNull(dynamic);

    }

    @Test
    public void testSelectById() {
    }

    @Test
    public void deleteByObjectParam() {
    }

    @Test
    public void deleteByQuery() {
    }

    @Test
    public void selectList() {


//        selectList


        UserDynamic userDynamic = new UserDynamic();

//        userDynamic.setName("testSaveName");

        HashMap<String, Object> map = new HashMap<>();


        int i = 10;
        map.put("cName_" + i++, "列1");
        userDynamic.setDataMap(map);

        final List<UserDynamic> ts =mongoDBHelper.selectList(userDynamic);


        assertNotNull(ts);

    }

    @Test
    public void testSelectList() {
    }

    @Test
    public void selectByParam() {
    }

    @Test
    public void selectByQuery() {

//        selectByQuery

        //TODO 集合查询拼接问题
        Criteria criteria = Criteria.where("dataMap.cName_10").is("列1");

        Query query = new Query();
        query.addCriteria(criteria);

        final List<UserDynamic> objects = mongoDBHelper.selectByQuery(query, UserDynamic.class);

        assertNotNull(objects);

    }

    @Test
    public void testSelectByQuery() {
    }

    @Test
    public void selectOneByObjectParam() {
    }

    @Test
    public void testSelectOneByObjectParam() {
    }

    @Test
    public void selectOneByQuery() {
    }

    @Test
    public void testSelectOneByQuery() {
    }

    @Test
    public void countByObjectParam() {
    }

    @Test
    public void countByQuery() {
    }

    @Test
    public void pageByObjectParam() {
    }

    @Test
    public void pageByQuery() {
    }

    @Test
    public void aggregatePage() {
    }

    @Test
    public void aggregate() {
    }

    @Test
    public void aggregateData() {
    }

    @Test
    public void queryAddCriteria() {
    }

    @Test
    public void createCriteria() {
    }
}