package com.example.mongo.utils;

import com.example.mongo.dao.UserInfoRepository;
import com.example.mongo.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-05 10:29
 * @Version V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoUtilsTest {


//    @Autowired
//    private MongoUtils mongoUtils;

    @Resource
    private UserInfoRepository userInfoRepository;

//    @Before
//    public void init() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(MongoDBAutoConfiguration.class);
//        mongoUtils= (MongoUtils) context.getBean("mongoUtils");
//    }

    @Test
    public void save() {

        UserInfo userInfo = new UserInfo("10", "test", "1234");

        List<String> tags = new ArrayList<>();
        tags.add("tags1");
        tags.add("tags2");
        userInfo.setTags(tags);
        userInfo.setAge(20);
//        mongoUtils.save(userInfo);

        userInfoRepository.save(userInfo);
    }

    @Test
    public void update() {

        UserInfo userInfo = new UserInfo("0", "test123", "12345");
//        userDao.update(user.getId(), user);
    }

    @Test
    public void delete() {
    }
}