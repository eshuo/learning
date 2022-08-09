package com.wyci.mogodbdemo.dao;

import com.wyci.mogodbdemo.entity.User;
import com.wyci.mongo.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-18 16:17
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTest {

    @Autowired
    private UserDao userDao;


    @Test //保存
    public void testsave() {
        User user = new User("1", "test", "123");
        userDao.save(user);
    }

    @Test //更新
    public void testUpdate() {
        User user = new User("1", "test123", "12345");
        userDao.save(user);
    }

    @Test //删除
    public void testDelete() {
        userDao.deleteById("1");
    }


    @Test
    public void testSaveAll() {

        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            users.add(new User(String.valueOf(200 + i), "name" + i, "msg" + i, i));
        }

        userDao.saveAll(users);


    }


}