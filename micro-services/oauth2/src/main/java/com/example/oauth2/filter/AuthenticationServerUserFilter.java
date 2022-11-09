//package com.example.oauth2.filter;
//
//import com.example.oauth2.provider.UsernamePasswordAuthenticationProvider;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @Description
// * @Author wangshuo
// * @Date 2022-11-09 17:47
// * @Version V1.0
// */
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class AuthenticationServerUserFilter extends OncePerRequestFilter {
//
//    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
//    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
//    private String usernameParameter = "username";
//    private String passwordParameter = "password";
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//
//
//        String username = this.obtainUsername(request);
//        String password = this.obtainPassword(request);
//        if (username == null) {
//            username = "";
//        }
//
//        if (password == null) {
//            password = "";
//        }
//
//        username = username.trim();
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//
//
//        AuthorizationServerUser authorizationServerUser = AuthorizationServerUser.builder().userId("1").build();
//        AuthenticationServerUserToken authenticationServerUserToken = new AuthenticationServerUserToken(authorizationServerUser, authorizationServerUser.getUserId());
//        SecurityContextHolder.getContext().setAuthentication(authenticationServerUserToken);
//        filterChain.doFilter(request,response);
//    }
//
//
//    protected String obtainPassword(HttpServletRequest request) {
//        return request.getParameter(this.passwordParameter);
//    }
//
//    protected String obtainUsername(HttpServletRequest request) {
//        return request.getParameter(this.usernameParameter);
//    }
//}
