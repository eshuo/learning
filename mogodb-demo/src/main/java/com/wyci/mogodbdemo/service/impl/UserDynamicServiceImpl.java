package com.wyci.mogodbdemo.service.impl;

import com.wyci.mogodbdemo.dao.UserDynamicDao;
import com.wyci.mogodbdemo.entity.UserDynamic;
import com.wyci.mogodbdemo.service.UserDynamicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-29 13:50
 * @Version V1.0
 */
@Service
public class UserDynamicServiceImpl implements UserDynamicService {

    @Resource
    private UserDynamicDao userDynamicDao;

    @Override
    public UserDynamicDao getMongoRepository() {
        return userDynamicDao;
    }

    @Override
    public UserDynamic find(String id) {
        return userDynamicDao.findById(id).orElse(null);
    }

    @Override
    public List<UserDynamic> findAll() {
        final List<UserDynamic> all = userDynamicDao.findAll();
        return all;
    }
}
