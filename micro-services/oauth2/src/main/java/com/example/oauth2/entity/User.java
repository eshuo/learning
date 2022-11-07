package com.example.oauth2.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-11-01 11:14
 * @Version V1.0
 */
@Data
public class User implements Serializable {


    private String id;


    private String password;

    private final String username;

    private final Set<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    /**
     * 手机号
     */
    private String mobile;

    /** 信息 */
    private String msg;


}
