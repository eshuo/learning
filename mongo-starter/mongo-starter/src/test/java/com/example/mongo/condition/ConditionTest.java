package com.example.mongo.condition;

import com.example.mongo.entity.UserInfo;
import com.example.mongo.service.MongoUserInfoService;
import com.example.mongo.utils.MongoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 条件查询测试
 * @Author wangshuo
 * @Date 2022-08-09 14:15
 * @Version V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConditionTest {

//    @Autowired
//    private MongoUtils mongoUtils;


    @Autowired
    private MongoUserInfoService mongoUserInfoService;

    @Test
    public void testType() {

        ConditionWrapper<UserInfo> conditionWrapper = new ConditionWrapper<>();

//                ALL,
//        List<String> tags = new ArrayList<>();
//        tags.add("tags1");
//        tags.add("tags2");
//        conditionWrapper.and("tags", ConditionType.ALL, tags);

//                GT,
//        conditionWrapper.and("age",ConditionType.GT,10);
//                GTE,
//        conditionWrapper.and("age",ConditionType.GTE,20);
//                IN,
//        conditionWrapper.and("name", ConditionType.IN, new String[]{"test", "test1", "test2", "test3"});
//        conditionWrapper.and("name", ConditionType.IN, Arrays.asList("test1","test"));
//                NOT_IN,
//        conditionWrapper.and("name", ConditionType.NOT_IN, Arrays.asList("test1","test"));
//                CONCAT,
//        conditionWrapper.and("name", ConditionType.CONCAT, "test");
//                START_LIKE,
//        conditionWrapper.and("name", ConditionType.START_LIKE, "test");
//                END_LIKE,
//        conditionWrapper.and("name", ConditionType.END_LIKE, "test");
//                IS,
//        conditionWrapper.and("name", ConditionType.IS, "test");
//                IS_NULL,
//        conditionWrapper.and("tags", ConditionType.IS_NULL, true);
        conditionWrapper.and("tags", ConditionType.IS_NULL, false);
//                LT,
//        conditionWrapper.and("age",ConditionType.LT,20);
//                LTE,
//        conditionWrapper.and("age",ConditionType.LTE,20);
//                MOD,
//                NE,
//        conditionWrapper.and("age",ConditionType.NE,20);
//                NIN,
//        conditionWrapper.and("name", ConditionType.NOT_IN, Arrays.asList("test1","test"));
//                NOT,

//                REGEX,
//        conditionWrapper.and("name",ConditionType.REGEX,".*test.*");
//                SIZE,
//        conditionWrapper.and("tags",ConditionType.SIZE,2);
//                TYPE,
//                SAMPLE_RATE;

//        final List<UserInfo> all = mongoUtils.findAll(conditionWrapper.toQuery(), UserInfo.class);
//
//        assert (null != all && !all.isEmpty());

//        final List<UserInfo> userInfos = mongoUserInfoService.find( new Query(Criteria.where("name").in(Arrays.asList("test1","test"))));
        final List<UserInfo> userInfos = mongoUserInfoService.find(conditionWrapper.toQuery());


        assert (null != userInfos && !userInfos.isEmpty());

    }


}
