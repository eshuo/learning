package com.example.tokenuserdemo.demos.web;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description @Author wangshuo @Date 2023-03-31 10:18 @Version V1.0
 */
@Controller
public class WebController {

  @Value("${iam.token.path}")
  private String iamTokenPath;

  @GetMapping("/")
  public String activeProfile(Model model) {
    Map<String, Object> map = new HashMap<>();
    map.put("iamPath", iamTokenPath);
    model.addAllAttributes(map);
    return "index";
  }
}
