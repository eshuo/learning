package com.example.oauth2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private String id;

    private String password;

    private String username;

    private Set<GrantedAuthority> authorities;

    /**
     * 账户不过期
     */
    private boolean accountNonExpired;

    /**
     * 非锁定账户
     */
    private boolean accountNonLocked;

    /**
     * 凭证不过期
     */
    private boolean credentialsNonExpired;

    /**
     * 启用
     */
    private boolean enabled;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 信息
     */
    private String msg;


}
