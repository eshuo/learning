package com.example.tokenuserdemo.demos.web;

import com.example.tokenuserdemo.demos.domain.UserInfo;
import com.example.tokenuserdemo.demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Description @Author wangshuo @Date 2023-03-28 19:21 @Version V1.0
 */
@RestController
public class TokenController {

  @Autowired private UserService userService;

  // 请求 http://localhost:8081/token/XXX
  @RequestMapping("/token/{token}")
  public UserInfo token2UserInfo(@PathVariable("token") String token) {
    return userService.token2UserInfo(token);
  }
}
