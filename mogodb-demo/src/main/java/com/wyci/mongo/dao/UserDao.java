package com.wyci.mongo.dao;

import com.wyci.mogodbdemo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-18 16:14
 * @Version V1.0
 */

public interface UserDao extends MongoRepository<User,String> {


}
