package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-02-09 15:36
 * @Version V1.0
 */
@Configuration
public class MySecurityConfig {


    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }


//    JWT

    public TokenStore jwtTokenStore() {
      return new JwtTokenStore(jwtAccessTokenStore());
    }

    private JwtAccessTokenConverter jwtAccessTokenStore() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("singKey");
        return converter;
    }

}
