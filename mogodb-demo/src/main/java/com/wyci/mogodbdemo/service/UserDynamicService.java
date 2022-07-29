package com.wyci.mogodbdemo.service;

import com.wyci.mogodbdemo.entity.UserDynamic;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-18 16:16
 * @Version V1.0
 */
public interface UserDynamicService extends BaseMongoDataService, Serializable {




    UserDynamic find(String id);

    List<UserDynamic> findAll();


}
