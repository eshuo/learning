package com.example.oauth2.provider;

import com.example.oauth2.config.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-11-09 17:24
 * @Version V1.0
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private MyUserDetailsService myUserDetailsService;



    /**
     * 认证方法
     *
     * @param authentication
     *
     * @return
     *
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authentication.getPrincipal().toString());
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    /**
     * 判断是不是使用此 provider 进行认证操作，返回true 则在认证的时候执行上面的 authenticate() 方法
     *
     * @param authentication
     *
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
