//package com.example.oauth2.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @Description
// * @Author wangshuo
// * @Date 2022-11-01 10:36
// * @Version V1.0
// */
//@Configuration
//@Order(30)
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        //自定义认证
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
//        http.csrf().disable()
//                //授权
//                .authorizeRequests()
//                .antMatchers("/oauth/**", "/login/**", "/logout/**")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .permitAll();
////        http.csrf().disable().httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
//        return http.build();
//    }
//
//
//    /**
//     * OAuth2AuthenticationManager 来处理我们密码模式的密码
//     *
//     * @return org.springframework.security.authentication.AuthenticationManager
//     *
//     * @author wanglufei
//     * @date 2022/4/11 10:44 PM
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }
//
//    /**
//     * 自定义加密逻辑
//     *
//     * @return org.springframework.security.crypto.password.PasswordEncoder
//     *
//     * @author wanglufei
//     * @date 2022/4/11 6:32 PM
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//}
