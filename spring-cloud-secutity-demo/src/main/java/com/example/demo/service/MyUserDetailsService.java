package com.example.demo.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-02-09 16:01
 * @Version V1.0
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(new User("test", "123", Collections.singleton(new SimpleGrantedAuthority("role1"))));
//        manager.createUser(new User("demo", "123", Collections.singleton(new SimpleGrantedAuthority("role1"))));
//        return manager;
        return new User(username, new BCryptPasswordEncoder().encode("123456"), Collections.singleton(new SimpleGrantedAuthority("role1")));
    }
}
