package com.wyci.mogodbdemo.dao;

import com.wyci.mogodbdemo.entity.UserSnapshot;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-18 16:14
 * @Version V1.0
 */

public interface UserSnapshotDao extends MongoRepository<UserSnapshot,String> {


}
