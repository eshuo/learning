package com.example.oauth2.server;

import com.example.oauth2.entity.User;

import java.io.Serializable;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-11-01 11:13
 * @Version V1.0
 */
public interface UserService extends Serializable {


    /**
     * 查找用户
     *
     * @param userName 用户名
     * @return {@code User}
     */
    User findByUser(String userName);

}
