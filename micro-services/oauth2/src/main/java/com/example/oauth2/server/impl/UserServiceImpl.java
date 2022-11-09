package com.example.oauth2.server.impl;

import com.example.oauth2.entity.User;
import com.example.oauth2.server.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author eshuo
 * @Date 2022-11-09 09:49
 * @Version V1.0
 */
@Service
public class UserServiceImpl implements UserService {


    /**
     * 加密方法(此加密方式需要与注册加密保持一致)
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * 查找用户
     *
     * @param userName 用户名
     *
     * @return {@code User}
     */
    @Override
    public User findByUser(String userName) {
        final User user = new User();
        user.setUsername(userName);
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        return user;
    }
}
