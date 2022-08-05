package com.wyci.mogodbdemo.dao;

import com.wyci.mogodbdemo.entity.User;
import com.wyci.mogodbdemo.entity.UserSnapshot;
import com.wyci.mogodbdemo.utils.MongodbUtils;
import com.wyci.mogodbdemo.utils.ReflectUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-18 16:17
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoSnapshotDaoTest {

    @Autowired
    private UserSnapshotDao userSnapshotDao;


    @Test //保存
    public void testsave() {

        UserSnapshot snapshot = new UserSnapshot();

        snapshot.setId(String.valueOf(System.currentTimeMillis()));
        snapshot.setUserName(UUID.randomUUID().toString());
        HashMap<String, Object> map = new HashMap<>();

        map.put("num", 1);
        map.put("str", "2");
        map.put("obj", new User());

        snapshot.setResourceMap(map);


//        userSnapshotDao.save(snapshot);


        MongodbUtils.save(snapshot);


    }


    @Test
    public void saveMap() {
//    user_snapshot
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("_id", "001002");
//        map.put("num", 1);
//        map.put("str", "2");
//        map.put("obj", new User());
//        MongodbUtils.save(map,"user_snapshot");


        final String documentInvokeAnnotation = MongodbUtils.getCollectionName(UserSnapshot.class);

        System.err.println(documentInvokeAnnotation);


    }


    @Test //更新
    public void testUpdate() {


    }

    @Test //删除
    public void testDelete() {
    }


    @Test
    public void testSaveAll() {


        List<UserSnapshot> list = new ArrayList<>(100);

        for (int i = 0; i < 100; i++) {
            UserSnapshot snapshot = new UserSnapshot();
            snapshot.setId(UUID.randomUUID().toString());
            snapshot.setUserName(UUID.randomUUID().toString());
            HashMap<String, Object> map = new HashMap<>();
            if (i % 2 == 0) {
                map.put("num", 1);
            } else {
                map.put("str", "2");
            }
            snapshot.setResourceMap(map);
            list.add(snapshot);

        }
        userSnapshotDao.saveAll(list);


    }

    @Test
    public void countTest() {
        UserSnapshot snapshot = new UserSnapshot();
        HashMap<String, Object> map = new HashMap<>();
        map.put("num", 1);
        snapshot.setResourceMap(map);
        final long count = userSnapshotDao.count(Example.of(snapshot));

        System.err.println(count);


    }

    @Test
    public void addFields() throws Exception {

        UserSnapshot snapshot = new UserSnapshot();
        HashMap<String, Object> map = new HashMap<>();
        map.put("order", 1);
//        snapshot.setDataMap(map);


        final Object target = ReflectUtil.getTarget(snapshot, map);


        System.err.println(target);


    }


}