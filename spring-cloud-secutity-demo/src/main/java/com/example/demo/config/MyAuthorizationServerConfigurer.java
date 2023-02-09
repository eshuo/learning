package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-02-09 14:53
 * @Version V1.0
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;


    //令牌配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_id")
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .authorizedGrantTypes("password", "refresh_token", "authorization_code")//授权类型  "qr_code", "sms_code",
                .resourceIds("res1")
                .scopes("all")//授权范围
                .autoApprove(false)//false 跳转授权页面
                .redirectUris("https://www.google.com");
//        http://localhost:8080/oauth/authorize?client_id=client_id&response_type=code&scope=all&redirect_uri=https://www.google.com
//        http://localhost:8080/oauth/token?client_id=client_id&client_secret=secret&grant_type=authorization_code&code=G384zs&redirect_uri=https://www.google.com
    }

    //管理令牌
    @Bean
    public AuthorizationServerTokenServices tokenServices() {

        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);
        service.setTokenStore(tokenStore);
        service.setSupportRefreshToken(true);
        service.setAccessTokenValiditySeconds(7200);//2H
        service.setRefreshTokenValiditySeconds(259200);//3D
        return service;
    }




//    授权码
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }


    //访问端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices())
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }


    //端点策略配置
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }
}
