package com.example.oauth2.config;

import com.example.oauth2.entity.User;
import com.example.oauth2.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-11-01 10:36
 * @Version V1.0
 */
@Configuration
@Order(30)
//@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/logout", "/api/token").permitAll()
                .antMatchers("/api/**").authenticated()
                .and().csrf().disable().cors().disable().httpBasic();
//        http.csrf().disable().httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            final User authInfo = userService.findByUser(username);
            if (null != authInfo) {
                return new org.springframework.security.core.userdetails.User(authInfo.getId(), authInfo.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("USER")));
//                return new org.springframework.security.core.userdetails.User(authInfo.getId(), authInfo.getPassword(), authInfo.getEnabled(), authInfo.getAccountNonExpired(), authInfo.getCredentialsNonExpired(), authInfo.getAccountNonLocked(), grantedAuthorities);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
//    }
//
//    private ClientRegistration googleClientRegistration() {
//        return ClientRegistration.withRegistrationId("google")
//                .clientId("google-client-id")
//                .clientSecret("google-client-secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
//                .scope("openid", "profile", "email", "address", "phone")
//                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//                .clientName("Google")
//                .build();
//    }


}
