package com.example.mongo.service;

import com.example.mongo.condition.ConditionType;
import com.example.mongo.condition.ConditionWrapper;
import com.example.mongo.entity.MongoDemo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-08 11:36
 * @Version V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoDemoTest {

    @Autowired
    private MongoDemoService mongoDemoService;

    @Test
//    @Transactional
    public void testSave() {

        MongoDemo mongoDemo = new MongoDemo();
        mongoDemo.setId("11111");
        mongoDemo.setName(null);


        mongoDemoService.save(mongoDemo);


        throw new RuntimeException("1111");


    }

    @Test
    public void testFind() {

        ConditionWrapper<MongoDemo> conditionWrapper = new ConditionWrapper<>();
        conditionWrapper.and("name", ConditionType.START_LIKE, "on");

        Query query = conditionWrapper.toQuery();
//        Query query = new Query(Criteria.where("name").regex("^.*yin.*$"));


        final List<MongoDemo> all = mongoDemoService.findAll(query);


        Assert.assertNotNull(all);





    }

}
