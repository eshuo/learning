package com.wyci.opendemo;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableKnife4j
public class OpenDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenDemoApplication.class, args);
  }
}
